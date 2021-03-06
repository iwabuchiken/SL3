package sl3.adapters;

import java.util.List;
import java.util.Locale;

import sl.main.ItemListActv;
import sl3.items.PS;
import sl3.items.SI;
import sl3.main.R;
import sl3.main.R.id;
import sl3.utils.Methods;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PSListAdapter extends ArrayAdapter<PS> {

	//
	private int resourceId; 
	
	public PSListAdapter(Context context, int textViewResourceId,
												List<PS> list) {
		super(context, textViewResourceId, list);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
		
		this.resourceId = textViewResourceId;
		
		
	}//public ItemListAdapter()

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/*----------------------------
		 * Steps
		 * 1. Inflate
		 * 2. Get views
		 * 3. Get item
		 * 4. Set values
		 * 
		 * 5. Set background
			----------------------------*/
		
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resourceId, null);
        }

        //
        PS ps = (PS) getItem(position);
		
        /***************************************
		 * 4. Set values
		 ***************************************/
        TextView tvStoreName =
        		(TextView) convertView.findViewById(R.id.listrow_load_tobuy_list_tv_store_name);
        
        TextView tvDueDate =
        		(TextView) convertView.findViewById(R.id.listrow_load_tobuy_list_tv_due_date);

        tvStoreName.setText(ps.getStoreName());
        
//        tvDueDate.setText(String.valueOf(ps.getDueDate()));
        String[] YMD = Methods.conv_TimeLabel_to_YMD(ps.getDueDate());
        
        String due_Date = String.format(
        					Locale.JAPAN,
        					"%s/%s/%s", 
        					YMD[0], YMD[1], YMD[2]);
        
        tvDueDate.setText(due_Date);
//        tvDueDate.setText(ps.getDueDate());
//        tvDueDate.setText(Methods.getTimeLabel_Japanese(ps.getDueDate()));
        
//        // Log
//		Log.d("PSListAdapter.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "duedate=" + ps.getDueDate());
        
		return convertView;
//		return super.getView(position, convertView, parent);
	}//public View getView(int position, View convertView, ViewGroup parent)

}//public class ItemListAdapter extends ArrayAdapter<PS>
