package sl3.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import sl3.items.Genre;
import sl3.items.PH;
import sl3.items.PS;
import sl3.items.SI;
import sl3.items.Store;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBUtils extends SQLiteOpenHelper {
//	// Name
//	public static final String name = "shopping_list.db";
//	
//	// Version
//	static final int version = 1;
//	
//	// Factory
//	static final CursorFactory factory = null;

	//
	Context context;

	Activity actv;
	
	String dbName;
	//
//	public static String tableName = "shopping_item";
//
//	public static String[] columns = {"store", "name", "price", "genre"};
//	public static String[] columns_with_index = 
//					{"store", "name", "price", "genre", android.provider.BaseColumns._ID};
//
//	public static String[] columns_for_table_stores = 
//					{"store_name", "memo"};
//	
//	public static String[] columns_for_table_stores_with_index = 
//		{android.provider.BaseColumns._ID, "store_name", "memo"};
//
//	public static String[] column_types_for_table_stores = 
//																	{"TEXT", "TEXT"};
//	
//	public static String[] columns_for_table_genres = 
//					{"genre_name", "memo"};
//
//	
//	public static String[] columns_for_table_genres_with_index = 
//		{android.provider.BaseColumns._ID, "genre_name", "memo"};
//
//	public static String[] column_types_for_table_genres = 
//																	{"TEXT", "TEXT"};
//	

	// DB name => Use default: CONS.DBAdmin.dbName
	public DBUtils(Context context) {
		super(context, CONS.DB.dbName, CONS.factory, CONS.version);
		// 
		this.context = context;
		
//		// Log
//		Log.d("DBUtils.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "DBManager => Instance");
		
	}//public DBManager(Context context)

	// DB name => From parameter
	public DBUtils(Context context, String dbName) {
		// TODO Auto-generated constructor stub
		super(context, dbName, CONS.factory, CONS.version);
		
		this.context = context;;
		
		this.dbName = dbName;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onCreate()");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	public boolean tableExists(SQLiteDatabase db, String tableName) {
		// The table exists?
		Cursor cursor = db.rawQuery(
									"SELECT * FROM sqlite_master WHERE tbl_name = '" + 
									tableName + "'", null);
		
		((Activity) context).startManagingCursor(cursor);
		
		// Judge
		if (cursor.getCount() > 0) {
			return true;
		} else {//if (cursor.getCount() > 0)
			return false;
		}//if (cursor.getCount() > 0)
	}//public boolean tableExists(String tableName)

	public boolean createTable(SQLiteDatabase db, String tableName) {
		//
//		if (!tableExists(db, tableName)) {
		if (tableExists(db, tableName)) {
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists => " + tableName);
			
			return false;
		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		//
		String sql = "CREATE TABLE " + tableName + " ("
				+ android.provider.BaseColumns._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"store TEXT, name TEXT, price INTEGER, genre TEXT);";
		
		//
		try {
			db.execSQL(sql);
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table created => " + tableName);
			
			
			return true;
		} catch (SQLException e) {
			// Log
			Log.d("MemoDBHelper.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			return false;
		}//try
		
	}//public boolean createTable(SQLiteDatabase db, String tableName)

	/****************************************
	 * createTable_generic()
	 * 
	 * <Caller> 
	 * 1. 
	 * 
	 * <Desc> 1. <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	public boolean createTable_generic(
					SQLiteDatabase db, String tableName, String[] columns, String[] types) {
		/*----------------------------
		 * Steps
		 * 1. Table exists?
		 * 2. Build sql
		 * 3. Exec sql
			----------------------------*/
		
		//
//		if (!tableExists(db, tableName)) {
		if (tableExists(db, tableName)) {
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists => " + tableName);
			
			return false;
		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		/*----------------------------
		 * 2. Build sql
			----------------------------*/
		//
		StringBuilder sb = new StringBuilder();
		
		sb.append("CREATE TABLE " + tableName + " (");
		sb.append(android.provider.BaseColumns._ID +
							" INTEGER PRIMARY KEY AUTOINCREMENT, ");
		
		int i = 0;
		for (i = 0; i < columns.length - 1; i++) {
			sb.append(columns[i] + " " + types[i] + ", ");
		}//for (int i = 0; i < columns.length - 1; i++)
		
		sb.append(columns[i] + " " + types[i]);
		
		sb.append(");");
		
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "sql => " + sb.toString());
		
//		return false;
		
//		String sql = "CREATE TABLE " + tableName + " ("
//				+ android.provider.BaseColumns._ID
//				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//				"store TEXT, name TEXT, price INTEGER, genre TEXT);";
//		
		/*----------------------------
		 * 3. Exec sql
			----------------------------*/
		//
		try {
//			db.execSQL(sql);
			db.execSQL(sb.toString());
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table created => " + tableName);
			
			
			return true;
		} catch (SQLException e) {
			// Log
			Log.d("MemoDBHelper.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			return false;
		}//try
		
	}//public boolean createTable_generic(SQLiteDatabase db, String tableName)

	public static boolean
	insert_Data
	(Activity actv, String tableName, ContentValues cv) {
		
		long new_id;
		
		boolean res;
		
		////////////////////////////////

		// db

		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		////////////////////////////////

		// insertion

		////////////////////////////////
		try {
			//
			wdb.beginTransaction();
			
			//
//			ContentValues cv = new ContentValues();
//			
//			// Put values
//			for (int i = 0; i < cols.length; i++) {
//				cv.put(cols[i], values[i]);
//			}//for (int i = 0; i < columnNames.length; i++)

			// Insert data
			new_id = wdb.insert(tableName, null, cv);
			
			if (new_id > 0) {
				
				// Set as successful
				wdb.setTransactionSuccessful();
				
				Log.d("DBUtils.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "data => inserted: id = " + new_id);
				
//				// End transaction
//				wdb.endTransaction();
				
				
				res = true;
//				return true;
				
			} else {
				
				Log.d("DBUtils.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "data => not inserted: return = " + new_id);
				
//				// End transaction
//				wdb.endTransaction();
				
				res = false;
//				return false;
				
			}
			
		} catch (Exception e) {
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			res = false;
//			return false;
		} finally {//try

			// End transaction
			wdb.endTransaction();

		}//try
		
		wdb.close();
		
		return res;
		
	}//insert_Data

	public boolean storeData(SQLiteDatabase db, String tableName, String[] cols, String[] values) {
		try {
			//
			db.beginTransaction();
			
			//
			ContentValues cv = new ContentValues();
			
			// Put values
			for (int i = 0; i < cols.length; i++) {
				cv.put(cols[i], values[i]);
			}//for (int i = 0; i < columnNames.length; i++)
			
			// Insert data
			db.insert(tableName, null, cv);
			
			// Set as successful
			db.setTransactionSuccessful();
			
			// End transaction
			db.endTransaction();
			
			// Log
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < cols.length; i++) {
				//
				sb.append(cols[i] + " => " + values[i] + "/");
				
			}//for (int i = 0; i < cols.length; i++)
			
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Stored => " + sb.toString());
			
			return true;
			
		} catch (Exception e) {
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			return false;
		}//try
		
	}//public boolean storeData(SQLiteDatabase db, String tableName, String[] cols, String[] values)
	
	public boolean 
	storeData_PS
	(String dbName, String tableName, PS ps) {
		
		try {
			
//			getWritableDatabase();
			SQLiteDatabase wdb = this.getWritableDatabase();
			
			ContentValues cv = storeData_PS__getContentValues(ps);
			
			//
			wdb.beginTransaction();
			
			// Insert data
			long res = wdb.insert(tableName, null, cv);

			if (res != -1) {

				// Set as successful
				wdb.setTransactionSuccessful();

				// End transaction
				wdb.endTransaction();

				wdb.close();
				
				// Log
				Log.d("DBUtils.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Data stored => " + ps.getStoreName());
				
				return true;
						
			} else {//if (res != -1)
				
				// Log
				Log.d("DBUtils.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Insertion => Failed: " + ps.getStoreName());

				wdb.close();
				
				return false;
				
			}//if (res != -1)
			
		} catch (Exception e) {
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			e.printStackTrace();
			
			return false;
		}//try
		
	}//public boolean storeData(SQLiteDatabase db, String tableName, String[] cols, String[] values)

	private ContentValues storeData_PS__getContentValues(PS ps) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		
		/***************************************
		 * Put values
		 ***************************************/
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",		// 1,2
//		"store_name", "due_date", "amount",	// 3,4,5 
//		"memo", "items"						// 6,7		
		
		cv.put(CONS.DB.col_Names_PS_full[1], Methods.get_TimeLabel());
		
		cv.put(CONS.DB.col_Names_PS_full[2], Methods.get_TimeLabel());
//		Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		cv.put(CONS.DB.col_Names_PS_full[3], ps.getStoreName());

		cv.put(CONS.DB.col_Names_PS_full[4], ps.getDueDate());

		cv.put(CONS.DB.col_Names_PS_full[5], ps.getAmount());
		
		cv.put(CONS.DB.col_Names_PS_full[6], ps.getMemo());

		cv.put(CONS.DB.col_Names_PS_full[7], ps.getItems());
		
		return cv;
		
	}//private ContentValues storeData_PS__getContentValues(PS ps)
	

	public boolean
	storeData_withTimeStamp
	(SQLiteDatabase db, String tableName,
			String[] cols, String[] values) {
		try {
			//
			db.beginTransaction();
			
			//
			ContentValues cv = new ContentValues();
			
			/***************************************
			 * Time stamps
			 ***************************************/
			// "created_at"
			cv.put(
					CONS.DB.timeStamps[0],
					Methods.getMillSeconds_now());

			// "modified_at"
			cv.put(
					CONS.DB.timeStamps[1],
					Methods.getMillSeconds_now());

			/***************************************
			 * Other values
			 ***************************************/
			// Put values
			for (int i = 0; i < cols.length; i++) {
				cv.put(cols[i], values[i]);
			}//for (int i = 0; i < columnNames.length; i++)

			// Insert data
			db.insert(tableName, null, cv);
			
			// Set as successful
			db.setTransactionSuccessful();

			// End transaction
			db.endTransaction();
			
			// Log
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < cols.length; i++) {
				//
				sb.append(cols[i] + " => " + values[i] + "/");
				
			}//for (int i = 0; i < cols.length; i++)
			
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Stored => " + sb.toString());
			
			return true;
			
		} catch (Exception e) {
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			return false;
		}//try
		
	}//storeData_withTimeStamp(SQLiteDatabase db, String tableName, String[] cols, String[] values)

	public Cursor getAllData(
					SQLiteDatabase db, String tableName, String[] cols) {
		
		////////////////////////////////

		// validate: table exists

		////////////////////////////////
		//
		Cursor cursor = db.query(tableName, cols, null, null, null, null, null);
		
		return cursor;
	}

	public boolean dropTable(Activity actv, SQLiteDatabase db, String tableName) {
		/*------------------------------
		 * The table exists?
		 *------------------------------*/
		// The table exists?
		boolean tempBool = tableExists(db, tableName);
		
		if (tempBool == true) {
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists: " + tableName);
			
		} else {//if (tempBool == true)
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist: " + tableName);
			
			return false;
		}

		/*------------------------------
		 * Drop the table
		 *------------------------------*/
		// Define the sql
        String sql 
             = "DROP TABLE " + tableName;
        
        // Execute
        try {
			db.execSQL(sql);
			
			// Vacuum
			db.execSQL("VACUUM");
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "The table dropped => " + tableName);
			
			// Return
			return true;
			
		} catch (SQLException e) {
			// TODO �����������ꂽ catch �u���b�N
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "DROP TABLE => failed (table=" + tableName + "): " + e.toString());
			
			// debug
			Toast.makeText(actv, 
						"DROP TABLE => failed(table=" + tableName, 
						3000).show();
			
			// Return
			return false;
		}//try

	}//public boolean dropTable(String tableName) 

	public static boolean dropTable(Activity actv, String dbName, String tableName) {
		
		/*********************************
		 * Setup DB
		 *********************************/
		DBUtils dbu = new DBUtils(actv, dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		
		/*------------------------------
		 * The table exists?
		 *------------------------------*/
		// The table exists?
		boolean tempBool = dbu.tableExists(wdb, tableName);
		
		if (tempBool == true) {
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists: " + tableName);
			
		} else {//if (tempBool == true)
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist: " + tableName);
			
			return false;
		}

		/***************************************
		 * Drop the table
		 ***************************************/
		// Define the sql
        String sql 
             = "DROP TABLE " + tableName;
        
        // Execute
        try {
			wdb.execSQL(sql);
			
			// Vacuum
			wdb.execSQL("VACUUM");
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "The table dropped => " + tableName);
			
			wdb.close();
			
			// Return
			return true;
			
		} catch (SQLException e) {
			// TODO �����������ꂽ catch �u���b�N
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "DROP TABLE => failed (table=" + tableName + "): " + e.toString());
			
			// debug
			Toast.makeText(actv, 
						"DROP TABLE => failed(table=" + tableName, 
						3000).show();

			wdb.close();

			// Return
			return false;
		}//try

	}//public boolean dropTable(String tableName) 

	public boolean updateData(Activity actv, SQLiteDatabase wdb, 
			String tableName, long dbId, String colName, String value) {
		/*----------------------------
		* Steps
		* 1. 
		----------------------------*/
		String sql = "UPDATE " + tableName + " SET " + 
					colName + "='" + value + "'"
//					+ " WHERE file_id = '" + dbId + "'";
					+ " WHERE " + android.provider.BaseColumns._ID
					+ " = '" + dbId + "'";
		
		try {
			
			wdb.execSQL(sql);
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "sql => Done: " + sql);
			
			//Methods.toastAndLog(actv, "Data updated", 2000);
			
			return true;
			
			
		} catch (SQLException e) {

			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Exception => " + e.toString() + " / " + "sql: " + sql);
			
			return false;
			
		}//try
		
	}//public void updateData_memos

	public boolean updateData(Activity actv, SQLiteDatabase wdb,
			String tableName, long dbId, String colName, long value) {
		/*----------------------------
		* Steps
		* 1. 
		----------------------------*/
		String sql = "UPDATE " + tableName + " SET " + 
					colName + "='" + value + "'"
//					+ " WHERE file_id = '" + dbId + "'";
					+ " WHERE " + android.provider.BaseColumns._ID
					+ " = '" + dbId + "'";
		
		try {
			
			wdb.execSQL(sql);
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "sql => Done: " + sql);
			
			//Methods.toastAndLog(actv, "Data updated", 2000);
			
			return true;
			
			
		} catch (SQLException e) {

			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Exception => " + e.toString() + " / " + "sql: " + sql);
			
			return false;
			
		}//try
		
	}//public boolean updateData()

	
	public int updateData_shoppingItem(Activity actv, SQLiteDatabase wdb,
			String tableName, long dbId, String colName, String value) {
		
		/*----------------------------
		* Steps
		* 1. 
		----------------------------*/
		String sql = "UPDATE " + tableName + " SET " + 
					colName + "='" + value + "'"
//					+ " WHERE file_id = '" + dbId + "'";
					+ " WHERE " + android.provider.BaseColumns._ID
					+ " = '" + dbId + "'";
		
		try {
			
			wdb.execSQL(sql);
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "sql => Done: " + sql);
			
			//Methods.toastAndLog(actv, "Data updated", 2000);
			
			return CONS.RV.DB_UPDATE_SUCCESSFUL;
			
			
		} catch (SQLException e) {

			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Exception => " + e.toString() + " / " + "sql: " + sql);
			
			return CONS.RV.EXCEPTION_SQL;
			
		}//try
		
	}//public int updateData_shoppingItem
	
	public boolean update_SI(
			Activity actv,
			SQLiteDatabase wdb,
			int id,
			SI si) {
		
		/*----------------------------
		 * Steps
		 * 1. 
		----------------------------*/
		/*
		//	0		1		2
		"store", "name", "price",
		//	3		4			5
		"genre", "yomi", android.provider.BaseColumns._ID, 
		//	6			7				8
		"created_at", "updated_at", "posted_at"
		*/
		String sql =
				"UPDATE " + CONS.DB.tableName
				+ " SET "
				+ CONS.DB.cols_SI_full[0] + "='" + si.getStore() + "'"
				
				+ " AND "
				+ CONS.DB.cols_SI_full[1] + "='" + si.getName() + "'"
				
				+ " AND "
				+ CONS.DB.cols_SI_full[2] + "='" + String.valueOf(si.getPrice()) + "'"
				
				+ " AND "
				+ CONS.DB.cols_SI_full[3] + "='" + si.getGenre() + "'"
				
				+ " AND "
				+ CONS.DB.cols_SI_full[4] + "='" + si.getYomi() + "'"
				
				+ " AND "
				+ CONS.DB.cols_SI_full[6] + "='" + String.valueOf(si.getCreated_at()) + "'"
				
				+ " AND "
				+ CONS.DB.cols_SI_full[7] + "='" + String.valueOf(si.getModified_at()) + "'"
				
				+ " AND "
				+ CONS.DB.cols_SI_full[8] + "='" + String.valueOf(si.getPosted_at()) + "'"
				
//					+ " WHERE file_id = '" + dbId + "'";
				+ " WHERE "
				+ android.provider.BaseColumns._ID + " = '" + id + "'";
		
//		// Log
//		Log.d("[" + "DBUtils.java : "
//				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ " : "
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "sql=" + sql);
		
		try {
			
			wdb.execSQL(sql);
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "sql => Done: " + sql);
			
			//Methods.toastAndLog(actv, "Data updated", 2000);
			
			return true;
			
			
		} catch (SQLException e) {
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Exception => " + e.toString() + " / " + "sql: " + sql);
			
			return false;
			
		}//try
		
	}//update_SI()

	public boolean createTable(
			SQLiteDatabase db, String tableName, String[] columns, String[] types) {
		/*----------------------------
		 * Steps
		 * 1. Table exists?
		 * 2. Build sql
		 * 3. Exec sql
			----------------------------*/
		
		//
		//if (!tableExists(db, tableName)) {
		if (tableExists(db, tableName)) {
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists => " + tableName);
			
			return false;
		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		/*----------------------------
		 * 2. Build sql
			----------------------------*/
		//
		StringBuilder sb = new StringBuilder();
		
		sb.append("CREATE TABLE " + tableName + " (");
		sb.append(android.provider.BaseColumns._ID +
							" INTEGER PRIMARY KEY AUTOINCREMENT, ");
		
		// created_at, modified_at
		sb.append("created_at INTEGER, modified_at INTEGER, ");
		
		int i = 0;
		for (i = 0; i < columns.length - 1; i++) {
			sb.append(columns[i] + " " + types[i] + ", ");
		}//for (int i = 0; i < columns.length - 1; i++)
		
		sb.append(columns[i] + " " + types[i]);
		
		sb.append(");");
		
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "sql => " + sb.toString());
		
		/*----------------------------
		 * 3. Exec sql
			----------------------------*/
		//
		try {
		//	db.execSQL(sql);
			db.execSQL(sb.toString());
			
			// Log
			Log.d(this.getClass().getName() + 
					"["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table created => " + tableName);
			
			
			return true;
		} catch (SQLException e) {
			// Log
			Log.d(this.getClass().getName() + 
					"[" + Thread.currentThread().getStackTrace()[2].getLineNumber() + "]", 
					"Exception => " + e.toString());
			
			return false;
		}//try
		
	}//public boolean createTable(SQLiteDatabase db, String tableName)

	
//	public List<PS> getPSList(Activity actv) {
//		
//		SQLiteDatabase rdb = this.getReadableDatabase();
//
//		Cursor c = null;
//		
//		try {
//			
//			c = rdb.query(
//							CONS.DB.tname_PS,
////							CONS.DBAdmin.col_purchaseSchedule,
//							CONS.DB.col_Names_PS_full,
//							null, null, null, null, null);
//			
//		} catch (Exception e) {
//
//			// Log
//			Log.e("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", e.toString());
//			
//			rdb.close();
//			
//			return null;
//			
//		}//try
//		
//		/***************************************
//		 * Validate
//		 * 	Cursor => Null?
//		 * 	Entry => 0?
//		 ***************************************/
//		if (c == null) {
//			
//			// Log
//			Log.e("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "Query failed");
//			
//			rdb.close();
//			
//			return null;
//			
//		} else if (c.getCount() < 1) {//if (c == null)
//			
//			// Log
//			Log.d("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "No entry in the table");
//			
//			rdb.close();
//			
//			return null;
//			
//		}//if (c == null)
//		
//		/***************************************
//		 * Build list
//		 ***************************************/
//		c.moveToFirst();
//		
//		List<PS> psList = new ArrayList<PS>();
//		
//		for (int i = 0; i < c.getCount(); i++) {
//			
//			PS ps = new PS();
//			
//			ps.setDbId(c.getLong(
//							c.getColumnIndex(CONS.DB.col_Names_PS_full[0])));
//			
//			ps.setStoreName(c.getString(c.getColumnIndex("store_name")));
////			ps.setDueDate(c.getInt(c.getColumnIndex("due_date")));
//			ps.setDueDate(c.getLong(c.getColumnIndex("due_date")));
//			ps.setAmount(c.getInt(c.getColumnIndex("amount")));
//			ps.setMemo(c.getString(c.getColumnIndex("memo")));
//			ps.setItems(c.getString(c.getColumnIndex("items")));
//			
//			psList.add(ps);
//			
////			// Log
////			Log.d("DBUtils.java" + "["
////					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////					+ ":"
////					+ Thread.currentThread().getStackTrace()[2].getMethodName()
////					+ "]",
////					"ColumnIndex(\"due_date\")="
////					+ c.getColumnIndex("due_date")
////					+ "/"
//////					+ "c.getInt(c.getColumnIndex(\"due_date\"))="
////					+ "c.getLong(c.getColumnIndex(\"due_date\"))="
//////					+ c.getInt(c.getColumnIndex("due_date")));
////					+ c.getLong(c.getColumnIndex("due_date")));
//			
//			c.moveToNext();
//			
//		}//for (int i = 0; i < c.getCount(); i++)
//		
//		return psList;
//		
//	}//public List<PS> getPSList(Activity actv)

	public static SI
	find_SI_from_DB_Id
	(Activity actv, String dbId) {
		// TODO Auto-generated method stub
		
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "dbId=" + dbId);
	
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		String where = CONS.DB.col_Names_SI_full[0] + " = ?";
		String[] args = new String[]{String.valueOf(dbId)};
		
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
		try {
			
			c = rdb.query(
					
					CONS.DB.tname_si,			// 1
					CONS.DB.col_Names_SI_full,	// 2
//					null, null,		// 3,4
					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					null);
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			e.printStackTrace();
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Query failed");
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		// Log
		String msg_Log = "c.getCount() => " + c.getCount();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		/***************************************
		 * Build item
		 ***************************************/
		c.moveToFirst();
		
//		android.provider.BaseColumns._ID,	// 0
//		"store", 							// 1
//		"name", "price",					// 2,3
//		"genre", "yomi",					// 4,5
//		"created_at", "updated_at",			// 6,7
//		"posted_at"							// 8
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		"store", "name", "price",			// 3,4,5
//		"genre", "yomi", "num",				// 6,7,8
//		"posted_at"							// 9
			
		SI si = new SI.Builder()
			
				.setDb_id(c.getInt(0))
				.setCreated_at(c.getString(1))
				.setModified_at(c.getString(2))
				
				.setStore(c.getString(3))
				.setName(c.getString(4))
				.setPrice(c.getInt(5))
				
				.setGenre(c.getString(6))
				.setYomi(c.getString(7))
				.setNum(c.getInt(8))
				
				.setPosted_at(c.getString(9))
				
				.build();
			
//		SI si = new SI();
//		
//		si.setId((int)cursor.getLong(cursor.getColumnIndex(CONS.DB.columns_with_index2[0])));
//		si.setStore(cursor.getString(cursor.getColumnIndex("store")));
//		si.setName(cursor.getString(cursor.getColumnIndex("name")));
//		si.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
//		si.setGenre(cursor.getString(cursor.getColumnIndex("genre")));
//		si.setYomi(cursor.getString(cursor.getColumnIndex("yomi")));
		
		/***************************************
		 * Close db
		 ***************************************/
		rdb.close();
		
		/***************************************
		 * Return
		 ***************************************/
		return si;
		
	}//public ShoppingItem getSIFromDbId(String dbId)
	
	
	public SI getSI_FromItemName(String itemName) {
		// TODO Auto-generated method stub
		
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "itemName=" + itemName);
		
		SQLiteDatabase rdb = this.getReadableDatabase();
		
		String sql = "SELECT " + "*"
				+ " FROM " + CONS.DB.tableName
				+ " WHERE " + CONS.DB.columns_with_index2[1]
						+ " = "
						+ itemName;
		
		Cursor cursor = null;
		
		try {
			
			cursor = rdb.rawQuery(sql, null);
			
		} catch (Exception e) {
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			rdb.close();
			
			return null;
			
		}
		
		if (cursor == null) {
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "cursor => null");
			
			return null;
			
		}//if (cursor == null)
		
		/***************************************
		 * Build item
		 ***************************************/
		cursor.moveToFirst();
		
		SI si = new SI();
		
		si.setId((int)cursor.getLong(cursor.getColumnIndex(CONS.DB.columns_with_index2[0])));
		si.setStore(cursor.getString(cursor.getColumnIndex("store")));
		si.setName(cursor.getString(cursor.getColumnIndex("name")));
		si.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
		si.setGenre(cursor.getString(cursor.getColumnIndex("genre")));
		si.setYomi(cursor.getString(cursor.getColumnIndex("yomi")));
		
		/***************************************
		 * Close db
		 ***************************************/
		rdb.close();
		
		/***************************************
		 * Return
		 ***************************************/
		return si;
		
	}//public ShoppingItem getSIFromDbId(String dbId)

	
	public boolean
	updateData_PS_ItemIds
	(Activity actv, 
		String storeName, long dueDate, String itemIdsString) {
		// TODO Auto-generated method stub

		/***************************************
		 * Get db id
		 ***************************************/
		long dbId = this.getDbId_PS(actv, storeName, dueDate);

		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "dbId=" + dbId);
		
		////////////////////////////////

		// cv

		////////////////////////////////
		ContentValues cv = new ContentValues();
		
