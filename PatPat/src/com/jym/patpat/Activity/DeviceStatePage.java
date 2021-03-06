package com.jym.patpat.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.jym.helper.TextPref;
import com.jym.patpat.R;

public class DeviceStatePage extends ListFragment {


	// 프레퍼런스
	public static TextPref initPref;
	
	
	static String device_version;
	
	
	/** An array of items to display */
    String device_state_list[] = new String[]{
            "MANUFACTURER",
            "MODEL",
            "PRODUCT",
            "Network CountryIso",
            "Sim CountryIso", 
            "Device Version"
    };
    
    String device_states[] ;
    
    int android_images[] = new int[]{
            R.drawable.jb,
            R.drawable.ics,
            R.drawable.honeycomb,
            R.drawable.gingerbread,
            R.drawable.froyo,
            0
    };
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	     	
		try {
			initPref = new TextPref("mnt/sdcard/SsdamSsdam/bprofile.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}

		initPref.Ready();
		
		
		device_states = new String[] { 
				initPref.ReadString("MANUFACTURER", ""),
				initPref.ReadString("MODEL", ""), initPref.ReadString("PRODUCT", ""),
				initPref.ReadString("NetworkCountryIso", ""),
				initPref.ReadString("SimCountryIso", ""),
				initPref.ReadString("DeviceVersion", "")

		};

		initPref.EndReady();
		
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<device_state_list.length;i++){
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
		
		Log.d("mphone","call - updateDeviceVersionView");
		 
		 
	 		initPref.Ready();	 		
	 		device_version = initPref.ReadString("DeviceVersion", "");	 	
			initPref.EndReady();
			
			
			
			String sub = device_version.substring(device_version.indexOf(":"), device_version.lastIndexOf('.'));
			
			
			Log.d("mphone","updateDeviceVersionView");
			
			
			Log.d("mphone","device_version : "+sub);
			
			if (sub.equals(":2.3")){
				updateview(R.drawable.ds_back_gingerbread);				
			}
			else if (sub.equals(":3.1") || sub.equals(":3.2")){
				updateview(R.drawable.ds_back_honeycomb);	
			}
			else if (sub.equals(":4.0")){
				updateview(R.drawable.ds_back_icecreamsw);	
			}
			else if (sub.equals(":4.1") || sub.equals(":4.2") || sub.equals(":4.3")){
				updateview(R.drawable.ds_back_jellybean);	
			}
			else if (sub.equals(":4.4")){
				updateview(R.drawable.ds_back_kitkat);	
			}
			else {
				updateview(R.drawable.ds_back_gingerbread);	
			}
			
			
	   			
	   			
		 
	}
	
	public static void updateview (int drawbleid/*, String txt*/){
		 
		 
		Log.d("mphone","updateview");
		 
			//set newstate's background img
			LinearLayout a = (LinearLayout) Activity_Intro.getInstance().findViewById(R.id.tab_linear);	
			
			Log.d("mphone","LinearLayout");
			a.setBackgroundResource(drawbleid);
			
			 
			Log.d("mphone","setBackgroundResource");
			
			
		/*	//set new state's text
			TextView statetxt = (TextView)v.findViewById(R.id.welcome);		
			statetxt.setText(txt);*/
			
			
			
			
		 
	}
	
	
	
	
	
	
}