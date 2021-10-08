package th.motive.assetsscan.form;

import java.math.BigDecimal;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Column;
import org.adempiere.webui.component.Columns;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WebEditorFactory;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.session.SessionManager;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.MTable;
import org.compiere.model.MValRule;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkforge.keylistener.Keylistener;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zul.Div;

import th.motive.assetsscan.model.I_TH_FA_Scan;
import th.motive.assetsscan.model.I_TH_FA_Scan_Detail;
import th.motive.assetsscan.model.I_TH_FA_Scan_Warehouse;
import th.motive.assetsscan.model.X_TH_FA_Scan;
import th.motive.assetsscan.model.X_TH_FA_Scan_Warehouse;

public class AssetWarehouseScanForm extends ADForm{

	/**
	 *
	 */
	private static final long serialVersionUID = -9159986724606159883L;

	Textbox txtBarcode;
	Button btScan;
	Label lbQty;

	GridField mFieldOrg;
	GridField mFieldLocator;
	WEditor editorOrg;
	WEditor editorLocator;

	String tableName;
	int recordId;
	int tableId;
	I_TH_FA_Scan faScan;

	@Override
	protected void initForm() {
		tableName = getGridTab().getTableName();
		tableId = getGridTab().getAD_Table_ID();
		recordId = getGridTab().getRecord_ID();

		faScan = new X_TH_FA_Scan(Env.getCtx(), recordId, null);

		Grid gridSelection = GridFactory.newGridLayout();
		this.appendChild(gridSelection);

		Columns columns = new Columns();
		gridSelection.appendChild(columns);

		Column column = new Column();
		column.setWidth("15%");
		columns.appendChild(column);
		column.setClass("form-label");

		column = new Column();
		column.setWidth("25%");
		columns.appendChild(column);

		column = new Column();
		column.setWidth("25%");
		columns.appendChild(column);

		column = new Column();
		column.setWidth("25%");
		columns.appendChild(column);

		column = new Column();
		column.setWidth("10%");
		columns.appendChild(column);

		Rows rows = new Rows();
		gridSelection.appendChild(rows);
		// org
		Row row = new Row();
		rows.appendChild(row);

		GridFieldVO voF = GridFieldVO.createParameter(Env.getCtx(), m_WindowNo, AEnv.getADWindowID(m_WindowNo), 0, 0,
				I_TH_FA_Scan.COLUMNNAME_AD_Org_ID,
				Msg.translate(Env.getCtx(), I_TH_FA_Scan.COLUMNNAME_AD_Org_ID),
				DisplayType.TableDir,
				0, false, false, null);

		int orgWithoutZero = 130;
		voF.ValidationCode = MValRule.get(Env.getCtx(), orgWithoutZero).getCode();
		// to re-create LookupInfo already create inside GridFieldVO.createParameter to use ValidationCode
		voF.loadLookupInfo();

		mFieldOrg = new GridField(voF);
		editorOrg = WebEditorFactory.getEditor(mFieldOrg, false);

		addLabel(row, editorOrg.getLabel());
		addComponent(row, editorOrg.getComponent());

		if (faScan.getAD_Org_ID() != 0) {
			editorOrg.setValue(faScan.getAD_Org_ID());
			editorOrg.setReadWrite(false);
		}

		// localtor
		voF = GridFieldVO.createParameter(Env.getCtx(), m_WindowNo, AEnv.getADWindowID(m_WindowNo), 0, 0,
				I_TH_FA_Scan_Detail.COLUMNNAME_M_Locator_ID,
				Msg.translate(Env.getCtx(), I_TH_FA_Scan_Detail.COLUMNNAME_M_Locator_ID),
				DisplayType.Locator,
				0, false, false, null);

		int validateLocatorOrgId = 52087;// M_Locator of Org
		voF.ValidationCode = MValRule.get(Env.getCtx(), validateLocatorOrgId).getCode();
		//don't need re-create because it will use ValidationCode on DefaultLookupFactory.getLookup on case Locator
		mFieldLocator = new GridField(voF);
		editorLocator = WebEditorFactory.getEditor(mFieldLocator, false);

		if(faScan.getM_Locator_ID() != 0) {
			editorLocator.setValue(faScan.getM_Locator_ID());
			editorLocator.setReadWrite(false);
		}

		addLabel(row, editorLocator.getLabel());
		addComponent(row, editorLocator.getComponent());

		// barcode text
		row = new Row();
		rows.appendChild(row);

		txtBarcode = new Textbox();
		Label lbBarcode = new Label("Barcode");
		addLabel(row, lbBarcode);
		addComponent(row, txtBarcode);
		txtBarcode.focus();
		//txtBarcode.addEventListener(Events.ON_CHANGING, e -> txtScanBarcodeEventHandle(e));

		btScan = new Button("scan");
		btScan.addEventListener(Events.ON_CLICK, e -> btScanBarcodeEventHandle(e));
		addComponent(row, btScan);

		row = new Row();
		rows.appendChild(row);
		Label lbForQty = new Label(Msg.translate(Env.getCtx(), I_TH_FA_Scan_Warehouse.COLUMNNAME_Qty));
		addLabel(row, lbForQty);
		lbQty = new Label();
		lbQty.setValue("0");
		addLabel(row, lbQty);
	}

