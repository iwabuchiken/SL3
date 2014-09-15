package sl.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import sl.items.SI;
import sl.listeners.ButtonOnTouchListener;
import sl.listeners.dialog.DB_OCL;
import sl.listeners.dialog.DialogButtonOnTouchListener;
import sl.listeners.dialog.DOI_CL;
import sl.main.ItemListActv;
import sl3.main.R;
import sl.main.MainActv;
import sl.main.RegisterItemActv;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Vibrator;
import android.util.Log;
import android.util.Xml;
import android.view.Display;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Methods {

	//
	static ArrayAdapter<String> adapter;		//=> Used in: public static void dlg_dropTable(Activity actv)

	public static int vibLength = 35;
	
	/*----------------------------
	 * Variables
		----------------------------*/

	public static void register_store(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
			----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_register_store);
		
		// Title
		dlg.setTitle(R.string.dlg_register_store_title);
		
		//

		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg.findViewById(R.id.dlg_register_store_btn_ok);
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_register_store_btn_cancel);
		
		//
		btn_ok.setTag(Tags.DialogTags.dlg_register_store_ok);
		btn_cancel.setTag(Tags.DialogTags.dlg_register_store_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DB_OCL(actv, dlg));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));

		//
		dlg.show();

	}//public static void register_store(Activity actv)

	public static void dlg_input_empty(Activity actv, Dialog dlg) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
			----------------------------*/
		
		// 
		Dialog dlg_new = new Dialog(actv);
		
		//
		dlg_new.setContentView(R.layout.dlg_input_empty);
		
		// Title
		dlg_new.setTitle(R.string.dlg_input_empty_title);
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg_new.findViewById(R.id.dlg_input_empty_btn_reenter);
		Button btn_cancel = (Button) dlg_new.findViewById(R.id.dlg_input_empty_btn_cancel);
		
		//
		btn_ok.setTag(Tags.DialogTags.dlg_input_empty_btn_reenter);
		btn_cancel.setTag(Tags.DialogTags.dlg_input_empty_btn_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg_new));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg_new));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DB_OCL(actv, dlg, dlg_new));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg, dlg_new));
		
		//
		dlg_new.show();

	}//public static void dlg_input_empty(Activity actv, Dialog dlg)

	public static void insertStoreName(
					Activity actv, Dialog dlg, String tableName, String storeName) {
		/*----------------------------
		 * Steps
		 * 1. Table exists?
		 * 1-2. If not => Create table
		 * 2. Reconfirm store name
			----------------------------*/
		
		// 
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase db = dbm.getWritableDatabase();
		
		//
		if (!dbm.tableExists(db, tableName)) {
			//
//			dbm.createTable(db, tableName);
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table does not exist => " + tableName);

			/*----------------------------
			 * 1-2. If not => Create table
				----------------------------*/
			//
			String[] columns = CONS.DB.col_Names_Store;
			
			String[] types = CONS.DB.column_types_for_table_stores;
			
			dbm.createTable_generic(db, tableName, columns, types);
			
		} else {//if (!dbm.tableExists(db, tableName))
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists => " + tableName);
			
		}//if (!dbm.tableExists(db, tableName))
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Store name => " + storeName);
		
		//
		db.close();
		
		/*----------------------------
		 * 2. Reconfirm store name
			----------------------------*/
		dlg_reconfirm_store_name(actv, dlg, tableName, storeName);

	}//public static void insertStoreName(Activity actv, String tableName, String storeName)

	/****************************************
	 * dlg_reconfirm_store_name()
	 * 
	 * <Caller> 
	 * 1. insertStoreName()
	 * 
	 * <Desc> 1. <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	private static void dlg_reconfirm_store_name(
										Activity actv, Dialog dlg,
										String tableName, String storeName) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 * 4. Set store name
			----------------------------*/
		
		// 
		Dialog dlg_new = new Dialog(actv);
		
		//
		dlg_new.setContentView(R.layout.dlg_reconfirm_store_name);
		
		// Title
		dlg_new.setTitle(R.string.dlg_reconfirm_store_name_title);
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg_new.findViewById(R.id.dlg_reconfirm_store_name_btn_yes);
		Button btn_cancel = (Button) dlg_new.findViewById(R.id.dlg_reconfirm_store_name_btn_cancel);
		
		//
		btn_ok.setTag(Tags.DialogTags.dlg_reconfirm_store_name_btn_yes);
		btn_cancel.setTag(Tags.DialogTags.dlg_reconfirm_store_name_btn_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DB_OCL(actv, dlg, dlg_new));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg, dlg_new));
		
		/*----------------------------
		 * 4. Set store name
			----------------------------*/
		//
//		TextView tv_store_name = (TextView) dlg_new.findViewById(R.id.dlg_reconfirm_store_name_tv_message_store_name);
		TextView tv_store_name = (TextView) dlg_new.findViewById(R.id.dlg_reconfirm_store_name_tv_store_name);
		
		tv_store_name.setText(storeName);
		tv_store_name.setTextColor(Color.YELLOW);
		
		//
		dlg_new.show();
		
	}//private static void dlg_reconfirm_store_name

	public static void insertStoreName_final(
							Activity actv, Dialog dlg, Dialog dlg2, String tableName) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Insert data
		 * 3. Close db
		 * 4. Close dialogues
			----------------------------*/
		
		//
		TextView tv_store_name = 
					(TextView) dlg2.findViewById(
//							R.id.dlg_reconfirm_store_name_tv_message_store_name);
							R.id.dlg_reconfirm_store_name_tv_store_name);
		
		String storeName = tv_store_name.getText().toString();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Store name => " + storeName);

		// 
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase db = dbm.getWritableDatabase();

		/*----------------------------
		 * 2. Insert data
			----------------------------*/
		//
		boolean result = dbm.storeData(
										db, 
										tableName, 
										CONS.DB.col_Names_Store, 
										new String[]{storeName, ""});
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "dbm.storeData => " + result);
			
		/*----------------------------
		 * 3. Close db
			----------------------------*/
		db.close();

		/*----------------------------
		 * 4. Close dialogues
			----------------------------*/
		//
		if (result == true) {
			// debug
			Toast.makeText(actv, "�X�ܖ����o�^����܂����@=>�@" + storeName, Toast.LENGTH_LONG).show();
			
			//
			dlg2.dismiss();
			dlg.dismiss();
			
		} else {//if (result == true)
			// debug
			Toast.makeText(actv, "�X�ܖ��o�^�@=>�@�ł��܂���ł���", Toast.LENGTH_LONG).show();
			
			//
			dlg2.dismiss();
			
		}//if (result == true)


	}//public static void insertStoreName_final()

	public static void registerGenre(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
			----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_register_genre);
		
		// Title
		dlg.setTitle(R.string.dlg_register_genre_title);
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg.findViewById(R.id.dlg_register_genre_btn_register);
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_register_genre_btn_cancel);
		
		//
		btn_ok.setTag(Tags.DialogTags.dlg_register_genre_register);
		btn_cancel.setTag(Tags.DialogTags.dlg_register_genre_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DB_OCL(actv, dlg));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));

		//
		dlg.show();

	}//public static void registerGenre(Activity actv)

	public static void dlg_reconfirm_genre_name(
								Activity actv, Dialog dlg, String tableName, String genreName) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Add listeners => OnTouch
		 * 3. Add listeners => OnClick
		 * 4. Set store name
			----------------------------*/
		
		// 
		Dialog dlg_new = new Dialog(actv);
		
		//
		dlg_new.setContentView(R.layout.dlg_reconfirm_genre_name);
		
		// Title
		dlg_new.setTitle(R.string.generic_confirm);
		
		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg_new.findViewById(R.id.dlg_reconfirm_genre_name_btn_yes);
		Button btn_cancel = (Button) dlg_new.findViewById(R.id.dlg_reconfirm_genre_name_btn_cancel);
		
		//
		btn_ok.setTag(Tags.DialogTags.dlg_reconfirm_genre_name_btn_register);
		btn_cancel.setTag(Tags.DialogTags.dlg_reconfirm_genre_name_btn_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DB_OCL(actv, dlg, dlg_new));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg, dlg_new));
		
		/*----------------------------
		 * 4. Set store name
			----------------------------*/
		//
//		TextView tv_store_name = (TextView) dlg_new.findViewById(R.id.dlg_reconfirm_store_name_tv_message_store_name);
		TextView tv_genre_name = 
					(TextView) dlg_new.findViewById(R.id.dlg_reconfirm_genre_name_tv_genre_name);
		
		tv_genre_name.setText(genreName);
		tv_genre_name.setTextColor(Color.YELLOW);
		
		//
		dlg_new.show();
		
	}//public static void dlg_reconfirm_genre_name
	
	public static void registerGenre_final(Activity actv, Dialog dlg1, Dialog dlg2) {
		/*----------------------------
		 * Steps
			----------------------------*/
		
	}//public static void registerGenre_final(Activity actv, Dialog dlg1, Dialog dlg2)

	public static void registerGenreName_final(Activity actv, Dialog dlg,
			Dialog dlg2, String tableName) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 1-2. Table exists?
		 * 2. Insert data
		 * 3. Close db
		 * 4. Close dialogues
			----------------------------*/
		
		//
		TextView tv_genre_name = 
					(TextView) dlg2.findViewById(
//							R.id.dlg_reconfirm_store_name_tv_message_store_name);
							R.id.dlg_reconfirm_genre_name_tv_genre_name);

		
		String genreName = tv_genre_name.getText().toString();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Genre name => " + genreName);

		// 
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase db = dbm.getWritableDatabase();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "DB => Opened");
		
		/*----------------------------
		 * 1-2. Table exists?
			----------------------------*/
		//
		boolean result = dbm.tableExists(db, tableName);
		
		/*----------------------------
		 * If the table doesn't exist, create a new one
			----------------------------*/
		if (result == false) {
			result = dbm.createTable_generic(
					db, 
					tableName, 
					CONS.DB.columns_for_table_genres, 
					CONS.DB.column_types_for_table_genres);
			
			if (result == false) {
				/*----------------------------
				 * If "create table" failed, dismiss the reconfirm dialog
				 * 		=> Close db
				 * 		=> Back to "Enter genre name" dialog
					----------------------------*/
				
				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Create table => Failed: " + tableName);
				
				// debug
				Toast.makeText(actv, "Create table => Failed: " + tableName, Toast.LENGTH_LONG).show();
				
				db.close();
				
				dlg2.dismiss();
				
				return;
				
			}//if (result == false)
		}//if (result == false)
		
		/*----------------------------
		 * At this point, the table exists
			----------------------------*/

		/*----------------------------
		 * 2. Insert data
			----------------------------*/
		//
		result = dbm.storeData(
										db, 
										tableName, 
										CONS.DB.columns_for_table_genres, 
										new String[]{genreName, ""});

		/*----------------------------
		 * 3. Close db
			----------------------------*/
		db.close();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "DB => Closed");
		
		/*----------------------------
		 * 4. Close dialogues
			----------------------------*/

		//
		if (result == true) {
			// debug
			Toast.makeText(actv, "�W�����������o�^����܂����@=>�@" + genreName, Toast.LENGTH_LONG).show();
			
			//
			dlg2.dismiss();
			dlg.dismiss();
			
		} else {//if (result == true)
			/*----------------------------
			 * If "storeData" failed, dismiss the reconfirm dialog
			 * 		=> Close db
			 * 		=> Back to "Enter genre name" dialog
				----------------------------*/		

			// debug
			Toast.makeText(actv, "�W���������o�^�@=>�@�ł��܂���ł���", Toast.LENGTH_LONG).show();
			
			//
			dlg2.dismiss();
			
		}//if (result == true)

