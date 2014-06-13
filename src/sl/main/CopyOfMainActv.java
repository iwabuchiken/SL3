package sl.main;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import sl.items.ShoppingItem;
import sl.listeners.ButtonOnClickListener;
import sl.listeners.ButtonOnTouchListener;
import sl.main.R;
import sl.tasks.TaskAudioTrack;
import sl.utils.CONS;
import sl.utils.DBUtils;
import sl.utils.Methods;
import sl.utils.Methods_dlg;
import sl.utils.Methods_sl;
import sl.utils.Tags;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

//public class ShoppingListActivity extends ListActivity {
public class CopyOfMainActv extends Activity {
	
	public static Vibrator vib;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	/*----------------------------
		 * Steps
		 * 1. Set up
		 * 		1. super
		 * 		2. Content view
		 * 		3. vib
		 * 2. Add listeners
			----------------------------*/
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_main);
        
        setTitle(this.getClass().getName());
        
        vib = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        
        /*----------------------------
		 * 2. Add listeners
			----------------------------*/
		add_listeners();
        
        //debug
		do_debugs();
		
		//admin
		admin_createTable_purchaseSchedule();
		
    }//public void onCreate(Bundle savedInstanceState)

    
	private void do_debugs() {
		
//		_debug_D_44_V_3_0_7_SQL_Generic(
//				CONS.SQLs.a_20140110_105949_InsertInto_ShoppingItemNew);
//		_debug_D_44_V_3_0_7_DropTable_ShoppingItemNew();
//		_debug_D_44_V_3_0_7_CreateTable_ShoppingItemNew();
		_debug_D_44_V_3_0_7_DropTable_ShoppingItem();
		_debug_D_44_V_3_0_7_RenameTable_ShoppingItemNew();
//		_debug_D_44_V_3_0_7_Test_GetTimeLabel();
//		_debug_D_44_V_3_0_7_UpdateColumns();
//		_debug_D_44_V_3_0_6_Test_update_SI_V2();
//		_debug_D_44_V_3_0_6_Test_update_SI();
//		_debug_D_44_V_3_0_6_GetColumnNames();
//		_debug_1_d_44_seg_5();
//		_debug_1_d_44();
//		_debug_D_44_V_3_0_2_TestMethod_getSI_FromDbId();
//		_debug_D_44_V_3_0_3_TestMethod_getSI_FromNameAndStore();
//		_debug_D_44_V_3_0_3_AddColumns();
//		_debug_D_44_V_3_0_3_AddColumns();
		
	}//private void do_debugs()


	private void _debug_D_44_V_3_0_7_SQL_Generic(String[] sqls) {
		// TODO Auto-generated method stub
//		String[] sqls =
//				CONS.SQLs.a_20140105_112211_DropTable_shopping_item_new;
//		CONS.SQLs.a_20140110_071149_CreateTable_shopping_item_new;
		
		DBUtils dbu = new DBUtils(this, CONS.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		int count = 0;
		
		for (String sql : sqls) {
			
			// Log
			Log.d("["
					+ "MainActv.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
							+ Thread.currentThread().getStackTrace()[2].getMethodName()
							+ "]", "sql=" + sql);
			
			try {
				wdb.execSQL(sql);
				
				count += 1;
				
				// debug
				
//				Toast.makeText(this, "debug done", Toast.LENGTH_LONG).show();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				Toast.makeText(this, "SQLException", Toast.LENGTH_LONG).show();
				e.printStackTrace();
				
			}
			
		}
		
		Toast.makeText(this,
				"SQL done => "
						+ String.valueOf(count)
						+ " out of "
						+ String.valueOf(sqls.length),
						Toast.LENGTH_LONG).show();
		
		wdb.close();

	}
	
	private void _debug_D_44_V_3_0_7_DropTable_ShoppingItemNew() {
		// TODO Auto-generated method stub
		String[] sqls =
				CONS.SQLs.a_20140105_112211_DropTable_shopping_item_new;
//		CONS.SQLs.a_20140110_071149_CreateTable_shopping_item_new;
		
		DBUtils dbu = new DBUtils(this, CONS.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		int count = 0;
		
		for (String sql : sqls) {
			
			// Log
			Log.d("["
					+ "MainActv.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
							+ Thread.currentThread().getStackTrace()[2].getMethodName()
							+ "]", "sql=" + sql);
			
			try {
				wdb.execSQL(sql);
				
				count += 1;
				
				// debug
				
//				Toast.makeText(this, "debug done", Toast.LENGTH_LONG).show();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				Toast.makeText(this, "SQLException", Toast.LENGTH_LONG).show();
				e.printStackTrace();
				
			}
			
		}
		
		Toast.makeText(this,
				"SQL done => "
						+ String.valueOf(count)
						+ " out of "
						+ String.valueOf(sqls.length),
						Toast.LENGTH_LONG).show();
		
		wdb.close();
		
	}


	private void _debug_D_44_V_3_0_7_CreateTable_ShoppingItemNew() {
		// TODO Auto-generated method stub
		String[] sqls =
				CONS.SQLs.a_20140110_104629_Createtable_ShoppingItemNew;
//		CONS.SQLs.a_20140110_071149_CreateTable_shopping_item_new;
		
		DBUtils dbu = new DBUtils(this, CONS.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		int count = 0;
		
		for (String sql : sqls) {
			
			// Log
			Log.d("["
					+ "MainActv.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "sql=" + sql);
			
			try {
				wdb.execSQL(sql);
				
				count += 1;
				
				// debug
				
//				Toast.makeText(this, "debug done", Toast.LENGTH_LONG).show();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				Toast.makeText(this, "SQLException", Toast.LENGTH_LONG).show();
				e.printStackTrace();
				
			}
			
		}
		
		Toast.makeText(this,
				"SQL done => "
				+ String.valueOf(count)
				+ " out of "
				+ String.valueOf(sqls.length),
				Toast.LENGTH_LONG).show();
		
		wdb.close();
		
	}//private void _debug_D_44_V_3_0_7_CreateTable_ShoppingItemNew()


	private void _debug_D_44_V_3_0_7_RenameTable_ShoppingItemNew() {
		// TODO Auto-generated method stub
		String[] sqls =
				CONS.SQLs.a_20140105_113651_ChangeTableName_shopping_item_new;
//		CONS.SQLs.a_20140110_071149_CreateTable_shopping_item_new;
		
		DBUtils dbu = new DBUtils(this, CONS.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		int count = 0;
		
		for (String sql : sqls) {
			
			// Log
			Log.d("["
					+ "MainActv.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "sql=" + sql);
			
			try {
				wdb.execSQL(sql);
				
				count += 1;
				
				// debug
				
//				Toast.makeText(this, "debug done", Toast.LENGTH_LONG).show();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				Toast.makeText(this, "SQLException", Toast.LENGTH_LONG).show();
				e.printStackTrace();
				
			}
			
		}
		
		Toast.makeText(this,
				"SQL done => "
				+ String.valueOf(count)
				+ " out of "
				+ String.valueOf(sqls.length),
				Toast.LENGTH_LONG).show();
		
		wdb.close();
		
	}
	
	private void _debug_D_44_V_3_0_7_DropTable_ShoppingItem() {
		// TODO Auto-generated method stub
		String[] sqls =
				CONS.SQLs.a_20140105_113308_DropTable_shopping_item;
//		CONS.SQLs.a_20140110_071149_CreateTable_shopping_item_new;
		
		DBUtils dbu = new DBUtils(this, CONS.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		int count = 0;
		
		for (String sql : sqls) {
			
			// Log
			Log.d("["
					+ "MainActv.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
							+ Thread.currentThread().getStackTrace()[2].getMethodName()
							+ "]", "sql=" + sql);
			
			try {
				wdb.execSQL(sql);
				
				count += 1;
				
				// debug
				
//				Toast.makeText(this, "debug done", Toast.LENGTH_LONG).show();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				Toast.makeText(this, "SQLException", Toast.LENGTH_LONG).show();
				e.printStackTrace();
				
			}
			
		}
		
		Toast.makeText(this,
				"SQL done => "
						+ String.valueOf(count)
						+ " out of "
						+ String.valueOf(sqls.length),
						Toast.LENGTH_LONG).show();
		
		wdb.close();
		
	}


	private void _debug_D_44_V_3_0_7_Test_GetTimeLabel() {
		// TODO Auto-generated method stub
		String label = Methods.getTimeLabel_V2(Methods.getMillSeconds_now(), 2);
		
		// Log
		Log.d("[" + "MainActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "time=" + label);
	}


	private void _debug_D_44_V_3_0_7_UpdateColumns() {
		// TODO Auto-generated method stub
		String[] sqls =
				CONS.SQLs.a_20140110_095304_AddColumns_CreatedAt_ToShoppingItemNew;
//		CONS.SQLs.a_20140110_071149_CreateTable_shopping_item_new;
		
		DBUtils dbu = new DBUtils(this, CONS.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		int count = 0;
		
		for (String sql : sqls) {
			
			// Log
			Log.d("["
					+ "MainActv.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "sql=" + sql);
			
			try {
				wdb.execSQL(sql);
				
				count += 1;
				
				// debug
				
//				Toast.makeText(this, "debug done", Toast.LENGTH_LONG).show();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				Toast.makeText(this, "SQLException", Toast.LENGTH_LONG).show();
				e.printStackTrace();
				
			}
			
		}
		
		Toast.makeText(this,
				"SQL done => "
				+ String.valueOf(count)
				+ " out of "
				+ String.valueOf(sqls.length),
				Toast.LENGTH_LONG).show();
		
		wdb.close();

	}//private void _debug_D_44_V_3_0_7_UpdateColumns()


	private void _debug_D_44_V_3_0_6_GetColumnNames() {
		// TODO Auto-generated method stub
		List<String> names = Methods.get_ColumnNames(this, CONS.tableName);
		
		for (String name : names) {
			
			// Log
			Log.d("["
					+ "MainActv.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "name=" + name);
			
		} 
	}


	private void _debug_D_44_V_3_0_6_Test_update_SI() {
		// TODO Auto-generated method stub
		DBUtils dbu = new DBUtils(this, CONS.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		ShoppingItem si =
				Methods_sl.getSI_FromDbId(this, 203);
//		Methods_sl.getSI_FromNameAndStore(this, "なにぬねの", "キャン・ドゥ");
//		Methods_sl.getSI_FromNameAndStore(this, "消毒液", "クリエイト");
		
		if (si == null) {
			// Log
			Log.d("["
					+ "MainActv.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "si => null");
			
			return;
		}
		
		int id = si.getId();
		
		// Log
		Log.d("[" + "MainActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"item=" + si.getName()
				+ "("
				+ "posted=" + si.getPosted_at()
				+ ")");
		
//		si.setPosted_at(Methods.getMillSeconds_now());
		
		si.setName("なにぬねの!!");

		// Log
		Log.d("[" + "MainActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"item=" + si.getName()
				+ "("
				+ "posted=" + si.getPosted_at()
				+ ")");

		boolean res = dbu.update_SI(this, wdb, id, si);
		
		// Log
		Log.d("[" + "MainActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "result => " + res);
		
		wdb.close();
		
	}//private void _debug_D_44_V_3_0_6_update_SI()
	
	private void _debug_D_44_V_3_0_6_Test_update_SI_V2() {
		// TODO Auto-generated method stub
		DBUtils dbu = new DBUtils(this, CONS.dbName);
		
		ShoppingItem si =
				Methods_sl.getSI_FromDbId(this, 203);
//		Methods_sl.getSI_FromNameAndStore(this, "なにぬねの", "キャン・ドゥ");
//		Methods_sl.getSI_FromNameAndStore(this, "消毒液", "クリエイト");
		
		if (si == null) {
			// Log
			Log.d("["
					+ "MainActv.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
							+ Thread.currentThread().getStackTrace()[2].getMethodName()
							+ "]", "si => null");
			
			return;
		}
		
		// Log
		Log.d("[" + "MainActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"item=" + si.getName()
				+ "("
				+ "posted=" + si.getPosted_at()
				+ ")");
		
//		si.setPosted_at(Methods.getMillSeconds_now());
		
		si.setName("なにぬねの!!");
		
		si.setStore("キャン・ドゥ");
		
		// Log
		Log.d("[" + "MainActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"item=" + si.getName()
				+ "("
				+ "posted=" + si.getPosted_at()
				+ ")");

		boolean res = dbu.updateData_SI_all_V2(si);
		// Log
		Log.d("[" + "MainActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "result => " + res);
		
	}//private void _debug_D_44_V_3_0_6_update_SI()


	private void _debug_D_44_V_3_0_3_AddColumns() {
		// TODO Auto-generated method stub
		String[] sqls =
				CONS.SQLs.a_20140108_220957_AddColumns_created_at_etc;
		
		DBUtils dbu = new DBUtils(this, CONS.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		int count = 0;
		
		for (String sql : sqls) {
			
			// Log
			Log.d("["
					+ "MainActv.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "sql=" + sql);
			
			try {
				wdb.execSQL(sql);
				
				count += 1;
				
				// debug
				
//				Toast.makeText(this, "debug done", Toast.LENGTH_LONG).show();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				Toast.makeText(this, "SQLException", Toast.LENGTH_LONG).show();
				e.printStackTrace();
				
			}
			
		}
		
		Toast.makeText(this,
				"SQL done => "
				+ String.valueOf(count)
				+ " out of "
				+ String.valueOf(sqls.length),
				Toast.LENGTH_LONG).show();
		
		wdb.close();
		
	}//private void _debug_D_44_V_3_0_3_AddColumns()
	


	private void
	_debug_D_44_V_3_0_3_TestMethod_getSI_FromNameAndStore() {
		// TODO Auto-generated method stub
		
		ShoppingItem si =
				Methods_sl.getSI_FromNameAndStore(this, "消毒液", "クリエイト");
		
		if (si == null) {
			
			// Log
			Log.d("["
					+ "MainActv.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "si => null");
			
		} else {//if (si == null)
			
			// Log
			Log.d("["
					+ "MainActv.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"name => " + si.getName()
					+ " / "
					+ "price => " + String.valueOf(si.getPrice())
					+ " / "
					+ "id => " + si.getId());
//			+ "price => " + si.getPrice());
			
		}//if (si == null)
		
	}//_debug_D_44_V_3_0_3_TestMethod_getSI_FromNameAndStore()


	private void
	_debug_D_44_V_3_0_2_TestMethod_getSI_FromDbId() {
		// TODO Auto-generated method stub
		
		ShoppingItem si = Methods_sl.getSI_FromDbId(this, 100);
		
		// Log
		Log.d("[" + "MainActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"id=3 => " + si.getName()
					+ "(" + si.getCreated_at()
					+ " / "
					+ "price="
					+ si.getPrice()
					+ ")"
					);
		
//		List<String> names = Methods.get_ColumnNames(this, CONS.tableName);
//		
//		for (String name : names) {
//			
//			// Log
//			Log.d("["
//					+ "MainActv.java : "
//					+ +Thread.currentThread().getStackTrace()[2]
//							.getLineNumber() + " : "
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "name=" + name);
//			
//		}
		
	}//_debug_D_44_V_3_0_2_TestMethod_getSI_FromDbId()


	private void _debug_1_d_44() {
		// TODO Auto-generated method stub
		DBUtils dbu = new DBUtils(this, CONS.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		wdb.close();
	}


	private void _debug_1_d_44_seg_5() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String[] sqls = CONS.SQLs.a_20140105_113651_ChangeTableName_shopping_item_new;
//		String[] sqls = CONS.SQLs.a_20140108_094802_CreateTable_shopping_item_new;
//		String[] sqls = CONS.SQLs.a_20140105_113651_ChangeTableName_shopping_item_new;
/*
//		String[] sqls = CONS.SQLs.a_20140105_113308_DropTable_shopping_item;
//		String[] sqls = CONS.SQLs.a_20140105_112910_AddColumn_created_at;
//		String[] sqls = CONS.SQLs.a_20140105_112211_DropTable_shopping_item_new;
//		String[] sqls = CONS.SQLs.a_20140105_104744_create_table_shopping_item_new;
//		String[] sqls = CONS.SQLs.a_20140105_102851_add_column_created_at_etc;
//		String sql = CONS.SQLs.a_20140105_102851_add_column;
*/
		DBUtils dbu = new DBUtils(this, CONS.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		int count = 0;
		
		for (String sql : sqls) {
			
			// Log
			Log.d("["
					+ "MainActv.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "sql=" + sql);
			
			try {
				wdb.execSQL(sql);
				
				count += 1;
				
				// debug
				
//				Toast.makeText(this, "debug done", Toast.LENGTH_LONG).show();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				Toast.makeText(this, "SQLException", Toast.LENGTH_LONG).show();
				e.printStackTrace();
				
			}
			
		}
		
		Toast.makeText(this,
				"SQL done => "
				+ String.valueOf(count)
				+ " out of "
				+ String.valueOf(sqls.length),
				Toast.LENGTH_LONG).show();
		
		wdb.close();

	}//private void _debug_1_d_44_seg_5()


	private void admin_createTable_purchaseSchedule() {
		// TODO Auto-generated method stub
		
		boolean res =
				Methods.createTable(
							this,
							CONS.dbName,
							CONS.DBAdmin.tname_purchaseSchedule,
							CONS.DBAdmin.col_purchaseSchedule,
							CONS.DBAdmin.colTypes_purchaseSchedule);
		
		if (res == true) {
			
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Table created => " + CONS.DBAdmin.tname_purchaseSchedule);
			
		} else {//if (res == true)
			
			// Log
			Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"Table creation failed => " + CONS.DBAdmin.tname_purchaseSchedule);
			
		}//if (res == true)
		
	}//private void admin_createTable_purchaseSchedule()


	private void debug_B13_v_1_0() {
		
		File f = new File(CONS.dirPath_db);
		
		String[] fileNames = f.list();

		// Log
		Log.d("SLActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "f.exists() => " + f.exists());
	
		if (fileNames != null) {
		
			// Log
			Log.d("SLActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "fileNames.length=" + fileNames.length);
			
			for (String name : fileNames) {
				
				// Log
				Log.d("SLActv.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "name=" + name);
				
			}//for (String name : fileNames)
			
		} else {//if (fileNames != null)
			
			// Log
			Log.d("SLActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "fileNames == null");
			
		}//if (fileNames != null)
		
		/*********************************
		 * Create a db file
		 *********************************/
		DBUtils dbm = new DBUtils(this, CONS.dbName);
		
		SQLiteDatabase db = dbm.getWritableDatabase();
		
		db.close();
		
		// Log
		Log.d("SLActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "db => Done");
		
		// Log
		Log.d("SLActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "f.exists() => " + f.exists());
		

		/*********************************
		 * Restore db
		 *********************************/
		String srcFileName = Methods.getFileNameFromDir_latest(
										this, CONS.dirPath_db_backup);
		
		String src = StringUtils.join(
							new String[]{CONS.dirPath_db_backup,
									srcFileName},
							File.separator);
		
		String dst = StringUtils.join(
							new String[]{
									CONS.dirPath_db,
									CONS.dbName},
							File.separator);
//		String dst = CONS.dirPath_db;
		
		Methods.restore_db(this, CONS.dbName, src, dst);
		
//		for (String name : fileNames) {
//			
//			// Log
//			Log.d("SLActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "name=" + name);
//			
//		}//for (String name : fileNames)
		
	}


	private void debug_restore_db() {
		/*********************************
		 * memo
		 *********************************/
		String src = StringUtils.join(
				new String[]{CONS.dirPath_db_backup,
//						"shoppinglist_backup_20121108_122426.bk"},
						"shoppinglist_backup_20130905_004749.bk"},
				File.separator);
		
		String dst = StringUtils.join(
				new String[]{CONS.dirPath_db,
						CONS.dbName},
				File.separator);
		
		// Restore db
		Methods.restore_db(this, CONS.dbName, src, dst);
		
		
		File f = new File(CONS.dirPath_db);
		
		String[] f_list = f.list();
		
		for (String fname : f_list) {
			
			// Log
			Log.d("SLActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "fname=" + fname);
			
		}
		
	}//private void debug_restore_db()

	private void backup_db() {
		/*----------------------------
		 * memo
			----------------------------*/
//		String dirName_ExternalStorage = "/mnt/sdcard-ext";
		
//		String dirPath_db_backup = dirName_ExternalStorage + "/ShoppingList_backup";

//		String dirPath_db = "/data/data/shoppinglist.main/databases";
		
//		String fileName_db_backup_trunk = "shoppinglist_backup";
//		String fileName_db_backup_ext = ".bk";

		
		
		String time_label = Methods.get_TimeLabel(Methods.getMillSeconds_now());
		
		String db_src = StringUtils.join(
							new String[]{
								CONS.dirPath_db,
								CONS.dbName},
							File.separator);
		
		String db_dst = StringUtils.join(
							new String[]{
								CONS.dirPath_db_backup,
								CONS.fileName_db_backup_trunk},
							File.separator);
		
		db_dst = db_dst + "_" + time_label + CONS.fileName_db_backup_ext;
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", 
				"db_src: " + db_src + " * " + "db_dst: " + db_dst);
//				"db_dst: " + db_dst);
		
//		/*----------------------------
//		 * 2. Prep => Files
//			----------------------------*/
//		File src = new File(db_src);
//		File dst = new File(db_dst);
//		
//		/*----------------------------
//		 * 2-2. Folder exists?
//			----------------------------*/
//		File db_backup = new File(CONS.dirPath_db_backup);
//		
//		if (!db_backup.exists()) {
//			
//			try {
//				db_backup.mkdir();
//				
//				// Log
//				Log.d("Methods.java" + "["
//						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//						+ "]", "Folder created: " + db_backup.getAbsolutePath());
//			} catch (Exception e) {
//				
//				// Log
//				Log.d("Methods.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", "Create folder => Failed");
//				
//				return;
//				
//			}
//			
//		} else {//if (!db_backup.exists())
//			
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Folder exists: ");
//			
//		}//if (!db_backup.exists())
//		
//		/*----------------------------
//		 * 3. Copy
//			----------------------------*/
//		try {
//			FileChannel iChannel = new FileInputStream(src).getChannel();
//			FileChannel oChannel = new FileOutputStream(dst).getChannel();
//			iChannel.transferTo(0, iChannel.size(), oChannel);
//			iChannel.close();
//			oChannel.close();
//			
//			// Log
//			Log.d("ThumbnailActivity.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "File copied");
//			
//			// debug
//			Toast.makeText(this, "DB backup => Done", 3000).show();
//
//		} catch (FileNotFoundException e) {
//			// Log
//			Log.d("MainActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Exception: " + e.toString());
//			
//		} catch (IOException e) {
//			// Log
//			Log.d("MainActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Exception: " + e.toString());
//		}//try

		

		
//		Methods.db_backup(this);
//		
//		DBManager dbm = new DBManager(this);
//		
//		SQLiteDatabase db = dbm.getWritableDatabase();
//
//		// Log
//		Log.d("ShoppingListActivity.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "db.getPath(): " + db.getPath());
//		
//		db.close();
		
		
	}//private void backup_db()

	private void add_listeners() {
		/*----------------------------
		 * Steps
		 * 1. tv_list
		 * 2. tv_register
		 * 3. tv_db
			----------------------------*/
		
		// 
		TextView tv_list = (TextView) findViewById(R.id.sl_tv_item_list);
		
		
		
		tv_list.setTag(Tags.ButtonTags.sl_main_bt_item_list);
		
		tv_list.setOnTouchListener(new ButtonOnTouchListener(this));
		tv_list.setOnClickListener(new ButtonOnClickListener(this));
		
		/*----------------------------
		 * 2. tv_register
			----------------------------*/
		TextView tv_register = (TextView) findViewById(R.id.sl_tv_register);
		
		tv_register.setTag(Tags.ButtonTags.sl_main_bt_register);
		
		tv_register.setOnTouchListener(new ButtonOnTouchListener(this));
		tv_register.setOnClickListener(new ButtonOnClickListener(this));
		
//		/*----------------------------
//		 * 3. tv_db
//			----------------------------*/
//		TextView tv_db = (TextView) findViewById(R.id.sl_tv_db);
//		
//		tv_db.setTag(Tags.ButtonTags.sl_main_bt_db);
//		
//		tv_db.setOnTouchListener(new ButtonOnTouchListener(this));
//		tv_db.setOnClickListener(new ButtonOnClickListener(this));
		
//		Methods.setOnTouchListener_button(this, Methods.ViewNames.TV, 
//				Tags.ButtonTags.sl_main_bt_item_list, R.id.sl_tv_item_list);
//		
//		Methods.setOnTouchListener_button(this, Methods.ViewNames.TV, 
//				Tags.ButtonTags.sl_main_bt_register, R.id.sl_tv_register);
//		
//		Methods.setOnTouchListener_button(this, Methods.ViewNames.TV, 
//				Tags.ButtonTags.sl_main_bt_db, R.id.sl_tv_db);
		
		
	}//private void add_listeners()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.menu_main, menu);

		
		
		return super.onCreateOptionsMenu(menu);
	}//public boolean onCreateOptionsMenu(Menu menu)

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
//		// Log
//		Log.d("MainActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "Options");
		
		switch (item.getItemId()) {
				
			case R.id.v1_menu_db_manager:
				
				// Log
				Log.d("MainActv.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "db_manager");
				
				Methods_dlg.dlg_db_activity(this);
				
				break;
				
			case R.id.menu_main_tab_actv:
				
				Intent i = new Intent();
				
				i = new Intent();
				
				i.setClass(this, TabActv.class);
				
				i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				
				// Log
				Log.d("["
						+ "MainActv.java : "
						+ +Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ " : "
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "Starting TabActv");
				
				startActivity(i);

				break;

			case R.id.menu_main_pref_actv:
				
				i = new Intent();
				
				i = new Intent();
				
				i.setClass(this, PrefActv.class);
				
				i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				
				startActivity(i);

				break;// case R.id.menu_main_pref_actv
				
			default:
				break;
					
		}//switch (item.getItemId())
		
		return super.onOptionsItemSelected(item);
	}//public boolean onOptionsItemSelected(MenuItem item)

	@Override
	protected void onPause() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onResume();
		
		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Resume");
		
//		/***************************************
//		 * Preference values: BGM
//		 ***************************************/
//		SharedPreferences prefs = this
//				.getSharedPreferences(
//					this.getString(R.string.shared_preferences_name),
//					Context.MODE_PRIVATE);
//
//		boolean bgm = prefs.getBoolean(this.getString(R.string.prefs_key_bgm), false);
//		
//		// Log
//		Log.d("MainActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "bgm=" + bgm);
//		 
//		/***************************************
//		 * Play sound
//		 ***************************************/
//		if (bgm == true) {
//			
////			Methods_sl.playSound(this);
//			TaskAudioTrack task = new TaskAudioTrack(this);
//			
//			task.execute("Start");
//			
//		}//if (bgm == true)
		
	}//protected void onResume()

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
    
    
    
}//public class MainActv extends Activity
