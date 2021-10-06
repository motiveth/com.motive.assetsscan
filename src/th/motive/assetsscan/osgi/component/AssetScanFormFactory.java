package th.motive.assetsscan.osgi.component;

import org.adempiere.webui.factory.IFormFactory;
import org.adempiere.webui.panel.ADForm;
import org.osgi.service.component.annotations.Component;

import th.motive.assetsscan.form.AssetWarehouseScanForm;

@Component(
		 property= {"service.ranking:Integer=2"}
		 )
public class AssetScanFormFactory implements IFormFactory {

	@Override
	public ADForm newFormInstance(String formName) {
		//line 216 class DefaultFormFactory String packageName = originalName.substring(0, lastdot);
		// require formName has at least one dot
		if ("FakePackage.Asset Warehouse Scan".equalsIgnoreCase(formName)) {
			return new AssetWarehouseScanForm();
		}
		return null;
	}

}
