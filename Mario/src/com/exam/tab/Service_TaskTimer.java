package com.exam.tab;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.exam.helper.TaskTimer;



public class Service_TaskTimer extends Service {

	
	
	public static TaskTimer taskTimer2;
	/* (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		

		Log.d("Stop_Overlapping","Service_TaskTimer_onCreate");
		
		taskTimer2 = new TaskTimer();
		
		
		Log.d("Stop_Overlapping","taskTimer2 :"+taskTimer2);
		taskTimer2.execute();
		Log.d("Stop_Overlapping","execute");
		
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onStart(android.content.Intent, int)
	 */
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onDestroy()
	 */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}



}