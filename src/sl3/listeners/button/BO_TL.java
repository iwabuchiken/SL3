package sl3.listeners.button;

import sl3.main.R;
import sl3.utils.Methods;
import sl3.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

public class BO_TL implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	public BO_TL(Activity actv) {
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
		
		ImageButton ib;
		
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
				
			case ITEMLIST_TABS_BT_CHOOSE:
				
//				ImageButton ibAdd = (ImageButton) v.findViewById(R.id.itemlist_bt_choose);
				ImageButton ibAdd = (ImageButton) v.findViewById(R.id.itemlist_tab1_ib);
				
//				ibAdd.setImageResource(R.drawable.sl_add_item_touched_150x150);
				ibAdd.setImageResource(R.drawable.sl_add_item_bar_touched_150x150);
				
				break;// case itemlist_tabs_bt_choose
				
			case ITEMLIST_TABS_IB_TOP:
				
				ib = (ImageButton) v.findViewById(R.id.itemlist_tab1_ib_top);
				
				ib.setImageResource(R.drawable.actv_showlist_bt_top_45x45_disabled);
				
				break;// case itemlist_tabs_bt_choose
				
			case ITEMLIST_TABS_IB_BOTTOM:
				
				ib = (ImageButton) v.findViewById(R.id.itemlist_tab1_ib_bottom);
				
				ib.setImageResource(R.drawable.actv_showlist_bt_bottom_45x45_disabled);
				
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
					
			case ITEMLIST_TABS_BT_CHOOSE:
				
//				ImageButton ibAdd = (ImageButton) v.findViewById(R.id.itemlist_bt_choose);
				ImageButton ibAdd = (ImageButton) v.findViewById(R.id.itemlist_tab1_ib);
				
//				ibAdd.setImageResource(R.drawable.sl_add_item);
//				ibAdd.setImageResource(R.drawable.sl_add_item_150x150);
				ibAdd.setImageResource(R.drawable.sl_add_item_bar_150x150);
				
				break;// case itemlist_tabs_bt_choose
				
			case ITEMLIST_TABS_IB_TOP:
				
				ib = (ImageButton) v.findViewById(R.id.itemlist_tab1_ib_top);
				
				ib.setImageResource(R.drawable.actv_showlist_bt_top_45x45);
				
				break;// case itemlist_tabs_bt_choose

			case ITEMLIST_TABS_IB_BOTTOM:
				
				ib = (ImageButton) v.findViewById(R.id.itemlist_tab1_ib_bottom);
				
				ib.setImageResource(R.drawable.actv_showlist_bt_bottom_45x45);
				
				break;// case itemlist_tabs_bt_choose
				
			default:
				break;

					
				}//switch (tag_name)
			
			break;//case MotionEvent.ACTION_UP:
			
			
		}//switch (event.getActionMasked())
		return false;
	}

}
