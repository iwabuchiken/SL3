package sl3.utils;

public class Tags {

	public static enum DialogTags {
		// dlg_generic
		DLG_GENERIC_CANCEL, DLG_GENERIC_DISMISS,
		DLG_GENERIC_DISMISS_SECOND_DIALOG,
		DLG_GENERIC_DISMISS_3RD_DIALOG,
		
		generic_cancel_second_dialog, 
		
		// dlg_register_store.xml
		dlg_register_store_ok, dlg_register_store_cancel,

		// dlg_input_empty.xml
		dlg_input_empty_btn_reenter, dlg_input_empty_btn_cancel,
		
		// dlg_reconfirm_store_name.xml
		dlg_reconfirm_store_name_btn_yes, dlg_reconfirm_store_name_btn_cancel,
		
		// dlg_register_genre.xml
		dlg_register_genre_register, dlg_register_genre_cancel,
		
		// dlg_reconfirm_genre_name.xml
		dlg_reconfirm_genre_name_btn_register, dlg_reconfirm_genre_name_btn_cancel,
		
		// dlg_create_table.xml
		dlg_create_table_create, dlg_create_table_cancel,

		// dlg_drop_table.xml
		dlg_drop_table_btn_cancel, dlg_drop_table,
		
		// dlg_confirm_drop_table.xml
		dlg_confirm_drop_table_btn_ok, dlg_confirm_drop_table_btn_cancel, dlg_confirm_drop_table,

		// dlg_filter_list.xml
		dlg_filter_list_ok, dlg_filter_list_cancel,
		dlg_filter_list_ok2, dlg_filter_list_cancel2,
		
		// dlg_register_main.xml
		dlg_register_main,
		
		// dlg_db_admin.xml
		dlg_db_admin_lv,
		
		// TabActv.java
		dlg_tabactv_tab2_lv, dlg_tabActv_adminDb,

		// dlg_save_tobuy_list.xml
		DLG_SAVE_TOBUY_LIST_BT_OK,
		
		// dlg_clear_selections
		dlg_clear_selections,
		
		// dlg_scheduleInDb
		dlg_scheduleInDb_ok, dlg_scheduleInDb_update,
		
		//dlg_confirm_delete_ps_item.xml
		dlg_confirm_delete_ps_item_bt_ok,
		
		// dlg_edit_items.xml
		DLG_EDIT_ITEMS_BT_OK,
		
		// dlg_sort_list.xml
		dlg_sort_list_lv,
		
		// dlg_item_list_long_click
		dlg_item_list_long_click, 
		
		ACTV_TAB_OPT_ADMIN, 
		ACTV_TAB_OPT_OPERATIONS, 
		ACTV_TAB_OPT_TABLES, 
		
		ACTV_TAB_OPT_DROP_TABLE_SI, 
		ACTV_TAB_OPT_DROP_TABLE_STORES, 
		ACTV_TAB_OPT_DROP_TABLE_GENRES, 
		
		GENERIC_DISMISS_ALL_SECOND_DIALOG, 
		GENERIC_DISMISS_ALL_2ND_DIALOG, 
		GENERIC_DISMISS_ALL_3RD_DIALOG, 
		
		GENERIC_DISMISS_SECOND_DIALOG,
		GENERIC_DISMISS_THIRD_DIALOG, 
		GENERIC_DISMISS_4TH_DIALOG, 
		
		ACTV_TAB_OPT_IMP_DATA_SI, 
		ACTV_TAB_OPT_IMP_DATA_Stores, 
		ACTV_TAB_OPT_IMP_DATA_Genres, 
		
		ACTV_TAB_OPT_DB, 
		ACTV_TAB_OPT_INSERT_NUM_SI, 
		ACTV_TAB_OPT_RESTORE_DB, 
		ACTV_TAB_OPT_DROP_TABLE_PS, ACTV_TAB_SEARCH_OK, TAB1_LONG_CLICK,
		
	}//public static enum DialogTags
	
	public static enum ButtonTags {
		// DBAdminActivity.java
		db_manager_activity_create_table, db_manager_activity_drop_table,

		// ShoppingList.java
		sl_main_bt_item_list, sl_main_bt_register, sl_main_bt_db,
		
		// itemlist.xml
		itemlist_bt_choose, itemlist_bt_see_chosen,
		
		// itemlist_tabs.xml
		ITEMLIST_TABS_BT_CHOOSE,
		
		//
		register, 
		
		ITEMLIST_TABS_IB_TOP, 
		ITEMLIST_TABS_IB_BOTTOM, 
		ITEMLIST_TABS_IB_UP, 
		ITEMLIST_TABS_IB_DOWN,
		
	}//public static enum ButtonTags

	public static enum ViewNames {
		TV, BT,
	}
	
	public static enum ListTags {
		// TabActv.java
		TAB_ITEM_LIST, tab_toBuyList,
		LOAD_TOBUY_LIST, delete_toBuyList,
	}
	
	public static enum SortTags {
		//
		pslist_store_name, pslist_due_date,
		
	}

	public static enum SpinnerTag {
		spStrore, spGenre,
	}

	/*********************************
	 * Dialog button tags
	 *********************************/
	public static enum DialogButtonTags {
		tab1_delete_item_ok,
		
		generic_cancel_second_dialog,
		
		tab2_post_items_ok, dlg_generic_dismiss,
		
	};

	/*********************************
	 * List tags
	 *********************************/
	public static enum ListViewTags {
		TAB1_LONG_CLICK,
	};
	
}//public class Tags
