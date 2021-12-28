package th.motive.assetsscan.process;

import java.util.List;

import org.compiere.model.I_A_Asset;
import org.compiere.model.Query;
import org.compiere.model.X_A_Asset;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

import th.motive.assetsscan.model.I_TH_FA_Scan_Detail;
import th.motive.assetsscan.model.X_TH_FA_Scan;
import th.motive.assetsscan.model.X_TH_FA_Scan_Detail;

public class AssetLocatorCorrectByScan extends SvrProcess{
	@Override
	protected void prepare() {

	}

	@Override
	protected String doIt() throws Exception {
		int assetScanId = getRecord_ID();

		X_TH_FA_Scan faScan = new X_TH_FA_Scan(Env.getCtx(), assetScanId, null);
		
		String scanDetailWhere = String.format("%s = ? AND %s = '%s'", I_TH_FA_Scan_Detail.COLUMNNAME_TH_FA_Scan_ID, 
						I_TH_FA_Scan_Detail.COLUMNNAME_TH_FA_Physical_Status, X_TH_FA_Scan_Detail.TH_FA_PHYSICAL_STATUS_KeepInAnotherLocation);
		
		List<X_TH_FA_Scan_Detail> scanDetails = new Query(getCtx(), I_TH_FA_Scan_Detail.Table_Name, scanDetailWhere, get_TrxName()).setParameters(faScan.getTH_FA_Scan_ID()).list();
		
		for (X_TH_FA_Scan_Detail scanDetail : scanDetails) {
			X_A_Asset asset = (X_A_Asset)scanDetail.getA_Asset();
			asset.setM_Locator_ID(scanDetail.getTH_FA_Scan_Location_ID());
			asset.setAD_Org_ID(scanDetail.getTH_FA_Scan_Org_ID());
			asset.saveEx(get_TrxName());
			
			addLog(0, null, null, 
					String.format("%s - %s", asset.getValue(), asset.getName()), 
					I_A_Asset.Table_ID, asset.getA_Asset_ID());
		}
		
		return "";
	}
}