	Keylistener keyListener;
	
	@Override
	public void onPageAttached(Page newpage, Page oldpage) {
		super.onPageAttached(newpage, oldpage);
		Keylistener keyListener = SessionManager.getSessionApplication().getKeylistener();
		keyListener.addEventListener(Events.ON_CTRL_KEY, this);
	}
	
	@Override
	public void onPageDetached(Page page) {
		super.onPageDetached(page);
		if (keyListener != null) {
			keyListener.removeEventListener(Events.ON_CTRL_KEY, this);
		}
	}
	
	@Override
	public void onEvent(Event event) throws Exception {
		if (Events.ON_CTRL_KEY.equals(event.getName())) {
    		KeyEvent keyEvent = (KeyEvent) event;
    		//enter == 13
    		if (keyEvent.getKeyCode() == 13) {
    			barcodeScan(txtBarcode.getText());
    			keyEvent.stopPropagation();
    			return;
    		}
    	}
		
		super.onEvent(event);
	}
	
	void newRow(Rows rows) {
		Row row = new Row();
		rows.appendChild(row);

	}

	void addLabel(Row row, org.zkoss.zul.Label label) {
		Div lbDiv = new Div();
		lbDiv.setSclass("form-label");
		row.appendCellChild(lbDiv);

		lbDiv.appendChild(label);

	}

	void addComponent(Row row, Component comp){
		((HtmlBasedComponent)comp).setWidth(null);
		((HtmlBasedComponent)comp).setHflex("1");
		row.appendCellChild(comp);

	}

	public void btScanBarcodeEventHandle(Event event) {
		barcodeScan(txtBarcode.getText());
	}

	public void txtScanBarcodeEventHandle(Event event) {
		InputEvent onchangeEvent = (InputEvent)event;
		barcodeScan(onchangeEvent.getValue());
	}

	private void barcodeScan(String inputText){
		if (inputText.trim().length() == 0)
			return;
		
		int orgId = 0;
		int locatorId = 0;

		if (editorOrg.getValue() == null) {
			throw new AdempiereException("Org is required");
		}else {
			orgId = (int)editorOrg.getValue();
		}

		if (editorLocator.getValue() == null) {
			throw new AdempiereException("Locator is required");
		}else {
			locatorId = (int)editorLocator.getValue();
		}

		MTable tbScanWarehouse = new MTable(Env.getCtx(), I_TH_FA_Scan_Warehouse.Table_ID, null);

		String queryOldScanWarehouse = String.format("%s = ? AND %s = ? AND %s = ? AND %s = ? ", 
				I_TH_FA_Scan_Warehouse.COLUMNNAME_TH_FA_Scan_ID, I_TH_FA_Scan_Warehouse.COLUMNNAME_AD_Org_ID,
				I_TH_FA_Scan_Warehouse.COLUMNNAME_M_Locator_ID, I_TH_FA_Scan_Warehouse.COLUMNNAME_Value);

		X_TH_FA_Scan_Warehouse scanWarehouse = (X_TH_FA_Scan_Warehouse)tbScanWarehouse.getPO(
				queryOldScanWarehouse,
				new Object[] {faScan.getTH_FA_Scan_ID(), orgId, locatorId, inputText},
				null);

		if (scanWarehouse == null) {
			scanWarehouse = new X_TH_FA_Scan_Warehouse(Env.getCtx(), 0, null);
			scanWarehouse.setTH_FA_Scan_ID(faScan.getTH_FA_Scan_ID());
			scanWarehouse.setAD_Org_ID(orgId);
			scanWarehouse.setM_Locator_ID(locatorId);
			scanWarehouse.setQty(BigDecimal.ZERO);
			scanWarehouse.setValue(inputText);
		}

		scanWarehouse.setQty(scanWarehouse.getQty().add(BigDecimal.ONE));
		scanWarehouse.saveEx();

		lbQty.setText(String.valueOf(scanWarehouse.getQty().intValue()));
		txtBarcode.focus();

	}

}
