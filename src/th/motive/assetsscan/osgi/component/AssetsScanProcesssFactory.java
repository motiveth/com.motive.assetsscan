package th.motive.assetsscan.osgi.component;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

import th.motive.assetsscan.process.AssetsScanProcess;

public class AssetsScanProcesssFactory implements IProcessFactory {

	@Override
	public ProcessCall newProcessInstance(String className) {
		if (className.equalsIgnoreCase("AssetsScanProcess"))
			return new AssetsScanProcess();
		return null;
	}

}
