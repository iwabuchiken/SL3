package sl.utils;

import java.util.ArrayList;
import java.util.List;

import sl.items.PS;
import sl.items.ShoppingItem;
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

	public boolean storeData_PS(String dbName, String tableName, PS ps) {
		try {
			
//			getWritableDatabase();
			SQLiteDatabase wdb = this.getWritableDatabase();
			
			ContentValues cv = storeData_PS__getContentValues(ps);
			
			//
//			ContentValues cv = new ContentValues();

//			/***************************************
//			 * Put values
//			 ***************************************/
//			cv.put(
//					CONS.DBAdmin
//						.col_purchaseSchedule[
//					              Methods.getArrayIndex(
//					            		  CONS.DBAdmin.col_purchaseSchedule,
//					            		  "store_name")],
//					ps.getStoreName());
//
//			cv.put(
//					CONS.DBAdmin
//						.col_purchaseSchedule[
//					              Methods.getArrayIndex(
//					            		  CONS.DBAdmin.col_purchaseSchedule,
//					            		  "due_date")],
//					ps.getDueDate());
			
//			// Put values
//			for (int i = 0; i < cols.length; i++) {
//				cv.put(cols[i], values[i]);
//			}//for (int i = 0; i < columnNames.length; i++)

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
			
//			// Set as successful
//			wdb.setTransactionSuccessful();
//
//			// End transaction
//			wdb.endTransaction();
			
//			// Log
//			StringBuilder sb = new StringBuilder();
//			
//			for (int i = 0; i < cols.length; i++) {
//				//
//				sb.append(cols[i] + " => " + values[i] + "/");
//				
//			}//for (int i = 0; i < cols.length; i++)
//			
//			Log.d("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Stored => " + sb.toString());
			
//			return true;
			
		} catch (Exception e) {
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			return false;
		}//try
		
	}//public boolean storeData(SQLiteDatabase db, String tableName, String[] cols, String[] values)

	private ContentValues storeData_PS__getContentValues(PS ps) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		
		/***************************************
		 * Put values
		 ***************************************/
