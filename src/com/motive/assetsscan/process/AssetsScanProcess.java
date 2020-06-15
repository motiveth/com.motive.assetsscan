package com.motive.assetsscan.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

import org.adempiere.model.GenericPO;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import com.motive.assetsscan.model.MTH_FA_Scan_Detail;
import com.motive.assetsscan.model.X_TH_FA_Scan_Detail;

public class AssetsScanProcess extends SvrProcess {

	/** Locator ID */
	private int locatorId;
	
	/** Table ID */
	private int recordId;
	
	/** Table ID */
	int AD_Table_ID = 0;
	String table_Name;
	
	// Asset line
	X_TH_FA_Scan_Detail a_line = null;
	
	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		ProcessInfoParameter[] parameters = getParameter();
		for (int i = 0; i < parameters.length; i++) {
			String name = parameters[i].getParameterName();
			locatorId = parameters[i].getParameterAsInt();
			//log.severe("Parameter : " + locatorId);
		}
		recordId = getRecord_ID();
		AD_Table_ID = getTable_ID();
		
		//log.severe("TableID : " + AD_Table_ID);
		//log.severe("RecordID : " + recordId);
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub	
		try
		{
			MTable table = new MTable (getCtx(), AD_Table_ID, get_TrxName());
			if (table.get_ID() == 0)
			    throw new IllegalArgumentException ("No AD_Table_ID=" + AD_Table_ID);
			String tableName = table.getTableName();// Get table name
			table_Name = tableName;
			log.severe("Table name : " + tableName);
			
			/** Delete existing record if match found */
			StringBuilder sqlD = new StringBuilder("DELETE FROM TH_FA_Scan_Detail WHERE TH_FA_Scan_ID =").append(recordId)
					.append("AND M_Locator_ID=").append(locatorId);
			int no = DB.executeUpdate(sqlD.toString(), get_TrxName());
			
			StringBuilder sql = new StringBuilder();
			
			// Get current record data incase need more than just ID
			sql.append("SELECT m_locator_id, documentno, th_fa_scan_id, ad_client_id, ad_org_id, created, createdby, isactive, th_fa_scan_uu, updated, updatedby, datedoc, docstatus, description, th_fa_scannedby FROM ").append(tableName).append(" WHERE m_locator_id = ? AND documentno = ?");
			
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
				pstmt.setInt(1, locatorId);
				pstmt.setInt(2, recordId);
				rs = pstmt.executeQuery();
				while (rs.next())
				{
					int Locator_ID = rs.getInt(1);
					int Document_NO = rs.getInt(2);
					log.severe("Locator_ID: " + Locator_ID);
					log.severe("Document_NO: " + Document_NO);
					
					StringBuilder sqlBuilder = new StringBuilder();
					// Get Asset data from master and insert into asset line details
					sqlBuilder.append("SELECT C_BPARTNER_ID, M_LOCATOR_ID, A_ASSET_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, NAME, DESCRIPTION, SERNO, VALUE FROM ").append("A_ASSET").append(" WHERE M_Locator_ID = ?");
					
					ResultSet rs1 = null;
					PreparedStatement pstmt1 = null;
					
					pstmt1 = DB.prepareStatement(sqlBuilder.toString(), get_TrxName());
					pstmt1.setInt(1,  locatorId);
					rs1 = pstmt1.executeQuery();
					while (rs1.next()) {
						int businessPartnerID = rs1.getInt(1);
						int locatorID = rs1.getInt(2);
						int assetID = rs1.getInt(3);
						int clientID = rs1.getInt(4);
						int orgID = rs1.getInt(5);
						String isActive = rs1.getString(6);
						String name = rs1.getString(7);
						String description = rs1.getString(8) != null ? rs1.getString(8) : "";
						String serielNo = rs1.getString(9);
						String value = rs1.getString(10);
						// Insert line details
						insertDetail(businessPartnerID, locatorID, assetID, clientID, orgID, isActive, name, description, serielNo, value);
					}
				}
			} finally {
				try {
					if (rs != null) rs.close();
					if (pstmt != null) pstmt.close();
				} catch (SQLException ex) {/*ignored*/
					log.severe(ex.toString());
				}
				rs = null;
				pstmt = null;
			}
			
		} catch (Exception e) {
			
		}
		return "Everything is OK! ";
	}

	private void insertDetail(int businessPartnerID, int locatorID2, int assetID, int clientID, int orgID, String isActive,
			String name, String description, String serielNo, String value) {
		try {
			
			PO newRecord = new GenericPO("TH_FA_Scan_Detail", getCtx(), 0);
			newRecord.set_ValueOfColumn("AD_Client_ID", clientID);
			newRecord.set_ValueOfColumn("AD_Org_ID", orgID);
			newRecord.set_ValueOfColumn("C_BPartner_ID", businessPartnerID);
			newRecord.set_ValueOfColumn("Description", description);
			newRecord.set_ValueOfColumn("IsActive", isActive);
			newRecord.set_ValueOfColumn("M_Locator_ID", locatorID2);
			newRecord.set_ValueOfColumn("Name", name);
			newRecord.set_ValueOfColumn("SerNo", serielNo);
			newRecord.set_ValueOfColumn("Value", value);
			newRecord.set_ValueOfColumn("TH_FA_SCAN_ID", recordId);
			newRecord.saveEx();
		} catch (Exception e) {
			// TODO: handle exception
			log.severe(e.toString());
		}
	}
	
	
}
