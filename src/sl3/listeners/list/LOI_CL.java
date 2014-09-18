package sl3.listeners.list;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import sl.main.MainActv;
import sl3.items.PS;
import sl3.items.SI;
import sl3.main.R;
import sl3.tasks.TaskAudioTrack;
import sl3.tasks.Task_GetYomi;
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
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class LOI_CL implements OnItemClickListener {

	//
	Activity actv;

	//
	Vibrator vib;

	Dialog dlg1, dlg2;
	
//	boolean bgm;
	
	//
	Tags.DialogTags dlgTag = null;

	public LOI_CL(Activity actv) {
		//
		this.actv = actv;
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);

//		/***************************************
//		 * Get preference value: bgm
//		 ***************************************/
//		SharedPreferences prefs = actv
//				.getSharedPreferences(
//					actv.getString(R.string.shared_preferences_name),
//					Context.MODE_PRIVATE);
//
////		boolean bgm = prefs.getBoolean(actv.getString(R.string.prefs_key_bgm), false);
//		CONS.bgm = prefs.getBoolean(actv.getString(R.string.prefs_key_bgm), false);
//		
//		// Log
//		Log.d("MainActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "bgm=" + CONS.bgm);

	}//public ListOnItemClickListener(Activity actv)

	
	public LOI_CL(Activity actv, Dialog dlg1, Dialog dlg2) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
		this.dlg1 = dlg1;
		
		this.dlg2 = dlg2;
		
	}


	//	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

//		vib.vibrate(40);
		
		/*********************************
		 * Called from: Methods_dlg.dlg_db_activity()
		 *********************************/
