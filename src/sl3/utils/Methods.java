package sl3.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import sl.main.ItemListActv;
import sl3.adapters.ItemListAdapter2;
import sl3.items.Genre;
import sl3.items.LogItem;
import sl3.items.PH;
import sl3.items.PS;
import sl3.items.SI;
import sl3.items.Store;
import sl3.listeners.button.ButtonOnTouchListener;
import sl3.listeners.dialog.DB_OCL;
import sl3.listeners.dialog.DL;
import sl3.listeners.dialog.DOI_CL;
import sl3.listeners.dialog.DialogButtonOnTouchListener;
import sl3.main.R;
import sl.main.LogActv;
import sl.main.MainActv;
import sl.main.RegisterItemActv;
import sl.main.ShowLogActv;
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Vibrator;
import android.util.Log;
import android.util.Xml;
import android.view.Display;
import android.view.KeyEvent;
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
		Button btn_ok = (Button) dlg_new.findViewById(R.id.dlg_input_empty_btn_ok);
//		Button btn_cancel = (Button) dlg_new.findViewById(R.id.dlg_input_empty_btn_cancel);
		
		//
		btn_ok.setTag(Tags.DialogTags.dlg_input_empty_btn_reenter);
//		btn_cancel.setTag(Tags.DialogTags.dlg_input_empty_btn_cancel);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg_new));
//		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg_new));
		
		/*----------------------------
		 * 3. Add listeners => OnClick
			----------------------------*/
		//
		btn_ok.setOnClickListener(new DB_OCL(actv, dlg, dlg_new));
//		btn_cancel.setOnClickListener(new DB_OCL(actv, dlg, dlg_new));
		
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

//		/***************************************
//		 * 3. Build query
//		 ***************************************/
//		//
////		String query;
//		String query = filterList2__buildQuery(actv, storeName, genreName);
		
		/***************************************
		 * 4. Exec query
		 ***************************************/
		Cursor c = Methods.filterList2__buildQuery_Cursor(
									actv, db, storeName, genreName);
//		Cursor c = db.rawQuery(query, null);
		
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
			
//			SI item = new SI(
//					c.getInt(0),		// id
//					c.getString(1),		// store
//					c.getString(2),		// name
//					c.getInt(3),		// price
//					c.getString(4),		//	genre
//					c.getString(5)			// yomi
//					);
			
			CONS.TabActv.itemList.add(si);
