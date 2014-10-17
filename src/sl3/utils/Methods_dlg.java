package sl3.utils;


import java.util.ArrayList;
import java.util.List;

import sl3.adapters.Adp_ListItems;
import sl3.adapters.PSListAdapter;
import sl3.items.ListItem;
import sl3.items.PS;
import sl3.items.SI;
import sl3.listeners.dialog.DB_OCL;
import sl3.listeners.dialog.DB_OTL;
import sl3.listeners.dialog.DOI_CL;
import sl3.listeners.dialog.DialogButtonOnTouchListener;
import sl3.listeners.list.LOI_CL;
import sl3.listeners.list.ListViewCL;
import sl3.main.R;
import sl3.utils.Tags.DialogButtonTags;
import sl3.utils.Tags.DialogTags;
import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Methods_dlg {

	public static void dlg_db_activity(Activity actv) {
		/*----------------------------
		 * 1. Dialog
		 * 2. Prep => List
		 * 3. Adapter
		 * 4. Set adapter
		 * 
		 * 5. Set listener to list
		 * 6. Show dialog
			----------------------------*/
		Dialog dlg = Methods_dlg.dlg_template_cancel(
									actv,
									R.layout.dlg_db_admin, 
									R.string.dlg_db_admin_title, 
									R.id.dlg_db_admin_bt_cancel, 
									Tags.DialogTags.DLG_GENERIC_DISMISS);
		
		/*----------------------------
		 * 2. Prep => List
			----------------------------*/
		String[] choices = {
				actv.getString(R.string.dlg_db_admin_item_backup_db),
				actv.getString(R.string.dlg_db_admin_item_refresh_db),
				actv.getString(R.string.dlg_db_admin_item_refatcor_db),
				actv.getString(R.string.dlg_db_admin_item_restore_db),
				actv.getString(R.string.dlg_db_admin_item_get_yomi)
//				actv.getString(R.string.dlg_db_admin_item_post_data),
		};
		
		List<String> list = new ArrayList<String>();
		
		for (String item : choices) {
			
			list.add(item);
			
		}
		
		/*----------------------------
		 * 3. Adapter
			----------------------------*/
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				actv,
//				R.layout.dlg_db_admin,
				android.R.layout.simple_list_item_1,
				list
				);

		/*----------------------------
		 * 4. Set adapter
			----------------------------*/
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_db_admin_lv);
		
		lv.setAdapter(adapter);
		
		/*----------------------------
		 * 5. Set listener to list
			----------------------------*/
		lv.setTag(Tags.DialogTags.dlg_db_admin_lv);
		
		lv.setOnItemClickListener(new DOI_CL(actv, dlg));
		
		/*----------------------------
		 * 6. Show dialog
			----------------------------*/
		dlg.show();
		
		
	}//public static void dlg_db_activity(Activity actv)

	public static void dlg_tabActv_adminDb(Activity actv) {
		/*----------------------------
		 * 1. Dialog
		 * 2. Prep => List
		 * 3. Adapter
		 * 4. Set adapter
		 * 
		 * 5. Set listener to list
		 * 6. Show dialog
			----------------------------*/
		Dialog dlg = Methods_dlg.dlg_template_cancel(
									actv,
									R.layout.dlg_db_admin, 
									R.string.menu_listitem_tabToBuy_admin_db, 
									R.id.dlg_db_admin_bt_cancel, 
									Tags.DialogTags.DLG_GENERIC_DISMISS);
		
		/*----------------------------
		 * 2. Prep => List
			----------------------------*/
		String[] choices = {
				actv.getString(
					R.string.menu_listitem_tabToBuy_admin_db_save_tobuy_list),
				actv.getString(
					R.string.menu_listitem_tabToBuy_admin_db_load_tobuy_list),
//				actv.getString(
//					R.string.menu__tabToBuy_admin_db_delete_tobuy_list),
				actv.getString(
					R.string.menu_listitem_tabToBuy_admin_db_post_tobuy_list),
				
		};
		
		List<String> list = new ArrayList<String>();
		
		for (String item : choices) {
			
			list.add(item);
			
		}
		
		/*----------------------------
		 * 3. Adapter
			----------------------------*/
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				actv,
//				R.layout.dlg_db_admin,
				android.R.layout.simple_list_item_1,
				list
				);

		/*----------------------------
		 * 4. Set adapter
			----------------------------*/
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_db_admin_lv);
		
		lv.setAdapter(adapter);
		
		/*----------------------------
		 * 5. Set listener to list
			----------------------------*/
//		lv.setTag(Tags.DialogTags.dlg_db_admin_lv);
		lv.setTag(Tags.DialogTags.dlg_tabActv_adminDb);
		
		lv.setOnItemClickListener(new DOI_CL(actv, dlg));
		
		/*----------------------------
		 * 6. Show dialog
			----------------------------*/
		dlg.show();
		
		//debug
//		DBUtils.dropTable(actv, CONS.dbName, CONS.DBAdmin.tname_purchaseSchedule);
		
		//debug
		
		
	}//public static void dlg_tabActv_adminDb(Activity actv)

	public static Dialog dlg_template_cancel(Activity actv, int layoutId, int titleStringId,
			int cancelButtonId, DialogTags cancelTag) {
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(layoutId);
		
		// Title
		dlg.setTitle(titleStringId);
		
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		//
		//dlg.show();
		
		return dlg;
	
	}//public static Dialog dlg_template_okCancel()

	
	public static
	Dialog dlg_template_cancel_2Dialogues
	(Activity actv, int layoutId, int titleStringId,
			int cancelButtonId, DialogTags cancelTag, Dialog dlg1) {
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(titleStringId);
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg2));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2));
		
		//
		//dlg2.show();
		
		return dlg2;
	
	}//public static Dialog dlg_template_cancel_2Dialogues


	public static Dialog dlg_template_cancel(Activity actv, int layoutId, String title,
			int cancelButtonId, DialogTags cancelTag) {
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(layoutId);
		
		// Title
//		dlg.setTitle(titleStringId);
		dlg.setTitle(title);
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		//
		//dlg.show();
		
		return dlg;
	
	}//public static Dialog dlg_template_okCancel()

	public static void dlg_tabActv_tab2Lv(Activity actv, SI si) {
		/***************************************
		 * 1. Dialog
		 ***************************************/
		Dialog dlg = Methods_dlg.dlg_template_cancel(
				actv,
				R.layout.dlg_db_admin, 
				si.getName(), 
				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.DLG_GENERIC_DISMISS);

//		dlg.setTitle(si.getName());
		
		/***************************************
		 * 2. Prep => List
		 ***************************************/
		String[] choices = {
				actv.getString(R.string.tabactv_tab2_lv_delete_from_list),
		};

		List<String> list = new ArrayList<String>();
		
		for (String item : choices) {
			
			list.add(item);
			
		}
		
		/***************************************
		 * 3. Adapter
		 ***************************************/
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				actv,
//				R.layout.dlg_db_admin,
				android.R.layout.simple_list_item_1,
				list
				);

		/***************************************
		 * 4. Set adapter
		 ***************************************/
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_db_admin_lv);
		
		lv.setAdapter(adapter);
		
		/***************************************
		 * 5. Set listener to list
		 ***************************************/
		lv.setTag(Tags.DialogTags.dlg_tabactv_tab2_lv);
		
		lv.setOnItemClickListener(new DOI_CL(actv, dlg, si));
		
		/***************************************
		 * 6. Show dialog
		 ***************************************/
		dlg.show();
		
	}//public static void dlg_tabActv_tab2Lv(Activity actv, ShoppingItem si)

	public static void 
	dlg_SaveToBuyList
	(Activity actv, Dialog d1) {
		// TODO Auto-generated method stub
		/***************************************
		 * Setup
		 ***************************************/
		Dialog d2 = Methods_dlg.dlg_template_okCancel_2Dialogues(
				actv,
				R.layout.dlg_save_tobuy_list, 
				R.string.menu_listitem_tabToBuy_admin_db_save_tobuy_list,
				
				R.id.dlg_save_tobuy_list_bt_ok,
				R.id.dlg_save_tobuy_list_bt_cancel,
				
//				Tags.DialogTags.dlg_save_tobuy_list_bt_ok,
				Tags.DialogTags.DLG_SAVE_TOBUY_LIST_BT_OK,
				Tags.DialogTags.DLG_GENERIC_DISMISS_SECOND_DIALOG,
				
				d1);
		
		/***************************************
		 * Spinner
		 ***************************************/
		Spinner spStoreNames = (Spinner) d2.findViewById(R.id.dlg_save_tobuy_list_sp_store_name);
		
		ArrayAdapter<String> adapter = _dlg_saveToBuyList__Adapter(actv);
		
		////////////////////////////////

		// Set adapter to spinner

		////////////////////////////////
		spStoreNames.setAdapter(adapter);
		
		////////////////////////////////

		// Set spinner default value
		// 1. Get the first item from CONS.TabActv.toBuyList
		// 2. Get the store name from the item
		// 3. Use this store name as the default

		////////////////////////////////
		SI item = CONS.TabActv.toBuyList.get(0);
		
		if (item != null) {
			
			String defaultStoreName = item.getStore();

			if (defaultStoreName != null) {

				int position = adapter.getPosition(defaultStoreName);
				
				spStoreNames.setSelection(position);

			}//if (defaultStoreName != null)
			
		}//if (item != null)
		
		////////////////////////////////

		// Amount(Sum of items in price)

		////////////////////////////////
		int amount = 0;
		
		for (SI si : CONS.TabActv.toBuyList) {
			
			amount += si.getPrice() * si.getNum();
//			amount += si.getPrice();
			
		}//for (ShoppingItem i : CONS.TabActv.toBuyList)
		
		EditText etAmount = (EditText) d2.findViewById(R.id.dlg_save_tobuy_list_et_amount);
		
		etAmount.setText(String.valueOf(amount));
		
		/***************************************
		 * Show dialog
		 ***************************************/
		d2.show();

	}//public static void dlg_saveToBuyList(Activity actv, Dialog dlg1)

	private static 
	ArrayAdapter<String> 
	_dlg_saveToBuyList__Adapter
	(Activity actv) {
		// TODO Auto-generated method stub
		
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//        actv, android.R.layout.simple_spinner_item);


		/*----------------------------
		* 2. Get store names from db
			----------------------------*/
//		DBUtils dbm = new DBUtils(actv);
//		
//		SQLiteDatabase db = dbm.getReadableDatabase();
//		
//		Cursor c = dbm.getAllData(db, "stores", CONS.DB.columns_for_table_stores_with_index);
//		
//		c.moveToFirst();
		
		ArrayAdapter<String> adapter = Methods.get_Adp_List_Store(actv);
//		List<String> list_Stores = Methods.get_Adp_List_Store(actv);
		
//		for (int i = 0; i < c.getCount(); i++) {
//			
//			adapter.add(c.getString(1));
//		
//			c.moveToNext();
//			
//		}//for (int i = 0; i < c.getCount(); i++)
		
		/*----------------------------
		* 3-1. setDropDownViewResource
			----------------------------*/
		adapter.setDropDownViewResource(
						android.R.layout.simple_spinner_dropdown_item);
		
		return adapter;
		
	}//_dlg_saveToBuyList__Adapter

	public static Dialog 
	dlg_Tmpl_OkCancel
	(Activity actv, 
		int layoutId, int titleStringId,
		int okButtonId, int cancelButtonId, 
		DialogTags okTag, DialogTags cancelTag) {
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(layoutId);
		
		// Title
		dlg.setTitle(titleStringId);
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_ok = (Button) dlg.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
		//
		btn_ok.setOnTouchListener(new DB_OTL(actv, dlg));
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg));
//		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
//		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_ok.setOnClickListener(new DB_OCL(actv, dlg));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		//
		//dlg.show();
		
		return dlg;
	
	}//public static Dialog dlg_template_okCancel()

	public static
	Dialog dlg_template_okCancel_2Dialogues
	(Activity actv,
			int layoutId, int titleStringId,
			
			int okButtonId, int cancelButtonId,
			
			DialogTags okTag, DialogTags cancelTag,
			
			Dialog dlg1) {
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(titleStringId);
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_ok = (Button) dlg2.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
		//
		btn_ok.setOnTouchListener(
				new DialogButtonOnTouchListener(actv, dlg2));
		btn_cancel.setOnTouchListener(
				new DialogButtonOnTouchListener(actv, dlg2));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_ok.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2));
		btn_cancel.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2));
		
		//
		//dlg.show();
		
		return dlg2;
	
	}//public static Dialog dlg_template_okCancel()

	public static
	Dialog dlg_template_okCancel_2Dialogues_SI
	(Activity actv,
			int layoutId, int titleStringId,
			int okButtonId, int cancelButtonId,
			DialogTags okTag, DialogTags cancelTag,
			Dialog dlg1, SI si) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		----------------------------*/
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(titleStringId);
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_ok = (Button) dlg2.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
		//
		btn_ok.setOnTouchListener(
				new DB_OTL(actv, dlg2));