//

	}//public static void registerGenreName_final

	public static void dlg_createTable(Activity actv) {
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_create_table);
		
		// Title
		dlg.setTitle(R.string.dlg_create_table_title);
		
		//

		/*----------------------------
		 * 2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_ok = (Button) dlg.findViewById(R.id.dlg_create_table_btn_create);
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_create_table_btn_cancel);
		
		//
		btn_ok.setTag(Tags.DialogTags.dlg_create_table_create);
		btn_cancel.setTag(Tags.DialogTags.dlg_create_table_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DB_OCL(actv, dlg));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));

		//
		dlg.show();
		
	}//public static void dlg_createTable(Activity actv)

	public static void createTable_FromDialog(Activity actv, Dialog dlg, String tableName, String[] columns, String[] types) {
		/*----------------------------
		 * Steps
		 * 1. DBManager
		 * 2. Table exists?
		 * 3. Create table
			----------------------------*/
		// 
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase db = dbm.getWritableDatabase();
		
		/*----------------------------
		 * 2. Table exists?
			----------------------------*/
		if (dbm.tableExists(db, tableName)) {
			//
//			dbm.createTable(db, tableName);
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table exists => " + tableName);

			return;
		}//if (dbm.tableExists(db, tableName))
		
		/*----------------------------
		 * 3. Create table
			----------------------------*/
		boolean result = dbm.createTable_generic(db, tableName, columns, types);
		
		if (result == true) {
			// debug
			Toast.makeText(actv, "Table created => " + tableName, Toast.LENGTH_LONG).show();
			
			//
			dlg.dismiss();
			
		} else {//if (result == true)
			// debug
			Toast.makeText(actv, "Create table => failed: " + tableName, Toast.LENGTH_LONG).show();
		}//if (result == true)

	}//public static void createTable_FromDialog(Activity actv, Dialog dlg)

	public static void dlg_createTable_isInputEmpty(Activity actv, Dialog dlg) {
		/*----------------------------
		 * Steps
		 * 1. Get views
		 * 2. Prepare data
		 * 3. Data valid?
		 * 4. Send data to other method
			----------------------------*/
		
		// Get views
		EditText et_table_name = (EditText) dlg.findViewById(R.id.dlg_create_table_et_table_name);
		EditText et_column_name = (EditText) dlg.findViewById(R.id.dlg_create_table_et_column_name);
		EditText et_data_type = (EditText) dlg.findViewById(R.id.dlg_create_table_et_data_type);
		
		if (et_table_name.getText().length() == 0 ||
			et_column_name.getText().length() == 0 ||
			et_data_type.getText().length() == 0
				) {
			// debug
			Toast.makeText(actv, "Empty box", Toast.LENGTH_LONG).show();
			
			return;
		} else {//if (et_column_name.getText().length() == 0)
			// debug
			Toast.makeText(actv, "Input complete", Toast.LENGTH_LONG).show();
			
//			dlg.dismiss();
		}//if (et_column_name.getText().length() == 0)
		
		/*----------------------------
		 * 2. Prepare data
			----------------------------*/
		//
		String[] columns = et_column_name.getText().toString().split(" ");
		String[] types = et_data_type.getText().toString().split(" ");
		
		/*----------------------------
		 * 3. Data valid?
			----------------------------*/
		if (columns.length != types.length) {
			// debug
			Toast.makeText(actv, "Columns and data types don't match", Toast.LENGTH_LONG).show();
			
			return;
		}//if (columns.length != types.length)
		
		/*----------------------------
		 * 4. Send data to other method
			----------------------------*/
		createTable_FromDialog(actv, dlg, et_table_name.getText().toString(), columns, types);

	}//public static void dlg_createTable_isInputEmpty(Activity actv, Dialog dlg)

	public static void dlg_dropTable(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Dialog
		 * 		1.1. Set up
		 * 		1.2. OnTouch
		 * 		1.3. OnClick
		 * 2. Adapter
		 * 		2.1. Prepare data
		 * 		2.2. Create adapter
		 * 3. ListView
		 * 		3.1 Get view
		 * 		3.2. Set adapter to list view
		 * 		3.3. Set listener to list view
		 * 		1.5. OnListItem
		 * 4. Set listener to list view
		 * 5. Show dialog
			----------------------------*/
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_drop_table);
		
		// Title
		dlg.setTitle(R.string.dlg_drop_table_title);

//

		/*----------------------------
		 * 1.2. Add listeners => OnTouch
			----------------------------*/
		//
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_drop_table_btn_cancel);
		
		//
		btn_cancel.setTag(Tags.DialogTags.dlg_drop_table_btn_cancel);
		
		//
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		 * 1.3. Add listeners => OnClick
			----------------------------*/
		//
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));

		/*----------------------------
		 * 2. Adapter
			----------------------------*/
		// 
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase db = dbm.getWritableDatabase();

		//=> source: http://stackoverflow.com/questions/4681744/android-get-list-of-tables : "Just had to do the same. This seems to work:"
		String q = "SELECT name FROM " + "sqlite_master"+
						" WHERE type = 'table' ORDER BY name";
		
		Cursor c = null;
		try {
			c = db.rawQuery(q, null);
		} catch (Exception e) {
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
		}
		
		// Table names list
		List<String> tableList = new ArrayList<String>();
		
		// Log
		if (c != null) {
			c.moveToFirst();
			
			for (int i = 0; i < c.getCount(); i++) {
				// Log
				Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getString(0) => " + c.getString(0));
				
				//
				tableList.add(c.getString(0));
				
				// Next
				c.moveToNext();
				
			}//for (int i = 0; i < c.getCount(); i++)

		} else {//if (c != null)
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c => null");
		}//if (c != null)

		db.close();
		
		// Adapter
		adapter = new ArrayAdapter<String>(
						actv,
						android.R.layout.simple_list_item_1, 
						tableList
				);
		
		/*----------------------------
		 * 3. ListView
			----------------------------*/
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_drop_lv_table_list);
		
		// Set adapter
		lv.setAdapter(adapter);

		/*----------------------------
		 * 4. Set listener to list view
			----------------------------*/
