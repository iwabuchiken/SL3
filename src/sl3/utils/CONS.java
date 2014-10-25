package sl3.utils;

import java.util.List;

import sl3.adapters.Adp_LogFileList;
import sl3.adapters.Adp_ShowLogFile_List;
import sl3.adapters.ItemListAdapter2;
import sl3.adapters.ToBuyListAdapter;
import sl3.items.LogItem;
import sl3.items.SI;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Vibrator;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class CONS {

	/*********************************
	 * Created at: 20130222_095606<br>
	 * 1. Number of columns automatically added 
	 * 		when the table gets created<br/>
	 * 2. For example, if you coded in such a way that
	 * 		the table id is automatically inserted, then
	 * 		you set this value at 1<br>
	 * 3. The name was formulated from "Number of <b>col</b>umns to<br>
	 * 		<b>add</b> <b>up</b> when extracting values from the cursor"
	 *********************************/
	public static int colAddUp = 1;
	
	/*********************************
	 * From: DBUtils.java (formerly, DBManager.java)
	 *********************************/
	// Version
	static final int version = 1;
	
	// Factory
	public static final CursorFactory factory = null;

	public static boolean bgm;
	
	public static class Admin {
		
		////////////////////////////////
		
		// Utilities
		
		////////////////////////////////
		public static Vibrator vib;
		
		public static final int vibLength_click = 35;
		
		public static final String format_Date_AudioFile = "yyyy-MM-dd_HH-mm-ss-SSS";
		
		public static final String format_Clock = "%02d:%02d";
		
		// X out of 100
		// Usage => e.g. width = screen_width * 100 / ratio_Dialog_to_Screen_W
		public static final int ratio_Dialog_to_Screen_W = 70;
		
		public static final String format_Date = "yyyy/MM/dd HH:mm:ss.SSS";
		
		public static final int dflt_SI_Num = 1;
		
	}
	
	public static class 
	DB {

		/*********************************
		 * DB
		 *********************************/
		public static String dbName = "sl";
		public static String dbName_SL_1 = "sl_1";
		
		public static String dbName_Importing = dbName_SL_1;

		/*********************************
		 * Table names
		 *********************************/
//		public static final String tname_stores	= "stores";
		
//		public static final String tname_genres	= "genres";
		
//		public static final String tname_si	= "shopping_item";
//
		////////////////////////////////

		// Paths and names

		////////////////////////////////
		public static String dirName_ExternalStorage = "/mnt/sdcard-ext";
		
		public final static String dPath_Data_Root = 
									dirName_ExternalStorage + "/sl3_data";
//		public final static String dPath_Data_Root = "/mnt/sdcard-ext/ta2_data";
		
		public static String dPath_dbFile_Backup = dPath_Data_Root + "/backup";
		
		public final static String dPath_Data = dPath_Data_Root + "/data";
		
		public final static String dPath_Log = dPath_Data_Root + "/log";
		
		public final static String fname_Log = "log.txt";

//		public static String dirPath_db = "/data/data/shoppinglist.main/databases";
		public static String dirPath_db = "/data/data/sl.main/databases";
		
		public static String fileName_db_backup_trunk = "shoppinglist_backup";

		public static String fileName_db_backup_ext = ".bk";

		public static String dirPath_dbFile_Backup_SL_1 = 
						dirName_ExternalStorage + "/ShoppingList_backup";
		
		public static String fname_DB_Backup_Trunk = "sl3_backup";
		
		public static String fname_DB_Backup_ext = ".bk";
		
		/*********************************
		 * 
		 *********************************/
//		created_at INTEGER, modified_at INTEGER,
		public static final
		String[] timeStamps = {"created_at", "modified_at"};
		
		public static final
		String tname_PS = "purchase_schedule";

		public static
		String[] col_Names_PS =
				//	0				1			2		3		4
				{"store_name", "due_date", "amount", "memo", "items"};

		public static
		String[] col_Names_PS_full = {
					android.provider.BaseColumns._ID,	// 0
					"created_at", "modified_at",		// 1,2
					"store_name", "due_date", "amount",	// 3,4,5 
					"memo", "items"						// 6,7
		};

		public static
		String[] col_Types_PS =
				{"TEXT",		"TEXT", "INTEGER", "TEXT", "TEXT"};

		/***************************************
		 * Query-related constant values
		 ***************************************/
		final static int DB_QUERY_FAILED = -1;
		final static int DB_QUERY_NO_ENTRY = -2;
		final static int DB_DATA_UPDATE_SUCCESSFUL = 1;
		
		final static int DB_DATA_UPDATE_FAILED = -3;
		
		/*********************************
		 * From: DBManager.java
		 *********************************/
		////////////////////////////////

		// shopping_item

		////////////////////////////////
		public static final String tname_si	= "shopping_item";

		public static String tableName = "shopping_item";

//		public static String[] columns = {"store", "name", "price", "genre"};
		public static String[] columns =
						{"store", "name", "price", "genre", "yomi"};
		
		public static String[] columns_with_index = 
						{"store", "name", "price", "genre", android.provider.BaseColumns._ID};

		public static String[]
		columns_with_index2 = 
			//		0							1		2		3		4			5
			{android.provider.BaseColumns._ID, "name", "yomi", "genre", "store", "price"};
		
		public static String[]
			cols_SI_full = { 
			//	0		1		2
			"store", "name", "price",
			//	3		4			5
			"genre", "yomi", android.provider.BaseColumns._ID, 
			//	6			7				8
			"created_at", "updated_at", "posted_at"
		};

		public static String[] col_Names_SI_full_SL_1 = { 
			
			android.provider.BaseColumns._ID,	// 0
			
			"store", 
			"name", "price",			// 3,4,5
			
			"genre", "yomi",				// 6,7,8
			
			"created_at", "updated_at",			// 1,2
			
			
			"posted_at"							// 9
			
		};
		

		public static String[] col_Names_SI = { 
			
					"store", "name", "price",			// 0,1,2
					"genre", "yomi", "num",					// 3,4
					
					"posted_at"							// 5
					
		};
		
		public static String[] col_Types_SI = { 
			
			"TEXT", "TEXT", "INTEGER",			// 0,1,2
			"TEXT", "TEXT", "INTEGER",			// 3,4
			
			"TEXT"							// 5
			
		};
		
		public static String[] col_Names_SI_full = { 
			
			android.provider.BaseColumns._ID,	// 0
			"created_at", "modified_at",			// 1,2
			
			"store", "name", "price",			// 3,4,5
			"genre", "yomi", "num",				// 6,7,8
			
			"posted_at"							// 9
			
		};
		
		public static String[] col_Types_SI_full = { 
			
			"INTEGER",	// 0
			"TEXT", "TEXT",			// 1,2
			
			"TEXT", "TEXT", "INTEGER",			// 3,4,5
			"TEXT", "TEXT", "INTEGER",			// 6,7,8
			
			"TEXT"							// 9
			
		};
		
		public static String[]
				cols_SI_Register = { 
			//	0		1		2
			"store", "name", "price",
			//	3		4	
			"genre", "yomi", 
			//	5			6	
			"created_at", "updated_at"
		};

		////////////////////////////////
		
		// pur_history
		
		////////////////////////////////
		public static final String tname_ph	= "pur_history";
		
		public static String[]
				col_Names_PH_full = { 
			
			android.provider.BaseColumns._ID,	// 0
			"created_at", "modified_at",		// 1,2
			
			"store_name", "pur_date",			// 3,4
			"items",							// 5
			"amount",							// 6
			"memo",								// 7
			"posted_at"							// 8
		};
		
		public static String[] col_Names_PH = { 
			
			"store_name", "pur_date",		// 0,1
			"items",						// 2
			"amount",							// 3
			"memo",							// 4
			"posted_at"						// 5
			
		};
		
		public static String[] col_Types_PH = { 
			
			"TEXT", "TEXT",				// 0,1
			"TEXT", 					// 2
			"INTEGER", 					// 3
			"TEXT", 					// 4
			"TEXT"						// 5
			
		};
		
		////////////////////////////////
		
		// admin
		
		////////////////////////////////
		public static final String tname_admin	= "admin";
		
		public static String[]
				col_Names_Admin_full = { 
			
			android.provider.BaseColumns._ID,	// 0
			"created_at", "modified_at",		// 1,2
			
			"name", "value"						// 3,4
			
		};
		
		public static String[] col_Names_Admin = { 
			
			"name", "value"			// 0,1
			
		};
		
		public static String[] col_Types_Admin = { 
			
			"TEXT", "TEXT",				// 0,1
			
		};

		public static String table_Admin_LastUploaded = "last_uploaded";
		
		////////////////////////////////

		// stores

		////////////////////////////////
		public static final String tname_stores	= "stores";

		public static String[] col_Names_Store = {
			
			"store_name",	// 0
			"posted_at"		// 1
			
		};
		
		public static String[] col_Names_Store_full = { 
			
			android.provider.BaseColumns._ID,	// 0
			"created_at", "modified_at",			// 1,2
			
			"store_name",						// 3
			
			"posted_at"							// 4
			
		};

		public static String[] col_Types_Store = { 
			
			"TEXT",					// 3
			"TEXT",			// 6,7
			
		};
		
		public static String[] col_Types_Store_full = { 
			
			"INTEGER",	// 0
			"TEXT", "TEXT",			// 1,2
			
			"TEXT",					// 3
			"TEXT",			// 6,7
			
		};

		public static String[] columns_for_table_stores_with_index = 
			{android.provider.BaseColumns._ID, "store_name", "memo"};

		public static String[] column_types_for_table_stores = 
													{"TEXT", "TEXT"};

		////////////////////////////////

		// genres

		////////////////////////////////
		public static final String tname_genres	= "genres";

		public static String[] col_Names_Genre = {
			
			"genre_name",	// 0
			"posted_at"		// 1
			
		};
		
		public static String[] col_Names_Genre_full = { 
			
			android.provider.BaseColumns._ID,	// 0
			"created_at", "modified_at",			// 1,2
			
			"genre_name",						// 3
			
			"posted_at"							// 4
			
		};

		public static String[] col_Types_Genre = { 
			
			"TEXT",					// 3
			"TEXT",			// 6,7
			
		};
		
		public static String[] col_Types_Genre_full = { 
			
			"INTEGER",	// 0
			"TEXT", "TEXT",			// 1,2
			
			"TEXT",					// 3
			"TEXT",			// 6,7
			
		};

		public static String[] columns_for_table_genres = 
											{"genre_name", "memo"};

		
		public static String[] columns_for_table_genres_with_index = 
			{android.provider.BaseColumns._ID, "genre_name", "memo"};

		public static String[] column_types_for_table_genres = 
													{"TEXT", "TEXT"};

		
		////////////////////////////////

		// others

		////////////////////////////////
		public final static String fname_Log_Trunk = "log";
		
		public final static String fname_Log_ext = ".txt";
		
		public static final long logFile_MaxSize = 40000;
		
	}//DB
	
	public static class SQLs {
		
		//REF http://stackoverflow.com/questions/25969/sql-insert-into-values-select-from answered Aug 25 '08 at 12:47
//		+ ", store, name, price, genre, yomi";
		public static String PARAM_20140110_105949 =
				"INSERT INTO"
						+ " " + "shopping_item_new" + " "
						+ " " + "(" + " "
						+ "store," + " "
						+ "name," + " "
						+ "price," + " "
						+ "genre," + " "
						+ "yomi"
						+ " "
						+ " "+ ")"
						
//						+ " " + "(" + " "
						+ "SELECT" + " "
						+ "store," + " "
						+ "name," + " "
						+ "price," + " "
						+ "genre," + " "
						+ "yomi"
						+ " " + "FROM" + " "
						+ CONS.DB.tname_si
//						+ " "+ ")"
						;
//				"INSERT INTO"
//					+ " " + "shopping_item_new" + " "
//					+ " " + "(" + " "
//					+ "SELECT" + " "
//						+ "store," + " "
//						+ "name," + " "
//						+ "price," + " "
//						+ "genre," + " "
//						+ "yomi"
//					+ " " + "FROM" + " "
//						+ CONS.DBAdmin.tname_si
//						+ " "+ ")";
		
		public static final String[]
				a_20140110_105949_InsertInto_ShoppingItemNew
				= {
					
					PARAM_20140110_105949
				};
		
//		+ ", store, name, price, genre, yomi";
		public static String PARAM_20140110_104629 =
			"CREATE TABLE"
				+ " " + "shopping_item_new" + " "
			+ "("
			+ android.provider.BaseColumns._ID + " "
				+ "INTEGER PRIMARY KEY,"
			+ "store	TEXT,"
			+ "name		TEXT,"
			+ "price	INTEGER,"
			+ "genre	TEXT,"
			+ "yomi		TEXT,"
			
			+ "created_at	TEXT,"
			+ "updated_at	TEXT,"
			+ "posted_at	TEXT"
			+ ")";
		
		public static final String[]
			a_20140110_104629_Createtable_ShoppingItemNew
				= {PARAM_20140110_104629};
				
		public static final String[]
			a_20140110_095304_AddColumns_CreatedAt_ToShoppingItemNew
				= {
					"ALTER TABLE shopping_item_new"
						+ " ADD COLUMN"
						+ " created_at TEXT",
						
					"ALTER TABLE shopping_item_new"
						+ " ADD COLUMN"
						+ " updated_at TEXT",
						
					"ALTER TABLE shopping_item_new"
						+ " ADD COLUMN"
						+ " posted_at TEXT"
				};
		
		public static String param_20140110_071149 = 
				android.provider.BaseColumns._ID
				+ ", store, name, price, genre, yomi";
		
		public static final String[]
		a_20140110_071149_CreateTable_shopping_item_new
			= {"CREATE TABLE"
				+ " shopping_item_new "
				+ " as select "
				+ param_20140110_071149
				+ " FROM "
				+ CONS.DB.tname_si};
		
		public static final String[]
		a_20140105_102851_add_column_created_at_etc
			= {"ALTER TABLE shopping_item"
					+ " ADD COLUMN"
					+ " created_at INTEGER",
					
				"ALTER TABLE shopping_item"
						+ " ADD COLUMN"
						+ " updated_at INTEGER",
				
				"ALTER TABLE shopping_item"
						+ " ADD COLUMN"
						+ " posted_at INTEGER"
			};
		
/*		public static final String
		a_20140105_110023_add_column_updated_at
		= "ALTER TABLE shopping_item"
				+ " ADD COLUMN"
				+ " updated_at INTEGER";
		
		public static final String
		a_20140105_110043_add_column_posted_at
		= "ALTER TABLE shopping_item"
				+ " ADD COLUMN"
				+ " posted_at INTEGER";
*/		
		
		/*REF http://stackoverflow.com/questions/7993809/android-sqlite-copy-table-to-another-table answered Nov 3 '11 at 11:16 */
		public static final String[]
		a_20140105_104744_create_table_shopping_item_new
			= {"CREATE TABLE shopping_item_new"
				+ " as select"
				+ " store, name, price, genre"
				+ ", updated_at"
				+ ", posted_at"
				+ ", " + android.provider.BaseColumns._ID
				+ " FROM"
				+ " " + CONS.DB.tname_si};
//				+ " created_at INTEGER"
//				+ " updated_at INTEGER"
//				+ " posted_at INTEGER";
		
		public static final String[]
			a_20140105_112211_DropTable_shopping_item_new
				= {"DROP TABLE shopping_item_new"
				};
//				+ " created_at INTEGER"
//				+ " updated_at INTEGER"
//				+ " posted_at INTEGER";
		
		public static final String[]
			a_20140105_112910_AddColumn_created_at
				= {"ALTER TABLE shopping_item_new"
						+ " ADD COLUMN"
						+ " created_at INTEGER",

		};
		
		public static final String[]
				a_20140105_113308_DropTable_shopping_item
				= {"DROP TABLE shopping_item"
		};
		
		/* REF http://stackoverflow.com/questions/426495/how-do-you-rename-a-table-in-sqlite-3-0 answered Jan 8 '09 at 23:41 */
		public static final String[]
				a_20140105_113651_ChangeTableName_shopping_item_new
				= {"ALTER TABLE" +
						" shopping_item_new" +
						" RENAME TO" +
						" " + CONS.DB.tname_si
				};
		
		public static final String[]
				a_20140108_094802_CreateTable_shopping_item_new
				= {
					"CREATE TABLE shopping_item_new"
						+ " as select"
						+ " store, name, price, genre, yomi"
						+ ", " + android.provider.BaseColumns._ID
						+ " FROM"
						+ " " + CONS.DB.tname_si
		};

		
		public static final String[]
				a_20140108_105402_AddColumn_created_at_etc
				= {
					"ALTER TABLE shopping_item_new"
						+ " ADD COLUMN"
						+ " created_at INTEGER",
						
					"ALTER TABLE shopping_item_new"
						+ " ADD COLUMN"
						+ " updated_at INTEGER",
						
					"ALTER TABLE shopping_item_new"
						+ " ADD COLUMN"
						+ " posted_at INTEGER"
		};
		
		public static final String[]
				a_20140108_220957_AddColumns_created_at_etc
				= {
					"ALTER TABLE"
						+ " " + CONS.DB.tableName
						+ " ADD COLUMN"
						+ " created_at INTEGER",
					
					"ALTER TABLE"
						+ " " + CONS.DB.tableName
						+ " ADD COLUMN"
						+ " updated_at INTEGER",
							
					"ALTER TABLE"
						+ " " + CONS.DB.tableName
						+ " ADD COLUMN"
						+ " posted_at INTEGER"
		};
		
		
	}//public static class SQLs
	
	public static class MagicConstants {
		
//		public static int MODIFY_TAB2_LV_HEIGHT = 150;
		public static int MODIFY_TAB2_LV_HEIGHT = 300;
		
	}
	
	public static class TabActv {

		////////////////////////////////

		// commons

		////////////////////////////////
		public static boolean screen_On;
		
		////////////////////////////////

		// views

		////////////////////////////////
		public static ListView lvTab1;
		public static ListView lvTab2;
		
		public static Spinner spStore;
		public static Spinner spGenre;
		public static Spinner spStore_SearchDlg;
		
		public static TabHost tabHost;
		public static TabSpec firstTab;
		public static TabSpec secondTab;

		////////////////////////////////

		// list-related

		////////////////////////////////
		public static ArrayAdapter<String> adp_List_Store;
		public static ArrayAdapter<String> adapterGenre;
		
		public static ArrayAdapter<String> adp_List_Store_SearchDlg;
		
		public static List<Integer> tab_checkedItemIds;
		public static List<Integer> tab_toBuyItemIds;
		public static List<Integer> tab_boughtItemIds;
		
		/***************************************
		 * Adapters
		 ***************************************/
		public static ItemListAdapter2 adpItems;
		public static ToBuyListAdapter adpToBuys;
		
		public static ItemListAdapter2 adpItems_Found;
		
		/***************************************
		 * List: ShoppingItem
		 ***************************************/
		public static List<SI> toBuyList;
		public static List<SI> itemList;

		public static Menu menu;
		
		////////////////////////////////

		// tab

		////////////////////////////////
		public static int tabID_Current;
		
		public static final int tabID_1st = 0;
		
		public static final int tabID_2nd = 1;
		
	}
	
	public static class HTTPData {

		////////////////////////////////

		// commons

		////////////////////////////////
		/*********************************
		 * Passwords
		 *********************************/
		public static final String passwdKey_SL		= "passwd_sl";
		
		////////////////////////////////

		// SI

		////////////////////////////////
		public static final String passwdSL_NewItem
		= "sl_NewItem";

		public static String UrlPostSI
//				= "http://cosmos-jqm-1.herokuapp.com/items/new";
		= "http://cosmos-jqm-1.herokuapp.com/sl/items/new";
//		= "http://cosmos-jqm-1.herokuapp.com/sl/items/new_data_from_device";
//		= "http://cosmos-jqm-1.herokuapp.com/sl/items/new_data_from_device";
//		= "http://cosmos-jqm-1.herokuapp.com/sl/items/new";
//		= "http://cosmos-jqm-1.herokuapp.com/items/new";
//		= "http://cosmos-jqm-1.herokuapp.com/items";
		
		// http://cosmos-jqm-1.herokuapp.com/items/new
		
		public static String[] siKeys = {
						"item_store_id",	"item_name",
						"item_price",		"item_genre_id",
						"item_yomi",		"item_mobile_id",
						
						"item_mobile_created_at",
						"item_mobile_updated_at",
						"item_mobile_posted_at"
//						"item[store_id]",	"item[name]",
//						"item[price]",		"item[genre_id]",
//						"item[yomi]",		"item[mobile_id]",
						};
		
		////////////////////////////////

		// PH

		////////////////////////////////
		public static String UrlPost_PH
					= "http://benfranklin.chips.jp/cake_apps/Cake_SL3"
						+ "/purhistorys/add_from_remote";
		
		public static final String passwdSL_PurHistory
		= "sl_PurHist";

		public static String[] Keys_PurHistory = {
//			private long dbId;
//			String created_at;
//			String modified_at;
//			
//			String store_name;
//			String pur_date;
//			String items;
//			
//			int amount;
//			
//			String memo;
//			
//			String posted_at;

			"sl_local_db_id",
			"sl_local_created_at",
			"sl_local_modified_at",
			
			"sl_local_store_id",
//			"sl_local_store_name",
			"sl_local_pur_date",
			"sl_local_items",
			
			"sl_local_amount",
			"sl_local_memo",
			
			"sl_local_posted_at",
			
		};

		/*********************************
		 * Posting data => Types
		 *********************************/
		public static enum registerChoice {
			single_item,
			
			pur_history,
		};
		
		/*********************************
		 * Others
		 *********************************/
		public static final String PostItems_SeparatorString
					= " ";
		
	}
	
	public static class ReturnValues {
		/*********************************
		 * No data: -1 ~
		 *********************************/
		public static final int PostSI_NoSIList	= -1;
		
		public static final int NoStoreData		= -2;
		
		public static final int NoGenreData		= -3;
		
		/*********************************
		 * Operation failed: -10 ~
		 *********************************/
		public static final int QueryFailed		= -10;
		
		public static final int BuildJOBodyFailed	= -11;
		
		public static final int BuildEntityFailed	= -12;
		
		public static final int BuildHttpPostFailed	= -13;
		
		public static final int HttpPostFailed		= -14;
		
		public static final int PostedButNotUpdated	= -15;
		
		public static final int ServerError		= -16;
		
		public static final int ClientError		= -17;
		
		public static final int ParamVariableNull	= -17;
		
		/*********************************
		 * Others: > 0, <= -90
		 *********************************/
		public static final int OK				= 1;
		
		public static final int NOP				= -90;
		
		public static final int FAILED			= -91;
		
		public static final int MAGINITUDE_ONE	= 1000;
		
	}//public static class ReturnValues

	public static class HTTPResponse {
		/*********************************
		 * 2xx
		 *********************************/
		public static final int ServiceReady	= 220;
		
		public static final int status_Created	= 201;
		
		public static final int status_OK		= 200;
		
		/*********************************
		 * 4xx
		 *********************************/
		public static final int BadRequest	= 400;
		
		public static final int NotFound		= 404;
		
		/*********************************
		 * 5xx
		 *********************************/
		public static final int ServerError	= 500;
		
		/******************************
			negatives
		 ******************************/
		public static final int status_NOT_CREATED	= -201;
		
		
	}

	/******************************
		Return Values
	 ******************************/
	public static class RV {
		
		/*******************
		 * Constant values
		 *********************************/
		// Generic
		public static final int UNKNOWN_ERROR = -9;
		public static final int EXCEPTION = -10;

		// Generi: Exception
		public static final int EXCEPTION_SQL = -1;
		
		public static final int DB_DOESNT_EXIST = -1;
		public static final int DB_CANT_CREATE_FOLDER = -2;
		public static final int DB_BACKUP_SUCCESSFUL = 1;
		public static final int DB_FILE_COPY_EXCEPTION = -3;
		
		public static final int DB_UPDATE_SUCCESSFUL = 2;
		
		// Methods_sl.refactorDb_colPrice()
		public static final int CURSOR_NULL = -1;
		public static final int CURSOR_NO_ENTRY = -2;
		public static final int DATA_REFACTORING_FAILED = -3;
		public static final int DATA_REFACTORED = 1;

		// Get yomi
		public static final int GETYOMI_SUCCESSFUL = 1;
		public static final int GETYOMI_NO_ENTRY = 2;
		public static final int GETYOMI_FAILED = -1;

		// TabActv.java
		public static final int PREP_LIST_SUCCESSFUL = 1;
		public static final int PREP_LIST_FAILED = -1;

		
	}

	public static class Enum {
		
		public static enum registerChoice {
			items, stores, genres,
		};
		
	}

	public static class Pref {

		////////////////////////////////
		
		// Commons
		
		////////////////////////////////
		public static long dflt_LongExtra_value = -1;
		
		public static int dflt_IntExtra_value = -1;

		////////////////////////////////

		// TabActv

		////////////////////////////////
		public static String pname_TabActv = "pname_TabActv";
		
		public static String pkey_TabActv_CheckedIds = "pkey_TabActv_CheckedIds";
		
		public static String pkey_TabActv_ToBuyIds = "pkey_TabActv_ToBuyIds";
		
		////////////////////////////////

		// MainActv.java

		////////////////////////////////
		
		public static SharedPreferences prefs_MainActv;
		
		public static String pname_MainActv = "pname_MainActv";
//		public static String pname_CurrentPath = "current_path";
		
		public static String pkey_CurrentPath = "pkey_CurrentPath";
		
		public static String pkey_CurrentPosition_MainActv = "pkey_CurrentPosition";

		
		////////////////////////////////

		// LogActv

		////////////////////////////////
		public static String pkey_CurrentPosition_LogActv = 
									"pkey_CurrentPosition_LogActv";

	}

	public static class ShowLogActv {
		
		public static List<LogItem> list_ShowLog_Files = null;
		
//		public static ArrayAdapter<String> adp_LogFile_List = null;
		
		public static Adp_ShowLogFile_List adp_ShowLog_File_List;
		
		public static String fname_Target_LogFile = null;
		
		public static List<String> list_RawLines = null;
		
	}

	public static class Intent {
		
		////////////////////////////////

		// commons

		////////////////////////////////
		public static long dflt_LongExtra_value = -1;
		
		public static int dflt_IntExtra_value = -1;
		
		
		////////////////////////////////

		// MainActv

		////////////////////////////////
		public static String iKey_CurrentPath_MainActv = "current_path";

		////////////////////////////////
		
		// MemoEditActv
		
		////////////////////////////////
		public static String iKey_Memo_Id = "iKey_Memo_Id";
		
		
		/***************************************
		 * Request codes
		 ***************************************/
		public final static int REQUEST_CODE_SEE_BOOKMARKS = 0;
		
		public final static int REQUEST_CODE_HISTORY = 1;
		
		/***************************************
		 * Result code
		 ***************************************/
		public final static int RESULT_CODE_SEE_BOOKMARKS_OK = 1;
		
		public final static int RESULT_CODE_SEE_BOOKMARKS_CANCEL = 0;
		
		////////////////////////////////

		// PlayActv

		////////////////////////////////
		public final static String iKey_PlayActv_Memo_Id = "iKey_PlayActv_Memo_Id";
		
		// Used in Service_ShowProgress
		public static String iKey_PlayActv_TaskPeriod
											= "iKey_PlayActv_TaskPeriod";

		
		////////////////////////////////

		// ShowLogActv

		////////////////////////////////
		public static final String iKey_LogActv_LogFileName =
													"iKey_LogActv_LogFileName";
		

	}//public static class Intent

	public static class LogActv {
		
		public static List<String> list_LogFiles = null;
		
//		public static ArrayAdapter<String> adp_LogFile_List = null;
		
		public static Adp_LogFileList adp_LogFile_List;
		
	}

}//public class CONS