//		new DialogButtonOnTouchListener(actv, dlg2));
		btn_cancel.setOnTouchListener(
				new DB_OTL(actv, dlg2));
//		new DialogButtonOnTouchListener(actv, dlg2));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_ok.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2, si));
		btn_cancel.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2));
		
		//
		//dlg.show();
		
		return dlg2;
		
	}//public static Dialog dlg_template_okCancel()

	public static
	Dialog dlg_template_okCancel_2Dialogues_SI
	(Activity actv,
			int layoutId, int titleStringId,
			
			int okButtonId, int cancelButtonId,
			
			Tags.DialogButtonTags okTag, Tags.DialogButtonTags cancelTag,
			
			Dialog dlg1, SI si) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		----------------------------*/
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(titleStringId);
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_ok = (Button) dlg2.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
		//
		btn_ok.setOnTouchListener(
				new DB_OTL(actv, dlg2));
		btn_cancel.setOnTouchListener(
				new DB_OTL(actv, dlg2));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_ok.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2, si));
		btn_cancel.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2));
		
		//
		//dlg.show();
		
		return dlg2;
		
	}//public static Dialog dlg_template_okCancel()
	
	public static
	Dialog dlg_template_okCancel_3Dialogues
	(Activity actv,
			int layoutId, int titleStringId,
			
			int okButtonId, int cancelButtonId,
			DialogTags okTag, DialogTags cancelTag,
			
			Dialog dlg1, Dialog dlg2) {
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg3 = new Dialog(actv);
		
		//
		dlg3.setContentView(layoutId);
		
		// Title
		dlg3.setTitle(titleStringId);
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_ok = (Button) dlg3.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg3.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
		//
		btn_ok.setOnTouchListener(
				new DialogButtonOnTouchListener(actv, dlg3));
		btn_cancel.setOnTouchListener(
				new DialogButtonOnTouchListener(actv, dlg3));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_ok.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2, dlg3));
		btn_cancel.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2, dlg3));
		
		//
		//dlg.show();
		
		return dlg3;
	
	}//public static Dialog dlg_template_okCancel()
	
	public static
	Dialog dlg_Tmpl_OkCancel_3rd_Dialogue
	(Activity actv, Dialog dlg1, Dialog dlg2,
			int layoutId, int titleStringId,
			
			int okButtonId, int cancelButtonId,
			DialogTags okTag, DialogTags cancelTag
			
			) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg3 = new Dialog(actv);
		
		//
		dlg3.setContentView(layoutId);
		
		// Title
		dlg3.setTitle(titleStringId);
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_ok = (Button) dlg3.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg3.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
		//
		btn_ok.setOnTouchListener(
				new DB_OTL(actv, dlg1, dlg2, dlg3));
//		new DialogButtonOnTouchListener(actv, dlg3));
		btn_cancel.setOnTouchListener(
				new DB_OTL(actv, dlg1, dlg2, dlg3));
//				new DialogButtonOnTouchListener(actv, dlg3));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_ok.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2, dlg3));
		btn_cancel.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2, dlg3));
		
		//
		//dlg.show();
		
		return dlg3;
		
	}//public static Dialog dlg_template_okCancel()

	
	public static
	Dialog dlg_template_okCancel_3Dialogues
	(Activity actv,
			int layoutId, int titleStringId,
			
			int okButtonId, int cancelButtonId, int choiceButtonId,
			DialogTags okTag, DialogTags cancelTag, DialogTags thirdTag,
			
			Dialog dlg1, Dialog dlg2) {
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg3 = new Dialog(actv);
		
		//
		dlg3.setContentView(layoutId);
		
		// Title
		dlg3.setTitle(titleStringId);
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_ok = (Button) dlg3.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg3.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
		//
		btn_ok.setOnTouchListener(
				new DialogButtonOnTouchListener(actv, dlg3));
		btn_cancel.setOnTouchListener(
				new DialogButtonOnTouchListener(actv, dlg3));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_ok.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2, dlg3));
		btn_cancel.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2, dlg3));
		
		//
		//dlg.show();
		
		return dlg3;
	
	}//public static Dialog dlg_template_okCancel()

	public static
	Dialog dlg_template_okCancel_3Dialogues_withPS
	(Activity actv,
			int layoutId, int titleStringId,
			
			int okButtonId, int cancelButtonId,
			DialogTags okTag, DialogTags cancelTag,
			
			Dialog dlg1, Dialog dlg2, PS ps) {
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg3 = new Dialog(actv);
		
		//
		dlg3.setContentView(layoutId);
		
		// Title
		dlg3.setTitle(titleStringId);
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_ok = (Button) dlg3.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg3.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
		//
		btn_ok.setOnTouchListener(
				new DialogButtonOnTouchListener(actv, dlg3));
		btn_cancel.setOnTouchListener(
				new DialogButtonOnTouchListener(actv, dlg3));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_ok.setOnClickListener(
//				new DialogButtonOnClickListener(actv, dlg1, dlg2, dlg3));
				new DB_OCL(actv, dlg1, dlg2, dlg3, ps));
		btn_cancel.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2, dlg3));
		
		//
		//dlg.show();
		
		return dlg3;
	
	}//Dialog dlg_template_okCancel_3Dialogues_withPS

	public static
	Dialog dlg_template_okCancel_3Dialogues_3Choices
	(Activity actv,
			int layoutId, int titleStringId,
			
			int okButtonId, int cancelButtonId, int thirdButtonId,
			
			DialogTags okTag, DialogTags cancelTag, DialogTags thirdChoiceTag,
			
			Dialog dlg1, Dialog dlg2) {
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg3 = new Dialog(actv);
		
		//
		dlg3.setContentView(layoutId);
		
		// Title
		dlg3.setTitle(titleStringId);
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_ok = (Button) dlg3.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg3.findViewById(cancelButtonId);
		Button btn_third = (Button) dlg3.findViewById(thirdButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		btn_third.setTag(thirdChoiceTag);
		
		//
		btn_ok.setOnTouchListener(
				new DialogButtonOnTouchListener(actv, dlg3));
		btn_cancel.setOnTouchListener(
				new DialogButtonOnTouchListener(actv, dlg3));
		btn_third.setOnTouchListener(
				new DialogButtonOnTouchListener(actv, dlg3));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_ok.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2, dlg3));
		btn_cancel.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2, dlg3));
		btn_third.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2, dlg3));
		
		//
		//dlg.show();
		
		return dlg3;
	
	}//public static Dialog dlg_template_okCancel()

	public static void
	dlg_LoadToBuyList
	(Activity actv, Dialog d1) {
		// TODO Auto-generated method stub
		/***************************************
		 * 1. Get cursor
		 * 2. Build a PS list
		 * 3. Show the list in the dialog
		 ***************************************/
		List<PS> psList = Methods_sl.getPSList(actv);

		if (psList == null) {
			
			// Log
			Log.d("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "psList => Null");
			
			// debug
			Toast.makeText(actv, "Seems no purchase list", Toast.LENGTH_LONG).show();
			
			return;
			
		}//if (psList == null)
		
		/***************************************
		 * Sort list
		 ***************************************/
		Methods_sl.sortPSList(psList, Tags.SortTags.pslist_due_date);
		
		/***************************************
		 * 3. Show the list in the dialog
		 ***************************************/
		Dialog d2 = Methods_dlg.dlg_template_cancel_2Dialogues(
				actv,
				R.layout.dlg_tmpl_cancel_lv_with_btn, 
//				R.layout.dlg_db_admin, 
				R.string.menu_listitem_tabToBuy_admin_db_load_tobuy_list,
				
				R.id.dlg_tmpl_cancel_lv_with_btn_bt_cancel,
				Tags.DialogTags.DLG_GENERIC_DISMISS_SECOND_DIALOG,
				
				d1);

		/***************************************
		 * Set list
		 ***************************************/
		PSListAdapter adp = new PSListAdapter(
				actv,
				R.layout.listrow_load_tobuy_list,
				psList
				);
		
		ListView lv = (ListView) d2.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_lv);
		
//		int lvHeight = Methods.getSmallerNumber(350, 75 * psList.size());
//		
//		lv.setLayoutParams(new LinearLayout.LayoutParams(
//										LayoutParams.WRAP_CONTENT,	// Width
////										300));
//										lvHeight));					// Height
		
		lv.setTag(Tags.ListTags.LOAD_TOBUY_LIST);
		
		lv.setOnItemClickListener(new LOI_CL(actv, d1, d2));
		
		
		lv.setAdapter(adp);

		////////////////////////////////

		// button: all dismiss

		////////////////////////////////
		ImageButton ib_AllClear = 
				(ImageButton) d2.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_ib);
//		(ImageButton) d2.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_ib);
		
		ib_AllClear.setTag(Tags.DialogTags.GENERIC_DISMISS_ALL_2ND_DIALOG);
		
		ib_AllClear.setOnTouchListener(new DB_OTL(actv, d1, d2));
		
		ib_AllClear.setOnClickListener(new DB_OCL(actv, d1, d2));

		/***************************************
		 * Show dialog
		 ***************************************/
		d2.show();
		
		
	}//dlg_LoadToBuyList(Activity actv, Dialog dlg)