//		lv.setOnItemClickListener(new DialogOnItemClickListener(actv, dlg));
		lv.setOnItemClickListener(new DOI_CL(actv, dlg, Tags.DialogTags.dlg_drop_table));

		/*----------------------------
		 * 5. Show dialog
			----------------------------*/
		dlg.show();
		
	}//public static void dlg_dropTable(Activity actv)

	public static void dlg_confirmTableDrop(Activity actv, Dialog dlg, String tableName) {
		/*----------------------------
		 * Steps
		 * 1. Set up dialog
		 * 2. Set table name to view
		 * 3. Set listener => onTouch
		 * 4. Set listener => Cancel
		 * 5. Set listener => Drop
		 * 6. Show dialog
			----------------------------*/
		
		// 
		Dialog dlg2 = new Dialog(actv);
		
		//
		dlg2.setContentView(R.layout.dlg_confirm_drop_table);
		
		// Title
		dlg2.setTitle(R.string.generic_confirm);
		
		/*----------------------------
		 * 2. Set table name to view
			----------------------------*/
		//
//		TextView tv_table_name = (TextView) actv.findViewById(R.id.dlg_confirm_drop_table_tv_table_name);
		TextView tv_table_name = (TextView) dlg2.findViewById(R.id.dlg_confirm_drop_table_tv_table_name);
		
		tv_table_name.setText(tableName);

		/*----------------------------
		 * 3. Set listener => onTouch
			----------------------------*/
		// Buttons
		Button btn_ok = (Button) dlg2.findViewById(R.id.dlg_confirm_drop_table_btn_ok);
		Button btn_cancel = (Button) dlg2.findViewById(R.id.dlg_confirm_drop_table_btn_cancel);
		
		// Tags
		btn_ok.setTag(Tags.DialogTags.dlg_confirm_drop_table_btn_ok);
		btn_cancel.setTag(Tags.DialogTags.dlg_confirm_drop_table_btn_cancel);
		
		// Set
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/*----------------------------
		 * 4. Set listener => Cancel
			----------------------------*/
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg, dlg2));
		
		/*----------------------------
		 * 5. Set listener => Drop
			----------------------------*/
		btn_ok.setOnClickListener(new DB_OCL(actv, dlg, dlg2));
		
		/*----------------------------
		 * 6. Show dialog
			----------------------------*/
		dlg2.show();
		
	}//public static void dlg_confirmTableDrop

	public static void dropTable(Activity actv, Dialog dlg) {
		/*----------------------------
		 * Steps
		 * 1. Get table name
		 * 2. Open db
		 * 3. Drop table
		 * 4. Dismiss dialog
		 * 5. Close db
			----------------------------*/
		
		// 
		TextView tv_table_name = (TextView) dlg.findViewById(R.id.dlg_confirm_drop_table_tv_table_name);
		
		String tableName = tv_table_name.getText().toString();
		
		// 
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase db = dbm.getWritableDatabase();

		boolean result = dbm.dropTable(actv, db, tv_table_name.getText().toString());
		
		if (result == true) {
			// debug
			Toast.makeText(actv, "Table dropped => " + tableName, Toast.LENGTH_LONG).show();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table dropped => " + tableName);
			
		} else {//if (result == true)
			// debug
			Toast.makeText(actv, "Drop table => Failed: " + tableName, Toast.LENGTH_LONG).show();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Drop table => Failed: " + tableName);
			
		}//if (result == true)
		
		/*----------------------------
		 * 4. Dismiss dialog
			----------------------------*/
		dlg.dismiss();
		
		db.close();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "db => Closed");

	}//public static void dropTable(Activity actv, Dialog dlg)

	/******************************
		@return
			-1 table doesn't exist<br>
			-2 Drop table => Failed<br>
			1 Table dropped<br>
	 ******************************/
	public static int
	dropTable
	(Activity actv, String tname) {
		
		////////////////////////////////

		// validate

		////////////////////////////////
		boolean res = DBUtils.tableExists(actv, CONS.DB.dbName, tname);
		
		if (res == false) {
			
			// Log
			String msg_Log = "table doesn't exist => " + tname;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -1;
			
		}
		
		////////////////////////////////

		// setup: db

		////////////////////////////////
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase wdb = dbm.getWritableDatabase();
		
		boolean result = dbm.dropTable(actv, wdb, tname);
		
		if (result == true) {
			// debug
			Toast.makeText(actv, "Table dropped => " + tname, Toast.LENGTH_LONG).show();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table dropped => " + tname);
			
			wdb.close();
			
			return 1;
			
		} else {//if (result == true)
			// debug
			Toast.makeText(actv, "Drop table => Failed: " + tname, Toast.LENGTH_LONG).show();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Drop table => Failed: " + tname);
			
			wdb.close();
			
			return -2;
			
		}//if (result == true)
		
//		db.close();
		
	}//public static void dropTable(Activity actv, Dialog dlg)
	
	public static void dlg_filterList(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Prepare data for spinners
		 * 		2.1. Stores
		 * 		2.2. Genres
		 * 		2.3. Close db
		 * 3. Set data to adapter
		 * 4. Adapter to spinner
		 * 5. Set listeners
		 * 		5.1. Touch
		 * 		5.2. Click
		 * 9. Show dialog
			----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_filter_list);
		
		// Title
		dlg.setTitle(R.string.dlg_filter_list_tv_title);
		
		/*----------------------------
		 * 2. Prepare data for spinners
			----------------------------*/
		/*----------------------------
		 * 2.1. Stores
			----------------------------*/
		List<String> storeList = new ArrayList<String>();
		
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase db = dbm.getReadableDatabase();
		
		Cursor c = dbm.getAllData(db, "stores", CONS.DB.columns_for_table_stores_with_index);
		
		// All
		storeList.add(actv.getString(R.string.generic_label_all));
		
		//
		c.moveToFirst();
		
		for (int i = 0; i < c.getCount(); i++) {
			//
			storeList.add(c.getString(1));
			
			//
			c.moveToNext();
		}//for (int i = 0; i < c.getCount(); i++)
		
		/*----------------------------
		 * 2.2. Genres
			----------------------------*/
		List<String> genreList = new ArrayList<String>();
		
		c = dbm.getAllData(db, "genres", CONS.DB.columns_for_table_genres_with_index);
		
		// All
		genreList.add(actv.getString(R.string.generic_label_all));
		
		//
		c.moveToFirst();
		
		for (int i = 0; i < c.getCount(); i++) {
			//
			genreList.add(c.getString(1));
			
			//
			c.moveToNext();
		}//for (int i = 0; i < c.getCount(); i++)
		
		/*----------------------------
		 * 2.3. Close db
			----------------------------*/
		db.close();
		
		/*----------------------------
		 * 3. Set data to adapter
			----------------------------*/
		// Stores
		ArrayAdapter<String> adapterStore = new ArrayAdapter<String>(
	              actv, android.R.layout.simple_spinner_item, storeList);
		
		// Stores
		ArrayAdapter<String> adapterGenre = new ArrayAdapter<String>(
	              actv, android.R.layout.simple_spinner_item, genreList);
		
		// Drop down view
		adapterStore.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		
		adapterGenre.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		
		/*----------------------------
		 * 4. Adapter to spinner
			----------------------------*/
		//
		Spinner spStore = (Spinner) dlg.findViewById(R.id.dlg_filter_list_sp_store);
		Spinner spGenre = (Spinner) dlg.findViewById(R.id.dlg_filter_list_sp_genre);
		
		spStore.setAdapter(adapterStore);
		spGenre.setAdapter(adapterGenre);
		
		/*----------------------------
		 * 5. Set listeners
			----------------------------*/
		/*----------------------------
		 * 5.1. Touch
			----------------------------*/
		// View
		Button btn_ok = (Button) dlg.findViewById(R.id.dlg_filter_list_bt_ok);
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_filter_list_bt_cancel);
		
		// Tags
		btn_ok.setTag(Tags.DialogTags.dlg_filter_list_ok);
		btn_cancel.setTag(Tags.DialogTags.dlg_filter_list_cancel);
		
		// Set
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv));
		
		/*----------------------------
		 * 5.2. Click
			----------------------------*/
		// 
		btn_ok.setOnClickListener(new DB_OCL(actv, dlg));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		/*----------------------------
		 * 9. Show dialog
			----------------------------*/
		dlg.show();
		
	}//public static void dlg_filterList(Activity actv)

	public static void dlg_filterList2(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Prepare data for spinners
		 * 		2.1. Stores
		 * 		2.2. Genres
		 * 		2.3. Close db
		 * 3. Set data to adapter
		 * 4. Adapter to spinner
		 * 5. Set listeners
		 * 		5.1. Touch
		 * 		5.2. Click
		 * 9. Show dialog
			----------------------------*/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_filter_list);
		
		// Title
		dlg.setTitle(R.string.dlg_filter_list_tv_title);
		
		/*----------------------------
		 * 2. Prepare data for spinners
			----------------------------*/
		/***************************************
		 * 2.1. Stores
		 ***************************************/
		List<String> storeList = new ArrayList<String>();
		
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase db = dbm.getReadableDatabase();
		
		Cursor c = dbm.getAllData(db, "stores", CONS.DB.columns_for_table_stores_with_index);
		
		// All
		storeList.add(actv.getString(R.string.generic_label_all));
		
		//
		c.moveToFirst();
		
		for (int i = 0; i < c.getCount(); i++) {
			//
			storeList.add(c.getString(1));
			
			//
			c.moveToNext();
		}//for (int i = 0; i < c.getCount(); i++)
		
		/***************************************
		 * 2.2. Genres
		 ***************************************/
		List<String> genreList = new ArrayList<String>();
		
		c = dbm.getAllData(db, "genres", CONS.DB.columns_for_table_genres_with_index);
		
		// All
		genreList.add(actv.getString(R.string.generic_label_all));
		
		//
		c.moveToFirst();
		
		for (int i = 0; i < c.getCount(); i++) {
			//
			genreList.add(c.getString(1));
			
			//
			c.moveToNext();
		}//for (int i = 0; i < c.getCount(); i++)
		
		/*----------------------------
		 * 2.3. Close db
			----------------------------*/
		db.close();
		
		/***************************************
		 * 3. Set data to adapter
		 ***************************************/
		// Stores
		ArrayAdapter<String> adapterStore = new ArrayAdapter<String>(
	              actv, android.R.layout.simple_spinner_item, storeList);
		
		// Stores
		ArrayAdapter<String> adapterGenre = new ArrayAdapter<String>(
	              actv, android.R.layout.simple_spinner_item, genreList);
		
		// Drop down view
		adapterStore.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		
		adapterGenre.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);

		/***************************************
		 * 4. Adapter to spinner
		 ***************************************/
		//
		Spinner spStore = (Spinner) dlg.findViewById(R.id.dlg_filter_list_sp_store);
		Spinner spGenre = (Spinner) dlg.findViewById(R.id.dlg_filter_list_sp_genre);
		
		spStore.setAdapter(adapterStore);
		spGenre.setAdapter(adapterGenre);
		
		/*----------------------------
		 * 5. Set listeners
			----------------------------*/
		/***************************************
		 * 5.1. Touch
		 ***************************************/
		// View
		Button btn_ok = (Button) dlg.findViewById(R.id.dlg_filter_list_bt_ok);
		Button btn_cancel = (Button) dlg.findViewById(R.id.dlg_filter_list_bt_cancel);
		
		// Tags
		btn_ok.setTag(Tags.DialogTags.dlg_filter_list_ok2);
		btn_cancel.setTag(Tags.DialogTags.dlg_filter_list_cancel);
		
		// Set
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv));
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv));
		
		/***************************************
		 * 5.2. Click
		 ***************************************/
		// 
		btn_ok.setOnClickListener(new DB_OCL(actv, dlg));
		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg));
		
		/*----------------------------
		 * 9. Show dialog
			----------------------------*/
		dlg.show();
		
	}//public static void dlg_filterList2(Activity actv)

	public static void filterList(Activity actv, Dialog dlg) {
		/*----------------------------
		 * Steps
		 * 1. Get db
		 * 2. Get store name and genre name
		 * 2-2. Dismiss dlg
		 * 3. Build query
		 * 4. Exec query
		 * 5. Update list
		 * 6. Close db
		 * 7. Notify adapter
		 * 8. Sort adapter
			----------------------------*/
		// 
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase db = dbm.getReadableDatabase();

		/*----------------------------
		 * 2. Get store name and genre name
			----------------------------*/
		Spinner spStore = (Spinner)dlg.findViewById(R.id.dlg_filter_list_sp_store);
		Spinner spGenre = (Spinner)dlg.findViewById(R.id.dlg_filter_list_sp_genre);
		
		String storeName = (String) spStore.getSelectedItem();
		String genreName = (String) spGenre.getSelectedItem();

		/*----------------------------
		 * 2-2. Dismiss dlg
			----------------------------*/
		dlg.dismiss();

		/*----------------------------
		 * 3. Build query
			----------------------------*/
		//
		String query;
		
		// Both are "All"
		if (storeName.equals(actv.getString(R.string.generic_label_all)) &&
				genreName.equals(actv.getString(R.string.generic_label_all))) {
			query = "SELECT * FROM " + CONS.DB.tableName;

		// Store => All, Genre => Specific
		} else if (storeName.equals(actv.getString(R.string.generic_label_all)) &&
						!genreName.equals(actv.getString(R.string.generic_label_all))) {
			
			query = "SELECT * FROM " + CONS.DB.tableName + 
							" WHERE genre is '" + genreName + "'";
					
		// Store => Specific, Genre => All
		} else if (!storeName.equals(actv.getString(R.string.generic_label_all)) &&
						genreName.equals(actv.getString(R.string.generic_label_all))) {
			
			query = "SELECT * FROM " + CONS.DB.tableName + 
					" WHERE store is '" + storeName + "'";

		// Store => Specific, Genre => Specific
		} else {
			
			query = "SELECT * FROM " + CONS.DB.tableName + 
					" WHERE store is '" + storeName + "'" + " AND " +
					"genre is '" + genreName + "'";
			
		}//if (storeName.equals(actv.getString(R.string.generic_label_all)))
		
		/*----------------------------
		 * 4. Exec query
			----------------------------*/
		Cursor c = db.rawQuery(query, null);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount() => " + c.getCount());
		
		/*----------------------------
		 * 5. Update list
			----------------------------*/
		//
		c.moveToFirst();
		
		ItemListActv.list.clear();
		
		//
		for (int i = 0; i < c.getCount(); i++) {
			//
//			ShoppingItem item = new ShoppingItem(
//									c.getString(1),		// store
//									c.getString(2),		// name
//									c.getInt(3),			// price
//									c.getString(4),		// genre
//									c.getInt(0)				// id
//									);

			SI item = new SI(
					c.getInt(0),		// id
					c.getString(1),		// store
					c.getString(2),		// name
					c.getInt(3),		// price
					c.getString(4),		//	genre
					c.getString(5)			// yomi
					);
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c.getString(0) => " + c.getString(0));
			
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c.getString(1) => " + c.getString(1));
			
			//
			ItemListActv.list.add(item);
			
			//
			c.moveToNext();

		}//for (int i = 0; i < c.getCount(); i++)

		/*----------------------------
		 * 6. Close db
			----------------------------*/
		db.close();
		
		/*----------------------------
		 * 7. Notify adapter
			----------------------------*/
		ItemListActv.adapter.notifyDataSetChanged();
		
		/*----------------------------
		 * 8. Sort adapter
			----------------------------*/
		Comparator<Object> cmp = new Comparator<Object>(){

//			@Override
			public int compare(Object obj1, Object obj2) {
				// 
				String itemName1 = ((SI) obj1).getName();
				String itemName2 = ((SI) obj2).getName();
				
				return itemName1.compareToIgnoreCase(itemName2);
			}//public int compare(Object obj1, Object obj2)
			
		};//Comparator<Object> cmp = new Comparator<Object>()
		
		// Sort
		ItemListActv.adapter.sort(cmp);
		
	}//public static void filterList(Activity actv, Dialog dlg)

	public static void filterList2(Activity actv, Dialog dlg) {
		/*----------------------------
		 * Steps
		 * 1. Get db
		 * 2. Get store name and genre name
		 * 2-2. Dismiss dlg
		 * 3. Build query
		 * 4. Exec query
		 * 5. Update list
		 * 6. Close db
		 * 7. Notify adapter
		 * 8. Sort adapter
			----------------------------*/
		// 
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase db = dbm.getReadableDatabase();

		/***************************************
		 * 2. Get store name and genre name
		 ***************************************/
		Spinner spStore = (Spinner)dlg.findViewById(R.id.dlg_filter_list_sp_store);
		Spinner spGenre = (Spinner)dlg.findViewById(R.id.dlg_filter_list_sp_genre);
		
		String storeName = (String) spStore.getSelectedItem();
		String genreName = (String) spGenre.getSelectedItem();

		/***************************************
		 * 2-2. Dismiss dlg
		 ***************************************/
		dlg.dismiss();

		/***************************************
		 * 3. Build query
		 ***************************************/
		//
//		String query;
		String query = filterList2__buildQuery(actv, storeName, genreName);
		
		/***************************************
		 * 4. Exec query
		 ***************************************/
		Cursor c = db.rawQuery(query, null);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount() => " + c.getCount());
		
		/***************************************
		 * Validations
		 ***************************************/
		if (c.getCount() < 1) {
			
			db.close();
			
			// debug
			Toast.makeText(actv, "No entry", Toast.LENGTH_LONG).show();
			
			return;
			
		}//if (c.getCount() == condition)
		
		/***************************************
		 * 5. Update list
		 ***************************************/
		//
		c.moveToFirst();
		
//		ItemListActv.list.clear();
		CONS.TabActv.itemList.clear();
		
		//
		for (int i = 0; i < c.getCount(); i++) {
			
//			{android.provider.BaseColumns._ID, "name", "yomi", "genre", "store", "price"}
			//

			SI item = new SI(
					c.getInt(0),		// id
					c.getString(1),		// store
					c.getString(2),		// name
					c.getInt(3),		// price
					c.getString(4),		//	genre
					c.getString(5)			// yomi
					);
			
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "c.getString(0) => " + c.getString(0));
//			
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "c.getString(1) => " + c.getString(1));
//			
//			//
////			ItemListActv.list.add(item);
			CONS.TabActv.itemList.add(item);
			
			//
			c.moveToNext();

		}//for (int i = 0; i < c.getCount(); i++)

		/***************************************
		 * 6. Close db
		 ***************************************/
		db.close();

		/***************************************
		 * Sort list
		 ***************************************/
		Methods_sl.sortItemList(CONS.TabActv.itemList);
		
		/*----------------------------
		 * 7. Notify adapter
			----------------------------*/
