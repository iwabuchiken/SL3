package sl3.listeners;

import java.util.ArrayList;
import java.util.List;

import sl3.items.MI;
import sl3.main.R;
import sl3.utils.CONS;
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

		////////////////////////////////

		// validate: menu => not null

		////////////////////////////////
		if (CONS.TabActv.menu == null) {
			
			// Log
			String msg_Log = "CONS.TabActv.menu => null";
			Log.d("TCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
			
		}
		
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
					.setId_Item(R.id.actv_tab_1_admin_db)
					.setId_Title(R.string.menu_listitem_tabToBuy_admin)
					.setId_Icon(R.drawable.general_ib_ball_blue_48x48)
					.build()
				);
		
		list_MIs.add(new MI.Builder()
					.setId_Item(R.id.actv_tab_2_lists)
					.setId_Title(R.string.menu_listitem_tabToBuy_lists)
					.setId_Icon(R.drawable.general_ib_ball_brown_48x48)
					.build()
				);
		
		list_MIs.add(new MI.Builder()
					.setId_Item(R.id.actv_tab_2_db)
					.setId_Title(R.string.menu_listitem_tabToBuy_db)
					.setId_Icon(R.drawable.general_ib_ball_green_48x48)
					.build()
				);
		
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
		
	}//_onTabChanged__ChangeItems__ToSecond

	private void 
	_onTabChanged__ChangeItems__ToFirst() {
		// TODO Auto-generated method stub
		
		CONS.TabActv.menu.clear();

		////////////////////////////////

		// prep: data

		////////////////////////////////
		List<MI> list_MIs = new ArrayList<MI>();
		
//		list_MIs.add(new MI.Builder()
//					.setId_Item(R.id.actv_tab_1_filter)
//					.setId_Title(R.string.menu_listitem_filter)
//					.setId_Icon(R.drawable.menu_listitem_filter_30x30_v3)
//					.build()
//				);
		
		list_MIs.add(new MI.Builder()
					.setId_Item(R.id.actv_tab_1_search)
					.setId_Title(R.string.commons_lbl_search)
					.setId_Icon(R.drawable.general_ib_ball_yellow_48x48)
					.build()
				);
		
		list_MIs.add(new MI.Builder()
					.setId_Item(R.id.actv_tab_1_clear_selections)
					.setId_Title(R.string.menu_listitem_tabToBuy_clear_selections)
					.setId_Icon(R.drawable.sl_basket_empty_35x35)
					.build()
				);
		
		list_MIs.add(new MI.Builder()
					.setId_Item(R.id.actv_tab_1_admin_db)
					.setId_Title(R.string.menu_listitem_tabToBuy_admin)
					.setId_Icon(R.drawable.general_ib_ball_blue_48x48)
					.build()
				);
		
		list_MIs.add(new MI.Builder()
					.setId_Item(R.id.actv_tab_1_sort_list)
					.setId_Title(R.string.menu_main_sort_list)
					.setId_Icon(android.R.drawable.ic_menu_sort_alphabetically)
					.build()
				);
		
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
//					0, R.id.actv_tab_1_filter, 
//					2, R.string.menu_listitem_filter)
//				.setIcon(R.drawable.menu_listitem_filter_30x30_v3);
//		
//		CONS.TabActv.menu
//				.add(
//						0, R.id.actv_tab_1_clear_selections, 
//						2, R.string.menu_listitem_tabToBuy_clear_selections)
//				.setIcon(R.drawable.sl_basket_empty_35x35);
//		
//		CONS.TabActv.menu
//				.add(
//						0, R.id.actv_tab_1_admin_db, 
//						2, R.string.menu_listitem_tabToBuy_admin)
//				.setIcon(R.drawable.sl_db_item_35x35);
//		
//		CONS.TabActv.menu
//				.add(
//						0, R.id.actv_tab_1_sort_list, 
//						2, R.string.menu_main_sort_list)
//				.setIcon(android.R.drawable.ic_menu_sort_alphabetically);
		
		
	}//_onTabChanged__ChangeItems__ToSecond
	
}