//	public static void
//	dlg_DeleteToBuyList(Activity actv, Dialog dlg1) {
//		// TODO Auto-generated method stub
//		/***************************************
//		 * 1. Get cursor
//		 * 2. Build a PS list
//		 * 3. Show the list in the dialog
//		 ***************************************/
////		DBUtils dbu = new DBUtils(actv, CONS.dbName);
//		
//		List<PS> psList = Methods_sl.getPSList(actv);
//
//		//debug
////		PS ps = psList.get(0);
//		
//		for (PS ps : psList) {
//			
//			// Log
//			Log.d("Methods_dlg.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "ps.getDbId()=" + ps.getDbId());
//			
//		}//for (PS ps : psList)
////		// Log
////		Log.d("Methods_dlg.java" + "["
////				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////				+ ":"
////				+ Thread.currentThread().getStackTrace()[2].getMethodName()
////				+ "]", "ps.getDbId()=" + ps.getDbId());
//
//		//////////////////////////////////////////
//		if (psList == null) {
//			
//			// Log
//			Log.d("Methods_dlg.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "psList => Null");
//			
//			// debug
//			Toast.makeText(actv, "Seems no purchase list", Toast.LENGTH_LONG).show();
//			
//			return;
//			
//		}//if (psList == null)
//		
//		/***************************************
//		 * Sort list
//		 ***************************************/
//		Methods_sl.sortPSList(psList, Tags.SortTags.pslist_due_date);
//		
//		/***************************************
//		 * 3. Show the list in the dialog
//		 ***************************************/
////		(Activity actv, int layoutId, int titleStringId,
////				int cancelButtonId, DialogTags cancelTag, Dialog dlg1)
//		Dialog dlg2 = Methods_dlg.dlg_template_cancel_2Dialogues(
//				actv,
//				R.layout.dlg_tmpl_cancel_lv, 
////				R.string.menu_listitem_tabToBuy_admin_db_save_tobuy_list,
//				R.string.menu_listitem_tabToBuy_admin_db_delete_tobuy_list,
//				
//				R.id.dlg_tmpl_cancel_lv_bt_cancel,
////				dlg_generic_dismiss
////				Tags.DialogTags.dlg_generic_dismiss,
//				Tags.DialogTags.DLG_GENERIC_DISMISS_SECOND_DIALOG,
//				
//				dlg1);
//
//		/***************************************
//		 * Set list
//		 ***************************************/
//		PSListAdapter adp = new PSListAdapter(
//				actv,
//				R.layout.listrow_load_tobuy_list,
//				psList
//				);
//		
//		ListView lv = (ListView) dlg2.findViewById(R.id.dlg_tmpl_cancel_lv_lv);
//		
//		int lvHeight = Methods.getSmallerNumber(350, 75 * psList.size());
//		
//		lv.setLayoutParams(new LinearLayout.LayoutParams(
//										LayoutParams.WRAP_CONTENT,	// Width
////										300));
//										lvHeight));					// Height
//		
////		lv.setTag(Tags.ListTags.load_toBuyList);
//		lv.setTag(Tags.ListTags.delete_toBuyList);
//		
//		lv.setOnItemClickListener(new LOI_CL(actv, dlg1, dlg2));
//		
//		
//		lv.setAdapter(adp);
//		
//		/***************************************
//		 * Show dialog
//		 ***************************************/
//		dlg2.show();
//		
//		
//	}//dlg_LoadToBuyList(Activity actv, Dialog dlg)
//
	
	public static void
	dlg_TabActv_ClearSelections(Activity actv) {
		Dialog dlg = Methods_dlg.dlg_template_cancel(
				actv,
				R.layout.dlg_db_admin, 
				R.string.menu_listitem_tabToBuy_clear_selections,
				
				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.DLG_GENERIC_DISMISS);

		/*----------------------------
		* 2. Prep => List
		----------------------------*/
		String[] choices = {
			actv.getString(R.string.menu_listitem_tabToBuy_clear_basket),
			actv.getString(R.string.menu_listitem_tabToBuy_clear_checked_items),
			actv.getString(R.string.generic_label_all),
		};
		
		List<String> list = new ArrayList<String>();
		
		for (String item : choices) {
		
			list.add(item);
		
		}
		
		/*----------------------------
		* 3. Adapter
		----------------------------*/
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
								actv,
								//R.layout.dlg_db_admin,
								android.R.layout.simple_list_item_1,
								list
		);
		
		/*----------------------------
		* 4. Set adapter
		----------------------------*/
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_db_admin_lv);
		
		lv.setAdapter(adapter);
		
		/*----------------------------
		* 5. Set listener to list
		----------------------------*/
		lv.setTag(Tags.DialogTags.dlg_clear_selections);
		
		lv.setOnItemClickListener(new DOI_CL(actv, dlg));
		
		/*----------------------------
		* 6. Show dialog
		----------------------------*/
		dlg.show();

	}//public static void dlg_tabActv_clearSelections(Activity actv)

	
	public static void
	dlg_scheduleInDb(Activity actv, Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub
//		Methods_dlg.dlg_template_okCancel_3Dialogues_3Choices(actv, layoutId, titleStringId, okButtonId, cancelButtonId, thirdButtonId, okTag, cancelTag, thirdChoiceTag, dlg1, dlg2)
		Dialog d3 = Methods_dlg.dlg_template_okCancel_3Dialogues_3Choices(
							actv,
							R.layout.dlg_template_3choices_tv,
							R.string.generic_confirm,
							
							R.id.dlg_template_3choices_tv_btn_ok,
							R.id.dlg_template_3choices_tv_btn_cancel,
							R.id.dlg_template_3choices_tv_btn_choice,
							
							Tags.DialogTags.dlg_scheduleInDb_ok,
							Tags.DialogTags.DLG_GENERIC_DISMISS_3RD_DIALOG,
							Tags.DialogTags.dlg_scheduleInDb_update,
							
							d1, d2);
		
		/***************************************
		 * Set text to the third button
		 ***************************************/
		Button btChoice = (Button) d3.findViewById(R.id.dlg_template_3choices_tv_btn_choice);
		
		btChoice.setText(actv.getString(R.string.generic_label_update));
		
		/***************************************
		 * Set message
		 ***************************************/
		d3.show();
		
	}//dlg_scheduleInDb(Activity actv, Dialog dlg1, Dialog dlg2)

	
	public static
	Dialog dlg_template_okCancel(
			Activity actv,
			int layoutId, int titleStringId,
			
			int okButtonId, int cancelButtonId,
			
			DialogTags okTag, DialogTags cancelTag,
			SI si) {
		// TODO Auto-generated method stub
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(layoutId);
		
		// Title
		dlg.setTitle(titleStringId);
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_ok = (Button) dlg.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(okTag);
		btn_cancel.setTag(cancelTag);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_ok.setOnClickListener(new DB_OCL(actv, dlg, si));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		//
		//dlg.show();
		
		return dlg;
		
		
//		return null;
	}//dlg_template_okCancel()

	
	public static void dlg_SortList(Activity actv) {
		
		Dialog dlg = Methods_dlg.dlg_template_cancel(
				actv,
				R.layout.dlg_db_admin, 
				R.string.dlg_sort_list_title, 
				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.DLG_GENERIC_DISMISS);

		/*----------------------------
		* 2. Prep => List
		----------------------------*/
		String[] choices = {
					actv.getString(R.string.dlg_sort_list_item_name),
					actv.getString(R.string.dlg_sort_list_genre_item_name),
					actv.getString(R.string.dlg_sort_list_store_item_name),
					actv.getString(R.string.dlg_sort_list_store_genre_item_name),
		};
		
		List<String> list = new ArrayList<String>();
		
		for (String item : choices) {
		
			list.add(item);
		
		}
		
		/*----------------------------
		* 3. Adapter
		----------------------------*/
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				actv,
				//R.layout.dlg_db_admin,
				android.R.layout.simple_list_item_1,
				list
		);
		
		/*----------------------------
		* 4. Set adapter
		----------------------------*/
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_db_admin_lv);
		
		lv.setAdapter(adapter);
		
		/*----------------------------
		* 5. Set listener to list
		----------------------------*/
		lv.setTag(Tags.DialogTags.dlg_sort_list_lv);
		
		lv.setOnItemClickListener(new DOI_CL(actv, dlg));
		
		/*----------------------------
		* 6. Show dialog
		----------------------------*/
		dlg.show();
		
		
	}//public static void dlg_SortList(Activity actv)

	public static void
	dlg_Tab1_Edit_Item
	(Activity actv, SI si, Dialog dlg1) {

		/***************************************
		 * Dialog
		 ***************************************/
//		Dialog dlg2 = Methods_dlg.dlg_template_okCancel(
		Dialog d2 = Methods_dlg.dlg_template_okCancel_2Dialogues_SI(
						actv,
						R.layout.dlg_edit_items,
						R.string.dlg_edit_items_title,
						
						R.id.dlg_edit_items_btn_ok,
						R.id.dlg_edit_items_btn_cancel,
						
//						Tags.DialogTags.dlg_edit_items_bt_ok,
						Tags.DialogTags.DLG_EDIT_ITEMS_BT_OK,
						Tags.DialogTags.DLG_GENERIC_DISMISS_SECOND_DIALOG,
						
						dlg1,
						si);

		/***************************************
		 * Set store name
		 ***************************************/
		case_tab_itemList__setStoreName(si, d2, actv);
		
		// Log
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "setStoreName => Done");
		
		/***************************************
		 * Set: Item name
		 ***************************************/
		case_tab_itemList__setItemNameAndYomi(si, d2);
		
		// Log
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Item name and yomi => Done");
		
		/***************************************
		 * Set: Price and genre
		 ***************************************/
		case_tab_itemList__setPrice(si, d2);

		/***************************************
		 * Set: Genre
		 ***************************************/
		case_tab_itemList__setGenre(actv, si, d2);

		////////////////////////////////

		// set: num

		////////////////////////////////
		// Log
		String msg_Log = "si.getNum() => " + si.getNum();
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		EditText et_Num = (EditText) d2.findViewById(R.id.dlg_edit_items_et_num);
		
		if (et_Num != null) {
			
			et_Num.setText(String.valueOf(si.getNum()));
//			et_Num.setText(si.getNum());
			
			et_Num.setSelection(String.valueOf(si.getNum()).length());
			
		} else {
			
			// Log
			msg_Log = "et_Num => null";
			Log.e("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

		}

		////////////////////////////////

		// show

		////////////////////////////////
		d2.show();
		
	}//dlg_tab1_edit_item(AdapterView<?> parent, int position)

	private static void
	case_tab_itemList__setItemNameAndYomi(SI si, Dialog dlg) {
		// TODO Auto-generated method stub
		EditText etItemName = (EditText) dlg.findViewById(R.id.dlg_edit_items_et_name);
		etItemName.setText(si.getName());

		EditText etYomi = (EditText) dlg.findViewById(R.id.dlg_edit_items_et_yomi);
		etYomi.setText(si.getYomi());
		
	}//case_tab_itemList__setItemNameAndYomi(ShoppingItem si, Dialog dlg)
	
	private static void
	case_tab_itemList__setPrice(SI si, Dialog dlg) {
		// TODO Auto-generated method stub

		EditText etPrice = (EditText) dlg.findViewById(R.id.dlg_edit_items_et_price);
		
//		etPrice.setText(si.getPrice());				//=> android.content.res.Resources$NotFoundException: String resource ID #0x64

		etPrice.setText(String.valueOf(si.getPrice()));
		
	}//case_tab_itemList__setPrice(ShoppingItem si, Dialog dlg)

	private static void
	case_tab_itemList__setStoreName
	(SI si, Dialog dlg, Activity actv) {
		// TODO Auto-generated method stub
		// Resource => http://www.java2s.com/Open-Source/Android/Samples/techbooster/org/jpn/techbooster/sample/spinner/SpinnerActivity.java.htm
		Spinner sp_store_name = (Spinner) dlg.findViewById(R.id.dlg_edit_items_sp_store);
		
		ArrayAdapter<String> adapter = Methods.get_Adp_List_Store(actv);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//	              actv, android.R.layout.simple_spinner_item);

//		/***************************************
//		 * Get store names from db
//		 ***************************************/
//		DBUtils dbm = new DBUtils(actv);
//		
//		SQLiteDatabase db = dbm.getReadableDatabase();
//		
//		Cursor c = dbm.getAllData(db, "stores", CONS.DB.columns_for_table_stores_with_index);
//		
//		// Log
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "c.getCount()" + c.getCount());
//		
//		c.moveToFirst();
//		
//		// Log
//		for (int i = 0; i < c.getCount(); i++) {
//
//			adapter.add(c.getString(1));
//
//			c.moveToNext();
//			
//		}//for (int i = 0; i < c.getCount(); i++)
//		
		
		/*----------------------------
		 * 3-1. setDropDownViewResource
			----------------------------*/
		adapter.setDropDownViewResource(
						android.R.layout.simple_spinner_dropdown_item);
		
