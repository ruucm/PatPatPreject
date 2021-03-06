package com.jym.patpat;

import java.io.File;
import java.util.Hashtable;

import com.jym.helper.TextPref;

import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class PatpatWidgetApp extends Application {
	
	
	
	
	private static PatpatWidgetApp self;
	private static Hashtable<Integer, PatpatView> views = new Hashtable<Integer, PatpatView>();
	private static DisplayMetrics metrics;

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		 Log.d("atActivityRemoved","onConfigurationChanged");
		
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		self = this;
		Log.d("addClickIntent", "Application create");
		WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
		metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		UpdateAllWidgets();
		
		
		
		 Log.d("atActivityRemoved","onCreate");
		
		
		
	}

	public void UpdateAllWidgets() {
		Log.d("addClickIntent", "UpdateAllWidgets");
		AppWidgetManager man = AppWidgetManager.getInstance(getApplication());
		views.clear();
		int[] ids = man.getAppWidgetIds(new ComponentName(getApplication(), PatpatWidgetProvider.class));
		for (int x : ids) {
			UpdateWidget(x);
		}
	}

	public void UpdateWidget(int widgetId) {
		Log.d("atActivityRemoved","widgetId : "+widgetId);
		if (!views.containsKey(widgetId)) {
			PatpatView view = new PatpatView(this, widgetId);
			views.put(widgetId, view);
		}
	}

	public void DeleteWidget(int widgetId) {
		if(views.containsKey(widgetId))
		{
			views.remove(widgetId);
		}
	}

	public PatpatView GetView(int widgetId) {
		
		Log.d("CoinBlockWidgetApp", "GetView, widgetId is = "+widgetId);
		
		
		if (!views.containsKey(widgetId)) {
			PatpatView view = new PatpatView(this, widgetId);
			views.put(widgetId, view);
		}
		return views.get(widgetId);
	}

	public static Context getApplication() {
		return self;
	}

	public static DisplayMetrics getMetrics() {
		return metrics;
	}
}