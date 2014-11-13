package ji.ruucm.android.vertical_view.fragment;

import java.util.Date;

import ji.ruucm.android.vertical_view.Service_BackImage;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;



class AsyncTask02 extends AsyncTask<String, String, String> {


	public static boolean isCanceled = false;
	private static final String RESULT_SUCCESS = "1";
	private static final String RESULT_FAIL = "0";


	@Override
	public void onCancelled() {

	}

	@Override
	protected void onPreExecute() {


		Log.d("AsyncTask02", "onPreExecute");

	}

	@Override
	protected String doInBackground(String... params) {

		while (true) {
			try {
				Thread.sleep(6000); // one second sleep
				// time++; // decrement time

				Log.d("AsyncTask02", "doInBackground");
				publishProgress(); // trigger onProgressUpdate()

			} catch (InterruptedException e) {
				return RESULT_FAIL;
			}
		}

	}

	/**
	 * this method is used by doInBackground because it's called on the main
	 * thread (UI thread), you can directly modify UI
	 */
	@Override
	protected void onProgressUpdate(String... value) {


		if (Frag03.getInstance() != null){
		
		Chat_ExamData data = null;
		// 첫번째 버튼이 눌리면 상대방이 이야기하는 듯한 데이터를 구성한다.
		data = new Chat_ExamData((byte) 0, "머라냐",
				Frag03.m_time_format.format(new Date()), 0);
		
		// 어댑터에 데이터를 추가한다.
       Frag03.m_adapter.add(data);
       // 추가된 영역으로 스크롤을 이동시킨다.
       Frag03.m_list.smoothScrollToPosition(Frag03.m_adapter.getCount() - 1); 
		}
		else{
			Toast.makeText( Service_BackImage.getIntance(), "Frag03.class == null ", Toast.LENGTH_SHORT);
			Log.d("AsyncTask02", "Frag03.class == null");
		}
		}

	/**
	 * this method is executed right AFTER doInBackground() on the main thread
	 * (UI thread)
	 */
	@Override
	protected void onPostExecute(String result) {
	}
}
