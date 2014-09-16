package sl3.listeners;

import sl3.main.R;
import sl3.utils.Methods;
import sl3.utils.Tags;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

public class ItemSelectedListener implements OnItemSelectedListener {

	Activity actv;
	
	public ItemSelectedListener(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
	}

	public void
	onItemSelected
	(AdapterView<?> parent, View v, int position, long id) {
		
		// Log
		String msg_Log = "onItemSelected";
		Log.d("ItemSelectedListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		Tags.SpinnerTag tag = (Tags.SpinnerTag) parent.getTag();
		
		// Log
		msg_Log = "tag => " + tag.toString();
		Log.d("ItemSelectedListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		Spinner spStore;
		Spinner spGenre;
		String storeName;
		String genreName;
		
		switch(tag) {
		
		case spStrore://----------------------------------------

			// TODO Auto-generated method stub
			storeName = (String) parent.getItemAtPosition(position);

			spGenre = (Spinner) actv.findViewById(R.id.itemlist_tab1_sp_genre);
			
			genreName = (String) spGenre.getSelectedItem();

			/***************************************
			 * Filter
			 ***************************************/
			Methods.filterList3(actv, storeName, genreName);

			break;// case spStrore

		case spGenre://----------------------------------------

			// TODO Auto-generated method stub
			spStore = (Spinner) actv.findViewById(R.id.itemlist_tab1_sp_store_name);
			
			storeName = (String) spStore.getSelectedItem();

			genreName = (String) parent.getItemAtPosition(position);

			/***************************************
			 * Filter
			 ***************************************/
			Methods.filterList3(actv, storeName, genreName);

			break;// case spStrore
		
		default://----------------------------------------
			break;
		
		}//switch(tag)
		
//		// Log
//		Log.d("ItemSelectedListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "tag=" + tag);
		
//		// TODO Auto-generated method stub
//		String storeName = (String) parent.getItemAtPosition(position);
//
//		Spinner spGenre = (Spinner) actv.findViewById(R.id.itemlist_tab1_sp_genre);
//		
//		String genreName = (String) spGenre.getSelectedItem();
//
//		/***************************************
//		 * Filter
//		 ***************************************/
//		Methods.filterList3(actv, storeName, genreName);
		
//		// Log
//		Log.d("ItemSelectedListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "selected=" + selected);
//		
////		Spinner spGenre = (Spinner) actv.findViewById(R.id.itemlist_tab1_sp_genre);
////		
////		String genre = (String) spGenre.getSelectedItem();
//		
//		// Log
//		Log.d("ItemSelectedListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "genre=" + genre);
	}//onItemSelected()

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
