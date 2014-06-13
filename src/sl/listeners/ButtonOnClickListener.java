package sl.listeners;

import java.util.ArrayList;
import java.util.List;

import sl.adapters.ItemListAdapter;
import sl.items.ShoppingItem;
import sl.main.ItemListActv;
import sl.main.R;
import sl.tasks.TaskAudioTrack;
import sl.tasks.Task_PostData;
import sl.utils.CONS;
import sl.utils.DBUtils;
import sl.utils.Methods;
import sl.utils.Methods_sl;
import sl.utils.Tags;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ButtonOnClickListener implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	Spinner sp_store_name;
	Spinner sp_genre_name;
	
	//
	Vibrator vib;
	
	public ButtonOnClickListener(Activity actv) {
		//
		this.actv = actv;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	public ButtonOnClickListener(Activity actv,
			Spinner sp_store_name, Spinner sp_genre_name) {
		
		this.actv			= actv;
		
		this.sp_store_name	= sp_store_name;
		this.sp_genre_name	= sp_genre_name;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
	}

	//	@Override
	public void onClick(View v) {
		//
		Tags.ButtonTags tag_name = (Tags.ButtonTags) v.getTag();

		//
		switch (tag_name) {
		
		case register:
			
			case_register(v);
			
			break;//case register
		
		case db_manager_activity_create_table://-----------------------------------
			
			// REF => http://www.adakoda.com/android/000124.html
			//
			vib.vibrate(40);
			
			//
			Methods.dlg_createTable(actv);
			
			break;
			
		case db_manager_activity_drop_table://-----------------------------------
			//
//			vib.vibrate(10);
//			vib.vibrate(100);
//			vib.vibrate(50);
			vib.vibrate(40);
			
			//
			Methods.dlg_dropTable(actv);
			
			break;

		case sl_main_bt_item_list://-----------------------------------

			vib.vibrate(Methods.vibLength);
			
			Intent i = new Intent();
			i.setClass(actv, ItemListActv.class);
			actv.startActivity(i);
			
//			// debug
//			Toast.makeText(actv, "sl_main_bt_item_list", 2000)
//					.show();
			
			break;
			
		case sl_main_bt_register://-----------------------------------
			
			vib.vibrate(Methods.vibLength);
			
			Methods.dlg_register_main(actv);
			
//			// debug
//			Toast.makeText(actv, "sl_main_bt_register", 2000)
//					.show();
			
			break;
			
//		case sl_main_bt_db://-----------------------------------
//			
//			vib.vibrate(Methods.vibLength);
//			
//			//
//			i = new Intent();
//			i.setClass(actv, DBAdminActv.class);
//			actv.startActivity(i);
//
//			break;
//			
		case itemlist_bt_choose://-----------------------------------
			
			if (ItemListActv.toBuys == null) {
				
				// Log
				Log.d("ButtonOnClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "ItemListActv.toBuys => null");
				
			} else {//if (ItemListActv.toBuys == null)

				// Log
				Log.d("ButtonOnClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "ItemListActv.toBuys => Not null");

			}//if (ItemListActv.toBuys == null)
			
			
			ItemListActv.toBuys.addAll(ItemListActv.checkedPositions);
			
			List<ShoppingItem> chosen_items_list = new ArrayList<ShoppingItem>();
			
			for (Integer num : ItemListActv.toBuys) {
				
				chosen_items_list.add(ItemListActv.list.get(num));
				
			}

			if (chosen_items_list.size() <= 0) {
				
				// debug
				Toast.makeText(actv, "No item chosen", Toast.LENGTH_SHORT).show();
				
				return;
				
			}//if (chosen_items_list.size() == 0)

			/***************************************
			 * Sort list
			 ***************************************/
			// Log
			Log.d("ButtonOnClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "chosen_items_list.size()=" + chosen_items_list.size());
			
			// Log
			ShoppingItem si = chosen_items_list.get(0);
			
			Log.d("ButtonOnClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"si.getName()=" + si.getName()
					+ "/"
					+ "si.getYomi()=" + si.getYomi());
			
			Methods_sl.sortItemList(chosen_items_list);
			
			/***************************************
			 * Adapter
			 ***************************************/
			ItemListActv.adapter.clear();
			
			ItemListActv.adapter = new ItemListAdapter(
					actv,
					R.layout.adapteritem,
//					ItemList.list
					chosen_items_list
					);

			((ListActivity)actv).setListAdapter(ItemListActv.adapter);
			
			/***************************************
			 * Clear the positions
			 ***************************************/
			ItemListActv.checkedPositions.clear();
			
			ItemListActv.toBuys.clear();
			
			break;
			
//			ItemListActv.toBuys.addAll(ItemListActv.checkedPositions);
//			
//			ItemListActv.adapter.notifyDataSetChanged();
//			
//			break;

		case itemlist_tabs_bt_choose://-----------------------------------
			
			itemlist_tabs_bt_choose();
			
			break;// case itemlist_tabs_bt_choose
			
		default:
			break;
		}//switch (tag_name)
	}

	private void case_register(View v) {
		// TODO Auto-generated method stub
		EditText et_name = (EditText) actv.findViewById(R.id.v1_et_name);
		EditText et_price = (EditText) actv.findViewById(R.id.v1_et_price);
		
		EditText et_yomi = (EditText) actv.findViewById(R.id.v1_et_yomi);
		
		// All written?
		if(
				et_name.getText().toString().equals("") ||
				et_price.getText().toString().equals("") //||
//				et_genre.getText().toString().equals("")
				) {
			// debug
			Toast.makeText(actv, "Empty item exists", 2000)
					.show();
		}//if
		

		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase db = dbm.getWritableDatabase();
		
//		columns => {"store", "name", "price", "genre", "yomi"};
		/*
		//	0		1		2
		"store", "name", "price",
		//	3		4			5
		"genre", "yomi", android.provider.BaseColumns._ID, 
		//	6			7				8
		"created_at", "updated_at", "posted_at"
		*/
		boolean result = dbm.storeData(
						db, 
						CONS.tableName, 
						CONS.cols_SI_Register,
//						CONS.columns,
						new String[]{
//								et_store.getText().toString(),
								sp_store_name.getSelectedItem().toString(),
								
								et_name.getText().toString(),
//								et_yomi.getText().toString(),
								et_price.getText().toString(),
								
//								et_genre.getText().toString()
								sp_genre_name.getSelectedItem().toString(),
								et_yomi.getText().toString(),
								Methods.getTimeLabel_V2(
										Methods.getMillSeconds_now(), 2),
								Methods.getTimeLabel_V2(
										Methods.getMillSeconds_now(), 2)
//								String.valueOf(Methods.getMillSeconds_now()),
//								String.valueOf(Methods.getMillSeconds_now())
						});
		
		if (result == true) {
			// Log
			Log.d("ButtonOnClickListener.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", "Data stored");
			// debug
			Toast.makeText(actv, "Data stored", Toast.LENGTH_LONG)
					.show();
			
		} else {//if (result == true)
			// Log
			Log.d("ButtonOnClickListener.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", "Data not stored");
		}//if (result == true)
		
		db.close();
		
		/*********************************
		 * Post data to remote
		 * 		Build: ShoppingItem instance
		 * 		Post data
		 *********************************/
		// Build: ShoppingItem instance
		ShoppingItem si = Methods_sl.getSI_FromNameAndStore(
					actv,
					et_name.getText().toString(),
					sp_store_name.getSelectedItem().toString());
		
//		si.setPosted_at(
//				Methods.getTimeLabel_V2(
//						Methods.getMillSeconds_now(), 2));
//		si.setPosted_at(Methods.getMillSeconds_now());
		
		// Log
		Log.d("["
				+ "ButtonOnClickListener.java : "
				+ +Thread.currentThread().getStackTrace()[2]
						.getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2]
						.getMethodName() + "]",
				"New item => "
				+ "name=" + si.getName()
				+ " / "
				+ "store=" + si.getStore()
				+ " / "
				+ "id=" + si.getId()
				+ " / "
				+ "created_at=" + si.getCreated_at()
				);
		
//		boolean res = Methods_sl.update_SI(actv, si);
		
//		if (res == false) {
//			
//			// debug
//			Toast.makeText(actv,
//					"Data 'posted_at' => Not added",
//					Toast.LENGTH_LONG).show();
//			
//			return;
//			
//		}//if (res == false)
		
		
		Task_PostData task = new Task_PostData(actv, si);
		
		task.execute(
				CONS.HTTPData.registerChoice.single_item.toString());


	}//private void case_register(View v)

	private void itemlist_tabs_bt_choose() {
		// TODO Auto-generated method stub
		
		/***************************************
		 * BGM
		 ***************************************/
		if (CONS.bgm == true) {
			
			int bgmResourceId = R.raw.bgm_4_add_to_tobuy_list;
			
			TaskAudioTrack task = new TaskAudioTrack(actv);
			
//			task.execute("Start");
			task.execute(bgmResourceId);
			
		}//if (bgm == true)


		/***************************************
		 * Add to toBuyItemIds
		 ***************************************/
		// Log
		Log.d("ButtonOnClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"CONS.tab_checkedItemIds.size()=" + CONS.tab_checkedItemIds.size());
		
		for (Integer id : CONS.tab_checkedItemIds) {
			
			if (!CONS.tab_toBuyItemIds.contains(id)) {
				
				CONS.tab_toBuyItemIds.add(id);
				
			} else {//if (CONS.tab_toBuyItemIds.contains(id))
				
				// Log
				Log.d("ButtonOnClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Item is already in toBuy list: " + id.intValue());
				
			}//if (CONS.tab_toBuyItemIds.contains(id))
			
			
		}//for (Integer id : CONS.tab_checkedItemIds)
		
		//debug
		StringBuilder sb = new StringBuilder();
		
		for (Integer id : CONS.tab_toBuyItemIds) {
			
			sb.append(id.intValue());
			
			sb.append(",");
			
		}
		
		// Log
		Log.d("ButtonOnClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "CONS.tab_toBuyItemIds=" + sb.toString());
		
		/***************************************
		 * Notify adapter: adpItems
		 * 	=> The background of the items go green 
		 ***************************************/
		CONS.adpItems.notifyDataSetChanged();
		
		/***************************************
		 * Update: toBuyList
		 ***************************************/
		// Log
		Log.d("ButtonOnClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"CONS.toBuyList.size()=" + CONS.toBuyList.size());
		
		Methods_sl.updateListView_ToBuyList(actv);
		
		// Log
		Log.d("ButtonOnClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"CONS.toBuyList.size()=" + CONS.toBuyList.size());
		
		/***************************************
		 * Notify adapter: CONS.tab_toBuyItemIds
		 ***************************************/
		CONS.adpToBuys.notifyDataSetChanged();
		
	}//private void itemlist_tabs_bt_choose()

}//public class ButtonOnClickListener implements OnClickListener
