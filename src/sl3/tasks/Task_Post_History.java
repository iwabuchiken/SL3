package sl3.tasks;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import sl3.items.PH;
import sl3.items.SI;
import sl3.items.Store;
import sl3.utils.CONS;
import sl3.utils.DBUtils;
import sl3.utils.Methods;
import sl3.utils.Methods_sl;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class Task_Post_History extends AsyncTask<String, Integer, Integer> {

	static Activity actv;
	
	Dialog dlg;
	Dialog dlg1;
	Dialog dlg2;

	public static Vibrator vib;
	
	public static String instanceParam[];
	
	public Task_Post_History(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}

	public Task_Post_History(Activity actv, Dialog dlg) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		
		this.dlg = dlg;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}

	public Task_Post_History
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
		Task_Post_History.instanceParam = params;

		////////////////////////////////

		// vars

		////////////////////////////////
		int counter = 0;	// count the number of items posted
		
		////////////////////////////////

		// get: PH list

		////////////////////////////////
		List<PH> list_PHs = DBUtils.find_ALL_PHs__Unposted(actv);

		/******************************
			validate
		 ******************************/
		if (list_PHs == null) {
			
			return -8;
		}
		
		////////////////////////////////

		// post each history

		////////////////////////////////
//<<<<<<< HEAD
//		JSONObject joBody = null;
		
		int res;
		
		for (PH ph : list_PHs) {

			
			res = Methods.post_PurHist_to_Remote(actv, ph);
			
//			////////////////////////////////
//			
//			// get: json
//	
//			////////////////////////////////
//			joBody = _getJSONBody(ph);
//			
//			// Log
//			String msg_Log = "joBody => " + joBody.toString();
//			Log.d("Task_Post_History.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//
//			////////////////////////////////
//			
//			// get: http post
//	
//			////////////////////////////////
//			String url = CONS.HTTPData.UrlPostSI;
//			
//		    //url with the post data
//			HttpPost httpPost = _getHttpPost(url, joBody);
//			
//			if (httpPost == null) {
//				
//				String msg = String.format(
//							"httpPost => null: %s, %s", 
//							ph.getStore_name(),
//							ph.getPur_date());
//				// Log
//				Log.d("["
//						+ "Task_PostData.java : "
//						+ +Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + " : "
//						+ Thread.currentThread().getStackTrace()[2].getMethodName()
//						+ "]", msg);
//				
//				continue;
////				return -2;
//				
//			}

//			////////////////////////////////
//			
//			// post
//	
//			////////////////////////////////
//			res = _PostData(httpPost);
			
//=======
//		JSONObject joBody = null;
//		
//		for (PH ph : list_PHs) {
//			
//			////////////////////////////////
//			
//			// get: json
//	
//			////////////////////////////////
//			joBody = _getJSONBody(ph);
//			
//			// Log
//			String msg_Log = "joBody => " + joBody.toString();
//			Log.d("Task_Post_History.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//
//			////////////////////////////////
//			
//			// get: http post
//	
//			////////////////////////////////
//			String url = CONS.HTTPData.UrlPostSI;
//			
//		    //url with the post data
//			HttpPost httpPost = _getHttpPost(url, joBody);
//			
//			if (httpPost == null) {
//				
//				String msg = String.format(
//							"httpPost => null: %s, %s", 
//							ph.getStore_name(),
//							ph.getPur_date());
//				// Log
//				Log.d("["
//						+ "Task_PostData.java : "
//						+ +Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + " : "
//						+ Thread.currentThread().getStackTrace()[2].getMethodName()
//						+ "]", msg);
//				
//				continue;
////				return -2;
//				
//			}
//
//			////////////////////////////////
//			
//			// post
//	
//			////////////////////////////////
//			int res = _PostData(httpPost);
//			
//>>>>>>> D-13_save-pur-hist
			////////////////////////////////

			// count

			////////////////////////////////
//			if (res == 1) {
			if (res == CONS.HTTPResponse.status_Created
					|| res == CONS.HTTPResponse.status_OK) {
				
				String msg = String.format(
						"post => successful: %s, %s", 
						ph.getStore_name(),
						ph.getPur_date());
				// Log
				Log.d("["
						+ "Task_PostData.java : "
						+ +Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + " : "
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]", msg);
				
				////////////////////////////////

				// update: pur history: "posted_at"

				////////////////////////////////
				
				
				////////////////////////////////

				// count

				////////////////////////////////
				counter += 1;
				
			}
			
		}//for (PH ph : list_PHs)

		////////////////////////////////

		// return

		////////////////////////////////
		return counter;
		
//		//test
//		return -1;
		
