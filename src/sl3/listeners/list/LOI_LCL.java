package sl3.listeners.list;

import java.util.ArrayList;
import java.util.List;

import sl3.items.SI;
import sl3.listeners.dialog.DOI_CL;
import sl3.main.R;
import sl3.utils.CONS;
import sl3.utils.DBUtils;
import sl3.utils.Methods_dlg;
import sl3.utils.Tags;
import sl.main.RegisterItemActv;
import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemLongClickListener;

public class LOI_LCL implements OnItemLongClickListener {

	Activity actv;
	Vibrator vib;
	
	public LOI_LCL(Activity actv) {
		//
		this.actv = actv;
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);

	}

	public boolean onItemLongClick(AdapterView<?> parent, View v, int position,
			long id) {
		// TODO Auto-generated method stub
		Tags.ListTags tag = (Tags.ListTags) parent.getTag();
		
		SI si;
		
		switch (tag) {

		case tab_toBuyList://-------------------------------------------

			case_tab_toBuyList(parent, position);
			
			break;// case tab_toBuyList

		case TAB_ITEM_LIST://-------------------------------------------
			
			si = (SI) parent.getItemAtPosition(position);
			
			case_TAB_ITEM_LIST(si);
			
			break;// case tab_itemList
			
		default:
			break;
		
		}//switch (item)

		return true;
	}

	/***************************************
	 * case_tab_itemList(AdapterView<?> parent, int position)<br>
	 * Tab 1: Edit item
	 ***************************************/
//	private void case_TAB_ITEM_LIST(AdapterView<?> parent, int position) {
	private void case_TAB_ITEM_LIST(SI si) {
		// TODO Auto-generated method stub
//		SI si = (SI) parent.getItemAtPosition(position);
		
		Methods_dlg.dlg_Admin_ShoppingItem(actv, si);
		
//		String title = si.getName() + "/" + si.getStore();
//		
//		Dialog dlg = Methods_dlg.dlg_template_cancel(actv, 
//				R.layout.dlg_register_main, title,
//				R.id.dlg_register_main_btn_cancel, Tags.DialogTags.DLG_GENERIC_CANCEL);
//		
//		/*----------------------------
//		 * 2. List view
//		 * 		1. Get view
//		 * 		2. Prepare list data
//		 * 		3. Prepare adapter
//		 * 		4. Set adapter
//			----------------------------*/
//		ListView lv = (ListView) dlg.findViewById(R.id.dlg_register_main_lv_list);
//		
//		List<String> menuItem = new ArrayList<String>();
//		
//		menuItem.add(actv.getString(R.string.dlg_item_list_long_click_edit));
//		menuItem.add(actv.getString(R.string.dlg_item_list_long_click_delete));
//
//		ArrayAdapter<String> adp = 
//				new ArrayAdapter<String>(
//						actv,
//						android.R.layout.simple_list_item_1,
//						menuItem
//						);
//				
//		/*----------------------------
//		 * 2.4. Set adapter
//		----------------------------*/
//		lv.setAdapter(adp);
//		
//		/*********************************
//		 * Set: tag
//		 *********************************/
//		lv.setTag(Tags.ListViewTags.TAB1_LONG_CLICK);
//
//		
//		/*----------------------------
//		 * 3. Set listener => list
//			----------------------------*/
//		lv.setOnItemClickListener(
//				new ListViewCL(
//						actv, 
//						dlg, 
////						Tags.DialogTags.dlg_item_list_long_click,
//						si));
////		lv.setOnItemClickListener(
////				new DialogOnItemClickListener(
////						actv, 
////						dlg, 
////						Tags.DialogTags.dlg_item_list_long_click,
////						si));
//		
//		/*----------------------------
//		 * 9. Show dialog
//			----------------------------*/
//		dlg.show();

		
	}//private void case_tab_itemList(AdapterView<?> parent, int position)

	private void case_tab_toBuyList(AdapterView<?> parent, int position) {
		// TODO Auto-generated method stub
		/***************************************
		 * Get item
		 ***************************************/
		SI si = (SI) parent.getItemAtPosition(position);
		
		// Log
		Log.d("ListOnItemClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "si.getName()=" + si.getName());
		
		/***************************************
		 * Show dialog
		 ***************************************/
		Methods_dlg.dlg_tabActv_tab2Lv(actv, si);
		
	}//private void tab_toBuyList(AdapterView<?> parent, int position)

}
