package sl.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import sl3.adapters.ItemListAdapter;
import sl3.adapters.ItemListAdapter2;
import sl3.adapters.ToBuyListAdapter;
import sl3.items.PS;
import sl3.items.SI;
import sl3.listeners.ItemSelectedListener;
import sl3.listeners.TCL;
import sl3.listeners.button.BO_CL;
import sl3.listeners.button.BO_TL;
import sl3.listeners.button.ButtonOnClickListener;
import sl3.listeners.button.ButtonOnTouchListener;
import sl3.listeners.list.LOI_CL;
import sl3.listeners.list.LOI_LCL;
import sl3.main.R;
import sl3.utils.CONS;
import sl3.utils.DBUtils;
import sl3.utils.Methods;
import sl3.utils.Methods_dlg;
import sl3.utils.Methods_sl;
import sl3.utils.Tags;
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
import android.view.KeyEvent;
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

public class TabActv extends TabActivity 
					implements TabHost.TabContentFactory {
	
//	public static TabHost CONS.TabActv.tabHost;
//	TabSpec CONS.TabActv.firstTab;
//	TabSpec CONS.TabActv.secondTab;

//	ListView lvTab1;
//	ListView lvTab2;

//	Spinner spStore;
//	Spinner spGenre;
	
//	ArrayAdapter<String> adpTab1;
//	ArrayAdapter<String> adpTab2;

//	ItemListAdapter2 adpItems;
	
//	List<ShoppingItem> itemList;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemlist_tabs);
        
        setTitle(this.getClass().getName());
        
    }//public void onCreate(Bundle savedInstanceState)

    private void test_B32_v_1_2() {
		// TODO Auto-generated method stub
    	/***************************************
		 * Build: List
		 ***************************************/
    	List<SI> itemList = new ArrayList<SI>();
    	
		itemList.add(new SI.Builder().setName("B").setPrice(1).build());
		itemList.add(new SI.Builder().setName("C").setPrice(1).build());
		itemList.add(new SI.Builder().setName("A").setPrice(2).build());
		itemList.add(new SI.Builder().setName("B").setPrice(3).build());
		itemList.add(new SI.Builder().setName("B").setPrice(2).build());
		itemList.add(new SI.Builder().setName("A").setPrice(1).build());
		
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
		Collections.sort(itemList, new Comparator<SI>(){

//			@Override
			public int compare(SI i1, SI i2) {

				
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
		Collections.sort(itemList, new Comparator<SI>(){

//			@Override
			public int compare(SI i1, SI i2) {
				
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
		 * Set listener: CONS.TabActv.lvTab1
		 ***************************************/
		CONS.TabActv.lvTab1.setTag(Tags.ListTags.tab_itemList);
		
		CONS.TabActv.lvTab1.setOnItemClickListener(new LOI_CL(this));

		CONS.TabActv.lvTab1.setOnItemLongClickListener(new LOI_LCL(this));
		
		/***************************************
		 * Set listener: ImageButton
		 ***************************************/
		ImageButton ib_tab1Choose = (ImageButton) findViewById(R.id.itemlist_tab1_ib);
		
//		ib_tab1Choose.setTag(Tags.ButtonTags.itemlist_tabs_bt_choose);
		ib_tab1Choose.setTag(Tags.ButtonTags.ITEMLIST_TABS_BT_CHOOSE);
		
		ib_tab1Choose.setOnClickListener(new BO_CL(this));
//		ib_tab1Choose.setOnClickListener(new ButtonOnClickListener(this));
		
		ib_tab1Choose.setOnTouchListener(new BO_TL(this));
		
		/***************************************
		 * Set listener: lvTab2
		 ***************************************/
		/***************************************
		 * Listener: Item click
		 ***************************************/
		CONS.TabActv.lvTab2.setTag(Tags.ListTags.tab_toBuyList);
		
		CONS.TabActv.lvTab2.setOnItemClickListener(new LOI_CL(this));
		
		/***************************************
		 * Listener: Item long click
		 ***************************************/
		CONS.TabActv.lvTab2.setOnItemLongClickListener(new LOI_LCL(this));
		
		/***************************************
		 * Change listeners: Spinner: store
		 ***************************************/
		CONS.TabActv.spStore.setTag(Tags.SpinnerTag.spStrore);
		CONS.TabActv.spStore.setOnItemSelectedListener(new ItemSelectedListener(this));
		
		// Log
		String msg_Log = "CONS.TabActv.spStore => listener set";
		Log.d("TabActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		/***************************************
		 * Change listeners: Spinner: store
		 ***************************************/
		CONS.TabActv.spGenre.setTag(Tags.SpinnerTag.spGenre);
		CONS.TabActv.spGenre.setOnItemSelectedListener(new ItemSelectedListener(this));
		
		// Log
		msg_Log = "CONS.TabActv.spGenre => listener set";
		Log.d("TabActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// tab changed

		////////////////////////////////
		TabHost th = this.getTabHost();
		
		//REF http://stackoverflow.com/questions/3583405/get-index-of-selected-tab-in-tabhost answered Aug 27 '10 at 15:31
		th.setOnTabChangedListener(new TCL(this));
		
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
		CONS.TabActv.spStore = (Spinner) this.findViewById(R.id.itemlist_tab1_sp_store_name);
		
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
		
		/*----------------------------
		 * 4. Set adapter to spinner
			----------------------------*/
		CONS.TabActv.spStore.setAdapter(CONS.TabActv.adp_List_Store);
//		spStore.setAdapter(adapter);
	
		/***************************************
		 * Set initial value
		 ***************************************/
		int num = 0;
		
		for (int i = 0; i < CONS.TabActv.adp_List_Store.getCount(); i++) {
			
			String storeName = CONS.TabActv.adp_List_Store.getItem(i);
	
			if (storeName.equals(this.getString(R.string.generic_label_all))) {
				
				num = i;
				
				break;
				
			}//if (si.getName() == condition)
			
		}//for (int i = 0; i < adapter.getCount(); i++)
		
		
		CONS.TabActv.spStore.setSelection(num);
	
		/***************************************
		 * Spinner: Genre
		 ***************************************/
		CONS.TabActv.spGenre = (Spinner) this.findViewById(R.id.itemlist_tab1_sp_genre);
		
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
		
		
		/*----------------------------
		 * 4. Set adapter to spinner
			----------------------------*/
		CONS.TabActv.spGenre.setAdapter(CONS.TabActv.adapterGenre);
		
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

//		//debug
//		num = 0;
		
		CONS.TabActv.spGenre.setSelection(num);
	
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
		int res = _prepareItemList();
		
		/***************************************
		 * List in the tab 1
		 ***************************************/
		if (res == CONS.RV.PREP_LIST_SUCCESSFUL) {

			CONS.TabActv.lvTab1 = (ListView) findViewById(R.id.itemlist_tab1_lv);
			
			List<String> listTab1 = new ArrayList<String>();

			
			
			for (int i = 1; i < numOfEntries; i++) {
				
				listTab1.add("Number: " + i);
				
			}//for (int i = 1; i < 11; i++)
			
			/***************************************
			 * Adapter
			 ***************************************/
			// Log
			String msg_Log = "CONS.TabActv.itemList.get(10).getNum() => " 
						+ CONS.TabActv.itemList.get(10).getNum();
			Log.d("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			
			CONS.TabActv.adpItems = new ItemListAdapter2(
					this,
					R.layout.adapteritem,
					CONS.TabActv.itemList
					);
			
//			CONS.TabActv.lvTab1.setAdapter(adpTab1);
			CONS.TabActv.lvTab1.setAdapter(CONS.TabActv.adpItems);

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
		int res = _prep_ToBuyList();
		
		// Log
		String msg_Log = "_prep_ToBuyList => done";
		Log.d("TabActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		// Log
		Log.d("TabActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "res=" + res);
		
		if (res == 1) {
			CONS.TabActv.lvTab2 = (ListView) findViewById(R.id.itemlist_tab2_lv);
			
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

//		CONS.TabActv.lvTab2.setAdapter(adpTab2);
		CONS.TabActv.lvTab2.setAdapter(CONS.TabActv.adpToBuys);

		// Log
		Log.d("TabActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "CONS.TabActv.adpToBuys => Set");
		
	}//private void setupToBuyListView()

	private int _prepareItemList() {
		/***************************************
		 * itemList
		 ***************************************/
		CONS.TabActv.itemList = new ArrayList<SI>();
		
		//
		DBUtils dbm = new DBUtils(this);
		
		SQLiteDatabase rdb = dbm.getReadableDatabase();
		
		Cursor c = null;
		
		try {
			c = rdb.query(
					CONS.DB.tname_si, 
//										DBManager.columns,
//				CONS.DBAdmin.columns_with_index,
					CONS.DB.col_Names_SI_full,
//					CONS.DB.columns_with_index2,
											null, null, null, null, null);
		} catch (Exception e) {
			
			// Log
			Log.e("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			rdb.close();
			
			return CONS.RV.PREP_LIST_FAILED;
			
		}//try
		
		//
		c.moveToFirst();

//		android.provider.BaseColumns._ID,	// 0
//		"created_at", "modified_at",			// 1,2
//		
//		"store", "name", "price",			// 3,4,5
//		"genre", "yomi", "num",				// 6,7,8
//		
//		"posted_at"							// 9
		
		for (int i = 0; i < c.getCount(); i++) {

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
			
			//
			CONS.TabActv.itemList.add(si);
			
//			// Log
//			String msg_Log = "c.getInt(8) => " + c.getInt(8);
//			Log.d("TabActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
			
//			// Log
//			String msg_Log = "si.getNum() => " + si.getNum();
//			Log.d("TabActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
			
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
		return CONS.RV.PREP_LIST_SUCCESSFUL;
		
	}//private void prepareItemList()

	/******************************
		@return
			-1 query exception<br>
			1 List => built<br>
	 ******************************/
	private int _prep_ToBuyList() {
		/***************************************
		 * itemList
		 ***************************************/
		CONS.TabActv.toBuyList = new ArrayList<SI>();
		
		/***************************************
		 * Setup db
		 ***************************************/
		DBUtils dbm = new DBUtils(this);
		
		SQLiteDatabase rdb = dbm.getReadableDatabase();
		
		String where = CONS.DB.col_Names_SI_full[0] + " = ?";
		String[] args = null;
		
		SI si = null;
		
		Cursor c = null;
		
		// Log
		if (CONS.TabActv.tab_toBuyItemIds != null) {
			
			String msg_Log = "CONS.TabActv.tab_toBuyItemIds.size() => "
					+ CONS.TabActv.tab_toBuyItemIds.size();
			Log.d("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
			// Log
			String msg_Log = "CONS.TabActv.tab_toBuyItemIds.size() => Null";
			Log.d("TabActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

		}
		
		for (Integer itemId : CONS.TabActv.tab_toBuyItemIds) {
			
			args = new String[]{String.valueOf(itemId.intValue())};
			
			try {
				
				c = rdb.query(
						CONS.DB.tableName, 
						CONS.DB.col_Names_SI_full,
//						CONS.DB.columns_with_index2,
						where,
//						String.valueOf(CONS.DB.columns_with_index2[0]),
						args,
//						new String[]{String.valueOf(itemId.intValue())},
						null, null, null);
				
			} catch (Exception e) {
				
				// Log
				Log.e("TabActv.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]", e.toString());
				
				rdb.close();
				
				return -1;
//				return CONS.RV.PREP_LIST_FAILED;
				
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
			
//				android.provider.BaseColumns._ID,	// 0
//				"store", 							// 1
//				"name", "price",					// 2,3
//				"genre", "yomi",					// 4,5
//				"created_at", "updated_at",			// 6,7
//				"posted_at"							// 8
				
//				android.provider.BaseColumns._ID,	// 0
//				"created_at", "modified_at",			// 1,2
//				"store", "name", "price",			// 3,4,5
//				"genre", "yomi", "num",				// 6,7,8
//				"posted_at"							// 9
				
				
//				SI si = null;
				
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
				//
				CONS.TabActv.toBuyList.add(si);
				
				//
				c.moveToNext();
				
				// Log
				String msg_Log = "si.getNum() => " + si.getNum();
				Log.d("TabActv.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
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
		
		for (SI item : CONS.TabActv.toBuyList) {
			
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
		return 1;
		
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
		
		String msg_Log;
		
		String tabTag = CONS.TabActv.tabHost.getCurrentTabTag();
		int tabID = CONS.TabActv.tabHost.getCurrentTab();
//		String tabTag = CONS.TabActv.tabHost.getCurrentTabTag();

		// Log
		msg_Log = String.format(
							Locale.JAPAN,
							"current tab => %s (%d)",
							tabTag, tabID);
		Log.d("TabActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
//		TabHost.
		
		/***************************************
		 * Switch the menu file using tab tag
		 ***************************************/
		MenuInflater mi = getMenuInflater();
		
		if (tabTag.equals(this.getString(
							R.string.tabactv_tabtags_first))) {
			
			mi.inflate(R.menu.actv_tab_1, menu);
			
		} else if (tabTag.equals(this.getString(
							R.string.tabactv_tabtags_second))) {//if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first)))
			
			mi.inflate(R.menu.actv_tab_2, menu);
			
		} else {//if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first)))
			
			mi.inflate(R.menu.actv_tab_1, menu);
			
		}//if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first)))

//		MenuInflater mi = getMenuInflater();
//		mi.inflate(R.menu.actv_tab_1, menu);

		CONS.TabActv.menu = menu;
		
		// Log
		msg_Log = "menu => created";
		Log.d("TabActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.actv_tab_1_filter:

			String tabTag = CONS.TabActv.tabHost.getCurrentTabTag();
			
			if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first))) {
				
				Methods.dlg_filterList2(this);
				
			} else {//if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first)))
				
			}//if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first)))
			
			break;

		case R.id.actv_tab_1_clear_selections:

			Methods_dlg.dlg_tabActv_clearSelections(this);
			
			break;
			
		case R.id.actv_tab_1_admin_db:
			
//			Methods_dlg.dlg_tabActv_adminDb(this);
			Methods_dlg.dlg_OptMenu_TabActv_Admin(this);
			
			break;// case R.id.menu_listitem_tabToBuy_admin_db
			
		case R.id.actv_tab_1_sort_list://-------------------------------
			
			Methods_dlg.dlg_SortList(this);
			
			break;// case R.id.menu_listitem_tabToBuy_sort_list
			
		case R.id.actv_tab_2_lists://-------------------------------
			
			Methods_dlg.dlg_OptMenu_Lists(this);
			
			break;// case R.id.menu_listitem_tabToBuy_sort_list
			
		case R.id.actv_tab_2_db://-------------------------------
			
			Methods_dlg.dlg_OptMenu_DB(this);
			
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
	
		//debug
		do_test();

		////////////////////////////////

		// init

		////////////////////////////////
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
		
		// Log
		String msg_Log = "_Setup_ToBuyListView => done";
		Log.d("TabActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		_Setup_Listeners();
		
		// Log
		msg_Log = "listeners => set";
		Log.d("TabActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		//debug
//		do_test();
		
//		test_B32_v_1_2();

	}//onStart

	private void 
	do_test() {
		// TODO Auto-generated method stub
		
		_do_test_D_4_SEG_1_V_3_0();
//		_do_test_D_4_V_1_0();
		
	}

	private void 
	_do_test_D_4_SEG_1_V_3_0() {
		// TODO Auto-generated method stub
	
		Methods.restore_DB(this);
		
	}

	private void 
	_do_test_D_4_V_1_0() {
		// TODO Auto-generated method stub
	
//		this.getTabHost().getCurrentTab()
		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		Methods.confirm_quit(this, keyCode);
		
		return super.onKeyDown(keyCode, event);
	}

}//public class TabActv extends TabActivity implements TabHost.TabContentFactory