//		////////////////////////////////
//
//		// get: json
//
//		////////////////////////////////
//		JSONObject joBody = _getJSONBody();
//		
//		/******************************
//			validate
//		 ******************************/
//		if (joBody == null) {
//			
//			return -1;
//<<<<<<< HEAD
//			
//		}//if (joBody == null)
//
//		////////////////////////////////
//
//		// get: http post
//
//		////////////////////////////////
//		String url = CONS.HTTPData.UrlPostSI;
//		
//	    //url with the post data
//		HttpPost httpPost = _getHttpPost(url, joBody);
//		
//		if (httpPost == null) {
//			
//			// Log
//			Log.d("["
//					+ "Task_PostData.java : "
//					+ +Thread.currentThread().getStackTrace()[2]
//							.getLineNumber() + " : "
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "httpPost => null");
//			
//			return -2;
//			
//		}
//
//		////////////////////////////////
//
//		// post
//
//		////////////////////////////////
//		return _PostData(httpPost);
////		int iRes = _doInBackground__4_PostData(httpPost);
//		
////		int result = _doInBackground__PurHistory();
////			
////		return result;
		
	}//doInBackground(String... params)

	/******************************
		@return
			null => JSONException
	 ******************************/
	private JSONObject 
	_getJSONBody
	(PH ph) {
		// TODO Auto-generated method stub

		////////////////////////////////

		// keys, values

		////////////////////////////////
		Store st = DBUtils.find_Store_from_Name(actv, ph.getStore_name());
		
		int st_Id = 0;
		
		if (st != null) {
			
			st_Id = st.getDb_Id();
			
		}		
		
		Object[] values = new Object[]{
				
				ph.getDbId(),
				ph.getCreated_at(),
				ph.getModified_at(),
				
				st_Id,
//				ph.getStoreName(),
				ph.getPur_date(),
				ph.getItems(),
				
				ph.getAmount(),
				
				ph.getMemo(),
				
				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now())
				
		};
		
		String[] keys = CONS.HTTPData.Keys_PurHistory;

		////////////////////////////////

		// build

		////////////////////////////////
		//REF json object: http://stackoverflow.com/questions/8706046/create-json-in-android answered Jan 2 '12 at 22:42
		JSONObject joBody = Methods.get_JsonBody_Generic(actv, keys, values);

		////////////////////////////////

		// add: password

		////////////////////////////////
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
		
	}//_getJSONBody
	

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
	

	/*********************************
	 * 
	 * @return CONS.ReturnValues.OK(1)<br>
	 * 		
	 * 
	 *********************************/
	private int _exec_post(SI si) {
		
		if (si == null) {
			
			// Log
			Log.d("["
					+ "Task_PostData.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "si => null");
			
			return CONS.ReturnValues.ParamVariableNull;
			
		}
		
		
		// Log
		Log.d("[" + "Task_PostData.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Starting _exec_post");
		// Log
		Log.d("[" + "Task_PostData.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"si.name=" + si.getName()
				+ "/"
				+ "si.created_at=" + si.getCreated_at());
		
		/*********************************
		 * Update: posted_at
		 *********************************/
		si.setPosted_at(
				Methods.getTimeLabel_V2(
						Methods.getMillSeconds_now(), 2));
		
		// TODO Auto-generated method stub
		JSONObject joBody =
				_doInBackground__1_getJSONBody(si);
	
		if (joBody == null) {
			
			// Log
			Log.d("["
					+ "Task_PostData.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "joBody => null");
			
			return CONS.ReturnValues.BuildJOBodyFailed;
			
		}
		
		// Log
		Log.d("[" + "Task_PostData.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"joBody => " + "[" + joBody.toString() + "]");
		
	//	
		//REF post json: http://stackoverflow.com/questions/6218143/android-post-json-using-http answered Jun 2 '11 at 18:16
		
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
		
		/*********************************
		 * Update: SI.posted_at
		 *********************************/
		boolean res = Methods_sl.update_SI(actv, si);
		
		if (res == false) {
			
			return CONS.ReturnValues.PostedButNotUpdated;
			
		}
		
		return CONS.ReturnValues.OK;
//		return CONS.ReturnValues.NOP;

	}//private int _exec_post()

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

	private int 
	_doInBackground__3_CheckHTTPCodes(HttpResponse hr) {
		// TODO Auto-generated method stub
		int status = hr.getStatusLine().getStatusCode();
		
		if (status >= CONS.HTTPResponse.ServerError) {
		
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "status=" + status);
		
			return CONS.ReturnValues.ServerError;
		//	return CONS.HTTP_Response.CREATED;
			
		} else if (status < CONS.HTTPResponse.ServerError
					&& status >= CONS.HTTPResponse.BadRequest){//if (status == CONS.HTTP_Response.CREATED)
			
			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "status=" + status);
		
			return CONS.ReturnValues.ClientError;
			
		} else {//if (status == CONS.HTTP_Response.CREATED)
			
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "status=" + status);
			
//			return CONS.HTTP_Response.NOT_CREATED;
			
		}//if (status == CONS.HTTP_Response.CREATED)

		return CONS.ReturnValues.OK;
		
	}//_doInBackground__3_CheckHTTPCodes(HttpResponse hr)

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
		
		String message;
		
		// Log
		String msg_Log = "res => " + res.intValue();
		Log.d("Task_Post_History.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
//		if (res.intValue() == CONS.ReturnValues.FAILED) {
//			
//			message = "Posting => Failed";
//			
//		} else if (res.intValue() == 
//						CONS.ReturnValues.PostedButNotUpdated) {//if (res.intValue() == CONS.ReturnValues.FAILED)
//			
//			message = "Posted but device data => not updated";
//			
//		} else {//if (res.intValue() == CONS.ReturnValues.FAILED)
//			
//			message = "Posting => Done(Unknown result => " 
//					+ String.valueOf(res.intValue()) + ")";
//			
//		}//if (res.intValue() == CONS.ReturnValues.FAILED)
		
//		/*********************************
//		 * Dismiss: Dialogues
//		 *********************************/
//		if (dlg1 != null) {
//			
//			dlg1.dismiss();
//		}
//		
//		if (dlg2 != null) {
//			
//			dlg2.dismiss();
//		}
		
	}//protected void onPostExecute(Integer res)

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		// debug
		Toast.makeText(actv, "Posting data ...", Toast.LENGTH_SHORT).show();
	}

}//public class Task_GetYomi extends AsyncTask<String, Integer, Integer>
