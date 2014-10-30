package com.exam.Intro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.PatPat.Intro.UnityPlayerNativeActivity;
import com.exam.R;
import com.exam.helper.TextPref;
import com.exam.tab.coinBlockLoginActivity;

public class UnityIntro extends Activity {
	private static int introResult = 0;
	private static UnityIntro ui;
	
	private UnityPlayerNativeActivity upna;
	private UpdateCheckThread ucThread;
	
	// back key event for exit
		private Handler mHandler;
		private boolean mFlag = false;
	
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
		    if (keyCode == KeyEvent.KEYCODE_BACK) {
		        if(!mFlag) {
		            Toast.makeText(this, "'뒤로'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
		            mFlag = true;
		            mHandler.sendEmptyMessageDelayed(0, 2000);
		            return false;
		        } else {
		            finish();
		        }
		    }
		    return super.onKeyDown(keyCode, event);
		}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		ui = this;
		super.onCreate(savedInstanceState);
		
		
		mHandler = new Handler() {
		    @Override
		    public void handleMessage(Message msg) {
		        if(msg.what == 0) {
		            mFlag = false;
		        }
		    }
		};
		
		try {
			TextPref mPref = new TextPref("mnt/sdcard/SsdamSsdam/textpref.pref");
		//	setContentView(R.layout.invisiblelayout);
			Intent intent = new Intent(this, coinBlockLoginActivity.class);
			startActivity(intent);
		}
		// if file not exists (It means user run the application first time)
		catch (Exception e) {
			// TODO Auto-generated catch block
			Intent intent = new Intent(this, UnityPlayerNativeActivity.class);
			ucThread = new UpdateCheckThread();
			
			startActivity(intent);
			ucThread.start();
		}
	}
	
	public static int GetIntroResult() {
		return introResult;
	}

	private void _CallLoginActivity() {
		upna.finish();
		introResult = 1;
		
		Intent intent = new Intent(this, coinBlockLoginActivity.class);
		startActivity(intent);
		
		setContentView(R.layout.invisiblelayout);
	}
	
	private void _FinishActivity() {
		upna.finish();
		introResult = -1;
		finish();
	}
	
	public static UnityIntro GetInstance() {
		return ui;
	}

	class UpdateCheckThread extends Thread {
		private boolean isPlay = true;

		@Override
		public void run() {
			while(isPlay) {
				try {
					Thread.sleep(1000);
					int r = UnityPlayerNativeActivity.GetbResult();
					upna = UnityPlayerNativeActivity.GetInstance();
					
					if(r > 0){
						Log.v("Unity", "GetResult: True");
						isPlay = false;
						_CallLoginActivity();
					}
					else if(r < 0){
						Log.v("Unity", "GetResult: False");
						isPlay = false;
						_FinishActivity();
					}
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}