//		/*----------------------------
//		 * 3-2. Close db
//			----------------------------*/
//		db.close();
		
		/*----------------------------
		 * 4. Set adapter to spinner
			----------------------------*/
		sp_store_name.setAdapter(adapter);

		/***************************************
		 * Set the initial store name
		 * 1. Get the position number
		 * 2. Set the selection
		 ***************************************/
		/***************************************
		 * 1. Get the position number
		 ***************************************/
		int num = 0;
		
		for (int i = 0; i < adapter.getCount(); i++) {
			
			String storeName = adapter.getItem(i);
			
			// Log
			Log.d("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
//					"si.getName()=" + si.getName()
					"si.getStore()=" + si.getStore()
					+ "/"
					+ "storeName=" + storeName);
			
//			if (si.getName().equals(storeName)) {
			if (si.getStore().equals(storeName)) {
				
				// Log
				Log.d("Methods_dlg.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "si.getName().equals(storeName)");
				
				num = i;
				
				break;
				
			}//if (si.getName() == condition)
			
		}//for (int i = 0; i < adapter.getCount(); i++)
		
		sp_store_name.setSelection(num);
		
	}//case_tab_itemList__setStoreName(ShoppingItem si, Dialog dlg)

	private static void
	_dlg_Register_Item__setStoreName
	(Activity actv, Dialog dlg) {
		// TODO Auto-generated method stub
		// Resource => http://www.java2s.com/Open-Source/Android/Samples/techbooster/org/jpn/techbooster/sample/spinner/SpinnerActivity.java.htm
		Spinner sp_store_name = (Spinner) dlg.findViewById(R.id.dlg_edit_items_sp_store);
		
		ArrayAdapter<String> adapter = Methods.get_Adp_List_Store(actv);
		
		/*----------------------------
		 * 3-1. setDropDownViewResource
			----------------------------*/
		adapter.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		
		/*----------------------------
		 * 4. Set adapter to spinner
			----------------------------*/
		sp_store_name.setAdapter(adapter);
		
		/***************************************
		 * Set the initial store name
		 * 1. Get the position number
		 * 2. Set the selection
		 ***************************************/
		/***************************************
		 * 1. Get the position number
		 ***************************************/
		int num = 0;
		
		sp_store_name.setSelection(num);
		
	}//case_tab_itemList__setStoreName(ShoppingItem si, Dialog dlg)
	
	private static void
	case_tab_itemList__setGenre
	(Activity actv, SI si, Dialog dlg) {
		// TODO Auto-generated method stub
		// Resource => http://www.java2s.com/Open-Source/Android/Samples/techbooster/org/jpn/techbooster/sample/spinner/SpinnerActivity.java.htm
		Spinner sp_genre_name = (Spinner) dlg.findViewById(R.id.dlg_edit_items_sp_genre);
		
		ArrayAdapter<String> adapter = Methods.get_Adp_List_Genre(actv);
				
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//	              actv, android.R.layout.simple_spinner_item);
////
//		/*----------------------------
//		 * 2. Get genre names from db
//			----------------------------*/
//		DBUtils dbm = new DBUtils(actv);
//		
//		SQLiteDatabase db = dbm.getReadableDatabase();
//		
//		Cursor c = dbm.getAllData(db, "genres", CONS.DB.columns_for_table_genres_with_index);
//		
//		// Log
//		Log.d("RegisterItem.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "c.getCount()" + c.getCount());
//		
//		c.moveToFirst();
//		
//		// Log
//		for (int i = 0; i < c.getCount(); i++) {
//
//			adapter.add(c.getString(1));
//
//			c.moveToNext();
//		}//for (int i = 0; i < c.getCount(); i++)
		
		
		/*----------------------------
		 * 3-1. setDropDownViewResource
			----------------------------*/
		adapter.setDropDownViewResource(
						android.R.layout.simple_spinner_dropdown_item);
		
//		/*----------------------------
//		 * 3-2. Close db
//			----------------------------*/
//		db.close();
		
		/*----------------------------
		 * 4. Set adapter to spinner
			----------------------------*/
		sp_genre_name.setAdapter(adapter);
		
		/***************************************
		 * Set initial value
		 ***************************************/
		int num = 0;
		
		for (int i = 0; i < adapter.getCount(); i++) {
			
			String genreName = adapter.getItem(i);
			
			// Log
			Log.d("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
//					"si.getName()=" + si.getName()
					"si.getGenre()=" + si.getGenre()
					+ "/"
					+ "genreName=" + genreName);
			
//			if (si.getName().equals(storeName)) {
			if (si.getGenre().equals(genreName)) {
				
				// Log
				Log.d("Methods_dlg.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"si.getGenre().equals(genreName)");
				
				num = i;
				
				break;
				
			}//if (si.getName() == condition)
			
		}//for (int i = 0; i < adapter.getCount(); i++)
		
		sp_genre_name.setSelection(num);

	
	}//case_tab_itemList__setGenre(ShoppingItem si, Dialog dlg)

	private static void
	_dlg_Register_Item__setGenre
	(Activity actv, Dialog dlg) {
		// TODO Auto-generated method stub
		// Resource => http://www.java2s.com/Open-Source/Android/Samples/techbooster/org/jpn/techbooster/sample/spinner/SpinnerActivity.java.htm
		Spinner sp_genre_name = (Spinner) dlg.findViewById(R.id.dlg_edit_items_sp_genre);
		
		ArrayAdapter<String> adapter = Methods.get_Adp_List_Genre(actv);
		
		/*----------------------------
		 * 3-1. setDropDownViewResource
			----------------------------*/
		adapter.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		
		/*----------------------------
		 * 4. Set adapter to spinner
			----------------------------*/
		sp_genre_name.setAdapter(adapter);
		
		/***************************************
		 * Set initial value
		 ***************************************/
		int num = 0;
		
		sp_genre_name.setSelection(num);
		
	}//case_tab_itemList__setGenre(ShoppingItem si, Dialog dlg)
	
	public static void
	dlg_tab1_delete_item
	(Activity actv, SI si, Dialog dlg1) {
		// TODO Auto-generated method stub
		Dialog dlg2 = Methods_dlg.dlg_template_okCancel_2Dialogues_SI(
						actv,
						R.layout.dlg_template_ok_cancel,
						R.string.generic_confirm,
						
						R.id.dlg_template_ok_cancel_btn_ok,
//						R.id.dlg_template_ok_cancel_btn_ok,
						R.id.dlg_template_ok_cancel_btn_cancel,
						
						Tags.DialogButtonTags.tab1_delete_item_ok,
						Tags.DialogButtonTags.generic_cancel_second_dialog,
				
						dlg1, si);
		
		/*********************************
		 * Set: message
		 *********************************/
		TextView tv_message =
				(TextView) dlg2.findViewById(
						R.id.dlg_template_ok_cancel_tv_message);
//		R.id.dlg_template_ok_cancel_tv_string);
		
		tv_message.setText(actv.getString(R.string.generic_message_delete_item));
//		tv_message.setText("Delete the item?");
		
		/*********************************
		 * Set: Item name
		 *********************************/
		TextView tv_itemName =
				(TextView) dlg2.findViewById(
						R.id.dlg_template_ok_cancel_tv_item_name);
//		R.id.dlg_template_ok_cancel_tv_string);
		
		tv_itemName.setText(si.getName());
		
		/*********************************
		 * Show: dlg2
		 *********************************/
		dlg2.show();
		
	}//dlg_tab1_delete_item

	public static void
	dlg_PostToBuyList(Activity actv, Dialog dlg1) {
		// TODO Auto-generated method stub
		/*********************************
		 * Validation
		 *********************************/
		if (CONS.TabActv.tab_toBuyItemIds == null) {
			
			// debug
			Toast.makeText(actv,
					"To-buy list => Null", Toast.LENGTH_LONG).show();
			
			return;
			
		}
		
		if (CONS.TabActv.tab_toBuyItemIds.size() < 1) {
			
			// debug
			Toast.makeText(actv,
					"To-buy list => No entry", Toast.LENGTH_LONG).show();
			
			return;
			
		}

		/*********************************
		 * Build: Dialog 2
		 *********************************/
		Dialog dlg2 = Methods_dlg.dlg_template_okCancel_2Dialogues(
				actv,
				R.layout.dlg_template_ok_cancel,
				R.string.generic_confirm,
				
				R.id.dlg_template_ok_cancel_btn_ok,
//				R.id.dlg_template_ok_cancel_btn_ok,
				R.id.dlg_template_ok_cancel_btn_cancel,
				
				Tags.DialogButtonTags.tab2_post_items_ok,
				Tags.DialogButtonTags.generic_cancel_second_dialog,
		
				dlg1);
		
		/*********************************
		 * Get: Views
		 *********************************/
		TextView tv_Message = (TextView) dlg2.findViewById(
				R.id.dlg_template_ok_cancel_tv_message);
		
		TextView tv_Value = (TextView) dlg2.findViewById(
				R.id.dlg_template_ok_cancel_tv_item_name);
		
		/*********************************
		 * Modify: Views
		 *********************************/
		//REF http://stackoverflow.com/questions/4602902/how-to-set-text-color-of-textview-in-code answered Jan 5 '11 at 10:17
		tv_Message.setTextColor(Color.WHITE);
		
		/*********************************
		 * Add: Message
		 *********************************/
		tv_Message.setText(actv.getString(
						R.string.dlg_post_bought_items_message));
		
		tv_Value.setText(String.valueOf(CONS.TabActv.tab_toBuyItemIds.size()) + " items");
		
		dlg2.show();
		
	}//dlg_UploadToBuyList(Activity actv, Dialog dlg)

	private static Dialog
	dlg_template_okCancel_2Dialogues(
			Activity actv,
			int layoutId, int dlgTitle,
			
			int okButtonId, int cancelButtonId,
			
			DialogButtonTags btnTagOK,
			DialogButtonTags btnTagCancel,

			Dialog dlg1) {
		/*----------------------------
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		----------------------------*/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(dlgTitle);
		
		/*----------------------------
		* 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_ok = (Button) dlg2.findViewById(okButtonId);
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		//
		btn_ok.setTag(btnTagOK);
		btn_cancel.setTag(btnTagCancel);
		
		//
		btn_ok.setOnTouchListener(
				new DB_OTL(actv, dlg2));
//		new DialogButtonOnTouchListener(actv, dlg2));
		btn_cancel.setOnTouchListener(
				new DB_OTL(actv, dlg2));
//		new DialogButtonOnTouchListener(actv, dlg2));
		
		/*----------------------------
		* 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_ok.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2));
		btn_cancel.setOnClickListener(
				new DB_OCL(actv, dlg1, dlg2));
		
		//
		//dlg.show();
		
		return dlg2;

	}//dlg_template_okCancel_2Dialogues

	public static void
	dlg_ShowMessage(Activity actv, String message) {
		
		Dialog dlg = Methods_dlg.dlg_Template_Cancel(
				actv, R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_confirm, 
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.DLG_GENERIC_DISMISS);
		
		TextView tv_Message = 
				(TextView) dlg.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		dlg.show();
		
	}

	public static
	Dialog dlg_Template_Cancel
	(Activity actv, int layoutId, int titleStringId,
			int cancelButtonId, DialogTags tag_CancelButton) {
		/****************************
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		****************************/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(layoutId);
		
		// Title
		dlg.setTitle(titleStringId);
		
		/****************************
		* 2. Add listeners => OnTouch
		****************************/
		//
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(tag_CancelButton);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg));
		
		/****************************
		* 3. Add listeners => OnClick
		****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		//
		//dlg.show();
		
		return dlg;
	
	}//public static Dialog dlg_template_okCancel()

	public static void
	dlg_ShowMessage
	(Activity actv, String message, int colorId) {
		
		Dialog dlg = Methods_dlg.dlg_Template_Cancel(
				actv, R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_ok, 
//				R.string.generic_tv_confirm, 
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.DLG_GENERIC_DISMISS);
		
		TextView tv_Message = 
				(TextView) dlg.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		////////////////////////////////

		// background

		////////////////////////////////
		tv_Message.setBackgroundColor(actv.getResources().getColor(colorId));
		
		dlg.show();
		
	}//dlg_ShowMessage

	public static void 
	dlg_OptMenu_TabActv_Admin
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// vars

		////////////////////////////////
		String msg_Log;
		
		
		////////////////////////////////

		// dlg

		////////////////////////////////
		Dialog d1 = Methods_dlg.dlg_Template_Cancel(
						actv,
						R.layout.dlg_tmpl_cancel_lv,
						R.string.menu_listitem_tabToBuy_admin,
						
						R.id.dlg_tmpl_cancel_lv_bt_cancel,
						Tags.DialogTags.DLG_GENERIC_DISMISS);
		
		/****************************
		* 2. Prep => List
		****************************/
//		String[] choices = {
//					actv.getString(R.string.dlg_actvmain_lv_delete),
//					};
		
		List<ListItem> list = new ArrayList<ListItem>();
//		List<String> list = new ArrayList<String>();
		
		list.add(new ListItem.Builder()
						.setText(actv.getString(
									R.string.opt_TabActv_Admin_Main__BackupDB))
						.setIconID(R.drawable.menu_icon_admin_32x32)
						.setTextColor_ID(R.color.blue1)
						.build());
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.opt_TabActv_Admin_Main__Operations))
						.setIconID(R.drawable.menu_icon_admin_32x32_brown)
						.setTextColor_ID(R.color.black)
						.build());
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.opt_TabActv_Admin_Main__SeeLog))
								.setIconID(R.drawable.menu_icon_admin_32x32_purple)
								.setTextColor_ID(R.color.purple4)
								.build());
		
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.opt_TabActv_Admin_Main__Register_Item))
							.setIconID(R.drawable.menu_icon_admin_32x32_green)
							.setTextColor_ID(R.color.darkgreen)
							.build());
		
		/****************************
		* 3. Adapter
		****************************/
		Adp_ListItems adapter = new Adp_ListItems(
							actv,
							//R.layout.dlg_db_admin,
							R.layout.list_row_simple_iv_1,
							//android.R.layout.simple_list_item_1,
							list
		);
		
		/****************************
		* 4. Set adapter
		****************************/
		ListView lv = (ListView) d1.findViewById(R.id.dlg_tmpl_cancel_lv_lv);
		
		lv.setAdapter(adapter);
		
		/****************************
		* 5. Set listener to list
		****************************/
		lv.setTag(Tags.DialogTags.ACTV_TAB_OPT_ADMIN);
		
		lv.setOnItemClickListener(new DOI_CL(actv, d1));
		
		/***************************************
		* Modify the list view height
		***************************************/
