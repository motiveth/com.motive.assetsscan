package th.motive.assetsscan.osgi.component;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.osgi.service.component.annotations.Component;

import th.motive.assetsscan.process.AssetBookingScan;
import th.motive.assetsscan.process.AssetConciliationScan;

@Component(
		 property= {"service.ranking:Integer=2"}
		 )
public class AssetsScanProcesssFactory implements IProcessFactory {

	@Override
	public ProcessCall newProcessInstance(String className) {
		if (className.equalsIgnoreCase("AssetBookingScan"))
			return new AssetBookingScan();
		else if (className.equalsIgnoreCase("AssetConciliationScan"))
			return new AssetConciliationScan();
		return null;
	}

}
