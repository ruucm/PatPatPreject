package com.jym.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.jym.helper.TextPref;
import com.jym.patpat.PatpatView;
import com.jym.patpat.PatpatWidgetApp;
import com.jym.patpat.R;
import com.jym.patpat.Activity.Activity_Intro;

public class IntentService_DeviceEvents extends IntentService {

	TextPref mPref;

	NotificationCompat.Builder const_builder;
	private static boolean isBatteryLow = false;

	int level;

	public static TaskTimer taskTimer2;
	private static final String TAG = IntentService_DeviceEvents.class
			.getSimpleName();

	public static final String INPUT_TEXT = "INPUT_TEXT";
	public static final String OUTPUT_TEXT = "OUTPUT_TEXT";

	/**
	 * initiate service in background thread with service name
	 */
	public IntentService_DeviceEvents() {
		super(IntentService_DeviceEvents.class.getSimpleName());
		
		
		
	}

	@Override
	public void onCreate() {
		Log.d("Stop_renotify","onCreate");
		 
		
		Toast.makeText(getApplicationContext(),"onCreate()_registerRestartAlarm(true)", Toast.LENGTH_LONG).show();

		const_builder = new NotificationCompat.Builder(IntentService_DeviceEvents.this);
		registerRestartAlarm(true);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		
		Log.d("immortal_service","onStartCommand_deviceEvent");
		
		// foregrounding Service
		startForeground(1, new Notification());

		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_BATTERY_CHANGED);
		filter.addAction(Intent.ACTION_HEADSET_PLUG);
		registerReceiver(mBRdeviceEvents, filter);
		
		
		
		
		return START_STICKY;
	}

	/* (non-Javadoc)
	 * @see android.app.IntentService#onDestroy()
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		Log.d("Stop_renotify","onDestroy");
		
		Toast.makeText(getApplicationContext(),"onDestroy()_registerRestartAlarm(false)", Toast.LENGTH_LONG).show();

		registerRestartAlarm(false);
		
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		Log.d("Stop_renotify", "onHandleIntent");
		

		

	}

	BroadcastReceiver mBRdeviceEvents = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {

			String action = intent.getAction();
			
			Log.v("Stop_renotify", "context : "+context);
			Log.v("ServiceMonitor", "action : "+action);
			
			if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {

				int plugType = intent.getIntExtra("plugged", 0);
				level = intent.getIntExtra("level", 0);
				int scale = intent.getIntExtra("scale", 100);
				int voltage = intent.getIntExtra("voltage", 0);
				String temper = Integer.toString(intent.getIntExtra(
						"temperature", 0));
				String tech = intent.getStringExtra("technology");
				int health = intent.getIntExtra("health",
						BatteryManager.BATTERY_HEALTH_UNKNOWN);
				String strPlug = null;
				
				if(plugType ==0){
					Toast.makeText(context, "하아 에너지가 빠져나가고 있어.. ",
							Toast.LENGTH_SHORT).show();
				}else{
				Toast.makeText(context, "plugType  " + plugType,
						Toast.LENGTH_SHORT).show();
				}

			} else if (Intent.ACTION_HEADSET_PLUG.equals(action)) {
				int id = PatpatView.mWidgetId;
				

				if (intent.getIntExtra("state", -1) == 1) {
					Toast.makeText(context, "Headset connected",
							Toast.LENGTH_SHORT).show();
					((PatpatWidgetApp) context.getApplicationContext())
							.GetView(id).OnHeadsetConnected();
				}

				else {
					Toast.makeText(context, "Headset disconnected",
							Toast.LENGTH_SHORT).show();
				}
				
			/*	 * AppWidgetManager manager = AppWidgetManager
				 * .getInstance(context); this.onUpdate(context, manager,
				 * manager.getAppWidgetIds(new ComponentName(context,
				 * getClass())));*/
				 
			}

			if (level < 15)
				isBatteryLow = true;
			else
				isBatteryLow = false;

			// String action = intent.getAction();

			Log.d("battersv", "onReceive");

			Log.d("battersv", "handleMessag2e 실행");
			NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

			try {
				manager.cancel(2); // 기존에 constNotification 있으면 삭제
			} catch (Exception e) {
				Log.d("battersv", "Const에서 에러 났다");
			}

			Log.d("battersv", "NotificationManager 실행");

			const_builder.setSmallIcon(R.drawable.fish01);
			const_builder.setTicker("Device Energy-State Changed");
			const_builder.setContentTitle("Device Energy-State");

			// 알림창 진행중 으로 띄우기
			const_builder.setOngoing(true);
			// set progress
			const_builder.setProgress(100, level, false);

			if (level == -1)
				const_builder.setContentText("배터리 잔량을 확인 중입니다.");
			else
				const_builder.setContentText("배터리 잔량: " + level + "%");

			const_builder.setAutoCancel(false); // 알림바에서 자동 삭제

			// 알람 클릭시 MainActivity를 화면에 띄운다
			Intent nintent = new Intent(getApplicationContext(),
					Activity_Intro.class);
			PendingIntent pIntent = PendingIntent.getActivity(
					getApplicationContext(), 0, nintent,
					Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
			const_builder.setContentIntent(pIntent);
			manager.notify(2, const_builder.build());
			
			
			

			// write battery level to pref
			try {
				mPref = new TextPref("mnt/sdcard/SsdamSsdam/textpref.pref");
			} catch (Exception e) {
				e.printStackTrace();
			}

			mPref.Ready();
			mPref.WriteInt("battery_level", level);
			mPref.CommitWrite();

		}
	};

	
	
	public void registerRestartAlarm(boolean isOn){
		Intent intent = new Intent(IntentService_DeviceEvents.this, RestartReceiver.class);
		intent.setAction(RestartReceiver.ACTION_RESTART_SERVICE);
		PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
				
		AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
		if(isOn){
		am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 1000, 10000, sender);
		}else{
		am.cancel(sender);
		}
		}
	
}