//		ItemListActv.adapter.notifyDataSetChanged();
		CONS.TabActv.adpItems.notifyDataSetChanged();
		
		
		
//		/*----------------------------
//		 * 8. Sort adapter
//			----------------------------*/
//		Comparator<Object> cmp = new Comparator<Object>(){
//
////			@Override
//			public int compare(Object obj1, Object obj2) {
//				// 
//				String itemName1 = ((ShoppingItem) obj1).getName();
//				String itemName2 = ((ShoppingItem) obj2).getName();
//				
//				return itemName1.compareToIgnoreCase(itemName2);
//			}//public int compare(Object obj1, Object obj2)
//			
//		};//Comparator<Object> cmp = new Comparator<Object>()
		
		// Sort
//		ItemListActv.adapter.sort(cmp);
		
	}//public static void filterList2(Activity actv, Dialog dlg)

	public static void
	filterList3(Activity actv, String storeName, String genreName) {
		/*----------------------------
		 * Steps
		 * 1. Get db
		 * 2. Get store name and genre name
		 * 2-2. Dismiss dlg
		 * 3. Build query
		 * 4. Exec query
		 * 5. Update list
		 * 6. Close db
		 * 7. Notify adapter
		 * 8. Sort adapter
			----------------------------*/
		// 
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase db = dbm.getReadableDatabase();

		/***************************************
		 * 3. Build query
		 ***************************************/
		//
//		String query;
		String query = filterList2__buildQuery(actv, storeName, genreName);
		
		/***************************************
		 * 4. Exec query
		 ***************************************/
		Cursor c = db.rawQuery(query, null);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount() => " + c.getCount());
		
		if (c.getCount() < 1) {
			
			db.close();
			
			// debug
			Toast.makeText(actv, "No entry", Toast.LENGTH_LONG).show();
			
			return;
			
		}//if (c.getCount() == condition)
		
		/***************************************
		 * 5. Update list
		 ***************************************/
		//
//		c.moveToFirst();
		
//		ItemListActv.list.clear();
		CONS.TabActv.itemList.clear();
		
		while(c.moveToNext()) {
			
			SI item = new SI(
					c.getInt(0),		// id
					c.getString(1),		// store
					c.getString(2),		// name
					c.getInt(3),		// price
					c.getString(4),		//	genre
					c.getString(5)			// yomi
					);
			
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "c.getString(0) => " + c.getString(0));
//			
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "c.getString(1) => " + c.getString(1));
//			
//			//
////			ItemListActv.list.add(item);
			CONS.TabActv.itemList.add(item);
			
		}

//		for (int i = 0; i < c.getCount(); i++) {
//			
////			{android.provider.BaseColumns._ID, "name", "yomi", "genre", "store", "price"}
//			//
//
//			ShoppingItem item = new ShoppingItem(
//					c.getInt(0),		// id
//					c.getString(1),		// store
//					c.getString(2),		// name
//					c.getInt(3),		// price
//					c.getString(4),		//	genre
//					c.getString(5)			// yomi
//					);
//			
////			// Log
////			Log.d("Methods.java" + "["
////					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////					+ "]", "c.getString(0) => " + c.getString(0));
////			
////			Log.d("Methods.java" + "["
////					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////					+ "]", "c.getString(1) => " + c.getString(1));
////			
////			//
//////			ItemListActv.list.add(item);
//			CONS.TabActv.itemList.add(item);
//			
//			//
//			c.moveToNext();
//
//		}//for (int i = 0; i < c.getCount(); i++)

		/***************************************
		 * 6. Close db
		 ***************************************/
		db.close();

		/***************************************
		 * Sort list
		 ***************************************/
		Methods_sl.sortItemList(CONS.TabActv.itemList);
		
		/*----------------------------
		 * 7. Notify adapter
			----------------------------*/
//		ItemListActv.adapter.notifyDataSetChanged();
		CONS.TabActv.adpItems.notifyDataSetChanged();

	}//filterList3(Activity actv, String storeName, String genreName)

	private static
	String filterList2__buildQuery
	(Activity actv, String storeName, String genreName) {
		
		String query = null;
		
		
		// Both are "All"
		if (storeName.equals(actv.getString(R.string.generic_label_all)) &&
				genreName.equals(actv.getString(R.string.generic_label_all))) {
			query = "SELECT * FROM " + CONS.DB.tableName;

		// Store => All, Genre => Specific
		} else if (storeName.equals(actv.getString(R.string.generic_label_all)) &&
						!genreName.equals(actv.getString(R.string.generic_label_all))) {
			
			query = "SELECT * FROM " + CONS.DB.tableName + 
							" WHERE genre = '" + genreName + "'";
					
		// Store => Specific, Genre => All
		} else if (!storeName.equals(actv.getString(R.string.generic_label_all)) &&
						genreName.equals(actv.getString(R.string.generic_label_all))) {
			
			query = "SELECT * FROM " + CONS.DB.tableName + 
					" WHERE store = '" + storeName + "'";

		// Store => Specific, Genre => Specific
		} else {
			
			query = "SELECT * FROM " + CONS.DB.tableName + 
					" WHERE store = '" + storeName + "'" + " AND " +
					"genre = '" + genreName + "'";
			
		}//if (storeName.equals(actv.getString(R.string.generic_label_all)))

		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "sql=" + query);

