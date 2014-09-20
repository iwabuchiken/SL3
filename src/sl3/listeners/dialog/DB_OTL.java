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
			
			case dlg_generic_cancel:
			case DLG_GENERIC_DISMISS:
			case DLG_GENERIC_DISMISS_SECOND_DIALOG:
			case DLG_GENERIC_DISMISS_3RD_DIALOG:
			case GENERIC_DISMISS_THIRD_DIALOG:
			case GENERIC_DISMISS_4TH_DIALOG:
			case GENERIC_DISMISS_SECOND_DIALOG:
				
			case ACTV_TAB_OPT_DROP_TABLE_SI:
			case ACTV_TAB_OPT_DROP_TABLE_PS:
				
			case ACTV_TAB_OPT_IMP_DATA_SI:
			case ACTV_TAB_OPT_IMP_DATA_Stores:
			case ACTV_TAB_OPT_IMP_DATA_Genres:
			case ACTV_TAB_OPT_INSERT_NUM_SI:
				
			case ACTV_TAB_OPT_RESTORE_DB:
			case ACTV_TAB_SEARCH_OK:
				
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

			case dlg_generic_cancel:
			case DLG_GENERIC_DISMISS:
			case DLG_GENERIC_DISMISS_SECOND_DIALOG:
			case DLG_GENERIC_DISMISS_3RD_DIALOG:
			case GENERIC_DISMISS_THIRD_DIALOG:
			case GENERIC_DISMISS_4TH_DIALOG:
			case GENERIC_DISMISS_SECOND_DIALOG:
				
			case ACTV_TAB_OPT_DROP_TABLE_SI:
			case ACTV_TAB_OPT_DROP_TABLE_PS:
				
			case ACTV_TAB_OPT_IMP_DATA_SI:
			case ACTV_TAB_OPT_IMP_DATA_Stores:
			case ACTV_TAB_OPT_IMP_DATA_Genres:
			case ACTV_TAB_OPT_INSERT_NUM_SI:
				
			case ACTV_TAB_OPT_RESTORE_DB:
			case ACTV_TAB_SEARCH_OK:

				v.setBackgroundColor(Color.WHITE);
				
				break;
				
			}//switch (tag_name)
			
			break;//case MotionEvent.ACTION_UP:
		}//switch (event.getActionMasked())
		return false;
	}

}