//			CONS.TabActv.itemList.add(item);
			
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

		return query;
		
	}//String filterList2__buildQuery()

	private static
	Cursor filterList2__buildQuery_Cursor
	(Activity actv, 
		SQLiteDatabase db, String storeName, String genreName) {
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store", "name", "price",			// 3,4,5
//		"genre", "yomi", "num",				// 6,7,8
//		
//		"posted_at"							// 9

//		String query = null;
		
		String where = null;
		String[] args = null;
		
		
		// Both are "All"
		if (storeName.equals(actv.getString(R.string.generic_label_all)) &&
				genreName.equals(actv.getString(R.string.generic_label_all))) {
//			query = "SELECT * FROM " + CONS.DB.tableName;
			
			// Store => All, Genre => Specific
		} else if (storeName.equals(actv.getString(R.string.generic_label_all)) &&
				!genreName.equals(actv.getString(R.string.generic_label_all))) {
			
//			query = "SELECT * FROM " + CONS.DB.tableName + 
//					" WHERE genre = '" + genreName + "'";
			
			where = String.format(
							Locale.JAPAN,
							"%s = ?", 
							CONS.DB.col_Names_SI_full[6]); 
			
			args = new String[]{
					
					genreName
					
			};
			
			// Store => Specific, Genre => All
		} else if (!storeName.equals(actv.getString(R.string.generic_label_all)) &&
				genreName.equals(actv.getString(R.string.generic_label_all))) {
			
//			query = "SELECT * FROM " + CONS.DB.tableName + 
//					" WHERE store = '" + storeName + "'";
			
			where = String.format(
					Locale.JAPAN,
					"%s = ?", 
					CONS.DB.col_Names_SI_full[3]); 
			
			args = new String[]{
								
								storeName
								
			};
			
		// Store => Specific, Genre => Specific
		} else {

			where = String.format(
							Locale.JAPAN,
							"%s = ? AND %s = ?", 
							CONS.DB.col_Names_SI_full[6],
							CONS.DB.col_Names_SI_full[3]); 
			
			args = new String[]{
								
							genreName,
							storeName
								
			};

//			query = "SELECT * FROM " + CONS.DB.tableName + 
//					" WHERE store = '" + storeName + "'" + " AND " +
//					"genre = '" + genreName + "'";
			
		}//if (storeName.equals(actv.getString(R.string.generic_label_all)))
		
		return db.query(
				CONS.DB.tname_si, 
				CONS.DB.col_Names_SI_full, 
				where, args, 
				null, null, null);
		
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
				Tags.DialogTags.DLG_GENERIC_CANCEL);
		
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

	/******************************
		@return
			null
			1. table => exist not<br>
	 ******************************/
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
		
		String tname = CONS.DB.tname_stores;
		
		////////////////////////////////

		// validate: table exists

		////////////////////////////////
		boolean res = DBUtils.tableExists(
							actv, CONS.DB.dbName, tname);
		
		if (res == false) {
			
			// Log
			String msg_Log = "table => exist not: " + tname;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			db.close();
			
			return null;
			
		}
		
		Cursor c = dbm.getAllData(
							db, 
							tname, 
							CONS.DB.col_Names_Store_full);

		while (c.moveToNext()) {
			
			adapter.add(c.getString(3));
//			adapter.add(c.getString(1));
			
		}
	
		adapter.add(actv.getString(R.string.generic_label_all));
		
		/*----------------------------
		 * 3-1. setDropDownViewResource
			----------------------------*/
		adapter.setDropDownViewResource(
						android.R.layout.simple_spinner_dropdown_item);
		
		/*----------------------------
		 * 3-2. Close db
			----------------------------*/
		db.close();
		
		// Log
		String msg_Log = "adapter.getCount() => " + adapter.getCount();
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
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
			
			adapter.add(c.getString(3));
			
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
			
		} else if (tname.equals(CONS.DB.tname_PS)) {
			
			_opt_ActvTab_CreateTables__PS(actv, d1, d2, d3);
			
		} else if (tname.equals(CONS.DB.tname_ph)) {
			
			Methods._opt_ActvTab_CreateTables__generic(
						actv, d1, d2, d3, 
						CONS.DB.tname_ph, 
						CONS.DB.col_Names_PH, 
						CONS.DB.col_Types_PH);
			
		} else if (tname.equals(CONS.DB.tname_admin)) {
			
			Methods._opt_ActvTab_CreateTables__generic(
					actv, d1, d2, d3, 
					CONS.DB.tname_admin, 
					CONS.DB.col_Names_Admin, 
					CONS.DB.col_Types_Admin);
			
		} else {

			// Log
			String msg_Log = "unknown table name => " + tname;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
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
	
	private static void 
	_opt_ActvTab_CreateTables__PS
	(Activity actv,
			Dialog d1, Dialog d2, Dialog d3) {
		// TODO Auto-generated method stub
		
		String tname = CONS.DB.tname_PS;
		
		int res = DBUtils.createTable(
				actv, 
				CONS.DB.dbName, 
				tname, 
				CONS.DB.col_Names_PS, 
				CONS.DB.col_Types_PS);
		
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
		
		
	}//_opt_ActvTab_CreateTables__PS
	
	private static void 
	_opt_ActvTab_CreateTables__generic
	(Activity actv,
		Dialog d1, Dialog d2, Dialog d3,
		String tname, String[] col_names, String[] col_types) {
		// TODO Auto-generated method stub
		
//		String tname = CONS.DB.tname_PS;
		
		int res = DBUtils.createTable(
				actv, 
				CONS.DB.dbName, 
				tname, 
				col_names, 
				col_types);
//		CONS.DB.col_Names_PS, 
//		CONS.DB.col_Types_PS);
		
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
		
		
	}//_opt_ActvTab_CreateTables__generic
	
	public static void 
	opt_ActvTab_DropTables
	(Activity actv, 
		Dialog d1, Dialog d2, Dialog d3, String tname) {
		// TODO Auto-generated method stub
		
		
		
	}//opt_ActvTab_DropTables

	public static void 
	import_Data_SI
	(Activity actv, 
		Dialog d1, Dialog d2, Dialog d3) {
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
		List<SI> list_SIs = DBUtils.find_ALL_SI_from_Previous(actv);
		
		////////////////////////////////

		// insert data

		////////////////////////////////
		int res_i = DBUtils.insert_SIs(actv, list_SIs);
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		switch(res_i) {

		case -1: 
			
			msg = "Table doesn't exist => " + CONS.DB.tname_si;
			colorID = R.color.gold2;
			
			d3.dismiss();
			
			break;
		
		default: 
			
			msg = "SIs inserted => " + res_i;
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
		
//		
	}//import_Data_SI

	/******************************
		@return
	 ******************************/
	public static void
	import_Data_Stores
	(Activity actv, 
			Dialog d1, Dialog d2, Dialog d3) {
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
		List<Store> list_Stores = DBUtils.find_ALL_Stores_from_Previous(actv);
		
		/******************************
			validate
		 ******************************/
		if (list_Stores == null) {
			
			// Log
			String msg_Log = "list_Stores => null";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
			
		}
		
		////////////////////////////////
		
		// insert data
		
		////////////////////////////////
		int res_i = DBUtils.insert_Stores(actv, list_Stores);
		
		////////////////////////////////
		
		// report
		
		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		switch(res_i) {
		
		case -1: 
			
			msg = "Table doesn't exist => " + CONS.DB.tname_si;
			colorID = R.color.gold2;
			
			d3.dismiss();
			
			break;
			
		case 0: 
			
			msg = "Insertion => not done: " + CONS.DB.tname_si;
			colorID = R.color.gold2;
			
			d3.dismiss();
			
			break;
			
		default: 
			
			msg = "SIs inserted => " + res_i;
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
		
//		
	}//import_Data_Stores
	
	/******************************
		@return
	 ******************************/
	public static void
	import_Data_Genres
	(Activity actv, 
			Dialog d1, Dialog d2, Dialog d3) {
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
		List<Genre> list_Genres = DBUtils.find_ALL_Genres_from_Previous(actv);
		
		/******************************
			validate
		 ******************************/
		if (list_Genres == null) {
			
			// Log
			String msg_Log = "list_Genres => null";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
			
		}
		
		////////////////////////////////
		
		// insert data
		
		////////////////////////////////
		int res_i = DBUtils.insert_Genres(actv, list_Genres);
		
		////////////////////////////////
		
		// report
		
		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		switch(res_i) {
		
		case -1: 
			
			msg = "Table doesn't exist => " + CONS.DB.tname_si;
			colorID = R.color.gold2;
			
			d3.dismiss();
			
			break;
			
		case 0: 
			
			msg = "Insertion => not done: " + CONS.DB.tname_si;
			colorID = R.color.gold2;
			
			d3.dismiss();
			
			break;
			
		default: 
			
			msg = "SIs inserted => " + res_i;
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
		
//		
	}//import_Data_Genres
	
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

	/*********************************
	 * @return
	 * 		1 File copied<br>
	 * 		-1 FileNotFoundException<br>
	 * 		-2 IOException<br>
	 *********************************/
	public static int
	restore_DB_int
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
			
			return 1;
			
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
			
			return -1;
			
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
			
			
			return -2;
			
		}//try
		
	}//restore_DB
	
	/******************************
		format => "yyyy/MM/dd HH:mm:ss.SSS"
	 ******************************/
	public static String
	conv_MillSec_to_TimeLabel(long millSec)
	{
		//REF http://stackoverflow.com/questions/7953725/how-to-convert-milliseconds-to-date-format-in-android answered Oct 31 '11 at 12:59
		String dateFormat = CONS.Admin.format_Date;
//		String dateFormat = "yyyy/MM/dd hh:mm:ss.SSS";
		
		DateFormat formatter = new SimpleDateFormat(dateFormat, Locale.JAPAN);
//		DateFormat formatter = new SimpleDateFormat(dateFormat);

		// Create a calendar object that will convert the date and time value in milliseconds to date. 
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTimeInMillis(millSec);
		
		return formatter.format(calendar.getTime());
		
	}//conv_MillSec_to_TimeLabel(long millSec)

	public static void confirm_quit(Activity actv, int keyCode) {
		
		if (keyCode==KeyEvent.KEYCODE_BACK) {
			
			AlertDialog.Builder dialog=new AlertDialog.Builder(actv);
			
	        dialog.setTitle(actv.getString(R.string.generic_confirm));
	        dialog.setMessage(actv.getString(R.string.generic_quit_app));
	        
	        dialog.setPositiveButton(
	        				actv.getString(R.string.generic_ok),
	        				new DL(actv, dialog, 0));
	        
	        dialog.setNegativeButton(
	        				actv.getString(R.string.generic_cancel),
	        				new DL(actv, dialog, 1));
	        
	        dialog.create();
	        dialog.show();
			
		}//if (keyCode==KeyEvent.KEYCODE_BACK)
		
	}//public static void confirm_quit(Activity actv, int keyCode)

	public static void 
	insert_Num_SI
	(Activity actv, 
		Dialog d1, Dialog d2, Dialog d3) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// get: si list

		////////////////////////////////
		List<SI> list_SIs = DBUtils.find_ALL_SIs(actv);
		
		////////////////////////////////

		// enter num

		////////////////////////////////
		for (SI si : list_SIs) {

			si.setNum(1);
			
		}
		
		////////////////////////////////

		// update: SIs

		////////////////////////////////
		int res = DBUtils.update_SIs_Num(actv, list_SIs);
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
//		-1 => Table doesn't exist
				
		switch(res) {

		case -1: 
			
			msg = "Table doesn't exist => " + CONS.DB.tname_si;
			colorID = R.color.red;
			
			d3.dismiss();
			
			break;
		
		case 0: 
			
			msg = "SI nums => not updated";
			colorID = R.color.gold2;
			
			d3.dismiss();
			
			break;
			
		default: 
			
			msg = "SIs updated => " + res;
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
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg);
		
	}//insert_Num_SI

	public static void 
	ACTV_TAB_OPT_RESTORE_DB
	(Activity actv, 
		Dialog d1, Dialog d2, Dialog d3) {
		// TODO Auto-generated method stub
		
		// Log
		String msg_Log = "ACTV_TAB_OPT_RESTORE_DB";
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		int res = Methods.restore_DB(actv);
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		switch(res) {

//		1 File copied
//		-1 FileNotFoundException
//		-2 IOException
//		-3 No files in the src dir

		case 1: 
			
			msg = "DB file => restored";
			colorID = R.color.green4;
			
			d3.dismiss();
			d2.dismiss();
			d1.dismiss();
			
			break;
			
		case -3: 
			
			msg = "No files in the src dir";
			colorID = R.color.red;
			
			d3.dismiss();
			
			break;
			
		case -1: 
			
			msg = "src or dst file => not found";
			colorID = R.color.red;
			
			d3.dismiss();
			
			break;
		
		case -2: 
			
			msg = "transfer file => IOException";
			colorID = R.color.red;
			
			d3.dismiss();
			
			break;
			
		default: 
			
			msg = "unknown result => " + res;
			colorID = R.color.gold2;

			d3.dismiss();
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage(
				actv, 
				msg,
				colorID);
		
	}//ACTV_TAB_OPT_RESTORE_DB

	/******************************
		@return
			1 File copied<br>
			-1 FileNotFoundException<br>
			-2 IOException<br>
			-3 No files in the src dir<br>
	 ******************************/
	public static int
	restore_DB(Activity actv) {
    	
    	// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Starting: restore_DB()");

		/*********************************
		 * Get the absolute path of the latest backup file
		 *********************************/
		// Get the most recently-created db file
		String src_dir = CONS.DB.dPath_dbFile_Backup;
//		String src_dir = "/mnt/sdcard-ext/IFM9_backup";
		
		File f_dir = new File(src_dir);
		
		File[] src_dir_files = f_dir.listFiles();
		
		// If no files in the src dir, quit the method
		if (src_dir_files.length < 1) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread()
						.getStackTrace()[2].getLineNumber()
					+ "]", "No files in the dir: " + src_dir);
			
			return -3;
			
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
		
		/*********************************
		 * Restore file
		 *********************************/
		String src = f_src_latest.getAbsolutePath();
		String dst = StringUtils.join(
				new String[]{
						//REF http://stackoverflow.com/questions/9810430/get-database-path answered Jan 23 at 11:24
						actv.getDatabasePath(CONS.DB.dbName).getPath()
				},
//						actv.getFilesDir().getPath() , 
//						CONS.DB.dbName},
				File.separator);
		
		int res = Methods.restore_DB_int(
							actv, 
							CONS.DB.dbName, 
							src, dst);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "res=" + res);
		
		////////////////////////////////

		// return

		////////////////////////////////
		return res;
		
	}//private void restore_DB()

	public static List<SI> 
	conv_ToBuyList_to_SIList
	(Activity actv) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// vars

		////////////////////////////////
		List<SI> list_SIs = new ArrayList<SI>();
		
		String msg;
		
		
		/***************************************
		 * Setup db
		 ***************************************/
		DBUtils dbm = new DBUtils(actv);
		
		SQLiteDatabase rdb = dbm.getReadableDatabase();
		
		String where = CONS.DB.col_Names_SI_full[0] + " = ?";
		String[] args = null;
		
		String tname = CONS.DB.tname_si;
		
		SI si = null;
		
		Cursor c = null;
		
		for (Integer itemId : CONS.TabActv.tab_toBuyItemIds) {
			
			args = new String[]{String.valueOf(itemId.intValue())};
			
			try {
				
				c = rdb.query(
						tname, 
						CONS.DB.col_Names_SI_full,
						where, args,
						null, null, null);
				
			} catch (Exception e) {

				// Log
				String msg_Log = String.format(
							"Exception => id = %d\n%s",
							itemId.intValue(),
							e.toString());
				Log.e("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				rdb.close();
				
//				return CONS.PREP_LIST_FAILED;
				continue;
				
			}//try

			/***************************************
			 * If the cursor is null, then move on to
			 * 	the next id
			 ***************************************/
			if (c == null) {
				
				// Log
				Log.d("Methods_sl.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"c==null => id=" + itemId.intValue());
				
				continue;
				
			}//if (c == null)
			
			/***************************************
			 * If no result, then also, move on to
			 * 	the next
			 ***************************************/
			if (c.getCount() < 1) {
				
				// Log
				Log.d("Methods_sl.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"c.getCount() < 1 => id=" + itemId.intValue());
				
				continue;
				
			}//if (c.getCount() < 1)
			
			/***************************************
			 * If has result, the add the new item
			 * 	to the list
			 ***************************************/
			//
			c.moveToFirst();
			
			for (int i = 0; i < c.getCount(); i++) {
	
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
				//
				list_SIs.add(si);
				
				//
				c.moveToNext();
				
			}//for (int i = 0; i < c.getCount(); i++)

		}//for (Integer itemId : CONS.TabActv.tab_toBuyItemIds)

		//
		rdb.close();
		
		return list_SIs;
		
	}//conv_ToBuyList_to_SIList

	public static String[] 
	conv_MillSec_to_YMD
	(long date) {
		// TODO Auto-generated method stub
		
		String date_str = Methods.conv_MillSec_to_TimeLabel(date);
		
		// Log
		String msg_Log = "date_str => " + date_str;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		String[] dates = date_str.split(" ");
		
		return dates[0].split("[-/]");

//		return null;
	}

	public static String[] 
	conv_TimeLabel_to_YMD
	(String date) {
		// TODO Auto-generated method stub
		
		String[] dates = date.split(" ");
		
		return dates[0].split("[-/]");
		
//		return null;
	}

	public static String
	conv_IdsString_from_ToBuy_ItemIds() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		
		for (Integer id : CONS.TabActv.tab_toBuyItemIds) {
			
			sb.append(String.valueOf(id.intValue()));
			sb.append(" ");
			
		}//for (Integer id : CONS.TabActv.tab_toBuyItemIds)

		return sb.toString().trim();
		
//		String s = sb.toString();
//
//		// Log
//		Log.d("DB_OCL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "s=" + s + "(" + s.length() + ")");
//
//		s = s.trim();
//		
//		// Log
//		Log.d("DB_OCL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "s=" + s + "(" + s.length() + ")");
//		
//		return null;
	}//private void case_dlg_save_tobuy_list_bt_ok()

	public static String get_TimeLabel() {
		// TODO Auto-generated method stub
		return Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now());
	}

	public static void 
	update_TV_DueDate
	(Activity actv, PS ps) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// prep: text

		////////////////////////////////