//		lv.setLayoutParams(
//				new LinearLayout.LayoutParams(
//						300,	//	Width
//						LayoutParams.WRAP_CONTENT	//	Height
//				));
		
		/***************************************
		* Modify: Button layout
		***************************************/
		LinearLayout llButton =
					(LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_filepath);
//		(LinearLayout) dlg1.findViewById(R.id.actv_imp_ll_filepath);
		
		LinearLayout.LayoutParams params =
				new LinearLayout.LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);
		
		params.gravity = Gravity.CENTER_HORIZONTAL;
		
		llButton.setLayoutParams(params);

		////////////////////////////////

		// get: screen size

		////////////////////////////////
		//REF size http://stackoverflow.com/questions/19155559/how-to-get-android-device-screen-size answered Oct 3 '13 at 10:00
		DisplayMetrics displayMetrics = actv.getResources()
                			.getDisplayMetrics();
		
		int w = displayMetrics.widthPixels;
		
//		// Log
//		String msg_Log = "w => " + w;
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		int dialog_Width = w * CONS.Admin.ratio_Dialog_to_Screen_W / 100;
		
//		// Log
//		msg_Log = "dialog_Width => " + dialog_Width;
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		////////////////////////////////

		// linear layot: main

		////////////////////////////////
		LinearLayout ll_Main = (LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_main);
		
		// Log
		msg_Log = "ll_Main => created";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		//REF parent layout http://stackoverflow.com/questions/4631966/set-relativelayout-layout-params-programmatically-throws-classcastexception answered Jan 8 '11 at 5:42
//		08-21 11:30:45.434: E/AndroidRuntime(20722): java.lang.ClassCastException: android.widget.LinearLayout$LayoutParams
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.widget.FrameLayout.onLayout(FrameLayout.java:293)
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.view.View.layout(View.java:7184)

		FrameLayout.LayoutParams params2 =
				new FrameLayout.LayoutParams(
//						LinearLayout.LayoutParams params2 =
//						new LinearLayout.LayoutParams(
						dialog_Width,
//						400,
//						200,
//						LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT);
		
		// Log
		msg_Log = "setting params...";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		ll_Main.setLayoutParams(params2);
		
		// Log
		msg_Log = "params => set";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		/****************************
		* 6. Show dialog
		****************************/
		d1.show();

		
	}//dlg_OptMenu_TabActv_Admin

	public static void 
	dlg_OptMenu_TabActv_Opearations
	(Activity actv, Dialog d1) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// vars

		////////////////////////////////
		String msg_Log;
		
		////////////////////////////////

		// dlg

		////////////////////////////////
		Dialog d2 = Methods_dlg.dlg_Template_Cancel_SecondDialog(
						actv, d1,
						R.layout.dlg_tmpl_cancel_lv_with_btn,
						R.string.opt_TabActv_Admin_Main__Operations,
						
						R.id.dlg_tmpl_cancel_lv_with_btn_bt_cancel,
						Tags.DialogTags.GENERIC_CANCEL_SECOND_DIALOG);
		
		/****************************
		* 2. Prep => List
		****************************/
//		String[] choices = {
//					actv.getString(R.string.dlg_actvmain_lv_delete),
//					};
		
		List<ListItem> list = new ArrayList<ListItem>();
//		List<String> list = new ArrayList<String>();
		
		list.add(new ListItem.Builder()
						.setText(actv.getString(
									R.string.opt_TabActv_Admin_Ops__RestoreDB))
						.setIconID(R.drawable.menu_icon_admin_32x32)
						.setTextColor_ID(R.color.blue1)
						.build());
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.opt_TabActv_Admin_Ops__Tables))
						.setIconID(R.drawable.menu_icon_admin_32x32_brown)
						.setTextColor_ID(R.color.black)
						.build());
		
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.opt_TabActv_Admin_Ops__GetYomi))
								.setIconID(R.drawable.menu_icon_admin_32x32_purple)
								.setTextColor_ID(R.color.purple4)
								.build());
		
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.opt_TabActv_Admin_Ops__PostData))
							.setIconID(R.drawable.menu_icon_admin_32x32_purple)
							.setTextColor_ID(R.color.purple4)
							.build());
		
		////////////////////////////////

		// import data

		////////////////////////////////
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.opt_TabActv_Admin_ImportData_SI))
							.setIconID(R.drawable.menu_icon_admin_32x32_blue)
							.setTextColor_ID(R.color.blue1)
							.build());
		
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.opt_TabActv_Admin_ImportData_Stores))
							.setIconID(R.drawable.menu_icon_admin_32x32_green)
							.setTextColor_ID(R.color.green4)
							.build());
		
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.opt_TabActv_Admin_ImportData_Genres))
							.setIconID(R.drawable.menu_icon_admin_32x32_purple)
							.setTextColor_ID(R.color.purple4)
							.build());
		
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.opt_TabActv_Admin_Insert_Num))
							.setIconID(R.drawable.menu_icon_admin_32x32_yellow)
							.setTextColor_ID(R.color.black)
							.build());
		
		
		
		/****************************
		* 3. Adapter
		****************************/
		Adp_ListItems adapter = new Adp_ListItems(
							actv,
							//R.layout.dlg_db_admin,
							R.layout.list_row_simple_iv_1,
							//android.R.layout.simple_list_item_1,
							list
		);
		
		/****************************
		* 4. Set adapter
		****************************/
		ListView lv = (ListView) d2.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_lv);
		
		lv.setAdapter(adapter);
		
		/****************************
		* 5. Set listener to list
		****************************/
		lv.setTag(Tags.DialogTags.ACTV_TAB_OPT_OPERATIONS);
		
		lv.setOnItemClickListener(new DOI_CL(actv, d1, d2));
		
		////////////////////////////////

		// button: all dismiss

		////////////////////////////////
		ImageButton ib_AllClear = 
				(ImageButton) d2.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_ib);
//		(ImageButton) d2.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_ib);
		
		ib_AllClear.setTag(Tags.DialogTags.GENERIC_DISMISS_ALL_2ND_DIALOG);
		
		ib_AllClear.setOnTouchListener(new DB_OTL(actv, d1, d2));
		
		ib_AllClear.setOnClickListener(new DB_OCL(actv, d1, d2));

		/***************************************
		* Modify the list view height
		***************************************/
//		lv.setLayoutParams(
//				new LinearLayout.LayoutParams(
//						300,	//	Width
//						LayoutParams.WRAP_CONTENT	//	Height
//				));
		
		/***************************************
		* Modify: Button layout
		***************************************/
		LinearLayout llButton =
					(LinearLayout) d2.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_ll_filepath);
//		(LinearLayout) dlg1.findViewById(R.id.actv_imp_ll_filepath);
		
		LinearLayout.LayoutParams params =
				new LinearLayout.LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);
		
		params.gravity = Gravity.CENTER_HORIZONTAL;
		
		llButton.setLayoutParams(params);

		////////////////////////////////

		// get: screen size

		////////////////////////////////
		//REF size http://stackoverflow.com/questions/19155559/how-to-get-android-device-screen-size answered Oct 3 '13 at 10:00
		DisplayMetrics displayMetrics = actv.getResources()
                			.getDisplayMetrics();
		
		int w = displayMetrics.widthPixels;
		
//		// Log
//		String msg_Log = "w => " + w;
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		int dialog_Width = w * CONS.Admin.ratio_Dialog_to_Screen_W / 100;
		
//		// Log
//		msg_Log = "dialog_Width => " + dialog_Width;
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		////////////////////////////////

		// linear layot: main

		////////////////////////////////
		LinearLayout ll_Main = (LinearLayout) d2.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_ll_main);
		
		// Log
		msg_Log = "ll_Main => created";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		//REF parent layout http://stackoverflow.com/questions/4631966/set-relativelayout-layout-params-programmatically-throws-classcastexception answered Jan 8 '11 at 5:42
//		08-21 11:30:45.434: E/AndroidRuntime(20722): java.lang.ClassCastException: android.widget.LinearLayout$LayoutParams
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.widget.FrameLayout.onLayout(FrameLayout.java:293)
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.view.View.layout(View.java:7184)

		FrameLayout.LayoutParams params2 =
				new FrameLayout.LayoutParams(
//						LinearLayout.LayoutParams params2 =
//						new LinearLayout.LayoutParams(
						dialog_Width,
//						400,
//						200,
//						LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT);
		
		// Log
		msg_Log = "setting params...";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		ll_Main.setLayoutParams(params2);
		
		// Log
		msg_Log = "params => set";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		/****************************
		* 6. Show dialog
		****************************/
		d2.show();
		
	}//dlg_OptMenu_TabActv_Opearations

	public static Dialog 
	dlg_Template_Cancel_SecondDialog
	(Activity actv, Dialog dlg1,
			int layoutId, int titleStringId,
			int cancelButtonId, DialogTags tag_CancelButton) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(layoutId);
		
		// Title
		dlg2.setTitle(titleStringId);
		
		/****************************
		 * 2. Add listeners => OnTouch
		 ****************************/
		//
		Button btn_cancel = (Button) dlg2.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(tag_CancelButton);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg2));
		
		/****************************
		 * 3. Add listeners => OnClick
		 ****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2));
		
		//
		//dlg.show();
		
		return dlg2;
		
	}//public static Dialog dlg_template_okCancel()

	
	public static void 
	dlg_Op_Tables
	(Activity actv, Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub

		////////////////////////////////

		// vars

		////////////////////////////////
		String msg_Log;
		
		////////////////////////////////

		// dlg

		////////////////////////////////
		Dialog d3 = Methods_dlg.dlg_Template_Cancel_ThirdDialog(
						actv, d1, d2,
						R.layout.dlg_tmpl_cancel_lv_with_btn,
//						R.layout.dlg_tmpl_cancel_lv,
						R.string.opt_TabActv_Admin_Ops__Tables,
						
						R.id.dlg_tmpl_cancel_lv_with_btn_bt_cancel,
						Tags.DialogTags.DLG_GENERIC_DISMISS_3RD_DIALOG);
//		Tags.DialogTags.dlg_generic_dismiss_third_dialog);
		
		/****************************
		* 2. Prep => List
		****************************/
//		String[] choices = {
//					actv.getString(R.string.dlg_actvmain_lv_delete),
//					};
		
		List<ListItem> list = new ArrayList<ListItem>();
