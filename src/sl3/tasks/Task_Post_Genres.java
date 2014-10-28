package sl3.tasks;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import sl3.items.Genre;
import sl3.items.SI;
import sl3.items.Store;
import sl3.utils.CONS;
import sl3.utils.DBUtils;
import sl3.utils.Methods;
import sl3.utils.Methods_dlg;
import sl3.utils.Methods_sl;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class Task_Post_Genres extends AsyncTask<String, Integer, Integer> {

	Activity actv;
//	static Activity actv;
	
	Dialog dlg;
	Dialog dlg1;
	Dialog dlg2;

	public static Vibrator vib;
	
	public static String instanceParam[];
	
	public Task_Post_Genres(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}

	public Task_Post_Genres(Activity actv, Dialog dlg) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		
		this.dlg = dlg;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}

	public Task_Post_Genres
	(Activity actv, Dialog dlg1, Dialog dlg2) {
		// TODO Auto-generated constructor stub
		this.actv	= actv;
		
		vib			=
				(Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
		this.dlg1	= dlg1;
		
		this.dlg2	= dlg2;
		
	}

	/*********************************
	 * doInBackground(String... params)
	 * 
	 * @return
	 * 	-1	=> JsonBody ==> null<br>
	 * 	-2	=> httpPost => null<br>
	 * 	-3 ClientProtocolException<br>
	 * 
	 * 	-4 execute post => IOException<br>
	 * 	-5 execute post => null returned<br>
	 * 	-6 execute post => ServerError<br>
	 * 
	 * 	-7 execute post => ClientError<br>
	 * 	-8 No unposted pur history, or can't build pur history list<br>
	 * 
	 * 	1 execute post => success<br>
	 *********************************/
	@Override
	protected Integer 
	doInBackground(String... params) {
		
		/*********************************
		 * Set: Instance param
		 *********************************/
		Task_Post_Genres.instanceParam = params;

		////////////////////////////////

		// vars

		////////////////////////////////
		int counter = 0;	// count the number of items posted
		
		////////////////////////////////

		// get: PH list

		////////////////////////////////
		List<Genre> list_Genres = DBUtils.find_ALL_Genres__Unposted(actv);

		/******************************
			validate
		 ******************************/
		if (list_Genres == null) {
			
			return -8;
			
		}
		
		////////////////////////////////

		// post each store

		////////////////////////////////
		
		int res;
		
		for (Genre store : list_Genres) {

			
			res = Methods.post_Genre_to_Remote(actv, store);
			
			////////////////////////////////

			// count

			////////////////////////////////
//			if (res == 1) {
			if (res == CONS.HTTPResponse.status_Created
					|| res == CONS.HTTPResponse.status_OK) {
				
				String msg = String.format(
						Locale.JAPAN,
						"post store => successful: %s (id = %d)", 
						store.getGenre_name(),
						store.getDb_Id());
				// Log
				Log.d("["
						+ "Task_PostData.java : "
						+ +Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + " : "
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]", msg);
				
				////////////////////////////////

				// update: store: "posted_at"
				
				// 	=> done in Methods

				////////////////////////////////
				
				////////////////////////////////

				// count

				////////////////////////////////
				counter += 1;
				
				////////////////////////////////

				// log

				////////////////////////////////
				String log_msg = "Genre => posted: " + store.getGenre_name();
				Methods.write_Log(actv, log_msg, Thread.currentThread()
						.getStackTrace()[2].getFileName(), Thread
						.currentThread().getStackTrace()[2].getLineNumber());
				
			}//if (res == CONS.HTTPResponse.status_Created
			
		}//for (PH ph : list_PHs)

		////////////////////////////////

		// return

		////////////////////////////////
		return counter;
		
	}//doInBackground(String... params)

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Integer res) {

		vib.vibrate(150);
		
		// Log
		String msg_Log = "res => " + res.intValue();
		Log.d("Task_Post_History.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// messages

		////////////////////////////////
		if (res.intValue() == -8 && CONS.TabActv.screen_On == true) {
			
			String msg = "Can't get stores list";
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
		}
		
	}//protected void onPostExecute(Integer res)

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		// debug
		Toast.makeText(actv, "Posting data ...", Toast.LENGTH_SHORT).show();
	}

}//public class Task_GetYomi extends AsyncTask<String, Integer, Integer>
