package com.exam;



import java.io.*;
import java.net.*;

import android.annotation.*;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

import com.exam.view.*;
import com.facebook.widget.*;


public class coinBlockIntroActivity extends Activity implements OnClickListener
{
	/** Called when the activity is first created. */
	//액티비티간 통신을 위한
	final static int SETTING = 0;
	static int bLevel; 

	TextView welcome; 
	TextView InitStateText; 
	TextView Lv0StateText; 

	//프레퍼런스 
	public static TextPref mPref;
	public static TextPref fbPref;

	public static boolean lv0_1 = true;
	public static boolean lv1 = true;
	public static boolean lv2 = true;
	static boolean init = true;
	static boolean lv0_2 = true;

	public static TextView time;
	static int checkHandler = 0;
	static long count = 0;
	public static long second = 0;

	long tasktime ;

	//Async Task
	private AsyncTask<Void, Integer, Void> mTask;
	private Button mButton;
	private long time1;

	//Async Task
	private static coinBlockIntroActivity instance;
	public static  TaskTimer taskTimer1 = new TaskTimer();
	//private Get getAsyncTask;

	back task;
	Bitmap bmImg;
	ImageView Profile;
	String userId ;

	private ProfilePictureView profilePic;

	public static coinBlockIntroActivity getInstance() {
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.d("coinBlockIntroActivity","onCreate.");

		instance = this;
		Log.d("coinBlockIntroActivity", "instance"+instance);

		//프레퍼런스 읽어오기   
		File saveDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "SsdamSsdam"); // dir : 생성하고자 하는 경로

		Log.d("coinBlockIntroActivity", "saveDir(saveDir)fbPref.Ready();");

		if(!saveDir.exists()) 
			saveDir.mkdirs();

		try {
			mPref = new TextPref("mnt/sdcard/SsdamSsdam/textpref.pref");
			Log.d("coinBlockIntroActivity", "TextPref(intent)fbPref.Ready();");
			fbPref = new TextPref("mnt/sdcard/SsdamSsdam/facebookprofile.txt");
			Log.d("coinBlockIntroActivity", "fbPref(intent)fbPref.Ready();");
		} catch (Exception e) { 
			e.printStackTrace();
		}       
		mPref.Ready();
		Log.d("coinBlockIntroActivity", "mPref.Ready();(intent)fbPref.Ready();");
		fbPref.Ready();
		Log.d("coinBlockIntroActivity", "fbPref.Ready();(intent)fbPref.Ready();");

		//set Main Background Image & Text
		userId = fbPref.ReadString("userId", "");
		String userFirstName = fbPref.ReadString("userFirstName", "");
		String userLastName = fbPref.ReadString("userLastName", "");

		init = mPref.ReadBoolean("initstate", false);	
		lv0_1 = mPref.ReadBoolean("lv0_1state", false);
		lv0_2 = mPref.ReadBoolean("lv0_2state", false);
		lv1 = mPref.ReadBoolean("lv1state", false);
		lv2 = mPref.ReadBoolean("lv2state", false);

		Log.d("coinBlockIntroActivity", "fbPref.Ready();(intent)fbPref.Ready();");
		setContentView(R.layout.main); 

		welcome = (TextView)findViewById(R.id.welcome);		
		welcome.setText(userFirstName+" "+userLastName+" 님 환영합니다 위젯을 시작하려면 Set-up 버튼을 누르세요");

		UpdateIntroView();

		profilePic = (ProfilePictureView)findViewById(R.id.profilepic);
		Log.d("coinBlockIntroActivity", "profilePic = (Pr;"+userId);

		//	profilePic.setCropped(true);
		profilePic.setPresetSize(ProfilePictureView.LARGE);
		profilePic.setProfileId(userId);

		Log.d("coinBlockIntroActivity", "profilePic.setProfileId(userPref.Ready();");

