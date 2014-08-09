package com.exam.tab;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.exam.R;
import com.exam.TextPref;




	
public class viewPager01 extends Fragment {
	
	
	
	//프레퍼런스 
		public static TextPref mPref;
		//public static TextPref fbPref;
		 
		
		static boolean init ;
		public static boolean lv0_1 = true;
		static boolean lv0_2 = true;
		public static boolean lv1 = true;
		public static boolean lv2 = true;
		
		
		
		static View v;
	

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	v = inflater.inflate(R.layout.device_condition, container, false);
            //View tv = v.findViewById(R.id.text02);
            //((TextView) tv).setText("coolkim.tistory.com");
        	//coinBlockIntroActivity.UpdateIntroView();
        	/*
        	RelativeLayout a = (RelativeLayout)v.findViewById(R.id.mainlinear);	
		 	Log.d("coinBlockIntroActivity","RelativeLayout " +a);
			a.setBackgroundResource(R.drawable.background2);
			*/
        	UpdateIntroView();
        	
        	
            return v; 
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
				
				
				Log.d("viewPager01","UpdateIntroView" + init + lv0_1 + lv0_2 + lv1 + lv2);
		   			
		   			if (init){ 
		   				 
		   				
		   				updateview(R.drawable.background,"init 임 ㅇㅇ", false);
		   				
		   			}
		   			else if (lv0_1){
		   				
		   				updateview(R.drawable.background0,"lv0-1 임 ㅇㅇ", true);
		   				
		   				
		   				
		   			}
		   			else if (lv0_2){
		   				
		   				updateview(R.drawable.background0,"lv0-2 임 ㅇㅇ", false);
		   				
		   				
		   			}else if (lv1){
		   				updateview(R.drawable.background1, "레벨1s냐 아직도 ㅋㅋㅋㅋㅋㅋㅋㅄ",true);
		   				
		   			}
		   			else if (lv2){
		   				updateview(R.drawable.background2, "레벨2s냐 아직도 ㅋㅋㅋㅋㅋㅋㅋㅄ",true);    	    				
		   			}
			 
		   			Log.d("viewPager01","else if (lv2)  	 mP");
			 
		   			
		   			
		   			
			 
		}
		
		public static void updateview (int drawbleid, String txt, boolean notstopped){
			 
			 
			 
			 Log.d("viewPager01","updateview"+drawbleid+txt);
			 
				//set newstate's background img
			 	RelativeLayout a = (RelativeLayout)v.findViewById(R.id.mainlinear);	
			 	Log.d("viewPager01","RelativeLayout " +a);
				a.setBackgroundResource(drawbleid);
				
				Log.d("viewPager01","setBackgroundResource "+drawbleid);
				 
				//set new state's text
				TextView statetxt = (TextView)v.findViewById(R.id.welcome);		
				statetxt.setText(txt);
				Log.d("viewPager01","setText ");
				
				if(notstopped){
				TaskTimer taskTimer1 = new TaskTimer();
				taskTimer1.timer = (TextView)v.findViewById(R.id.time0);	
				taskTimer1.execute("");
				}
				
				Log.d("viewPager01","notstopped ");
				
				
			 
		}

        
        
    }

