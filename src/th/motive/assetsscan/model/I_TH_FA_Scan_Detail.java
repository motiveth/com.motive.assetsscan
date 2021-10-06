/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package th.motive.assetsscan.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for TH_FA_Scan_Detail
 *  @author iDempiere (generated) 
 *  @version Release 8.2
 */
@SuppressWarnings("all")
public interface I_TH_FA_Scan_Detail 
{

    /** TableName=TH_FA_Scan_Detail */
    public static final String Table_Name = "TH_FA_Scan_Detail";

    /** AD_Table_ID=1000002 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name M_Locator_ID */
    public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

	/** Set Locator.
	  * Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID);

	/** Get Locator.
	  * Warehouse Locator
	  */
	public int getM_Locator_ID();

	public I_M_Locator getM_Locator() throws RuntimeException;

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name ScanDate */
    public static final String COLUMNNAME_ScanDate = "ScanDate";

	/** Set Scan Date 	  */
	public void setScanDate (Timestamp ScanDate);

	/** Get Scan Date 	  */
	public Timestamp getScanDate();

    /** Column name SerNo */
    public static final String COLUMNNAME_SerNo = "SerNo";

	/** Set Serial No.
	  * Product Serial Number 
	  */
	public void setSerNo (String SerNo);

	/** Get Serial No.
	  * Product Serial Number 
	  */
	public String getSerNo();

    /** Column name TH_FA_Physical_Status */
    public static final String COLUMNNAME_TH_FA_Physical_Status = "TH_FA_Physical_Status";

	/** Set Physical Status 	  */
	public void setTH_FA_Physical_Status (String TH_FA_Physical_Status);

	/** Get Physical Status 	  */
	public String getTH_FA_Physical_Status();

    /** Column name TH_FA_ScanQty */
    public static final String COLUMNNAME_TH_FA_ScanQty = "TH_FA_ScanQty";

	/** Set Scan Qty	  */
	public void setTH_FA_ScanQty (BigDecimal TH_FA_ScanQty);

	/** Get Scan Qty	  */
	public BigDecimal getTH_FA_ScanQty();

    /** Column name TH_FA_Scan_Detail_ID */
    public static final String COLUMNNAME_TH_FA_Scan_Detail_ID = "TH_FA_Scan_Detail_ID";

	/** Set FA Scan Detail	  */
	public void setTH_FA_Scan_Detail_ID (int TH_FA_Scan_Detail_ID);

	/** Get FA Scan Detail	  */
	public int getTH_FA_Scan_Detail_ID();

    /** Column name TH_FA_Scan_Detail_UU */
    public static final String COLUMNNAME_TH_FA_Scan_Detail_UU = "TH_FA_Scan_Detail_UU";

	/** Set TH_FA_Scan_Detail_UU	  */
	public void setTH_FA_Scan_Detail_UU (String TH_FA_Scan_Detail_UU);

	/** Get TH_FA_Scan_Detail_UU	  */
	public String getTH_FA_Scan_Detail_UU();

    /** Column name TH_FA_Scan_ID */
    public static final String COLUMNNAME_TH_FA_Scan_ID = "TH_FA_Scan_ID";

	/** Set FA_Scan	  */
	public void setTH_FA_Scan_ID (int TH_FA_Scan_ID);

	/** Get FA_Scan	  */
	public int getTH_FA_Scan_ID();

	public I_TH_FA_Scan getTH_FA_Scan() throws RuntimeException;

    /** Column name TH_FA_Scan_Location_ID */
    public static final String COLUMNNAME_TH_FA_Scan_Location_ID = "TH_FA_Scan_Location_ID";

	/** Set Scan Location 	  */
	public void setTH_FA_Scan_Location_ID (int TH_FA_Scan_Location_ID);

	/** Get Scan Location 	  */
	public int getTH_FA_Scan_Location_ID();

	public I_M_Locator getTH_FA_Scan_Location() throws RuntimeException;

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
