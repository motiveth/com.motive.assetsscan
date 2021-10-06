package th.motive.assetsscan.osgi.component;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.osgi.service.component.annotations.Component;

import th.motive.assetsscan.model.I_TH_FA_Scan;
import th.motive.assetsscan.model.I_TH_FA_Scan_Detail;
import th.motive.assetsscan.model.I_TH_FA_Scan_Warehouse;
import th.motive.assetsscan.model.X_TH_FA_Scan;
import th.motive.assetsscan.model.X_TH_FA_Scan_Detail;
import th.motive.assetsscan.model.X_TH_FA_Scan_Warehouse;

@Component(
		 property= {"service.ranking:Integer=2"}
		 )
public class AssetScanModelFactory implements IModelFactory {

	@Override
	public Class<?> getClass(String tableName) {
		if (I_TH_FA_Scan_Detail.Table_Name.equals(tableName)) {
			return X_TH_FA_Scan_Detail.class;
		}else if (I_TH_FA_Scan.Table_Name.equals(tableName)) {
			return X_TH_FA_Scan.class;
		}else if (I_TH_FA_Scan_Warehouse.Table_Name.equals(tableName)) {
			return X_TH_FA_Scan_Warehouse.class;
		}else {
			return null;
		}
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		if (I_TH_FA_Scan_Detail.Table_Name.equals(tableName)) {
			return new X_TH_FA_Scan_Detail(Env.getCtx(), Record_ID, trxName);
		}else if (I_TH_FA_Scan.Table_Name.equals(tableName)) {
			return new X_TH_FA_Scan(Env.getCtx(), Record_ID, trxName);
		}else if (I_TH_FA_Scan_Warehouse.Table_Name.equals(tableName)) {
			return new X_TH_FA_Scan_Warehouse(Env.getCtx(), Record_ID, trxName);
		}else {
			return null;
		}
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		if (I_TH_FA_Scan_Detail.Table_Name.equals(tableName)) {
			return new X_TH_FA_Scan_Detail(Env.getCtx(), rs, trxName);
		}else if (I_TH_FA_Scan.Table_Name.equals(tableName)) {
			return new X_TH_FA_Scan(Env.getCtx(), rs, trxName);
		}else if (I_TH_FA_Scan_Warehouse.Table_Name.equals(tableName)) {
			return new X_TH_FA_Scan_Warehouse(Env.getCtx(), rs, trxName);
		}else {
			return null;
		}
	}

}