//		"store_name", "due_date", "amount", "memo", "items"
		
		cv.put(
				CONS.DB
					.col_purchaseSchedule[
				              Methods.getArrayIndex(
				            		  CONS.DB.col_purchaseSchedule,
				            		  "store_name")],
				ps.getStoreName());

		cv.put(
				CONS.DB
					.col_purchaseSchedule[
				              Methods.getArrayIndex(
				            		  CONS.DB.col_purchaseSchedule,
				            		  "due_date")],
				ps.getDueDate());

		cv.put(
				CONS.DB
					.col_purchaseSchedule[
				              Methods.getArrayIndex(
				            		  CONS.DB.col_purchaseSchedule,
				            		  "amount")],
				ps.getAmount());
		
		cv.put(
				CONS.DB
					.col_purchaseSchedule[
				              Methods.getArrayIndex(
				            		  CONS.DB.col_purchaseSchedule,
				            		  "memo")],
				ps.getMemo());

		cv.put(
				CONS.DB
					.col_purchaseSchedule[
				              Methods.getArrayIndex(
				            		  CONS.DB.col_purchaseSchedule,
				            		  "items")],
				ps.getItems());
		
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
			ShoppingItem si) {
		
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
				+ CONS.DB.cols_SI_full[7] + "='" + String.valueOf(si.getUpdated_at()) + "'"
				
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

	
	public List<PS> getPSList(Activity actv) {
		
		SQLiteDatabase rdb = this.getReadableDatabase();

		Cursor c = null;
		
		try {
			
			c = rdb.query(
							CONS.DB.tname_purchaseSchedule,
//							CONS.DBAdmin.col_purchaseSchedule,
							CONS.DB.col_purchaseSchedule_full,
							null, null, null, null, null);
			
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
		
		/***************************************
		 * Build list
		 ***************************************/
		c.moveToFirst();
		
		List<PS> psList = new ArrayList<PS>();
		
		for (int i = 0; i < c.getCount(); i++) {
			
			PS ps = new PS();
			
			ps.setDbId(c.getLong(
							c.getColumnIndex(CONS.DB.col_purchaseSchedule_full[0])));
			
			ps.setStoreName(c.getString(c.getColumnIndex("store_name")));
//			ps.setDueDate(c.getInt(c.getColumnIndex("due_date")));
			ps.setDueDate(c.getLong(c.getColumnIndex("due_date")));
			ps.setAmount(c.getInt(c.getColumnIndex("amount")));
			ps.setMemo(c.getString(c.getColumnIndex("memo")));
			ps.setItems(c.getString(c.getColumnIndex("items")));
			
			psList.add(ps);
			
//			// Log
//			Log.d("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]",
//					"ColumnIndex(\"due_date\")="
//					+ c.getColumnIndex("due_date")
//					+ "/"
////					+ "c.getInt(c.getColumnIndex(\"due_date\"))="
//					+ "c.getLong(c.getColumnIndex(\"due_date\"))="
////					+ c.getInt(c.getColumnIndex("due_date")));
//					+ c.getLong(c.getColumnIndex("due_date")));
			
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)
		
		return psList;
		
	}//public List<PS> getPSList(Activity actv)

	public ShoppingItem getSIFromDbId(String dbId) {
		// TODO Auto-generated method stub
		
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "dbId=" + dbId);
		
		SQLiteDatabase rdb = this.getReadableDatabase();
		
//		CONS.DBAdmin.columns => "store", "name", "price", "genre", "yomi"
//		Cursor cursor = rdb.query(
//							CONS.DBAdmin.tableName,
////							CONS.DBAdmin.columns,
//							CONS.DBAdmin.columns_with_index2,
////							android.provider.BaseColumns._ID + "=",	// where
//							String.valueOf(CONS.DBAdmin.columns_with_index2[0]),
//							new String[]{dbId},	// param
//							null, null, null);

//		// From: TabActv.java
//		Cursor cursor = rdb.query(
//				CONS.DBAdmin.tableName, 
////										DBManager.columns,
////				CONS.DBAdmin.columns_with_index,
//				CONS.DBAdmin.columns_with_index2,
//				String.valueOf(CONS.DBAdmin.columns_with_index2[0]),
//				new String[]{dbId},
//				null, null, null);
		
//		String sql = "SELECT " + "store, name, price, genre, yomi"
		String sql = "SELECT " + "*"
					+ " FROM " + CONS.DB.tableName
					+ " WHERE " + CONS.DB.columns_with_index2[0]
					+ " = "
					+ dbId;
		
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
		
		ShoppingItem si = new ShoppingItem();
		
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
	
	
	public ShoppingItem getSI_FromItemName(String itemName) {
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
		
		ShoppingItem si = new ShoppingItem();
		
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
	(Activity actv2, String storeName, long dueDate, String itemIdsString) {
		// TODO Auto-generated method stub
		/***************************************
		 * Get db id
		 ***************************************/
		long dbId = this.getDbId_PS(storeName, dueDate);

		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "dbId=" + dbId);
		
		/***************************************
		 * Exec updating
		 ***************************************/
		String tname = CONS.DB.tname_purchaseSchedule;
//		String colName = CONS.DBAdmin.col_purchaseSchedule[4];
		
		String sql = "UPDATE " + tname
					+ " SET "
					+ CONS.DB.col_purchaseSchedule[4] + "='"	// items
						+ itemIdsString + "'"
					+ " WHERE " + android.provider.BaseColumns._ID
					+ " = '" + dbId + "'";
	
		SQLiteDatabase wdb = this.getWritableDatabase();
		
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

	}//updateData_PS_ItemIds

	/***************************************
	 * 20130312_170156
	 * @return CONS.DBAdmin.DB_QUERY_FAILED<br>
	 * 			CONS.DBAdmin.DB_QUERY_NO_ENTRY<br>
	 * 			Database id (long)
	 ***************************************/
	private long getDbId_PS(String storeName, long dueDate) {
		// TODO Auto-generated method stub
		/***************************************
		 * Prepare reference data
		 ***************************************/
		int[] referenceDueDateData = Methods.getDateArrayFromLongData(dueDate);
		
		/***************************************
		 * Database
		 ***************************************/
		SQLiteDatabase rdb = getReadableDatabase();
		
		String sql = "SELECT * FROM " + CONS.DB.tname_purchaseSchedule;
		
		Cursor c = null;
		
		//
		try {

			c = rdb.rawQuery(sql, null);

		} catch (SQLException e) {
			
			// Log
			Log.d("MemoDBHelper.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			rdb.close();
			
			return CONS.DB.DB_QUERY_FAILED;
			
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
			
			return CONS.DB.DB_QUERY_FAILED;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
			rdb.close();
			
			return CONS.DB.DB_QUERY_NO_ENTRY;
			
		}//if (c == null)

		/***************************************
		 * Search for the target item
		 ***************************************/
		c.moveToFirst();
		
		for (int i = 0; i < c.getCount(); i++) {
			
			long targetDueDate = c.getLong(
					c.getColumnIndex(
							CONS.DB.col_purchaseSchedule[1]));
	
			int[] targetDueDateData = Methods.getDateArrayFromLongData(targetDueDate);

			String targetStoreName = c.getString(
					c.getColumnIndex(
							CONS.DB.col_purchaseSchedule[0]));

			if (targetStoreName.equals(storeName)
				&& referenceDueDateData[0] == targetDueDateData[0]
				&& referenceDueDateData[1] == targetDueDateData[1]
				&& referenceDueDateData[2] == targetDueDateData[2]) {
				
				rdb.close();
				
				return c.getLong(0);	// Return database id
				
			}//if (cal.YEAR == dueDateData[0])
				
			c.moveToNext();

			
		}//for (int i = 0; i < c.getCount(); i++)
		
		// Log
		Log.d("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"No matching entry: Given params => "
				+ "Store name=" + storeName + "/"
				+ "Due date=" + Methods.getTimeLabel_Japanese(dueDate));

		/***************************************
		 * Close db
		 ***************************************/
		rdb.close();
		
		return CONS.DB.DB_QUERY_NO_ENTRY;
		
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

	public boolean updateData_SI_all(ShoppingItem si) {
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
	public boolean updateData_SI_all_V2(ShoppingItem si) {
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
		cv.put(CONS.DB.cols_SI_full[7], si.getUpdated_at());
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

}//public class DBUtils extends SQLiteOpenHelper

