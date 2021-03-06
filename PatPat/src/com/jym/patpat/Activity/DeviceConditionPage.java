package com.jym.patpat.Activity;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jym.helper.TextPref;
import com.jym.patpat.R;
import com.todddavies.components.progressbar.ProgressWheel;

public class DeviceConditionPage extends Fragment  {

	
	
	//Progress Wheel
	boolean running;
	ProgressWheel pw_battr;
	int progress = 0;
	int bLevel ;
	
	
	
	
	// 프레퍼런스
	static TextPref ePref;

	static boolean init;
	public static boolean lv0_1;
	static boolean lv0_2;
	public static boolean lv1;
	public static boolean lv2;
	public static boolean lv3_1;

	static View v;
	
	View rv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		
		
		v = inflater.inflate(R.layout.device_condition, container, false);
		rv = inflater.inflate(R.layout.patpat_widget, container, false);
		
		
		
		
		//set battr percentage to progressbar
		pw_battr = (ProgressWheel) v.findViewById(R.id.progressBar_battr);
		  
		
		try {
			ePref = new TextPref("mnt/sdcard/SsdamSsdam/entitypref.pref");
		} catch (Exception e) { 
			e.printStackTrace();
		}   
		
		ePref.Ready();
		bLevel = ePref.ReadInt("battery_level", 0);
		ePref.EndReady();

//		float a = (((float) bLevel / 360) * 100 );
		 
		final Runnable r = new Runnable() {
			public void run() {
				running = true;
				while(progress<(((float) bLevel / 100) * 360 )) {
					pw_battr.incrementProgress();
					progress++;
					try {
						Thread.sleep(15); 
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				running = false;
			}
        };
		
		
		if(!running) {
			progress = 0;
			pw_battr.resetCount();
			Thread s = new Thread(r);
			s.start();
		}
		
		
		
		
		
        //setListener
		Button button = (Button) v.findViewById(R.id.reset1);
		Button button2 = (Button) v.findViewById(R.id.btn_battle);
		
		
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT)
						.show();
			}
		});

		button.setOnLongClickListener(new View.OnLongClickListener() {
			public boolean onLongClick(View v) {
				
				
			/*	
				PatpatWidgetApp a = new PatpatWidgetApp();
				a.UpdateAllWidgets();
				*/
				
				
//				Lv3_1State.recycleAnimDrawable();
				
				 dalvik.system.VMRuntime.getRuntime().runFinalizationSync();

			/*	
				ImageView a = (ImageView) rv.findViewById(R.id.patview01);
				
				Drawable drawable = a.getDrawable();
				
				Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
		        Canvas canvas = new Canvas(bitmap);
		        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		        drawable.draw(canvas);
		        
		        bitmap.recycle();
				*/
				
				Toast.makeText(getActivity(), "Recycling...",
						Toast.LENGTH_SHORT).show();
				return true;
			}
		});
	   
         
		
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "Entering Battle zone..", Toast.LENGTH_LONG)
						.show();
			/*	
				Intent aintent = new Intent(getActivity(), BluetoothChat.class);
				startActivity(aintent);
				*/
				
			}
		});

		

		
		
		UpdateIntroView();

		return v;
	}

	public static void UpdateIntroView() {

		try {
			ePref = new TextPref("mnt/sdcard/SsdamSsdam/entitypref.pref");

		} catch (Exception e) {
			e.printStackTrace();
		}
		ePref.Ready();

		init = ePref.ReadBoolean("initstate", false);

		lv0_1 = ePref.ReadBoolean("lv0_1state", false);
		lv0_2 = ePref.ReadBoolean("lv0_2state", false);
		lv1 = ePref.ReadBoolean("lv1state", false);
		lv2 = ePref.ReadBoolean("lv2state", false);
		lv3_1 = ePref.ReadBoolean("lv3_1state", false);

		ePref.EndReady();

		Log.d("viewPager01", "UpdateIntroView" + init + lv0_1 + lv0_2 + lv1
				+ lv2 + lv3_1);

		if (init) {

			updateview(R.drawable.background, "init 임 ㅇㅇ", false);

		} else if (lv0_1) {

			updateview(R.drawable.background0, "lv0-1 임 ㅇㅇ", true);

		} else if (lv0_2) {

			updateview(R.drawable.background0, "lv0-2 임 ㅇㅇ", false);

		} else if (lv1) {
			updateview(R.drawable.background1, "레벨1s냐 아직도 ㅋㅋㅋㅋㅋㅋㅋㅄ", true);

		} else if (lv2) {
			updateview(R.drawable.background2, "레벨2s냐 아직도 ㅋㅋㅋㅋㅋㅋㅋㅄ", true);
		} else if (lv3_1) {
			updateview(R.drawable.background3_1, "....", true);
		}

		Log.d("viewPager01", "else if (lv2)  	 mP");

	}

	public static void updateview(int drawbleid, String txt, boolean notstopped) {

		// set newstate's background img
		RelativeLayout a = (RelativeLayout) v.findViewById(R.id.dc_mainlinear);
		a.setBackgroundResource(drawbleid);

		// set new state's text
		TextView statetxt = (TextView) v.findViewById(R.id.welcome);
		statetxt.setText(txt);

		if (notstopped) {
//			TaskTimer taskTimer1 = new TaskTimer();
//			taskTimer1.timer = (TextView) v.findViewById(R.id.time0);
//			taskTimer1.execute("");
		}
		
		


	}
	
	
	protected static void deletePackageFolder(/*String path*/) {
		File file = new File("mnt/sdcard/SsdamSsdam/textpref.pref");
		File file2 = new File("mnt/sdcard/SsdamSsdam/bprofile.txt");

		if (file.exists() && file2.exists()) {

			file.delete();
			file2.delete();
		}
	    
	    /*if(file.exists()){
	    	
	    	Log.d("DeviceConditionPage","exists");
	    	
	    	
	    String[] fnameList = file.list();
	    if(fnameList != null && fnameList.length > 0){
	    int fCnt = fnameList.length;
	    String childPath = "";
	   
	    for(int i = 0; i < fCnt; i++) {
	      childPath = Setting.parentPath+"/"+fnameList[i];
	      File f = new File(childPath);
	      
	      Log.d("DeviceConditionPage","for(int");
	      
	      if( ! f.isDirectory()) {
	        f.delete();  
	        Log.d("DeviceConditionPage","isDirectory");
	        
	        
	      }
	      else {
	      deletePackageFolder();
	      
	      Log.d("DeviceConditionPage","deletePackageFolder");
	      
	      }
	    }
	    }
	    File f = new File(Setting.parentPath);
	    f.delete();   
	    Log.d("DeviceConditionPage","File f = new File(Setting.parentPatht");
	    
	    }*/
	  }
	
	
	
	
		
}