//		String[] YMD = Methods.conv_TimeLabel_to_YMD(ps.getDueDate());
//		
//		String due_Date = String.format(
//					Locale.JAPAN,
//					"%s/%s/%s", 
//					YMD[0], YMD[1], YMD[2]);
		
		long millSec = Methods.conv_TimeLabel_to_MillSec(ps.getDueDate());
		
		String due_Date = Methods.getTimeLabel_Japanese(millSec);
		
		////////////////////////////////

		// view

		////////////////////////////////
		TextView tvDueDate = (TextView) actv.findViewById(R.id.itemlist_tab2_tv_due_date);

		tvDueDate.setText(
				String.format("%s / %s",
						due_Date,
						ps.getStoreName())
				);
		
		////////////////////////////////

		// bg

		////////////////////////////////
		tvDueDate.setBackgroundColor(
						actv.getResources().getColor(R.color.green_pale));
		
	}//update_TV_DueDate

	public static long
	conv_TimeLabel_to_MillSec(String timeLabel)
//	conv_MillSec_to_TimeLabel(long millSec)
	{
//		String input = "Sat Feb 17 2012";
		Date date;
		try {
			date = new SimpleDateFormat(
						CONS.Admin.format_Date, Locale.JAPAN).parse(timeLabel);
			
			return date.getTime();
//			long milliseconds = date.getTime();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			// Log
			String msg_Log = "Exception: " + e.toString();
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -1;
			
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return -1;
			
		}
		
//		Locale.ENGLISH).parse(input);
		
//		Date date = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH).parse(input);
//		long milliseconds = date.getTime();
		
	}//conv_TimeLabel_to_MillSec(String timeLabel)

	public static String 
	get_Pref_String
	(Activity actv, String pref_name,
			String pref_key, String defValue) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(
						pref_name, Context.MODE_PRIVATE);

		/****************************
		 * Return
			****************************/
		return prefs.getString(pref_key, defValue);

	}//public static String get_Pref_String

	public static List<Integer> 
	conv_ItemIdString_to_IdsList
	(Activity actv, String pref_CheckedIds) {
		// TODO Auto-generated method stub
		
		String[] ids = pref_CheckedIds.split(" ");
		
		List<Integer> list = new ArrayList<Integer>();
		
		for (String id : ids) {
			
			list.add(Integer.parseInt(id));
			
		}
		
		return list;
		
	}//conv_ItemIdString_to_IdsList

	public static boolean
	set_Pref_String
	(Activity actv, String pName, String pKey, boolean value) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pName, Context.MODE_PRIVATE);
		
		/****************************
		 * 2. Get editor
		 ****************************/
		SharedPreferences.Editor editor = prefs.edit();
		
		/****************************
		 * 3. Set value
		 ****************************/
		editor.putBoolean(pKey, value);
		
		try {
			
			editor.commit();
			
			return true;
			
		} catch (Exception e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Excption: " + e.toString());
			
			return false;
			
		}
		
	}//public static boolean setPref_long(Activity actv, String pref_name, String pref_key, long value)

	public static boolean
	set_Pref_String
	(Activity actv, String pName, String pKey, String value) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pName, Context.MODE_PRIVATE);
		
		/****************************
		 * 2. Get editor
		 ****************************/
		SharedPreferences.Editor editor = prefs.edit();
		
		/****************************
		 * 3. Set value
		 ****************************/
		editor.putString(pKey, value);
		
		try {
			
			editor.commit();
			
			return true;
			
		} catch (Exception e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Excption: " + e.toString());
			
			return false;
			
		}
		
	}//public static boolean setPref_long(Activity actv, String pref_name, String pref_key, long value)

	public static boolean
	set_Pref_Clear
	(Activity actv, String pName, String pKey) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pName, Context.MODE_PRIVATE);
		
		/****************************
		 * 2. Get editor
		 ****************************/
		SharedPreferences.Editor editor = prefs.edit();
		
		/****************************
		 * 3. Set value
		 ****************************/
		editor.clear();
		
		return editor.commit();
		