//		List<String> list = new ArrayList<String>();
		
		////////////////////////////////

		// tables

		////////////////////////////////
		list.add(new ListItem.Builder()
						.setText(actv.getString(
									R.string.opt_TabActv_Admin_Tables__Stores_Create))
						.setIconID(R.drawable.menu_icon_admin_32x32)
						.setTextColor_ID(R.color.blue1)
						.build());
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.opt_TabActv_Admin_Tables__Stores_Drop))
						.setIconID(R.drawable.menu_icon_admin_32x32_red)
						.setTextColor_ID(R.color.red)
						.build());
		
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.opt_TabActv_Admin_Tables__Genres_Create))
							.setIconID(R.drawable.menu_icon_admin_32x32)
							.setTextColor_ID(R.color.blue1)
							.build());
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.opt_TabActv_Admin_Tables__Genres_Drop))
							.setIconID(R.drawable.menu_icon_admin_32x32_red)
							.setTextColor_ID(R.color.red)
							.build());
		
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.opt_TabActv_Admin_Tables__SI_Create))
							.setIconID(R.drawable.menu_icon_admin_32x32)
							.setTextColor_ID(R.color.blue1)
							.build());
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.opt_TabActv_Admin_Tables__SI_Drop))
							.setIconID(R.drawable.menu_icon_admin_32x32_red)
							.setTextColor_ID(R.color.red)
							.build());
		
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.opt_TabActv_Admin_Tables__Create_PS))
							.setIconID(R.drawable.menu_icon_admin_32x32_blue)
							.setTextColor_ID(R.color.blue1)
							.build());
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.opt_TabActv_Admin_Tables__Drop_PS))
							.setIconID(R.drawable.menu_icon_admin_32x32_red)
							.setTextColor_ID(R.color.red)
							.build());
		
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.opt_TabActv_Admin_Tables__PH_Create))
							.setIconID(R.drawable.menu_icon_admin_32x32_blue)
							.setTextColor_ID(R.color.blue1)
							.build());
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.opt_TabActv_Admin_Tables__PH_Drop))
							.setIconID(R.drawable.menu_icon_admin_32x32_red)
							.setTextColor_ID(R.color.red)
							.build());
		
		/****************************
		* 3. Adapter
		****************************/
		Adp_ListItems adapter = new Adp_ListItems(
							actv,
							//R.layout.dlg_db_admin,
							R.layout.list_row_simple_iv_1,
							//android.R.layout.simple_list_item_1,
							list
		);
		
		/****************************
		* 4. Set adapter
		****************************/
		ListView lv = (ListView) d3.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_lv);
		
		lv.setAdapter(adapter);
		
		/****************************
		* 5. Set listener to list
		****************************/
		lv.setTag(Tags.DialogTags.ACTV_TAB_OPT_TABLES);
		
		lv.setOnItemClickListener(new DOI_CL(actv, d1, d2, d3));

		////////////////////////////////

		// button: all dismiss

		////////////////////////////////
		ImageButton ib_AllClear = 
				(ImageButton) d3.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_ib);
//		(ImageButton) d2.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_ib);
		
		ib_AllClear.setTag(Tags.DialogTags.GENERIC_DISMISS_ALL_3RD_DIALOG);
		
		ib_AllClear.setOnTouchListener(new DB_OTL(actv, d1, d2, d3));
		
		ib_AllClear.setOnClickListener(new DB_OCL(actv, d1, d2, d3));

		/***************************************
		* Modify the list view height
		***************************************/
//		lv.setLayoutParams(
//				new LinearLayout.LayoutParams(
//						300,	//	Width
//						LayoutParams.WRAP_CONTENT	//	Height
//				));
		
		/***************************************
		* Modify: Button layout
		***************************************/
		LinearLayout llButton =
					(LinearLayout) d3.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_ll_filepath);
//		(LinearLayout) dlg1.findViewById(R.id.actv_imp_ll_filepath);
		
		LinearLayout.LayoutParams params =
				new LinearLayout.LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);
		
		params.gravity = Gravity.CENTER_HORIZONTAL;
		
		llButton.setLayoutParams(params);

		////////////////////////////////

		// get: screen size

		////////////////////////////////
		//REF size http://stackoverflow.com/questions/19155559/how-to-get-android-device-screen-size answered Oct 3 '13 at 10:00
		DisplayMetrics displayMetrics = actv.getResources()
                			.getDisplayMetrics();
		
		int w = displayMetrics.widthPixels;
		
//		// Log
//		String msg_Log = "w => " + w;
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		int dialog_Width = w * CONS.Admin.ratio_Dialog_to_Screen_W / 100;
		
//		// Log
//		msg_Log = "dialog_Width => " + dialog_Width;
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		////////////////////////////////

		// linear layot: main

		////////////////////////////////
		LinearLayout ll_Main = (LinearLayout) 
						d3.findViewById(R.id.dlg_tmpl_cancel_lv_with_btn_ll_main);
		
		// Log
		msg_Log = "ll_Main => created";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		//REF parent layout http://stackoverflow.com/questions/4631966/set-relativelayout-layout-params-programmatically-throws-classcastexception answered Jan 8 '11 at 5:42
//		08-21 11:30:45.434: E/AndroidRuntime(20722): java.lang.ClassCastException: android.widget.LinearLayout$LayoutParams
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.widget.FrameLayout.onLayout(FrameLayout.java:293)
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.view.View.layout(View.java:7184)

		FrameLayout.LayoutParams params2 =
				new FrameLayout.LayoutParams(
//						LinearLayout.LayoutParams params2 =
//						new LinearLayout.LayoutParams(
						dialog_Width,
//						400,
//						200,
//						LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT);
		
		// Log
		msg_Log = "setting params...";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		ll_Main.setLayoutParams(params2);
		
		// Log
		msg_Log = "params => set";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		/****************************
		* 6. Show dialog
		****************************/
		d3.show();

		
	}//dlg_Op_Tables

	public static Dialog 
	dlg_Template_Cancel_ThirdDialog
	(Activity actv, Dialog dlg1, Dialog dlg2,
			int layoutId, int titleStringId,
			int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog dlg3 = new Dialog(actv);
		
		//
		dlg3.setContentView(layoutId);
		
		// Title
		dlg3.setTitle(titleStringId);
		
		/****************************
		 * 2. Add listeners => OnTouch
		 ****************************/
		//
		Button btn_cancel = (Button) dlg3.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, dlg1, dlg2, dlg3));
		
		/****************************
		 * 3. Add listeners => OnClick
		 ****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg1, dlg2, dlg3));
		
		//
		//dlg.show();
		
		return dlg3;
		
	}//public static Dialog dlg_template_okCancel()

	public static void 
	conf_DropTable
	(Activity actv, Dialog d1, Dialog d2, Dialog d3, 
		String tname, Tags.DialogTags tag_ButtonOK) {
		// TODO Auto-generated method stub
		
		Dialog d4 = 
				Methods_dlg.dlg_Template_OkCancel_4th_Dialog(
						actv, d1, d2, d3,
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						tag_ButtonOK, 
						Tags.DialogTags.GENERIC_DISMISS_4TH_DIALOG
//						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG 
						
						);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) d4.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				R.string.commons_lbl_drop_table)
				+ "?");
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) d4.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		tv_ItemName.setText(tname);
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		d4.show();
		
	}//conf_DropTable_Patterns

	public static Dialog 
	dlg_Template_Cancel_4th_Dialog
	(Activity actv, 
		Dialog d1, Dialog d2, Dialog d3,
		int layoutId, int titleStringId,
		int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog d4 = new Dialog(actv);
		
		//
		d4.setContentView(layoutId);
		
		// Title
		d4.setTitle(titleStringId);
		
		/****************************
		 * 2. Add listeners => OnTouch
		 ****************************/
		//
		Button btn_cancel = (Button) d4.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DB_OTL(actv, d1, d2, d3, d4));
		
		/****************************
		 * 3. Add listeners => OnClick
		 ****************************/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, d1, d2, d3, d4));
		
		//
		//dlg.show();
		
		return d4;
		
	}//public static Dialog dlg_template_okCancel()

	public static Dialog 
	dlg_Template_OkCancel_4th_Dialog
	(Activity actv, Dialog d1, Dialog d2, Dialog d3,
			
			int layoutId, int titleStringId,
			
			int okButtonId, int cancelButtonId,
			Tags.DialogTags okTag, Tags.DialogTags cancelTag) {
		/****************************
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 ****************************/
		
		// 
		Dialog d4 = new Dialog(actv);
		
		//
		d4.setContentView(layoutId);
		
		// Title
		d4.setTitle(titleStringId);
		
		////////////////////////////////
		
		// Button: cancel
		
		////////////////////////////////
		Button btn_Ok = (Button) d4.findViewById(okButtonId);
		
		//
		btn_Ok.setTag(okTag);
		
		//
		btn_Ok.setOnTouchListener(new DB_OTL(actv, d1, d2, d3, d4));
		
		btn_Ok.setOnClickListener(new DB_OCL(actv, d1, d2, d3, d4));
		
		////////////////////////////////

		// Button: cancel

		////////////////////////////////
		Button btn_Cancel = (Button) d4.findViewById(cancelButtonId);
		
		//
		btn_Cancel.setTag(cancelTag);
		
		//
		btn_Cancel.setOnTouchListener(new DB_OTL(actv, d1, d2, d3, d4));
		
		btn_Cancel.setOnClickListener(new DB_OCL(actv, d1, d2, d3, d4));
		
		return d4;
		
	}//dlg_Template_OkCancel_4th_Dialog

	public static void 
	conf_ImportData_SI
	(Activity actv, 
		Dialog d1, Dialog d2, DialogTags tag_ButtonOK) {
		// TODO Auto-generated method stub

		Dialog d3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_3rd_Dialogue(
						actv, d1, d2,
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						tag_ButtonOK, 
						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG
//						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG 
						
						);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) d3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				R.string.opt_TabActv_Admin_ImportData_Title)
				+ "?");
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) d3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
//		dlg_tmpl_confirm_simple_tv_message
		
		String tname = null;
		
		switch(tag_ButtonOK) {
		
		case ACTV_TAB_OPT_IMP_DATA_SI:
			
			tname = CONS.DB.tname_si;
			
			break;
			
		case ACTV_TAB_OPT_IMP_DATA_Stores:
			
			tname = CONS.DB.tname_stores;
			
			break;
			
		case ACTV_TAB_OPT_IMP_DATA_Genres:
			
			tname = CONS.DB.tname_genres;
			
			break;
			
		default:
			
			// Log
			String msg_Log = "unknown tag => " + tag_ButtonOK.toString();
			Log.d("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
		
		}
		
		tv_ItemName.setText(tname);
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		d3.show();

	}//conf_ImportData_SI

	public static void 
	conf_InsertNum_SI
	(Activity actv, 
			Dialog d1, Dialog d2, DialogTags tag_ButtonOK) {
		// TODO Auto-generated method stub
		
		Dialog d3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_3rd_Dialogue(
						actv, d1, d2,
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						tag_ButtonOK, 
						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG
//						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG 
						
						);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) d3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				R.string.opt_TabActv_Admin_Insert_Num)
				+ "?");
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) d3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
		
		tv_ItemName.setText(CONS.DB.tname_si);
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		d3.show();
		
	}//conf_InsertNum_SI
	
	public static void 
	conf_Delete_SI
	(Activity actv, Dialog d1, SI si) {
		// TODO Auto-generated method stub
		
		Dialog d2 = 
				Methods_dlg.dlg_template_okCancel_2Dialogues_SI(
						actv, 
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						Tags.DialogTags.DLG_CONFIRM_DELETE_SI_OK, 
//						Tags.DialogTags.dlg_confirm_delete_SI_OK, 
						Tags.DialogTags.GENERIC_DISMISS_SECOND_DIALOG, 
						d1, si);
		
////						Methods_dlg.dlg_Tmpl_OkCancel_3rd_Dialogue(
//						actv, d1, d2,
//						R.layout.dlg_tmpl_confirm_simple, 
//						R.string.generic_confirm, 
//						
//						R.id.dlg_tmpl_confirm_simple_btn_ok, 
//						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
//						
//						tag_ButtonOK, 
//						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG
////						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG 
//						
//						);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) d2.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				R.string.commons_lbl_Delete)
				+ "?");
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) d2.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
		
		tv_ItemName.setText(si.getName());
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		d2.show();
		
	}//conf_Delete_SI
	
	public static void 
	dlg_OptMenu_Lists
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// vars

		////////////////////////////////
		String msg_Log;
		
		
		////////////////////////////////

		// dlg

		////////////////////////////////
		Dialog d1 = Methods_dlg.dlg_Template_Cancel(
						actv,
						R.layout.dlg_tmpl_cancel_lv,
						R.string.menu_listitem_tabToBuy_admin,
						
						R.id.dlg_tmpl_cancel_lv_bt_cancel,
						Tags.DialogTags.DLG_GENERIC_DISMISS);
		
		////////////////////////////////

		// Prep => List

		////////////////////////////////
		List<ListItem> list = new ArrayList<ListItem>();
		
		list.add(new ListItem.Builder()
						.setText(actv.getString(
									R.string.opt_TabActv_Admin_Main__BackupDB))
						.setIconID(R.drawable.menu_icon_admin_32x32)
						.setTextColor_ID(R.color.blue1)
						.build());
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.opt_TabActv_Admin_Main__Operations))
						.setIconID(R.drawable.menu_icon_admin_32x32_brown)
						.setTextColor_ID(R.color.black)
						.build());
		list.add(new ListItem.Builder()
						.setText(actv.getString(
								R.string.opt_TabActv_Admin_Main__SeeLog))
								.setIconID(R.drawable.menu_icon_admin_32x32_purple)
								.setTextColor_ID(R.color.purple4)
								.build());
		
		/****************************
		* 3. Adapter
		****************************/
		Adp_ListItems adapter = new Adp_ListItems(
							actv,
							//R.layout.dlg_db_admin,
							R.layout.list_row_simple_iv_1,
							//android.R.layout.simple_list_item_1,
							list
		);
		
		/****************************
		* 4. Set adapter
		****************************/
		ListView lv = (ListView) d1.findViewById(R.id.dlg_tmpl_cancel_lv_lv);
		
		lv.setAdapter(adapter);
		
		/****************************
		* 5. Set listener to list
		****************************/
		lv.setTag(Tags.DialogTags.ACTV_TAB_OPT_ADMIN);
		
		lv.setOnItemClickListener(new DOI_CL(actv, d1));
		
		/***************************************
		* Modify the list view height
		***************************************/
