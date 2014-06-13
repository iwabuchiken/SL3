package sl.listeners.list;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import sl.items.ShoppingItem;
import sl.main.MainActv;
import sl.main.R;
import sl.main.RegisterItemActv;
import sl.tasks.Task_GetYomi;
import sl.utils.CONS;
import sl.utils.DBUtils;
import sl.utils.Methods;
import sl.utils.Methods_dlg;
import sl.utils.Methods_sl;
import sl.utils.Tags;
import sl.utils.Tags.DialogTags;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListViewCL implements OnItemClickListener {

	//
	Activity actv;
	
	Dialog dlg;
	Dialog dlg1;
	Dialog dlg2;
	
	ShoppingItem si;
	//
	Vibrator vib;
	
	//
	CONS.ListViewTags lvTag = null;
	
	public ListViewCL(Activity actv, Dialog dlg, ShoppingItem si) {
		// 
		this.actv = actv;
		this.dlg = dlg;
		this.si = si;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)
	
	//	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		/*----------------------------
		 * Steps
		 * 0. Vibrate
		 * 1. Get table name
		 * 2. Call method
			----------------------------*/
		/*----------------------------
		 * 0. Vibrate
			----------------------------*/
		vib.vibrate(40);
		
		/*********************************
		 * Called from: Methods_dlg.dlg_db_activity()
		 *********************************/
		CONS.ListViewTags tag = (CONS.ListViewTags) parent.getTag();
		
		// Log
		Log.d("[" + "DOICL_2.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "tag => " + tag);
		
		if (tag == null) {
			
			// debug
			Toast.makeText(actv, "tag => null", Toast.LENGTH_SHORT).show();
			
		} else {//if (tag == null)
			
			switch (tag) {
			
			case tab1_long_click:
				
				case_tab1_long_click(parent, position);
			
			default:
				break;
				
			}//switch (tag) {
			
		}//if (tag == null)
		
		
	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)

	private void case_tab1_long_click(AdapterView<?> parent, int position) {
		// TODO Auto-generated method stub
		String choice = (String) parent.getItemAtPosition(position);

		if (choice.equals(actv.getString(
						R.string.dlg_item_list_long_click_edit))) {
			
			Methods_dlg.dlg_tab1_edit_item(actv, si, dlg);
			
		} else if (choice.equals(actv.getString(
						R.string.dlg_item_list_long_click_delete))) {//if (choice.equals(actv.getString(R.string.dlg_item_list_long_click_edit)))
			
			Methods_dlg.dlg_tab1_delete_item(actv, si, dlg);
			
		} else {//if (choice.equals(actv.getString(R.string.dlg_item_list_long_click_edit)))
			
		}//if (choice.equals(actv.getString(R.string.dlg_item_list_long_click_edit)))
		
	}//private void case_tab1_long_click(AdapterView<?> parent, int position) {


}//public class DialogOnItemClickListener implements OnItemClickListener
