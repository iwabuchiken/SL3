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
		List<Store> list_Stores = DBUtils.find_ALL_Stores__Unposted(actv);

		/******************************
			validate
		 ******************************/
		if (list_Stores == null) {
			
			return -8;
			
		}
		
		////////////////////////////////

		// post each store

		////////////////////////////////
		
		int res;
		
		for (Store store : list_Stores) {

			
			res = Methods.post_Store_to_Remote(actv, store);
			
			////////////////////////////////

			// count

			////////////////////////////////
//			if (res == 1) {
			if (res == CONS.HTTPResponse.status_Created
					|| res == CONS.HTTPResponse.status_OK) {
				
				String msg = String.format(
						Locale.JAPAN,
						"post store => successful: %s (id = %d)", 
						store.getStore_name(),
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
				String log_msg = "Store => posted: " + store.getStore_name();
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

	@SuppressWarnings("unused")
	private int _doInBackground__PurHistory() {

		/*********************************
		 * Build: JSONBody
		 *********************************/
		JSONObject joBody =
				_getJSONBody();
		
		// Log
		Log.d("[" + "Task_PostData.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "joBody => " + joBody.toString());
		
		if (joBody == null) {
			
			return CONS.ReturnValues.BuildJOBodyFailed;
			
		}//if (joBody == null)
		
		/*********************************
		 * Build: HTTP object
		 *********************************/
		String url = CONS.HTTPData.UrlPostSI;
		
	    //url with the post data
		HttpPost httpPost = _getHttpPost(url, joBody);
		
		if (httpPost == null) {
			
			// Log
			Log.d("["
					+ "Task_PostData.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "httpPost => null");
			
			return CONS.ReturnValues.BuildHttpPostFailed;
			
		}
		
		// Log
		Log.d("[" + "Task_PostData.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"httpPost => " + httpPost.toString()
				+ "(" + httpPost.getURI().toString() + ")"
				);
		
	    /***************************************
		 * Post
		 ***************************************/
		int iRes = _PostData(httpPost);
		
		if (iRes != CONS.ReturnValues.OK) {
			
			return iRes;
			
		}

		//default
		if (CONS.TabActv.tab_toBuyItemIds == null) {
			
			return 1
					+ CONS.ReturnValues.MAGINITUDE_ONE;
			
		} else {//if (CONS.TabActv.tab_toBuyItemIds == null)
			
			return CONS.TabActv.tab_toBuyItemIds.size()
					+ CONS.ReturnValues.MAGINITUDE_ONE;
			
		}//if (CONS.TabActv.tab_toBuyItemIds == null)
		
		
	}//private int _doInBackground__PurHistory()

	/*********************************
	 * @return null => Building failed
	 *********************************/
	private JSONObject
	_getJSONBody() {
		
		// Log
		Log.d("[" + "Task_PostData.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Start => get JSON body");
		
		/*********************************
		 * Prep: values
		 *********************************/
		String itemIds = _doInBackground__getJSONBody_PurHistory_BuildItemIds();
//				String itemIds = StringUtils.join(
//				CONS.TabActv.tab_toBuyItemIds.toArray(),
//				CONS.HTTPData.PostItems_SeparatorString);
		
		// Log
		Log.d("[" + "Task_PostData.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "itemIds => " + itemIds);
		
		if (itemIds == null) {
			
			return null;
			
		}
		
		/***************************************
		 * Build: JOBody
		 *
		 ***************************************/
		SI si = Methods_sl.getSI_FromDbId(
						actv,
						CONS.TabActv.tab_toBuyItemIds.get(0));
		
		Object[] values = new Object[]{
				
				itemIds,
				
				Methods.getTimeLabel_V2(Methods.getMillSeconds_now(), 2),
				
				Methods_sl.get_StoreId_from_StoreName(actv, si.getStore())
				
		};
		
		String[] keys = CONS.HTTPData.Keys_PurHistory;
		
		//REF json object: http://stackoverflow.com/questions/8706046/create-json-in-android answered Jan 2 '12 at 22:42
		
		JSONObject joBody = Methods.get_JsonBody_Generic(actv, keys, values);
		
		// Add password parameter
		try {
			
			joBody.put(
					CONS.HTTPData.passwdKey_SL,
					CONS.HTTPData.passwdSL_PurHistory);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			
			// Log
			Log.d("["
					+ "Task_PostData.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					
					"add password param => Failed"
					+ "(" + e.getMessage() + ")");
		
			return null;
		}
		
		
		return joBody;

	}//_doInBackground__getJSONBody_PurHistory()

	/*********************************
	 * @return null => CONS.TabActv.tab_toBuyItemIds == null
	 *********************************/
	private String
	_doInBackground__getJSONBody_PurHistory_BuildItemIds() {
		
		String itemIds = null;
		
		if (CONS.TabActv.tab_toBuyItemIds == null) {
			
			return null;
			
//			itemIds = "40,1 87,1 109,2";
			
		} else {//if (CONS.TabActv.tab_toBuyItemIds == null)
			
			String[] mixedLabel = new String[CONS.TabActv.tab_toBuyItemIds.size()];
				
			for (int i = 0; i < mixedLabel.length; i++) {
				
				mixedLabel[i] =
						String.valueOf(CONS.TabActv.tab_toBuyItemIds.get(i))
						+ ","
						+ String.valueOf(1);
				
			}
			
			itemIds = StringUtils.join(
					mixedLabel,
//					CONS.TabActv.tab_toBuyItemIds.toArray(),
					CONS.HTTPData.PostItems_SeparatorString);
			
		}//if (CONS.TabActv.tab_toBuyItemIds == null)
		
		return itemIds;
		
	}//_doInBackground__getJSONBody_PurHistory_BuildItemIds()
	

	/******************************
		@return
			-3	ClientProtocolException<br>
			-4	execute post => IOException<br>
			-5	execute post => null returned<br>
			-6	execute post => ServerError<br>
			-7	execute post => ClientError<br>
			1	execute post => success<br>
	 ******************************/
	private int
	_PostData(HttpPost httpPost) {
		// TODO Auto-generated method stub
	    DefaultHttpClient dhc = new DefaultHttpClient();
	    
		HttpResponse hr = null;
		

		try {
			
			hr = dhc.execute(httpPost);
			
		} catch (ClientProtocolException e) {
			// Log
			Log.d("Task_PostData.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", e.toString());
			
			return -3;
			
		} catch (IOException e) {
			
			// Log
			Log.d("Task_PostData.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", e.toString());
			
			return -4;
			
		}
		
		if (hr == null) {
		
			// Log
			Log.d("Task_PostData.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "hr => null");
			
			return -5;
			
		}//if (hr == null)
	
		// Log
		Log.d("[" + "Task_PostData.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "hr => " + hr.getStatusLine().getStatusCode());
		
		////////////////////////////////

		// Check: HTTP return code

		////////////////////////////////
		int status = hr.getStatusLine().getStatusCode();
		
		if (status >= CONS.HTTPResponse.ServerError) {
		
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "status=" + status);
		
			return -6;
		//	return CONS.HTTP_Response.CREATED;
			
		} else if (status < CONS.HTTPResponse.ServerError
					&& status >= CONS.HTTPResponse.BadRequest){//if (status == CONS.HTTP_Response.CREATED)
			
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "status=" + status);
		
			return -7;
//			return CONS.ReturnValues.ClientError;
			
		} else {//if (status == CONS.HTTP_Response.CREATED)
			
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "status=" + status);
			
//			return CONS.HTTP_Response.NOT_CREATED;
			
		}//if (status == CONS.HTTP_Response.CREATED)

		return 1;
//		return CONS.ReturnValues.OK;
		
	}//_doInBackground__4_PostData(HttpPost httpPost)

	private HttpPost
	_getHttpPost(String url, JSONObject joBody) {
		// TODO Auto-generated method stub
		StringEntity se;
		
		HttpPost httpPost = new HttpPost(url);
		
		try {
			
			//REF encoging: http://stackoverflow.com/questions/5084462/how-to-send-unicode-characters-in-an-httppost-on-android answered Feb 22 '11 at 22:38
			se = new StringEntity(joBody.toString(), "UTF-8");
			
			// Log
			Log.d("Task_PostData.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "joBody => Set \"UTF-8\"");
			
		} catch (UnsupportedEncodingException e) {
			
			// Log
			Log.d("Task_PostData.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}
		
		httpPost.setEntity(se);
		
		httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");

		return httpPost;
	}//_doInBackground__2_getHttpPost(String url, JSONObject joBody)
	

	@SuppressWarnings("unused")
	private JSONObject
	_doInBackground__1_getJSONBody(SI si) {
		
		/*********************************
		 * Prep: values
		 *********************************/
		/*********************************
		 * store_id
		 *********************************/
		int store_id = 
				Methods_sl.get_StoreId_from_StoreName(actv,
						si.getStore());
		
		if (store_id == CONS.ReturnValues.NoStoreData) {
			
			// Log
			Log.d("["
					+ "Task_PostData.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No store data");
			
			return null;
			
		}
		
		/*********************************
		 * genre_id
		 *********************************/
		int genre_id = 
				Methods_sl.get_GenreId_from_GenreName(actv,
						si.getGenre());
		
		if (genre_id == CONS.ReturnValues.NoGenreData) {
			
			// Log
			Log.d("["
					+ "Task_PostData.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "No genre data");
					+ "]",
					"No genre data => " + si.getName());
			
//			return null;
			
		}
		
		/***************************************
		 * Build: JOBody
		 *
		 ***************************************/
//		ShoppingItem si = si_list.get(0);
		
		Object[] values = new Object[]{
				
				store_id,		si.getName(),
				si.getPrice(),	genre_id,
				si.getYomi(),	si.getId(),
				
				si.getCreated_at(),
				si.getModified_at(),
				si.getPosted_at()
				
		};
//		"item[store_id]",	"item[name]",		1 2
//		"item[price]",		"item[genre_id]",	3 4
//		"item[yomi]",		"item[mobile_id]",	5 6
		
		String[] keys = CONS.HTTPData.siKeys;
		
		//REF json object: http://stackoverflow.com/questions/8706046/create-json-in-android answered Jan 2 '12 at 22:42
//		JSONObject joBody = new JSONObject();
		
		// Log
		Log.d("[" + "Task_PostData.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Start => get JSON body");
		
		JSONObject joBody = Methods_sl.get_json_body_SI(keys, values);
		
		// Add password parameter
		try {
			
//			joBody.put("passwd[sl]", "abc");
//			joBody.put("pass_sl", "abc");
			joBody.put(
					CONS.HTTPData.passwdKey_SL,
					CONS.HTTPData.passwdSL_NewItem);
//			joBody.put("password_sl", "abc");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			
			// Log
			Log.d("["
					+ "Task_PostData.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					
					"add password param => Failed"
					+ "(" + e.getMessage() + ")");
		
			return null;
		}
		
		if (joBody == null) {
			
			// Log
			Log.d("["
					+ "Task_PostData.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "joBody => null");
			
			return null;
			
		}
		
		return joBody;
		
	}//_doInBackground__1_getJSONBody()

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
