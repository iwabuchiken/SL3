package sl.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import sl.adapters.ItemListAdapter;
import sl.adapters.ItemListAdapter2;
import sl.adapters.ToBuyListAdapter;
import sl.items.PS;
import sl.items.ShoppingItem;
import sl.listeners.ButtonOnClickListener;
import sl.listeners.ButtonOnTouchListener;
import sl.listeners.ItemSelectedListener;
import sl.listeners.list.ListOnItemClickListener;
import sl.listeners.list.ListOnItemLongClickListener;
import sl.utils.CONS;
import sl.utils.DBUtils;
import sl.utils.Methods;
import sl.utils.Methods_dlg;
import sl.utils.Methods_sl;
import sl.utils.Tags;
import sl3.main.R;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class TabActv extends TabActivity implements TabHost.TabContentFactory {
	
//	public static TabHost CONS.TabActv.tabHost;
//	TabSpec CONS.TabActv.firstTab;
//	TabSpec CONS.TabActv.secondTab;

	ListView lvTab1;
	ListView lvTab2;

	Spinner spStore;
	Spinner spGenre;
	
//	ArrayAdapter<String> adpTab1;
//	ArrayAdapter<String> adpTab2;

//	ItemListAdapter2 adpItems;
	
//	List<ShoppingItem> itemList;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemlist_tabs);
        
        setTitle(this.getClass().getName());
        
//        initVars();
//        
//        setupTabs();
//        
//        setupItemListView();
//        
//        setupToBuyListView();
//        
//        setupListeners();
//        
//        //debug
//        int[] size = Methods.getDisplaySize(this);
//        
//        // Log
//		Log.d("TabActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"window size: width=" + size[0]
//				+ "/"
//				+ "window size: height=" + size[1]);
//         
//		//debug
////		test_B32_v_1_2();
		
    }//public void onCreate(Bundle savedInstanceState)

    private void test_B32_v_1_2() {
		// TODO Auto-generated method stub
    	/***************************************
		 * Build: List
		 ***************************************/
    	List<ShoppingItem> itemList = new ArrayList<ShoppingItem>();
    	
		itemList.add(new ShoppingItem.Builder().setName("B").setPrice(1).build());
		itemList.add(new ShoppingItem.Builder().setName("C").setPrice(1).build());
		itemList.add(new ShoppingItem.Builder().setName("A").setPrice(2).build());
		itemList.add(new ShoppingItem.Builder().setName("B").setPrice(3).build());
		itemList.add(new ShoppingItem.Builder().setName("B").setPrice(2).build());
		itemList.add(new ShoppingItem.Builder().setName("A").setPrice(1).build());
		
		// Log
		Log.d("TabActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "<Original>");
		
		for (int i = 0; i < itemList.size(); i++) {
			
			// Log
			Log.d("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Name=" + itemList.get(i).getName()
					+ "/"
					+ "Price=" + itemList.get(i).getPrice());
			
		}//for (int i = 0; i < itemList.size(); i++)
    	
		/***************************************
		 * Sort: Name
		 ***************************************/
		Collections.sort(itemList, new Comparator<ShoppingItem>(){

//			@Override
			public int compare(ShoppingItem i1, ShoppingItem i2) {

				
				return (int) (i1.getName().compareTo(i2.getName()));
				
			}//public int compare(PS i1, PS i2)

		});//Collections.sort()

		// Log
		Log.d("TabActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "<Sorted: Name>");
		
		for (int i = 0; i < itemList.size(); i++) {
			
			// Log
			Log.d("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Name=" + itemList.get(i).getName()
					+ "/"
					+ "Price=" + itemList.get(i).getPrice());
			
		}//for (int i = 0; i < itemList.size(); i++)
    	
		/***************************************
		 * Sort: Price
		 ***************************************/
		Collections.sort(itemList, new Comparator<ShoppingItem>(){

//			@Override
			public int compare(ShoppingItem i1, ShoppingItem i2) {
				
//				if (!i1.getName().equals(i2.getName())) {
				if (i1.getName().equals(i2.getName())) {
					
					return (i1.getPrice() - i2.getPrice());
					
				} else {//if (i1.getName().equals(i2.getName()) == condition)
					
					return 0;
					
				}//if (i1.getName().equals(i2.getName()) == condition)
				
				
//				return (int) (i1.getName().compareTo(i2.getName()));
				
			}//public int compare(PS i1, PS i2)

		});//Collections.sort()

		// Log
		Log.d("TabActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "<Sorted: Price>");
		
		for (int i = 0; i < itemList.size(); i++) {
			
			// Log
			Log.d("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Name=" + itemList.get(i).getName()
					+ "/"
					+ "Price=" + itemList.get(i).getPrice());
			
		}//for (int i = 0; i < itemList.size(); i++)

		
	}//private void test_B32_v_1_2()

	private void _Setup_Listeners() {
		// TODO Auto-generated method stub
		/***************************************
		 * Set listener: lvTab1
		 ***************************************/
		lvTab1.setTag(Tags.ListTags.tab_itemList);
		
		lvTab1.setOnItemClickListener(new ListOnItemClickListener(this));

		lvTab1.setOnItemLongClickListener(new ListOnItemLongClickListener(this));
		
		/***************************************
		 * Set listener: ImageButton
		 ***************************************/
		ImageButton ib_tab1Choose = (ImageButton) findViewById(R.id.itemlist_tab1_ib);
		
		ib_tab1Choose.setTag(Tags.ButtonTags.itemlist_tabs_bt_choose);
		
		ib_tab1Choose.setOnClickListener(new ButtonOnClickListener(this));
		
		ib_tab1Choose.setOnTouchListener(new ButtonOnTouchListener(this));
		
		/***************************************
		 * Set listener: lvTab2
		 ***************************************/
		/***************************************
		 * Listener: Item click
		 ***************************************/
		lvTab2.setTag(Tags.ListTags.tab_toBuyList);
		
		lvTab2.setOnItemClickListener(new ListOnItemClickListener(this));
		
		/***************************************
		 * Listener: Item long click
		 ***************************************/
		lvTab2.setOnItemLongClickListener(new ListOnItemLongClickListener(this));
		
		/***************************************
		 * Change listeners: Spinner: store
		 ***************************************/
		spStore.setTag(Tags.SpinnerTag.spStrore);
		spStore.setOnItemSelectedListener(new ItemSelectedListener(this));
		
		/***************************************
		 * Change listeners: Spinner: store
		 ***************************************/
		spGenre.setTag(Tags.SpinnerTag.spGenre);
		spGenre.setOnItemSelectedListener(new ItemSelectedListener(this));
		
		
	}//private void setupListeners()

	private void _Setup_InitVars() {
		// TODO Auto-generated method stub
		CONS.TabActv.tab_checkedItemIds = new ArrayList<Integer>();
		CONS.TabActv.tab_toBuyItemIds = new ArrayList<Integer>();
		CONS.TabActv.tab_boughtItemIds = new ArrayList<Integer>();
		
		/***************************************
		 * Get preference value: bgm
		 ***************************************/
		SharedPreferences prefs = this
				.getSharedPreferences(
					this.getString(R.string.shared_preferences_name),
					Context.MODE_PRIVATE);

//		boolean bgm = prefs.getBoolean(actv.getString(R.string.prefs_key_bgm), false);
		CONS.bgm = prefs.getBoolean(this.getString(R.string.prefs_key_bgm), false);
		
		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "bgm=" + CONS.bgm);

	}//private void initVars()

	private boolean _SetupTabs() {
		// TODO Auto-generated method stub
		
		boolean res;
		
        /***************************************
		 * Tab host
		 ***************************************/
        //TabHostクラスのインスタンス生成
//        TabHost CONS.TabActv.tabHost = getTabHost();
        CONS.TabActv.tabHost = getTabHost();
        
        /***************************************
		 * First tab
		 ***************************************/
        res = _SetupTabs__first();
        
        if (res == false) {
			
        	String msg = "Can't setup tabs";
			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
			
			return false;
        	
		}
        
        /***************************************
		 * Second tab
		 ***************************************/
        setupTabs__second();
////        TabSpec CONS.TabActv.secondTab = CONS.TabActv.tabHost.newTabSpec("Second");
////        CONS.TabActv.secondTab = CONS.TabActv.tabHost.newTabSpec("Second");
////        CONS.TabActv.secondTab.setIndicator("CONS.TabActv.secondTab", getResources().getDrawable(android.R.drawable.ic_media_next));
//        
//        CONS.TabActv.secondTab = CONS.TabActv.tabHost.newTabSpec(this.getString(R.string.tabactv_tabtags_second));
//        
//        CONS.TabActv.secondTab.setIndicator(
//        		"",
//        		getResources().getDrawable(R.drawable.sl_basket));
//
//        CONS.TabActv.secondTab.setContent(R.id.second_content);
//        
//        CONS.TabActv.tabHost.addTab(CONS.TabActv.secondTab);
//        
//        //３つ目のタブを生成
//        TabSpec thirdTab = CONS.TabActv.tabHost.newTabSpec("Third");
//        thirdTab.setIndicator("thirdTab", getResources().getDrawable(android.R.drawable.ic_menu_add));
//        thirdTab.setContent(this);
//        CONS.TabActv.tabHost.addTab(thirdTab);

        /***************************************
		 * Set size to views: Tab2
		 ***************************************/
        ListView lvTab2ToBuyList = (ListView) this.findViewById(R.id.itemlist_tab2_lv);
        TextView tvTab2DueDate = (TextView) this.findViewById(R.id.itemlist_tab2_tv_due_date);
        
        // Window height
//        int windowHeight = Methods.getDisplaySize(this)[0];
        int windowHeight = Methods.getDisplaySize(this)[1];
        
        // TextView height
        int tvTab2DueDate_Height = tvTab2DueDate.getHeight();
//        
//        lvTab2ToBuyList.setLayoutParams(new LinearLayout.LayoutParams(
//				LayoutParams.MATCH_PARENT,	// Width
////				300));
//				windowHeight - tvTab2DueDate_Height));		// Height
//        
//        // Log
//		Log.d("TabActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"windowHeight=" + windowHeight
//				+ "/"
//				+ "tvTab2DueDate_Height=" + tvTab2DueDate_Height);

//		// Log
//		Log.d("TabActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"tvTab2DueDate=" + tvTab2DueDate.getLineCount());
		
		/***************************************
		 * Get text view height
		 ***************************************/
		// REF=> http://stackoverflow.com/questions/4912687/android-get-the-height-of-the-textview	(answered Feb 6 '11 at 14:03)
		// REF=> http://stackoverflow.com/questions/3630086/how-to-get-string-width-on-android	(answered Sep 2 '10 at 19:05)
		Rect bounds = new Rect();
//		String s = "abcde";
		String s = tvTab2DueDate.getText().toString();
		TextPaint p = tvTab2DueDate.getPaint();
		p.getTextBounds(s, 0, s.length(), bounds);
//		
//		// Log
//		Log.d("TabActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "bounds.height()=" + bounds.height());
//		
		int textHeight = bounds.height();

//		lvTab2ToBuyList.setLayoutParams(new LinearLayout.LayoutParams(
//					LayoutParams.MATCH_PARENT,	// Width
//		//				300));
//					windowHeight - (textHeight + 300)));		// Height

//      // Log
//		Log.d("TabActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"windowHeight=" + windowHeight
//				+ "/"
//				+ "windowHeight - (textHeight + 10)="
//				+ (windowHeight - (textHeight + 300)));
		
//		// Log
//		Log.d("TabActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"CONS.TabActv.tabHost.getTabWidget().getMeasuredHeight()="
//				+ CONS.TabActv.tabHost.getTabWidget().getMeasuredHeight()
//				+ "/"
//				+ "CONS.TabActv.tabHost.getTabWidget().getHeight()="
//				+ CONS.TabActv.tabHost.getTabWidget().getHeight()
//				+ "/"
//				+ "CONS.TabActv.tabHost.getTabWidget().getChildAt(1).getBottom()="
//				+ CONS.TabActv.tabHost.getTabWidget().getChildAt(1).getBottom()
//				//REF=> http://stackoverflow.com/questions/2502800/tabwidget-height	(answered Jun 15 '11 at 15:16)
//				+ "CONS.TabActv.tabHost.getTabWidget().getChildAt(1).getLayoutParams().height="
//				+ CONS.TabActv.tabHost.getTabWidget().getChildAt(1).getLayoutParams().height);
		
		// Log
		int widgetHeight = CONS.TabActv.tabHost.getTabWidget().getChildAt(1).getLayoutParams().height;

//		Log.d("TabActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"(windowHeight - (textHeight + widgetHeight))="
//				+ (windowHeight - (textHeight + widgetHeight)));

		lvTab2ToBuyList.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT,	// Width
	//				300));
//				(windowHeight - (textHeight + widgetHeight + 150))));		// Height
//				(windowHeight - (textHeight + widgetHeight				// Due date
				(windowHeight - (textHeight * 2 + widgetHeight			// Due date and sum
									+ CONS.MagicConstants.MODIFY_TAB2_LV_HEIGHT))));		// Height

		
//		// Log
//		Log.d("TabActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "CONS.TabActv.tabHost.getTabContentView().getHeight()="
//				+ CONS.TabActv.tabHost.getTabContentView().getHeight());
//		
//		// Log
//		Log.d("TabActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "CONS.TabActv.tabHost.getTabContentView().getLayoutParams().height="
//				+ CONS.TabActv.tabHost.getTabContentView().getLayoutParams().height);
		
		/***************************************
		 * Set default due date
		 ***************************************/
		tvTab2DueDate.setText(Methods.getTimeLabel_Japanese(Methods.getMillSeconds_now()));

        /***************************************
		 * Set current tab
		 ***************************************/
        //最初にカーソルを当てたいタブを指定
        CONS.TabActv.tabHost.setCurrentTabByTag("First");
        
        ////////////////////////////////

		// return

		////////////////////////////////
		return true;
        
	}//private void setupTabs()

	private void setupTabs__second() {
		// TODO Auto-generated method stub
//      TabSpec CONS.TabActv.secondTab = CONS.TabActv.tabHost.newTabSpec("Second");
//      CONS.TabActv.secondTab = CONS.TabActv.tabHost.newTabSpec("Second");
//      CONS.TabActv.secondTab.setIndicator("CONS.TabActv.secondTab", getResources().getDrawable(android.R.drawable.ic_media_next));
      
      CONS.TabActv.secondTab = CONS.TabActv.tabHost.newTabSpec(this.getString(R.string.tabactv_tabtags_second));
      
      CONS.TabActv.secondTab.setIndicator(
      		"",
      		getResources().getDrawable(R.drawable.sl_basket));

      CONS.TabActv.secondTab.setContent(R.id.second_content);
      
      CONS.TabActv.tabHost.addTab(CONS.TabActv.secondTab);

	}

	private boolean
	_SetupTabs__first() {
		// TODO Auto-generated method stub
		
		CONS.TabActv.firstTab = CONS.TabActv.tabHost.newTabSpec(
    		  					this.getString(R.string.tabactv_tabtags_first));
      
      // タブ部分に表示するテキストおよびアイコンのセット
//      CONS.TabActv.firstTab.setIndicator("CONS.TabActv.firstTab", getResources().getDrawable(android.R.drawable.ic_menu_agenda));
		CONS.TabActv.firstTab.setIndicator(
				"",
				getResources().getDrawable(R.drawable.sl_tab_itemlist));
      
		// タブ選択時に表示したいViewのセット
		CONS.TabActv.firstTab.setContent(R.id.first_content);
		// タブをTabHostに追加
		CONS.TabActv.tabHost.addTab(CONS.TabActv.firstTab);
		
		////////////////////////////////
	
		// setup: Spinners
	
		////////////////////////////////
		spStore = (Spinner) this.findViewById(R.id.itemlist_tab1_sp_store_name);
		
		CONS.TabActv.adp_List_Store = Methods.get_Adp_List_Store(this);
		
		/******************************
			validate
		 ******************************/
		if (CONS.TabActv.adp_List_Store == null) {
			
			// Log
			String msg_Log = "CONS.TabActv.adp_List_Store => null";
			Log.e("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return false;
			
		}
		
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//	              this, android.R.layout.simple_spinner_item);
//	
//		/***************************************
//		 * Get store names from db
//		 ***************************************/
//		DBUtils dbm = new DBUtils(this);
//		
//		SQLiteDatabase db = dbm.getReadableDatabase();
//		
//		Cursor c = dbm.getAllData(db, "stores", CONS.DBAdmin.columns_for_table_stores_with_index);
//		
//		// Log
//		Log.d("ListOnItemLongClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "c.getCount()" + c.getCount());
//	
//	//		int count = 0;
//		
//		while (c.moveToNext()) {
//			
//			adapter.add(c.getString(1));
//			
//		}
//	
//		adapter.add(this.getString(R.string.generic_label_all));
//		
//	//		c.moveToFirst();
//	//		
//	//		// Log
//	//		for (int i = 0; i < c.getCount(); i++) {
//	//
//	//			adapter.add(c.getString(1));
//	//
//	//			c.moveToNext();
//	//			
//	//		}//for (int i = 0; i < c.getCount(); i++)
//		
//		
//		/*----------------------------
//		 * 3-1. setDropDownViewResource
//			----------------------------*/
//		adapter.setDropDownViewResource(
//						android.R.layout.simple_spinner_dropdown_item);
//		
//		/*----------------------------
//		 * 3-2. Close db
//			----------------------------*/
//		db.close();
		
		/*----------------------------
		 * 4. Set adapter to spinner
			----------------------------*/
		spStore.setAdapter(CONS.TabActv.adp_List_Store);
//		spStore.setAdapter(adapter);
	
		/***************************************
		 * Set initial value
		 ***************************************/
		int num = 0;
		
		for (int i = 0; i < CONS.TabActv.adp_List_Store.getCount(); i++) {
			
			String genreName = CONS.TabActv.adp_List_Store.getItem(i);
	
			if (genreName.equals(this.getString(R.string.generic_label_all))) {
				
				num = i;
				
				break;
				
			}//if (si.getName() == condition)
			
		}//for (int i = 0; i < adapter.getCount(); i++)
		
		spStore.setSelection(num);
	
		/***************************************
		 * Spinner: Genre
		 ***************************************/
		spGenre = (Spinner) this.findViewById(R.id.itemlist_tab1_sp_genre);
		
		CONS.TabActv.adapterGenre = Methods.get_Adp_List_Genre(this);
		
		/******************************
			validate
		 ******************************/
		if (CONS.TabActv.adapterGenre == null) {
			
			// Log
			String msg_Log = "CONS.TabActv.adapterGenre => null";
			Log.e("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return false;
			
		}
		
//		ArrayAdapter<String> adapterGenre = new ArrayAdapter<String>(
//	              this, android.R.layout.simple_spinner_item);
//	//
//		/*----------------------------
//		 * 2. Get genre names from db
//			----------------------------*/
//		dbm = new DBUtils(this);
//		
//		db = dbm.getReadableDatabase();
//		
//		c = dbm.getAllData(db, "genres", CONS.DBAdmin.columns_for_table_genres_with_index);
//		
//		// Log
//		Log.d("RegisterItem.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "c.getCount()" + c.getCount());
//	
//		while (c.moveToNext()) {
//			
//			adapterGenre.add(c.getString(1));
//			
//		}
//	
//		adapterGenre.add(this.getString(R.string.generic_label_all));
//		
//	//		c.moveToFirst();
//	//		
//	//		// Log
//	//		for (int i = 0; i < c.getCount(); i++) {
//	//
//	//			adapter.add(c.getString(1));
//	//
//	//			c.moveToNext();
//	//		}//for (int i = 0; i < c.getCount(); i++)
//		
//		
//		/*----------------------------
//		 * 3-1. setDropDownViewResource
//			----------------------------*/
//		adapterGenre.setDropDownViewResource(
//						android.R.layout.simple_spinner_dropdown_item);
//		
//		/*----------------------------
//		 * 3-2. Close db
//			----------------------------*/
//		db.close();
		
		/*----------------------------
		 * 4. Set adapter to spinner
			----------------------------*/
		spGenre.setAdapter(CONS.TabActv.adapterGenre);
		
		/***************************************
		 * Set initial value
		 ***************************************/
		num = 0;
		
		for (int i = 0; i < CONS.TabActv.adapterGenre.getCount(); i++) {
			
			String genreName = CONS.TabActv.adapterGenre.getItem(i);
	
			if (genreName.equals(this.getString(R.string.generic_label_all))) {
				
				num = i;
				
				break;
				
			}//if (si.getName() == condition)
			
		}//for (int i = 0; i < adapter.getCount(); i++)
		
		spGenre.setSelection(num);
	
		////////////////////////////////

		// return

		////////////////////////////////
		return true;
		
	}//_SetupTabs__first

	private boolean _Setup_ItemListView() {
		
		int numOfEntries = 30;
		
		/***************************************
		 * Prepare list
		 ***************************************/
		int res = prepareItemList();
		
		/***************************************
		 * List in the tab 1
		 ***************************************/
		if (res == CONS.PREP_LIST_SUCCESSFUL) {

			lvTab1 = (ListView) findViewById(R.id.itemlist_tab1_lv);
			
			List<String> listTab1 = new ArrayList<String>();

			
			
			for (int i = 1; i < numOfEntries; i++) {
				
				listTab1.add("Number: " + i);
				
			}//for (int i = 1; i < 11; i++)
			
			/***************************************
			 * Adapter
			 ***************************************/

			CONS.TabActv.adpItems = new ItemListAdapter2(
					this,
					R.layout.adapteritem,
					CONS.TabActv.itemList
					);
			
//			lvTab1.setAdapter(adpTab1);
			lvTab1.setAdapter(CONS.TabActv.adpItems);

			// Log
			Log.d("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "itemList.size()=" + CONS.TabActv.itemList.size());
	
			return true;
			
		} else {//if (res == CONS.PREP_LIST_SUCCESSFUL)
			
			// Log
			Log.d("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Prep item list => Failed");
			
			return false;
			
		}//if (res == CONS.PREP_LIST_SUCCESSFUL)

	}//private void setupListView()

	private void _Setup_ToBuyListView() {
		
		/***************************************
		 * List in the tab 2
		 ***************************************/
		int res = prepareToBuyList();
		
		// Log
		Log.d("TabActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "res=" + res);
		
		if (res == CONS.PREP_LIST_SUCCESSFUL) {
			lvTab2 = (ListView) findViewById(R.id.itemlist_tab2_lv);
			
//			List<String> listTab2 = new ArrayList<String>();
			
//			for (int i = 1; i < numOfEntries; i++) {
//				
//				listTab2.add("番号: " + i);
//				
//			}//for (int i = 1; i < 11; i++)
			
	//		ArrayAdapter<String> adpTab2 = new ArrayAdapter<String>(
			
//			CONS.TabActv.adpToBuys = new ItemListAdapter2(
			CONS.TabActv.adpToBuys = new ToBuyListAdapter(
					this,
	//				android.R.layout.simple_list_item_1,
					R.layout.adapteritem,
					CONS.TabActv.toBuyList
					);
			
		} else {//if (res == CONS.PREP_LIST_SUCCESSFUL)
			
			// Log
			Log.d("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Prep toBuy list => Failed");
			
		}//if (res == CONS.PREP_LIST_SUCCESSFUL)

//		lvTab2.setAdapter(adpTab2);
		lvTab2.setAdapter(CONS.TabActv.adpToBuys);

		// Log
		Log.d("TabActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "CONS.TabActv.adpToBuys => Set");
		
	}//private void setupToBuyListView()

	private int prepareItemList() {
		/***************************************
		 * itemList
		 ***************************************/
		CONS.TabActv.itemList = new ArrayList<ShoppingItem>();
		
		//
		DBUtils dbm = new DBUtils(this);
		
		SQLiteDatabase rdb = dbm.getReadableDatabase();
		
		Cursor c = null;
		
		try {
			c = rdb.query(
					CONS.DBAdmin.tableName, 
//										DBManager.columns,
//				CONS.DBAdmin.columns_with_index,
					CONS.DBAdmin.columns_with_index2,
											null, null, null, null, null);
		} catch (Exception e) {
			
			// Log
			Log.e("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			rdb.close();
			
			return CONS.PREP_LIST_FAILED;
			
		}//try
		
		//
		c.moveToFirst();
		
		for (int i = 0; i < c.getCount(); i++) {

//			0									1		2		3		4			5
//			{android.provider.BaseColumns._ID, "name", "yomi", "genre", "store", "price"}
			ShoppingItem item = new ShoppingItem(
					c.getInt(0),		// id store
					c.getString(1),		// name
					c.getString(2),		// yomi
					c.getString(3),		// genre
					c.getString(4),		//	store
					c.getInt(5)			// price
					);
			
			//
			CONS.TabActv.itemList.add(item);
			
			//
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)
		
		//
		rdb.close();

		// Log
		Log.d("[" + "TabActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "CONS.TabActv.itemList.size()=" + CONS.TabActv.itemList.size());
		
		
		/***************************************
		 * Sort list
		 ***************************************/
		Methods_sl.sortItemList(CONS.TabActv.itemList);
		
		/***************************************
		 * Return
		 ***************************************/
		return CONS.PREP_LIST_SUCCESSFUL;
		
	}//private void prepareItemList()

	private int prepareToBuyList() {
		/***************************************
		 * itemList
		 ***************************************/
		CONS.TabActv.toBuyList = new ArrayList<ShoppingItem>();
		
		/***************************************
		 * Setup db
		 ***************************************/
		DBUtils dbm = new DBUtils(this);
		
		SQLiteDatabase rdb = dbm.getReadableDatabase();
		
		Cursor c = null;
		
		for (Integer itemId : CONS.TabActv.tab_toBuyItemIds) {
			
			try {
				
				c = rdb.query(
						CONS.DBAdmin.tableName, 
	//										DBManager.columns,
	//				CONS.DBAdmin.columns_with_index,
						CONS.DBAdmin.columns_with_index2,
						String.valueOf(CONS.DBAdmin.columns_with_index2[0]),
						new String[]{String.valueOf(itemId.intValue())},
						null, null, null);
				
			} catch (Exception e) {
				
				// Log
				Log.e("TabActv.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]", e.toString());
				
				rdb.close();
				
				return CONS.PREP_LIST_FAILED;
				
			}//try

			/***************************************
			 * If the cursor is null, then move on to
			 * 	the next id
			 ***************************************/
			if (c == null) {
				
				// Log
				Log.d("TabActv.java"
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
				Log.d("TabActv.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"c.getCount() < 1 => id=" + itemId.intValue());
				
				continue;
				
			}//if (c.getCount() < 1)
			
			
			//
			c.moveToFirst();
			
			for (int i = 0; i < c.getCount(); i++) {
	
	//			0									1		2		3		4			5
	//			{android.provider.BaseColumns._ID, "name", "yomi", "genre", "store", "price"}
				ShoppingItem item = new ShoppingItem(
						c.getInt(0),		// id store
						c.getString(1),		// name
						c.getString(2),		// yomi
						c.getString(3),		// genre
						c.getString(4),		//	store
						c.getInt(5)			// price
						);
				
				//
				CONS.TabActv.toBuyList.add(item);
				
				//
				c.moveToNext();
				
			}//for (int i = 0; i < c.getCount(); i++)

		}//for (Integer itemId : CONS.tab_toBuyItemIds)

		//
		rdb.close();

		/***************************************
		 * Sort list
		 ***************************************/
//		Methods_sl.sortItemList(itemList);
		Methods_sl.sortItemList(CONS.TabActv.toBuyList);
		
		//debug
		//debug
		StringBuilder sb = new StringBuilder();
		
		for (ShoppingItem item : CONS.TabActv.toBuyList) {
			
			sb.append(item.getId());
			
			sb.append(",");
			
		}
		
		// Log
		Log.d("ButtonOnClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "CONS.TabActv.toBuyList=" + sb.toString());
		
		/***************************************
		 * Return
		 ***************************************/
		return CONS.PREP_LIST_SUCCESSFUL;
		
	}//private int prepareToBuyList()

	private void setupListView_B22_v_1_1_b() {
		// TODO Auto-generated method stub
		ListView lv = (ListView) findViewById(R.id.itemlist_tab2_lv);
		
		List<String> list = new ArrayList<String>();
		
		for (int i = 1; i < 11; i++) {
			
			list.add("Number: " + i);
			
		}//for (int i = 1; i < 11; i++)
		
		ArrayAdapter<String> adp = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_1,
				list
				);
		
		lv.setAdapter(adp);
		
	}//private void setupListView_B22_v_1_1_b()

		//		@Override
	public View createTabContent(String tag) {
			Time time = new Time("Asia/Tokyo");
			time.setToNow();
			String date = time.year + "年" + (time.month+1) + "月" + time.monthDay + "日" + time.hour + "時" + time.minute + "分" + time.second + "秒";					
			TextView textView = new TextView(this);
			textView.setText(date);			
			return textView;
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		String tabTag = CONS.TabActv.tabHost.getCurrentTabTag();

//		/***************************************
//		 * Switch the menu file using tab tag
//		 ***************************************/
//		if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first))) {
//			
//			MenuInflater mi = getMenuInflater();
//			mi.inflate(R.menu.itemlist, menu);
//			
//		} else {//if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first)))
//			
//		}//if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first)))

		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.itemlist, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.menu_listitem_filter:

			String tabTag = CONS.TabActv.tabHost.getCurrentTabTag();
			
			if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first))) {
				
				Methods.dlg_filterList2(this);
				
			} else {//if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first)))
				
			}//if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first)))
			
			
//			// Log
//			Log.d("TabActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "CONS.TabActv.tabHost.getCurrentTabTag()=" + CONS.TabActv.tabHost.getCurrentTabTag());
			
//			Methods.dlg_filterList2(this);
			
			break;

		case R.id.menu_listitem_tabToBuy_clear_selections:

			Methods_dlg.dlg_tabActv_clearSelections(this);
			
//			CONS.TabActv.toBuyList.clear();
//			CONS.tab_toBuyItemIds.clear();
//			
//			CONS.TabActv.adpItems.notifyDataSetChanged();
//			CONS.TabActv.adpToBuys.notifyDataSetChanged();
			
			break;
			
		case R.id.menu_listitem_tabToBuy_admin_db:
			
			Methods_dlg.dlg_tabActv_adminDb(this);
			
			break;// case R.id.menu_listitem_tabToBuy_admin_db
			
		case R.id.menu_listitem_tabToBuy_sort_list://-------------------------------
			
			Methods_dlg.dlg_SortList(this);
			
			break;// case R.id.menu_listitem_tabToBuy_sort_list
			
		default:
			break;
		}//switch (item.getItemId())

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void 
	onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		boolean res;
		
		_Setup_InitVars();
		
		////////////////////////////////

		// tabs

		////////////////////////////////
		res = _SetupTabs();

		if (res == false) return;
		
		////////////////////////////////

		// Item list

		////////////////////////////////
		res = _Setup_ItemListView();
		
		if (res == false) return;
		
		_Setup_ToBuyListView();
		
		_Setup_Listeners();
		
		//debug
//		test_B32_v_1_2();

	}//onStart

}//public class TabActv extends TabActivity implements TabHost.TabContentFactory
