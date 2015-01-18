package com.jym.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.jym.patpat.PatpatView;

public class RestartReceiver extends BroadcastReceiver{

	static public final String ACTION_RESTART_SERVICE = "RestartReceiver.restart";    // 값은 맘대로
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		Toast.makeText(context, intent.getAction(), Toast.LENGTH_LONG).show();
		if(intent.getAction().equals(ACTION_RESTART_SERVICE)){
			Intent i = new Intent(context, IntentService_DeviceEvents.class);
			context.startService(i);
		}		
	}	
}