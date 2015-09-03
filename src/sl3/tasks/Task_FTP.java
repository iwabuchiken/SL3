/********************************************
 * Task_FTP
 * 
 * 18/08/2014 10:30:12
 * 
 * <What for>
 * 	1. Ftp image file to remote server
 * 	2. Ftp db file to remote server
 * 
 * <Usage>
 * 	1. The "ftp_Type" parameter in doInBackground()
 * 		=> use CONS.Remote.FtpType
 *********************************************/
package sl3.tasks;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import sl3.main.R;
import sl3.utils.CONS;
import sl3.utils.Methods;
import sl3.utils.Methods_dlg;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

//public class TaskFTP extends AsyncTask<String, Integer, String> {
public class Task_FTP extends AsyncTask<String, Integer, Integer> {

	Activity actv;
	
	boolean delete;
	
	String ftpTag;//=> Use this field for ftp_upload_db_file

	private String ftp_Type;
	
	Vibrator vib;

	private Dialog d1;

	private Dialog d2;

	private Dialog d3;
	
	public Task_FTP(Activity actv) {
		
		this.actv = actv;
		
	}
	
	public Task_FTP
	(Activity actv, Dialog d1, Dialog d2, boolean delete) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		
		this.d1	= d1;
		this.d2	= d2;
		
		this.delete	= delete;
		
	}

	public Task_FTP
	(Activity actv, Dialog d1, Dialog d2, String ftp_Type) {
		
		this.actv	= actv;
		
		this.d1	= d1;
		this.d2	= d2;
		
		this.ftp_Type	= ftp_Type;
		
	}
	

	public Task_FTP
	(Activity actv, 
		Dialog d1, Dialog d2, 
		String ftp_Type, boolean delete) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		
		this.d1	= d1;
		this.d2	= d2;
		
		this.ftp_Type	= ftp_Type;

		this.delete		= delete;
		
	}

	public Task_FTP
	(Activity actv, 
		Dialog d1, Dialog d2, Dialog d3, String ftp_Type) {
		// TODO Auto-generated constructor stub

		this.actv	= actv;
		
		this.d1	= d1;
		this.d2	= d2;
		this.d3	= d3;

		this.ftp_Type	= ftp_Type;
		
	}

	/******************************
		Used when: Uploading DB file
	 ******************************/

	@Override
