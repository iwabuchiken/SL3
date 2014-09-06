package sl.adapters;

import java.util.List;

import sl.items.ShoppingItem;
import sl.main.ItemListActv;
import sl3.main.R;
import sl3.main.R.id;
import sl.utils.CONS;
import sl.utils.Methods;
import sl.utils.Methods_sl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ToBuyListAdapter extends ArrayAdapter<ShoppingItem> {

	//
	private int resourceId; 

	TextView tvItemName;

	Context con;
	
	public ToBuyListAdapter(Context context, int textViewResourceId,
												List<ShoppingItem> list) {
		super(context, textViewResourceId, list);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
		
		this.resourceId = textViewResourceId;
		
		this.con = context;
		
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

        ShoppingItem si = (ShoppingItem) getItem(position);

        getView__1_setupTextView(convertView, si);

//		/*----------------------------
//		 * 5. Set background
//			----------------------------*/
        
        getView__2_setupBackground(convertView, si, position);
        
//		if (ItemListActv.toBuys.contains((Integer) position)) {
//			
//			convertView.setBackgroundColor(Color.GREEN);
//			
//		} else if (ItemListActv.checkedPositions.contains((Integer) position)) {
//			
//			convertView.setBackgroundColor(Color.BLUE);
//			
//		} else {//if (ItemList.checkedPositions.contains((Integer) position))
//			
//			convertView.setBackgroundColor(Color.BLACK);
//			
//		}//if (ItemList.checkedPositions.contains((Integer) position))
		
		
		
		
		return convertView;
//		return super.getView(position, convertView, parent);
	}//public View getView(int position, View convertView, ViewGroup parent)

	private void
	getView__2_setupBackground(View convertView, ShoppingItem si, int position) {

		// Log
		Log.d("ToBuyListAdapter.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "postion=" + position);
		
//		if (CONS.tab_toBuyItemIds.contains(new Integer(position))) {
		if (CONS.TabActv.tab_boughtItemIds.contains(Integer.valueOf(si.getId()))) {

			// Log
			Log.d("ToBuyListAdapter.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Contain");

	//			convertView.setBackgroundColor(Color.BLUE);
			tvItemName.setBackgroundColor(Color.BLUE);
	//		tvItemName.setTextColor(Color.BLACK);
			tvItemName.setTextColor(Color.WHITE);
	
			//debug
			tvItemName.setWidth(Methods.getDisplaySize((Activity)con)[0]);
			
			/***************************************
			 * LinearLayout for item name
			 ***************************************/
			LinearLayout llName =
					(LinearLayout) convertView.findViewById(R.id.adapteritem_LL_item_name);
			
			llName.setBackgroundColor(Color.BLUE);
			
		} else {//if (CONS.tab_boughtItemIds.contains(new Integer(position)))
			
			// Log
			Log.d("ToBuyListAdapter.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Doen't contain");
			
		//		convertView.setBackgroundColor(Color.BLUE);
			tvItemName.setBackgroundColor(Color.WHITE);
		//	tvItemName.setTextColor(Color.BLACK);
			tvItemName.setTextColor(((Activity)con).getResources().getColor(R.color.gray1));
		
			//debug
			tvItemName.setWidth(Methods.getDisplaySize((Activity)con)[0]);
			
			/***************************************
			 * LinearLayout for item name
			 ***************************************/
			LinearLayout llName =
					(LinearLayout) convertView.findViewById(R.id.adapteritem_LL_item_name);
			
			llName.setBackgroundColor(Color.WHITE);
			
		}//if (CONS.tab_boughtItemIds.contains(new Integer(position)))
		
////			convertView.setBackgroundColor(Color.BLUE);
//		tvItemName.setBackgroundColor(Color.WHITE);
////		tvItemName.setTextColor(Color.BLACK);
//		tvItemName.setTextColor(((Activity)con).getResources().getColor(R.color.gray1));
//
//		//debug
//		tvItemName.setWidth(Methods.getDisplaySize((Activity)con)[0]);
//		
//		/***************************************
//		 * LinearLayout for item name
//		 ***************************************/
//		LinearLayout llName =
//				(LinearLayout) convertView.findViewById(R.id.adapteritem_LL_item_name);
//		
//		llName.setBackgroundColor(Color.WHITE);
		
		

	}//private void getView__2_setupBackground(View convertView, int position)

	private void getView__1_setupTextView(View convertView, ShoppingItem si) {
		// TODO Auto-generated method stub

        tvItemName = 
        				(TextView) convertView.findViewById(R.id.adapteritem_tv_item_name);
        TextView tv_store = 
				(TextView) convertView.findViewById(R.id.adapteritem_tv_store);
        TextView tv_price = 
				(TextView) convertView.findViewById(R.id.adapteritem_tv_price);
        TextView tv_genre = 
				(TextView) convertView.findViewById(R.id.adapteritem_tv_genre);

		/*----------------------------
		 * 4. Set values
			----------------------------*/
		//
		tvItemName.setText(si.getName());
		tv_store.setText(si.getStore());
//		tv_price.setText(si.getPrice());
		tv_price.setText(String.valueOf(si.getPrice()));
		
		tv_genre.setText("(" + si.getGenre() + ")");

	}//private void getView__1_setupTextView(View convertView)

}//public class ItemListAdapter extends ArrayAdapter<ShoppingItem>
