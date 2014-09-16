package sl.listeners;

import java.util.ArrayList;
import java.util.List;

import sl.items.MI;
import sl.utils.CONS;
import sl3.main.R;
import android.app.Activity;
import android.app.TabActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.TabHost.OnTabChangeListener;

public class TCL implements OnTabChangeListener {

	Activity actv;
	
	public TCL(Activity actv) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		
	}

	public void 
	onTabChanged
	(String tabTag) {
		// TODO Auto-generated method stub

		String msg_Log;
		
		// Log
		msg_Log = "tab changed => " + tabTag;
		Log.d("TCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		////////////////////////////////

		// change menu items

		////////////////////////////////
		_onTabChanged__ChangeItems(tabTag);
		
//		// Log
//		msg_Log = "getCurrentTab() => " 
//						+ ((TabActivity)actv).getTabHost().getCurrentTab();
//		Log.d("TCL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
//		// Log
//		if (CONS.TabActv.menu == null) {
//			
//			// Log
//			msg_Log = "CONS.TabActv.menu => null";
//			Log.d("TCL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//		} else {
//
//			msg_Log = "CONS.TabActv.menu => " + CONS.TabActv.menu;
//			Log.d("TCL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//		}
//		
////		actv.onCreateOptionsMenu(CONS.TabActv.menu);
//		CONS.TabActv.menu.removeItem(R.id.actv_tab_1_admin_db);
//		
//		CONS.TabActv.menu
//					.add(
//						0, 
//						R.id.actv_tab_2_admin_db, 
//						0, 
////						Menu.FIRST, 
////						1, 
////						Menu.NONE, 
//						R.string.menu_listitem_tabToBuy_admin)
//					.setIcon(R.drawable.general_ib_ball_blue_48x48)
//					;
		
	}//onTabChanged

	private void 
	_onTabChanged__ChangeItems
	(String tabTag) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// dispatch

		////////////////////////////////
		if (tabTag.equals(actv.getString(
						R.string.tabactv_tabtags_first))) {

			_onTabChanged__ChangeItems__ToFirst();
		
		} else if (tabTag.equals(actv.getString(
						R.string.tabactv_tabtags_second))) {//if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first)))
		
			_onTabChanged__ChangeItems__ToSecond();
		
		} else {//if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first)))
		
			// Log
			String msg_Log = "unknown tabtag name => " + tabTag;
			Log.d("TCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
		
		}//if (tabTag.equals(this.getString(R.string.tabactv_tabtags_first)))
		
		
	}//_onTabChanged__ChangeItems

	private void 
	_onTabChanged__ChangeItems__ToSecond() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// prep: data

		////////////////////////////////
		List<MI> list_MIs = new ArrayList<MI>();
		
		list_MIs.add(new MI.Builder()
						.setId_Item(R.id.actv_tab_2_filter)
						.setId_Title(R.string.menu_listitem_filter)
						.setId_Icon(R.drawable.menu_listitem_filter_30x30_v3)
						.build()
				);
		
		list_MIs.add(new MI.Builder()
					.setId_Item(R.id.actv_tab_2_clear_selections)
					.setId_Title(R.string.menu_listitem_tabToBuy_clear_selections)
					.setId_Icon(R.drawable.general_ib_ball_purple_48x48)
//					.setId_Icon(R.drawable.sl_basket_empty_35x35)
					.build()
				);
		
		list_MIs.add(new MI.Builder()
					.setId_Item(R.id.actv_tab_2_admin_db)
					.setId_Title(R.string.menu_listitem_tabToBuy_admin)
					.setId_Icon(R.drawable.general_ib_ball_blue_48x48)
					.build()
				);
		
		list_MIs.add(new MI.Builder()
					.setId_Item(R.id.actv_tab_2_sort_list)
					.setId_Title(R.string.menu_main_sort_list)
					.setId_Icon(R.drawable.general_ib_ball_brown_48x48)
					.build()
				);
		
//		List<Integer> list_ItemIDs = new ArrayList<Integer>();
//		
//		list_ItemIDs.add(R.id.actv_tab_2_filter);
//		list_ItemIDs.add(R.id.actv_tab_2_clear_selections);
//		list_ItemIDs.add(R.id.actv_tab_2_admin_db);
//		list_ItemIDs.add(R.id.actv_tab_2_sort_list);
//		
//		List<Integer> list_Titles = new ArrayList<Integer>();
//		
//		list_Titles.add(R.string.menu_listitem_filter);
//		list_Titles.add(R.string.menu_listitem_tabToBuy_clear_selections);
//		list_Titles.add(R.string.menu_listitem_tabToBuy_admin);
//		list_Titles.add(R.string.menu_main_sort_list);
//		
//		List<Integer> list_Icons = new ArrayList<Integer>();
//		
//		list_Icons.add(R.drawable.menu_listitem_filter_30x30_v3);
//		list_Icons.add(R.drawable.sl_basket_empty_35x35);
//		list_Icons.add(R.drawable.general_ib_ball_blue_48x48);
//		list_Icons.add(R.drawable.general_ib_ball_brown_48x48);
////		list_Icons.add(android.R.drawable.ic_menu_sort_alphabetically);
		
		CONS.TabActv.menu.clear();

		for (int i = 0; i < list_MIs.size(); i++) {
//			for (int i = 0; i < list_Icons.size(); i++) {
			
			CONS.TabActv.menu
				.add(
					0, 
					list_MIs.get(i).getId_Item(), 
					2, 
					list_MIs.get(i).getId_Title())
				.setIcon(list_MIs.get(i).getId_Icon());
			
		}
		
//		CONS.TabActv.menu
//				.add(
//					0, R.id.actv_tab_2_filter, 
//					2, R.string.menu_listitem_filter)
//				.setIcon(R.drawable.menu_listitem_filter_30x30_v3);
//		
//		CONS.TabActv.menu
//				.add(
//						0, R.id.actv_tab_2_clear_selections, 
//						2, R.string.menu_listitem_tabToBuy_clear_selections)
//				.setIcon(R.drawable.sl_basket_empty_35x35);
//		
//		CONS.TabActv.menu
//				.add(
//						0, R.id.actv_tab_2_admin_db, 
//						2, R.string.menu_listitem_tabToBuy_admin)
//				.setIcon(R.drawable.general_ib_ball_blue_48x48);
//		
//		CONS.TabActv.menu
//				.add(
//						0, R.id.actv_tab_2_sort_list, 
//						2, R.string.menu_main_sort_list)
//				.setIcon(android.R.drawable.ic_menu_sort_alphabetically);
		
	}//_onTabChanged__ChangeItems__ToSecond

	private void 
	_onTabChanged__ChangeItems__ToFirst() {
		// TODO Auto-generated method stub
		
		CONS.TabActv.menu.clear();
		
		CONS.TabActv.menu
				.add(
					0, R.id.actv_tab_1_filter, 
					2, R.string.menu_listitem_filter)
				.setIcon(R.drawable.menu_listitem_filter_30x30_v3);
		
		CONS.TabActv.menu
				.add(
						0, R.id.actv_tab_1_clear_selections, 
						2, R.string.menu_listitem_tabToBuy_clear_selections)
				.setIcon(R.drawable.sl_basket_empty_35x35);
		
		CONS.TabActv.menu
				.add(
						0, R.id.actv_tab_1_admin_db, 
						2, R.string.menu_listitem_tabToBuy_admin)
				.setIcon(R.drawable.sl_db_item_35x35);
		
		CONS.TabActv.menu
				.add(
						0, R.id.actv_tab_1_sort_list, 
						2, R.string.menu_main_sort_list)
				.setIcon(android.R.drawable.ic_menu_sort_alphabetically);
		
		
	}//_onTabChanged__ChangeItems__ToSecond
	
}
