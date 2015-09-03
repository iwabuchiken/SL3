package sl3.listeners.dialog;

import sl3.utils.CONS;
import sl3.utils.Methods;
import sl3.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DB_OTL implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog d1;
	private Dialog d2;
	private Dialog d3;
	private Dialog d4;
	
	public DB_OTL(Activity actv, Dialog dlg) {
		//
		this.actv = actv;
		this.d1 = dlg;
	}
	
	public DB_OTL(Activity actv) {
		//
		this.actv = actv;
	}

	public DB_OTL
	(Activity actv, Dialog d1, Dialog d2) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		this.d1 = d1;
		this.d2 = d2;

	}

	public DB_OTL
	(Activity actv, Dialog d1, Dialog d2, Dialog d3) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		this.d1 = d1;
		this.d2 = d2;
		this.d3 = d3;

	}

	public DB_OTL
	(Activity actv, Dialog d1, Dialog d2, Dialog d3, Dialog d4) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		this.d1 = d1;
		this.d2 = d2;
		this.d3 = d3;
		this.d4 = d4;

	}

	//	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		Tags.DialogTags tag_name = (Tags.DialogTags) v.getTag();
//		Tags.DialogButtonTags tag_name = (Tags.DialogButtonTags) v.getTag();
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			switch (tag_name) {
				//
			
//			case tab1_delete_item_ok:
//			case generic_cancel_second_dialog:			
//			case tab2_post_items_ok:
			
			case DLG_GENERIC_CANCEL:
			case DLG_GENERIC_DISMISS:
			case DLG_GENERIC_DISMISS_SECOND_DIALOG:
			case DLG_GENERIC_DISMISS_3RD_DIALOG:
			case GENERIC_DISMISS_THIRD_DIALOG:
			case GENERIC_DISMISS_4TH_DIALOG:
			case GENERIC_DISMISS_SECOND_DIALOG:
			case GENERIC_CANCEL_SECOND_DIALOG:
				
			case ACTV_TAB_OPT_DROP_TABLE_SI:
			case ACTV_TAB_OPT_DROP_TABLE_PS:
				
			case ACTV_TAB_OPT_IMP_DATA_SI:
			case ACTV_TAB_OPT_IMP_DATA_Stores:
			case ACTV_TAB_OPT_IMP_DATA_Genres:
			case ACTV_TAB_OPT_INSERT_NUM_SI:
				
			case ACTV_TAB_OPT_RESTORE_DB:
			case ACTV_TAB_SEARCH_OK:
				
			case DLG_EDIT_ITEMS_BT_OK:
				
			case dlg_register_store_ok:
			case dlg_register_store_cancel:
			case dlg_input_empty_btn_reenter:
			case dlg_input_empty_btn_cancel:
			case dlg_reconfirm_store_name_btn_yes:
			case dlg_reconfirm_store_name_btn_cancel:
			case dlg_register_genre_register:
			case dlg_register_genre_cancel:

			case dlg_reconfirm_genre_name_btn_register:
			case dlg_reconfirm_genre_name_btn_cancel:
			case dlg_create_table_create:
			case dlg_create_table_cancel:
			case dlg_drop_table_btn_cancel:
			case dlg_confirm_drop_table_btn_ok:
			case dlg_confirm_drop_table_btn_cancel:
			case dlg_filter_list_ok:
			case dlg_filter_list_ok2:
			case dlg_filter_list_cancel:
			
			case DLG_SAVE_TOBUY_LIST_BT_OK:
			case dlg_scheduleInDb_ok:
			case dlg_scheduleInDb_update:
				
			case REGISTER_ITEM_OK:
				
			case DLG_SAVE_PUR_HISTORY_OK:
				
			case DLG_POST_ITEMS_OK:
				
			case DLG_POST_STORES_OK:
			case DLG_POST_GENRES_OK:
			case DLG_POST_SIS_OK:
				
			case ACTV_TAB_OPT_UPLOAD_DB:
				
				
				//
				v.setBackgroundColor(Color.GRAY);
				
				break;
				
			}//switch (tag_name)
			
			break;//case MotionEvent.ACTION_DOWN:
			
		case MotionEvent.ACTION_UP:
			switch (tag_name) {
				//
//			case tab1_delete_item_ok:
//			case generic_cancel_second_dialog:			
//			case tab2_post_items_ok:			

			case DLG_GENERIC_CANCEL:
			case DLG_GENERIC_DISMISS:
			case DLG_GENERIC_DISMISS_SECOND_DIALOG:
			case DLG_GENERIC_DISMISS_3RD_DIALOG:
			case GENERIC_DISMISS_THIRD_DIALOG:
			case GENERIC_DISMISS_4TH_DIALOG:
			case GENERIC_DISMISS_SECOND_DIALOG:
//			case generic_cancel_second_dialog:
			case GENERIC_CANCEL_SECOND_DIALOG:
				
			case ACTV_TAB_OPT_DROP_TABLE_SI:
			case ACTV_TAB_OPT_DROP_TABLE_PS:
				
			case ACTV_TAB_OPT_IMP_DATA_SI:
			case ACTV_TAB_OPT_IMP_DATA_Stores:
			case ACTV_TAB_OPT_IMP_DATA_Genres:
			case ACTV_TAB_OPT_INSERT_NUM_SI:
				
			case ACTV_TAB_OPT_RESTORE_DB:
			case ACTV_TAB_SEARCH_OK:
				
			case DLG_EDIT_ITEMS_BT_OK:

			case dlg_register_store_ok:
			case dlg_register_store_cancel:
			case dlg_input_empty_btn_reenter:
			case dlg_input_empty_btn_cancel:
			case dlg_reconfirm_store_name_btn_yes:
			case dlg_reconfirm_store_name_btn_cancel:
			case dlg_register_genre_register:
			case dlg_register_genre_cancel:

			case dlg_reconfirm_genre_name_btn_register:
			case dlg_reconfirm_genre_name_btn_cancel:	

			case dlg_create_table_create:
			case dlg_create_table_cancel:
			
			case dlg_drop_table_btn_cancel:
			
			case dlg_confirm_drop_table_btn_ok:
			case dlg_confirm_drop_table_btn_cancel:
			
			case dlg_filter_list_ok:
			case dlg_filter_list_ok2:
			case dlg_filter_list_cancel:
			
			case DLG_SAVE_TOBUY_LIST_BT_OK:
			
			case dlg_scheduleInDb_ok:
			case dlg_scheduleInDb_update:
				
			case REGISTER_ITEM_OK:
				
			case DLG_SAVE_PUR_HISTORY_OK:
				
			case DLG_POST_ITEMS_OK:
				
			case DLG_POST_STORES_OK:
			case DLG_POST_GENRES_OK:
			case DLG_POST_SIS_OK:
				
			case ACTV_TAB_OPT_UPLOAD_DB:
				
				v.setBackgroundColor(Color.WHITE);
				
				break;
				
			}//switch (tag_name)
			
			break;//case MotionEvent.ACTION_UP:
		}//switch (event.getActionMasked())
		return false;
	}

}