//	protected String doInBackground(String... ftpTags) {
	protected Integer
	doInBackground(String... ftp_Type) {
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

//		this.ftp_Type = ftp_Type[0];
		
		// Log
		String msg_Log = "starting... doInBackground";
		Log.d("TaskFTP.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		// Log
		msg_Log = "ftp type[0] => " + ftp_Type[0];
		Log.d("Task_FTP.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// dispatch

		////////////////////////////////
		int res = CONS.Remote.initial_IntValue;
		
		if (this.ftp_Type.equals(CONS.Remote.FtpType.IMAGE.toString())) {
			
//			res = Methods.ftp_Image_to_Remote(actv, ti);
			
			////////////////////////////////

			// post data

			////////////////////////////////
//			if (res == CONS.Remote.status_220) {
//				
//				//REF http://wikiwiki.jp/android/?AsyncTask%A4%C7%A5%D0%A5%C3%A5%AF%A5%B0%A5%E9%A5%A6%A5%F3%A5%C9%BD%E8%CD%FD%A4%F2%B9%D4%A4%A6 "進捗ダイアログの更新"
//				this.publishProgress(CONS.Remote.status_220);
//				
//				res = Methods.post_ImageData_to_Remote(actv, ti);
//				
//			}
			
//		} else if (this.ftp_Type.equals(
//						CONS.Remote.FtpType.IMAGE_MULTIPLE.toString())) {
//			
//			// Log
//			msg_Log = "ftp type => IMAGE_MULTIPLE";
//			Log.d("Task_FTP.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			res = Methods.ftp_MulipleImages_to_Remote_V2(actv, delete);
////			res = Methods.ftp_MulipleImages_to_Remote(actv, delete);
			
		} else if (this.ftp_Type.equals(CONS.Remote.FtpType.DB_FILE.toString())) {
//		} else if (ftp_Type[0].equals(CONS.Remote.FtpType.DB_FILE.toString())) {
			
			res = Methods.ftp_Remote_DB(actv);
			
		} else {

			// Log
			msg_Log = "Unknown ftp type => " + this.ftp_Type;
//			msg_Log = "Unknown ftp type => " + ftp_Type[0];
			Log.d("Task_FTP.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
		return res;
//		return 0;

	}//doInBackground(String... ftpTags)

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
		
		
		
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		
		String msg = "Image file => uploaded. Posting info....";
		Methods_dlg.dlg_ShowMessage_Duration(
					actv, msg, 
					CONS.Admin.dflt_MessageDialog_Length);
		
	}
	

	@Override
//	protected void onPostExecute(String result) {
	protected void onPostExecute(Integer res) {
		
		super.onPostExecute(res);
		
		////////////////////////////////

		// dispatch

		////////////////////////////////
		if (ftp_Type.equals(CONS.Remote.FtpType.IMAGE.toString())) {
			
//			_onPostExecute__Upload_Image(res);
			
//		} else if (ftp_Type.equals(CONS.Remote.FtpType.IMAGE_MULTIPLE.toString())) {
//			
//			_onPostExecute__Upload_Image_Multiple(res);
			
		} else if (ftp_Type.equals(CONS.Remote.FtpType.DB_FILE.toString())) {
			
			_onPostExecute__Upload_DB(res);
			
		} else {

//			// Log
//			msg_Log = "Unknown ftp type => " + ftp_Type[0];
//			Log.d("Task_FTP.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
			
		}
		
//		String msg = "Upload result => " + res.intValue();
//		Methods_dlg.dlg_ShowMessage_Duration(
//						actv, msg, CONS.Admin.dflt_MessageDialog_Length);
//		
//		// Log
//		Log.d("Task_FTP.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg);
		
	}//protected void onPostExecute(String result)

	private void 
	_onPostExecute__Upload_DB
	(Integer res) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// log

		////////////////////////////////
		String log_msg = "Upload DB: result => " + res.intValue();
//		String log_msg = "Upload result => " + res.intValue();
		Methods.write_Log(actv, log_msg,
				Thread.currentThread().getStackTrace()[2].getFileName(), Thread
						.currentThread().getStackTrace()[2].getLineNumber());
		
		////////////////////////////////

		// setup

		////////////////////////////////
		int res_i = res.intValue();
		
		String msg = null;
		int colorID = 0;
		
		////////////////////////////////

		// dispatch

		////////////////////////////////
		switch(res_i) {
		
		case 220:
			
			msg = "Upload result => " + res_i
				+ "\n"
				+ "-1	=> SocketException\n"
				+ "-2 => IOException\n"
				+ "-3 => IOException in disconnecting\n"
				+ "-4 => Login failed\n"
				+ "-5 => IOException in logging-in\n"
				  
				+ "-6 => storeFile returned false\n"
				+ "-7 => can't find the source file\n"
				+ "-8 => can't find the source file; can't disconnect FTP client\n"
				+ "-9 => storeFile ---> IOException\n"
				+ "-10 => storeFile ---> IOException; can't disconnect FTP client\n"
				  
				+ "-11 => set file type ---> failed\n"
				+ "-12 => IOException in logging-in; can't disconnect FTP client\n"
				  
				+ "-2 => Log in failed\n"
				+ ">0	=> Reply code"
						;
			colorID = R.color.green4;
			
			////////////////////////////////

			// dismiss

			////////////////////////////////
			if(d3 != null) d3.dismiss();
			if(d2 != null) d2.dismiss();
			if(d1 != null) d1.dismiss();
			
			break;

		case 0:
			
			msg = "Sorry. Uploading DB is not ready yet";
			colorID = R.color.gold2;
			
//			if(d2 != null) d2.dismiss();
			////////////////////////////////
			
			// dismiss
			
			////////////////////////////////
			if(d3 != null) d3.dismiss();
			if(d2 != null) d2.dismiss();
			if(d1 != null) d1.dismiss();

			break;
			
		default:
			
			msg = "Upload result => " + res_i;
			colorID = R.color.red;

			////////////////////////////////
			
			// dismiss
			
			////////////////////////////////
			if(d3 != null) d3.dismiss();
			if(d2 != null) d2.dismiss();
			if(d1 != null) d1.dismiss();

			break;
			
		}
		
		// Log
		String msg_Log = "colorID => " + colorID;
		Log.d("Task_FTP.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		Methods_dlg.dlg_ShowMessage(
						actv, 
						msg,
						colorID);
//		Methods_dlg.dlg_ShowMessage_Duration(
//				actv, 
//				msg,
//				colorID,
//				CONS.Admin.dflt_MessageDialog_Length);
		
		// Log
		Log.d("Task_FTP.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg);

	}//_onPostExecute__Upload_DB

	@Override
	protected void 
	onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		////////////////////////////////

		// setup

		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		////////////////////////////////

		// dispatch

		////////////////////////////////
		if (this.ftp_Type.equals(CONS.Remote.FtpType.IMAGE.toString())) {
//			if (ftp_Type[0].equals(CONS.Remote.FtpType.IMAGE.toString())) {
			
//			msg = "Uploading... " + ti.getFile_name();
//			colorID = R.color.green4;
			
		} else if (this.ftp_Type.equals(CONS.Remote.FtpType.DB_FILE.toString())) {
//		} else if (ftp_Type[0].equals(CONS.Remote.FtpType.DB_FILE.toString())) {
			
			msg = "Uploading db file... ";
			colorID = R.color.green4;
			
		} else {

			// Log
			msg = "Unknown ftp type => " + this.ftp_Type;
			colorID = R.color.red;
			
		}

		Methods_dlg.dlg_ShowMessage_Duration(
						actv, msg, 
						colorID,
						CONS.Admin.dflt_MessageDialog_Length);
		
	}//onPreExecute
	
}
