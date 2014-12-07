package com.jym.patpat;

import java.io.File;

import com.jym.helper.TextPref;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbManager;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;


public class PatpatWidgetProvider extends AppWidgetProvider {
	
	// Init pref files at application class
	static String parentPath = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "SsdamSsdam";

	// service Switch for onetime run service
	public static TextPref mPref;
	boolean serviceSwitch;
	File saveDir;

	@Override
    public void onDeleted(Context context, int[] appWidgetIds) {
            super.onDeleted(context, appWidgetIds);
            Log.d("atActivityRemoved","onDeleted");
            for (int x : appWidgetIds) {
                    ((PatpatWidgetApp) context.getApplicationContext()).DeleteWidget(x);
            }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
            super.onUpdate(context, appWidgetManager, appWidgetIds);

            Log.d("atActivityRemoved","onUpdate");
            for (int i=0; i<appWidgetIds.length; i++)
            {
                    ((PatpatWidgetApp) context.getApplicationContext()).UpdateWidget(appWidgetIds[i]);
            }
    }

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		
		Log.d("serviceSwitch", "parentPath : "+parentPath);
		
		// Init pref files at application class
		saveDir = new File(parentPath); // dir : 생성하고자 하는 경로
		Log.d("serviceSwitch","new File");
		if (!saveDir.exists()) {
			Log.d("serviceSwitch","aveDir.exists()");
			saveDir.mkdirs();
		}

		
		Log.d("serviceSwitch","End File");
		try {
			mPref = new TextPref("mnt/sdcard/SsdamSsdam/textpref.pref");

		} catch (Exception e) {
			e.printStackTrace();
			Log.d("serviceSwitch","e : "+e);
		}   
		
		
		mPref.Ready();
		Log.d("serviceSwitch","mPref.Ready();");
		serviceSwitch = mPref.ReadBoolean("serviceSwitch", true);
		mPref.EndReady();

		
		Log.d("serviceSwitch","serviceSwitch : "+serviceSwitch);
		
		
		if(serviceSwitch){
		Intent intent = new Intent("com.jym.service.IntentService_DeviceEvents");
		context.startService(intent);
		
		Toast.makeText(context, "startService", Toast.LENGTH_SHORT).show();
		
		Log.d("atActivityRemoved","startService");
		
		mPref.Ready();
		mPref.WriteBoolean("serviceSwitch", false);
		mPref.CommitWrite();
		}
		
	
//		Intent intent3 = new Intent("com.jym.service.IntentService_TaskTimer");
//		context.startService(intent3);
		
	}

	@Override
    public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            if (intent.getAction().startsWith("com.gueei")) {
                    int id = intent.getIntExtra("widgetId", 0);
                    ((PatpatWidgetApp) context.getApplicationContext()).GetView(id).OnClick();
            }
    }
}