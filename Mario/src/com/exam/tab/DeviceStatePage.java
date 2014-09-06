package com.exam.tab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.exam.R;
import com.exam.TextPref;

public class DeviceStatePage extends SherlockListFragment {


	// 프레퍼런스
	public static TextPref bPref;
	
	
	static String device_version;
	
	
	/** An array of items to display */
    String device_state_list[] = new String[]{
            "MANUFACTURER",
            "MODEL",
            "PRODUCT",
            "NetworkCountryIso",
            "SimCountryIso"
    };
    
    String device_states[] ;
    
    int android_images[] = new int[]{
            R.drawable.jb,
            R.drawable.ics,
            R.drawable.honeycomb,
            R.drawable.gingerbread,
            R.drawable.froyo
    };
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	     	
		try {
			bPref = new TextPref("mnt/sdcard/SsdamSsdam/bprofile.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}

		bPref.Ready();
		
		
		device_states = new String[] { bPref.ReadString("MANUFACTURER", ""),
				bPref.ReadString("MODEL", ""), bPref.ReadString("PRODUCT", ""),
				bPref.ReadString("NetworkCountryIso", ""),
				bPref.ReadString("SimCountryIso", "")

		};

		bPref.EndReady();
		
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<5;i++){
                HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt01", device_state_list[i]);
            hm.put("txt02", device_states[i]);
            hm.put("img", Integer.toString(android_images[i]  ) );
            aList.add(hm);
        }

        
        Log.d("DeviceStatePage","EndReady();();");
        
        
        // Keys used in Hashmap
        String[] from = { "img", "txt01", "txt02"};

        // Ids of views in listview_layout
        int[] to = { R.id.img, R.id.txt01, R.id.txt02};
        
        
        Log.d("DeviceStatePage","int[] to =");

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.device_state, from, to);        
        
         
        // Setting the adapter to the listView
        setListAdapter(adapter);      
        
        
        updateDeviceVersionView() ;
        
        return super.onCreateView(inflater, container, savedInstanceState);

    }
	
	
	public static void updateDeviceVersionView() {
		
		 
		 
		 
	 		bPref.Ready();	 		
	 		device_version = bPref.ReadString("DeviceVersion", "");	 	
			bPref.EndReady();
			
			
			
			String sub = device_version.substring(device_version.indexOf(":"), device_version.lastIndexOf('.'));
			
			
			
			
			if (sub == "2.3"){
				updateview(R.drawable.background, device_version );				
			}
			else if (sub == "3.1" || sub =="3.2"){
				
			}
			else if (sub == "4.0"){
				
			}
			else if (sub == "4.1" || sub == "4.2" || sub == "4.3"){
				
			}
			else if (sub == "4.4"){
				
			}
			else {
				
			}
			
			
			
			
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
	   			else if (lv3_1){
	   				updateview(R.drawable.background3_1, "이런 인간도있군..",true);    	    				
	   			}
		 
	   			Log.d("viewPager01","else if (lv2)  	 mP");
		 
	   			
	   			
	   			
		 
	}
	
	public static void updateview (int drawbleid, String txt){
		 
		 
		 
		 
			//set newstate's background img
			LinearLayout a = (LinearLayout) IntroActivity.getInstance().findViewById(R.id.tab_linear);	
			a.setBackgroundResource(drawbleid);
			
			 
			//set new state's text
			TextView statetxt = (TextView)v.findViewById(R.id.welcome);		
			statetxt.setText(txt);
			
			
			
			
		 
	}
	
	
}