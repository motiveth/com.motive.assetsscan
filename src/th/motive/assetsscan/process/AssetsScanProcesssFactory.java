package th.motive.assetsscan.process;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

public class AssetsScanProcesssFactory implements IProcessFactory {

	@Override
	public ProcessCall newProcessInstance(String className) {
		if (className.equalsIgnoreCase("AssetsScanProcess"))
			return new AssetsScanProcess();
		return null;
	}

}
