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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for TH_FA_Scan_Warehouse
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_TH_FA_Scan_Warehouse extends PO implements I_TH_FA_Scan_Warehouse, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211006L;

    /** Standard Constructor */
    public X_TH_FA_Scan_Warehouse (Properties ctx, int TH_FA_Scan_Warehouse_ID, String trxName)
    {
      super (ctx, TH_FA_Scan_Warehouse_ID, trxName);
      /** if (TH_FA_Scan_Warehouse_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_TH_FA_Scan_Warehouse (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_TH_FA_Scan_Warehouse[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Scan Warehouse.
		@param TH_FA_Scan_Warehouse_ID Scan Warehouse	  */
	public void setTH_FA_Scan_Warehouse_ID (int TH_FA_Scan_Warehouse_ID)
	{
		if (TH_FA_Scan_Warehouse_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_TH_FA_Scan_Warehouse_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_TH_FA_Scan_Warehouse_ID, Integer.valueOf(TH_FA_Scan_Warehouse_ID));
	}

	/** Get Scan Warehouse.
		@return Scan Warehouse	  */
	public int getTH_FA_Scan_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TH_FA_Scan_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Scan Warehouse UU.
		@param TH_FA_Scan_Warehouse_UU Scan Warehouse UU	  */
	public void setTH_FA_Scan_Warehouse_UU (String TH_FA_Scan_Warehouse_UU)
	{
		set_ValueNoCheck (COLUMNNAME_TH_FA_Scan_Warehouse_UU, TH_FA_Scan_Warehouse_UU);
	}

	/** Get Scan Warehouse UU.
		@return Scan Warehouse UU	  */
	public String getTH_FA_Scan_Warehouse_UU () 
	{
		return (String)get_Value(COLUMNNAME_TH_FA_Scan_Warehouse_UU);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}