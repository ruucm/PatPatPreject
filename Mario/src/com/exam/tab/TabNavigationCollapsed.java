package com.exam.tab;

import android.annotation.SuppressLint;
import android.os.Bundle;

public class TabNavigationCollapsed extends coinBlockIntroActivity {
    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //The following two options trigger the collapsing of the main action bar view.
        //See the parent activity for the rest of the implementation
        
        /*
        requestWindowFeature(Window.FEATURE_PROGRESS);
        
        int progress = (Window.PROGRESS_END - Window.PROGRESS_START) / 100 * 130;
        setSupportProgress(progress);
        
        */
        
        getActionBar().setDisplayShowHomeEnabled(false); 
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayUseLogoEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setDisplayShowCustomEnabled(false);
        getActionBar().setHomeButtonEnabled(false);
    }
}
