package sl.main;

import sl3.items.SI;
import sl3.listeners.button.ButtonOnClickListener;
import sl3.main.R;
import sl3.main.R.id;
import sl3.main.R.layout;
import sl3.tasks.Task_PostData;
import sl3.utils.CONS;
import sl3.utils.DBUtils;
import sl3.utils.Methods;
import sl3.utils.Methods_sl;
import sl3.utils.Tags;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterItemActv extends Activity {

	//
	Activity actv;

	//
	Spinner sp_store_name;
	Spinner sp_genre_name;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		/*----------------------------
		 * Steps
		 * 1. Super, etc.
		 * 2. Set content
		 * 3. DBManager => Create table
		 * 3-2. Set spinner items
		 * 4. Set listener => Register
			----------------------------*/
		
		super.onCreate(savedInstanceState);

		this.actv = this;
		
		/*----------------------------
		 * 2. Set content
			----------------------------*/
		setContentView(R.layout.registeritem);
		
		// Title
		setTitle(this.getClass().getName());
		
		/*----------------------------
		 * 3. DBManager => Create table
			----------------------------*/
		DBUtils dbm = new DBUtils(this);
		
		SQLiteDatabase db = dbm.getWritableDatabase();
		
		boolean result = dbm.createTable(db, CONS.DB.tableName);
		
		db.close();

		/*----------------------------
		 * 3-2. Set spinner items
			----------------------------*/
		set_spinner();
		
		setSpinner_genres();
		/*----------------------------
		 * 4. Set listener => Register
			----------------------------*/
		//
		setLister();
		
		
		
	}//public void onCreate(Bundle savedInstanceState)

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

	void setLister() {
		//
		Button bt = (Button) findViewById(R.id.v1_btn_register);
		
		bt.setTag(Tags.ButtonTags.register);
		
		//
		bt.setOnClickListener(new ButtonOnClickListener(
								this,
								sp_store_name,
								sp_genre_name));
		
		bt.setOnTouchListener(new OnTouchListener(){

//			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO �����������ꂽ���\�b�h�E�X�^�u
				switch (event.getActionMasked()) {
				case MotionEvent.ACTION_DOWN:
					v.setBackgroundColor(Color.DKGRAY);
					break;
				case MotionEvent.ACTION_UP:
					v.setBackgroundColor(Color.WHITE);
					break;
				}//switch (event.getAction())
				
				return false;
			}});
	}//void setLister()

	void set_spinner() {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Get store names from db
		 * 3. Set store data to adapter
		 * 3-1. setDropDownViewResource
		 * 3-2. Close db
		 * 4. Set adapter to spinner
			----------------------------*/
		
		// Resource => http://www.java2s.com/Open-Source/Android/Samples/techbooster/org/jpn/techbooster/sample/spinner/SpinnerActivity.java.htm
		sp_store_name = (Spinner) findViewById(R.id.v1_sp_store);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
	              RegisterItemActv.this, android.R.layout.simple_spinner_item);

		/*----------------------------
		 * 2. Get store names from db
			----------------------------*/
		DBUtils dbm = new DBUtils(this);
		
		SQLiteDatabase db = dbm.getReadableDatabase();
		
		Cursor c = dbm.getAllData(db, "stores", CONS.DB.columns_for_table_stores_with_index);
		
		// Log
		Log.d("RegisterItem.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount()" + c.getCount());
		
		c.moveToFirst();
		
		// Log
		for (int i = 0; i < c.getCount(); i++) {
//			Log.d("RegisterItem.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "c.getString(1) => " + c.getString(1));

			/*----------------------------
			 * 3. Set store data to adapter
				----------------------------*/
//			adapter.add("abc");
			adapter.add(c.getString(1));

			c.moveToNext();
		}//for (int i = 0; i < c.getCount(); i++)
		
		
		/*----------------------------
		 * 3-1. setDropDownViewResource
			----------------------------*/
		adapter.setDropDownViewResource(
						android.R.layout.simple_spinner_dropdown_item);
		
		/*----------------------------
		 * 3-2. Close db
			----------------------------*/
		db.close();
		
		/*----------------------------
		 * 4. Set adapter to spinner
			----------------------------*/
		sp_store_name.setAdapter(adapter);

	}//void set_spinner()

	void setSpinner_genres() {
		/*----------------------------
		 * Steps
		 * 1. Set up
		 * 2. Get genre names from db
		 * 3. Set genre data to adapter
		 * 3-1. setDropDownViewResource
		 * 3-2. Close db
		 * 4. Set adapter to spinner
			----------------------------*/
		
		// Resource => http://www.java2s.com/Open-Source/Android/Samples/techbooster/org/jpn/techbooster/sample/spinner/SpinnerActivity.java.htm
		sp_genre_name = (Spinner) findViewById(R.id.v1_sp_genre);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
	              RegisterItemActv.this, android.R.layout.simple_spinner_item);
//
		/*----------------------------
		 * 2. Get genre names from db
			----------------------------*/
		DBUtils dbm = new DBUtils(this);
		
		SQLiteDatabase db = dbm.getReadableDatabase();
		
		Cursor c = dbm.getAllData(db, "genres", CONS.DB.columns_for_table_genres_with_index);
		
		// Log
		Log.d("RegisterItem.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount()" + c.getCount());
		
		c.moveToFirst();
		
		// Log
		for (int i = 0; i < c.getCount(); i++) {
//			Log.d("RegisterItem.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "c.getString(1) => " + c.getString(1));

			/*----------------------------
			 * 3. Set genre data to adapter
				----------------------------*/
//			adapter.add("abc");
			adapter.add(c.getString(1));

			c.moveToNext();
		}//for (int i = 0; i < c.getCount(); i++)
		
		
		/*----------------------------
		 * 3-1. setDropDownViewResource
			----------------------------*/
		adapter.setDropDownViewResource(
						android.R.layout.simple_spinner_dropdown_item);
		
		/*----------------------------
		 * 3-2. Close db
			----------------------------*/
		db.close();
		
		/*----------------------------
		 * 4. Set adapter to spinner
			----------------------------*/
		sp_genre_name.setAdapter(adapter);
		
	}
}
