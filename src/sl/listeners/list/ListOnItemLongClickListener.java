package sl.listeners.list;

import java.util.ArrayList;
import java.util.List;

import sl.items.ShoppingItem;
import sl.listeners.dialog.DialogOnItemClickListener;
import sl.main.R;
import sl.main.RegisterItemActv;
import sl.utils.CONS;
import sl.utils.DBUtils;
import sl.utils.Methods_dlg;
import sl.utils.Tags;
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

public class ListOnItemLongClickListener implements OnItemLongClickListener {

	Activity actv;
	Vibrator vib;
	
	public ListOnItemLongClickListener(Activity actv) {
		//
		this.actv = actv;
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);

	}

	public boolean onItemLongClick(AdapterView<?> parent, View v, int position,
			long id) {
		// TODO Auto-generated method stub
		Tags.ListTags tag = (Tags.ListTags) parent.getTag();
		
		switch (tag) {

		case tab_toBuyList://-------------------------------------------

			case_tab_toBuyList(parent, position);
			
			break;// case tab_toBuyList

		case tab_itemList://-------------------------------------------
			
			case_tab_itemList(parent, position);
			
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
	private void case_tab_itemList(AdapterView<?> parent, int position) {
		// TODO Auto-generated method stub
		ShoppingItem si = (ShoppingItem) parent.getItemAtPosition(position);
		
		String title = si.getName() + "/" + si.getStore();
		
		Dialog dlg = Methods_dlg.dlg_template_cancel(actv, 
				R.layout.dlg_register_main, title,
				R.id.dlg_register_main_btn_cancel, Tags.DialogTags.dlg_generic_cancel);
		
		/*----------------------------
		 * 2. List view
		 * 		1. Get view
		 * 		2. Prepare list data
		 * 		3. Prepare adapter
		 * 		4. Set adapter
			----------------------------*/
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_register_main_lv_list);
		
		List<String> menuItem = new ArrayList<String>();
		
		menuItem.add(actv.getString(R.string.dlg_item_list_long_click_edit));
		menuItem.add(actv.getString(R.string.dlg_item_list_long_click_delete));

		ArrayAdapter<String> adp = 
				new ArrayAdapter<String>(
						actv,
						android.R.layout.simple_list_item_1,
						menuItem
						);
				
		/*----------------------------
		 * 2.4. Set adapter
		----------------------------*/
		lv.setAdapter(adp);
		
		/*********************************
		 * Set: tag
		 *********************************/
		lv.setTag(CONS.ListViewTags.tab1_long_click);

		
		/*----------------------------
		 * 3. Set listener => list
			----------------------------*/
		lv.setOnItemClickListener(
				new ListViewCL(
						actv, 
						dlg, 
//						Tags.DialogTags.dlg_item_list_long_click,
						si));
//		lv.setOnItemClickListener(
//				new DialogOnItemClickListener(
//						actv, 
//						dlg, 
//						Tags.DialogTags.dlg_item_list_long_click,
//						si));
		
		/*----------------------------
		 * 9. Show dialog
			----------------------------*/
		dlg.show();

		
	}//private void case_tab_itemList(AdapterView<?> parent, int position)

	private void case_tab_toBuyList(AdapterView<?> parent, int position) {
		// TODO Auto-generated method stub
		/***************************************
		 * Get item
		 ***************************************/
		ShoppingItem si = (ShoppingItem) parent.getItemAtPosition(position);
		
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
