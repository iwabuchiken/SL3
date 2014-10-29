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

public class Task_Post_Sis extends AsyncTask<String, Integer, Integer> {

	Activity actv;
//	static Activity actv;
	
	Dialog dlg;
	Dialog dlg1;
	Dialog dlg2;

	public static Vibrator vib;
	
	public static String instanceParam[];
	
	public Task_Post_Sis(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
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
		Task_Post_Sis.instanceParam = params;

		////////////////////////////////

		// vars

		////////////////////////////////
		int counter = 0;	// count the number of items posted
		
		////////////////////////////////

		// get: PH list

		////////////////////////////////
		List<SI> list_Sis = DBUtils.find_ALL_SIs__Unposted(actv);

		/******************************
			validate
		 ******************************/
		if (list_Sis == null) {
			
			return -8;
			
		}
		
		////////////////////////////////

		// post each si

		////////////////////////////////
		
		int res;
		
		//debug
		int limit = 20;	//=> limit the number of uploads in one cycle
		
		for (SI si : list_Sis) {

			
			res = Methods.post_SI_to_Remote(actv, si);
			
			////////////////////////////////

			// count

			////////////////////////////////
//			if (res == 1) {
			if (res == CONS.HTTPResponse.status_Created
					|| res == CONS.HTTPResponse.status_OK) {
				
				String msg = String.format(
						Locale.JAPAN,
						"post si => successful: %s (id = %d)", 
						si.getName(),
						si.getId());
				// Log
				Log.d("["
						+ "Task_PostData.java : "
						+ +Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + " : "
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]", msg);
				
				////////////////////////////////

				// update: si: "posted_at"
				
				// 	=> done in Methods

				////////////////////////////////
				
				////////////////////////////////

				// count

				////////////////////////////////
				counter += 1;
				
				////////////////////////////////

				// log

				////////////////////////////////
				String log_msg = String.format(
							Locale.JAPAN,
							"Si => posted: %s (id = %d)", 
							si.getName(), si.getId());
						 
				Methods.write_Log(actv, log_msg, Thread.currentThread()
						.getStackTrace()[2].getFileName(), Thread
						.currentThread().getStackTrace()[2].getLineNumber());
				
				////////////////////////////////

				// limit number

				////////////////////////////////
				if (counter > limit) {
					
					// Log
					String msg_Log = "Limit reached => quitting";
					Log.d("Task_Post_Sis.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
					break;
					
				}
				
			}//if (res == CONS.HTTPResponse.status_Created
			
		}//for (SI si : list_Sis)

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
		String msg_Log = "res => " + res.intValue()
						+ " items";
		
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
