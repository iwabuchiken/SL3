package sl3.listeners.dialog;

import java.util.Calendar;
import java.util.Locale;

import sl3.items.PS;
import sl3.items.SI;
import sl3.main.R;
import sl3.tasks.Task_FTP;
import sl3.tasks.Task_Post_Genres;
import sl3.tasks.Task_Post_History;
import sl3.tasks.Task_Post_Sis;
import sl3.tasks.Task_Post_Stores;
import sl3.utils.CONS;
import sl3.utils.DBUtils;
import sl3.utils.Methods;
import sl3.utils.Methods_dlg;
import sl3.utils.Methods_sl;
import sl3.utils.Tags;
import sl3.utils.Tags.DialogTags;
import sl.main.RegisterItemActv;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class
DB_OCL implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog d1;
	Dialog d2;		//=> Used in dlg_input_empty_btn_XXX
	Dialog d3;		//=> Methods_dlg.java: Dialog dlg_template_okCancel_3Dialogues

	PS ps;
	
	SI si;
	
	//
	Vibrator vib;
	private Dialog d4;
	
	public DB_OCL(Activity actv, Dialog dlg) {
		//
		this.actv = actv;
		this.d1 = dlg;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	public DB_OCL(Activity actv, Dialog dlg1,
			Dialog dlg2) {
		//
		this.actv = actv;
		this.d1 = dlg1;
		this.d2 = dlg2;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	public DB_OCL
	(Activity actv, Dialog dlg1, Dialog dlg2, Dialog dlg3) {
		//
		this.actv = actv;
		this.d1 = dlg1;
		this.d2 = dlg2;
		this.d3 = dlg3;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
	}

	public DB_OCL(Activity actv, Dialog dlg1,
			Dialog dlg2, Dialog dlg3, PS ps) {
		this.actv = actv;
		this.d1 = dlg1;
		this.d2 = dlg2;
		this.d3 = dlg3;
	
		this.ps = ps;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);

	}

	public DB_OCL(Activity actv, Dialog dlg1,
			SI si) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		this.d1 = dlg1;
		
		this.si = si;
		
		//
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	public DB_OCL(Activity actv, Dialog dlg1,
			Dialog dlg2, SI si) {
		
		this.actv = actv;
		this.d1 = dlg1;
		this.d2 = dlg2;
		
		this.si = si;
		
		//
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	public DB_OCL
	(Activity actv, Dialog d1, Dialog d2, Dialog d3, Dialog d4) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		this.d1 = d1;
		this.d2 = d2;
		this.d3 = d3;
		this.d4 = d4;
		
		//
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	//	@Override
	public void 
	onClick(View v) {
		//
		Tags.DialogTags tag_name = (Tags.DialogTags) v.getTag();
		
		vib.vibrate(CONS.Admin.vibLength_click);

		//
		switch (tag_name) {
		
		case ACTV_TAB_OPT_UPLOAD_DB://---------------------------------------
			
			case_ACTV_TAB_OPT_UPLOAD_DB();
			
			break;
			
		case DLG_POST_SIS_OK://---------------------------------------
			
			case_DLG_POST_SIS_OK();
			
			break;
			
		case DLG_POST_GENRES_OK://---------------------------------------
			
			case_DLG_POST_GENRES_OK();
			
			break;
			
		case DLG_POST_STORES_OK://---------------------------------------

			case_DLG_POST_STORES_OK();
			
			break;
			
		case ACTV_TAB_OPT_RESTORE_DB://-------------------------------------
			
			// Log
			String msg_Log = "case ACTV_TAB_OPT_RESTORE_DB";
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			Methods.ACTV_TAB_OPT_RESTORE_DB(actv, d1, d2, d3);
			
			break;

		case DLG_POST_ITEMS_OK://------------------------------------------
			
			case_DLG_POST_ITEMS_OK();
			
			break;
			
		case DLG_SAVE_PUR_HISTORY_OK://------------------------------------------
			
			case_DLG_SAVE_PUR_HISTORY_OK();
			
			break;
			
		case REGISTER_ITEM_OK://------------------------------------------
			
			case_REGISTER_ITEM_OK();
			
			break;
		
		case DLG_GENERIC_CANCEL://------------------------------------------
			
			d1.dismiss();
			
			break;
			
		case DLG_GENERIC_DISMISS://------------------------------------------
			
			d1.dismiss();
			
			break;

		case DLG_GENERIC_DISMISS_SECOND_DIALOG://------------------------------------------
		case GENERIC_DISMISS_SECOND_DIALOG://------------------------------------------
			
			d2.dismiss();
			
			break;

		case DLG_GENERIC_DISMISS_3RD_DIALOG://------------------------------------------
		case GENERIC_DISMISS_THIRD_DIALOG:
			
			d3.dismiss();
			
			break;// case dlg_generic_dismiss_third_dialog
			
		case GENERIC_CANCEL_SECOND_DIALOG:
			
			d2.dismiss();
			
			break;
		
		case GENERIC_DISMISS_ALL_2ND_DIALOG://------------------------------------------
			
			d2.dismiss();
			d1.dismiss();
			
			break;// case dlg_generic_dismiss_third_dialog
			
		case GENERIC_DISMISS_ALL_3RD_DIALOG:
		
			
			d3.dismiss();
			d2.dismiss();
			d1.dismiss();
			
			break;
			
		case GENERIC_DISMISS_4TH_DIALOG:
			
			d4.dismiss();
			
			break;
			
		case dlg_register_store_ok://------------------------------------------
			/*----------------------------
			 * Validate if the edit view has some input.
			 * If no input => Show another dialog
				----------------------------*/
			//
			vib.vibrate(40);
			
			//
			EditText et = (EditText) d1.findViewById(R.id.dlg_register_store_et);
			
			if (et.getText().toString().equals("")) {
				
				Methods.dlg_input_empty(actv, d1);
				
			} else {//if (et.getText().toString().equals(""))
				
				Methods.insertStoreName(actv, d1, "stores", et.getText().toString());
				
			}//if (et.getText().toString().equals(""))
			
			break;
			
		case dlg_register_store_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			//
			d1.dismiss();
			
			break;
			
		case dlg_input_empty_btn_reenter://------------------------------------------
			//
			vib.vibrate(40);
			
			//
			d2.dismiss();
			
			break;
			
		case dlg_input_empty_btn_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			//
			d2.dismiss();
			d1.dismiss();
			
			break;
			
		case dlg_reconfirm_store_name_btn_yes://------------------------------------------
			//
			vib.vibrate(40);
			
//			//
//			et object = (et) findViewById(arguement);
//			
			Methods.insertStoreName_final(actv, d1, d2, "stores");
			
			break;
			
		case dlg_reconfirm_store_name_btn_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			d2.dismiss();
			break;

		case dlg_register_genre_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			d1.dismiss();
			break;
			
		case dlg_register_genre_register://------------------------------------------
			/*----------------------------
			 * Validate if the edit view has some input.
			 * If no input => Show another dialog
				----------------------------*/
			//
			vib.vibrate(40);
			
			//
			et = (EditText) d1.findViewById(R.id.dlg_register_genre_et);
			
			if (et.getText().toString().equals("")) {
				
				Methods.dlg_input_empty(actv, d1);
				
			} else {//if (et.getText().toString().equals(""))
				
				Methods.dlg_reconfirm_genre_name(actv, d1, "genres", et.getText().toString());
				
//				// debug
//				Toast.makeText(actv, "Start => registerGenre()",
//						2000).show();
				
//				Methods.insertStoreName(actv, dlg, "stores", et.getText().toString());
				
			}//if (et.getText().toString().equals(""))
			
			break;
			
		// dlg_reconfirm_genre_name.xml
		case dlg_reconfirm_genre_name_btn_register://------------------------------------------
			//
			vib.vibrate(40);
			
			Methods.registerGenreName_final(actv, d1, d2, "genres");
			
//			// debug
//			Toast.makeText(actv, "Register", 2000).show();
			
			break;
			
		case dlg_reconfirm_genre_name_btn_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			d2.dismiss();
			
			break;
			
		case dlg_create_table_create://------------------------------------------
			//
			vib.vibrate(40);
			
			//
			Methods.dlg_createTable_isInputEmpty(actv, d1);
			
//			Methods.createTable_FromDialog(actv, dlg);
			
			break;
			
		case dlg_create_table_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			d1.dismiss();
			
			break;

		case dlg_drop_table_btn_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			d1.dismiss();
			break;
			
		case dlg_confirm_drop_table_btn_cancel://------------------------------------------
			//
			vib.vibrate(40);
			
			d2.dismiss();
			d1.dismiss();
			
			break;
			
		case dlg_confirm_drop_table_btn_ok://------------------------------------------
			//
			vib.vibrate(40);
			
			// Dismiss the first dialog
			d1.dismiss();
			
			// Call the method; Pass the second dialog
			Methods.dropTable(actv, d2);
			break;
			
		case dlg_filter_list_cancel://------------------------------------------
			
			d1.dismiss();
			
			break;

		case dlg_filter_list_ok://------------------------------------------
			
			Methods.filterList(actv, d1);
			
			break;
			
		case dlg_filter_list_ok2://------------------------------------------
			
			Methods.filterList2(actv, d1);
			
			break;

		case DLG_SAVE_TOBUY_LIST_BT_OK://------------------------------------------
			
			case_DLG_SAVE_TOBUY_LIST_BT_OK();
			
			break;// case dlg_save_tobuy_list_bt_ok
		
		case dlg_scheduleInDb_ok://------------------------------------------
			
			case_dlg_scheduleInDb_ok();
			
			break;// case dlg_scheduleInDb_ok
			
		case dlg_scheduleInDb_update://------------------------------------------
			
			case_dlg_scheduleInDb_update();
			
			break;// case dlg_scheduleInDb_update
			
		case dlg_confirm_delete_ps_item_bt_ok://------------------------------------------
			
			case_dlg_confirm_delete_ps_item_bt_ok();
			
			break;// case dlg_confirm_delete_ps_item_bt_ok
			
		case DLG_EDIT_ITEMS_BT_OK://------------------------------------------
			
			case_DLG_EDIT_ITEMS_BT_OK();
			
			break;// case dlg_edit_items_bt_ok
			
		case ACTV_TAB_OPT_DROP_TABLE_SI://------------------------------------------
		case ACTV_TAB_OPT_DROP_TABLE_STORES://------------------------------------------
		case ACTV_TAB_OPT_DROP_TABLE_GENRES://------------------------------------------
		case ACTV_TAB_OPT_DROP_TABLE_PS://------------------------------------------
		case ACTV_TAB_OPT_DROP_TABLE_PH://------------------------------------------
		case ACTV_TAB_OPT_DROP_TABLE_ADMIN://------------------------------------------
			
			case_ACTV_TAB_OPT_DROP_TABLE(tag_name);
			
			break;// case dlg_edit_items_bt_ok
			
		case ACTV_TAB_OPT_IMP_DATA_SI://------------------------------------------
		case ACTV_TAB_OPT_IMP_DATA_Stores://------------------------------------------
		case ACTV_TAB_OPT_IMP_DATA_Genres://------------------------------------------
			
			case_ACTV_TAB_OPT_IMP_DATA(tag_name);
			
			break;// case dlg_edit_items_bt_ok
			
		case ACTV_TAB_OPT_INSERT_NUM_SI://------------------------------------------
			
			case_ACTV_TAB_OPT_INSERT_NUM_SI();
			
			break;// case dlg_edit_items_bt_ok
			
		case ACTV_TAB_SEARCH_OK://------------------------------------------
			
			case_ACTV_TAB_SEARCH_OK();
			
			break;// case dlg_edit_items_bt_ok
			
		case DLG_CONFIRM_DELETE_SI_OK://------------------------------------------
			
			case_DLG_CONFIRM_DELETE_SI_OK();
			
			break;// case dlg_edit_items_bt_ok
			
		default:
			break;
		}//switch (tag_name)
	}

	private void 
	case_ACTV_TAB_OPT_UPLOAD_DB() {
		// TODO Auto-generated method stub

		////////////////////////////////

		// validate: network status

		////////////////////////////////
		boolean res = Methods.isOnline(actv);
		
		if (res == false) {
			
			String msg = "Sorry. Network is not ready";
			Methods_dlg.dlg_ShowMessage_4thDialog(
							actv, d1, d2, d3,
							msg, R.color.gold2);
			
			return;
			
		} else {
			
			// Log
			String msg_Log = "Network is ready";
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
//		////////////////////////////////
//
//		// get view: checkbox
//
//		////////////////////////////////
//		CheckBox cb = (CheckBox) dlg2.findViewById(
//							R.id.dlg_tmpl_confirm_simple_cb_delete_file);

		////////////////////////////////

		// task

		////////////////////////////////
		Task_FTP task = new Task_FTP(
							actv, d1, d2, d3,
							CONS.Remote.FtpType.DB_FILE.toString()
							);
//		cb.isChecked());
		
		task.execute(CONS.Remote.FtpType.DB_FILE.toString());

		
	}//case_ACTV_TAB_OPT_UPLOAD_DB
	

	private void 
	case_DLG_POST_STORES_OK() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// start: task

		////////////////////////////////
		Task_Post_Stores task = new Task_Post_Stores(actv);
		
		task.execute(CONS.HTTPData.registerChoice.POST_STORES.toString());

		////////////////////////////////

		// dismiss

		////////////////////////////////
		d2.dismiss();
		d1.dismiss();
		
	}//case_DLG_POST_STORES_OK
	
	private void 
	case_DLG_POST_GENRES_OK() {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// start: task
		
		////////////////////////////////
		Task_Post_Genres task = new Task_Post_Genres(actv);
		
		task.execute(CONS.HTTPData.registerChoice.POST_GENRES.toString());
		
		////////////////////////////////
		
		// dismiss
		
		////////////////////////////////
		d2.dismiss();
		d1.dismiss();
		
	}//case_DLG_POST_GENRES_OK
	
	private void 
	case_DLG_POST_SIS_OK() {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// start: task
		
		////////////////////////////////
		Task_Post_Sis task = new Task_Post_Sis(actv);
		
		task.execute(CONS.HTTPData.registerChoice.POST_SIS.toString());
		
		////////////////////////////////
		
		// dismiss
		
		////////////////////////////////
		d2.dismiss();
		d1.dismiss();
		
	}//case_DLG_POST_GENRES_OK
	

	private void 
	case_DLG_POST_ITEMS_OK() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// start: task

		////////////////////////////////
		Task_Post_History task = new Task_Post_History(actv);
		
		task.execute(CONS.HTTPData.registerChoice.pur_history.toString());

		////////////////////////////////

		// dismiss

		////////////////////////////////
		d2.dismiss();
		d1.dismiss();
		
	}//case_DLG_POST_ITEMS_OK
	

	private void 
	case_DLG_SAVE_PUR_HISTORY_OK() {
		// TODO Auto-generated method stub
		
		boolean res = Methods.save_Pur_History(actv, d1, d2);
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;

		if (res == true) {
			
			msg = "Pur history => saved";
			colorID = R.color.green4;

			////////////////////////////////

			// update list

			////////////////////////////////
			
			
			////////////////////////////////

			// dismiss

			////////////////////////////////
			d2.dismiss();
			d1.dismiss();
			
			Methods_dlg.dlg_ShowMessage(
					actv, 
					msg,
					colorID);

		} else {

			msg = "Save pur history => failed";
			colorID = R.color.red;
			
			Methods_dlg.dlg_ShowMessage_ThirdDialog(actv, msg, d1, d2);
			
		}
		
	}//case_DLG_SAVE_PUR_HISTORY_OK

	private void 
	case_REGISTER_ITEM_OK() {
		// TODO Auto-generated method stub

		/***************************************
		 * Get views
		 ***************************************/
		EditText et_Name = (EditText) d2.findViewById(R.id.dlg_edit_items_et_name);
		EditText et_Yomi = (EditText) d2.findViewById(R.id.dlg_edit_items_et_yomi);
		EditText et_Price = (EditText) d2.findViewById(R.id.dlg_edit_items_et_price);	
		
		EditText et_Num = (EditText) d2.findViewById(R.id.dlg_edit_items_et_num);
		
		Spinner sp_Store = (Spinner) d2.findViewById(R.id.dlg_edit_items_sp_store);
		Spinner sp_Genre = (Spinner) d2.findViewById(R.id.dlg_edit_items_sp_genre);

		////////////////////////////////
		
		// validate: any input
		
		////////////////////////////////
		String tmp = et_Name.getText().toString();
		
		if (tmp == null || tmp.equals("")) {
			
			String msg = "No input!";
			int colorID = R.color.red;
			
			Methods_dlg.dlg_ShowMessage_ThirdDialog(actv, msg, d1, d2, colorID);
			
			return;
			
		}
		

		////////////////////////////////

		// build: content values

		////////////////////////////////
		ContentValues cv = new ContentValues();
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store", "name", "price",			// 3,4,5
//		"genre", "yomi", "num",				// 6,7,8
//		
//		"posted_at"							// 9

		cv.put(
				CONS.DB.col_Names_SI_full[1], 
				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		cv.put(
				CONS.DB.col_Names_SI_full[2], 
				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		cv.put(
				CONS.DB.col_Names_SI_full[3], 
				sp_Store.getSelectedItem().toString());
		
		cv.put(CONS.DB.col_Names_SI_full[4], et_Name.getText().toString());
		
		cv.put(CONS.DB.col_Names_SI_full[5], et_Price.getText().toString());
		
		cv.put(CONS.DB.col_Names_SI_full[6], sp_Genre.getSelectedItem().toString());
		
		cv.put(CONS.DB.col_Names_SI_full[7], et_Yomi.getText().toString());
		
		cv.put(CONS.DB.col_Names_SI_full[8], et_Num.getText().toString());

//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store", "name", "price",			// 3,4,5
//		"genre", "yomi", "num",				// 6,7,8
//		
//		"posted_at"							// 9
		
		////////////////////////////////

		// save item

		////////////////////////////////
		boolean res = DBUtils.insert_Data(actv, CONS.DB.tname_si, cv);
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;

		if (res == true) {
			
			msg = "insert => done: " + et_Name.getText().toString();
			colorID = R.color.green4;

			////////////////////////////////

			// update list

			////////////////////////////////
			
			
			////////////////////////////////

			// dismiss

			////////////////////////////////
			d2.dismiss();
			d1.dismiss();
			
			Methods_dlg.dlg_ShowMessage(
					actv, 
					msg,
					colorID);

		} else {

			msg = "insert => failed: " + et_Name.getText().toString();
			colorID = R.color.red;
			
			Methods_dlg.dlg_ShowMessage_ThirdDialog(actv, msg, d1, d2);
			
		}
			
//		Methods_dlg.dlg_ShowMessage(
//				actv, 
//				msg,
//				colorID);
		
	}//case_REGISTER_ITEM_OK
	

	private void 
	case_DLG_CONFIRM_DELETE_SI_OK() {
		// TODO Auto-generated method stub
		
		boolean res = DBUtils.delete_Item_static(actv, CONS.DB.tname_si, si.getId());
		
		String msg = null;
		int colorID = 0;

		if (res == true) {

			msg = "items => deleted: " + si.getName();
			colorID = R.color.green4;
		
			d2.dismiss();
			d1.dismiss();
			
			Methods_dlg.dlg_ShowMessage(
					actv, 
					msg,
					colorID);
			
			////////////////////////////////

			// refresh list

			////////////////////////////////
//			// item list
			if (CONS.TabActv.itemList.contains(si)) {
				
				CONS.TabActv.itemList.remove(si);
				
				// Log
				String msg_Log = "removed from item list => " + si.getName();
				Log.d("DB_OCL.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			} else {
			
				// Log
				String msg_Log = "CONS.TabActv.itemList => not contain: " + si.getName();
				Log.d("DB_OCL.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			}
			
//			for (SI si_inTab : CONS.TabActv.itemList) {
//				
//				if (si_inTab.getId() == si.getId()) {
//					
//					CONS.TabActv.itemList.remove(si_inTab);
//					
//				}
//				
//			}

			// to-buy list
//			// item list
			if (CONS.TabActv.toBuyList.contains(si)) {
				
				CONS.TabActv.toBuyList.remove(si);
				
				// Log
				String msg_Log = "removed from to-buy list => " + si.getName();
				Log.d("DB_OCL.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			} else {
			
				// Log
				String msg_Log = "CONS.TabActv.toBuyList => not contain: " + si.getName();
				Log.d("DB_OCL.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			}

			
			// checked item ids
			if (CONS.TabActv.tab_checkedItemIds.contains(Integer.valueOf(si.getId()))) {
				
				CONS.TabActv.tab_checkedItemIds.remove(Integer.valueOf(si.getId()));
				
				// Log
				String msg_Log = "removed from item id list => " + si.getName();
				Log.d("DB_OCL.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			} else {
			
				// Log
				String msg_Log = "CONS.TabActv.tab_checkedItemIds => not contain: " 
							+ si.getName();
				Log.d("DB_OCL.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			}

//			for (Integer id : CONS.TabActv.tab_checkedItemIds) {
//				
//				if (id.intValue() == si.getId()) {
//					
//					CONS.TabActv.tab_checkedItemIds.remove(id);
//					
//				}
//				
//			}
			
			// to-buy item ids
			if (CONS.TabActv.tab_toBuyItemIds.contains(Integer.valueOf(si.getId()))) {
				
				CONS.TabActv.tab_toBuyItemIds.remove(Integer.valueOf(si.getId()));
				
				// Log
				String msg_Log = "removed from to-buy id list => " + si.getName();
				Log.d("DB_OCL.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			} else {
			
				// Log
				String msg_Log = "CONS.TabActv.tab_toBuyItemIds => not contain: " 
							+ si.getName();
				Log.d("DB_OCL.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			}

//			for (Integer id : CONS.TabActv.tab_toBuyItemIds) {
//				
//				if (id.intValue() == si.getId()) {
//					
//					CONS.TabActv.tab_toBuyItemIds.remove(id);
//					
//				}
//				
//			}
			
			// notify
			CONS.TabActv.adpItems.notifyDataSetChanged();
			
			CONS.TabActv.adpToBuys.notifyDataSetChanged();

		} else {

			msg = "Can't delete item: " + si.getName();
			colorID = R.color.red;
			
			d2.dismiss();
			
			Methods_dlg.dlg_ShowMessage(
					actv, 
					msg,
					colorID);
			
		}
			
	}//case_DLG_CONFIRM_DELETE_SI_OK

	private void 
	case_ACTV_TAB_SEARCH_OK() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// validate: any input

		////////////////////////////////
		EditText et = (EditText) d1.findViewById(R.id.dlg_search_items_et_yomi);
		
		String yomi = et.getText().toString();
		
		if (yomi == null || yomi.equals("")) {
			
			Methods_dlg.dlg_IsEmpty(actv, d1);
			
			return;
				
		}
		
		////////////////////////////////

		// get inputs

		////////////////////////////////
		// Log
		String msg_Log = "inputs are... => " + yomi;
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// spinner

		////////////////////////////////
		Spinner sp = (Spinner) d1.findViewById(R.id.dlg_search_items_sp_store);
		
		String store = (String) sp.getSelectedItem();
		
		// Log
		msg_Log = "store is => " + store;
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// call method

		////////////////////////////////
		int res = Methods.search_Items(actv, d1, store, yomi);
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;

//		-1 table doesn't exist
//		-2 Drop table => Failed
//		1 Table dropped
		
		switch(res) {

		case -1: 
			
			msg = "Can't build list";
			colorID = R.color.red;
			
			break;
		
		case 1: 
			
			msg = "items found";
			colorID = R.color.green4;
		
			d1.dismiss();
			
			break;
			
		default: 
			
			msg = "Unknown result => " + res;
			colorID = R.color.gold2;
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage(
				actv, 
				msg,
				colorID);

		
	}//case_ACTV_TAB_SEARCH_OK

	private void 
	case_ACTV_TAB_OPT_INSERT_NUM_SI() {
		// TODO Auto-generated method stub

		Methods.insert_Num_SI(actv, d1, d2, d3);
		
	}

	private void 
	case_ACTV_TAB_OPT_IMP_DATA
	(DialogTags tag_name) {
		// TODO Auto-generated method stub
		
		switch(tag_name) {
		
		case ACTV_TAB_OPT_IMP_DATA_SI:
			
			Methods.import_Data_SI(actv, d1, d2, d3);
			
			break;
		
		case ACTV_TAB_OPT_IMP_DATA_Stores:
			
			Methods.import_Data_Stores(actv, d1, d2, d3);
			
			break;
			
		case ACTV_TAB_OPT_IMP_DATA_Genres:
			
			Methods.import_Data_Genres(actv, d1, d2, d3);
			
			break;
			
		case ACTV_TAB_OPT_RESTORE_DB:
			
			// Log
			String msg_Log = "case ACTV_TAB_OPT_RESTORE_DB";
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			Methods.ACTV_TAB_OPT_RESTORE_DB(actv, d1, d2, d3);
			
			break;
			
		
		}
		
//		Methods.import_Data_SI()
		
		
	}//case_ACTV_TAB_OPT_IMP_DATA_SI

	private void 
	case_ACTV_TAB_OPT_DROP_TABLE(DialogTags tag_name) {
		// TODO Auto-generated method stub
		String tname = null;
		
		switch(tag_name) {

		case ACTV_TAB_OPT_DROP_TABLE_SI:
			
			tname = CONS.DB.tname_si;
			
			break;
			
		case ACTV_TAB_OPT_DROP_TABLE_STORES:
			
			tname = CONS.DB.tname_stores;
			
			break;
			
		case ACTV_TAB_OPT_DROP_TABLE_GENRES:
			
			tname = CONS.DB.tname_genres;
			
			break;
			
		case ACTV_TAB_OPT_DROP_TABLE_PS:
			
			tname = CONS.DB.tname_PS;
			
			break;
			
		case ACTV_TAB_OPT_DROP_TABLE_PH:
			
			tname = CONS.DB.tname_ph;
			
			break;
			
		case ACTV_TAB_OPT_DROP_TABLE_ADMIN:
			
			tname = CONS.DB.tname_admin;
			
			break;
			
		default:
			
			// Log
			String msg_Log = "Unknown tag => " + tag_name;
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
			
		}
		
		int res = Methods.dropTable(actv, tname);
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;

//		-1 table doesn't exist
//		-2 Drop table => Failed
//		1 Table dropped
		
		switch(res) {

		case -1: 
			
			msg = "table doesn't exist => " + tname;
			colorID = R.color.gold2;
			
			d4.dismiss();
			
			break;
		
		case -2: 
			
			msg = "Drop table => Failed: " + tname;
			colorID = R.color.red;
			
			d4.dismiss();
			
			break;
			
		case 1: 
			
			msg = "Table dropped => " + tname;
			colorID = R.color.green4;
			
			d4.dismiss();
			d3.dismiss();
			d2.dismiss();
			d1.dismiss();
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage(
				actv, 
				msg,
				colorID);

//		// Log
//		String msg_Log = "Drop table: SI";
//		Log.d("DB_OCL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		
	}//case_ACTV_TAB_OPT_DROP_TABLE_SI

	private void case_DLG_EDIT_ITEMS_BT_OK() {
		/***************************************
		 * Get views
		 ***************************************/
		EditText etItemName = (EditText) d2.findViewById(R.id.dlg_edit_items_et_name);
		EditText etPrice = (EditText) d2.findViewById(R.id.dlg_edit_items_et_price);	
		EditText etYomi = (EditText) d2.findViewById(R.id.dlg_edit_items_et_yomi);
		
		Spinner spStoreName = (Spinner) d2.findViewById(R.id.dlg_edit_items_sp_store);
		Spinner spGenre = (Spinner) d2.findViewById(R.id.dlg_edit_items_sp_genre);
		
		EditText etNum = (EditText) d2.findViewById(R.id.dlg_edit_items_et_num);
		
		// Log
		Log.d("[" + "DB_OCL.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "spStoreName => " + spStoreName.toString());
		
		// Log
		String msg_Log = "etYomi => " + etYomi.getText().toString();
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		/***************************************
		 * Build a new si
		 ***************************************/
		SI newSI = new SI.Builder()
					
					.setDb_id(si.getId())
					.setStore(spStoreName.getSelectedItem().toString())
					.setName(etItemName.getText().toString())
					.setYomi(etYomi.getText().toString())
					.setPrice(Integer.parseInt(etPrice.getText().toString()))
					.setGenre(spGenre.getSelectedItem().toString())
					
					.setNum(Integer.parseInt(etNum.getText().toString()))
					
					.build();
		
		
		/***************************************
		 * Database
		 ***************************************/
//		DBUtils dbu = new DBUtils(actv);
		
//		SQLiteDatabase db = dbm.getWritableDatabase();
		
//		columns => {"store", "name", "price", "genre", "yomi"};
//		boolean result = dbm.storeData(
//		boolean result = dbu.updateData_SI_all(si);
//		boolean result = dbu.updateData_SI_all(newSI);
//		int res = DBUtils.update_SI_All(actv, si);
		int res = DBUtils.update_SI_All(actv, newSI);

		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;

//		-1 => Table doesn't exist
//		-2 => insertion => failed
//		-3 => update => Exception
//		1 => update => done		
		
		switch(res) {

		case -1: 
			
			msg = "Table doesn't exist";
			colorID = R.color.red;
			
			break;
		
		case -2: 
			
			msg = "insertion => failed";
			colorID = R.color.red;
			
			break;
			
		case -3: 
			
			msg = "update => Exception";
			colorID = R.color.red;
			
			break;
			
		case 1: 
			
			msg = "update => done";
			colorID = R.color.green4;
		
			d2.dismiss();
			d1.dismiss();
			
			////////////////////////////////

			// notify

			////////////////////////////////
			CONS.TabActv.adpItems.notifyDataSetChanged();
			
			if (CONS.TabActv.adpItems_Found != null) {
				
				CONS.TabActv.adpItems_Found.notifyDataSetChanged();
				
			}
			
			break;
			
		default: 
			
			msg = "Unknown result => " + res;
			colorID = R.color.gold2;
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage(
				actv, 
				msg,
				colorID);

		
//		if (result == true) {
//			// Log
//			Log.d("DB_OCL.java"
//					+ "["
//					+ Thread.currentThread().getStackTrace()[2]
//							.getLineNumber() + "]", "Data stored");
//			// debug
//			Toast.makeText(actv, "Data updated", Toast.LENGTH_LONG)
//					.show();
//			
//			// Close dialogues
//			d1.dismiss();
//			d2.dismiss();
//
//			/***************************************
//			 * Update the item list
//			 ***************************************/
//			case_dlg_edit_items_bt_ok__updateItemList(newSI);
//			
//		} else {//if (result == true)
//			
//			// Log
//			Log.d("DB_OCL.java"
//					+ "["
//					+ Thread.currentThread().getStackTrace()[2]
//							.getLineNumber() + "]", "Data update => Failed");
//			// debug
//			Toast.makeText(actv, "Data update => Failed", Toast.LENGTH_LONG)
//					.show();
//
//		}//if (result == true)
		
//		db.close();

	}//private void case_dlg_edit_items_bt_ok()

	private void case_dlg_edit_items_bt_ok__updateItemList(SI newSI) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < CONS.TabActv.itemList.size(); i++) {
			
			SI si = CONS.TabActv.itemList.get(i);
			
			if (si.getId() == newSI.getId()) {
				
				si.setStore(newSI.getStore());
				si.setName(newSI.getName());
				si.setYomi(newSI.getYomi());
				si.setPrice(newSI.getPrice());
				si.setGenre(newSI.getGenre());
			
				CONS.TabActv.itemList.remove(i);
				CONS.TabActv.itemList.add(si);
				
				Methods_sl.sortItemList(CONS.TabActv.itemList);
				
				CONS.TabActv.adpItems.notifyDataSetChanged();

				break;
			}//if (si.getId() == newSI.getId())
			
		}//for (int i = 0; i < CONS.TabActv.itemList.size(); i++)
		
		
	}

	private void case_dlg_confirm_delete_ps_item_bt_ok() {
		// TODO Auto-generated method stub
		/***************************************
		 * Get ps id
		 ***************************************/
		long dbId = ps.getDbId();
		
		// Log
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "dbId=" + dbId);
		
		/***************************************
		 * Setup db
		 ***************************************/
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		boolean res = dbu.deleteItem(CONS.DB.tname_PS, dbId);
		
		// Log
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "res=" + res);
		
		if (res == true) {

			// debug
			Toast.makeText(actv, "Schedule => Deleted: " + dbId, Toast.LENGTH_LONG).show();

			d3.dismiss();
			d2.dismiss();
			d1.dismiss();
			
		} else {//if (res == true)

			// debug
			Toast.makeText(actv, "Delete schedule => Failed: " + dbId, Toast.LENGTH_LONG).show();

		}//if (res == true)
		
		
		
		
	}//private void case_dlg_confirm_delete_ps_item_bt_ok()

	private void case_dlg_scheduleInDb_update() {
		/***************************************
		 * Prepare data
		 * 1. Date
		 * 2. Store name
		 * 3. 
		 ***************************************/
		DatePicker dp = (DatePicker) d2.findViewById(R.id.dlg_save_tobuy_list_dp);
		
		int year = dp.getYear();
		int month = dp.getMonth();
//		int month = dp.getMonth() + 1;
		int day = dp.getDayOfMonth();
//		// Log
//		Log.d("DB_OCL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"year=" + year
//				+ "/"
//				+ "month=" + month
//				+ "/"
//				+ "day=" + day);

		Calendar cal = Calendar.getInstance();
		
		cal.set(year, month, day);
		
		long dueDate = cal.getTimeInMillis();
		
		/***************************************
		 * Store name
		 ***************************************/
		Spinner spStoreNames = (Spinner) d2.findViewById(R.id.dlg_save_tobuy_list_sp_store_name);
		
		String storeName = spStoreNames.getSelectedItem().toString();
		
		/***************************************
		 * Update data
		 ***************************************/
		this.case_dlg_scheduleInDb_update_execute(spStoreNames, storeName, dueDate);
		
		/***************************************
		 * Close dialog 3
		 ***************************************/
		d3.dismiss();
		
	}//private void case_dlg_scheduleInDb_update()

	private void
	case_dlg_scheduleInDb_update_execute
	(Spinner spStoreNames, String storeName, long dueDate) {
		// TODO Auto-generated method stub
		/***************************************
		 * Item ids string
		 ***************************************/
		StringBuilder sb = new StringBuilder();
		
		for (Integer id : CONS.TabActv.tab_toBuyItemIds) {
			
			sb.append(String.valueOf(id.intValue()));
			sb.append(" ");
			
		}//for (Integer id : CONS.TabActv.tab_toBuyItemIds)

		String itemIdsString = sb.toString();		
		
		// Log
		String msg_Log = "itemIdsString => " + itemIdsString;
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		/***************************************
		 * Update
		 ***************************************/
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		boolean res = dbu.updateData_PS_ItemIds(actv, storeName, dueDate, itemIdsString);
		
		// Log
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "res=" + res);
		
		/***************************************
		 * Confirm
		 ***************************************/
		if (res == true) {
			
			// debug
			Toast.makeText(actv, "Schedule data => Updated", Toast.LENGTH_LONG).show();
			
			/***************************************
			 * Close dialogues
			 ***************************************/
			d3.dismiss();
			d2.dismiss();
			d1.dismiss();
			
		} else {//if (res == true)
			
			// debug
			Toast.makeText(actv, "Updated schedule data => Failed", Toast.LENGTH_LONG).show();

			d3.dismiss();
			
		}//if (res == true)
		
	}//case_dlg_scheduleInDb_update_execute

	private void case_dlg_scheduleInDb_ok() {
		/***************************************
		 * Prepare data
		 * 1. Date
		 * 2. Store name
		 * 3. 
		 ***************************************/
		DatePicker dp = (DatePicker) d2.findViewById(R.id.dlg_save_tobuy_list_dp);
		
		int year = dp.getYear();
		int month = dp.getMonth();
//		int month = dp.getMonth() + 1;
		int day = dp.getDayOfMonth();
		// Log
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"year=" + year
				+ "/"
				+ "month=" + month
				+ "/"
				+ "day=" + day);

		Calendar cal = Calendar.getInstance();
		
		cal.set(year, month, day);
		
		long dueDate = cal.getTimeInMillis();
		
		String dueDate_str = Methods.conv_MillSec_to_TimeLabel(dueDate);
		
		/***************************************
		 * Store name
		 ***************************************/
		Spinner spStoreNames = (Spinner) d2.findViewById(R.id.dlg_save_tobuy_list_sp_store_name);
		
		String storeName = spStoreNames.getSelectedItem().toString();
		
		
		// Log
		String msg_Log = String.format("%s, %s", dueDate_str, storeName);
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		/***************************************
		 * Store data
		 ***************************************/
		this._case_case_DLG_SAVE_TOBUY_LIST_BT_OK_execute(
					spStoreNames, storeName, dueDate_str);
		
		/***************************************
		 * Close dialog 3
		 ***************************************/
		d3.dismiss();
		
	}//private void case_dlg_scheduleInDb_ok()

	private void case_DLG_SAVE_TOBUY_LIST_BT_OK() {
		// TODO Auto-generated method stub
		/***************************************
		 * Get data: Date
		 ***************************************/
		DatePicker dp = (DatePicker) d2.findViewById(R.id.dlg_save_tobuy_list_dp);
		
		int year = dp.getYear();
		int month = dp.getMonth();
//		int month = dp.getMonth() + 1;
		int day = dp.getDayOfMonth();
		// Log
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"year=" + year
				+ "/"
				+ "month=" + month
				+ "/"
				+ "day=" + day);

		Calendar cal = Calendar.getInstance();
		
		cal.set(year, month, day);
		
		long dueDate = cal.getTimeInMillis();
		
		String dueDate_str = Methods.conv_MillSec_to_TimeLabel(dueDate);
		
		/***************************************
		 * Get data: The rest
		 ***************************************/
		/***************************************
		 * Store name
		 ***************************************/
		Spinner spStoreNames = (Spinner) d2.findViewById(R.id.dlg_save_tobuy_list_sp_store_name);
		
		String storeName = spStoreNames.getSelectedItem().toString();
		
		// Log
		String msg_Log = String.format("%s, %s", dueDate_str, storeName);
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		/***************************************
		 * Is the schedule already in db?
		 ***************************************/
		boolean res = Methods_sl.isInDb_PS(actv, storeName, cal);
		
		// Log
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "res=" + res);
		
		/***************************************
		 * Show dialog if the schedule already in db
		 ***************************************/
		if (res == true) {
			
			Methods_dlg.dlg_scheduleInDb(actv, d1, d2);
			
		} else {//if (res == true)

			// Log
			msg_Log = "schedule => not in DB";
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			_case_case_DLG_SAVE_TOBUY_LIST_BT_OK_execute(
							spStoreNames, storeName, dueDate_str);
//			spStoreNames, storeName, dueDate);
			
		}//if (res == true)
		
	}//private void case_dlg_save_tobuy_list_bt_ok()

	private void
	_case_case_DLG_SAVE_TOBUY_LIST_BT_OK_execute
	(Spinner spStoreNames, String storeName, long dueDate) {
		// TODO Auto-generated method stub
		/***************************************
		 * Amount
		 ***************************************/
		EditText etAmount = (EditText) d2.findViewById(R.id.dlg_save_tobuy_list_et_amount);
		
		/***************************************
		 * Memo
		 ***************************************/
		EditText etMemo = (EditText) d2.findViewById(R.id.dlg_save_tobuy_list_et_memo);
		
		/***************************************
		 * Items
		 ***************************************/
//		// Log
//		Log.d("DB_OCL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "Calling => case_dlg_save_tobuy_list_bt_ok__convertToBuyList2String()");
//		
		String items = Methods.conv_IdsString_from_ToBuy_ItemIds();
//		String items = case_dlg_save_tobuy_list_bt_ok__getItemIdsString();
		
		// Log
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "items=" + items);
		
		// Log
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "dueDate="+ dueDate);
		
		/***************************************
		 * Store data
		 * 1. Get dbId: Get the number of data already stored
		 * 		=> Add 1 to the number
		 * 2. Construct a PS instance
		 * 3. Store date => DBUtils.
		 ***************************************/
		/***************************************
		 * Construct: A PS instance
		 ***************************************/
		PS ps = new PS.Builder()

						.setStoreName(spStoreNames.getSelectedItem().toString())
						.setAmount(Integer.parseInt(etAmount.getText().toString()))
						.setMemo(etMemo.getText().toString())
						.setItems(items)
						
						.setDueDate(Methods.conv_MillSec_to_TimeLabel(dueDate))
						
						.build();
//		PS ps = new PS();
//		
////		ps.setDbId(dbId);
//		ps.setStoreName(spStoreNames.getSelectedItem().toString());
//		ps.setAmount(Integer.parseInt(etAmount.getText().toString()));
//		ps.setMemo(etMemo.getText().toString());
//		ps.setItems(items);
//		ps.setDueDate(dueDate);
		
		/***************************************
		 * Store the PS instance to database
		 ***************************************/
//		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
////		
////		SQLiteDatabase wdb = dbu.getWritableDatabase();
//
//		boolean res = dbu.storeData_PS(
//								CONS.DB.dbName,
//								CONS.DB.tname_PS,
//								ps);
		
		//debug
		boolean res = true;
		
		/***************************************
		 * Validate saving
		 ***************************************/
		String msg = null;
		int colorID = 0;
		
		if (res == true) {
			
			msg = "Schedule saved";
			colorID = R.color.green4;
			
			d2.dismiss();
			d1.dismiss();
			
//			// debug
//			Toast.makeText(actv, "Schedule saved", Toast.LENGTH_LONG).show();
			
		} else {

			msg = "Saving schedule => Failed";
			colorID = R.color.red;

			d2.dismiss();
			
//			// debug
//			Toast.makeText(actv, "Saving schedule => Failed", Toast.LENGTH_LONG).show();
//			
//			return;
			
		}
		
		Methods_dlg.dlg_ShowMessage(
				actv, 
				msg,
				colorID);
		
		/***************************************
		 * Dismiss dialog
		 ***************************************/
//		d1.dismiss();
//		d2.dismiss();
		
	}//private void case_dlg_save_tobuy_list_bt_ok_execute()

	private void
	_case_case_DLG_SAVE_TOBUY_LIST_BT_OK_execute
	(Spinner spStoreNames, String storeName, String dueDate) {
		// TODO Auto-generated method stub
		/***************************************
		 * Amount
		 ***************************************/
		EditText etAmount = (EditText) d2.findViewById(R.id.dlg_save_tobuy_list_et_amount);
		
		/***************************************
		 * Memo
		 ***************************************/
		EditText etMemo = (EditText) d2.findViewById(R.id.dlg_save_tobuy_list_et_memo);
		
		/***************************************
		 * Items
		 ***************************************/
		String items = Methods.conv_IdsString_from_ToBuy_ItemIds();
//		String items = case_dlg_save_tobuy_list_bt_ok__getItemIdsString();
		
		// Log
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "items=" + items);
		
		// Log
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "dueDate="+ dueDate);
		
		/***************************************
		 * Store data
		 * 1. Get dbId: Get the number of data already stored
		 * 		=> Add 1 to the number
		 * 2. Construct a PS instance
		 * 3. Store date => DBUtils.
		 ***************************************/
		/***************************************
		 * Construct: A PS instance
		 ***************************************/
		PS ps = new PS.Builder()
		
				.setStoreName(spStoreNames.getSelectedItem().toString())
				.setAmount(Integer.parseInt(etAmount.getText().toString()))
				.setMemo(etMemo.getText().toString())
				.setItems(items)
				
				.setDueDate(dueDate)
				
				.build();
//		PS ps = new PS();
//		
////		ps.setDbId(dbId);
//		ps.setStoreName(spStoreNames.getSelectedItem().toString());
//		ps.setAmount(Integer.parseInt(etAmount.getText().toString()));
//		ps.setMemo(etMemo.getText().toString());
//		ps.setItems(items);
//		ps.setDueDate(dueDate);
		
		/***************************************
		 * Store the PS instance to database
		 ***************************************/
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
//		
//		SQLiteDatabase wdb = dbu.getWritableDatabase();

		boolean res = dbu.storeData_PS(
								CONS.DB.dbName,
								CONS.DB.tname_PS,
								ps);
		
//		//debug
//		boolean res = true;
		
		/***************************************
		 * Validate saving
		 ***************************************/
		String msg = null;
		int colorID = 0;
		
		if (res == true) {
			
			msg = "Schedule saved";
			colorID = R.color.green4;
			
			d2.dismiss();
			d1.dismiss();
			
//			// debug
//			Toast.makeText(actv, "Schedule saved", Toast.LENGTH_LONG).show();
			
		} else {
			
			msg = "Saving schedule => Failed";
			colorID = R.color.red;
			
			d2.dismiss();
			
//			// debug
//			Toast.makeText(actv, "Saving schedule => Failed", Toast.LENGTH_LONG).show();
//			
//			return;
			
		}
		
		Methods_dlg.dlg_ShowMessage(
				actv, 
				msg,
				colorID);
		
		/***************************************
		 * Dismiss dialog
		 ***************************************/
//		d1.dismiss();
//		d2.dismiss();
		
	}//private void case_dlg_save_tobuy_list_bt_ok_execute()
	
	private String case_dlg_save_tobuy_list_bt_ok__getItemIdsString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		
		for (Integer id : CONS.TabActv.tab_toBuyItemIds) {
			
			sb.append(String.valueOf(id.intValue()));
			sb.append(" ");
			
		}//for (Integer id : CONS.TabActv.tab_toBuyItemIds)

		return sb.toString().trim();
		
//		String s = sb.toString();
//
//		// Log
//		Log.d("DB_OCL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "s=" + s + "(" + s.length() + ")");
//
//		s = s.trim();
//		
//		// Log
//		Log.d("DB_OCL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "s=" + s + "(" + s.length() + ")");
//		
//		return null;
	}//private void case_dlg_save_tobuy_list_bt_ok()

}//DialogButtonOnClickListener implements OnClickListener
