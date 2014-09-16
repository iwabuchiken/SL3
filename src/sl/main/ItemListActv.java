package sl.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sl.adapters.ItemListAdapter;
import sl.items.SI;
import sl.listeners.ButtonOnClickListener;
import sl.listeners.ButtonOnTouchListener;
import sl3.main.R;
import sl.utils.CONS;
import sl.utils.DBUtils;
import sl.utils.Methods;
import sl.utils.Methods_sl;
import sl.utils.Tags;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ItemListActv extends ListActivity {

	//
	public static List<SI> list;
	
	//
	public static ItemListAdapter adapter;
	
	//
	public static List<Integer> checkedPositions;
	
	public static List<Integer> toBuys;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		/*----------------------------
		 * Steps
		 * 1. Super
		 * 2. Set content
		 * 3. setList()
		 * 4. Set listener => Buttons
		 * 5. Instantiate vars
		----------------------------*/
		super.onCreate(savedInstanceState);

		//
		setContentView(R.layout.itemlist);

		// Title
		setTitle(this.getClass().getName());
		
		/*----------------------------
		 * 3. setList()
			----------------------------*/
		setList();
		
		/*----------------------------
		 * 4. Set listener => Buttons
			----------------------------*/
		set_listener();
		
		/*----------------------------
		 * 5. Instantiate vars
			----------------------------*/
		checkedPositions = new ArrayList<Integer>();
		
		toBuys = new ArrayList<Integer>();
		
	}//public void onCreate(Bundle savedInstanceState)

	
	private void set_listener() {
		/*----------------------------
		 * Steps
		 * 1. Button => choose
		 * 2. Button => see_chosen
			----------------------------*/
		Button bt_choose = (Button) findViewById(R.id.itemlist_bt_choose);
		
		bt_choose.setTag(Tags.ButtonTags.itemlist_bt_choose);
		
		bt_choose.setOnTouchListener(new ButtonOnTouchListener(this));
		bt_choose.setOnClickListener(new ButtonOnClickListener(this));
		
//		/*----------------------------
//		 * 2. Button => see_chosen
//			----------------------------*/
//		Button bt_see_chosen = (Button) findViewById(R.id.itemlist_bt_see_chosen);
//		
//		bt_see_chosen.setTag(Tags.ButtonTags.itemlist_bt_see_chosen);
//		
//		bt_see_chosen.setOnTouchListener(new ButtonOnTouchListener(this));
//		bt_see_chosen.setOnClickListener(new ButtonOnClickListener(this));
		
		
	}//private void set_listener()

	private void setList() {
		//
		list = new ArrayList<SI>();
		
		//
		DBUtils dbm = new DBUtils(this);
		
		SQLiteDatabase db = dbm.getReadableDatabase();
		
		Cursor c = db.query(
				CONS.DB.tableName, 
//										DBManager.columns,
//				CONS.DBAdmin.columns_with_index,
				CONS.DB.columns_with_index2,
										null, null, null, null, null);
		
		//
		c.moveToFirst();
		
		for (int i = 0; i < c.getCount(); i++) {
//			// Log
//			Log.d("ItemList.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "c.getString(0) => " + c.getString(0));
//			Log.d("ItemList.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "c.getString(1) => " + c.getString(1));
//			Log.d("ItemList.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "c.getString(4) => " + c.getString(4));
			
			//
//			ShoppingItem item = new ShoppingItem(
//									c.getString(0),		// store
//									c.getString(1),		// name
//									c.getInt(2),			// price
//									c.getString(3),		// genre
//									c.getInt(4)				// id
//									);

//			0									1		2		3		4			5
//			{android.provider.BaseColumns._ID, "name", "yomi", "genre", "store", "price"}
			SI item = new SI(
					c.getInt(0),		// id store
					c.getString(1),		// name
					c.getString(2),		// yomi
					c.getString(3),		// genre
					c.getString(4),		//	store
					c.getInt(5)			// price
					);
			
			//
			list.add(item);
			
			//
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)
		
		//
		db.close();

		/***************************************
		 * Sort list
		 ***************************************/
		Methods_sl.sortItemList(list);
		
		/***************************************
		 * 4. Set up adapter
		 ***************************************/
		//
		adapter = new ItemListAdapter(
							this,
							R.layout.adapteritem,
							list
							);
		
		//
		setListAdapter(adapter);
		
	}//private void setList()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.actv_tab_1, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		switch (item.getItemId()) {
		case R.id.actv_tab_1_filter:

			Methods.dlg_filterList(this);
			
			break;
		
		}//switch (item.getItemId())
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onDestroy();
	}

	@Override
	protected void onListItemClick(ListView lv, View v, int position, long id) {
		/*----------------------------
		 * Steps
		 * 1. super
		 * 2. checkedPositions
		 * 3. Notify adapter
			----------------------------*/
		
		super.onListItemClick(lv, v, position, id);
		
		SI si = (SI) lv.getItemAtPosition(position);
		
		// Log
		Log.d("ItemList.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "si.toString() => " + si.toString());
				+ "]", 
				"si.getName() => " + si.getName()+
				"/" + "id=" + si.getId());
		
		/*----------------------------
		 * 2. checkedPositions
			----------------------------*/
		if (toBuys.contains((Integer) position)) {
			
			toBuys.remove((Integer) position);
			
		} else if (checkedPositions.contains((Integer) position)) {

			checkedPositions.remove((Integer) position);
			
			// Log
			Log.d("ItemList.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Position removed => " + position + "(size=" + checkedPositions.size());
			
			
		} else {//if (checkedPositions.contains((Integer) position)

			checkedPositions.add((Integer) position);

			// Log
			Log.d("ItemList.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Position added => " + position + "(size=" + checkedPositions.size());

		}//if (checkedPositions.contains((Integer) position)
		
		/*----------------------------
		 * 3. Notify adapter
			----------------------------*/
		adapter.notifyDataSetChanged();
		
	}//protected void onListItemClick(ListView l, View v, int position, long id)
}
