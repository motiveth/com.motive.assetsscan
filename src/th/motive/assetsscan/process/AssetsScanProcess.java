package th.motive.assetsscan.process;

import java.util.List;
import java.util.logging.Level;

import org.compiere.model.I_AD_Sequence;
import org.compiere.model.I_A_Asset;
import org.compiere.model.MAsset;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import th.motive.assetsscan.model.X_TH_FA_Scan;
import th.motive.assetsscan.model.X_TH_FA_Scan_Detail;

public class AssetsScanProcess extends SvrProcess {	
	boolean isOrgLevel = false;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (ProcessInfoParameter element : para){
			String name = element.getParameterName();
			if (I_AD_Sequence.COLUMNNAME_IsOrgLevelSequence.equals(name))
				isOrgLevel = element.getParameterAsBoolean();
			else
				if (log.isLoggable(Level.INFO))log.log(Level.INFO, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		
		X_TH_FA_Scan scanHeader = new X_TH_FA_Scan(getCtx(), getRecord_ID(), null);
		
		/** Delete existing record if match found */
		StringBuilder sqlD = new StringBuilder("DELETE FROM TH_FA_Scan_Detail WHERE TH_FA_Scan_ID = ").
				append(getRecord_ID());
		
		DB.executeUpdate(sqlD.toString(), get_TrxName());

		Query assetQuery = null;
		
		if (isOrgLevel) {
			assetQuery = new Query(getCtx(), I_A_Asset.Table_Name, I_A_Asset.COLUMNNAME_AD_Org_ID + " = " + scanHeader.getAD_Org_ID(), null);
		}else {// locator level
			assetQuery = new Query(getCtx(), I_A_Asset.Table_Name, I_A_Asset.COLUMNNAME_M_Locator_ID + " = " + scanHeader.getM_Locator_ID(), null);
		}
		
		
		List<MAsset> assets = assetQuery.list();
			
		for (MAsset asset : assets) {
			// Insert line details
			insertDetail(asset, scanHeader);
		}
		return "Everything is OK! ";
	}

	private void insertDetail(MAsset asset, X_TH_FA_Scan scanHeader) {
		X_TH_FA_Scan_Detail scanDetail = new X_TH_FA_Scan_Detail(getCtx(), 0, get_TrxName());
		scanDetail.setAD_Org_ID(asset.getAD_Org_ID());
		scanDetail.setC_BPartner_ID(asset.getC_BPartner_ID());
		scanDetail.setDescription(asset.getDescription());
		scanDetail.setIsActive(asset.isActive());
		scanDetail.setM_Locator_ID(asset.getM_Locator_ID());
		scanDetail.setName(asset.getName());
		scanDetail.setSerNo(asset.getSerNo());
		scanDetail.setValue(asset.getValue());
		scanDetail.setTH_FA_Scan_ID(scanHeader.getTH_FA_Scan_ID());
		scanDetail.saveEx();
	}
	
	
}