//		Tags.DialogTags tag = (Tags.DialogTags) parent.getTag();
		Tags.ListTags tag = (Tags.ListTags) parent.getTag();
		
		// Log
		Log.d("ListOnItemClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "tag.name()=" + tag.name());
		
		
		switch (tag) {
		case tab_itemList:

			tab_itemList(parent, position);
			
			break;

		case tab_toBuyList:

			tab_toBuyList(parent, position);
			
			break;// case tab_toBuyList

		case LOAD_TOBUY_LIST:

			case_LOAD_TOBUY_LIST(parent, position);
			
			break;// case load_toBuyList

		case delete_toBuyList:

			case_delete_toBuyList(parent, position);
			
			break;// case load_toBuyList

		default:
			break;
		
		}//switch (item)

	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)


	/***************************************
	 * 20130313_131103
	 * Using... dlg1
	 ***************************************/
	private void case_delete_toBuyList
	(AdapterView<?> parent, int position) {
		// TODO Auto-generated method stub
		/***************************************
		 * Steps
		 * 
		 * Get item
		 * Get the store name and the due date
		 * Show a new dialog for confirmation
		 * 
		 ***************************************/
		PS ps = (PS) parent.getItemAtPosition(position);
		
		String itemIdString = ps.getItems();
		
		List<SI> loadedSIList = Methods_sl.getSIListFromItemList(actv, itemIdString);
		
//		// Log
//		Log.d("ListOnItemClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "loadedSIList.size()=" + loadedSIList.size());
		
		Methods_sl.sortItemList(loadedSIList);
		
		/***************************************
		 * Dialog
		 ***************************************/
//		Dialog dlg3 = Methods_dlg.dlg_template_okCancel_2Dialogues(
//							actv, R.layout.dlg_confirm_delete_ps_item,
//							R.string.menu_listitem_tabToBuy_admin_db_delete_tobuy_list,
//							
//							R.id.dlg_confirm_delete_ps_item_bt_ok,
//							R.id.dlg_confirm_delete_ps_item_bt_cancel,
//							
//							Tags.DialogTags.dlg_confirm_delete_ps_item_bt_ok,
//							Tags.DialogTags.dlg_generic_dismiss_second_dialog,
//							
//							dlg1);

//		Dialog dlg3 = Methods_dlg.dlg_template_okCancel_3Dialogues(
		Dialog dlg3 = Methods_dlg.dlg_template_okCancel_3Dialogues_withPS(
				actv, R.layout.dlg_confirm_delete_ps_item,
				R.string.menu_listitem_tabToBuy_admin_db_delete_tobuy_list,
				
				R.id.dlg_confirm_delete_ps_item_bt_ok,
				R.id.dlg_confirm_delete_ps_item_bt_cancel,
				
				Tags.DialogTags.dlg_confirm_delete_ps_item_bt_ok,
//				Tags.DialogTags.dlg_generic_dismiss_second_dialog,
				Tags.DialogTags.DLG_GENERIC_DISMISS_3RD_DIALOG,
				
				dlg1, dlg2, ps);

		/***************************************
		 * Store name
		 ***************************************/
		TextView tvStoreName =
				(TextView) dlg3.findViewById(R.id.dlg_confirm_delete_ps_item_tv_val_store_name);
		
		tvStoreName.setText(ps.getStoreName());
		
		/***************************************
		 * Due date
		 ***************************************/
		TextView tvDueDate =
				(TextView) dlg3.findViewById(R.id.dlg_confirm_delete_ps_item_tv_val_due_date);
		
		tvDueDate.setText(ps.getDueDate());
//		tvDueDate.setText(Methods.getTimeLabel_Japanese(ps.getDueDate()));
		
		
		/***************************************
		 * Get list view
		 ***************************************/
		ListView lv = (ListView) dlg3.findViewById(R.id.dlg_confirm_delete_ps_item_lv);
		
		/***************************************
		 * Get item name list
		 ***************************************/
		List<String> itemNameList = new ArrayList<String>();
		
		for (int i = 0; i < loadedSIList.size(); i++) {
			
			SI si = loadedSIList.get(i);
			
			itemNameList.add(si.getName());
			
		}//for (int i = 0; i < loadedSIList.size(); i++)
		
		// Log
		Log.d("ListOnItemClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "itemNameList.size()=" + itemNameList.size());
		
		/***************************************
		 * Array adapter
		 ***************************************/
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
	              actv,
//	              android.R.layout.simple_spinner_item,
	              android.R.layout.simple_list_item_1,
	              itemNameList);
		
		/***************************************
		 * Set item names to list
		 ***************************************/
		lv.setAdapter(adapter);
		
		/***************************************
		 * Show dialog
		 ***************************************/
		dlg3.show();
		
	}//private void case_delete_toBuyList(AdapterView<?> parent, int position)


	private void 
	case_LOAD_TOBUY_LIST
	(AdapterView<?> parent, int position) {
		// TODO Auto-generated method stub
		/***************************************
		 * Get PS item
		 ***************************************/
		PS ps = (PS) parent.getItemAtPosition(position);
		
		/***************************************
		 * Set due date
		 ***************************************/
		Methods.update_TV_DueDate(actv, ps);
		
//		TextView tvDueDate = (TextView) actv.findViewById(R.id.itemlist_tab2_tv_due_date);
//
//		tvDueDate.setText(
//				String.format("%s %s",
//						ps.getDueDate(),
//						ps.getStoreName())
//				);

		/***************************************
		 * Set item list
		 ***************************************/
		/***************************************
		 * Get item list
		 ***************************************/
		String s_ItemList = ps.getItems();

		CONS.TabActv.toBuyList.clear();
		
		List<SI> loadedSIList = Methods_sl.getSIListFromItemList(actv, s_ItemList);
		
		// Sort list
		Methods_sl.sortItemList(loadedSIList);
		
		CONS.TabActv.toBuyList.addAll(loadedSIList);
		
		// Log
		if (CONS.TabActv.toBuyList == null) {
			
			// debug
			Toast.makeText(actv, "Couldn't get the list", Toast.LENGTH_LONG).show();
			
			// Log
			Log.d("ListOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "CONS.TabActv.toBuyList == null");
			
			return;

		}//if (CONS.TabActv.toBuyList == null)
		
		/***************************************
		 * Add new item ids to CONS.TabActv.tab_toBuyItemIds
		 * 1. First, clear the current list
		 * 2. Then, add new item ids
		 ***************************************/
		CONS.TabActv.tab_toBuyItemIds.clear();
		
		for (int i = 0; i < loadedSIList.size(); i++) {
			
			SI si = loadedSIList.get(i);
			
			CONS.TabActv.tab_toBuyItemIds.add(si.getId());
			
			CONS.TabActv.tab_checkedItemIds.add(si.getId());
			
		}//for (int i = 0; i < loadedSIList.size(); i++)
		
		
		/***************************************
		 * Set list to the tab
		 ***************************************/
		CONS.TabActv.adpToBuys.notifyDataSetChanged();
		CONS.TabActv.adpItems.notifyDataSetChanged();
		
		/***************************************
		 * Close dialogues
		 ***************************************/
		dlg1.dismiss();
		dlg2.dismiss();
		
		/***************************************
		 * Update sum
		 ***************************************/
		TextView tvSum = (TextView) actv.findViewById(R.id.itemlist_tab2_tv_sum);
		
		// Get sum
		int sum = 0;
		
		for (int i = 0; i < CONS.TabActv.toBuyList.size(); i++) {
			
			SI si = CONS.TabActv.toBuyList.get(i);
			
			sum += si.getPrice() * si.getNum();
			
		}//for (int i = 0; i < CONS.TabActv.toBuyList.size(); i++)
		
		// Display
		tvSum.setText(String.format("合計 %d 円", sum));

	}//private void load_toBuyList(AdapterView<?> parent, int position)


	private void 
	tab_toBuyList
	(AdapterView<?> parent, int position) {
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
		
		// Log
		Log.d("ListOnItemClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "position=" + position);
		
		/***************************************
		 * checkedItemPositions
		 ***************************************/
		int itemId = si.getId();
		
		if (CONS.TabActv.tab_boughtItemIds.contains(itemId)) {
			
			// Log
			Log.d("ListOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Contained in CONS.TabActv.tab_boughtItemIds=" + itemId);
			
//			CONS.TabActv.tab_checkedPositions.remove(itemId);
			CONS.TabActv.tab_boughtItemIds.remove(new Integer(itemId));

			/***************************************
			 * BGM
			 ***************************************/
			if (CONS.bgm == true) {
				
				int bgmResourceId = R.raw.bgm_3_uncheck_item;
				
				TaskAudioTrack task = new TaskAudioTrack(actv);
				
//				task.execute("Start");
				task.execute(bgmResourceId);
				
			}//if (bgm == true)

		} else if (!CONS.TabActv.tab_boughtItemIds.contains(itemId)) {//if (CONS.TabActv.tab_checkedPositions.contains(itemId))

			// Log
			Log.d("ListOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Not contained in CONS.TabActv.tab_boughtItemIds=" + itemId);

			CONS.TabActv.tab_boughtItemIds.add(itemId);
			
			// Log
			Log.d("ListOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Added: Item id=" + itemId);

			/***************************************
			 * BGM
			 ***************************************/
			if (CONS.bgm == true) {
				
				int bgmResourceId = R.raw.bgm_2_koto_t150_1second;
				
				TaskAudioTrack task = new TaskAudioTrack(actv);
				
//				task.execute("Start");
				task.execute(bgmResourceId);
				
			}//if (bgm == true)

		}//if (CONS.TabActv.tab_checkedPositions.contains(itemId))
		
//		//debug
//		StringBuilder sb = new StringBuilder();
//		
//		for (int i = 0; i < CONS.TabActv.tab_toBuyItemIds.size(); i++) {
//		
//			sb.append(CONS.TabActv.tab_toBuyItemIds.get(i));
//			sb.append(",");
//			
//		}//for (int i = 0; i < CONS.TabActv.tab_checkedPositions.size(); i++)
//		
//		// Log
//		Log.d("ListOnItemClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "Positions=" + sb.toString());
		
		/***************************************
		 * Notify adapter
		 ***************************************/
//		CONS.TabActv.adpItems.notifyDataSetChanged();
		CONS.TabActv.adpToBuys.notifyDataSetChanged();
		
//		/***************************************
//		 * Show dialog
//		 ***************************************/
//		Methods_dlg.dlg_tabActv_tab2Lv(actv, si);
		
	}//private void tab_toBuyList(AdapterView<?> parent, int position)


	private void 
	tab_itemList
	(AdapterView<?> parent, int position) {
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
		 * checkedItemPositions
		 ***************************************/
		int itemId = si.getId();
		
//		if (CONS.TabActv.tab_checkedItemIds.contains(itemId)) {
		if (CONS.TabActv.tab_checkedItemIds.contains(itemId)
				&& !CONS.TabActv.tab_toBuyItemIds.contains(itemId)) {
			
//			CONS.TabActv.tab_checkedPositions.remove(itemId);
			CONS.TabActv.tab_checkedItemIds.remove(new Integer(itemId));

			/***************************************
			 * BGM
			 ***************************************/
			if (CONS.bgm == true) {
				
				int bgmResourceId = R.raw.bgm_3_uncheck_item;
				
				TaskAudioTrack task = new TaskAudioTrack(actv);
				
//				task.execute("Start");
				task.execute(bgmResourceId);
				
			}//if (bgm == true)

//		} else if (!CONS.TabActv.tab_checkedItemIds.contains(itemId)) {//if (CONS.TabActv.tab_checkedPositions.contains(itemId))
		} else if (!CONS.TabActv.tab_checkedItemIds.contains(itemId)
					&& !CONS.TabActv.tab_toBuyItemIds.contains(itemId)) {//if (CONS.TabActv.tab_checkedPositions.contains(itemId))
			
			CONS.TabActv.tab_checkedItemIds.add(itemId);
			
			// Log
			Log.d("ListOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Added: Item id=" + itemId);

			/***************************************
			 * BGM
			 ***************************************/
			if (CONS.bgm == true) {
				
				int bgmResourceId = R.raw.bgm_2_koto_t150_1second;
				
				TaskAudioTrack task = new TaskAudioTrack(actv);
				
//				task.execute("Start");
				task.execute(bgmResourceId);
				
			}//if (bgm == true)

		}//if (CONS.TabActv.tab_checkedPositions.contains(itemId))
		
		//debug
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < CONS.TabActv.tab_checkedItemIds.size(); i++) {
		
			sb.append(CONS.TabActv.tab_checkedItemIds.get(i));
			sb.append(",");
//			// Log
//			Log.d("ListOnItemClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "item" + (i+1) + "=" + CONS.TabActv.tab_checkedPositions.get(i));
//			
		}//for (int i = 0; i < CONS.TabActv.tab_checkedPositions.size(); i++)
		
		// Log
		Log.d("ListOnItemClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Positions=" + sb.toString());
		
//		/***************************************
//		 * BGM
//		 ***************************************/
//
//		/***************************************
//		 * Play sound
//		 ***************************************/
//		if (bgm == true) {
//			
//			int bgmResourceId = R.raw.bgm_2_koto_t150_1second;
//			
////			bgm(actv, bgmResourceId);
//			
////			Methods_sl.playSound(this);
////			TaskAudioTrack task = new TaskAudioTrack(actv, bgmResourceId);
//			TaskAudioTrack task = new TaskAudioTrack(actv);
//			
////			task.execute("Start");
//			task.execute(bgmResourceId);
//			
//		}//if (bgm == true)
		
		/***************************************
		 * Notify adapter
		 ***************************************/
		CONS.TabActv.adpItems.notifyDataSetChanged();
		
	}//private void tab_itemList(AdapterView<?> parent, int position)

}//public class DialogOnItemClickListener implements OnItemClickListener