//		0			1				2		3		4
//		"store_name", "due_date", "amount", "memo", "items"
		
		cv.put(CONS.DB.col_Names_PS[4], itemIdsString);
		
		////////////////////////////////

		// Setup db

		////////////////////////////////
		SQLiteDatabase wdb = this.getWritableDatabase();
		
		try {
			//
			wdb.beginTransaction();
			
			// Insert data
			long res = wdb.update(
							CONS.DB.tname_PS,
							cv,
							android.provider.BaseColumns._ID + " = ?",
							new String[]{ String.valueOf(dbId) });
			
			if (res < 1) {
				
				// Log
				Log.d("DBUtils.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "Update => Returned less than 1");
				
				wdb.close();
				
				return false;
				
			}	
			
			// Set as successful
			wdb.setTransactionSuccessful();

			// End transaction
			wdb.endTransaction();

			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Update => Successful");
			
			wdb.close();
			
			return true;
			
		} catch (Exception e) {
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			wdb.close();
			
			return false;
			
		}//try

		
//		String tname = CONS.DB.tname_PS;
////		String colName = CONS.DBAdmin.col_purchaseSchedule[4];
//		
//		String sql = "UPDATE " + tname
//					+ " SET "
//					+ CONS.DB.col_Names_PS[4] + "='"	// items
//						+ itemIdsString + "'"
//					+ " WHERE " + android.provider.BaseColumns._ID
//					+ " = '" + dbId + "'";
//	
//		SQLiteDatabase wdb = this.getWritableDatabase();
//		
//		try {
//			
//			wdb.execSQL(sql);
//			
//			// Log
//			Log.d("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "sql => Done: " + sql);
//			
//			//Methods.toastAndLog(actv, "Data updated", 2000);
//			
//			return true;
//			
//			
//		} catch (SQLException e) {
//	
//			// Log
//			Log.d("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]",
//					"Exception => " + e.toString() + " / " + "sql: " + sql);
//			
//			return false;
//			
//		}//try

	}//updateData_PS_ItemIds

	/***************************************
	 * 20130312_170156
	 * 
	 * If the table has more than one record, the method
	 * 		returns the earliest-saved one.
	 * @param actv 
	 * 
	 * @return
	 * 		-1 No ps found<br>
	 * 		1< ps id 
	 ***************************************/
	private long 
	getDbId_PS
	(Activity actv, String storeName, long dueDate) {
		// TODO Auto-generated method stub
		
		List<PS> list_PSs = DBUtils.find_ALL_PSs(actv);
		
		String time_Label = Methods.conv_MillSec_to_TimeLabel(dueDate);
		
		String[] YMD = Methods.conv_TimeLabel_to_YMD(time_Label);
		
		// Log
		String msg_Log = String.format("YMD => %s, %s, %s", YMD[0], YMD[1], YMD[2]);
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		// Log
		msg_Log = "storeName => " + storeName;
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// iteration

		////////////////////////////////
		String[] YMD_tmp = null;
		
		for (PS ps : list_PSs) {
			
			YMD_tmp = Methods.conv_TimeLabel_to_YMD(ps.getDueDate());
			
			// Log
			msg_Log = String.format(
							"YMD_tmp => %s, %s, %s (id = %d, store = %s)", 
							YMD_tmp[0], YMD_tmp[1], YMD_tmp[2],
							ps.getDbId(), ps.getStoreName());
			
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			////////////////////////////////

			// judge

			////////////////////////////////
			if (storeName.equals(ps.getStoreName())
					&& YMD[0].equals(YMD_tmp[0]) 
					&& YMD[1].equals(YMD_tmp[1])
					&& YMD[2].equals(YMD_tmp[2])) {
//				if (YMD[0] == YMD_tmp[0] && YMD[1] == YMD_tmp[1] && YMD[2] == YMD_tmp[2]) {
				
				return ps.getDbId();
				
			}
			
		}
		
		////////////////////////////////

		// no ps found

		////////////////////////////////
		return -1;
		
//		/***************************************
//		 * Prepare reference data
//		 ***************************************/
//		int[] referenceDueDateData = Methods.getDateArrayFromLongData(dueDate);
//		
//		/***************************************
//		 * Database
//		 ***************************************/
//		SQLiteDatabase rdb = getReadableDatabase();
//		
//		String sql = "SELECT * FROM " + CONS.DB.tname_PS;
//		
//		Cursor c = null;
//		
//		//
//		try {
//
//			c = rdb.rawQuery(sql, null);
//
//		} catch (SQLException e) {
//			
//			// Log
//			Log.d("MemoDBHelper.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Exception => " + e.toString());
//			
//			rdb.close();
//			
//			return CONS.DB.DB_QUERY_FAILED;
//			
//		}//try
//
//		/***************************************
//		 * Validate
//		 * 	Cursor => Null?
//		 * 	Entry => 0?
//		 ***************************************/
//		if (c == null) {
//			
//			// Log
//			Log.e("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "Query failed");
//			
//			rdb.close();
//			
//			return CONS.DB.DB_QUERY_FAILED;
//			
//		} else if (c.getCount() < 1) {//if (c == null)
//			
//			// Log
//			Log.d("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "No entry in the table");
//			
//			rdb.close();
//			
//			return CONS.DB.DB_QUERY_NO_ENTRY;
//			
//		}//if (c == null)
//
//		/***************************************
//		 * Search for the target item
//		 ***************************************/
//		c.moveToFirst();
//		
//		for (int i = 0; i < c.getCount(); i++) {
//			
//			long targetDueDate = c.getLong(
//					c.getColumnIndex(
//							CONS.DB.col_Names_PS[1]));
//	
//			int[] targetDueDateData = Methods.getDateArrayFromLongData(targetDueDate);
//
//			String targetStoreName = c.getString(
//					c.getColumnIndex(
//							CONS.DB.col_Names_PS[0]));
//
//			if (targetStoreName.equals(storeName)
//				&& referenceDueDateData[0] == targetDueDateData[0]
//				&& referenceDueDateData[1] == targetDueDateData[1]
//				&& referenceDueDateData[2] == targetDueDateData[2]) {
//				
//				rdb.close();
//				
//				return c.getLong(0);	// Return database id
//				
//			}//if (cal.YEAR == dueDateData[0])
//				
//			c.moveToNext();
//
//			
//		}//for (int i = 0; i < c.getCount(); i++)
//		
//		// Log
//		Log.d("DBUtils.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"No matching entry: Given params => "
//				+ "Store name=" + storeName + "/"
//				+ "Due date=" + Methods.getTimeLabel_Japanese(dueDate));
//
//		/***************************************
//		 * Close db
//		 ***************************************/
//		rdb.close();
//		
//		return CONS.DB.DB_QUERY_NO_ENTRY;
		
	}//private long getDbId_PS(String storeName, long dueDate)

	
	public boolean deleteItem(String tname, long dbId) {
		// TODO Auto-generated method stub
		/***************************************
		 * Get db
		 ***************************************/
		SQLiteDatabase wdb = this.getWritableDatabase();
		
		int res = -1;
		
		try {
			
			res = wdb.delete(
						tname,
						android.provider.BaseColumns._ID + " = ?",
						new String[]{String.valueOf(dbId)});
			
		} catch (Exception e) {
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			wdb.close();
			
			return false;
		}
		
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "res=" + res);
		
		wdb.close();
		
		return true;
		
	}//public boolean deleteItem(String tname, long dbId)

	public static boolean 
	delete_Item_static
	(Activity actv, String tname, long dbId) {
		// TODO Auto-generated method stub
		/***************************************
		 * Get db
		 ***************************************/
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		int res = -1;
		
		try {
			
			res = wdb.delete(
					tname,
					android.provider.BaseColumns._ID + " = ?",
					new String[]{String.valueOf(dbId)});
			
		} catch (Exception e) {
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			wdb.close();
			
			return false;
		}
		
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "res=" + res);
		
		wdb.close();
		
		return true;
		
	}//deleteItem_static
	
	public boolean updateData_SI_all(SI si) {
		// TODO Auto-generated method stub
		/***************************************
		 * Build value set
		 ***************************************/
		ContentValues cv = new ContentValues();
		
//		0			1		2		3		4
//		"store", "name", "price", "genre", "yomi"
		cv.put(CONS.DB.columns[0], si.getStore());
		cv.put(CONS.DB.columns[1], si.getName());
		cv.put(CONS.DB.columns[2], si.getPrice());
		cv.put(CONS.DB.columns[3], si.getGenre());
		cv.put(CONS.DB.columns[4], si.getYomi());
		
		/***************************************
		 * Setup db
		 ***************************************/
		SQLiteDatabase wdb = this.getWritableDatabase();
		
		try {
			//
			wdb.beginTransaction();
			
//			//
//			ContentValues cv = new ContentValues();
//			
//			// Put values
//			for (int i = 0; i < cols.length; i++) {
//				cv.put(cols[i], values[i]);
//			}//for (int i = 0; i < columnNames.length; i++)

			// Insert data
//			long res = wdb.insert(CONS.DBAdmin.tableName, null, cv);
			long res = wdb.update(
							CONS.DB.tableName,
							cv,
							android.provider.BaseColumns._ID + " = ?",
							new String[]{String.valueOf(si.getId())});
			
			if (res < 1) {
				
				// Log
				Log.d("DBUtils.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "Update => Returned less than 1");
				
				wdb.close();
				
				return false;
				
			}	
			
			// Set as successful
			wdb.setTransactionSuccessful();

			// End transaction
			wdb.endTransaction();

			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Update => Successful");
			
			wdb.close();
			
			return true;
			
		} catch (Exception e) {
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			wdb.close();
			
			return false;
			
		}//try
		
	}//public boolean updateData_SI_all(ShoppingItem si)
	/*********************************
	 * updateData_SI_all_V2(ShoppingItem si)
	 * 
	 * @return true => update successful<br>
	 * 	false =>
	 * 	<pre>1. Transaction unsuccessful
	 * 2. Exception</pre>
	 *********************************/
	public boolean updateData_SI_all_V2(SI si) {
		// TODO Auto-generated method stub
		/***************************************
		 * Build value set
		 ***************************************/
		ContentValues cv = new ContentValues();
		
		/*
		//	0		1		2
		"store", "name", "price",
		//	3		4			5
		"genre", "yomi", android.provider.BaseColumns._ID, 
		//	6			7				8
		"created_at", "updated_at", "posted_at"
		*/
		
		cv.put(CONS.DB.cols_SI_full[0], si.getStore());
		cv.put(CONS.DB.cols_SI_full[1], si.getName());
		cv.put(CONS.DB.cols_SI_full[2], si.getPrice());
		cv.put(CONS.DB.cols_SI_full[3], si.getGenre());
		cv.put(CONS.DB.cols_SI_full[4], si.getYomi());
		
		cv.put(CONS.DB.cols_SI_full[6], si.getCreated_at());
		cv.put(CONS.DB.cols_SI_full[7], si.getModified_at());
		cv.put(CONS.DB.cols_SI_full[8], si.getPosted_at());
		
		/***************************************
		 * Setup db
		 ***************************************/
		SQLiteDatabase wdb = this.getWritableDatabase();
		
		try {
			//
			wdb.beginTransaction();
			
//			//
//			ContentValues cv = new ContentValues();
//			
//			// Put values
//			for (int i = 0; i < cols.length; i++) {
//				cv.put(cols[i], values[i]);
//			}//for (int i = 0; i < columnNames.length; i++)
			
			// Insert data
//			long res = wdb.insert(CONS.DBAdmin.tableName, null, cv);
			long res = wdb.update(
					CONS.DB.tableName,
					cv,
					android.provider.BaseColumns._ID + " = ?",
					new String[]{String.valueOf(si.getId())});
			
			if (res < 1) {
				
				// Log
				Log.d("DBUtils.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
						"Update => Returned less than 1");
				
				wdb.close();
				
				return false;
				
			}	
			
			// Set as successful
			wdb.setTransactionSuccessful();
			
			// End transaction
			wdb.endTransaction();
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Update => Successful");
			
			wdb.close();
			
			return true;
			
		} catch (Exception e) {
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			wdb.close();
			
			return false;
			
		}//try
		
	}//public boolean updateData_SI_all_V2(ShoppingItem si)

	public static boolean 
	tableExists
	(Activity actv, String dbName, String tableName) {
		// The table exists?
		DBUtils dbu = new DBUtils(actv, dbName);
		
		//
		SQLiteDatabase rdb = dbu.getReadableDatabase();

		Cursor cursor = rdb.rawQuery(
				"SELECT * FROM sqlite_master WHERE tbl_name = '" + 
						tableName + "'", null);
		
		actv.startManagingCursor(cursor);
//		actv.startManagingCursor(cursor);
		
		// Judge
		if (cursor.getCount() > 0) {
		
			rdb.close();
			return true;
			
		} else {//if (cursor.getCount() > 0)
			
			rdb.close();
			return false;
			
		}//if (cursor.getCount() > 0)
		
	}//public boolean tableExists(String tableName)

	/******************************
		createTable()
		
		@param columns, types => use non-full version
		@return
				-1	Table exists<br>
				-2	Exception in executing the sql<br>
				1	Table created<br>
	 ******************************/
	public static int 
	createTable
	(Activity actv, 
		String dbName, String tableName, 
		String[] columns, String[] types) {
		/*----------------------------
		 * Steps
		 * 1. Table exists?
		 * 2. Build sql
		 * 3. Exec sql
			----------------------------*/
		DBUtils dbu = new DBUtils(actv, dbName);
		
		//
		SQLiteDatabase wdb = dbu.getWritableDatabase();
	
		////////////////////////////////
	
		// validate: table exists
	
		////////////////////////////////
		if (DBUtils.tableExists(actv, dbName, tableName)) {
			// Log
			Log.i("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists => " + tableName);
			
			wdb.close();
			
			return -1;
	
		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		////////////////////////////////
	
		// Build sql
	
		////////////////////////////////
		StringBuilder sb = new StringBuilder();
		
		sb.append("CREATE TABLE " + tableName + " (");
		sb.append(android.provider.BaseColumns._ID +
				" INTEGER PRIMARY KEY AUTOINCREMENT, ");
		
		// created_at, modified_at
		sb.append("created_at TEXT, modified_at TEXT, ");
	//	sb.append("created_at INTEGER, modified_at INTEGER, ");
		
		int i = 0;
		for (i = 0; i < columns.length - 1; i++) {
			sb.append(columns[i] + " " + types[i] + ", ");
		}//for (int i = 0; i < columns.length - 1; i++)
		
		sb.append(columns[i] + " " + types[i]);
		
		sb.append(");");
		
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "sql => " + sb.toString());
		
		////////////////////////////////
	
		// Exec sql
	
		////////////////////////////////
		try {
			//	db.execSQL(sql);
			wdb.execSQL(sb.toString());
			
			// Log
			Log.d(actv.getClass().getName() + 
					"["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table created => " + tableName);
			
			wdb.close();
			
			return 1;
			
		} catch (SQLException e) {
			
			// Log
			Log.e(actv.getClass().getName() + 
					"[" + Thread.currentThread().getStackTrace()[2].getLineNumber() + "]", 
					"Exception => " + e.toString());
			
			wdb.close();
			
			return -2;
			
		}//try
		
	}//createTable_static

	public static boolean 
	db_Exists
	(Activity actv, String dbName) {
		// TODO Auto-generated method stub
		
		String dpath_DB = actv.getDatabasePath(CONS.DB.dbName).getPath();
		
		String dpath = Methods.get_Dirname(actv, dpath_DB);
		
		File db = new File(dpath, CONS.DB.dbName_SL_1);
		
		return db.exists();
		
	}

	/******************************
		@return
		null<br>
			1. No DB file<br>
			2. No such table<br>
			3. Query => Exception<br>
			4. Query => no entry<br>
	 ******************************/
	public static List<SI> 
	find_ALL_SI_from_Previous
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// validate: DB file exists?

		////////////////////////////////
		String dbName = CONS.DB.dbName_Importing;
		
		File dpath_DBFile = actv.getDatabasePath(dbName);

		if (!dpath_DBFile.exists()) {
			
			String msg = "No DB file: " + dbName;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			return null;
			
		}
		
		////////////////////////////////

		// DB

		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName_Importing);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		////////////////////////////////
		
		// validate: table exists?
		
		////////////////////////////////
		String tname = CONS.DB.tname_si;
		boolean res = dbu.tableExists(rdb, tname);

		if (res == false) {
			
			String msg = "No such table: " + tname;

			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			rdb.close();
			
			return null;
			
		}

		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
		try {
			
			c = rdb.query(
					
					CONS.DB.tname_si,			// 1
					CONS.DB.col_Names_SI_full_SL_1,	// 2
					null, null,		// 3,4
//					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					null);
			
		} catch (Exception e) {

			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Query failed");
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
			rdb.close();
			
			return null;
			
		}//if (c == null)

		// Log
		String msg_Log = "c.getCount() => " + c.getCount();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// build list

		////////////////////////////////
		List<SI> list_SIs = new ArrayList<SI>();
		
//		android.provider.BaseColumns._ID,	// 0
//		"store", 							// 1
//		"name", "price",					// 2,3
//		"genre", "yomi",					// 4,5
//		"created_at", "updated_at",			// 6,7
//		"posted_at"							// 8
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		"store", "name", "price",			// 3,4,5
//		"genre", "yomi", "num",				// 6,7,8
//		"posted_at"							// 9


		SI si = null;
		
		while(c.moveToNext()) {
			
			si = new SI.Builder()
					
					.setDb_id(c.getInt(0))
					.setCreated_at(c.getString(6))
					.setModified_at(c.getString(7))
					
					.setStore(c.getString(1))
					.setName(c.getString(2))
					.setPrice(c.getInt(3))
					
					.setGenre(c.getString(4))
					.setYomi(c.getString(5))
					
					.setPosted_at(c.getString(8))
					
					.build();
			
			list_SIs.add(si);
			
		}//while(c.moveToNext())
		
		////////////////////////////////

		// close

		////////////////////////////////
		rdb.close();
		
		// Log
		msg_Log = "list_SIs.size() => " + list_SIs.size();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		return list_SIs;
		
	}//find_ALL_SI_from_Previous

	/******************************
		@return
		null<br>
			1. No DB file<br>
			2. No such table<br>
			3. Query => Exception<br>
			4. Query => no entry<br>
	 ******************************/
	public static List<PS> 
	find_ALL_PSs
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// validate: DB file exists?
		
		////////////////////////////////
		String dbName = CONS.DB.dbName;
		
		File dpath_DBFile = actv.getDatabasePath(dbName);
		
		if (!dpath_DBFile.exists()) {
			
			String msg = "No DB file: " + dbName;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			return null;
			
		}
		
		////////////////////////////////
		
		// DB
		
		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		////////////////////////////////
		
		// validate: table exists?
		
		////////////////////////////////
		String tname = CONS.DB.tname_PS;
		boolean res = dbu.tableExists(rdb, tname);
		
		if (res == false) {
			
			String msg = "No such table: " + tname;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			rdb.close();
			
			return null;
			
		}
		
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
		try {
			
			c = rdb.query(
					
					CONS.DB.tname_PS,			// 1
					CONS.DB.col_Names_PS_full,	// 2
					null, null,		// 3,4
//					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					null);
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Query failed");
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		// Log
		String msg_Log = "c.getCount() => " + c.getCount();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// build list
		
		////////////////////////////////
		List<PS> list_PSs = new ArrayList<PS>();

////		0				1			2		3		4
//		{android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",		// 1,2
//		"store_name", "due_date", "amount",	// 3,4,5
//		"memo", "items"						// 6,7
		
		PS ps = null;
		
		while(c.moveToNext()) {
			
			ps = new PS.Builder()
			
						.setDbId(c.getInt(0))
						.setCreated_at(c.getString(1))
						.setModified_at(c.getString(2))
						
						.setStoreName(c.getString(3))
						.setDueDate(c.getString(4))
						.setAmount(c.getInt(5))
						
						.setMemo(c.getString(6))
						.setItems(c.getString(7))
						
						.build();
			
			list_PSs.add(ps);
			
		}//while(c.moveToNext())
			
		////////////////////////////////
		
		// close
		
		////////////////////////////////
		rdb.close();
		
		msg_Log = "list_PSs.size() => " + list_PSs.size();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		return list_PSs;
		
	}//find_ALL_PSs
	
	/******************************
		@return
		null<br>
			1. No DB file<br>
			2. No such table<br>
			3. Query => Exception<br>
			4. Query => no entry<br>
	 ******************************/
	public static List<SI> 
	find_ALL_SIs
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// validate: DB file exists?
		
		////////////////////////////////
		String dbName = CONS.DB.dbName;
		
		File dpath_DBFile = actv.getDatabasePath(dbName);
		
		if (!dpath_DBFile.exists()) {
			
			String msg = "No DB file: " + dbName;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			return null;
			
		}
		
		////////////////////////////////
		
		// DB
		
		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		////////////////////////////////
		
		// validate: table exists?
		
		////////////////////////////////
		String tname = CONS.DB.tname_si;
		boolean res = dbu.tableExists(rdb, tname);
		
		if (res == false) {
			
			String msg = "No such table: " + tname;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			rdb.close();
			
			return null;
			
		}
		
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
		try {
			
			c = rdb.query(
					
					CONS.DB.tname_si,			// 1
					CONS.DB.col_Names_SI_full,	// 2
					null, null,		// 3,4
//					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					null);
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Query failed");
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		// Log
		String msg_Log = "c.getCount() => " + c.getCount();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// build list
		
		////////////////////////////////
		List<SI> list_SIs = new ArrayList<SI>();
		
//		android.provider.BaseColumns._ID,	// 0
//		"store", 							// 1
//		"name", "price",					// 2,3
//		"genre", "yomi",					// 4,5
//		"created_at", "updated_at",			// 6,7
//		"posted_at"							// 8
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		"store", "name", "price",			// 3,4,5
//		"genre", "yomi", "num",				// 6,7,8
//		"posted_at"							// 9
		
		
		SI si = null;
		
		while(c.moveToNext()) {
			
			si = new SI.Builder()
			
			.setDb_id(c.getInt(0))
			.setCreated_at(c.getString(1))
			.setModified_at(c.getString(2))
			
			.setStore(c.getString(3))
			.setName(c.getString(4))
			.setPrice(c.getInt(5))
			
			.setGenre(c.getString(6))
			.setYomi(c.getString(7))
			.setNum(c.getInt(8))
			
			.setPosted_at(c.getString(9))
			
			.build();
			
			list_SIs.add(si);
			
		}//while(c.moveToNext())
		
		////////////////////////////////
		
		// close
		
		////////////////////////////////
		rdb.close();
		
		msg_Log = "list_SIs.size() => " + list_SIs.size();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		return list_SIs;
		
	}//find_ALL_SIs
	
	/******************************
		@return
		null<br>
			1. No DB file<br>
			2. No such table<br>
			3. Query => Exception<br>
			4. Query => no entry<br>
	 ******************************/
	public static List<Store> 
	find_ALL_Stores_from_Previous
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// validate: DB file exists?
		
		////////////////////////////////
		String dbName = CONS.DB.dbName_Importing;
		
		File dpath_DBFile = actv.getDatabasePath(dbName);
		
		if (!dpath_DBFile.exists()) {
			
			String msg = "No DB file: " + dbName;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			return null;
			
		}
		
		////////////////////////////////
		
		// DB
		
		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName_Importing);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		////////////////////////////////
		
		// validate: table exists?
		
		////////////////////////////////
		String tname = CONS.DB.tname_stores;
		boolean res = dbu.tableExists(rdb, tname);
		
		if (res == false) {
			
			String msg = "No such table: " + tname;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			rdb.close();
			
			return null;
			
		}
		
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
		try {
			
			c = rdb.query(
					
					tname,			// 1
					CONS.DB.columns_for_table_stores_with_index,	// 2
					null, null,		// 3,4
//					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					null);
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Query failed");
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		// Log
		String msg_Log = "c.getCount() => " + c.getCount();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// build list
		
		////////////////////////////////
		List<Store> list_Stores = new ArrayList<Store>();
		
//		android.provider.BaseColumns._ID, "store_name", "memo"
		
		Store store = null;
		
		while(c.moveToNext()) {
			
			store = new Store.Builder()
						
						.setStore_name(c.getString(1))
			
						.build();
			
			list_Stores.add(store);
			
		}//while(c.moveToNext())
			
		////////////////////////////////
		
		// close
		
		////////////////////////////////
		rdb.close();
		
		// Log
		msg_Log = "list_Stores.size() => " + list_Stores.size();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		return list_Stores;
		
	}//find_ALL_Stores_from_Previous
	
	/******************************
		@return
		null<br>
			1. No DB file<br>
			2. No such table<br>
			3. Query => Exception<br>
			4. Query => no entry<br>
	 ******************************/
	public static List<Genre> 
	find_ALL_Genres_from_Previous
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// validate: DB file exists?
		
		////////////////////////////////
		String dbName = CONS.DB.dbName_Importing;
		
		File dpath_DBFile = actv.getDatabasePath(dbName);
		
		if (!dpath_DBFile.exists()) {
			
			String msg = "No DB file: " + dbName;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			return null;
			
		}
		
		////////////////////////////////
		
		// DB
		
		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName_Importing);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		////////////////////////////////
		
		// validate: table exists?
		
		////////////////////////////////
		String tname = CONS.DB.tname_genres;
		
		boolean res = dbu.tableExists(rdb, tname);
		
		if (res == false) {
			
			String msg = "No such table: " + tname;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			rdb.close();
			
			return null;
			
		}
		
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
		try {
			
			c = rdb.query(
					
					tname,			// 1
					CONS.DB.columns_for_table_genres_with_index,	// 2
					null, null,		// 3,4
//					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					null);
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Query failed");
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		// Log
		String msg_Log = "c.getCount() => " + c.getCount();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// build list
		
		////////////////////////////////
		List<Genre> list_Genres = new ArrayList<Genre>();
		
//		android.provider.BaseColumns._ID, "genre_name", "memo"
		
		Genre genre = null;
		
		while(c.moveToNext()) {
			
			genre = new Genre.Builder()
			
			.setGenre_name(c.getString(1))
			
			.build();
			
			list_Genres.add(genre);
			
		}//while(c.moveToNext())
		
		////////////////////////////////
		
		// close
		
		////////////////////////////////
		rdb.close();
		
		// Log
		msg_Log = "list_Genres.size() => " + list_Genres.size();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		return list_Genres;
		
	}//find_ALL_Stores_from_Previous
	
	
	/******************************
		@return -1 => Table doesn't exist<br>
	 ******************************/
	public static int 
	insert_SIs
	(Activity actv, List<SI> list_SIs) {
		// TODO Auto-generated method stub
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		String tname = CONS.DB.tname_si;
		
		////////////////////////////////
	
		// validate: table exists
	
		////////////////////////////////
		if (!DBUtils.tableExists(
					actv, CONS.DB.dbName, tname)) {
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist => " + tname);
			
			return -1;
			
		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		////////////////////////////////
	
		// Iteration
	
		////////////////////////////////
		int counter = 0;
		
		ContentValues val = null;
	//	
		for (SI si : list_SIs) {
			
			////////////////////////////////
			
			// prep: content values
			
			////////////////////////////////
			val = _insert_SIs__ContentValues(si);
			
			try {
				// Start transaction
				wdb.beginTransaction();
				
				// Insert data
				long res = wdb.insert(tname, null, val);
	//			long res = wdb.insert(CONS.DB.tname_RefreshLog, null, val);
			
				if (res == -1) {
					
					// Log
					String msg_Log = "insertion => failed: " + si.getName();
					Log.e("DBUtils.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
	
				} else {
					
					counter += 1;
					
					// Set as successful
					wdb.setTransactionSuccessful();
					
				}
				
				// End transaction
				wdb.endTransaction();
				
			} catch (Exception e) {
				
				// Log
				// Log
				String msg_Log = String.format(
									"Exception(%s) => %s", 
									si.getName(), e.toString());
				Log.e("DBUtils.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", msg_Log);
				
			}//try
			
		}//for (String pattern : patterns_List)
	
		////////////////////////////////
	
		// close
	
		////////////////////////////////
		wdb.close();
	
		////////////////////////////////
	
		// return
	
		////////////////////////////////
		return counter;
		
	}//insert_SIs

	/******************************
		@return -1 => Table doesn't exist<br>
	 ******************************/
	public static int 
	insert_Stores
	(Activity actv, List<Store> list_Stores) {
		// TODO Auto-generated method stub
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		String tname = CONS.DB.tname_stores;
		
		////////////////////////////////
		
		// validate: table exists
		
		////////////////////////////////
		if (!DBUtils.tableExists(
				actv, CONS.DB.dbName, tname)) {
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist => " + tname);
			
			return -1;
			
		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		////////////////////////////////
		
		// Iteration
		
		////////////////////////////////
		int counter = 0;
		
		ContentValues val = null;
		//	
		for (Store si : list_Stores) {
			
			////////////////////////////////
			
			// prep: content values
			
			////////////////////////////////
			val = _insert_Stores__ContentValues(si);
			
			try {
				// Start transaction
				wdb.beginTransaction();
				
				// Insert data
				long res = wdb.insert(tname, null, val);
				//			long res = wdb.insert(CONS.DB.tname_RefreshLog, null, val);
				
				if (res == -1) {
					
					// Log
					String msg_Log = "insertion => failed: " + si.getStore_name();
					Log.e("DBUtils.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
				} else {
					
					counter += 1;
					
					// Set as successful
					wdb.setTransactionSuccessful();
					
				}
				
				// End transaction
				wdb.endTransaction();
				
			} catch (Exception e) {
				
				// Log
				// Log
				String msg_Log = String.format(
						"Exception(%s) => %s", 
						si.getStore_name(), e.toString());
				Log.e("DBUtils.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", msg_Log);
				
			}//try
			
		}//for (String pattern : patterns_List)
		
		////////////////////////////////
		
		// close
		
		////////////////////////////////
		wdb.close();
		
		////////////////////////////////
		
		// return
		
		////////////////////////////////
		return counter;
		
	}//insert_SIs
	
	/******************************
		@return -1 => Table doesn't exist<br>
	 ******************************/
	public static int 
	insert_Genres
	(Activity actv, List<Genre> list_Genres) {
		// TODO Auto-generated method stub
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		String tname = CONS.DB.tname_genres;
		
		////////////////////////////////
		
		// validate: table exists
		
		////////////////////////////////
		if (!DBUtils.tableExists(
				actv, CONS.DB.dbName, tname)) {
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist => " + tname);
			
			return -1;
			
		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		////////////////////////////////
		
		// Iteration
		
		////////////////////////////////
		int counter = 0;
		
		ContentValues val = null;
		//	
		for (Genre si : list_Genres) {
			
			////////////////////////////////
			
			// prep: content values
			
			////////////////////////////////
			val = _insert_Genres__ContentValues(si);
			
			try {
				// Start transaction
				wdb.beginTransaction();
				
				// Insert data
				long res = wdb.insert(tname, null, val);
				//			long res = wdb.insert(CONS.DB.tname_RefreshLog, null, val);
				
				if (res == -1) {
					
					// Log
					String msg_Log = "insertion => failed: " + si.getGenre_name();
					Log.e("DBUtils.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
				} else {
					
					counter += 1;
					
					// Set as successful
					wdb.setTransactionSuccessful();
					
				}
				
				// End transaction
				wdb.endTransaction();
				
			} catch (Exception e) {
				
				// Log
				// Log
				String msg_Log = String.format(
						"Exception(%s) => %s", 
						si.getGenre_name(), e.toString());
				Log.e("DBUtils.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", msg_Log);
				
			}//try
			
		}//for (String pattern : patterns_List)
		
		////////////////////////////////
		
		// close
		
		////////////////////////////////
		wdb.close();
		
		////////////////////////////////
		
		// return
		
		////////////////////////////////
		return counter;
		
	}//insert_SIs
	
	private static ContentValues 
	_insert_SIs__ContentValues
	(SI si) {
		// TODO Auto-generated method stub
		
		ContentValues val = new ContentValues();

//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store", "name", "price",			// 3,4,5
//		"genre", "yomi", "num",				// 6,7,8
//		
//		"posted_at"							// 9
		
		val.put("created_at", si.getCreated_at());
		val.put("modified_at", si.getModified_at());
		
		val.put("store", si.getStore());
		val.put("name", si.getName());
		val.put("price", si.getPrice());
		
		val.put("genre", si.getGenre());
		val.put("yomi", si.getYomi());
		
		val.put("posted_at", si.getPosted_at());

		return val;
		
	}//_insert_SIs__ContentValues

	private static ContentValues 
	_update_SIs_Num__CV
	(SI si) {
		// TODO Auto-generated method stub
		
		ContentValues val = new ContentValues();
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store", "name", "price",			// 3,4,5
//		"genre", "yomi", "num",				// 6,7,8
//		
//		"posted_at"							// 9
		
//		val.put("created_at", si.getCreated_at());
		val.put(CONS.DB.col_Names_SI_full[2], 
					Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		val.put(CONS.DB.col_Names_SI_full[8], CONS.Admin.dflt_SI_Num);
		
		return val;
		
	}//_insert_SIs__ContentValues
	
	private static ContentValues 
	_update_SIs_All__CV
	(SI si) {
		// TODO Auto-generated method stub
		
		ContentValues val = new ContentValues();
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store", "name", "price",			// 3,4,5
//		"genre", "yomi", "num",				// 6,7,8
//		
//		"posted_at"							// 9
		
		val.put(CONS.DB.col_Names_SI_full[2], 
				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		val.put(CONS.DB.col_Names_SI_full[3], si.getStore());
		
		val.put(CONS.DB.col_Names_SI_full[4], si.getName());
		
		val.put(CONS.DB.col_Names_SI_full[5], si.getPrice());
		
		val.put(CONS.DB.col_Names_SI_full[6], si.getGenre());
		
		val.put(CONS.DB.col_Names_SI_full[7], si.getYomi());
		
		val.put(CONS.DB.col_Names_SI_full[8], si.getNum());
		
		val.put(CONS.DB.col_Names_SI_full[9], si.getPosted_at());
		
//		val.put(CONS.DB.col_Names_SI_full[8], CONS.Admin.dflt_SI_Num);
		
		return val;
		
	}//_update_SIs_All__CV
	
	private static ContentValues 
	_insert_Stores__ContentValues
	(Store si) {
		// TODO Auto-generated method stub
		
		ContentValues val = new ContentValues();

//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store_name",						// 3
//		
//		"posted_at"							// 4
		
		val.put("created_at", 
					Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		val.put("modified_at", 
					Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		val.put(CONS.DB.col_Names_Store_full[3], si.getStore_name());
		
		if (si.getPosted_at() != null) {
			
			val.put(CONS.DB.col_Names_Store_full[4], si.getPosted_at());
			
		}
		
		return val;
		
	}//_insert_SIs__ContentValues
	
	private static ContentValues 
	_insert_Genres__ContentValues
	(Genre genre) {
		// TODO Auto-generated method stub
		
		ContentValues val = new ContentValues();
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store_name",						// 3
//		
//		"posted_at"							// 4
		
		val.put("created_at", 
				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		val.put("modified_at", 
				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		val.put(CONS.DB.col_Names_Genre_full[3], genre.getGenre_name());
		
		if (genre.getPosted_at() != null) {
			
			val.put(CONS.DB.col_Names_Genre_full[4], genre.getPosted_at());
			
		}
		
		return val;
		
	}//_insert_SIs__ContentValues

	/******************************
		@return -1 => Table doesn't exist<br>
	 ******************************/
	public static int 
	update_SIs_Num
	(Activity actv, List<SI> list_SIs) {
		// TODO Auto-generated method stub
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		String tname = CONS.DB.tname_si;
		
		////////////////////////////////
	
		// validate: table exists
	
		////////////////////////////////
		if (!DBUtils.tableExists(
					actv, CONS.DB.dbName, tname)) {
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist => " + tname);
			
			return -1;
			
		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		////////////////////////////////
	
		// Iteration
	
		////////////////////////////////
		int counter = 0;
		
		ContentValues val = null;
	//	
		String where = null;
		String[] args = null;
		
		for (SI si : list_SIs) {
			
			////////////////////////////////
			
			// prep: content values
			
			////////////////////////////////
			val = _update_SIs_Num__CV(si);
			
			where = CONS.DB.col_Names_SI_full[0] + " = ?";
			
			args = new String[]{String.valueOf(si.getId())};
			
			try {
				// Start transaction
				wdb.beginTransaction();
				
				// Insert data
//				long res = wdb.insert(tname, null, val);
				
				long res = wdb.update(CONS.DB.tname_si, val, where, args);
				
				
				
	//			long res = wdb.insert(CONS.DB.tname_RefreshLog, null, val);
			
				if (res == -1) {
					
					// Log
					String msg_Log = "insertion => failed: " + si.getName();
					Log.e("DBUtils.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
	
				} else {
					
					counter += 1;
					
					// Set as successful
					wdb.setTransactionSuccessful();
					
				}
				
				// End transaction
				wdb.endTransaction();
				
			} catch (Exception e) {
				
				// Log
				// Log
				String msg_Log = String.format(
									"Exception(%s) => %s", 
									si.getName(), e.toString());
				Log.e("DBUtils.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", msg_Log);
				
			}//try
			
		}//for (String pattern : patterns_List)
	
		////////////////////////////////
	
		// close
	
		////////////////////////////////
		wdb.close();
	
		////////////////////////////////
	
		// return
	
		////////////////////////////////
		return counter;		
	}//update_SIs

	/******************************
		@return -1 => Table doesn't exist<br>
	 ******************************/
	public static int 
	update_SIs_All
	(Activity actv, List<SI> list_SIs) {
		// TODO Auto-generated method stub
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		String tname = CONS.DB.tname_si;
		
		////////////////////////////////
		
		// validate: table exists
		
		////////////////////////////////
		if (!DBUtils.tableExists(
				actv, CONS.DB.dbName, tname)) {
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist => " + tname);
			
			return -1;
			
		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		////////////////////////////////
		
		// Iteration
		
		////////////////////////////////
		int counter = 0;
		
		ContentValues val = null;
		//	
		String where = null;
		String[] args = null;
		
		for (SI si : list_SIs) {
			
			////////////////////////////////
			
			// prep: content values
			
			////////////////////////////////
			val = _update_SIs_All__CV(si);
			
			where = CONS.DB.col_Names_SI_full[0] + " = ?";
			
			args = new String[]{String.valueOf(si.getId())};
			
			try {
				// Start transaction
				wdb.beginTransaction();
				
				long res = wdb.update(CONS.DB.tname_si, val, where, args);
				
				if (res == -1) {
					
					// Log
					String msg_Log = "insertion => failed: " + si.getName();
					Log.e("DBUtils.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
				} else {
					
					counter += 1;
					
					// Set as successful
					wdb.setTransactionSuccessful();
					
				}
				
				// End transaction
				wdb.endTransaction();
				
			} catch (Exception e) {
				
				// Log
				String msg_Log = String.format(
						"Exception(%s) => %s", 
						si.getName(), e.toString());
				Log.e("DBUtils.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", msg_Log);
				
			}//try
			
		}//for (String pattern : patterns_List)
		
		////////////////////////////////
		
		// close
		
		////////////////////////////////
		wdb.close();
		
		////////////////////////////////
		
		// return
		
		////////////////////////////////
		return counter;		
		
	}//update_SIs_All
	
	/******************************
		@return 
			-1 => Table doesn't exist<br>
			-2 => insertion => failed<br>
			-3 => update => Exception<br>
			1 => update => done<br>
	 ******************************/
	public static int 
	update_SI_All
	(Activity actv, SI si) {
		// TODO Auto-generated method stub
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		String tname = CONS.DB.tname_si;
		
		////////////////////////////////
		
		// validate: table exists
		
		////////////////////////////////
		if (!DBUtils.tableExists(
				actv, CONS.DB.dbName, tname)) {
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table doesn't exist => " + tname);
			
			return -1;
			
		}//if (!tableExists(SQLiteDatabase db, String tableName))
		
		////////////////////////////////
		
		// Iteration
		
		////////////////////////////////
		ContentValues val = null;
		//	
		String where = null;
		String[] args = null;
		
		////////////////////////////////
		
		// prep: content values
		
		////////////////////////////////
		val = _update_SIs_All__CV(si);
		
		where = CONS.DB.col_Names_SI_full[0] + " = ?";
		
		args = new String[]{String.valueOf(si.getId())};
		
		try {
			// Start transaction
			wdb.beginTransaction();
			
			long res = wdb.update(CONS.DB.tname_si, val, where, args);
			
			if (res == -1) {
				
				// Log
				String msg_Log = "insertion => failed: " + si.getName();
				Log.e("DBUtils.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
			
				wdb.close();
				
				return -2;
				
			} else {
				
				// Set as successful
				wdb.setTransactionSuccessful();
				
			}
			
			// End transaction
			wdb.endTransaction();
			
		} catch (Exception e) {
			
			// Log
			String msg_Log = String.format(
					"Exception(%s) => %s", 
					si.getName(), e.toString());
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			wdb.close();
			
			return -3;
			
		}//try
		
		////////////////////////////////
		
		// close
		
		////////////////////////////////
		wdb.close();
		
		////////////////////////////////
		
		// return
		
		////////////////////////////////
		return 1;		
		
	}//update_SI_All
	
	/******************************
		@return
			null
			1. query => Exception<br>
			2. query => returned null<br>
			3. query => no entry<br>
	 ******************************/
	public static List<SI> 
	search_Items
	(Activity actv, String store, String yomi) {
		// TODO Auto-generated method stub
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();		

		List<SI> list_Found = new ArrayList<SI>();

		////////////////////////////////

		// query

		////////////////////////////////
		Cursor c = null;
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store", "name", "price",			// 3,4,5
//		"genre", "yomi", "num",				// 6,7,8
//		
//		"posted_at"							// 9
		
		String where = 
				CONS.DB.col_Names_SI_full[7] + " LIKE ?"
				+ " OR "
				+ CONS.DB.col_Names_SI_full[4] + " LIKE ?"
				;
//		String where = CONS.DB.col_names_IFM11[11] + " = ?";
		String[] args = new String[]{
				
						"%" + yomi + "%",
						"%" + yomi + "%",
				
				};
		
		try {
			
			c = rdb.query(
					
					CONS.DB.tname_si,			// 1
					CONS.DB.col_Names_SI_full,	// 2
					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					null);
			
		} catch (Exception e) {

			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Query failed");
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
			rdb.close();
			
			return null;
			
		}//if (c == null)

		////////////////////////////////

		// build list

		////////////////////////////////
		SI si = null;
		
		while(c.moveToNext()) {
			
			si = new SI.Builder()
			
			.setDb_id(c.getInt(0))
			.setCreated_at(c.getString(1))
			.setModified_at(c.getString(2))
			
			.setStore(c.getString(3))
			.setName(c.getString(4))
			.setPrice(c.getInt(5))
			
			.setGenre(c.getString(6))
			.setYomi(c.getString(7))
			.setNum(c.getInt(8))
			
			.setPosted_at(c.getString(9))
			
			.build();
			
			list_Found.add(si);
			
		}//while(c.moveToNext())
		
		////////////////////////////////

		// close

		////////////////////////////////
		rdb.close();

		////////////////////////////////

		// return

		////////////////////////////////
		return list_Found;
		
	}//search_Items

	public static boolean delete_SI(Activity actv, SI si) {
		// TODO Auto-generated method stub
		return false;
	}

	/******************************
		@return
		null<br>
			1. No DB file<br>
			2. No such table<br>
			3. Query => Exception<br>
			4. Query => no entry<br>
	 ******************************/
	public static List<PH> 
	find_ALL_PHs__Unposted
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// validate: DB file exists?
		
		////////////////////////////////
		String dbName = CONS.DB.dbName;
		
		File dpath_DBFile = actv.getDatabasePath(dbName);
		
		if (!dpath_DBFile.exists()) {
			
			String msg = "No DB file: " + dbName;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			return null;
			
		}
		
		////////////////////////////////
		
		// DB
		
		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		////////////////////////////////
		
		// validate: table exists?
		
		////////////////////////////////
		String tname = CONS.DB.tname_ph;
		boolean res = dbu.tableExists(rdb, tname);
		
		if (res == false) {
			
			String msg = "No such table: " + tname;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			rdb.close();
			
			return null;
			
		}
		
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",		// 1,2
//		
//		"store_name", "pur_date",			// 3,4
//		"items",							// 5
//		"amount",							// 6
//		"memo",								// 7
//		"posted_at"							// 8

		//REF http://stackoverflow.com/questions/10465083/android-sqlite-select-records-where-field-is-null-or-empty answered May 5 '12 at 19:10
		String where = CONS.DB.col_Names_PH_full[8] + " is null or "
						+ CONS.DB.col_Names_PH_full[8] + " = ?";
		
		String[] args = new String[]{ "" };
		
//		String where = CONS.DB.col_Names_PH_full[8] + " = ?";
//		String[] args = new String[]{ "NULL" };
//		String[] args = new String[]{ null };	//=> java.lang.IllegalArgumentException: the bind value at index 1 is null

//		String[] args = new String[]{ "" };	//=> no entry

		try {
			
			c = rdb.query(
					
					CONS.DB.tname_ph,			// 1
					CONS.DB.col_Names_PH_full,	// 2
//					null, null,		// 3,4
//					where, null,		// 3,4
					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					null);
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Query failed");
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		// Log
		String msg_Log = "c.getCount() => " + c.getCount();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// build list
		
		////////////////////////////////
		List<PH> list_PHs = new ArrayList<PH>();

		PH ps = null;

//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",		// 1,2
//		
//		"store_name", "pur_date",			// 3,4
//		"items",							// 5
//		"amount",							// 6
//		"memo",								// 7
//		"posted_at"							// 8

		while(c.moveToNext()) {
			
			ps = new PH.Builder()
			
						.setDbId(c.getInt(0))
						.setCreated_at(c.getString(1))
						.setModified_at(c.getString(2))

						.setStore_name(c.getString(3))
						.setPur_date(c.getString(4))
						.setItems(c.getString(5))
						
						.setAmount(c.getInt(6))
						.setMemo(c.getString(7))
						.setPosted_at(c.getString(8))
						
						.build();
			
			list_PHs.add(ps);
			
		}//while(c.moveToNext())
			
		////////////////////////////////
		
		// close
		
		////////////////////////////////
		rdb.close();
		
		msg_Log = "list_PHs.size() => " + list_PHs.size();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		return list_PHs;
		
	}//find_ALL_PHs__Unposted

	/******************************
		@return
		null<br>
			1. No DB file<br>
			2. No such table<br>
			3. Query => Exception<br>
			4. Query => no entry<br>
	 ******************************/
	public static List<Store> 
	find_ALL_Stores__Unposted
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// validate: DB file exists?
		
		////////////////////////////////
		String dbName = CONS.DB.dbName;
		
		File dpath_DBFile = actv.getDatabasePath(dbName);
		
		if (!dpath_DBFile.exists()) {
			
			String msg = "No DB file: " + dbName;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			return null;
			
		}
		
		////////////////////////////////
		
		// DB
		
		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		////////////////////////////////
		
		// validate: table exists?
		
		////////////////////////////////
		String tname = CONS.DB.tname_stores;
		boolean res = dbu.tableExists(rdb, tname);
		
		if (res == false) {
			
			String msg = "No such table: " + tname;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			rdb.close();
			
			return null;
			
		}
		
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store_name",						// 3
//		
//		"posted_at"							// 4		
		
		//REF http://stackoverflow.com/questions/10465083/android-sqlite-select-records-where-field-is-null-or-empty answered May 5 '12 at 19:10
		String where = CONS.DB.col_Names_Store_full[4] + " is null or "
				+ CONS.DB.col_Names_Store_full[4] + " = ?";
		
		String[] args = new String[]{ "" };
		
		try {
			
			c = rdb.query(
					
					tname,			// 1
					CONS.DB.col_Names_Store_full,	// 2
//					null, null,		// 3,4
//					where, null,		// 3,4
					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					null);
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Query failed");
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		// Log
		String msg_Log = "c.getCount() => " + c.getCount();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// build list
		
		////////////////////////////////
		List<Store> list_Stores = new ArrayList<Store>();
		
		Store ps = null;
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store_name",						// 3
//		
//		"posted_at"							// 4		
		
		while(c.moveToNext()) {
			
			ps = new Store.Builder()
			
			.setDb_Id((int)c.getLong(0))
			.setCreated_at(c.getString(1))
			.setModified_at(c.getString(2))
			
			.setStore_name(c.getString(3))
			
			.build();
			
			list_Stores.add(ps);
			
		}//while(c.moveToNext())
			
			////////////////////////////////
		
		// close
		
		////////////////////////////////
		rdb.close();
		
		msg_Log = "list_Stores.size() => " + list_Stores.size();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		return list_Stores;
		
	}//find_ALL_Stores__Unposted
	
	/******************************
		@return
		null<br>
			1. No DB file<br>
			2. No such table<br>
			3. Query => Exception<br>
			4. Query => no entry<br>
	 ******************************/
	public static List<SI> 
	find_ALL_SIs__Unposted
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// validate: DB file exists?
		
		////////////////////////////////
		String dbName = CONS.DB.dbName;
		
		File dpath_DBFile = actv.getDatabasePath(dbName);
		
		if (!dpath_DBFile.exists()) {
			
			String msg = "No DB file: " + dbName;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			return null;
			
		}
		
		////////////////////////////////
		
		// DB
		
		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		////////////////////////////////
		
		// validate: table exists?
		
		////////////////////////////////
		String tname = CONS.DB.tname_si;
		
		boolean res = dbu.tableExists(rdb, tname);
		
		if (res == false) {
			
			String msg = "No such table: " + tname;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			rdb.close();
			
			return null;
			
		}
		
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store", "name", "price",			// 3,4,5
//		"genre", "yomi", "num",				// 6,7,8
//		
//		"posted_at"							// 9		
		
		//REF http://stackoverflow.com/questions/10465083/android-sqlite-select-records-where-field-is-null-or-empty answered May 5 '12 at 19:10
		String where = CONS.DB.col_Names_SI_full[9] + " is null or "
				+ CONS.DB.col_Names_SI_full[9] + " = ?";
		
		String[] args = new String[]{ "" };
		
		try {
			
			c = rdb.query(
					
					tname,			// 1
					CONS.DB.col_Names_SI_full,	// 2
//					null, null,		// 3,4
//					where, null,		// 3,4
					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					null);
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Query failed");
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		// Log
		String msg_Log = "c.getCount() => " + c.getCount();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// build list
		
		////////////////////////////////
		List<SI> list_SIs = new ArrayList<SI>();
		
		SI ps = null;
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store", "name", "price",			// 3,4,5
//		"genre", "yomi", "num",				// 6,7,8
//		
//		"posted_at"							// 9		
		
		while(c.moveToNext()) {
			
			ps = new SI.Builder()
			
			.setDb_id((int)c.getLong(0))
			.setCreated_at(c.getString(1))
			.setModified_at(c.getString(2))
			
			.setStore(c.getString(3))
			.setName(c.getString(4))
			.setPrice((int)c.getLong(5))
			
			.setGenre(c.getString(6))
			.setYomi(c.getString(7))
			.setNum((int)c.getLong(8))
			
			.build();
			
			list_SIs.add(ps);
			
		}//while(c.moveToNext())
		
		////////////////////////////////
		
		// close
		
		////////////////////////////////
		rdb.close();
		
		msg_Log = "list_SIs.size() => " + list_SIs.size();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		return list_SIs;
		
	}//find_ALL_Stores__Unposted
	
	/******************************
		@return
		null<br>
			1. No DB file<br>
			2. No such table<br>
			3. Query => Exception<br>
			4. Query => no entry<br>
	 ******************************/
	public static List<SI> 
	find_ALL_SIs__Posted
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// validate: DB file exists?
		
		////////////////////////////////
		String dbName = CONS.DB.dbName;
		
		File dpath_DBFile = actv.getDatabasePath(dbName);
		
		if (!dpath_DBFile.exists()) {
			
			String msg = "No DB file: " + dbName;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			return null;
			
		}
		
		////////////////////////////////
		
		// DB
		
		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		////////////////////////////////
		
		// validate: table exists?
		
		////////////////////////////////
		String tname = CONS.DB.tname_si;
		
		boolean res = dbu.tableExists(rdb, tname);
		
		if (res == false) {
			
			String msg = "No such table: " + tname;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			rdb.close();
			
			return null;
			
		}
		
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store", "name", "price",			// 3,4,5
//		"genre", "yomi", "num",				// 6,7,8
//		
//		"posted_at"							// 9		
		
		//REF http://stackoverflow.com/questions/10465083/android-sqlite-select-records-where-field-is-null-or-empty answered May 5 '12 at 19:10
		String where = CONS.DB.col_Names_SI_full[9] + " IS NOT NULL AND "
				//REF != http://stackoverflow.com/questions/9386304/what-is-the-syntax-for-not-equal-in-sqlite answered Feb 21 '12 at 22:32
				+ CONS.DB.col_Names_SI_full[9] + " != ?";
//		String where = CONS.DB.col_Names_SI_full[9] + " is null or "
//				+ CONS.DB.col_Names_SI_full[9] + " = ?";
		
		String[] args = new String[]{ "" };
		
		try {
			
			c = rdb.query(
					
					tname,			// 1
					CONS.DB.col_Names_SI_full,	// 2
//					null, null,		// 3,4
//					where, null,		// 3,4
					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					null);
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Query failed");
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		// Log
		String msg_Log = "c.getCount() => " + c.getCount();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// build list
		
		////////////////////////////////
		List<SI> list_SIs = new ArrayList<SI>();
		
		SI ps = null;
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store", "name", "price",			// 3,4,5
//		"genre", "yomi", "num",				// 6,7,8
//		
//		"posted_at"							// 9		
		
		while(c.moveToNext()) {
			
			ps = new SI.Builder()
			
			.setDb_id((int)c.getLong(0))
			.setCreated_at(c.getString(1))
			.setModified_at(c.getString(2))
			
			.setStore(c.getString(3))
			.setName(c.getString(4))
			.setPrice((int)c.getLong(5))
			
			.setGenre(c.getString(6))
			.setYomi(c.getString(7))
			.setNum((int)c.getLong(8))
			
			.build();
			
			list_SIs.add(ps);
			
		}//while(c.moveToNext())
		
		////////////////////////////////
		
		// close
		
		////////////////////////////////
		rdb.close();
		
		msg_Log = "list_SIs.size() => " + list_SIs.size();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		return list_SIs;
		
	}//find_ALL_Stores__Unposted
	
	/******************************
		@return
		null<br>
			1. No DB file<br>
			2. No such table<br>
			3. Query => Exception<br>
			4. Query => no entry<br>
	 ******************************/
	public static List<Genre> 
	find_ALL_Genres__Unposted
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// validate: DB file exists?
		
		////////////////////////////////
		String dbName = CONS.DB.dbName;
		
		File dpath_DBFile = actv.getDatabasePath(dbName);
		
		if (!dpath_DBFile.exists()) {
			
			String msg = "No DB file: " + dbName;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			return null;
			
		}
		
		////////////////////////////////
		
		// DB
		
		////////////////////////////////
		DBUtils dbu = new DBUtils(actv, dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		////////////////////////////////
		
		// validate: table exists?
		
		////////////////////////////////
		String tname = CONS.DB.tname_genres;
		
		boolean res = dbu.tableExists(rdb, tname);
		
		if (res == false) {
			
			String msg = "No such table: " + tname;
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			rdb.close();
			
			return null;
			
		}
		
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;

//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"genre_name",						// 3
//		
//		"posted_at"							// 4
		
		//REF http://stackoverflow.com/questions/10465083/android-sqlite-select-records-where-field-is-null-or-empty answered May 5 '12 at 19:10
		String where = CONS.DB.col_Names_Genre_full[4] + " is null or "
				+ CONS.DB.col_Names_Genre_full[4] + " = ?";
		
		String[] args = new String[]{ "" };
		
		try {
			
			c = rdb.query(
					
					tname,			// 1
					CONS.DB.col_Names_Genre_full,	// 2
//					null, null,		// 3,4
//					where, null,		// 3,4
					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					null);
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Query failed");
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		// Log
		String msg_Log = "c.getCount() => " + c.getCount();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// build list
		
		////////////////////////////////
		List<Genre> list_Genres = new ArrayList<Genre>();
		
		Genre ps = null;
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"genre_name",						// 3
//		
//		"posted_at"							// 4
		
		while(c.moveToNext()) {
			
			ps = new Genre.Builder()
			
			.setDb_Id((int)c.getLong(0))
			.setCreated_at(c.getString(1))
			.setModified_at(c.getString(2))
			
			.setGenre_name(c.getString(3))
			
			.build();
			
			list_Genres.add(ps);
			
		}//while(c.moveToNext())
		
		////////////////////////////////
		
		// close
		
		////////////////////////////////
		rdb.close();
		
		msg_Log = "list_Genres.size() => " + list_Genres.size();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		return list_Genres;
		
	}//find_ALL_Genres__Unposted
	
	/******************************
		@return
		null<br>
			1. query => Exception<br>
			2. query => returned null<br>
			3. query => no entry<br>
	 ******************************/
	public static Store 
	find_Store_from_Name
	(Activity actv, String store_name) {
		// TODO Auto-generated method stub
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store_name",						// 3
//		
//		"posted_at"							// 4

		String where = CONS.DB.col_Names_Store_full[3] + " = ?";
		String[] args = new String[]{
				
				store_name
				
		};
		
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
		try {
			
			c = rdb.query(
					
					CONS.DB.tname_stores,			// 1
					CONS.DB.col_Names_Store_full,	// 2
//					null, null,		// 3,4
					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					null);
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			e.printStackTrace();
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Query failed");
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		// Log
		String msg_Log = "c.getCount() => " + c.getCount();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		/***************************************
		 * Build item
		 ***************************************/
		c.moveToFirst();
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store_name",						// 3
//		
//		"posted_at"							// 4
			
		Store st = new Store.Builder()
			
				.setDb_Id((int)c.getLong(0))
				.setCreated_at(c.getString(1))
				.setModified_at(c.getString(2))

				.setStore_name(c.getString(3))
				.setPosted_at(c.getString(4))
				
				.build();
			
		/***************************************
		 * Close db
		 ***************************************/
		rdb.close();
		
		/***************************************
		 * Return
		 ***************************************/
		return st;
		
	}//find_Store_from_Name

	/******************************
		@return
		null<br>
			1. query => Exception<br>
			2. query => returned null<br>
			3. query => no entry<br>
	 ******************************/
	public static Genre
	find_Genre_from_Name
	(Activity actv, String genre_name) {
		// TODO Auto-generated method stub
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();

//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"genre_name",						// 3
//		
//		"posted_at"							// 4
		
		String where = CONS.DB.col_Names_Genre_full[3] + " = ?";
		String[] args = new String[]{
				
				genre_name
				
		};
		
		////////////////////////////////
		
		// Query
		
		////////////////////////////////
		Cursor c = null;
		
		try {
			
			c = rdb.query(
					
					CONS.DB.tname_genres,			// 1
					CONS.DB.col_Names_Genre_full,	// 2
//					null, null,		// 3,4
					where, args,		// 3,4
					null, null,		// 5,6
					null,			// 7
					null);
			
		} catch (Exception e) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			e.printStackTrace();
			
			rdb.close();
			
			return null;
			
		}//try
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Query failed");
			
			rdb.close();
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
			rdb.close();
			
			return null;
			
		}//if (c == null)
		
		// Log
		String msg_Log = "c.getCount() => " + c.getCount();
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		/***************************************
		 * Build item
		 ***************************************/
		c.moveToFirst();
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"genre_name",						// 3
//		
//		"posted_at"							// 4		
		
		Genre genre = new Genre.Builder()
		
					.setDb_Id((int)c.getLong(0))
					.setCreated_at(c.getString(1))
					.setModified_at(c.getString(2))
					
					.setGenre_name(c.getString(3))
					.setPosted_at(c.getString(4))
		
					.build();
		
		/***************************************
		 * Close db
		 ***************************************/
		rdb.close();
		
		/***************************************
		 * Return
		 ***************************************/
		return genre;
		
	}//find_Genre_from_Name
	
	public static boolean 
	update_Data_generic
	(Activity actv, 
		String tname, long dbId, String col_Name, String value) {
		// TODO Auto-generated method stub
		
		/***************************************
		 * Setup: DB
		 ***************************************/
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
//		/***************************************
//		 * Build SQL
//		 ***************************************/
//		String sql = "UPDATE " + tname + " SET "
////				+ colName + "='" + colValue + "', "
//				+ colName + "='" + colValue + "'"
//				+ " WHERE " + android.provider.BaseColumns._ID + " = '" + dbId + "'";
				
		/***************************************
		 * Exec: Query
		 ***************************************/
		String where = android.provider.BaseColumns._ID + " = ?";
		
		String[] args = new String[]{String.valueOf(dbId)};
		
		ContentValues cv = new ContentValues();
		
		cv.put(col_Name, value);
		
		try {
			
			wdb.update(tname, cv, where, args);
			
//			wdb.execSQL(sql);
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "update => Done: " + value);
			
			wdb.close();
			
			return true;
			
		} catch (SQLException e) {
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", 
				"Exception => " + e.toString() + " / " + "value => " + value);
			
			wdb.close();
			
			return false;
			
		}
		
	}//update_Data_generic

}//public class DBUtils extends SQLiteOpenHelper