//		lv.setLayoutParams(
//				new LinearLayout.LayoutParams(
//						300,	//	Width
//						LayoutParams.WRAP_CONTENT	//	Height
//				));
		
		/***************************************
		* Modify: Button layout
		***************************************/
		LinearLayout llButton =
					(LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_filepath);
//		(LinearLayout) dlg1.findViewById(R.id.actv_imp_ll_filepath);
		
		LinearLayout.LayoutParams params =
				new LinearLayout.LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);
		
		params.gravity = Gravity.CENTER_HORIZONTAL;
		
		llButton.setLayoutParams(params);

		////////////////////////////////

		// get: screen size

		////////////////////////////////
		//REF size http://stackoverflow.com/questions/19155559/how-to-get-android-device-screen-size answered Oct 3 '13 at 10:00
		DisplayMetrics displayMetrics = actv.getResources()
                			.getDisplayMetrics();
		
		int w = displayMetrics.widthPixels;
		
//		// Log
//		String msg_Log = "w => " + w;
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		int dialog_Width = w * CONS.Admin.ratio_Dialog_to_Screen_W / 100;
		
//		// Log
//		msg_Log = "dialog_Width => " + dialog_Width;
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		////////////////////////////////

		// linear layot: main

		////////////////////////////////
		LinearLayout ll_Main = (LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_main);
		
		// Log
		msg_Log = "ll_Main => created";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		//REF parent layout http://stackoverflow.com/questions/4631966/set-relativelayout-layout-params-programmatically-throws-classcastexception answered Jan 8 '11 at 5:42
//		08-21 11:30:45.434: E/AndroidRuntime(20722): java.lang.ClassCastException: android.widget.LinearLayout$LayoutParams
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.widget.FrameLayout.onLayout(FrameLayout.java:293)
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.view.View.layout(View.java:7184)

		FrameLayout.LayoutParams params2 =
				new FrameLayout.LayoutParams(
//						LinearLayout.LayoutParams params2 =
//						new LinearLayout.LayoutParams(
						dialog_Width,
//						400,
//						200,
//						LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT);
		
		// Log
		msg_Log = "setting params...";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		ll_Main.setLayoutParams(params2);
		
		// Log
		msg_Log = "params => set";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		/****************************
		* 6. Show dialog
		****************************/
		d1.show();
		
	}//dlg_OptMenu_Items

	public static void 
	dlg_OptMenu_DB
	(Activity actv) {
		// TODO Auto-generated method stub
		
		String msg_Log;
		
		////////////////////////////////

		// dialog

		////////////////////////////////
		Dialog d1 = Methods_dlg.dlg_Template_Cancel(
				actv,
				R.layout.dlg_tmpl_cancel_lv, 
//				R.layout.dlg_db_admin, 
				R.string.menu_listitem_tabToBuy_admin_db, 
				R.id.dlg_tmpl_cancel_lv_bt_cancel, 
//				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.DLG_GENERIC_DISMISS);
		
		/*----------------------------
		* 2. Prep => List
		----------------------------*/
		List<ListItem> list = new ArrayList<ListItem>();
		
		list.add(new ListItem.Builder()
						.setText(actv.getString(
							R.string.menu_listitem_tabToBuy_admin_db_save_tobuy_list))
						.setIconID(R.drawable.menu_icon_admin_32x32_blue)
						.setTextColor_ID(R.color.blue1)
						.build());

		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.menu_listitem_tabToBuy_admin_db_load_tobuy_list))
							.setIconID(R.drawable.menu_icon_admin_32x32_brown)
							.setTextColor_ID(R.color.black)
					.build());
		
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.menu_listitem_tabToBuy_admin_db_delete_tobuy_list))
							.setIconID(R.drawable.menu_icon_admin_32x32_purple)
							.setTextColor_ID(R.color.purple4)
							.build());
		
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.menu_listitem_tabToBuy_admin_db_post_tobuy_list))
							.setIconID(R.drawable.menu_icon_admin_32x32_green)
							.setTextColor_ID(R.color.green4)
							.build());
		
		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.menu_listitem_tabToBuy_admin_db_save_hist))
							.setIconID(R.drawable.menu_icon_admin_32x32_yellow)
							.setTextColor_ID(R.color.yellow_dark)
							.build());
		
		/****************************
		* 3. Adapter
		****************************/
		Adp_ListItems adapter = new Adp_ListItems(
							actv,
							//R.layout.dlg_db_admin,
							R.layout.list_row_simple_iv_1,
							//android.R.layout.simple_list_item_1,
							list
		);
		
		/****************************
		* 4. Set adapter
		****************************/
		ListView lv = (ListView) d1.findViewById(R.id.dlg_tmpl_cancel_lv_lv);
		
		lv.setAdapter(adapter);
		
		/****************************
		* 5. Set listener to list
		****************************/
		lv.setTag(Tags.DialogTags.ACTV_TAB_OPT_DB);
		
		lv.setOnItemClickListener(new DOI_CL(actv, d1));
		
		/***************************************
		* Modify the list view height
		***************************************/
//		lv.setLayoutParams(
//				new LinearLayout.LayoutParams(
//						300,	//	Width
//						LayoutParams.WRAP_CONTENT	//	Height
//				));
		
		/***************************************
		* Modify: Button layout
		***************************************/
		LinearLayout llButton =
					(LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_filepath);
//		(LinearLayout) dlg1.findViewById(R.id.actv_imp_ll_filepath);
		
		LinearLayout.LayoutParams params =
				new LinearLayout.LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT);
		
		params.gravity = Gravity.CENTER_HORIZONTAL;
		
		llButton.setLayoutParams(params);

		////////////////////////////////

		// get: screen size

		////////////////////////////////
		//REF size http://stackoverflow.com/questions/19155559/how-to-get-android-device-screen-size answered Oct 3 '13 at 10:00
		DisplayMetrics displayMetrics = actv.getResources()
                			.getDisplayMetrics();
		
		int w = displayMetrics.widthPixels;
		
		int dialog_Width = w * CONS.Admin.ratio_Dialog_to_Screen_W / 100;
		
		////////////////////////////////

		// linear layot: main

		////////////////////////////////
		LinearLayout ll_Main = (LinearLayout) d1.findViewById(R.id.dlg_tmpl_cancel_lv_ll_main);
		
		// Log
		msg_Log = "ll_Main => created";
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		//REF parent layout http://stackoverflow.com/questions/4631966/set-relativelayout-layout-params-programmatically-throws-classcastexception answered Jan 8 '11 at 5:42
//		08-21 11:30:45.434: E/AndroidRuntime(20722): java.lang.ClassCastException: android.widget.LinearLayout$LayoutParams
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.widget.FrameLayout.onLayout(FrameLayout.java:293)
//		08-21 11:30:45.434: E/AndroidRuntime(20722): 	at android.view.View.layout(View.java:7184)

		FrameLayout.LayoutParams params2 =
				new FrameLayout.LayoutParams(
						dialog_Width,
						LayoutParams.WRAP_CONTENT);
		
//		// Log
//		msg_Log = "setting params...";
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		ll_Main.setLayoutParams(params2);
		
