package sl.tasks;

import sl.utils.Methods_sl;
import android.app.Activity;
import android.os.AsyncTask;

public class TaskAudioTrack extends AsyncTask<Integer, Integer, Integer> {

	Activity actv;
	int bgmResourceId;
	
	public TaskAudioTrack(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
	}

	public TaskAudioTrack(Activity actv, int bgmResourceId) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		this.bgmResourceId = bgmResourceId;
	}

	@Override
	protected Integer doInBackground(Integer... bgmResourceIds) {
		
		Methods_sl.playSound(actv, bgmResourceIds[0]);
		
		return null;
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