//		
//		// Both are "All"
//		if (storeName.equals(actv.getString(R.string.generic_label_all)) &&
//				genreName.equals(actv.getString(R.string.generic_label_all))) {
//			query = "SELECT * FROM " + CONS.DBAdmin.tableName;
//
//		// Store => All, Genre => Specific
//		} else if (storeName.equals(actv.getString(R.string.generic_label_all)) &&
//						!genreName.equals(actv.getString(R.string.generic_label_all))) {
//			
//			query = "SELECT * FROM " + CONS.DBAdmin.tableName + 
//							" WHERE genre is '" + genreName + "'";
//					
//		// Store => Specific, Genre => All
//		} else if (!storeName.equals(actv.getString(R.string.generic_label_all)) &&
//						genreName.equals(actv.getString(R.string.generic_label_all))) {
//			
//			query = "SELECT * FROM " + CONS.DBAdmin.tableName + 
//					" WHERE store is '" + storeName + "'";
//
//		// Store => Specific, Genre => Specific
//		} else {
//			
//			query = "SELECT * FROM " + CONS.DBAdmin.tableName + 
//					" WHERE store is '" + storeName + "'" + " AND " +
//					"genre is '" + genreName + "'";
//			
//		}//if (storeName.equals(actv.getString(R.string.generic_label_all)))
//
//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "sql=" + query);

		return query;
		
	}//String filterList2__buildQuery()

	/****************************************
	 *
	 * 
	 * <Caller> 
	 * 1. ShoppingList.add_listeners()
	 * 
	 *  <Desc> 
	 *  1. REF=> ChineseReader2\src\cr2\main\Utils.java
	 *  
	 *  <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 ****************************************/
	public static void setOnTouchListener_button(Activity actv, Tags.ViewNames viewName, 
			Tags.ButtonTags tagName, int resourceId) {
		//
	//		if (viewName.equals("tv")) {
		/*----------------------------
		 * memo: Second param needs not to be enum. To avoid mistyping,
		 * 				I decieded to use enum instead of raw string :20120721_182940
			----------------------------*/
		
		if (viewName == Tags.ViewNames.TV) {
			// Get the view
			TextView tv = (TextView) actv.findViewById(resourceId);
			  
			// Set a tag
			tv.setTag(tagName);
			  
			// Set a listener
			tv.setOnTouchListener(new ButtonOnTouchListener(actv));
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Listener set => " + tv.toString());

//		} else if (viewName.equals("bt")) {//if (viewName.equals("textview"))
		} else if (viewName == Tags.ViewNames.BT) {//if (viewName.equals("textview"))
			// Get the view
			Button bt = (Button) actv.findViewById(resourceId);
			  
			// Set a tag
			bt.setTag(tagName);
			  
			// Set a listener
			bt.setOnTouchListener(new ButtonOnTouchListener(actv));

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Listener set => " + bt.toString());
		
		}//if (viewName.equals("textview"))
		
	}//public void setOnTouchListener_button()

	public static void dlg_register_main(Activity actv) {
		/*----------------------------
		 * Steps
		 * 1. Get a dialog
		 * 2. List view
		 * 3. Set listener => list
		 * 9. Show dialog
			----------------------------*/
		 
		Dialog dlg = Methods_dlg.dlg_template_cancel(actv, 
				R.layout.dlg_register_main, R.string.generic_register,
				R.id.dlg_register_main_btn_cancel,
				Tags.DialogTags.dlg_generic_cancel);
		
		/*----------------------------
		 * 2. List view
		 * 		1. Get view
		 * 		2. Prepare list data
		 * 		3. Prepare adapter
		 * 		4. Set adapter
			----------------------------*/
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_register_main_lv_list);
		
		/*----------------------------
		 * 2.2. Prepare list data
			----------------------------*/
//		List<String> registerItems = new ArrayList<String>();
		List<CONS.Enum.registerChoice> registerItems = 
					new ArrayList<CONS.Enum.registerChoice>();
		
		for (CONS.Enum.registerChoice item : CONS.Enum.registerChoice.values()) {
			
			registerItems.add(item);
			
		}//for (String string : ShoppingListActivity.registerItems)
		
//		ArrayAdapter<String> adp = new ArrayAdapter<String>(
		ArrayAdapter<CONS.Enum.registerChoice> adp = 
				new ArrayAdapter<CONS.Enum.registerChoice>(
		
				actv,
				android.R.layout.simple_list_item_1,
				registerItems
		);
		
		/*----------------------------
		 * 2.4. Set adapter
			----------------------------*/
		lv.setAdapter(adp);
		
		/*----------------------------
		 * 3. Set listener => list
			----------------------------*/
		lv.setOnItemClickListener(
						new DOI_CL(
								actv, 
								dlg, 
								Tags.DialogTags.dlg_register_main));
		
		/*----------------------------
		 * 9. Show dialog
			----------------------------*/
		dlg.show();
		
	}//public static void dlg_register_main(Activity actv)
	
	public static void db_backup(Activity actv) {
		/*----------------------------
		 * 1. Prep => File names
		 * 2. Prep => Files
		 * 2-2. Folder exists?
		 * 3. Copy
			----------------------------*/
		String dirPath_db = "/data/data/shoppinglist.main/databases";
		
		String fileName_db = "shopping_list.db";
		
		String dirName_ExternalStorage = "/mnt/sdcard-ext";
		
		String dirPath_db_backup = dirName_ExternalStorage + "/ShoppingList_backup";
		
		String fileName_db_backup_trunk = "ShopplingList_backup";
		
		String fileName_db_backup_ext = ".bk";

		String time_label = Methods.get_TimeLabel(Methods.getMillSeconds_now());
		
		String db_src = StringUtils.join(new String[]{dirPath_db, fileName_db}, File.separator);
		
		String db_dst = StringUtils.join(new String[]{dirPath_db_backup, fileName_db_backup_trunk}, File.separator);
		db_dst = db_dst + "_" + time_label + fileName_db_backup_ext;
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "db_src: " + db_src + " * " + "db_dst: " + db_dst);
		
		/*----------------------------
		 * 2. Prep => Files
			----------------------------*/
		File src = new File(db_src);
		File dst = new File(db_dst);
		
		/*----------------------------
		 * 2-2. Folder exists?
			----------------------------*/
		File db_backup = new File(dirPath_db_backup);
		
		if (!db_backup.exists()) {
			
			try {
				db_backup.mkdir();
				
				// Log
				Log.d("Methods.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "Folder created: " + db_backup.getAbsolutePath());
			} catch (Exception e) {
				
				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Create folder => Failed");
				
				return;
				
			}
			
		} else {//if (!db_backup.exists())
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Folder exists: ");
			
		}//if (!db_backup.exists())
		
		/*----------------------------
		 * 3. Copy
			----------------------------*/
		try {
			FileChannel iChannel = new FileInputStream(src).getChannel();
			FileChannel oChannel = new FileOutputStream(dst).getChannel();
			iChannel.transferTo(0, iChannel.size(), oChannel);
			iChannel.close();
			oChannel.close();
			
			// Log
			Log.d("ThumbnailActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "File copied");
			
			// debug
			Toast.makeText(actv, "DB backup => Done", Toast.LENGTH_LONG).show();
			
		} catch (FileNotFoundException e) {
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
		} catch (IOException e) {
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
		}//try

	}//public static void db_backup(Activity actv, Dialog dlg, String item)

	public static long getMillSeconds_now() {
		
		Calendar cal = Calendar.getInstance();
		
		return cal.getTime().getTime();
		
	}//private long getMillSeconds_now(int year, int month, int date)

	public static String get_TimeLabel(long millSec) {
		
		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd_HHmmss");
		 
		return sdf1.format(new Date(millSec));
		
	}//public static String get_TimeLabel(long millSec)

	public static boolean restore_db(Activity actv, String dbName,
			String src, String dst) {
		/*********************************
		 * 1. Setup db
		 * 2. Setup: File paths
		 * 3. Setup: File objects
		 * 4. Copy file
		 * 
		 *********************************/
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"src=" + src
				+ "/"
				+ "dst=" + dst);
		
		/*********************************
		 * 3. Setup: File objects
		 *********************************/
		File f_src = new File(src);
		File f_dst = new File(dst);
	
		/*********************************
		 * 4. Copy file
		 *********************************/
		try {
			FileChannel iChannel = new FileInputStream(src).getChannel();
			FileChannel oChannel = new FileOutputStream(dst).getChannel();
			iChannel.transferTo(0, iChannel.size(), oChannel);
			iChannel.close();
			oChannel.close();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "File copied: " + src);
			
			// debug
			Toast.makeText(actv, "DB restoration => Done", Toast.LENGTH_LONG).show();
			
			return true;
	
		} catch (FileNotFoundException e) {

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Exception: " + e.toString());
			
			return false;
			
		} catch (IOException e) {

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Exception: " + e.toString());
			
			return false;
			
		}//try
		
	}//private boolean restore_db()

	public static String getFileNameFromDir_latest(Activity actv,
							String dirPath_dbBackup) {
		
		File f = new File(dirPath_dbBackup);
		
		File[] fileList = f.listFiles();
		
		if (fileList.length < 1) {
			
			return null;
			
		}//if (fileList.length < 1)
		
		String latestFileName = fileList[0].getName();
	
		for (int i = 0; i < fileList.length - 1; i++) {
			
			if (fileList[i].lastModified() < fileList[i+1].lastModified()) {
				
				latestFileName = fileList[i+1].getName();
				
			}//if (fileList[0])
			
		}//for (int i = 0; i < fileList.length; i++)
		
		return latestFileName;
		
	}//public static String getFileNameFromDir_latest

	public static int backupDb(Activity actv,
						String dbName, String dirPathBk) {
		/*----------------------------
		 * 1. Prep => File names
		 * 2. Prep => Files
		 * 2-2. Folder exists?
		 * 3. Copy
			----------------------------*/
//		String time_label = Methods.get_TimeLabel(Methods.getMillSeconds_now());
		String timeLabel = Methods.getTimeLabel(Methods.getMillSeconds_now());
		
		String db_src = StringUtils.join(
						new String[]{
								CONS.DB.dirPath_db,
								dbName},
						File.separator);
		
		String db_dst = StringUtils.join(
						new String[]{
								dirPathBk,
								CONS.DB.fileName_db_backup_trunk},
						File.separator);
		
		db_dst = db_dst + "_" + timeLabel + CONS.DB.fileName_db_backup_ext;
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "db_src: " + db_src + " * " + "db_dst: " + db_dst);
		
		/*----------------------------
		 * 2. Prep => Files
			----------------------------*/
		File src = new File(db_src);
		File dst = new File(db_dst);
		
		/*********************************
		 * DB file exists?
		 *********************************/
		File f = new File(db_src);
		
		if (f.exists()) {
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "DB file exists=" + f.getAbsolutePath());
		} else {//if (f.exists())
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "File doesn't exist=");
			
			return CONS.RV.DB_DOESNT_EXIST;
			
		}//if (f.exists())

		/*----------------------------
		 * 2-2. Folder exists?
			----------------------------*/
		File db_backup = new File(dirPathBk);
		
		if (!db_backup.exists()) {
			
			try {
				db_backup.mkdir();

				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Folder created: " + db_backup.getAbsolutePath());
				
			} catch (Exception e) {
				
				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Create folder => Failed");
				
				return CONS.RV.DB_CANT_CREATE_FOLDER;
				
			}
			
		} else {//if (!db_backup.exists())

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Folder exists: " + db_backup.getAbsolutePath());
			
		}//if (!db_backup.exists())
		
		/*----------------------------
		 * 3. Copy
			----------------------------*/
		try {
			FileChannel iChannel = new FileInputStream(src).getChannel();
			FileChannel oChannel = new FileOutputStream(dst).getChannel();
			iChannel.transferTo(0, iChannel.size(), oChannel);
			iChannel.close();
			oChannel.close();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "File copied");
			
			return CONS.RV.DB_BACKUP_SUCCESSFUL;

		} catch (FileNotFoundException e) {

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Exception: " + e.toString());
			
			return CONS.RV.DB_FILE_COPY_EXCEPTION;
			
		} catch (IOException e) {

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Exception: " + e.toString());
			
			return CONS.RV.DB_FILE_COPY_EXCEPTION;
			
		}//try
		
	}//public static void backupDb()

	/******************************
		@return
			-1 Create folder => not successful<br>
			-2 Create folder => Exception<br>
			-3 FileNotFoundException<br>
			-4 IOException<br>
			1 backup => done<br>
	 ******************************/
	public static int 
	backup_DB
	(Activity actv) {
		/****************************
		 * 1. Prep => File names
		 * 2. Prep => Files
		 * 2-2. Folder exists?
		 * 
		 * 2-3. Dst folder => Files within the limit?
		 * 3. Copy
			****************************/
		String time_label = Methods.get_TimeLabel(Methods.getMillSeconds_now());
		
		String db_Src = StringUtils.join(
					new String[]{
							actv.getDatabasePath(CONS.DB.dbName).getPath()},
//							CONS.fname_db},
					File.separator);
		
		String db_Dst_Folder = StringUtils.join(
					new String[]{
							CONS.DB.dPath_dbFile_Backup,
							CONS.DB.fname_DB_Backup_Trunk},
//							CONS.dpath_db_backup,
//							CONS.fname_db_backup_trunk},
					File.separator);
		
		String db_Dst = db_Dst_Folder + "_"
				+ time_label
//				+ MainActv.fileName_db_backup_ext;
				+ CONS.DB.fname_DB_Backup_ext;
//		+ CONS.fname_db_backup_ext;
//				+ MainActv.fname_db_backup_trunk;

		// Log
		String msg_log = "db_Src = " + db_Src
						+ " / "
						+ "db_Dst = " + db_Dst;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_log);
		
		/****************************
		 * 2. Prep => Files
			****************************/
		File src = new File(db_Src);
		File dst = new File(db_Dst);
		
		/****************************
		 * 2-2. Folder exists?
			****************************/
		File db_Backup = new File(CONS.DB.dPath_dbFile_Backup);
