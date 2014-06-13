package sl.main;

import java.util.ArrayList;
import java.util.List;

import sl.listeners.*;
import sl3.main.R;
import sl.utils.CONS;
import sl.utils.DBUtils;
import sl.utils.Methods;
import sl.utils.Tags;
import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class DBActv extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		/*----------------------------
		 * Steps
		 * 1. Super
		 * 2. Set content
		----------------------------*/
		super.onCreate(savedInstanceState);

		//
		setContentView(R.layout.actv_db);
		
		//
		setListeners();

		// TEMP
//		modify_table();
//		String[] colNames = getColNames();
//		List<String> colNames = getColNames();
		
		
		
	}//public void onCreate(Bundle savedInstanceState)

	private List<String> getColNames() {
		//
		DBUtils dbm = new DBUtils(this);
		
		SQLiteDatabase wdb = dbm.getWritableDatabase();

		// REF=> http://stackoverflow.com/questions/947215/how-to-get-a-list-of-column-names-on-sqlite3-iphone
		String sql = "PRAGMA table_info('" + CONS.tableName + "')";
		
		Cursor c = wdb.rawQuery(sql, null);
		
		// Log
		Log.d("DBAdminActivity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount() => " + c.getCount());
		
		Log.d("DBAdminActivity.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getColumnCount() => " + c.getColumnCount());

		List<String> colNames = new ArrayList<String>();
		
		c.moveToFirst();
		
		for (int i = 0; i < c.getCount(); i++) {
			
			colNames.add(c.getString(0));
			
			// Log
			Log.d("DBAdminActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c.getString(0) => " + c.getString(0) + 
					" / " + "c.getString(1) => " + c.getString(1));
			
			
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)
		
		wdb.close();
		
		for (String string : colNames) {
			
			// Log
			Log.d("DBAdminActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "colName => " + string);
			
			
		}
		
//		return (String[])colNames.toArray();
		return colNames;
		
	}//private List<String> getColNames()

	private void modify_table() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		//
		DBUtils dbm = new DBUtils(this);
		
		SQLiteDatabase wdb = dbm.getWritableDatabase();
		
		boolean result = dbm.tableExists(wdb, CONS.tableName);
		
		if (result == false) {
			
			// Log
			Log.d("DBAdminActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist: " + CONS.tableName);
			
			wdb.close();
			
			return;
			
		}//if (result == false)
		
		// REF=> http://www.dbonline.jp/sqlite/table/index4.html
		//
		String sql = "ALTER TABLE " + CONS.tableName + " ADD COLUMN " + "yomi TEXT";
		
		try {
			wdb.execSQL(sql);
			
			// Log
			Log.d("DBAdminActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "SQL => Done: " + sql);
			
		} catch (SQLException e) {

			// Log
			Log.d("DBAdminActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
		}//try
				
		wdb.close();
		
		return;
		
	}//private void modify_table()

	private void setListeners() {
    	/*----------------------------
		 * 3.1. Buttons
			----------------------------*/
		//
    	Button bt_create = (Button) findViewById(R.id.db_manager_btn_create_table);
    	Button bt_drop = (Button) findViewById(R.id.db_manager_btn_drop_table);
    	
    	/*----------------------------
		 * 3.2. Tags
			----------------------------*/
    	bt_create.setTag(Tags.ButtonTags.db_manager_activity_create_table);
    	bt_drop.setTag(Tags.ButtonTags.db_manager_activity_drop_table);
    	
    	/*----------------------------
		 * 3.3. Listeners
		 * 		1. OnClick
		 * 		2. OnTouch
			----------------------------*/
    	/*----------------------------
		 * 3.3.1. OnClick
			----------------------------*/
    	//
    	bt_create.setOnClickListener(new ButtonOnClickListener(this));
    	bt_drop.setOnClickListener(new ButtonOnClickListener(this));
    	
    	/*----------------------------
		 * 3.3.2. OnTouch
			----------------------------*/
    	//
    	bt_create.setOnTouchListener(new ButtonOnTouchListener(this));
    	bt_drop.setOnTouchListener(new ButtonOnTouchListener(this));
    	
	}//private void setListeners()
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onPause();
		// Log
		Log.d("DBAdminActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Pausing...");
		
		this.overridePendingTransition(0, 0);
		
	}//protected void onPause()

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
		
		// Log
		Log.d("DBAdminActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Destroying");
		
//		this.finish();
//		
//		this.overridePendingTransition(0, 0);
	}//protected void onDestroy()
	
}//public class DBAdminActv extends Activity
