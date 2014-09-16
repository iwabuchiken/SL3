package sl3.listeners.dialog;

import java.util.Calendar;

import sl3.items.PS;
import sl3.items.SI;
import sl3.main.R;
import sl3.utils.CONS;
import sl3.utils.DBUtils;
import sl3.utils.Methods;
import sl3.utils.Methods_dlg;
import sl3.utils.Methods_sl;
import sl3.utils.Tags;
import sl.main.RegisterItemActv;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class
DB_CL implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog d1;
	Dialog d2;		//=> Used in dlg_input_empty_btn_XXX
	Dialog dlg3;		//=> Methods_dlg.java: Dialog dlg_template_okCancel_3Dialogues

	PS ps;
	
	SI si;
	
	//
	Vibrator vib;
	
	public DB_CL(Activity actv, Dialog dlg1,
			Dialog dlg2) {
		//
		this.actv = actv;
		this.d1 = dlg1;
		this.d2 = dlg2;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	public DB_CL
	(Activity actv, Dialog dlg1,
			Dialog dlg2, SI si) {
		
		this.actv = actv;
		this.d1 = dlg1;
		this.d2 = dlg2;
		
		this.si = si;
		
		//
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	public 
	DB_CL
	(Activity actv, Dialog d1) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		this.d1 = d1;
		
		//
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	//	@Override
	public void onClick(View v) {
		//
		Tags.DialogButtonTags tag_name = (Tags.DialogButtonTags) v.getTag();

		// Log
		Log.d("[" + "DB_CL.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "tag => " + tag_name);
		//
		switch (tag_name) {
		
		case tab1_delete_item_ok:
			
			case_tab1_delete_item_ok(v);
			
			break;
			
		case generic_cancel_second_dialog:
			
			d2.dismiss();
			
			break;
			
		case tab2_post_items_ok:
			
			d2.dismiss();
			
			Methods_sl.post_BoughtItems(actv, d1, d2);
			
			break;
			
		case dlg_generic_dismiss:
			
			d1.dismiss();
			
			break;
			
		default:
			break;
		}//switch (tag_name)
		
	}//public void onClick(View v) {

	private void
	case_tab1_delete_item_ok(View v) {
		// TODO Auto-generated method stub
		/*********************************
		 * Delete item from: database
		 * Delete item from: tab1 list
		 * Dismiss dialogues
		 *********************************/
		boolean result;
		
		/*********************************
		 * Delete item from: database
		 *********************************/
		result = Methods_sl.delete_item_from_database(actv, si);
		
		if (result == false) {
			
			// debug
			Toast.makeText(actv,
					"Delete from db => Failed: " + si.getName(),
					Toast.LENGTH_SHORT).show();
			
			return;
		} else {
			
			// debug
			Toast.makeText(actv,
					"Deleted from db: " + si.getName(),
					Toast.LENGTH_SHORT).show();
			
		}
		
		/*********************************
		 * Delete item from: tab1 list
		 *********************************/
		result = Methods_sl.delete_item_from_item_list(actv, si);
		
		if (result == false) {
			
			// debug
			Toast.makeText(actv,
					"Delete from tab list => Failed: " + si.getName(),
					Toast.LENGTH_SHORT).show();
			
			return;
			
		} else {
			
			// debug
			Toast.makeText(actv,
					"Deleted from tab list: " + si.getName(),
					Toast.LENGTH_SHORT).show();
			
		}
		
		/*********************************
		 * Dismiss dialogues
		 *********************************/
		d2.dismiss();
		d1.dismiss();
		
	}//case_tab1_delete_item_ok(View v)

}//DialogButtonOnClickListener implements OnClickListener