//		File db_backup = new File(CONS.dpath_db_backup);
		
		if (!db_Backup.exists()) {
			
			try {
				
				db_Backup.mkdirs();
//				db_Backup.mkdir();
				
				/******************************
					validate
				 ******************************/
				if (db_Backup.isDirectory()) {
					
					// Log
					Log.d("Methods.java" + "["
							+ Thread.currentThread().getStackTrace()[2].getLineNumber()
							+ "]", "Folder created: " + db_Backup.getAbsolutePath());
					
				} else {
					
					// Log
					String msg_Log = "Create folder => not successful";
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
					return -1;
					
				}
				
			} catch (Exception e) {
				
				// Log
				Log.e("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Create folder => Exception");
				
				return -2;
				
			}
			
		} else {//if (!db_backup.exists())
			
			// Log
			Log.i("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Folder exists: ");
			
		}//if (!db_backup.exists())
		
		////////////////////////////////

		// Dst folder => Files within the limit?

		////////////////////////////////
		File[] files_dst_folder = new File(CONS.DB.dPath_dbFile_Backup).listFiles();
//		File[] files_dst_folder = new File(CONS.dpath_db_backup).listFiles();

		if (files_dst_folder != null) {
			
			int num_of_files = files_dst_folder.length;
			
			// Log
			Log.i("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "num of backup files = " + num_of_files);
		}
		
		
		/****************************
		 * 3. Copy
			****************************/
		try {
			
			FileInputStream fis = new FileInputStream(src);
			
			FileChannel iChannel = fis.getChannel();
//			FileChannel iChannel = new FileInputStream(src).getChannel();
			FileChannel oChannel = new FileOutputStream(dst).getChannel();
			iChannel.transferTo(0, iChannel.size(), oChannel);
			iChannel.close();
			oChannel.close();
			
			// Log
			Log.i("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "DB file copied");

		} catch (FileNotFoundException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "FileNotFoundException: " + e.toString());
			
			return -3;
			
		} catch (IOException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "IOException: " + e.toString());
			
			return -4;
			
		}//try

		return 1;
		
	}//public static boolean db_backup(Activity actv)

	public static String getTimeLabel(long millSec) {
		
		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd_HHmmss");
		 
		return sdf1.format(new Date(millSec));
		
	}//public static String get_TimeLabel(long millSec)
	
	/*********************************
	 * @param type => 1 ---> yyyyMMdd_HHmmss<br>
	 * 				2 ---> yyyy-MM-dd HH:mm:ss.S
	 *********************************/
	public static String getTimeLabel_V2(long millSec, int type) {
		
		SimpleDateFormat sdf1 = null;
		
		switch(type) {
		
		case 1:
			
			sdf1 = new SimpleDateFormat("yyyyMMdd_HHmmss");
			
			break;
			
		case 2:
			
			sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			
			break;
			
		default:
			
			sdf1 = new SimpleDateFormat("yyyyMMdd_HHmmss");
			
			break;
		
		}
		
		return sdf1.format(new Date(millSec));
		
	}//public static String get_TimeLabel(long millSec)

	public static String getTimeLabel_Japanese(long millSec) {
		
		
//		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd_HHmmss");
//		SimpleDateFormat sdf1 = new SimpleDateFormat("MM月dd日", Locale.JAPAN);
		SimpleDateFormat sdf1 = new SimpleDateFormat("M月d日(E)", Locale.JAPAN);
		
		return sdf1.format(new Date(millSec));
		
	}//public static String get_TimeLabel(long millSec)

	public static int getArrayIndex(String[] targetArray, String targetString) {
		int index = -1;
		
		for (int i = 0; i < targetArray.length; i++) {
			
			if (targetArray[i].equals(targetString)) {
				
				index = i;
				
				break;
				
			}//if (targetArray[i] == )
			
		}//for (int i = 0; i < targetArray.length; i++)
		
		return index;
	}//public static int getArrayIndex(String[] targetArray, String targetString)

	public static boolean is_numeric(String str) {
		
		// REF=> http://www.coderanch.com/t/401142/java/java/check-if-String-value-numeric # Hurkpan Potgieter Greenhorn
		String regex = "((-|\\+)?[0-9]+(\\.[0-9]+)?)+";
		
//		Pattern p = Pattern.compile( "([0-9]*)\\.[0]" );
		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(str);
		
		return m.matches(); //TRUE
		
	}//public static boolean is_numeric(String str)

	/*********************************
	 * 20130213_134916
	 * convert_Kana2Gana(String s)
	 * 1. The name "Kana2Gana" is borrowed from: http://java.akjava.com/library/kanagana
	 * 2. The code from: http://www7a.biglobe.ne.jp/~java-master/samples/string/ZenkakuKatakanaToZenkakuHiragana.html
	 * 
	 *********************************/
	public static String convert_Kana2Gana(String s) {
		StringBuffer sb = new StringBuffer(s);
		
		for (int i = 0; i < sb.length(); i++) {
		
			char c = sb.charAt(i);
			
			if (c >= 'ァ' && c <= 'ン') {
				
				sb.setCharAt(i, (char)(c - 'ァ' + 'ぁ'));
				
			} else if (c == 'ヵ') {
				
				sb.setCharAt(i, 'か');
				
			} else if (c == 'ヶ') {
				
				sb.setCharAt(i, 'け');

			} else if (c == 'ヴ') {

				sb.setCharAt(i, 'う');

				i++;
			}
			
		}//for (int i = 0; i < sb.length(); i++)
		
		return sb.toString(); 
		
	}//public static String convert_Kana2Gana(String s)

	/*********************************
	 * 20130214_104250
	 * getYomi_getHttpEntity(String url)
	 *********************************/
	public static HttpEntity 
	getYomi_getHttpEntity(String url) {
		HttpPost httpPost = new HttpPost(url);
		
		httpPost.setHeader("Content-type", "application/json");
		
		/*********************************
		 * memo
		 *********************************/
		HttpUriRequest postRequest = httpPost;
		
		DefaultHttpClient dhc = new DefaultHttpClient();
		
		HttpResponse hr = null;
		
		/*********************************
		 * Execute request
		 *********************************/
		try {
			
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "Executing postRequest...");
			
			hr = dhc.execute(postRequest);
			
		} catch (ClientProtocolException e) {

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		} catch (IOException e) {

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}//try
		
		/*********************************
		 * Process response
		 *********************************/
		if (hr == null) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "hr == null");
			
			return null;
			
		}//if (hr == null)

		/*********************************
		 * Get status
		 *********************************/
		int statusCode = hr.getStatusLine().getStatusCode();
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "statusCode: " + statusCode);		

		return hr.getEntity();
		
	}//private static HttpEntity getYomi_B18_v_1_3__1_getHttpEntity(String sen)

	/*********************************
	 * 20130214_104945
	 * convert_HttpEntity2XmlString(HttpEntity entity)
	 *********************************/
	public static String
	convert_HttpEntity2XmlString(HttpEntity entity) {

		/*********************************
		 * Prepare: InputStream object
		 * Ref => http://symfoware.blog68.fc2.com/blog-entry-711.html
		 *********************************/
		String xmlString = null;
		
		try {
			
			xmlString = EntityUtils.toString(entity);
			
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", xmlString);
			
		} catch (ParseException e) {

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		} catch (IOException e) {

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;

		}
		
		return xmlString;
		
	}//private static XmlPullParser getYomi_B18_v_1_3__2_getXmlParser()

	/*********************************
	 * 20130214_105728
	 * getYomi_getXmlParser(String xmlString, String enc)
	 *********************************/
	public static XmlPullParser
	getYomi_getXmlParser(String xmlString, String enc) {

		/*********************************
		 * Prepare: InputStream object
		 * Ref => http://symfoware.blog68.fc2.com/blog-entry-711.html
		 *********************************/
		InputStream is = null;
		
		try {
			
			is = new ByteArrayInputStream(
									xmlString.getBytes(enc));
			
		} catch (UnsupportedEncodingException e) {

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}
		
		/*********************************
		 * Prepare: XML parser
		 * REF=> http://android.roof-balcony.com/shori/xml/xmlparse/
		 *********************************/
		XmlPullParser xmlPullParser = Xml.newPullParser();
		
		try {
			
			xmlPullParser.setInput(is, enc);
			
		} catch (XmlPullParserException e) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}
		
		return xmlPullParser;
		
	}//private static XmlPullParser getYomi_B18_v_1_3__2_getXmlParser()

	/*********************************
	 * 20130214_105749
	 * getYomi_B18_v_1_3__3_getFurigana(XmlPullParser xmlPullParser, String targetTag)
	 *********************************/
	public static String
	getYomi_getFurigana
		(XmlPullParser xmlPullParser, String targetTag) {

		String targetString = null;
		
//		StringBuilder sb = new StringBuilder();
		
		try {
			
			for(int e = xmlPullParser.getEventType();
					e != XmlPullParser.END_DOCUMENT;
					e = xmlPullParser.next()) {
				
				if(e == XmlPullParser.START_TAG &&
//						xmlPullParser.getName().equals("Furigana")) {
						xmlPullParser.getName().equals(targetTag)) {
					
					targetString = xmlPullParser.nextText();
					
					// Log
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]",
//							"Furigana=" + xmlPullParser.nextText());
							targetTag + "=" + targetString);
					
//					sb.append(targetString);
					
					return targetString;
					
				} else {//if
					
//					// Log
//					Log.d("Methods.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber()
//							+ ":"
//							+ Thread.currentThread().getStackTrace()[2]
//									.getMethodName() + "]",
//							"tag=" + xmlPullParser.getName());
					
				}//if
				
			}//for
			
		} catch (XmlPullParserException e) {

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		} catch (IOException e) {

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;

		}//try

		return targetString;
