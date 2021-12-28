package th.motive.assetsscan.process;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
				I_TH_FA_Scan_Warehouse.COLUMNNAME_M_Locator_ID, I_TH_FA_Scan_Warehouse.COLUMNNAME_A_Asset_ID);

		String scanWarehouseDiffLocatorWhere = String.format("%s = ? AND %s = ? AND %s = ? ",
				I_TH_FA_Scan_Warehouse.COLUMNNAME_TH_FA_Scan_ID, I_TH_FA_Scan_Warehouse.COLUMNNAME_AD_Org_ID,
				I_TH_FA_Scan_Warehouse.COLUMNNAME_A_Asset_ID);

		for (X_TH_FA_Scan_Detail scanDetail : scanDetails) {
			
			X_TH_FA_Scan_Warehouse scanWarehouse = new Query(getCtx(), I_TH_FA_Scan_Warehouse.Table_Name, scanWarehouseExtractWhere, null).
							setParameters(scanDetail.getTH_FA_Scan_ID(), scanDetail.getAD_Org_ID(),
									scanDetail.getM_Locator_ID(), scanDetail.getA_Asset_ID()).
							firstOnly();
			
			if (scanWarehouse == null) { // non match
				scanWarehouse = new Query(getCtx(), I_TH_FA_Scan_Warehouse.Table_Name, scanWarehouseDiffLocatorWhere, null).
						setParameters(scanDetail.getTH_FA_Scan_ID(), scanDetail.getAD_Org_ID(),
						scanDetail.getA_Asset_ID()).firstOnly();
				if (scanWarehouse == null) {// not found any
					scanDetail.setTH_FA_Physical_Status(X_TH_FA_Scan_Detail.TH_FA_PHYSICAL_STATUS_Missing);
				}else {// found on other locator
					scanDetail.setTH_FA_Physical_Status(X_TH_FA_Scan_Detail.TH_FA_PHYSICAL_STATUS_KeepInAnotherLocation);
					scanDetail.setTH_FA_ScanQty(scanWarehouse.getQty());
					scanDetail.setTH_FA_Scan_Location_ID(scanWarehouse.getM_Locator_ID());
					scanDetail.setTH_FA_Scan_Org_ID(scanWarehouse.getAD_Org_ID());
				}
			}else {
				scanDetail.setTH_FA_Physical_Status(X_TH_FA_Scan_Detail.TH_FA_PHYSICAL_STATUS_Normal);
				scanDetail.setTH_FA_ScanQty(scanWarehouse.getQty());
				scanDetail.setTH_FA_Scan_Location_ID(scanWarehouse.getM_Locator_ID());
			}

			if (scanWarehouse != null) {
				scanWarehouse.setProcessed(true);
				scanWarehouse.saveEx(get_TrxName());
			}
			
			scanDetail.saveEx();
		}
		
		String scanWarehouseUnprocessWhere = String.format("%s = ? AND %s = 'N'", I_TH_FA_Scan_Warehouse.COLUMNNAME_TH_FA_Scan_ID, I_TH_FA_Scan_Warehouse.COLUMNNAME_Processed);
		List<X_TH_FA_Scan_Warehouse> scanWarehouseUnprocess = new Query(getCtx(), I_TH_FA_Scan_Warehouse.Table_Name, scanWarehouseUnprocessWhere, get_TrxName()).
				setParameters(faScan.getTH_FA_Scan_ID()).list();
		for (X_TH_FA_Scan_Warehouse scanWarehouse : scanWarehouseUnprocess) {
			X_TH_FA_Scan_Detail detailOnOtherLocator = new X_TH_FA_Scan_Detail(getCtx(), 0, get_TrxName());
			detailOnOtherLocator.setAD_Org_ID(faScan.getAD_Org_ID());
			detailOnOtherLocator.setTH_FA_Scan_ID(faScan.getTH_FA_Scan_ID());
			detailOnOtherLocator.setA_Asset_ID(scanWarehouse.getA_Asset_ID());
			detailOnOtherLocator.setM_Locator_ID(scanWarehouse.getA_Asset().getM_Locator_ID());
			detailOnOtherLocator.setTH_FA_Scan_Location_ID(scanWarehouse.getM_Locator_ID());
			detailOnOtherLocator.setTH_FA_ScanQty(scanWarehouse.getQty());
			detailOnOtherLocator.setScanDate(Timestamp.valueOf(LocalDateTime.now()));
			detailOnOtherLocator.setTH_FA_Physical_Status(X_TH_FA_Scan_Detail.TH_FA_PHYSICAL_STATUS_KeepInAnotherLocation);
			detailOnOtherLocator.saveEx();
			
			scanWarehouse.setProcessed(true);
			scanWarehouse.saveEx();
		}

		return null;
	}

}