		fbPref.EndReady();
		Log.d("coinBlockIntroActivity","EndReady");
	} 

	public void onClick(View v)
	{
		if(mButton.getText().equals("start"))
		{
			Log.v("tag7", "equals(start");

			mTask = new AsyncTask<Void, Integer, Void>() {
				private boolean isCanceled = false;

				@Override
				protected void onPreExecute()
				{
					isCanceled = false;
				}

				@Override 
				protected Void doInBackground(Void... params)
				{
					Log.v("tag7", "doInBackground");

					for(int i = 1 ; i <= 50 && !isCanceled ; i++)
					{
						Log.v("tag7", "for"+i);

						try
						{
							publishProgress(i);
							Thread.sleep(100);
						}
						catch(InterruptedException e)
						{
							e.printStackTrace();
						}
					}
					return null;
				}

				@Override
				protected void onProgressUpdate(Integer... progress)
				{
					//mProgress.setProgress(progress[0]);
					count++;
					second = getSecond(count);
					time.setText( second + "초 " + count % 10);
				}

				@Override
				protected void onPostExecute(Void result)
				{
					//Toast.makeText(coinBlockIntroActivity.this, "onPostExecute", Toast.LENGTH_SHORT).show();
					//mButton.setText("start");
				}

				@Override
				protected void onCancelled()
				{
					Log.v("tag7", "onCancelled");
					isCanceled = true;
					//publishProgress(0);
					//Toast.makeText(coinBlockIntroActivity.this, "onCancelled", Toast.LENGTH_SHORT).show();
				}
			};

			mTask.execute();
			mButton.setText("cancel");
		}
		else if(mButton.getText().equals("cancel"))
		{
			Log.v("tag7", "equals(cancel");
			mTask.cancel(false);
			mButton.setText("start");
		}
	}

	public void mOnClick(View v)
	{
		switch(v.getId())
		{
		case R.id.setbutton:    		
			Intent intent = new Intent(this, Setting.class);
			startActivityForResult(intent, SETTING ); 		
			break;

		case R.id.btn_start:
			if(taskTimer1.isCanceled == false){
				TaskTimer taskTimer1 = new TaskTimer();
				taskTimer1.setTextView1(R.id.time0);
				taskTimer1.execute("");
			}
			else
				taskTimer1.isCanceled = false;
			
			break;

		case R.id.btn_pause:
			tasktime = taskTimer1.GetTime();
			Log.v("tag9", "tasktiem" +Long.toString(tasktime));
			taskTimer1.isCanceled = true;
			break;
			
		case R.id.btn_stop:
			if(taskTimer1.isCanceled == false){
				TaskTimer taskTimer1 = new TaskTimer();
				taskTimer1.setTextView1(R.id.time0);
				taskTimer1.setTime(0);
				taskTimer1.timer.setText("0");
			}
			else
				taskTimer1.isCanceled = false;

			break;

		case R.id.reset1:
			break;
		}
	}

	public static long getSecond(long milli){
		long secondValue = 0;
		secondValue = milli / 10;
		return secondValue;
	}

	@SuppressLint("NewApi")
	protected void onActivityResult (int requestCode, int resultCode, Intent data) {
		switch (requestCode){
		case SETTING:
			Log.d("tag02","onActivityResult");
		}
	}


	//AsyncTask for Http request
	private class back extends AsyncTask<String, Integer,Bitmap> {
		@Override
		protected Bitmap doInBackground(String... urls) {
			// TODO Auto-generated method stub
			try{
				URL myFileUrl = new URL(urls[0]);
				HttpURLConnection conn = (HttpURLConnection)myFileUrl.openConnection();
				conn.setDoInput(true);
				conn.connect();
				Log.d("tag1-1","connect"+urls[0]);
				bmImg = BitmapFactory.decodeStream(myFileUrl.openConnection().getInputStream());
				Log.d("tag1-1","BitmapFactory"+bmImg);

			}catch(IOException e){
				e.printStackTrace();
			}
			return bmImg;
		}

		protected void onPostExecute(Bitmap img){
			Profile.setImageBitmap(bmImg);
			Log.d("tag1-1","setImageBitmap"+bmImg);
		}
	}

	public static void UpdateIntroView() {
		try {
			mPref = new TextPref("mnt/sdcard/SsdamSsdam/textpref.pref");
		} catch (Exception e) { 
			e.printStackTrace();
		}       
		mPref.Ready();

		init = mPref.ReadBoolean("initstate", false);	
		lv0_1 = mPref.ReadBoolean("lv0_1state", false);
		lv0_2 = mPref.ReadBoolean("lv0_2state", false);
		lv1 = mPref.ReadBoolean("lv1state", false);
		lv2 = mPref.ReadBoolean("lv2state", false);

		mPref.EndReady();

		if (init) {
			updateview(R.drawable.background,"init 임 ㅇㅇ", true);
		}
		else if (lv0_1) {
			updateview(R.drawable.background0,"lv0-1 임 ㅇㅇ", true);
		} else if (lv0_2) {
			updateview(R.drawable.background0,"lv0-2 임 ㅇㅇ", false);
		} else if (lv1) {
			updateview(R.drawable.background1, "레벨1s냐 아직도 ㅋㅋㅋㅋㅋㅋㅋㅄ",true);
		} else if (lv2) {
			updateview(R.drawable.background2, "레벨2s냐 아직도 ㅋㅋㅋㅋㅋㅋㅋㅄ",true);    	    				
		}
		Log.d("coinBlockIntroActivity","else if (lv2)  	 mP");
	}

	public static void updateview (int drawbleid, String txt, boolean notstopped){
		Log.d("coinBlockIntroActivity","updateview"+drawbleid+txt);

		//set newstate's background img
		LinearLayout a = (LinearLayout)instance.findViewById(R.id.mainlinear);			
		a.setBackgroundResource(drawbleid);

		//set new state's text
		TextView statetxt = (TextView)instance.findViewById(R.id.welcome);		
		statetxt.setText(txt);

		if(notstopped) {
			TaskTimer taskTimer1 = new TaskTimer();
			taskTimer1.setTextView1(R.id.time0);
			taskTimer1.execute("");
		}
	}
}