//		editor.putString(pKey, value);
//		
//		try {
//			
//			editor.commit();
//			
//			return true;
//			
//		} catch (Exception e) {
//			
//			// Log
//			Log.e("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Excption: " + e.toString());
//			
//			return false;
//			
//		}
		
	}//public static boolean setPref_long(Activity actv, String pref_name, String pref_key, long value)
	
	public static String 
	conv_IdsList_to_IdsString
	(Activity actv, List<Integer> checkedItemIds) {
		// TODO Auto-generated method stub

		////////////////////////////////

		// dispatch

		////////////////////////////////
		int size = checkedItemIds.size();
		
		if (size == 0) {
			
			// Log
			String msg_Log = "size => 0";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return null;
			
		} else if (size == 1) {
			
			return String.valueOf(checkedItemIds.get(0));
			
		}
		
		////////////////////////////////

		// size > 1

		////////////////////////////////
		StringBuilder sb = new StringBuilder();
		
		int i = 0;
		
		for (; i < checkedItemIds.size() - 1; i++) {
			
			sb.append(String.valueOf(checkedItemIds.get(i)));
			
			sb.append(" ");
			
		}
		
		sb.append(String.valueOf(checkedItemIds.get(i)));
		
		return sb.toString();
		
	}//conv_IdsList_to_IdsString

	public static void 
	save_ItemIds
	(Activity actv) {
		// TODO Auto-generated method stub
		String msg_Log;
		
		////////////////////////////////

		// checked ids

		////////////////////////////////
		_save_ItemIds__CheckedIds(actv);
		
		////////////////////////////////
		
		// to-buy ids
		
		////////////////////////////////
		Methods._save_ItemIds__ToBuyIds(actv);
		
	}//save_ItemIds

	private static void 
	_save_ItemIds__CheckedIds
	(Activity actv) {
		// TODO Auto-generated method stub
		String msg_Log;
		
		if (CONS.TabActv.tab_checkedItemIds != null) {
			
			String ids = Methods.conv_IdsList_to_IdsString(
							actv, 
							CONS.TabActv.tab_checkedItemIds);
			
			// Log
			msg_Log = "item id string => " + ids;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			if (ids != null) {
				
				boolean res = Methods.set_Pref_String(
							actv,
							CONS.Pref.pname_TabActv, 
							CONS.Pref.pkey_TabActv_CheckedIds, 
							ids);

				if (res == true) {
					
					// Log
					msg_Log = "ids => stored: " + ids;
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
				} else {
					
					// Log
					msg_Log = "ids => not stored: " + ids;
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);

				}
				
			} else {
				
				// Log
				msg_Log = "ids => null";
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				boolean res = Methods.set_Pref_String(
								actv, 
								CONS.Pref.pname_TabActv, 
								CONS.Pref.pkey_TabActv_CheckedIds,
								null);
//				boolean res = Methods.set_Pref_Clear(
//						actv, 
//						CONS.Pref.pname_TabActv, 
//						CONS.Pref.pkey_TabActv_CheckedIds);
				
				if (res == true) {
					
					// Log
					msg_Log = "pref => set null";
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
				} else {

					// Log
					msg_Log = "pref => not set null";
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
				}
			}
			
		}//if (CONS.TabActv.tab_checkedItemIds != null)
		
	}

	
	private static void 
	_save_ItemIds__ToBuyIds
	(Activity actv) {
		// TODO Auto-generated method stub
		String msg_Log;
		
		if (CONS.TabActv.tab_toBuyItemIds != null) {
			
			String ids = Methods.conv_IdsList_to_IdsString(
					actv, 
					CONS.TabActv.tab_toBuyItemIds);
			
			// Log
			msg_Log = "item id string => " + ids;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			if (ids != null) {
				
				boolean res = Methods.set_Pref_String(
						actv,
						CONS.Pref.pname_TabActv, 
						CONS.Pref.pkey_TabActv_ToBuyIds, 
						ids);
				
				if (res == true) {
					
					// Log
					msg_Log = "ids => stored: " + ids;
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
				} else {
					
					// Log
					msg_Log = "ids => not stored: " + ids;
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
				}
				
			} else {
				
				// Log
				msg_Log = "ids => null";
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				boolean res = Methods.set_Pref_String(
						actv, 
						CONS.Pref.pname_TabActv, 
						CONS.Pref.pkey_TabActv_ToBuyIds,
						null);
				
				if (res == true) {
					
					// Log
					msg_Log = "pref => set null";
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
				} else {
					
					// Log
					msg_Log = "pref => not set null";
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
				}
			}
			
		}//if (CONS.TabActv.tab_checkedItemIds != null)
		
	}//_save_ItemIds__ToBuyIds
	
	
	
	public static void 
	restore_ItemIds
	(Activity actv) {
		// TODO Auto-generated method stub
		String msg_Log;
		
		////////////////////////////////

		// checked item ids

		////////////////////////////////
		Methods._restore_ItemIds__CheckedIds(actv);
		
		////////////////////////////////
		
		// to-buy item ids
		
		////////////////////////////////
		Methods._restore_ItemIds__ToBuyIds(actv);
		
	}//restore_ItemIds

	private static void 
	_restore_ItemIds__CheckedIds
	(Activity actv) {
		// TODO Auto-generated method stub
		String msg_Log;
		
		String pref_CheckedIds = Methods.get_Pref_String(
				actv, 
				CONS.Pref.pname_TabActv, 
				CONS.Pref.pkey_TabActv_CheckedIds, 
				null);

		if (pref_CheckedIds != null) {
		
			// Log
			msg_Log = "pref_CheckedIds => not null";
			Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
			
			if (CONS.TabActv.tab_checkedItemIds == null) {
			
				CONS.TabActv.tab_checkedItemIds = 
					Methods.conv_ItemIdString_to_IdsList(actv, pref_CheckedIds);
			
			} else {
			
				CONS.TabActv.tab_checkedItemIds.addAll(
					Methods.conv_ItemIdString_to_IdsList(actv, pref_CheckedIds));
			
			}
			
			////////////////////////////////
			
			// notify
			
			////////////////////////////////
			if (CONS.TabActv.adpItems != null) {
			
				CONS.TabActv.adpItems.notifyDataSetChanged();
				
				// Log
				msg_Log = "CONS.TabActv.adpItems => notified";
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
//				// Log
//				for (Integer id : CONS.TabActv.tab_checkedItemIds) {
//					
//					// Log
//					msg_Log = "tab_checkedItemIds => " + id;
//					Log.d("Methods.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber() + "]", msg_Log);
//					
//				}
			
			} else {//if (CONS.TabActv.adpItems != null)
				
				// Log
				msg_Log = "CONS.TabActv.adpItems => null";
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			}//if (CONS.TabActv.adpItems != null)
		
		} else {
		
			// Log
			msg_Log = "pref_CheckedIds => null";
			Log.d("Methods.java" + "["
			+ Thread.currentThread().getStackTrace()[2].getLineNumber()
			+ "]", msg_Log);
		
		}//if (pref_CheckedIds != null)
		
	}//_restore_ItemIds__CheckedIds
		
	private static void 
	_restore_ItemIds__ToBuyIds
	(Activity actv) {
		// TODO Auto-generated method stub
		String msg_Log;
		
		String pref_ToBuyIds = Methods.get_Pref_String(
				actv, 
				CONS.Pref.pname_TabActv, 
				CONS.Pref.pkey_TabActv_ToBuyIds, 
				null);
		
		if (pref_ToBuyIds != null) {
			
			// Log
			msg_Log = "pref_ToBuyIds => not null"
					+ "(" + pref_ToBuyIds + ")";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			if (CONS.TabActv.tab_toBuyItemIds == null) {
				
				CONS.TabActv.tab_toBuyItemIds = 
						Methods.conv_ItemIdString_to_IdsList(actv, pref_ToBuyIds);
				
			} else {
				
				CONS.TabActv.tab_toBuyItemIds.addAll(
						Methods.conv_ItemIdString_to_IdsList(actv, pref_ToBuyIds));
				
			}
			
			////////////////////////////////
			
			// notify
			
			////////////////////////////////
			if (CONS.TabActv.adpToBuys != null) {
				
				CONS.TabActv.adpToBuys.notifyDataSetChanged();
				
				// Log
				msg_Log = "CONS.TabActv.adpToBuys => notified";
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
//				// Log
//				for (Integer id : CONS.TabActv.tab_toBuyItemIds) {
//					
//					// Log
//					msg_Log = "tab_checkedItemIds => " + id;
//					Log.d("Methods.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber() + "]", msg_Log);
//					
//				}
				
			} else {//if (CONS.TabActv.adpToBuys != null)
				
				// Log
				msg_Log = "CONS.TabActv.adpToBuys => null";
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			}//if (CONS.TabActv.adpToBuys != null)
			
		} else {
			
			// Log
			msg_Log = "pref_ToBuyIds => null";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}//if (pref_ToBuyIds != null)
		
	}//_restore_ItemIds__ToBuyIds

	/******************************
		@return
			-1 Can't build list<br>
			1 list set to adapter<br>
	 ******************************/
	public static int 
	search_Items
	(Activity actv, Dialog d1, String store, String yomi) {
		// TODO Auto-generated method stub
		
		List<SI> list_Found = DBUtils.search_Items(actv, store, yomi);
		
		////////////////////////////////

		// validate: list obtained?

		////////////////////////////////
		if (list_Found == null) {
			
//			String message = "Can't build list";
//			int colorID = R.color.red;
//			
//			Methods_dlg.dlg_ShowMessage_SecondDialog(actv, d1, message, colorID);
			
			return -1;
			
		}

		// Log
		String msg_Log = "list_Found.size() => " + list_Found.size();
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// set list

		////////////////////////////////
		CONS.TabActv.adpItems_Found = new ItemListAdapter2(
				actv,
				R.layout.adapteritem,
				list_Found
				);
		
//		CONS.TabActv.lvTab1.setAdapter(adpTab1);
		CONS.TabActv.lvTab1.setAdapter(CONS.TabActv.adpItems_Found);
		
		return 1;
		
	}//search_Items

	public static boolean 
	save_Pur_History
	(Activity actv, Dialog d1, Dialog d2) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// content: store name
		
		////////////////////////////////
		Spinner sp_Store = 
				(Spinner) d2.findViewById(R.id.dlg_save_tobuy_list_sp_store_name);
		
		////////////////////////////////
		
		// content: memo
		
		////////////////////////////////
		EditText et_Memo = (EditText) d2.findViewById(R.id.dlg_save_tobuy_list_et_memo);
		
		String memo = et_Memo.getText().toString();
		
		////////////////////////////////
		
		// content: amount
		
		////////////////////////////////
		EditText et_Amount = (EditText) d2.findViewById(R.id.dlg_save_tobuy_list_et_amount);
		
		String amount = et_Amount.getText().toString();
		
		////////////////////////////////

		// content: items

		////////////////////////////////
		SI si = CONS.TabActv.toBuyList.get(0);
		
		// items
		StringBuilder sb = new StringBuilder();

		int size = CONS.TabActv.toBuyList.size();
		
		// 1 item
		if (size == 1) {
			
			sb.append(CONS.TabActv.toBuyList.get(0).getId() 
						+ "," 
						+ CONS.TabActv.toBuyList.get(0).getNum());
			
		} else if (size > 1) {
			
			int i = 0;
			
			for (; i < CONS.TabActv.toBuyList.size() - 1; i++) {
				
				sb.append(CONS.TabActv.toBuyList.get(i).getId() 
						+ "," 
						+ CONS.TabActv.toBuyList.get(i).getNum());
				
				sb.append(" ");
				
			}
			
			sb.append(CONS.TabActv.toBuyList.get(i).getId() 
					+ "," 
					+ CONS.TabActv.toBuyList.get(i).getNum());
			
		}//if (size == 1)

		////////////////////////////////

		// build: cv

		////////////////////////////////
		ContentValues cv = new ContentValues();
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",		// 1,2
//		
//		"store_name", "pur_date",			// 3,4
//		"items",							// 5
//		"amount",							// 6
//		"memo",								// 7
//		"posted_at"							// 8
		
		cv.put(CONS.DB.col_Names_PH_full[1], 
					Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		cv.put(CONS.DB.col_Names_PH_full[2], 
				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		cv.put(CONS.DB.col_Names_PH_full[3], 
					sp_Store.getSelectedItem().toString());
//		cv.put(CONS.DB.col_Names_PH_full[3], si.getStore());
		
		cv.put(CONS.DB.col_Names_PH_full[4], 
				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
		
		cv.put(CONS.DB.col_Names_PH_full[5], sb.toString());
		
		cv.put(CONS.DB.col_Names_PH_full[6], amount);
		
		cv.put(CONS.DB.col_Names_PH_full[7], memo);
		
//		amount
		////////////////////////////////

		// insert

		////////////////////////////////
		boolean res = DBUtils.insert_Data(actv, CONS.DB.tname_ph, cv);
		
		return res;
		
	}//save_Pur_History

	//REF http://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-timeouts answered Oct 24 '10 at 16:28
	public static boolean 
	isOnline
	(Activity actv) {
	    ConnectivityManager cm =
	        (ConnectivityManager) actv.getSystemService(Context.CONNECTIVITY_SERVICE);
	    
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	    	
	        return true;
	        
	    }
	    
	    return false;
	    
	}//isOnline

	public static JSONObject
	get_JsonBody_Generic
	(Activity actv, String[] keys, Object[] values) {
		
		JSONObject joBody = new JSONObject();
		
		try {
			
			for(int i = 0; i < keys.length; i++) {
				
				joBody.put(keys[i], values[i]);
				
			}
			
		} catch (JSONException e) {
			
			// Log
			Log.d("Methods_sl.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}

		return joBody;
	}//get_JsonBody_Generic

	/******************************
		@return 
			-20 UnsupportedEncodingException<br>
			-21 ClientProtocolException in executing post<br>
			-22 IOException in executing post<br>
			-23 HttpResponse => null<br>
			-24 EntityUtils.toString => ParseException<br>
			-25 EntityUtils.toString => IOException<br>
			-26 StrinEntity => UnsupportedEncodingException<br>
	 ******************************/
	public static int 
	post_PurHist_to_Remote
	(Activity actv, PH ph) {
		////////////////////////////////
	
		// setup
	
		////////////////////////////////
		String url = CONS.HTTPData.UrlPost_PH;
		
		HttpEntity param = _GetParam__PH(actv, ph);

		/******************************
			validate
		 ******************************/
		if (param == null) {
			
			// Log
			String msg_Log = "Building param => UnsupportedEncodingException";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -20;
			
		}
		
		////////////////////////////////
	
		// HttpPost
	
		////////////////////////////////
		HttpPost httpPost = new HttpPost(url);
		
		//REF content-type http://d.hatena.ne.jp/hogem/20091023/1256304878
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
		
		httpPost.setEntity(param);
		
		// Log
		String msg_Log;
		
		try {
			
			msg_Log = "url => " + httpPost.getURI().toURL().toString();
			
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} catch (MalformedURLException e1) {
			
			// Log
			msg_Log = "exception!";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			e1.printStackTrace();
			
		}
		
		DefaultHttpClient dhc = new DefaultHttpClient();
		
		HttpResponse hr = null;
		
		try {

			// Log
			String log_msg = "posting pur history ... : " + ph.getItems();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", log_msg);
			
			hr = dhc.execute(httpPost);
			
			// Log
			log_msg = "posting pur history => done: " + ph.getItems();
			
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", log_msg);
			
			Methods.write_Log(actv, log_msg, Thread.currentThread()
					.getStackTrace()[2].getFileName(), Thread.currentThread()
					.getStackTrace()[2].getLineNumber());
			
		} catch (ClientProtocolException e) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", e.toString());
			
			String log_msg = "upload pur history => ClientProtocolException: "
						+ ph.getItems();
			
			Methods.write_Log(actv, log_msg, Thread.currentThread()
					.getStackTrace()[2].getFileName(), Thread.currentThread()
					.getStackTrace()[2].getLineNumber());
			
			return -21;
			
		} catch (IOException e) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", e.toString());
			
			String log_msg = "upload pur history => IOException: "
					+ ph.getItems()
					+ "(" + e.toString() + ")";
		
			Methods.write_Log(actv, log_msg, Thread.currentThread()
					.getStackTrace()[2].getFileName(), Thread.currentThread()
					.getStackTrace()[2].getLineNumber());
		
			return -22;
			
		}//try
	
		////////////////////////////////
	
		// Validate: Return
	
		////////////////////////////////
		if (hr == null) {
			
			// Log
			Log.d("TaskHTTP.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "hr == null");
			
			return -23;
			
		} else {//if (hr == null)
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Http response => Obtained");
	
		}//if (hr == null)
	
		////////////////////////////////
	
		// Status code
	
		////////////////////////////////
		int status = hr.getStatusLine().getStatusCode();
		
		if (status == CONS.HTTPResponse.status_Created
				|| status == CONS.HTTPResponse.status_OK) {
	
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "status=" + status);
			
			String log_msg = "status=" + status + "/" + ph.getItems();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", log_msg);
	
			////////////////////////////////

			// update: PH

			////////////////////////////////
			Methods._update_PH__PostedAt(actv, ph);
			
		} else {//if (status == CONS.HTTP_Response.CREATED)
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "status=" + status);
			
			return CONS.HTTPResponse.status_NOT_CREATED;
			
		}//if (status == CONS.HTTP_Response.CREATED)
		
		return status;
		
	}//post_ImageData_to_Remote

	/******************************
	 * If poisting successful => update the column "posted_at"<br>
		@return 
			-20 UnsupportedEncodingException<br>
			-21 ClientProtocolException in executing post<br>
			-22 IOException in executing post<br>
			-23 HttpResponse => null<br>
			-24 EntityUtils.toString => ParseException<br>
			-25 EntityUtils.toString => IOException<br>
			-26 StrinEntity => UnsupportedEncodingException<br>
	 ******************************/
	public static int 
	post_Store_to_Remote
	(Activity actv, Store store) {
		////////////////////////////////
		
		// setup
		
		////////////////////////////////
		String url = CONS.HTTPData.UrlPost_Store;
		
		HttpEntity param = _GetParam__Store(actv, store);
		
		/******************************
			validate
		 ******************************/
		if (param == null) {
			
			// Log
			String msg_Log = "Building param => UnsupportedEncodingException";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -20;
			
		}
		
		////////////////////////////////
		
		// HttpPost
		
		////////////////////////////////
		HttpPost httpPost = new HttpPost(url);
		
		//REF content-type http://d.hatena.ne.jp/hogem/20091023/1256304878
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
		
		httpPost.setEntity(param);
		
		// Log
		String msg_Log;
		
		try {
			
			msg_Log = "url => " + httpPost.getURI().toURL().toString();
			
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} catch (MalformedURLException e1) {
			
			// Log
			msg_Log = "exception!";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			e1.printStackTrace();
			
		}
		
		DefaultHttpClient dhc = new DefaultHttpClient();
		
		HttpResponse hr = null;
		
		try {
			
			// Log
			String log_msg = "posting store ... : " + store.getStore_name();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", log_msg);
			
			hr = dhc.execute(httpPost);
			
			// Log
			log_msg = "posting store => done: " + store.getStore_name();
			
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", log_msg);
			
			Methods.write_Log(actv, log_msg, Thread.currentThread()
					.getStackTrace()[2].getFileName(), Thread.currentThread()
					.getStackTrace()[2].getLineNumber());
			
		} catch (ClientProtocolException e) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", e.toString());
			
			String log_msg = "upload store => ClientProtocolException: "
					+ store.getStore_name();
			
			Methods.write_Log(actv, log_msg, Thread.currentThread()
					.getStackTrace()[2].getFileName(), Thread.currentThread()
					.getStackTrace()[2].getLineNumber());
			
			return -21;
			
		} catch (IOException e) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", e.toString());
			
			String log_msg = "upload store => IOException: "
					+ store.getStore_name();
			
			Methods.write_Log(actv, log_msg, Thread.currentThread()
					.getStackTrace()[2].getFileName(), Thread.currentThread()
					.getStackTrace()[2].getLineNumber());
			
			return -22;
			
		}//try
		
		////////////////////////////////
		
		// Validate: Return
		
		////////////////////////////////
		if (hr == null) {
			
			// Log
			Log.d("TaskHTTP.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "hr == null");
			
			return -23;
			
		} else {//if (hr == null)
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Http response => Obtained");
			
		}//if (hr == null)
		
		////////////////////////////////
		
		// Status code
		
		////////////////////////////////
		int status = hr.getStatusLine().getStatusCode();
		
		if (status == CONS.HTTPResponse.status_Created
				|| status == CONS.HTTPResponse.status_OK) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "status=" + status);
			
			String log_msg = "status=" + status + "/" + store.getStore_name();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", log_msg);
			
			////////////////////////////////
			
			// update: PH
			
			////////////////////////////////
			Methods._update_Store__PostedAt(actv, store);
			
		} else {//if (status == CONS.HTTP_Response.CREATED)
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "status=" + status);
			
			return CONS.HTTPResponse.status_NOT_CREATED;
			
		}//if (status == CONS.HTTP_Response.CREATED)
		
		return status;
		
	}//post_ImageData_to_Remote
	
	/******************************
	 * If poisting successful => update the column "posted_at"<br>
		@return 
			-20 UnsupportedEncodingException<br>
			-21 ClientProtocolException in executing post<br>
			-22 IOException in executing post<br>
			-23 HttpResponse => null<br>
			-24 EntityUtils.toString => ParseException<br>
			-25 EntityUtils.toString => IOException<br>
			-26 StrinEntity => UnsupportedEncodingException<br>
	 ******************************/
	public static int 
	post_Genre_to_Remote
	(Activity actv, Genre genre) {
		////////////////////////////////
		
		// setup
		
		////////////////////////////////
		String url = CONS.HTTPData.UrlPost_Genre;
		
		HttpEntity param = _GetParam__Genre(actv, genre);
		
		/******************************
			validate
		 ******************************/
		if (param == null) {
			
			// Log
			String msg_Log = "Building param => UnsupportedEncodingException";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -20;
			
		}
		
		////////////////////////////////
		
		// HttpPost
		
		////////////////////////////////
		HttpPost httpPost = new HttpPost(url);
		
		//REF content-type http://d.hatena.ne.jp/hogem/20091023/1256304878
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
		
		httpPost.setEntity(param);
		
		// Log
		String msg_Log;
		
		try {
			
			msg_Log = "url => " + httpPost.getURI().toURL().toString();
			
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} catch (MalformedURLException e1) {
			
			// Log
			msg_Log = "exception!";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			e1.printStackTrace();
			
		}
		
		DefaultHttpClient dhc = new DefaultHttpClient();
		
		HttpResponse hr = null;
		
		try {
			
			// Log
			String log_msg = "posting genre ... : " + genre.getGenre_name();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", log_msg);
			
			hr = dhc.execute(httpPost);
			
			// Log
			log_msg = "posting genre => done: " + genre.getGenre_name();
			
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", log_msg);
			
			Methods.write_Log(actv, log_msg, Thread.currentThread()
					.getStackTrace()[2].getFileName(), Thread.currentThread()
					.getStackTrace()[2].getLineNumber());
			
		} catch (ClientProtocolException e) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", e.toString());
			
			String log_msg = "upload genre => ClientProtocolException: "
					+ genre.getGenre_name();
			
			Methods.write_Log(actv, log_msg, Thread.currentThread()
					.getStackTrace()[2].getFileName(), Thread.currentThread()
					.getStackTrace()[2].getLineNumber());
			
			return -21;
			
		} catch (IOException e) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", e.toString());
			
			String log_msg = "upload genre => IOException: "
					+ genre.getGenre_name();
			
			Methods.write_Log(actv, log_msg, Thread.currentThread()
					.getStackTrace()[2].getFileName(), Thread.currentThread()
					.getStackTrace()[2].getLineNumber());
			
			return -22;
			
		}//try
		
		////////////////////////////////
		
		// Validate: Return
		
		////////////////////////////////
		if (hr == null) {
			
			// Log
			Log.d("TaskHTTP.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "hr == null");
			
			return -23;
			
		} else {//if (hr == null)
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Http response => Obtained");
			
		}//if (hr == null)
		
		////////////////////////////////
		
		// Status code
		
		////////////////////////////////
		int status = hr.getStatusLine().getStatusCode();
		
		if (status == CONS.HTTPResponse.status_Created
				|| status == CONS.HTTPResponse.status_OK) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "status=" + status);
			
			String log_msg = "status=" + status + "/" + genre.getGenre_name();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", log_msg);
			
			////////////////////////////////
			
			// update: PH
			
			////////////////////////////////
			Methods._update_Genre__PostedAt(actv, genre);
			
		} else {//if (status == CONS.HTTP_Response.CREATED)
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "status=" + status);
			
			return CONS.HTTPResponse.status_NOT_CREATED;
			
		}//if (status == CONS.HTTP_Response.CREATED)
		
		return status;
		
	}//post_Genre_to_Remote
	
	/******************************
	 * If poisting successful => update the column "posted_at"<br>
		@return 
			-20 UnsupportedEncodingException<br>
			-21 ClientProtocolException in executing post<br>
			-22 IOException in executing post<br>
			-23 HttpResponse => null<br>
			-24 EntityUtils.toString => ParseException<br>
			-25 EntityUtils.toString => IOException<br>
			-26 StrinEntity => UnsupportedEncodingException<br>
	 ******************************/
	public static int 
	post_SI_to_Remote
	(Activity actv, SI si) {
		////////////////////////////////
		
		// setup
		
		////////////////////////////////
		String url = CONS.HTTPData.UrlPostSI;
		
		HttpEntity param = _GetParam__SI(actv, si);
		
		/******************************
			validate
		 ******************************/
		if (param == null) {
			
			// Log
			String msg_Log = "Building param => UnsupportedEncodingException";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -20;
			
		}
		
		////////////////////////////////
		
		// HttpPost
		
		////////////////////////////////
		HttpPost httpPost = new HttpPost(url);
		
		//REF content-type http://d.hatena.ne.jp/hogem/20091023/1256304878
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
		
		httpPost.setEntity(param);
		
		// Log
		String msg_Log;
		
		try {
			
			msg_Log = "url => " + httpPost.getURI().toURL().toString();
			
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} catch (MalformedURLException e1) {
			
			// Log
			msg_Log = "exception!";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			e1.printStackTrace();
			
		}
		
		DefaultHttpClient dhc = new DefaultHttpClient();
		
		HttpResponse hr = null;
		
		try {
			
			// Log
			String log_msg = "posting si ... : " + si.getName();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", log_msg);
			
			hr = dhc.execute(httpPost);
			
			// Log
			log_msg = "posting si => done: " + si.getName();
			
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", log_msg);
			
			Methods.write_Log(actv, log_msg, Thread.currentThread()
					.getStackTrace()[2].getFileName(), Thread.currentThread()
					.getStackTrace()[2].getLineNumber());
			
		} catch (ClientProtocolException e) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", e.toString());
			
			String log_msg = "upload si => ClientProtocolException: "
					+ si.getName();
			
			Methods.write_Log(actv, log_msg, Thread.currentThread()
					.getStackTrace()[2].getFileName(), Thread.currentThread()
					.getStackTrace()[2].getLineNumber());
			
			return -21;
			
		} catch (IOException e) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", e.toString());
			
			String log_msg = "upload si => IOException: "
					+ si.getName();
			
			Methods.write_Log(actv, log_msg, Thread.currentThread()
					.getStackTrace()[2].getFileName(), Thread.currentThread()
					.getStackTrace()[2].getLineNumber());
			
			return -22;
			
		}//try
		
		////////////////////////////////
		
		// Validate: Return
		
		////////////////////////////////
		if (hr == null) {
			
			// Log
			Log.d("TaskHTTP.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "hr == null");
			
			return -23;
			
		} else {//if (hr == null)
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Http response => Obtained");
			
		}//if (hr == null)
		
		////////////////////////////////
		
		// Status code
		
		////////////////////////////////
		int status = hr.getStatusLine().getStatusCode();
		
		if (status == CONS.HTTPResponse.status_Created
				|| status == CONS.HTTPResponse.status_OK) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "status=" + status);
			
			String log_msg = "status=" + status + "/" + si.getName();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", log_msg);
			
			////////////////////////////////
			
			// update: PH
			
			////////////////////////////////
			Methods._update_SI__PostedAt(actv, si);
			
		} else {//if (status == CONS.HTTP_Response.CREATED)
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "status=" + status);
			
			return CONS.HTTPResponse.status_NOT_CREATED;
			
		}//if (status == CONS.HTTP_Response.CREATED)
		
		return status;
		
	}//post_SI_to_Remote
	
	private static void 
	_update_PH__PostedAt
	(Activity actv, PH ph) {
		// TODO Auto-generated method stub
		
		String time = Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now());
		
		ph.setPosted_at(time);
		
		boolean res = DBUtils.update_Data_generic(
							actv,
							CONS.DB.tname_ph,
							ph.getDbId(),
							// "posted_at"							// 8
							CONS.DB.col_Names_PH_full[8],
							time);
		
	}//_update_PH__PostedAt

	private static void 
	_update_Store__PostedAt
	(Activity actv, Store store) {
		// TODO Auto-generated method stub
		
		String time = Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now());
		
		store.setPosted_at(time);
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store_name",						// 3
//		
//		"posted_at"							// 4
		
		boolean res = DBUtils.update_Data_generic(
				actv,
				CONS.DB.tname_stores,
				store.getDb_Id(),
				
				CONS.DB.col_Names_Store_full[4],
				time);
		
	}//_update_Store__PostedAt
	
	private static void 
	_update_Genre__PostedAt
	(Activity actv, Genre genre) {
		// TODO Auto-generated method stub
		
		String time = Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now());
		
		genre.setPosted_at(time);
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store_name",						// 3
//		
//		"posted_at"							// 4
		
		boolean res = DBUtils.update_Data_generic(
				actv,
				CONS.DB.tname_genres,
				genre.getDb_Id(),
				
				CONS.DB.col_Names_Genre_full[4],
				time);
		
	}//_update_Genre__PostedAt
	
	private static void 
	_update_SI__PostedAt
	(Activity actv, SI si) {
		// TODO Auto-generated method stub
		
		String time = Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now());
		
		si.setPosted_at(time);
		
