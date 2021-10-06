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
/** Generated Model - DO NOT CHANGE */
package th.motive.assetsscan.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for TH_FA_Scan
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_TH_FA_Scan extends PO implements I_TH_FA_Scan, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210926L;

    /** Standard Constructor */
    public X_TH_FA_Scan (Properties ctx, int TH_FA_Scan_ID, String trxName)
    {
      super (ctx, TH_FA_Scan_ID, trxName);
      /** if (TH_FA_Scan_ID == 0)
        {
			setTH_FA_Scan_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TH_FA_Scan (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_TH_FA_Scan[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Document Date.
		@param DateDoc 
		Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc)
	{
		set_Value (COLUMNNAME_DateDoc, DateDoc);
	}

	/** Get Document Date.
		@return Date of the Document
	  */
	public Timestamp getDateDoc () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateDoc);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Generate List.
		@param GenerateList 
		Generate List
	  */
	public void setGenerateList (String GenerateList)
	{
		set_Value (COLUMNNAME_GenerateList, GenerateList);
	}

	/** Get Generate List.
		@return Generate List
	  */
	public String getGenerateList () 
	{
		return (String)get_Value(COLUMNNAME_GenerateList);
	}

	public I_M_Locator getM_Locator() throws RuntimeException
    {
		return (I_M_Locator)MTable.get(getCtx(), I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Locator_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set FA_Scan.
		@param TH_FA_Scan_ID FA_Scan	  */
	public void setTH_FA_Scan_ID (int TH_FA_Scan_ID)
	{
		if (TH_FA_Scan_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TH_FA_Scan_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TH_FA_Scan_ID, Integer.valueOf(TH_FA_Scan_ID));
	}

	/** Get FA_Scan.
		@return FA_Scan	  */
	public int getTH_FA_Scan_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TH_FA_Scan_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TH_FA_Scan_UU.
		@param TH_FA_Scan_UU TH_FA_Scan_UU	  */
	public void setTH_FA_Scan_UU (String TH_FA_Scan_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TH_FA_Scan_UU, TH_FA_Scan_UU);
	}

	/** Get TH_FA_Scan_UU.
		@return TH_FA_Scan_UU	  */
	public String getTH_FA_Scan_UU () 
	{
		return (String)get_Value(COLUMNNAME_TH_FA_Scan_UU);
	}

	/** Set Scan Warehouse.
		@param TH_FA_Scan_Warehouse Scan Warehouse	  */
	public void setTH_FA_Scan_Warehouse (String TH_FA_Scan_Warehouse)
	{
		set_Value (COLUMNNAME_TH_FA_Scan_Warehouse, TH_FA_Scan_Warehouse);
	}

	/** Get Scan Warehouse.
		@return Scan Warehouse	  */
	public String getTH_FA_Scan_Warehouse () 
	{
		return (String)get_Value(COLUMNNAME_TH_FA_Scan_Warehouse);
	}

	/** Set Scanned By.
		@param TH_FA_ScannedBy Scanned By	  */
	public void setTH_FA_ScannedBy (String TH_FA_ScannedBy)
	{
		set_Value (COLUMNNAME_TH_FA_ScannedBy, TH_FA_ScannedBy);
	}

	/** Get Scanned By.
		@return Scanned By	  */
	public String getTH_FA_ScannedBy () 
	{
		return (String)get_Value(COLUMNNAME_TH_FA_ScannedBy);
	}
}