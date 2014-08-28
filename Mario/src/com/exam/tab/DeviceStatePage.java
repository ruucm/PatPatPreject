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
import android.widget.SimpleAdapter;

import com.actionbarsherlock.app.SherlockListFragment;
import com.exam.R;

public class DeviceStatePage extends SherlockListFragment {

	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.device_state, container, false);
		// View tv = v.findViewById(R.id.text03);
		// ((TextView) tv).setText("Hope is a good thing.");

		return v;
	}*/
	
	/** An array of items to display */
    String android_versions[] = new String[]{
            "Jelly Bean",
            "IceCream Sandwich",
            "HoneyComb",
            "GingerBread",
            "Froyo"
    };
    
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
    	     	
		
		
		
		
		
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<5;i++){
                HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", android_versions[i]);
            hm.put("img", Integer.toString(android_images[i]  ) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "img","txt" };

        // Ids of views in listview_layout
        int[] to = { R.id.img,R.id.txt};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.device_state, from, to);        
        
        // Setting the adapter to the listView
        setListAdapter(adapter);        
        
        return super.onCreateView(inflater, container, savedInstanceState);

    }
	
	
}