//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "sb.toString()=" + sb.toString());
//		
//		return sb.toString();
		
	}//getYomi_getFurigana(XmlPullParser xmlPullParser, String targetTag)

	/*********************************
	 * 20130214_114218
	 * getYomi_full(String kw, String enc)
	 *********************************/
	public static String[]
	getYomi_full(String kw, String enc) {
		/*********************************
		 * Build a url string
		 *********************************/
		String url = "http://jlp.yahooapis.jp/FuriganaService/V1/furigana" +
				"?appid=dj0zaiZpPTZjQWNRNExhd0thayZkPVlXazlhR2gwTTJGUE56SW1jR285TUEtLSZzPWNvbnN1bWVyc2VjcmV0Jng9Mjc-" +
				"&grade=1" +
				"&sentence=" + kw;
		
		/*********************************
		 * Get: Http entity
		 *********************************/
		HttpEntity entity = Methods.getYomi_getHttpEntity(url);

		if (entity == null) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "entity == null");
			
			return null;
			
		}//if (entity == null)

		/*********************************
		 * Get: XMLPullParser
		 *********************************/
		String xmlString =
					Methods.convert_HttpEntity2XmlString(entity);
		
		XmlPullParser xmlPullParser =
				Methods.getYomi_getXmlParser(xmlString, enc);
		
		/*********************************
		 * Extract: Furigana
		 *********************************/
		String furigana =
				Methods.getYomi_getFurigana(xmlPullParser, "Furigana");
		
		/*********************************
		 * Extract: Surface
		 *********************************/
		xmlPullParser =
				Methods.getYomi_getXmlParser(xmlString, "utf-8");
		
		String surface =
				Methods.getYomi_getFurigana(xmlPullParser, "Surface");

		/*********************************
		 * Return
		 *********************************/
//		if (furigana == null) {
		if (furigana == null && surface == null) {

			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"surface=" + surface + "/"
					+ "furigana == null");

			return null;
			
		} else {//if (furigana == null)
			
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]",
//					"surface=" + surface + "/"
//					+ "furigana = " + furigana);
			
			return new String[]{kw, surface, furigana};
			
		}//if (furigana == null)

	}//private static String[] getYomi_full(String kw, String enc)

	public static int[]
	getDisplaySize(Activity actv) {

		Display disp=((WindowManager)actv.getSystemService(
				Context.WINDOW_SERVICE)).getDefaultDisplay();
		int w=disp.getWidth();
		int h=disp.getHeight();
		
		return new int[]{w, h};

	}//getDisplaySize(Activity actv)

	public static boolean createTable(Activity actv, String dbName,
			String t_name, String[] colNames, String[] colTypes) {

		// db setup
		DBUtils dbu = new DBUtils(actv, dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		// Create a table
		boolean res = dbu.createTable(wdb, t_name, 
//					dbu.get_cols(), dbu.get_col_types());
				colNames, colTypes);
		
		// Close db
		wdb.close();
		
		// Return
		return res;

	}//public static boolean create_table

	public static int getTableSize(Activity actv, String dbName,
			String tname) {
		// TODO Auto-generated method stub
		
		int count = -1;
		
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase rdb = dbm.getReadableDatabase();
		
		Cursor c = null;

		String sql = "SELECT * FROM " + tname;
		
		
		try {
			
			c = rdb.rawQuery(sql, null);

			count = c.getCount();

		} catch (Exception e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
		} finally {
			
			rdb.close();
			
			return count;
		}

	}//public static int getTableSize

	
	public static int
	getSmallerNumber(int i, int j)
			throws NumberFormatException{
		
		if (Methods.is_numeric(String.valueOf(i)) == false
				|| Methods.is_numeric(String.valueOf(j)) == false) {
			// REF=> http://www.tohoho-web.com/java/exception.htm
			throw new NumberFormatException("Not a number");
			
		}//if (Methods.is_numeric(String.valueOf(i)) == false \)
		
		if (i > j) {
			
			return j;
			
		} else {//if (i > j)
			
			return i;
			
		}//if (i > j)
		
	}//public static int getSmallerNumber(int i, int j)


	public static int[] getDateArrayFromLongData(long dueDate) {
		
		Date d = new Date(dueDate);
		
		Calendar cal = Calendar.getInstance();
		
		if (cal == null) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "cal => null");
			
			return null;
			
		}//if (cal == null)
		
		cal.setTime(d);
		
//		return new int[]{cal.YEAR, cal.MONTH, cal.DATE};
		return new int[]{
				cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE)
				};
		
	}//public static int[] getDateArrayFromLongData(long dueDate)

	public static List<String>
	get_ColumnNames(Activity actv, String tname) {
		
		List<String> names = new ArrayList<String>();
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		Cursor c = rdb.rawQuery("PRAGMA table_info(" + tname + ")", null);
		
		if ( c.moveToFirst() ) {
		    do {
		    	
		    	names.add(c.getString(1));
		        
		    } while (c.moveToNext());
		}
		
		rdb.close();
		
		return names;
		
	}//get_ColumnNames(Activity actv, String tname)

	public static ArrayAdapter<String> 
	get_Adp_List_Store
	(Activity actv) {
		// TODO Auto-generated method stub
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
	              actv, android.R.layout.simple_spinner_item);
	
		/***************************************
		 * Get store names from db
		 ***************************************/
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase db = dbm.getReadableDatabase();
		
		////////////////////////////////

		// validate: table exists

		////////////////////////////////
		boolean res = DBUtils.tableExists(
							actv, CONS.DB.dbName, CONS.DB.tname_stores);
		
		if (res == false) {
			
			// Log
			String msg_Log = "table => exist not: " + CONS.DB.tname_stores;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			db.close();
			
			return null;
			
		}
		
		Cursor c = dbm.getAllData(
							db, 
							CONS.DB.tname_stores, 
							CONS.DB.col_Names_Store_full);
//		Cursor c = dbm.getAllData(db, "stores", CONS.DB.columns_for_table_stores_with_index);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount()" + c.getCount());
	
	//		int count = 0;
		
		while (c.moveToNext()) {
			
			adapter.add(c.getString(1));
			
		}
	
		adapter.add(actv.getString(R.string.generic_label_all));
		
	//		c.moveToFirst();
	//		
	//		// Log
	//		for (int i = 0; i < c.getCount(); i++) {
	//
	//			adapter.add(c.getString(1));
	//
	//			c.moveToNext();
	//			
	//		}//for (int i = 0; i < c.getCount(); i++)
		
		
		/*----------------------------
		 * 3-1. setDropDownViewResource
			----------------------------*/
		adapter.setDropDownViewResource(
						android.R.layout.simple_spinner_dropdown_item);
		
		/*----------------------------
		 * 3-2. Close db
			----------------------------*/
		db.close();
		
		return adapter;
		
	}//get_Adp_List_Store
	
	public static ArrayAdapter<String> 
	get_Adp_List_Genre
	(Activity actv) {
		// TODO Auto-generated method stub
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				actv, android.R.layout.simple_spinner_item);
		
		/***************************************
		 * Get store names from db
		 ***************************************/
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase db = dbm.getReadableDatabase();
		
		////////////////////////////////
		
		// validate: table exists
		
		////////////////////////////////
		boolean res = DBUtils.tableExists(
				actv, CONS.DB.dbName, CONS.DB.tname_genres);
		
		if (res == false) {
			
			// Log
			String msg_Log = "table => exist not: " + CONS.DB.tname_genres;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			db.close();
			
			return null;
			
		}
		
		Cursor c = dbm.getAllData(
							db, 
							CONS.DB.tname_genres, 
							CONS.DB.col_Names_Genre_full);
