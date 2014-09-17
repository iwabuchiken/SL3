package sl3.listeners.dialog;

import sl3.utils.Methods;
import sl3.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DialogButtonOnTouchListener implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog dlg;
	
	public DialogButtonOnTouchListener(Activity actv, Dialog dlg) {
		//
		this.actv = actv;
		this.dlg = dlg;
	}
	
	public DialogButtonOnTouchListener(Activity actv) {
		//
		this.actv = actv;
	}

//	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		Tags.DialogTags tag_name = (Tags.DialogTags) v.getTag();
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
				switch (tag_name) {
					//
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
				
				case dlg_generic_cancel:
				case DLG_GENERIC_DISMISS_SECOND_DIALOG:
				
				case dlg_save_tobuy_list_bt_ok:
				case DLG_GENERIC_DISMISS:
					
				case dlg_scheduleInDb_ok:
				case DLG_GENERIC_DISMISS_3RD_DIALOG:
				case dlg_scheduleInDb_update:
					//
					v.setBackgroundColor(Color.GRAY);
					
					break;
					
				}//switch (tag_name)
			break;//case MotionEvent.ACTION_DOWN:
			
		case MotionEvent.ACTION_UP:
			switch (tag_name) {
				//
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
				
				case dlg_save_tobuy_list_bt_ok:
				
				case DLG_GENERIC_DISMISS:

				case dlg_generic_cancel:
				case DLG_GENERIC_DISMISS_SECOND_DIALOG:

				case dlg_scheduleInDb_ok:
				case DLG_GENERIC_DISMISS_3RD_DIALOG:
				case dlg_scheduleInDb_update:
					//
					v.setBackgroundColor(Color.WHITE);
					
					break;
					
				}//switch (tag_name)
			break;//case MotionEvent.ACTION_UP:
		}//switch (event.getActionMasked())
		return false;
	}

}
