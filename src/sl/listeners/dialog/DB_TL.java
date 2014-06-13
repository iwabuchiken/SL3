package sl.listeners.dialog;

import sl.utils.CONS;
import sl.utils.Methods;
import sl.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DB_TL implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog dlg;
	
	public DB_TL(Activity actv, Dialog dlg) {
		//
		this.actv = actv;
		this.dlg = dlg;
	}
	
	public DB_TL(Activity actv) {
		//
		this.actv = actv;
	}

//	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		CONS.DialogButtonTags tag_name = (CONS.DialogButtonTags) v.getTag();
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			switch (tag_name) {
				//
			
			case tab1_delete_item_ok:
			case generic_cancel_second_dialog:			
			case tab2_post_items_ok:			
				
				//
				v.setBackgroundColor(Color.GRAY);
				
				break;
				
			}//switch (tag_name)
			
			break;//case MotionEvent.ACTION_DOWN:
			
		case MotionEvent.ACTION_UP:
			switch (tag_name) {
				//
			case tab1_delete_item_ok:
			case generic_cancel_second_dialog:			
			case tab2_post_items_ok:			
				
				v.setBackgroundColor(Color.WHITE);
				
				break;
				
			}//switch (tag_name)
			
			break;//case MotionEvent.ACTION_UP:
		}//switch (event.getActionMasked())
		return false;
	}

}
