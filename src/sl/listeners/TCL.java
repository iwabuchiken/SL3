package sl.listeners;

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
		
		CONS.TabActv.menu.clear();
		
		CONS.TabActv.menu
				.add(
					0, R.id.actv_tab_2_filter, 
					2, R.string.menu_listitem_filter)
				.setIcon(R.drawable.menu_listitem_filter_30x30_v3);
		
		CONS.TabActv.menu
				.add(
						0, R.id.actv_tab_2_clear_selections, 
						2, R.string.menu_listitem_tabToBuy_clear_selections)
				.setIcon(R.drawable.sl_basket_empty_35x35);
		
		CONS.TabActv.menu
				.add(
						0, R.id.actv_tab_2_admin_db, 
						2, R.string.menu_listitem_tabToBuy_admin)
				.setIcon(R.drawable.general_ib_ball_blue_48x48);
		
		CONS.TabActv.menu
				.add(
						0, R.id.actv_tab_2_sort_list, 
						2, R.string.menu_main_sort_list)
				.setIcon(android.R.drawable.ic_menu_sort_alphabetically);
		
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