//		CONS.DB.columns_for_table_genres_with_index);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount()" + c.getCount());
		
		//		int count = 0;
		
		while (c.moveToNext()) {
			
			adapter.add(c.getString(1));
			
		}
		
		adapter.add(actv.getString(R.string.generic_label_all));
		
		//		c.moveToFirst();
		//		
		//		// Log
		//		for (int i = 0; i < c.getCount(); i++) {
		//
		//			adapter.add(c.getString(1));
		//
		//			c.moveToNext();
		//			
		//		}//for (int i = 0; i < c.getCount(); i++)
		
		
		/*----------------------------
		 * 3-1. setDropDownViewResource
			----------------------------*/
		adapter.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		
		/*----------------------------
		 * 3-2. Close db
			----------------------------*/
		db.close();
		
		return adapter;
		
	}//get_Adp_List_Genre

	public static void 
	opt_ActvTab_CreateTables
	(Activity actv,
		Dialog d1, Dialog d2, Dialog d3, String tname) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// dispatch

		////////////////////////////////
		if (tname.equals(CONS.DB.tname_si)) {
			
			_opt_ActvTab_CreateTables__SI(actv, d1, d2, d3);
			
		} else if (tname.equals(CONS.DB.tname_stores)) {
			
			_opt_ActvTab_CreateTables__Stores(actv, d1, d2, d3);
			
		} else if (tname.equals(CONS.DB.tname_genres)) {
			
			_opt_ActvTab_CreateTables__Genres(actv, d1, d2, d3);
			
		} else {

		}
		
		
	}//opt_ActvTab_CreateTables

	private static void 
	_opt_ActvTab_CreateTables__SI
	(Activity actv,
		Dialog d1, Dialog d2, Dialog d3) {
		// TODO Auto-generated method stub

		String tname = CONS.DB.tname_si;
		
		int res = DBUtils.createTable(
							actv, 
							CONS.DB.dbName, 
							tname, 
							CONS.DB.col_Names_SI, 
							CONS.DB.col_Types_SI);
		
		// Log
		String msg_Log = "res => " + res;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		switch(res) {

//		-1 Table exists
//		-2 Exception in executing the sql
//		1 Table created

		case -1: 
			
			msg = "Table alread exists => " + tname;
			colorID = R.color.gold2;
			
//			d3.dismiss();
			
			break;
		
		case -2: 
			
			msg = "Exception in executing the sql";
			colorID = R.color.red;
			
//			d3.dismiss();
			
			break;
			
		case 1: 
			
			msg = "Table created => " + tname;
			colorID = R.color.green4;
			
			d3.dismiss();
			d2.dismiss();
			d1.dismiss();
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage(
				actv, 
				msg,
				colorID);

		
	}//_opt_ActvTab_CreateTables__SI

	private static void 
	_opt_ActvTab_CreateTables__Stores
	(Activity actv,
			Dialog d1, Dialog d2, Dialog d3) {
		// TODO Auto-generated method stub
		
		String tname = CONS.DB.tname_stores;
		
		int res = DBUtils.createTable(
				actv, 
				CONS.DB.dbName, 
				tname, 
				CONS.DB.col_Names_Store, 
				CONS.DB.col_Types_Store);
		
		// Log
		String msg_Log = "res => " + res;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// report
		
		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		switch(res) {
		
//		-1 Table exists
//		-2 Exception in executing the sql
//		1 Table created
		
		case -1: 
			
			msg = "Table alread exists => " + tname;
			colorID = R.color.gold2;
			
//			d3.dismiss();
			
			break;
			
		case -2: 
			
			msg = "Exception in executing the sql";
			colorID = R.color.red;
			
//			d3.dismiss();
			
			break;
			
		case 1: 
			
			msg = "Table created => " + tname;
			colorID = R.color.green4;
			
			d3.dismiss();
			d2.dismiss();
			d1.dismiss();
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage(
				actv, 
				msg,
				colorID);
		
		
	}//_opt_ActvTab_CreateTables__SI
	
	private static void 
	_opt_ActvTab_CreateTables__Genres
	(Activity actv,
			Dialog d1, Dialog d2, Dialog d3) {
		// TODO Auto-generated method stub
		
		String tname = CONS.DB.tname_genres;
		
		int res = DBUtils.createTable(
				actv, 
				CONS.DB.dbName, 
				tname, 
				CONS.DB.col_Names_Genre, 
				CONS.DB.col_Types_Genre);
		
		// Log
		String msg_Log = "res => " + res;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// report
		
		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		switch(res) {
		
//		-1 Table exists
//		-2 Exception in executing the sql
//		1 Table created
		
		case -1: 
			
			msg = "Table alread exists => " + tname;
			colorID = R.color.gold2;
			
//			d3.dismiss();
			
			break;
			
		case -2: 
			
			msg = "Exception in executing the sql";
			colorID = R.color.red;
			
//			d3.dismiss();
			
			break;
			
		case 1: 
			
			msg = "Table created => " + tname;
			colorID = R.color.green4;
			
			d3.dismiss();
			d2.dismiss();
			d1.dismiss();
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage(
				actv, 
				msg,
				colorID);
		
		
	}//_opt_ActvTab_CreateTables__SI
	
	public static void 
	opt_ActvTab_DropTables
	(Activity actv, 
		Dialog d1, Dialog d2, Dialog d3, String tname) {
		// TODO Auto-generated method stub
		
		
		
	}//opt_ActvTab_DropTables

	public static void 
	import_Data_SI
	(Activity actv, 
		Dialog d1, Dialog d2, Dialog d3, Dialog d4) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// validate: import db

		////////////////////////////////
		boolean res = DBUtils.db_Exists(actv, CONS.DB.dbName_SL_1);

		if (res == true) {
			
			// Log
			String msg_Log = "db exists => " + CONS.DB.dbName_SL_1;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
			int res_i = Methods.import_DB(actv);

			if (res_i != 1) {
				
				// Log
				String msg_Log = "import DB => not done";
				Log.e("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				return;
				
			} else {
			
				// Log
				String msg_Log = "DB => imported: " + CONS.DB.dbName_SL_1;
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);

//				//debug
//				d4.dismiss();
				
			}
			
		}//if (res == true)
		
		////////////////////////////////

		// build: list

		////////////////////////////////
//		List<SI> list_SIs = DBUtils.find_ALL_SI(actv)
		
		
//		// Log
//		String msg_Log = "import data => si";
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
	}//import_Data_SI

	public static String 
	get_Dirname
	(Activity actv, String target) {

		String[] tokens = target.split(File.separator);
	
		////////////////////////////////
		
		// tokens => null
		
		////////////////////////////////
		if (tokens == null) {
			
			// Log
			String msg_Log = "tokens => null";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return target;
			
		}
		
		////////////////////////////////

		// tokens => 1

		////////////////////////////////
		if (tokens.length == 1) {
			
			return target;
			
		}
		
		////////////////////////////////

		// tokens > 1

		////////////////////////////////
		String[] tokens_New = Arrays.copyOfRange(tokens, 0, tokens.length - 1);
		
		return StringUtils.join(tokens_New, File.separator);
	
	}//get_Dirname

	/******************************
		@return
			-1	No db file<br>
			-2	Copying db file => failed<br>
			1	db file => copied<br>
	 ******************************/
	public static int 
	import_DB
	(Activity actv) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
	
		// setup: src, dst
	
		////////////////////////////////
		// IFM10
		String src_dir = CONS.DB.dirPath_dbFile_Backup_SL_1;
		
		File f_dir = new File(src_dir);
		
		File[] src_dir_files = f_dir.listFiles();
		
		// If no files in the src dir, quit the method
		if (src_dir_files.length < 1) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread()
						.getStackTrace()[2].getLineNumber()
					+ "]", "No files in the dir: " + src_dir);
			
			return -1;
			
		}//if (src_dir_files.length == condition)
		
		// Latest file
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
		
		////////////////////////////////
	
		// Restore file
	
		////////////////////////////////
		String src = f_src_latest.getAbsolutePath();
		
		//REF http://stackoverflow.com/questions/9810430/get-database-path answered Jan 23 at 11:24
		String dst = actv.getDatabasePath(CONS.DB.dbName).getPath();
		
		// Log
		String msg_Log = "db path => " 
					+ actv.getDatabasePath(CONS.DB.dbName).getPath();
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
	
		// build: db file path (dst)
	
		////////////////////////////////
		String tmp_str = Methods.get_Dirname(actv, dst);
		
		String dst_New = StringUtils.join(
					new String[]{
							
							tmp_str,
							CONS.DB.dbName_SL_1
	//						CONS.DB.dbName_IFM11
							
					}, 
					File.separator);
		
		// Log
		msg_Log = String.format(
							"src = %s // dst = %s", 
							src, dst_New);
		
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
	
		// import (using restoration-related method)
	
		////////////////////////////////
		boolean res = Methods.restore_DB(
							actv, 
							CONS.DB.dbName, 
							src, dst_New);
		
//		//debug
//		boolean res = true;
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "res=" + res);
	
		
	//	//debug
	//	boolean res = true;
		
		/******************************
			validate
		 ******************************/
		if (res == false) {		// copying db file => failed
			
			// Log
			String msg = "Copying file => failed";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
			
			return -2;
			
		}
		
		////////////////////////////////
	
		// report
	
		////////////////////////////////
		// Log
		String msg = "DB => Imported\n" + CONS.DB.dbName_Importing;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg);
		
		////////////////////////////////
	
		// return
	
		////////////////////////////////
		return 1;
	
	}//import_DB

	/*********************************
	 * @return true => File copied(i.e. restored)<br>
	 * 			false => Copying failed
	 *********************************/
	public static boolean
	restore_DB
	(Activity actv, String dbName, 
			String src, String dst) {
		/*********************************
		 * 1. Setup db
		 * 2. Setup: File paths
		 * 3. Setup: File objects
		 * 4. Copy file
		 * 
		 *********************************/
		////////////////////////////////

		// Setup db => This process is necessary if the database folder
		//				is not yet created.

		////////////////////////////////
		
		DBUtils dbu = new DBUtils(actv, dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
	
		wdb.close();
	
		/*********************************
		 * 2. Setup: File paths
	
		/*********************************
		 * 3. Setup: File objects
		 *********************************/
	
		/*********************************
		 * 4. Copy file
		 *********************************/
		FileChannel iChannel = null;
		FileChannel oChannel = null;
		
		try {
			iChannel = new FileInputStream(src).getChannel();
			oChannel = new FileOutputStream(dst).getChannel();
			iChannel.transferTo(0, iChannel.size(), oChannel);
			
			iChannel.close();
			oChannel.close();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "File copied: " + src);
			
//			// debug
//			Toast.makeText(actv, "DB restoration => Done", Toast.LENGTH_LONG).show();
			
			return true;
	
		} catch (FileNotFoundException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			if (iChannel != null) {
				
				try {
					
					iChannel.close();
					
				} catch (IOException e1) {
					
					// Log
					Log.e("Methods.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "Exception: " + e.toString());
	
				}
				
			}
			
			if (iChannel != null) {
				
				try {
					
					iChannel.close();
					
				} catch (IOException e1) {
					
					// Log
					Log.e("Methods.java" + "["
							+ Thread.currentThread().getStackTrace()[2].getLineNumber()
							+ "]", "Exception: " + e.toString());
					
				}
				
			}
			
			if (oChannel != null) {
				
				try {
					oChannel.close();
				} catch (IOException e1) {
					
					// Log
					Log.e("Methods.java" + "["
							+ Thread.currentThread().getStackTrace()[2].getLineNumber()
							+ "]", "Exception: " + e.toString());
					
				}
				
			}
	
			return false;
			
		} catch (IOException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			if (iChannel != null) {
				
				try {
					
					iChannel.close();
					
				} catch (IOException e1) {
					
					// Log
					Log.e("Methods.java" + "["
							+ Thread.currentThread().getStackTrace()[2].getLineNumber()
							+ "]", "Exception: " + e.toString());
					
				}
				
			}
			
			if (oChannel != null) {
				
				try {
					oChannel.close();
				} catch (IOException e1) {
					
					// Log
					Log.e("Methods.java" + "["
							+ Thread.currentThread().getStackTrace()[2].getLineNumber()
							+ "]", "Exception: " + e.toString());
					
				}
				
			}
	
			
			return false;
			
		}//try
		
	}//restore_DB

}//public class Methods
