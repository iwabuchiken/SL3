package sl.listeners;

import sl.main.R;
import sl.utils.Methods;
import sl.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

public class ButtonOnTouchListener implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	public ButtonOnTouchListener(Activity actv) {
		//
		this.actv = actv;
	}

//	@Override
	
	public boolean onTouch(View v, MotionEvent event) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		Tags.ButtonTags tag = (Tags.ButtonTags) v.getTag();
		
//		// Log
//		Log.d("ButtonOnTouchListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "tag_name => " + tag.name());
		
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
//			// Log
//			Log.d("ButtonOnTouchListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "ACTION_DOWN");

			switch (tag) {
				//
			case db_manager_activity_create_table:
			case db_manager_activity_drop_table:

			case sl_main_bt_item_list:
			case sl_main_bt_register:
			case sl_main_bt_db:
				
			case itemlist_bt_choose:
			case itemlist_bt_see_chosen:
				//
				v.setBackgroundColor(Color.GRAY);
				
				break;
				
			case itemlist_tabs_bt_choose:
				
//				ImageButton ibAdd = (ImageButton) v.findViewById(R.id.itemlist_bt_choose);
				ImageButton ibAdd = (ImageButton) v.findViewById(R.id.itemlist_tab1_ib);
				
//				ibAdd.setImageResource(R.drawable.sl_add_item_touched_150x150);
				ibAdd.setImageResource(R.drawable.sl_add_item_bar_touched_150x150);
				
				break;// case itemlist_tabs_bt_choose
				
			default:
				break;
				
			}//switch (tag_name)
			
			break;//case MotionEvent.ACTION_DOWN:
			
		case MotionEvent.ACTION_UP:
			
//			// Log
//			Log.d("ButtonOnTouchListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "ACTION_UP");
			
			
			switch (tag) {
				//
			case db_manager_activity_create_table:
			case db_manager_activity_drop_table:

			case sl_main_bt_item_list:
			case sl_main_bt_register:
			case sl_main_bt_db:
				
			case itemlist_bt_choose:
			case itemlist_bt_see_chosen:

					//
					v.setBackgroundColor(Color.WHITE);
					
					break;
					
			case itemlist_tabs_bt_choose:
				
//				ImageButton ibAdd = (ImageButton) v.findViewById(R.id.itemlist_bt_choose);
				ImageButton ibAdd = (ImageButton) v.findViewById(R.id.itemlist_tab1_ib);
				
//				ibAdd.setImageResource(R.drawable.sl_add_item);
//				ibAdd.setImageResource(R.drawable.sl_add_item_150x150);
				ibAdd.setImageResource(R.drawable.sl_add_item_bar_150x150);
				
				break;// case itemlist_tabs_bt_choose
				
			default:
				break;

					
				}//switch (tag_name)
			
			break;//case MotionEvent.ACTION_UP:
			
			
		}//switch (event.getActionMasked())
		return false;
	}

}
