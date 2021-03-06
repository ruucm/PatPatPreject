package com.jym.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			Intent intent2 = new Intent("com.jym.service.IntentService_DeviceEvents");
			context.startService(intent2);
			
			Intent intent3 = new Intent("com.jym.service.IntentService_TaskTimer");
			context.startService(intent3);
			Toast.makeText(context, "startService_by_Boot", Toast.LENGTH_SHORT).show();
			
		}
	}
}