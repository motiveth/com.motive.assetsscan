package th.motive.assetsscan.process;

import java.util.List;

import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

import th.motive.assetsscan.model.I_TH_FA_Scan_Detail;
import th.motive.assetsscan.model.I_TH_FA_Scan_Warehouse;
import th.motive.assetsscan.model.X_TH_FA_Scan;
import th.motive.assetsscan.model.X_TH_FA_Scan_Detail;
import th.motive.assetsscan.model.X_TH_FA_Scan_Warehouse;

public class AssetConciliationScan extends SvrProcess {

	@Override
	protected void prepare() {

	}

	@Override
	protected String doIt() throws Exception {
		int assetScanId = getRecord_ID();

		X_TH_FA_Scan faScan = new X_TH_FA_Scan(Env.getCtx(), assetScanId, null);

		String scanDetailWhere = String.format("%s = ?", I_TH_FA_Scan_Detail.COLUMNNAME_TH_FA_Scan_ID);
		List<X_TH_FA_Scan_Detail> scanDetails = new Query(getCtx(), I_TH_FA_Scan_Detail.Table_Name, scanDetailWhere, get_TrxName()).setParameters(faScan.getTH_FA_Scan_ID()).list();

		String scanWarehouseExtractWhere = String.format("%s = ? AND %s = ? AND %s = ? AND %s = ? ",
				I_TH_FA_Scan_Warehouse.COLUMNNAME_TH_FA_Scan_ID, I_TH_FA_Scan_Warehouse.COLUMNNAME_AD_Org_ID,
				I_TH_FA_Scan_Warehouse.COLUMNNAME_M_Locator_ID, I_TH_FA_Scan_Warehouse.COLUMNNAME_Value);

		String scanWarehouseDiffLocatorWhere = String.format("%s = ? AND %s = ? AND %s = ? ",
				I_TH_FA_Scan_Warehouse.COLUMNNAME_TH_FA_Scan_ID, I_TH_FA_Scan_Warehouse.COLUMNNAME_AD_Org_ID,
				I_TH_FA_Scan_Warehouse.COLUMNNAME_Value);

		for (X_TH_FA_Scan_Detail scanDetail : scanDetails) {
			X_TH_FA_Scan_Warehouse scanWarehouse = new Query(getCtx(), I_TH_FA_Scan_Warehouse.Table_Name, scanWarehouseExtractWhere, get_TrxName()).
							setParameters(scanDetail.getTH_FA_Scan_ID(), scanDetail.getAD_Org_ID(),
							scanDetail.getM_Locator_ID(), scanDetail.getValue()).firstOnly();
			if (scanWarehouse == null) {
				scanWarehouse = new Query(getCtx(), I_TH_FA_Scan_Warehouse.Table_Name, scanWarehouseDiffLocatorWhere, get_TrxName()).
						setParameters(scanDetail.getTH_FA_Scan_ID(), scanDetail.getAD_Org_ID(),
						scanDetail.getValue()).firstOnly();
				if (scanWarehouse == null) {
					scanDetail.setTH_FA_Physical_Status(X_TH_FA_Scan_Detail.TH_FA_PHYSICAL_STATUS_Missing);
				}else {
					scanDetail.setTH_FA_Physical_Status(X_TH_FA_Scan_Detail.TH_FA_PHYSICAL_STATUS_KeepInAnotherLocation);
					scanDetail.setTH_FA_ScanQty(scanWarehouse.getQty());
					scanDetail.setTH_FA_Scan_Location_ID(scanWarehouse.getM_Locator_ID());
				}
			}else {
				scanDetail.setTH_FA_Physical_Status(X_TH_FA_Scan_Detail.TH_FA_PHYSICAL_STATUS_Normal);
				scanDetail.setTH_FA_ScanQty(scanWarehouse.getQty());
				scanDetail.setTH_FA_Scan_Location_ID(scanWarehouse.getM_Locator_ID());
			}

			scanDetail.saveEx();
		}

		return null;
	}

}