//		"posted_at"							// 9
		
		boolean res = DBUtils.update_Data_generic(
				actv,
				CONS.DB.tname_si,
				si.getId(),
				
				CONS.DB.col_Names_SI_full[9],
				time);
		
	}//_update_Genre__PostedAt
	
	
	private static HttpEntity 
	_GetParam__PH
	(Activity actv, PH ph) {
		// TODO Auto-generated method stub

		////////////////////////////////

		// value pair

		////////////////////////////////
		//REF http://stackoverflow.com/questions/3288823/how-to-add-parameters-in-android-http-post answered Jul 20 '10 at 15:10
		List<NameValuePair> params = new LinkedList<NameValuePair>();
		
		params.add(new BasicNameValuePair(
				"data[PurHistory][local_id]", 
				String.valueOf(ph.getDbId())));
		
		params.add(
				new BasicNameValuePair(
						"data[PurHistory][local_created_at]", 
						ph.getCreated_at()
						)
				);
		
		params.add(
				new BasicNameValuePair(
						"data[PurHistory][local_updated_at]", 
						ph.getModified_at()
						)
				);

		////////////////////////////////

		// store name

		////////////////////////////////
		Store st = DBUtils.find_Store_from_Name(actv, ph.getStore_name());
		
		int store_ID = 0;
		
		if (st != null) {
			
			store_ID = st.getDb_Id();
			
		}
		
		params.add(
				new BasicNameValuePair(
						"data[PurHistory][store_id]", String.valueOf(store_ID)
						)
				);
		
		params.add(
				new BasicNameValuePair(
						"data[PurHistory][pur_date]", ph.getPur_date()
						)
				);
		
		params.add(
				new BasicNameValuePair(
						"data[PurHistory][items]", ph.getItems()
						)
				);
		
		params.add(
				new BasicNameValuePair(
						"data[PurHistory][amount]", String.valueOf(ph.getAmount())
						)
				);
		
		params.add(
				new BasicNameValuePair(
						"data[PurHistory][memo]", String.valueOf(ph.getMemo())
						)
				);
		
		////////////////////////////////

		// entity

		////////////////////////////////
		HttpEntity entity = null;
		
		try {
			
			entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
//			entity = new UrlEncodedFormEntity(params);
			
			// Log
			String msg_Log = "entity => created";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} catch (UnsupportedEncodingException e) {
			
			
			e.printStackTrace();
			
			return null;
			
		}
		
		return entity;

	}//_GetParam__PH

	private static HttpEntity 
	_GetParam__Store
	(Activity actv, Store store) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// value pair
		
		////////////////////////////////
		//REF http://stackoverflow.com/questions/3288823/how-to-add-parameters-in-android-http-post answered Jul 20 '10 at 15:10
		List<NameValuePair> params = new LinkedList<NameValuePair>();
		
		params.add(
				new BasicNameValuePair(
						"data[Store][local_id]", 
						String.valueOf(store.getDb_Id())
						)
				);
		
		params.add(
				new BasicNameValuePair(
						"data[Store][local_created_at]", 
						store.getCreated_at()
						)
				);
		
		params.add(
				new BasicNameValuePair(
						"data[Store][local_updated_at]", 
						store.getModified_at()
						)
				);
		
		////////////////////////////////
		
		// store name
		
		////////////////////////////////
		params.add(
				new BasicNameValuePair(
						"data[Store][name]",
						store.getStore_name()
						)
				);
		
		////////////////////////////////
		
		// entity
		
		////////////////////////////////
		HttpEntity entity = null;
		
		try {
			
			entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
//			entity = new UrlEncodedFormEntity(params);
			
			// Log
			String msg_Log = "entity => created";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} catch (UnsupportedEncodingException e) {
			
			
			e.printStackTrace();
			
			return null;
			
		}
		
		return entity;
		
	}//_GetParam__Store
	
	private static HttpEntity 
	_GetParam__Genre
	(Activity actv, Genre store) {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// value pair
		
		////////////////////////////////
		//REF http://stackoverflow.com/questions/3288823/how-to-add-parameters-in-android-http-post answered Jul 20 '10 at 15:10
		List<NameValuePair> params = new LinkedList<NameValuePair>();
		
		params.add(
				new BasicNameValuePair(
						"data[Genre][local_id]", 
						String.valueOf(store.getDb_Id())
						)
				);
		
		params.add(
				new BasicNameValuePair(
						"data[Genre][local_created_at]", 
						store.getCreated_at()
						)
				);
		
		params.add(
				new BasicNameValuePair(
						"data[Genre][local_updated_at]", 
						store.getModified_at()
						)
				);
		
		////////////////////////////////
		
		// store name
		
		////////////////////////////////
		params.add(
				new BasicNameValuePair(
						"data[Genre][name]",
						store.getGenre_name()
						)
				);
		
		////////////////////////////////
		
		// entity
		
		////////////////////////////////
		HttpEntity entity = null;
		
		try {
			
			entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
//			entity = new UrlEncodedFormEntity(params);
			
			// Log
			String msg_Log = "entity => created";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} catch (UnsupportedEncodingException e) {
			
			
			e.printStackTrace();
			
			return null;
			
		}
		
		return entity;
		
	}//_GetParam__Genre
	
	private static HttpEntity 
	_GetParam__SI
	(Activity actv, SI si) {
		// TODO Auto-generated method stub
		
//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store", "name", "price",			// 3,4,5
//		"genre", "yomi", "num",				// 6,7,8
//		
//		"posted_at"							// 9
		
		////////////////////////////////
		
		// value pair
		
		////////////////////////////////
		//REF http://stackoverflow.com/questions/3288823/how-to-add-parameters-in-android-http-post answered Jul 20 '10 at 15:10
		List<NameValuePair> params = new LinkedList<NameValuePair>();
		
		params.add(
				new BasicNameValuePair(
						"data[Si][local_id]", 
						String.valueOf(si.getId())
						)
				);
		
		params.add(
				new BasicNameValuePair(
						"data[Si][local_created_at]", 
						si.getCreated_at()
						)
				);
		
		params.add(
				new BasicNameValuePair(
						"data[Si][local_updated_at]", 
						si.getModified_at()
						)
				);
		
		////////////////////////////////
		
		// store, name
		
		////////////////////////////////
		Store store = DBUtils.find_Store_from_Name(actv, si.getStore());
		
		int tmp_i;
		
		if (store != null) {
			
			tmp_i = store.getDb_Id();
			
		} else {

			tmp_i = CONS.Admin.dflt_Exception_Id_Value;
			
		}
		
		params.add(new BasicNameValuePair("data[Si][store_id]",
						String.valueOf(tmp_i)));
		
		params.add(new BasicNameValuePair("data[Si][name]",
				si.getName()));
		
		////////////////////////////////
		
		// price
		
		////////////////////////////////
		params.add(new BasicNameValuePair("data[Si][price]",
				String.valueOf(si.getPrice())));
		
		
		////////////////////////////////
		
		// "genre", "yomi", "num",				// 6,7,8
		
		////////////////////////////////
		Genre genre = DBUtils.find_Genre_from_Name(actv, si.getGenre());
		
		if (genre != null) {
			
			tmp_i = genre.getDb_Id();
			
		} else {

			tmp_i = CONS.Admin.dflt_Exception_Id_Value;
			
		}

//		params.add(new BasicNameValuePair("data[Si][genre]",
		params.add(new BasicNameValuePair("data[Si][genre_id]",
								String.valueOf(tmp_i)));
		
		params.add(new BasicNameValuePair("data[Si][yomi]",
								si.getYomi()));
		
		params.add(new BasicNameValuePair("data[Si][num]",
								String.valueOf(si.getNum())));
		
		////////////////////////////////
		
		// entity
		
		////////////////////////////////
		HttpEntity entity = null;
		
		try {
			
			entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
//			entity = new UrlEncodedFormEntity(params);
			
			// Log
			String msg_Log = "entity => created";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} catch (UnsupportedEncodingException e) {
			
			
			e.printStackTrace();
			
			return null;
			
		}
		
		return entity;
		
	}//_GetParam__Si
	
	public static void 
	start_Activity_LogActv
	(Activity actv, Dialog d1) {
		// TODO Auto-generated method stub
		Intent i = new Intent();
		
		i.setClass(actv, LogActv.class);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);
		
		////////////////////////////////

		// dismiss

		////////////////////////////////
		d1.dismiss();
		
	}//start_Activity_LogActv

	/******************************
		@return
			null => 1. Log file => doesn't exist<br>
			//REF http://stackoverflow.com/questions/2290757/how-can-you-escape-the-character-in-javadoc answered Dec 11 '11 at 11:11<br>
			2. {@literal List<String>} list => null<br>
			3. list_LogItem => null<br>
	 ******************************/
	public static List<LogItem> 
	get_LogItem_List
	(Activity actv) {
		
		
		String msg_Log;
		
		////////////////////////////////
	
		// validate: files exists
	
		////////////////////////////////
		File fpath_Log = new File(
				CONS.DB.dPath_Log,
				CONS.ShowLogActv.fname_Target_LogFile);
		
		if (!fpath_Log.exists()) {
			
			String msg = "Log file => doesn't exist";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return null;
			
		}
		
		////////////////////////////////
	
		// read file
	
		List<String> list = 
						Methods.get_LogLines(actv, fpath_Log.getAbsolutePath());
		
		/******************************
			validate
		 ******************************/
		if (list == null) {
			
			return null;
			
		} else {
			
			////////////////////////////////
			
			// list => reverse
			
			////////////////////////////////
			Collections.reverse(list);
			
			////////////////////////////////
	
			// add all
	
			////////////////////////////////
			CONS.ShowLogActv.list_RawLines.addAll(list);
			
		}
	
		////////////////////////////////
	
		// build: LogItem list
	
		////////////////////////////////
		List<LogItem> list_LogItem = 
				Methods.conv_LogLinesList_to_LogItemList(
									actv, CONS.ShowLogActv.list_RawLines);
	
		/******************************
			validate
		 ******************************/
		if (list_LogItem == null) {
			
			// Log
			msg_Log = "list_LogItem => null";
			Log.e("ShowLogActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return null;
			
		} else {
	
			// Log
			msg_Log = "list_LogItem => not null"
						+ "(" + list_LogItem.size() + ")";
			Log.d("ShowLogActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
	//		CONS.ShowLogActv.list_ShowLog_Files.addAll(list_LogItem);
			
			return list_LogItem;
			
		}
		
	}//get_LogItem_List

	public static List<String> 
	get_LogLines
	(Activity actv, String fpath_LogFile) {
		
		
		int count_Lines = 0;
		int count_Read = 0;
		
		List<String> list = new ArrayList<String>();
		
	//	File f = new File(fpath_LogFile);
		
		try {
			
	//		fis = new FileInputStream(fpath_Log);
	
			//REF BufferedReader http://stackoverflow.com/questions/7537833/filereader-for-text-file-in-android answered Sep 24 '11 at 8:29
			BufferedReader br = new BufferedReader(
						new InputStreamReader(new FileInputStream(fpath_LogFile)));
	//		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			
			String line = null;
			
			line = br.readLine();
					
			while(line != null) {
				
				list.add(line);
				
				count_Lines += 1;
				count_Read += 1;
				
				line = br.readLine();
				
			}
			
			////////////////////////////////
	
			// close
	
			////////////////////////////////
			br.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
			String msg = "FileNotFoundException";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return null;
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			count_Lines += 1;
			
		}
	
		// Log
		String msg_Log = String.format(
							Locale.JAPAN,
							"count_Lines => %d / count_Read => %d", 
							count_Lines, count_Read);
		
		Log.d("ShowLogActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
	
		
		return list;
		
	}//get_LogLines

	public static List<LogItem> 
	conv_LogLinesList_to_LogItemList
	(Activity actv, List<String> list_RawLines) {
		
		String msg_Log;
		
		List<LogItem> list_LogItems = new ArrayList<LogItem>();
		
		String reg = "\\[(.+?)\\] \\[(.+?)\\] (.+)";
//		String reg = "\\[(.+)\\] \\[(.+)\\] (.+)";
		
		Pattern p = Pattern.compile(reg);
		
		Matcher m = null;
		
		LogItem loi = null;
		
		for (String string : list_RawLines) {
			
			m = p.matcher(string);
			
			if (m.find()) {

				loi = _build_LogItem_from_Matcher(actv, m);
				
				if (loi != null) {
					
					list_LogItems.add(loi);
					
				}
				
			}//if (m.find())
			
		}//for (String string : list_RawLines)
		
		/******************************
			validate
		 ******************************/
		if (list_LogItems.size() < 1) {
			
			// Log
			msg_Log = "list_LogItems.size() => " + list_LogItems.size();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
		}
		
		return list_LogItems;
		
	}//conv_LogLinesList_to_LogItemList

	/******************************
		@return
			null => Matcher.groupCount() != 3
	 ******************************/
	private static LogItem 
	_build_LogItem_from_Matcher
	(Activity actv, Matcher m) {
		
	
		////////////////////////////////
	
		// validate
	
		////////////////////////////////
		if (m.groupCount() != 3) {
			
			return null;
			
		}
		
		////////////////////////////////
	
		// prep: data
	
		////////////////////////////////
		String[] tokens_TimeLabel = m.group(1).split(" ");
		
		String[] tokens_FileInfo = m.group(2).split(" : ");
		
		String text = m.group(3);
		
		String date = tokens_TimeLabel[0];
		
		String time = tokens_TimeLabel[1].split("\\.")[0];
		
		String fileName = tokens_FileInfo[0];
		
		String line = tokens_FileInfo[1];
		
		////////////////////////////////
	
		// LogItem
	
		////////////////////////////////
		LogItem loi = new LogItem.Builder()
					
					.setDate(date)
					.setTime(time)
					.setMethod(fileName)
					.setLine(Integer.parseInt(line))
					.setText(text)
					.build();
		
		return loi;
		
	}//_build_LogItem_from_Matcher

	public static void 
	start_Activity_ShowLogActv
	(Activity actv, String itemName) {
		
		
		// Log
		String msg_Log = "itemName => " + itemName;
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		Intent i = new Intent();
		
		i.setClass(actv, ShowLogActv.class);

		i.putExtra(CONS.Intent.iKey_LogActv_LogFileName, itemName);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		actv.startActivity(i);
		
		
	}//start_Activity_LogActv

	/******************************
		@return true => pref set
	 ******************************/
	public static boolean 
	set_Pref_Int
	(Activity actv, 
			String pref_name, String pref_key, int value) {
		SharedPreferences prefs = 
				actv.getSharedPreferences(pref_name, Context.MODE_PRIVATE);
	
		/****************************
		 * 2. Get editor
			****************************/
		SharedPreferences.Editor editor = prefs.edit();
	
		/****************************
		 * 3. Set value
			****************************/
		editor.putInt(pref_key, value);
		
		try {
			editor.commit();
			
			return true;
			
		} catch (Exception e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Excption: " + e.toString());
			
			return false;
		}
	
	}//set_Pref_Int

	public static int 
	get_Pref_Int
	(Activity actv, String pref_name, String pref_key, int defValue) {
		
		SharedPreferences prefs = 
				actv.getSharedPreferences(pref_name, Context.MODE_PRIVATE);

		/****************************
		 * Return
			****************************/
		return prefs.getInt(pref_key, defValue);

	}//public static boolean set_pref(String pref_name, String value)

	public static void 
	write_Log
	(Activity actv, String message,
			String fileName, int lineNumber) {
		
		////////////////////////////////

		// validate: dir exists

		////////////////////////////////
		File dpath_Log = new File(CONS.DB.dPath_Log);
		
		if (!dpath_Log.exists()) {
			
			dpath_Log.mkdirs();
			
			// Log
			String msg_Log = "log dir => created: " + dpath_Log.getAbsolutePath();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
			// Log
			String msg_Log = "log dir => exists: " + dpath_Log.getAbsolutePath();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
		////////////////////////////////

		// file

		////////////////////////////////
		File fpath_Log = new File(CONS.DB.dPath_Log, CONS.DB.fname_Log);
		
		////////////////////////////////

		// file exists?

		////////////////////////////////
		if (!fpath_Log.exists()) {
			
			try {
				
				fpath_Log.createNewFile();
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
				String msg = "Can't create a log file";
				Methods_dlg.dlg_ShowMessage_Duration(actv, msg, R.color.gold2);
				
				return;
				
			}
			
		} else {
			
			// Log
			String msg_Log = "log file => exists";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
		////////////////////////////////

		// validate: size

		////////////////////////////////
		long len = fpath_Log.length();
		
		if (len > CONS.DB.logFile_MaxSize) {
		
			fpath_Log.renameTo(new File(
						fpath_Log.getParent(), 
						CONS.DB.fname_Log_Trunk
						+ "_"
//						+ Methods.get_TimeLabel(Methods.getMillSeconds_now())
						+ Methods.get_TimeLabel(fpath_Log.lastModified())
						+ CONS.DB.fname_Log_ext
						));
			
			// new log.txt
			try {
				
				fpath_Log = new File(fpath_Log.getParent(), CONS.DB.fname_Log);
//				File f = new File(fpath_Log.getParent(), CONS.DB.fname_Log);
				
				fpath_Log.createNewFile();
				
				// Log
				String msg_Log = "new log.txt => created";
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				// Log
				String msg_Log = "log.txt => can't create!";
				Log.e("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				e.printStackTrace();
				
				return;
				
			}
			
		}//if (len > CONS.DB.logFile_MaxSize)

		////////////////////////////////

		// write

		////////////////////////////////
		try {
			
			//REF append http://stackoverflow.com/questions/8544771/how-to-write-data-with-fileoutputstream-without-losing-old-data answered Dec 17 '11 at 12:37
			FileOutputStream fos = new FileOutputStream(fpath_Log, true);
//			FileOutputStream fos = new FileOutputStream(fpath_Log);
			
			String text = String.format(Locale.JAPAN,
							"[%s] [%s : %d] %s\n", 
							Methods.conv_MillSec_to_TimeLabel(
											Methods.getMillSeconds_now()),
							fileName, lineNumber,
							message
						);
			
			//REF getBytes() http://www.adakoda.com/android/000240.html
			fos.write(text.getBytes());
//			fos.write(message.getBytes());
			
//			fos.write("\n".getBytes());
			
			fos.close();
			
			// Log
			String msg_Log = "log => written";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
//			FileChannel oChannel = new FileOutputStream(fpath_Log).getChannel();
//			
//			oChannel.wri
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}//write_Log

	public static void 
	delete_FromList_Tab2
	(Activity actv, Dialog d1, SI si) {
		// TODO Auto-generated method stub
	
		/***************************************
		 * 1. Remove the si from toBuyList
		 * 2. Notify the adapter: CONS.TabActv.adpToBuys, CONS.TabActv.adpItems
		 ***************************************/
		CONS.TabActv.toBuyList.remove(si);
		
//		CONS.TabActv.tab_toBuyItemIds.remove(si.getId());
		CONS.TabActv.tab_toBuyItemIds.remove(Integer.valueOf(si.getId()));
		
		CONS.TabActv.adpToBuys.notifyDataSetChanged();
		
//		CONS.TabActv.adpItems.notifyDataSetChanged();
		
		////////////////////////////////

		// delete: from Tab1, too

		////////////////////////////////
		CONS.TabActv.itemList.remove(si);
		
		CONS.TabActv.tab_checkedItemIds.remove(Integer.valueOf(si.getId()));
		
		CONS.TabActv.adpItems.notifyDataSetChanged();
		
		/***************************************
		 * Close dlg
		 ***************************************/
		d1.dismiss();
		
	}//delete_FromList_Tab2

	public static void
	sortItemList__Genre_ItemName
	(Activity actv, List<SI> itemList) {
	
//		////////////////////////////////
//
//		// debug
//
//		////////////////////////////////
//		for (SI si : itemList) {
//			
//			// Log
//			String msg_Log = "si.getName() => " + si.getName();
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//		}
		
		/***************************************
		 * Sort: Genre
		 ***************************************/
		Collections.sort(itemList, new Comparator<SI>(){

//			@Override
			public int compare(SI i1, SI i2) {

				
				return (int) (i1.getGenre().compareTo(i2.getGenre()));
				
			}//public int compare(PS i1, PS i2)

		});//Collections.sort()

//		////////////////////////////////
//
//		// debug
//
//		////////////////////////////////
//		// Log
//		String msg_Log = "sort: genre => done";
//		Log.i("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
//		for (SI si : itemList) {
//			
//			// Log
//			msg_Log = "si.getName() => " + si.getName();
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//		}

		/***************************************
		 * Sort: Item name
		 ***************************************/
		Collections.sort(itemList, new Comparator<SI>(){

//			@Override
			public int compare(SI i1, SI i2) {
				
//				if (!i1.getName().equals(i2.getName())) {
				if (i1.getGenre().equals(i2.getGenre())) {
					
					return (i1.getYomi().compareTo(i2.getYomi()));
					
				} else {//if (i1.getName().equals(i2.getName()) == condition)
					
					return 0;
					
				}//if (i1.getName().equals(i2.getName()) == condition)
				
				
//				return (int) (i1.getName().compareTo(i2.getName()));
				
			}//public int compare(PS i1, PS i2)

		});//Collections.sort()

	}//sortItemList_GenreItemName(List<ShoppingItem> itemList)

	public static void
	sortItemList__ItemName
	(Activity actv, List<SI> itemList) {
		////////////////////////////////

		// debug

		////////////////////////////////
		String msg_Log;
		
		// Log
		msg_Log = "sorting => item name";
		Log.i("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		for (SI si : itemList) {
			
			// Log
			msg_Log = "si.getName() => " + si.getName();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
		}
		
		/***************************************
		 * Sort: Item name
		 ***************************************/
		Collections.sort(itemList, new Comparator<SI>(){
			
//			@Override
			public int compare(SI i1, SI i2) {
				
////				if (!i1.getName().equals(i2.getName())) {
//				if (i1.getGenre().equals(i2.getGenre())) {
					
					return (i1.getYomi().compareTo(i2.getYomi()));
					
//				} else {//if (i1.getName().equals(i2.getName()) == condition)
//					
//					return 0;
//					
//				}//if (i1.getName().equals(i2.getName()) == condition)
				
			}//public int compare(PS i1, PS i2)
			
		});//Collections.sort()

		////////////////////////////////

		// debug

		////////////////////////////////
		// Log
		msg_Log = "sort by item name => done";
		Log.i("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		for (SI si : itemList) {
			
			// Log
			msg_Log = "si.getName() => " + si.getName();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
		}

	}//sortItemList__ItemName
	
	
	public static void
	sortItemList__Genre
	(Activity actv, List<SI> itemList) {
		
		/***************************************
		 * Sort: Genre
		 ***************************************/
		Collections.sort(itemList, new Comparator<SI>(){
			
//			@Override
			public int compare(SI i1, SI i2) {
				
				
				return (int) (i1.getGenre().compareTo(i2.getGenre()));
				
			}//public int compare(PS i1, PS i2)
			
		});//Collections.sort()
		
	}//sortItemList__Genre
	
	public static void
	sortItemList__Id
	(Activity actv, List<SI> itemList) {
		
		/***************************************
		 * Sort: Genre
		 ***************************************/
		////////////////////////////////

		// debug

		////////////////////////////////
		for (SI si : itemList) {
			
			// Log
			String msg_Log = "si.getName() => " + si.getName();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
		}
		
		
		////////////////////////////////

		// sort

		////////////////////////////////
		Collections.sort(itemList, new Comparator<SI>(){
			
//			@Override
			public int compare(SI i1, SI i2) {
				
				if (i1.getId() < i2.getId()) {
					
					return 1;
					
				} else if (i1.getId() == i2.getId()) {
					
					return 0;
					
				} else {

					return -1;
				}
				
//				return (int) (i1.getId() < i2.getId()) ? ;
//				return (int) (i1.getGenre().compareTo(i2.getGenre()));
				
			}//public int compare(PS i1, PS i2)
			
		});//Collections.sort()

		////////////////////////////////

		// debug

		////////////////////////////////
		// Log
		String msg_Log = "sort by id => done";
		Log.i("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		for (SI si : itemList) {
			
			// Log
			msg_Log = "si.getName() => " + si.getName();
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
		}

	}//sortItemList__Genre

	/*********************************
	 * REF=> http://www.searchman.info/tips/2640.html
	 * 
	 * #sqlite db file: "database disk image is malformed"
	 * REF=> http://stackoverflow.com/questions/9058169/sqlite-database-disk-image-is-malformed-on-windows-but-fine-on-android
	 * @return
	 * -1	=> SocketException<br>
	 * -2	=> IOException<br>
	 * -3	=> IOException in disconnecting<br>
	 * -4	=> Login failed<br>
	 * -5	=> IOException in logging-in<br>
	 * 
	 * -6	=> storeFile returned false<br>
	 * -7	=> can't find the source file<br>
	 * -8	=> can't find the source file; can't disconnect FTP client<br>
	 * -9	=> storeFile ---> IOException<br>
	 * -10	=> storeFile ---> IOException; can't disconnect FTP client<br>
	 * 
	 * -11	=> set file type ---> failed<br>
	 * -12	=> IOException in logging-in; can't disconnect FTP client<br>
	 * 
	 * -2	=> Log in failed<br>
	 * >0	=> Reply code<br>
	 * 
	 *********************************/
	public static int 
	ftp_Remote_DB
	(Activity actv) {
		/*********************************
		 * memo
		 *********************************/
		// FTP client
		FTPClient fp = new FTPClient();
		
		int reply_code;
		
		// backup db name
		String dbName_backup = String.format(Locale.JAPAN,
					"%s_%s%s", 
					CONS.DB.fname_DB_Backup_Trunk,
					Methods.get_TimeLabel(
							Methods.getMillSeconds_now()),
					CONS.DB.fname_DB_Backup_ext
					);
		
		String fpath_Src = 
					actv.getDatabasePath(CONS.DB.dbName).getAbsolutePath();
		
		String fpath_remote = StringUtils.join(
				new String[]{
						CONS.Remote.remote_Root_DBFile,
						dbName_backup
				}, File.separator);
		
		/*********************************
		 * Connect
		 *********************************/
		try {
			
			// Log
			String msg_Log = "connecting...";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			fp.connect(CONS.Remote.server_Name);
//			fp.connect(server_name);
			
			reply_code = fp.getReplyCode();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "fp.getReplyCode()=" + fp.getReplyCode());
			
		} catch (SocketException e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Error: " + e.toString());
			
			return -1;
			
		} catch (IOException e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Error: " + e.toString());
			
			return -2;
		}
		
		/*********************************
		 * Log in
		 *********************************/
		boolean res;
		
		try {
			
//			res = fp.login(uname, passwd);
			res = fp.login(
					CONS.Remote.uname, 
					CONS.Remote.passwd);
			
			if(res == false) {
				
				reply_code = fp.getReplyCode();
				
				// Log
				Log.e("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Log in failed => " + reply_code);
				
				fp.disconnect();
				
				return -4;
				
			} else {
				
				// Log
				Log.d("Methods.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "Log in => Succeeded");
				
			}
			
		} catch (IOException e1) {
			
			// Log
			String msg_Log = "IOException";
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			e1.printStackTrace();
			
			try {
				
				fp.disconnect();
				
				return -5;
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
				return -12;
				
			}
			
		}
		
		/*********************************
		 * FTP files
		 *********************************/
		// �t�@�C�����M
		FileInputStream is;
		
		try {
			
			// Log
			String msg_Log = "fpath_Src => " + fpath_Src;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			is = new FileInputStream(fpath_Src);
//			is = new FileInputStream(fpath_audio);
			
			// Log
			msg_Log = "Input stream => created";
//			String msg_Log = "Input stream => created";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			////////*////////////////////////
			
			// set: file type
			
			////////////////////////////////
			// REF http://stackoverflow.com/questions/7740817/how-to-upload-an-image-to-ftp-using-ftpclient answered Oct 12 '11 at 13:52
			res = fp.setFileType(FTP.BINARY_FILE_TYPE);
			
			/******************************
				validate
			 ******************************/
			if (res == false) {
				
				// Log
				msg_Log = "set file type => failed";
				Log.e("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
				is.close();
				
				fp.disconnect();
				
				return -11;
				
			}
			
			////////////////////////////////
			
			// store
			
			////////////////////////////////
			// Log
			msg_Log = "Stroing file to remote... => "
					+ fpath_remote;
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
//			fp.storeFile("./" + MainActv.fileName_db, is);// �T�[�o�[��
			res = fp.storeFile(fpath_remote, is);// �T�[�o�[��
			
//			fp.makeDirectory("./ABC");
			
			if (res == true) {
				
				// Log
				Log.d("Methods.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "File => Stored");
				
			} else {//if (res == true)
				
				// Log
				Log.d("Methods.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "Store file => Failed");
				
				fp.disconnect();
				
				return -6;
				
			}//if (res == true)
			
			is.close();
			
		} catch (FileNotFoundException e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			try {
				
				fp.disconnect();
				
				return -7;
				
			} catch (IOException e1) {
				
				e1.printStackTrace();
				
				return -8;
				
			}
			
			
		} catch (IOException e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			try {
				fp.disconnect();
				
				return -9;
				
			} catch (IOException e1) {
				
				
				e1.printStackTrace();
				
				return -10;
				
			}
			
		}
		
		
		//debug
		/*********************************
		 * Disconnect
		 *********************************/
		try {
			
			// Log
			String msg_Log = "disconnecting...";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			fp.disconnect();
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "fp => Disconnected");
			
			return reply_code;
			
		} catch (IOException e) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Error: " + e.toString());
			
			return -3;
			
		}
		
	}//ftp_DB_to_Remote

}//public class Methods