//		// Log
//		msg_Log = "params => set";
//		Log.d("Methods_dlg.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		/****************************
		* 6. Show dialog
		****************************/
		d1.show();

		
	}//dlg_OptMenu_DB

	public static void
	dlg_ShowMessage_SecondDialog
	(Activity actv, String message, Dialog dlg1) {
		
		Dialog dlg2 = Methods_dlg.dlg_Template_Cancel_SecondDialog(
				actv, dlg1,
				R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_confirm, 
				
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS_SECOND_DIALOG);
		
		TextView tv_Message = 
				(TextView) dlg2.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		dlg2.show();
		
	}

	public static void
	dlg_ShowMessage_SecondDialog
	(Activity actv, 
		Dialog d1, String message, int colorID) {
		
		Dialog d2 = Methods_dlg.dlg_Template_Cancel_SecondDialog(
				actv, d1,
				R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_confirm, 
				
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS_SECOND_DIALOG);
		
		TextView tv_Message = 
				(TextView) d2.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		tv_Message.setBackgroundColor(actv.getResources().getColor(colorID));
		
		d2.show();
		
	}
	
	
	public static void
	dlg_ShowMessage_ThirdDialog
	(Activity actv, 
		String message, Dialog dlg1, Dialog dlg2) {
		
		Dialog dlg3 = Methods_dlg.dlg_Template_Cancel_ThirdDialog(
				actv, dlg1, dlg2,
				R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_confirm, 
				
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG);
		
		TextView tv_Message = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		dlg3.show();
		
	}

	public static void
	dlg_ShowMessage_ThirdDialog
	(Activity actv, 
		String message, Dialog dlg1, Dialog dlg2, int colorID) {
		
		Dialog dlg3 = Methods_dlg.dlg_Template_Cancel_ThirdDialog(
				actv, dlg1, dlg2,
				R.layout.dlg_tmpl_toast_ok, 
				R.string.generic_confirm, 
				
				R.id.dlg_tmpl_toast_ok_bt_cancel, 
				Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG);
		
		TextView tv_Message = 
				(TextView) dlg3.findViewById(R.id.dlg_tmpl_toast_ok_tv_message);
		
		tv_Message.setText(message);
		
		tv_Message.setBackgroundColor(actv.getResources().getColor(colorID));
		
		dlg3.show();
		
	}
	
	public static void 
	conf_Restore_DB
	(Activity actv, 
		Dialog d1, Dialog d2, DialogTags tag_ButtonOK) {
		// TODO Auto-generated method stub
		
		Dialog d3 = 
				Methods_dlg.dlg_Tmpl_OkCancel_3rd_Dialogue(
						actv, d1, d2,
						R.layout.dlg_tmpl_confirm_simple, 
						R.string.generic_confirm, 
						
						R.id.dlg_tmpl_confirm_simple_btn_ok, 
						R.id.dlg_tmpl_confirm_simple_btn_cancel, 
						
						tag_ButtonOK, 
						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG
//						Tags.DialogTags.GENERIC_DISMISS_THIRD_DIALOG 
						
						);
		
		////////////////////////////////
		
		// view: message
		
		////////////////////////////////
		TextView tv_Msg = 
				(TextView) d3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_message);
		
		tv_Msg.setText(actv.getString(
				R.string.opt_TabActv_Admin_Insert_Num)
				+ "?");
		
		////////////////////////////////
		
		// view: item name
		
		////////////////////////////////
		TextView tv_ItemName = 
				(TextView) d3.findViewById(R.id.dlg_tmpl_confirm_simple_tv_item_name);
		
		tv_ItemName.setText(CONS.DB.tname_si);
		
		////////////////////////////////
		
		// show
		
		////////////////////////////////
		d3.show();
		
	}//conf_Restore_DB

	public static void
	dlg_DeleteToBuyList
	(Activity actv, Dialog dlg1) {
		// TODO Auto-generated method stub
		/***************************************
		 * 1. Get cursor
		 * 2. Build a PS list
		 * 3. Show the list in the dialog
		 ***************************************/
		List<PS> psList = DBUtils.find_ALL_PSs(actv);
//		List<PS> psList = Methods_sl.getPSList(actv);

		//////////////////////////////////////////
		if (psList == null) {
			
			// Log
			Log.d("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "psList => Null");
			
			// debug
			Toast.makeText(actv, "Seems no purchase list", Toast.LENGTH_LONG).show();
			
			return;
			
		}//if (psList == null)
		
		/***************************************
		 * Sort list
		 ***************************************/
		Methods_sl.sortPSList(psList, Tags.SortTags.pslist_due_date);
		
		/***************************************
		 * 3. Show the list in the dialog
		 ***************************************/
//		(Activity actv, int layoutId, int titleStringId,
//				int cancelButtonId, DialogTags cancelTag, Dialog dlg1)
		Dialog dlg2 = Methods_dlg.dlg_template_cancel_2Dialogues(
				actv,
				R.layout.dlg_tmpl_cancel_lv, 
//				R.string.menu_listitem_tabToBuy_admin_db_save_tobuy_list,
				R.string.menu_listitem_tabToBuy_admin_db_delete_tobuy_list,
				
				R.id.dlg_tmpl_cancel_lv_bt_cancel,
//				dlg_generic_dismiss
//				Tags.DialogTags.dlg_generic_dismiss,
//				Tags.DialogTags.dlg_generic_dismiss_second_dialog,
				Tags.DialogTags.DLG_GENERIC_DISMISS_SECOND_DIALOG,
				
				dlg1);

		/***************************************
		 * Set list
		 ***************************************/
		PSListAdapter adp = new PSListAdapter(
				actv,
				R.layout.listrow_load_tobuy_list,
				psList
				);
		
		ListView lv = (ListView) dlg2.findViewById(R.id.dlg_tmpl_cancel_lv_lv);
		
		int lvHeight = Methods.getSmallerNumber(350, 75 * psList.size());
		
		lv.setLayoutParams(new LinearLayout.LayoutParams(
										LayoutParams.WRAP_CONTENT,	// Width
//										300));
										lvHeight));					// Height
		
//		lv.setTag(Tags.ListTags.load_toBuyList);
		lv.setTag(Tags.ListTags.delete_toBuyList);
		
		lv.setOnItemClickListener(new LOI_CL(actv, dlg1, dlg2));
//		lv.setOnItemClickListener(new ListOnItemClickListener(actv, dlg1, dlg2));
		
		
		lv.setAdapter(adp);
		
		/***************************************
		 * Show dialog
		 ***************************************/
		dlg2.show();
		
		
	}//dlg_LoadToBuyList(Activity actv, Dialog dlg)

	public static void 
	dlg_TabActv_SearchItems
	(Activity actv) {
		// TODO Auto-generated method stub
		
		Dialog d1 = Methods_dlg.dlg_Tmpl_OkCancel(
						actv, 
						R.layout.dlg_search_items, R.string.commons_lbl_search, 
						
						R.id.dlg_search_items_btn_ok, 
						R.id.dlg_search_items_btn_cancel, 
						
						Tags.DialogTags.ACTV_TAB_SEARCH_OK, 
						Tags.DialogTags.DLG_GENERIC_DISMISS);
		
		////////////////////////////////

		// spinner: store

		////////////////////////////////
		CONS.TabActv.spStore_SearchDlg = 
						(Spinner) d1.findViewById(R.id.dlg_search_items_sp_store);
		
		CONS.TabActv.adp_List_Store_SearchDlg = Methods.get_Adp_List_Store(actv);
		
		/******************************
			validate
		 ******************************/
		if (CONS.TabActv.spStore_SearchDlg == null) {
			
			// Log
			String msg_Log = "CONS.TabActv.spStore_SearchDlg => null";
			Log.e("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
			
		}
		
		/*----------------------------
		 * 4. Set adapter to spinner
			----------------------------*/
		CONS.TabActv.spStore_SearchDlg.setAdapter(CONS.TabActv.adp_List_Store_SearchDlg);
		
		// selection
		CONS.TabActv.spStore_SearchDlg.setSelection(
								CONS.TabActv.adp_List_Store_SearchDlg.getCount() - 1);
		
		////////////////////////////////

		// show

		////////////////////////////////
		d1.show();
		
	}//dlg_TabActv_SearchItems

	public static void 
	dlg_IsEmpty
	(Activity actv, Dialog d1) {

		Dialog d2 = new Dialog(actv);
		
		//
		d2.setContentView(R.layout.dlg_input_empty);
		
		// Title
		d2.setTitle(R.string.generic_notice);
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) d2.findViewById(R.id.dlg_input_empty_btn_ok);
//		Button btn_cancel = (Button) dlg2.findViewById(R.id.dlg_input_empty_btn_cancel);
		
		//
//		btn_ok.setTag(DialogTags.dlg_input_empty_reenter);
		btn_ok.setTag(DialogTags.GENERIC_DISMISS_SECOND_DIALOG);
//		btn_cancel.setTag(DialogTags.dlg_input_empty_cancel);
		
		//
		btn_ok.setOnTouchListener(new DB_OTL(actv, d1,d2));
//		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg2));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DB_OCL(actv, d1, d2));
//		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg, dlg2));
		
		//
		d2.show();
		
	}//dlg_IsEmpty

	public static void 
	dlg_Admin_ShoppingItem
	(Activity actv, SI si) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// setup: d1

		////////////////////////////////
		String title = actv.getString(R.string.generic_edit);
//		String title = si.getName() + "/" + si.getStore();
		
		Dialog d1 = Methods_dlg.dlg_template_cancel(actv, 
				R.layout.dlg_register_main, title,
//				R.id.dlg_register_main_btn_cancel, Tags.DialogTags.dlg_generic_cancel);
				R.id.dlg_register_main_btn_cancel, Tags.DialogTags.DLG_GENERIC_CANCEL);

		////////////////////////////////

		// label

		////////////////////////////////
		TextView tv = (TextView) d1.findViewById(R.id.dlg_register_main_tv);
		
		String label = String.format(
							"%s (%s)", 
							si.getName(), si.getStore());
		
		tv.setText(label);
		
		////////////////////////////////

		// build: list

		////////////////////////////////
		List<ListItem> list = new ArrayList<ListItem>();
		
		list.add(new ListItem.Builder()
						.setText(actv.getString(
							R.string.dlg_item_list_long_click_edit))
						.setIconID(R.drawable.menu_icon_admin_32x32_blue)
						.setTextColor_ID(R.color.blue1)
						.build());

		list.add(new ListItem.Builder()
					.setText(actv.getString(
							R.string.dlg_item_list_long_click_delete))
							.setIconID(R.drawable.menu_icon_admin_32x32_brown)
							.setTextColor_ID(R.color.brown)
					.build());
		
		/****************************
		* 3. Adapter
		****************************/
		Adp_ListItems adp = new Adp_ListItems(
							actv,
							//R.layout.dlg_db_admin,
							R.layout.list_row_simple_iv_1,
							//android.R.layout.simple_list_item_1,
							list
		);

		////////////////////////////////

		// listview

		////////////////////////////////
		ListView lv = (ListView) d1.findViewById(R.id.dlg_register_main_lv_list);
		
//		List<String> menuItem = new ArrayList<String>();
		
		/*----------------------------
		 * 2.4. Set adapter
		----------------------------*/
		lv.setAdapter(adp);
		
		/*********************************
		 * Set: tag
		 *********************************/
//		lv.setTag(Tags.ListViewTags.tab1_long_click);
		lv.setTag(Tags.DialogTags.TAB1_LONG_CLICK);
//		lv.setTag(Tags.ListViewTags.TAB1_LONG_CLICK);

		/*----------------------------
		 * 3. Set listener => list
			----------------------------*/
		lv.setOnItemClickListener(
				new DOI_CL(
						actv, 
						d1, 
//						Tags.DialogTags.dlg_item_list_long_click,
						si));

		
		d1.show();
		
	}//dlg_Admin_Item

	public static void 
	dlg_Register_item
	(Activity actv, Dialog d1) {
		// TODO Auto-generated method stub

		boolean res;
		
		String msg_Log;
		
		////////////////////////////////

		// dialog

		////////////////////////////////
		Dialog d2 = new Dialog(actv);
		
		//
		d2.setContentView(R.layout.dlg_edit_items);
		
		// Title
		d2.setTitle(R.string.generic_register);
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
		----------------------------*/
		//
		Button btn_ok = (Button) d2.findViewById(R.id.dlg_edit_items_btn_ok);
		Button btn_cancel = (Button) d2.findViewById(R.id.dlg_edit_items_btn_cancel);
		
		//
		btn_ok.setTag(Tags.DialogTags.REGISTER_ITEM_OK);
		btn_cancel.setTag(Tags.DialogTags.GENERIC_CANCEL_SECOND_DIALOG);
		
		//
		btn_ok.setOnTouchListener(
				new DB_OTL(actv, d1, d2));
//		new DialogButtonOnTouchListener(actv, dlg2));
		btn_cancel.setOnTouchListener(
				new DB_OTL(actv, d1, d2));
//		new DialogButtonOnTouchListener(actv, dlg2));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
		----------------------------*/
		//
		btn_ok.setOnClickListener(
				new DB_OCL(actv, d1, d2));
		btn_cancel.setOnClickListener(
				new DB_OCL(actv, d1, d2));

		////////////////////////////////

		// store names

		////////////////////////////////
		Methods_dlg._dlg_Register_Item__setStoreName(actv, d2);
		
		////////////////////////////////

		// genre names

		////////////////////////////////
		Methods_dlg._dlg_Register_Item__setGenre(actv, d2);
		
		////////////////////////////////

		// focus

		////////////////////////////////
		EditText et_Name = (EditText) d2.findViewById(R.id.dlg_edit_items_et_name);
		
		res = et_Name.requestFocus();
		
		// Log
		msg_Log = "focus => " + res;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// show

		////////////////////////////////
		d2.show();
		
	}//dlg_Register_item

	public static void 
	conf_Save_PurHistory
	(Activity actv, Dialog d1) {
		// TODO Auto-generated method stub
		Dialog d2 = Methods_dlg.dlg_template_okCancel_2Dialogues(
				actv,
				R.layout.dlg_save_tobuy_list, 
				R.string.menu_listitem_tabToBuy_admin_db_save_tobuy_list,
				
				R.id.dlg_save_tobuy_list_bt_ok,
				R.id.dlg_save_tobuy_list_bt_cancel,
				
//				Tags.DialogTags.dlg_save_tobuy_list_bt_ok,
				Tags.DialogTags.DLG_SAVE_TOBUY_LIST_BT_OK,
				Tags.DialogTags.DLG_GENERIC_DISMISS_SECOND_DIALOG,
				
				d1);
		
		/***************************************
		 * Spinner
		 ***************************************/
		Spinner spStoreNames = (Spinner) d2.findViewById(R.id.dlg_save_tobuy_list_sp_store_name);
		
		ArrayAdapter<String> adapter = _dlg_saveToBuyList__Adapter(actv);
		
		////////////////////////////////

		// Set adapter to spinner

		////////////////////////////////
		spStoreNames.setAdapter(adapter);
		
		////////////////////////////////

		// Set spinner default value
		// 1. Get the first item from CONS.TabActv.toBuyList
		// 2. Get the store name from the item
		// 3. Use this store name as the default

		////////////////////////////////
		SI item = CONS.TabActv.toBuyList.get(0);
		
		if (item != null) {
			
			String defaultStoreName = item.getStore();

			if (defaultStoreName != null) {

				int position = adapter.getPosition(defaultStoreName);
				
				spStoreNames.setSelection(position);

			}//if (defaultStoreName != null)
			
		}//if (item != null)
		
		////////////////////////////////

		// Amount(Sum of items in price)

		////////////////////////////////
		int amount = 0;
		
		for (SI si : CONS.TabActv.toBuyList) {
			
			amount += si.getPrice() * si.getNum();
//			amount += si.getPrice();
			
		}//for (ShoppingItem i : CONS.TabActv.toBuyList)
		
		EditText etAmount = (EditText) d2.findViewById(R.id.dlg_save_tobuy_list_et_amount);
		
		etAmount.setText(String.valueOf(amount));
		
//		/***************************************
//		 * Show dialog
//		 ***************************************/
//		d2.show();

	}//conf_Save_PurHistory

}//public class Methods_dlg
