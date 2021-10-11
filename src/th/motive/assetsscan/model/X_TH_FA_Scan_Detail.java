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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for TH_FA_Scan_Detail
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_TH_FA_Scan_Detail extends PO implements I_TH_FA_Scan_Detail, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211011L;

    /** Standard Constructor */
    public X_TH_FA_Scan_Detail (Properties ctx, int TH_FA_Scan_Detail_ID, String trxName)
    {
      super (ctx, TH_FA_Scan_Detail_ID, trxName);
      /** if (TH_FA_Scan_Detail_ID == 0)
        {
			setTH_FA_Scan_Detail_ID (0);
			setTH_FA_Scan_ID (0);
        } */
    }

    /** Load Constructor */
    public X_TH_FA_Scan_Detail (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_TH_FA_Scan_Detail[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_A_Asset getA_Asset() throws RuntimeException
    {
		return (org.compiere.model.I_A_Asset)MTable.get(getCtx(), org.compiere.model.I_A_Asset.Table_Name)
			.getPO(getA_Asset_ID(), get_TrxName());	}

	/** Set Asset.
		@param A_Asset_ID 
		Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID < 1) 
			set_Value (COLUMNNAME_A_Asset_ID, null);
		else 
			set_Value (COLUMNNAME_A_Asset_ID, Integer.valueOf(A_Asset_ID));
	}

	/** Get Asset.
		@return Asset used internally or by customers
	  */
	public int getA_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Scan Date .
		@param ScanDate Scan Date 	  */
	public void setScanDate (Timestamp ScanDate)
	{
		set_Value (COLUMNNAME_ScanDate, ScanDate);
	}

	/** Get Scan Date .
		@return Scan Date 	  */
	public Timestamp getScanDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ScanDate);
	}

	/** Missing = Missing */
	public static final String TH_FA_PHYSICAL_STATUS_Missing = "Missing";
	/** Normal = Normal */
	public static final String TH_FA_PHYSICAL_STATUS_Normal = "Normal";
	/** damage = damage */
	public static final String TH_FA_PHYSICAL_STATUS_Damage = "damage";
	/** keep in another location = keep in another location */
	public static final String TH_FA_PHYSICAL_STATUS_KeepInAnotherLocation = "keep in another location";
	/** Set Physical Status .
		@param TH_FA_Physical_Status Physical Status 	  */
	public void setTH_FA_Physical_Status (String TH_FA_Physical_Status)
	{

		set_Value (COLUMNNAME_TH_FA_Physical_Status, TH_FA_Physical_Status);
	}

	/** Get Physical Status .
		@return Physical Status 	  */
	public String getTH_FA_Physical_Status () 
	{
		return (String)get_Value(COLUMNNAME_TH_FA_Physical_Status);
	}

	/** Set Scan Qty.
		@param TH_FA_ScanQty Scan Qty	  */
	public void setTH_FA_ScanQty (BigDecimal TH_FA_ScanQty)
	{
		set_Value (COLUMNNAME_TH_FA_ScanQty, TH_FA_ScanQty);
	}

	/** Get Scan Qty.
		@return Scan Qty	  */
	public BigDecimal getTH_FA_ScanQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TH_FA_ScanQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set FA Scan Detail.
		@param TH_FA_Scan_Detail_ID FA Scan Detail	  */
	public void setTH_FA_Scan_Detail_ID (int TH_FA_Scan_Detail_ID)
	{
		if (TH_FA_Scan_Detail_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TH_FA_Scan_Detail_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TH_FA_Scan_Detail_ID, Integer.valueOf(TH_FA_Scan_Detail_ID));
	}

	/** Get FA Scan Detail.
		@return FA Scan Detail	  */
	public int getTH_FA_Scan_Detail_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TH_FA_Scan_Detail_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TH_FA_Scan_Detail_UU.
		@param TH_FA_Scan_Detail_UU TH_FA_Scan_Detail_UU	  */
	public void setTH_FA_Scan_Detail_UU (String TH_FA_Scan_Detail_UU)
	{
		set_Value (COLUMNNAME_TH_FA_Scan_Detail_UU, TH_FA_Scan_Detail_UU);
	}

	/** Get TH_FA_Scan_Detail_UU.
		@return TH_FA_Scan_Detail_UU	  */
	public String getTH_FA_Scan_Detail_UU () 
	{
		return (String)get_Value(COLUMNNAME_TH_FA_Scan_Detail_UU);
	}

	public I_TH_FA_Scan getTH_FA_Scan() throws RuntimeException
    {
		return (I_TH_FA_Scan)MTable.get(getCtx(), I_TH_FA_Scan.Table_Name)
			.getPO(getTH_FA_Scan_ID(), get_TrxName());	}

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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getTH_FA_Scan_ID()));
    }

	public I_M_Locator getTH_FA_Scan_Location() throws RuntimeException
    {
		return (I_M_Locator)MTable.get(getCtx(), I_M_Locator.Table_Name)
			.getPO(getTH_FA_Scan_Location_ID(), get_TrxName());	}

	/** Set Scan Location .
		@param TH_FA_Scan_Location_ID Scan Location 	  */
	public void setTH_FA_Scan_Location_ID (int TH_FA_Scan_Location_ID)
	{
		if (TH_FA_Scan_Location_ID < 1) 
			set_Value (COLUMNNAME_TH_FA_Scan_Location_ID, null);
		else 
			set_Value (COLUMNNAME_TH_FA_Scan_Location_ID, Integer.valueOf(TH_FA_Scan_Location_ID));
	}

	/** Get Scan Location .
		@return Scan Location 	  */
	public int getTH_FA_Scan_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TH_FA_Scan_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}