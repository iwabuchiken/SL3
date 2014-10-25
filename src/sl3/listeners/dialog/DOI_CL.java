package sl3.listeners.dialog;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import sl.main.MainActv;
import sl3.items.ListItem;
import sl3.items.SI;
import sl3.main.R;
import sl3.tasks.Task_GetYomi;
import sl3.tasks.Task_PostData;
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
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DOI_CL implements OnItemClickListener {

	//
	Activity actv;
	
	Dialog d1;
	Dialog dlg1;
	Dialog d2;
	
	SI si;
	//
	Vibrator vib;
	
	//
	Tags.DialogTags dlgTag = null;

	private Dialog d3;
	
	public DOI_CL(Activity actv, Dialog d1) {
		// 
		this.actv = actv;
		this.d1 = d1;
		
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

	public DOI_CL(Activity actv, Dialog dlg, Tags.DialogTags dlgTag) {
		// 
		this.actv = actv;
		this.d1 = dlg;
		this.dlgTag = dlgTag;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

	public DOI_CL(Activity actv,
							Dialog dlg, SI si) {
		this.actv = actv;
		this.d1 = dlg;
		this.si = si;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);

	}

	public DOI_CL
	(Activity actv, Dialog dlg1, DialogTags dlgTag, SI si) {
		
		this.actv = actv;
		this.dlg1 = dlg1;
		this.si = si;
		this.dlgTag = dlgTag;
		
		//
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
		
	}//public DialogOnItemClickListener

	public DOI_CL
	(Activity actv, Dialog d1, Dialog d2) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		this.d1 = d1;
		this.d2 = d2;
		
		//
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}

	public DOI_CL
	(Activity actv, Dialog d1, Dialog d2, Dialog d3) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		this.d1 = d1;
		this.d2 = d2;
		this.d3 = d3;
		
		//
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	//	@Override
	public void 
	onItemClick
	(AdapterView<?> parent, View v, int position, long id) {
		/*----------------------------
		 * Steps
		 * 0. Vibrate
		 * 1. Get table name
		 * 2. Call method
			----------------------------*/
		/*----------------------------
		 * 0. Vibrate
			----------------------------*/
		vib.vibrate(40);
		
		/*********************************
		 * Called from: Methods_dlg.dlg_db_activity()
		 *********************************/
		Tags.DialogTags tag = (Tags.DialogTags) parent.getTag();
		
		ListItem li;
		String choice;
		
		// Log
		Log.d("["
				+ "DOI_CL.java : "
				+ +Thread.currentThread().getStackTrace()[2]
						.getLineNumber() + " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "tag.name()=" + tag.name());
		
		switch (tag) {
		
		case dlg_db_admin_lv:
			
			choice = (String) parent.getItemAtPosition(position);
			
			dlg_db_admin_lv(choice);
			
			break;

		case dlg_tabactv_tab2_lv:
			
			choice = (String) parent.getItemAtPosition(position);

			dlg_tabactv_tab2_lv(choice);
			
//				// Log
//				Log.d("DOI_CL.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]", "choice=" + choice);
//				
			break;// case dlg_tabactv_tab2_lv

		case dlg_tabActv_adminDb:
			
			choice = (String) parent.getItemAtPosition(position);
			
			case_dlg_tabActv_adminDb(choice);
			
			break;
			
		case dlg_clear_selections://----------------------------
			
			choice = (String) parent.getItemAtPosition(position);
			
			case_dlg_clear_selections(choice);
			
			break;// case dlg_clear_selections
			
		case dlg_sort_list_lv://----------------------------
			
			choice = (String) parent.getItemAtPosition(position);
			
			case_dlg_sort_list_lv(choice);
			
			break;// case dlg_sort_list_lv
			
		case dlg_item_list_long_click://----------------------------
			
			choice = (String) parent.getItemAtPosition(position);
			
			case_dlg_item_list_long_click(choice);
			
			break;// case dlg_sort_list_lv
			
		case ACTV_TAB_OPT_ADMIN://----------------------------
			
			li= (ListItem) parent.getItemAtPosition(position);
			
			case_ACTV_TAB_OPT_ADMIN(li);
			
			break;// case dlg_sort_list_lv
			
		case ACTV_TAB_OPT_OPERATIONS://----------------------------
			
			li= (ListItem) parent.getItemAtPosition(position);
			
			case_ACTV_TAB_OPT_OPERATIONS(li);
			
			break;// case dlg_sort_list_lv
			
		case ACTV_TAB_OPT_TABLES://----------------------------
			
			li= (ListItem) parent.getItemAtPosition(position);
			
			case_ACTV_TAB_OPT_TABLES(li);
			
			break;// case dlg_sort_list_lv
			
		case ACTV_TAB_OPT_DB://----------------------------
			
			li= (ListItem) parent.getItemAtPosition(position);
			
			case_ACTV_TAB_OPT_DB(li);
			
			break;// case dlg_sort_list_lv

		case TAB1_LONG_CLICK://----------------------------
			
			li = (ListItem) parent.getItemAtPosition(position);
			
			case_TAB1_LONG_CLICK(li);
			
		default:
			break;
		}//switch (tag)
			
	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)
	
	private void 
	case_TAB1_LONG_CLICK
	(ListItem li) {
//		(AdapterView<?> parent, int position) {
		// TODO Auto-generated method stub
//		String choice = (String) parent.getItemAtPosition(position);

		if (li.getText().equals(actv.getString(
						R.string.dlg_item_list_long_click_edit))) {
			
			Methods_dlg.dlg_Tab1_Edit_Item(actv, si, d1);
			
		} else if (li.getText().equals(actv.getString(
						R.string.dlg_item_list_long_click_delete))) {//if (choice.equals(actv.getString(R.string.dlg_item_list_long_click_edit)))
			
			Methods_dlg.conf_Delete_SI(actv, d1, si);
			
//			Methods_dlg.dlg_tab1_delete_item(actv, si, dlg);
			
		} else {//if (choice.equals(actv.getString(R.string.dlg_item_list_long_click_edit)))
			
		}//if (choice.equals(actv.getString(R.string.dlg_item_list_long_click_edit)))
		
	}//private void case_tab1_long_click(AdapterView<?> parent, int position) {

	private void 
	case_ACTV_TAB_OPT_DB
	(ListItem li) {
		// TODO Auto-generated method stub

		////////////////////////////////

		// dispatch

		////////////////////////////////
		if (li.getText().equals(actv.getString(
				R.string.menu_listitem_tabToBuy_admin_db_save_tobuy_list))) {

			////////////////////////////////

			// validate

			////////////////////////////////
			if (CONS.TabActv.tab_toBuyItemIds == null) {
				
				String message = "to-buy list => null";
				Methods_dlg.dlg_ShowMessage_SecondDialog(
									actv, d1, message, R.color.red);
				
				return;
				
			} else if (CONS.TabActv.tab_toBuyItemIds.size() < 1) {
				
				String message = "to-buy list => no item chosen";
				Methods_dlg.dlg_ShowMessage_SecondDialog(
									actv, d1, message, R.color.gold2);
				
				return;
				
			}
			
			////////////////////////////////

			// new dialog

			////////////////////////////////
			Methods_dlg.dlg_SaveToBuyList(actv, d1);
					
		} else if (li.getText().equals(actv.getString(
				R.string.menu_listitem_tabToBuy_admin_db_load_tobuy_list))) {

			Methods_dlg.dlg_LoadToBuyList(actv, d1);
			
		} else if (li.getText().equals(actv.getString(
				R.string.menu_listitem_tabToBuy_admin_db_delete_tobuy_list))) {
			
			Methods_dlg.dlg_DeleteToBuyList(actv, d1);
			
		} else if (li.getText().equals(actv.getString(
				R.string.menu_listitem_tabToBuy_admin_db_post_tobuy_list))) {
			
			Methods_dlg.conf_Post_ToBuy_List(actv, d1);
			
		} else if (li.getText().equals(actv.getString(
				R.string.menu_listitem_tabToBuy_admin_db_save_hist))) {

			Methods_dlg.conf_Save_PurHistory(actv, d1);
			
		} else {
			
			// Log
			String msg_Log = "unknown item => " + li.getText();
			Log.d("DOI_CL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
		}
		
	}//case_ACTV_TAB_OPT_DB

	private void 
	case_ACTV_TAB_OPT_TABLES
	(ListItem li) {
		// TODO Auto-generated method stub

		////////////////////////////////

		// stores

		////////////////////////////////
		if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Tables__Stores_Create))) {

			Methods.opt_ActvTab_CreateTables(
							actv, d1, d2, d3,
							CONS.DB.tname_stores);
					
		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Tables__Stores_Drop))) {

			Methods_dlg.conf_DropTable(
					actv, d1, d2, d3,
					CONS.DB.tname_stores, 
					Tags.DialogTags.ACTV_TAB_OPT_DROP_TABLE_STORES);
			
		////////////////////////////////

		// genres

		////////////////////////////////
		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Tables__Genres_Create))) {

			Methods.opt_ActvTab_CreateTables(
					actv, d1, d2, d3,
					CONS.DB.tname_genres);

		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Tables__Genres_Drop))) {
			
			Methods_dlg.conf_DropTable(
					actv, d1, d2, d3,
					CONS.DB.tname_genres, 
					Tags.DialogTags.ACTV_TAB_OPT_DROP_TABLE_GENRES);
			
		////////////////////////////////

		// shopping_item

		////////////////////////////////
		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Tables__SI_Create))) {

			Methods.opt_ActvTab_CreateTables(
							actv, d1, d2, d3,
							CONS.DB.tname_si);
			
		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Tables__SI_Drop))) {
			
			Methods_dlg.conf_DropTable(
							actv, d1, d2, d3,
							CONS.DB.tname_si, 
							Tags.DialogTags.ACTV_TAB_OPT_DROP_TABLE_SI);
			
		////////////////////////////////
		
		// ps
		
		////////////////////////////////
		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Tables__Create_PS))) {
			
			Methods.opt_ActvTab_CreateTables(
					actv, d1, d2, d3,
					CONS.DB.tname_PS);
			
		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Tables__Drop_PS))) {
			
			Methods_dlg.conf_DropTable(
					actv, d1, d2, d3,
					CONS.DB.tname_PS, 
					Tags.DialogTags.ACTV_TAB_OPT_DROP_TABLE_PS);
			
		////////////////////////////////
					
		// ph
		
		////////////////////////////////
		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Tables__PH_Create))) {
			
			Methods.opt_ActvTab_CreateTables(
					actv, d1, d2, d3,
					CONS.DB.tname_ph);
			
		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Tables__PH_Drop))) {
			
			Methods_dlg.conf_DropTable(
					actv, d1, d2, d3,
					CONS.DB.tname_ph, 
					Tags.DialogTags.ACTV_TAB_OPT_DROP_TABLE_PH);
			
			////////////////////////////////
			
			// admin
			
			////////////////////////////////
		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Tables__Create_Admin))) {
			
			Methods.opt_ActvTab_CreateTables(
					actv, d1, d2, d3,
					CONS.DB.tname_admin);
			
		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Tables__Drop_Admin))) {
			
			Methods_dlg.conf_DropTable(
					actv, d1, d2, d3,
					CONS.DB.tname_admin, 
					Tags.DialogTags.ACTV_TAB_OPT_DROP_TABLE_ADMIN);
			
		} else {//if (choice.equals(actv.getString(
			
			String msg = "Unknown option => " + li.getText();
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
			
		}//if (choice.equals(actv.getString(

		
	}//case_ACTV_TAB_OPT_TABLES

	private void 
	case_ACTV_TAB_OPT_OPERATIONS
	(ListItem li) {
		// TODO Auto-generated method stub
		
		if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Ops__RestoreDB))) {

			// Log
			String msg_Log = "opt_TabActv_Admin_Ops__RestoreDB";
			Log.d("DOI_CL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			Methods_dlg.conf_Restore_DB(
					actv, 
					d1, d2,
					Tags.DialogTags.ACTV_TAB_OPT_RESTORE_DB);
			
		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Ops__Tables))) {
			
			Methods_dlg.dlg_Op_Tables(actv, d1, d2);

		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Ops__GetYomi))) {
			
			String msg = "Sorry. Under construction";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);

		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Ops__PostData))) {
			
			String msg = "Sorry. Under construction";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
			
		////////////////////////////////

		// import data

		////////////////////////////////
		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_ImportData_SI))) {

			Methods_dlg.conf_ImportData_SI(
							actv, 
							d1, d2,
							Tags.DialogTags.ACTV_TAB_OPT_IMP_DATA_SI);
			
		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_ImportData_Stores))) {
			
			Methods_dlg.conf_ImportData_SI(
					actv, 
					d1, d2,
					Tags.DialogTags.ACTV_TAB_OPT_IMP_DATA_Stores);
			
		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_ImportData_Genres))) {
			
			Methods_dlg.conf_ImportData_SI(
					actv, 
					d1, d2,
					Tags.DialogTags.ACTV_TAB_OPT_IMP_DATA_Genres);
			
		////////////////////////////////
		
		// insert: num
		
		////////////////////////////////
		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Insert_Num))) {
			
			Methods_dlg.conf_InsertNum_SI(
					actv, 
					d1, d2,
					Tags.DialogTags.ACTV_TAB_OPT_INSERT_NUM_SI);
			
		////////////////////////////////

		// else

		////////////////////////////////
		} else {//if (choice.equals(actv.getString(
			
			String msg = "Unknown option => " + li.getText();
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
			
		}//if (choice.equals(actv.getString(
		
	}//case_ACTV_TAB_OPT_OPERATIONS

	private void 
	case_ACTV_TAB_OPT_ADMIN
	(ListItem li) {
		// TODO Auto-generated method stub
		
		if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Main__BackupDB))) {

			_case_ACTV_TAB_OPT_ADMIN__BackupDB(actv);
//			Methods.backup_DB(actv);
			
//			String msg = "Sorry. Under construction";
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
			
			
		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Main__Operations))) {
			
			Methods_dlg.dlg_OptMenu_TabActv_Opearations(actv, d1);

		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Main__SeeLog))) {
			
			Methods.start_Activity_LogActv(actv, d1);
			
//			String msg = "Sorry. Under construction";
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);

		} else if (li.getText().equals(actv.getString(
				R.string.opt_TabActv_Admin_Main__Register_Item))) {

			Methods_dlg.dlg_Register_item(actv, d1);
			
		}//if (choice.equals(actv.getString(
		
		
		
		
	}//case_ACTV_TAB_OPT_ADMIN

	private void 
	_case_ACTV_TAB_OPT_ADMIN__BackupDB
	(Activity actv) {
		// TODO Auto-generated method stub
		
		int res = Methods.backup_DB(actv);
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		switch(res) {

//		-1 Create folder => not successful
//		-2 Create folder => Exception
//		-3 FileNotFoundException
//		-4 IOException
//		1 backup => done
		
		case -4: 
			
			msg = "IOException";
			colorID = R.color.red;
			
			break;
			
		case -3: 
			
			msg = "FileNotFoundException";
			colorID = R.color.red;
			
			break;
			
		case -1: 
			
			msg = "Create folder => not successful";
			colorID = R.color.gold2;
			
			break;
		
		case -2: 
			
			msg = "Create folder => Exception";
			colorID = R.color.red;
			
			break;
			
		case 1: 
			
			msg = "Backup => done";
			colorID = R.color.green4;
			
			d1.dismiss();
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage(
				actv, 
				msg,
				colorID);

		
	}//_case_ACTV_TAB_OPT_ADMIN__BackupDB

	private void
	case_dlg_item_list_long_click(String choice) {
		
		// Log
		Log.d("[" + "DOI_CL.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "choice=" + choice);
		
		if (choice.equals(actv.getString(
				R.string.dlg_item_list_long_click_edit))) {
			
			Methods_dlg.dlg_Tab1_Edit_Item(actv, si, d1);
			
			
		} else if (choice.equals(actv.getString(
				R.string.dlg_item_list_long_click_delete))) {


		}//if (choice.equals(actv.getString(
		
	}//case_dlg_item_list_long_click(String choice)・・

	private void case_dlg_sort_list_lv(String choice) {
		
		if (choice.equals(actv.getString(
				R.string.dlg_sort_list_item_name))) {
			
			case_dlg_sort_list_ItemName();
			
		} else if (choice.equals(actv.getString(
				R.string.dlg_sort_list_genre_item_name))) {

			case_dlg_sort_list_GenreItemName();

		} else if (choice.equals(actv.getString(
				R.string.dlg_sort_list_store))) {

			// debug
			Toast.makeText(actv, "Store", Toast.LENGTH_LONG).show();

		}
			
	}//private void case_dlg_sort_list_lv(String choice)

	private void case_dlg_sort_list_ItemName() {
		String currrentTabTag = CONS.TabActv.tabHost.getCurrentTabTag();
		
		if (currrentTabTag.equals(actv.getString(
				R.string.tabactv_tabtags_first))) {

			Methods_sl.sortItemList(CONS.TabActv.itemList);
			
			CONS.TabActv.adpItems.notifyDataSetChanged();
			
			d1.dismiss();
		
		} else if (currrentTabTag.equals(actv.getString(
						R.string.tabactv_tabtags_second))) {
		
			Methods_sl.sortItemList(CONS.TabActv.toBuyList);
			
			CONS.TabActv.adpToBuys.notifyDataSetChanged();
			
			d1.dismiss();
		
		}//if (currrentTabTag.equals(actv.getString(

		Methods_sl.sortItemList(CONS.TabActv.toBuyList);
		
		CONS.TabActv.adpToBuys.notifyDataSetChanged();
		
		d1.dismiss();
		
	}

	private void case_dlg_sort_list_GenreItemName() {
		
		String currrentTabTag = CONS.TabActv.tabHost.getCurrentTabTag();
		
		// Log
		Log.d("DOI_CL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "currrentTabTag=" + currrentTabTag);
		
		if (currrentTabTag.equals(actv.getString(
								R.string.tabactv_tabtags_first))) {
			
			Methods_sl.sortItemList_GenreItemName(CONS.TabActv.itemList);
			
			CONS.TabActv.adpItems.notifyDataSetChanged();
		
			d1.dismiss();
			
		} else if (currrentTabTag.equals(actv.getString(
								R.string.tabactv_tabtags_second))) {

			Methods_sl.sortItemList_GenreItemName(CONS.TabActv.toBuyList);
			
			CONS.TabActv.adpToBuys.notifyDataSetChanged();
		
			d1.dismiss();
			
		}//if (currrentTabTag.equals(actv.getString(
		
	}//private void case_dlg_sort_list_GenreItemName()

	private void case_dlg_sort_list_Genre() {

		// debug
		Toast.makeText(actv, "Genre", Toast.LENGTH_LONG).show();
		
	}//private void case_dlg_sort_list_Genre()

	private void case_dlg_clear_selections(String choice) {
		// TODO Auto-generated method stub
		if (choice.equals(actv.getString(
					R.string.menu_listitem_tabToBuy_clear_basket))) {
			
			CONS.TabActv.toBuyList.clear();
			CONS.TabActv.tab_toBuyItemIds.clear();
			
			CONS.TabActv.adpItems.notifyDataSetChanged();
			CONS.TabActv.adpToBuys.notifyDataSetChanged();
			
			/***************************************
			 * Close dialog
			 ***************************************/
			d1.dismiss();
			
			/***************************************
			 * Clear sum
			 ***************************************/
			TextView tvSum = (TextView) actv.findViewById(R.id.itemlist_tab2_tv_sum);
			tvSum.setText(actv.getString(R.string.itemlist_tabs_tobuy_sum));
			
			/***************************************
			 * Clear due date (and store name if clearing after	
			 * 	loading a list)
			 ***************************************/
			TextView tvDueDate = (TextView) actv.findViewById(R.id.itemlist_tab2_tv_due_date);
			tvDueDate.setText(Methods.getTimeLabel_Japanese(Methods.getMillSeconds_now()));
			
		} else if (choice.equals(actv.getString(
				R.string.menu_listitem_tabToBuy_clear_checked_items))) {//if (choice.equals(actv.getString(
			
			CONS.TabActv.tab_checkedItemIds.clear();
			
			CONS.TabActv.adpItems.notifyDataSetChanged();

			/***************************************
			 * Close dialog
			 ***************************************/
			d1.dismiss();

		} else if (choice.equals(actv.getString(
				R.string.generic_label_all))) {//if (choice.equals(actv.getString(
			
			CONS.TabActv.toBuyList.clear();
			CONS.TabActv.tab_toBuyItemIds.clear();
			CONS.TabActv.tab_checkedItemIds.clear();
			
			CONS.TabActv.adpItems.notifyDataSetChanged();
			CONS.TabActv.adpToBuys.notifyDataSetChanged();
			
			d1.dismiss();

			/***************************************
			 * Clear sum
			 ***************************************/
			TextView tvSum = (TextView) actv.findViewById(R.id.itemlist_tab2_tv_sum);
			tvSum.setText(actv.getString(R.string.itemlist_tabs_tobuy_sum));

			/***************************************
			 * Clear due date (and store name if clearing after	
			 * 	loading a list)
			 ***************************************/
			TextView tvDueDate = (TextView) actv.findViewById(R.id.itemlist_tab2_tv_due_date);
			tvDueDate.setText(Methods.getTimeLabel_Japanese(Methods.getMillSeconds_now()));

		}//if (choice.equals(actv.getString(
		
	}//private void case_dlg_clear_selections(String choice)

	private void case_dlg_tabActv_adminDb(String choice) {
		// TODO Auto-generated method stub
		/***************************************
		 * Save list data
		 ***************************************/
		if (choice.equals(
					actv.getString(R.string.menu_listitem_tabToBuy_admin_db_save_tobuy_list))) {
			
			if (CONS.TabActv.toBuyList.size() < 1) {
				
				// debug
				Toast.makeText(actv, "Seems basket is empty", Toast.LENGTH_LONG).show();
				
				return;
				
			}//if (CONS.TabActv.toBuyList.size() == condition)
			
			Methods_dlg.dlg_SaveToBuyList(actv, d1);
			
		} else if (choice.equals(
					actv.getString(R.string.menu_listitem_tabToBuy_admin_db_load_tobuy_list))) {
			
			Methods_dlg.dlg_LoadToBuyList(actv, d1);
		
		} else if (choice.equals(
				actv.getString(R.string.menu_listitem_tabToBuy_admin_db_delete_tobuy_list))) {
	
			Methods_dlg.dlg_DeleteToBuyList(actv, d1);
			
		} else if (choice.equals(actv.getString(	// Upload bought items
				R.string.menu_listitem_tabToBuy_admin_db_post_tobuy_list))) {
			
			Methods_dlg.dlg_PostToBuyList(actv, d1);
			
		}//if (choice.equals(actv.getString(R.string.menu_listitem_tabToBuy_admin_db_save_tobuy_list)))
		
	}//private void case_dlg_tabActv_adminDb(String choice)

	private void dlg_tabactv_tab2_lv(String choice) {
		// TODO Auto-generated method stub
		// Log
		Log.d("[" + "DOI_CL.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "choice => " + choice);
		
		if (choice.equals(actv.getString(R.string.tabactv_tab2_lv_delete_from_list))) {
		
			/***************************************
			 * 1. Remove the si from toBuyList
			 * 2. Notify the adapter: CONS.TabActv.adpToBuys, CONS.TabActv.adpItems
			 ***************************************/
			CONS.TabActv.toBuyList.remove(si);
			
//			CONS.TabActv.tab_toBuyItemIds.remove(si.getId());
			CONS.TabActv.tab_toBuyItemIds.remove(Integer.valueOf(si.getId()));
			
			CONS.TabActv.adpToBuys.notifyDataSetChanged();
			
			CONS.TabActv.adpItems.notifyDataSetChanged();
			
			/***************************************
			 * Close dlg
			 ***************************************/
			d1.dismiss();
			
		} else {//if (choice.equals(actv.getString(R.string.tabactv_tab2_lv_delete_from_list)))
			
		}//if (choice.equals(actv.getString(R.string.tabactv_tab2_lv_delete_from_list)))
		
	}

	private void dlg_db_admin_lv(String choice) {
		// TODO Auto-generated method stub
		if (choice.equals(actv.getString(
						R.string.dlg_db_admin_item_backup_db))) {
			
			dlg_db_admin_lv_backupDb();
			
			return;
			
		} else if (choice.equals(actv.getString(
				R.string.dlg_db_admin_item_refatcor_db))) {

			dlg_db_admin_lv_refactorDb();
			
			return;
			
		} else if (choice.equals(actv.getString(
				R.string.dlg_db_admin_item_restore_db))) {
			
			dlg_db_admin_lv_RestoreDb();
		
		} else if (choice.equals(actv.getString(
					R.string.dlg_db_admin_item_get_yomi))) {
				
				dlg_db_admin_lv_GetYomi();
				
		} else if (choice.equals(actv.getString(
				R.string.dlg_db_admin_item_post_data))) {
			
			dlg_db_admin_lv_PostData();
			
		}//if
		
	}//private void dlg_db_admin_lv(String choice)

	private void dlg_db_admin_lv_PostData() {
		// TODO Auto-generated method stub
		Task_PostData task = new Task_PostData(actv, d1);
		
		task.execute(CONS.HTTPData.registerChoice.pur_history.toString());
		
		d1.dismiss();
		
	}//private void dlg_db_admin_lv_PostData()

	private void dlg_db_admin_lv_GetYomi() {
		// TODO Auto-generated method stub
//		int res = Methods_sl.getYomi(actv, dlg);
		
		Task_GetYomi task = new Task_GetYomi(actv);
		
		task.execute("Start");
		
	}//private void dlg_db_admin_lv_GetYomi()

	private void dlg_db_admin_lv_RestoreDb() {
		
		String src_dir = CONS.DB.dirPath_dbFile_Backup_SL_1;
		
		File f_dir = new File(src_dir);
		
		File[] src_dir_files = f_dir.listFiles();
		
		// If no files in the src dir, quit the method
		if (src_dir_files.length < 1) {
			
			// Log
			Log.d("DOI_CL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "No files in the dir: " + src_dir);
			
			return;
			
		}//if (src_dir_files.length == condition)

		File f_src_latest = src_dir_files[0];
		
		
		for (File file : src_dir_files) {
			
			if (f_src_latest.lastModified() < file.lastModified()) {
						
				f_src_latest = file;
				
			}//if (variable == condition)
			
		}//for (File file : src_dir_files)
		
		// Show the path of the latest file
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "f_src_latest=" + f_src_latest.getAbsolutePath());
		
		/*********************************
		 * Restore file
		 *********************************/
		String src = f_src_latest.getAbsolutePath();

		
//		String src = StringUtils.join(
//						new String[]{
//								CONS.DB.dirPath_db_backup,
//								"shoppinglist_backup_20131218_160952.bk"},
////								"shoppinglist_backup_20130213_121226.bk"},
//						File.separator);
		
		String dst = StringUtils.join(
						new String[]{
								CONS.DB.dirPath_db,
								CONS.DB.dbName},
						File.separator);

		// Log
		Log.d("DOI_CL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"src=" + src
				+ "|"
				+ "dst=" + dst);

		boolean res = Methods.restore_db(actv, CONS.DB.dbName, src, dst);

	}//private void dlg_db_admin_lv_RestoreDb()

	private void dlg_db_admin_lv_refactorDb() {
		
//		Methods_sl.refactorDb_colPrice(actv);
//		Methods_sl.refactorDb_colGenre(actv);
		int res = Methods_sl.refactorDb_colPrice_CanDo(actv);

		/*********************************
		 * Close dialog
		 *********************************/
		if (res == CONS.RV.DATA_REFACTORED) {
		
			d1.dismiss();
			
		}//if (res == true)
		
		return;

		
//		/*********************************
//		 * Setup DB
//		 *********************************/
//		DBUtils dbu = new DBUtils(actv, CONS.DBAdmin.dbName);
//		
//		SQLiteDatabase wdb = dbu.getWritableDatabase();
//		
//		/*********************************
//		 * Query
//		 *********************************/
//		String tableName = "shopping_item";
//		
//		String sql = "SELECT * FROM " + tableName;
//
//		Cursor c = null;
//
//		try {
//			
//			c = wdb.rawQuery(sql, null);
//			
//			/*********************************
//			 * Cursor => null?
//			 *********************************/
//			if (null == c) {
//				
//				// Log
//				Log.d("DOI_CL.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]", "Cursor => null");
//				
//				wdb.close();
//				
//				return;
//				
//			}//if (null == c)
//			
//			/*********************************
//			 * Num of entries in the cursor => Less than 1?
//			 *********************************/
//			if (c.getCount() < 1) {
//				
//				// Log
//				Log.d("DOI_CL.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]", "Cursor => No entry");
//				
//				wdb.close();
//				
//				return;
//				
//			}//if (null == c)
//			
//			/*********************************
//			 * Start refactoring data
//			 *********************************/
//			// Log
//			Log.d("DOI_CL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "Refactoring starts...");
//			
//			boolean res = Methods_sl.refactorData(
//										actv, wdb, dbu, tableName, c);
//			
//			if (res == true) {
//			
//				// Log
//				Log.d("DOI_CL.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]", "Data refactored");
//				
//			} else {//if (res == true)
//
//				// Log
//				Log.d("DOI_CL.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]",
//						"Data refactoring => Failed");
//				
//			}//if (res == true)
//			
//			/*********************************
//			 * Closing operations
//			 *********************************/
//			wdb.close();
//			
//			dlg.dismiss();
//			
//			return;
//			
//		} catch (Exception e) {
//
//			// Log
//			Log.d("DOI_CL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "Exception => " + e.toString());
//		}//try
//
//		
//		wdb.close();
		

	}//private void dlg_db_admin_lv_refactorDb()

	private void dlg_db_admin_lv_backupDb() {
		// TODO Auto-generated method stub
		int res = Methods.backupDb(
				actv, CONS.DB.dbName, CONS.DB.dirPath_dbFile_Backup_SL_1);

		if (res == CONS.RV.DB_DOESNT_EXIST) {
			
			// Log
			Log.d("DOI_CL.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2]
							.getMethodName() + "]", "DB file doesn't exist: " + res);
			
		} else if (res == CONS.RV.DB_FILE_COPY_EXCEPTION) {//if (res == CONS.RV.DB_DOESNT_EXIST)
		
			// Log
			Log.d("DOI_CL.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2]
							.getMethodName() + "]",
					"Copying file => Failed: " + res);
		
		} else if (res == CONS.RV.DB_CANT_CREATE_FOLDER) {//if (res == CONS.RV.DB_DOESNT_EXIST)
		
			// Log
			Log.d("DOI_CL.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2]
							.getMethodName() + "]",
					"Can't create a backup folder: " + res);
		
		} else if (res == CONS.RV.DB_BACKUP_SUCCESSFUL) {//if (res == CONS.RV.DB_DOESNT_EXIST)
		
			// Log
			Log.d("DOI_CL.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2]
							.getMethodName() + "]",
					"Backup successful: " + res);
		
			// debug
			Toast.makeText(actv,
					"DB backup => Done",
					Toast.LENGTH_LONG).show();
			
			/*********************************
			 * If successful, dismiss the dialog
			 *********************************/
			d1.dismiss();
		
		}//if (res == CONS.RV.DB_DOESNT_EXIST)
		
	}//private void dlg_db_admin_lv_backupDb()

}//public class DialogOnItemClickListener implements OnItemClickListener
