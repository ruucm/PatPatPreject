package com.exam.tab;

import com.exam.helper.TaskTimer;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;

public class IntentService_TaskTimer extends IntentService {
	
	public static TaskTimer taskTimer2;
	private static final String TAG =IntentService_TaskTimer.class.getSimpleName();

	public static final String INPUT_TEXT="INPUT_TEXT";
	public static final String OUTPUT_TEXT="OUTPUT_TEXT";
	
	/**
	 *  initiate service in background thread with service name
	 */
	public IntentService_TaskTimer() {
		super(IntentService_TaskTimer.class.getSimpleName());
	}
	
	
	
	/* (non-Javadoc)
	 * @see android.app.IntentService#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}



	/* (non-Javadoc)
	 * @see android.app.IntentService#onStartCommand(android.content.Intent, int, int)
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		taskTimer2 = new TaskTimer();
		
		
		Log.d("IntentService_TaskTimer","taskTimer2 :"+taskTimer2);
//		taskTimer2.execute();
		Log.d("IntentService_TaskTimer","execute");
		
		return START_STICKY;
	}



	@Override
	public void onDestroy() {
		super.onDestroy();
		
		Log.d("persist wake","IntentService_TaskTimer _ onDestroy");
		
		
		Log.d("persist wake","onDestroy _ taskTimer2.time : "+taskTimer2.time);
		registerRestartAlarm();
		
	}
	

	@Override
	protected void onHandleIntent(Intent intent) {
		/*Log.i(TAG,"onHandleIntent()");
		// get inputs from intent
		String data =intent.getStringExtra(INPUT_TEXT);
		Log.d(TAG,data);
		
		 * capitalize data and wait some time(this is represent data processing this is example in your case this 
		 * might be very time consuming process like image uploading/downloading )
		 * 
		 * 
		data=data.toUpperCase();
		SystemClock.sleep(4*1000);
		data =data+" \n processed at : "+DateFormat.format("hh:mm sss",System.currentTimeMillis());
		
		create new intent to broadcast our processed data to our activity
		Intent resultBroadCastIntent =new Intent();
		set action here
		resultBroadCastIntent.setAction(TextCapitalizeResultReceiver.ACTION_TEXT_CAPITALIZED);
		set intent category as default
		resultBroadCastIntent.addCategory(Intent.CATEGORY_DEFAULT);
		
		add data to intent
		resultBroadCastIntent.putExtra(OUTPUT_TEXT, data);
		send broadcast 
		sendBroadcast(resultBroadCastIntent);*/
		
		
Log.d("IntentService_TaskTimer"," new TaskTimer");
		
		
		
	}
	
	 void registerRestartAlarm() {
		  Log.d("persist wake","IntentService_TaskTimer _ registerRestartAlarm");
		    Intent intent = new Intent( IntentService_TaskTimer.this, IntentService_TaskTimer.class );
		    PendingIntent sender = PendingIntent.getService( IntentService_TaskTimer.this, 0, intent, 0 );
		    long firstTime = SystemClock.elapsedRealtime();
		    firstTime += 10*1000; // 10초 후에 알람이벤트 발생
		    AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
		    am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, 10*1000, sender);
		    
		    
		  }

		  void unregisterRestartAlarm() {
		    Intent intent = new Intent(IntentService_TaskTimer.this, IntentService_TaskTimer.class);
		    PendingIntent sender = PendingIntent.getService( IntentService_TaskTimer.this, 0, intent, 0 );
		    AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
		    am.cancel(sender);
		  }

}
