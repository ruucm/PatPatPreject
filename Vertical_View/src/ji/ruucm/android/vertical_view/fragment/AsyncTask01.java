package ji.ruucm.android.vertical_view.fragment;

import ji.ruucm.android.chat.R;
import android.os.AsyncTask;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

/**
 * AsyncTask<A, B, C> - A : parameters' type of doInBackground - B : parameters'
 * type of onProgressUpdate - C : parameters' type of onPostExecute
 */
public class AsyncTask01 extends AsyncTask<String, String, String> {

	public static boolean isCanceled = false;

	private static final String RESULT_SUCCESS = "1";
	private static final String RESULT_FAIL = "0";

	// for background anim
	RelativeLayout frag01;
	AlphaAnimation fadeInAnimation, fadeOutAnimation;

	int animsequence = 0;

	@Override
	public void onCancelled() {

	}

	@Override
	protected void onPreExecute() {

		// anim setting

		fadeInAnimation = new AlphaAnimation((float) 0.5, 1);
		fadeInAnimation.setDuration(1500);
//		fadeInAnimation.setFillAfter(true);
		
		fadeOutAnimation = new AlphaAnimation(1, (float) 0.5);
		fadeOutAnimation.setDuration(1500);
//		fadeOutAnimation.setFillAfter(true);
		

		Log.d("AsyncTask01", "onPreExecute");

	}

	@Override
	protected String doInBackground(String... params) {

		while (true) {
			try {
				Thread.sleep(6000); // one second sleep
				// time++; // decrement time

				Log.d("AsyncTask01", "doInBackground, doInBackground : ");

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

		if (Frag01.getInstance() != null) {


			int chance = animsequence % 4; 
			
			
			frag01 = (RelativeLayout) Frag01.v
					.findViewById(R.id.background_home01);
			if (Frag01.quickAction != null){
			Frag01.quickAction.dismiss();
			}
			

			if (chance == 0) {
				frag01.startAnimation(fadeOutAnimation);
				frag01.startAnimation(fadeInAnimation);
				frag01.setBackgroundResource(R.drawable.background_home02);
			} else if (chance == 1) {
				frag01.startAnimation(fadeOutAnimation);
				frag01.startAnimation(fadeInAnimation);
				frag01.setBackgroundResource(R.drawable.background_home03);
			} else if (chance == 2) {
				frag01.startAnimation(fadeOutAnimation);
				frag01.startAnimation(fadeInAnimation);
				frag01.setBackgroundResource(R.drawable.background_home04);
			} else {
				frag01.startAnimation(fadeOutAnimation);
				frag01.startAnimation(fadeInAnimation);
				frag01.setBackgroundResource(R.drawable.background_home01);

			}

			animsequence++;
		} else {
			Log.d("AsyncTask01", "Frag01.class == null");
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