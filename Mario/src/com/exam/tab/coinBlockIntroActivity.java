package com.exam.tab;



import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Window;
import com.exam.R;
import com.exam.TextPref;
import com.exam.R.drawable;
import com.exam.R.id;
import com.exam.R.layout;
import com.facebook.widget.ProfilePictureView;


public class coinBlockIntroActivity extends FragmentActivity
{
	/** Called when the activity is first created. */
	
	
	
	//add Actionbar
	
	private TextView mSelected;
    
	static int bLevel;
    
  
	
	//add tap x viewpager
	DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
	ViewPager mViewPager;

	
	
	//액티비티간 통신을 위한
	final static int SETTING = 0;
	
	
	
	TextView welcome ; 
	
	
	TextView InitStateText ; 
	TextView Lv0StateText ; 
	   
	
	
	//프레퍼런스 
	public static TextPref mPref;
  	public static TextPref fbPref;
	 
  	
  	static boolean init = true;
	public static boolean lv0_1 = true;
	static boolean lv0_2 = true;
	public static boolean lv1 = true;
	public static boolean lv2 = true;
	
  	/*
	//facebook	
	private Session.StatusCallback statusCallback = new SessionStatusCallback();
    private Button buttonLoginLogout;
    
    */
  	
  	
   // static String userId ;
	
	
	public static TextView time;
	static int checkHandler = 0;
	static long count = 0;
	public static long second = 0;
	
	int tasktime ;
	
	
	

	

	//Async Task
	private AsyncTask<Void, Integer, Void> mTask;
	private Button mButton;
	
	private long time1;
	
	
	//Async Task
	private static coinBlockIntroActivity instance;
	public static  TaskTimer taskTimer1 = new TaskTimer();
	//private Get getAsyncTask;
	back task;
	Bitmap bmImg;
	ImageView Profile;
	
	
	String userId ;
	
	
	private ProfilePictureView profilePic;
	 
	
	public static coinBlockIntroActivity getInstance() {
        return instance;
    }
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		//setContentView(R.layout.main);
		
		
		
		
		instance = this;
		Log.d("coinBlockIntroActivity", "instance"+instance);
		
		//프레퍼런스 읽어오기   
  		File saveDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "SsdamSsdam"); // dir : 생성하고자 하는 경로
  		
  		Log.d("coinBlockIntroActivity", "saveDir(saveDir)fbPref.Ready();");
  		
  		if(!saveDir.exists()) 
  		{
  			saveDir.mkdirs();
  		}


  		try {
  			mPref = new TextPref("mnt/sdcard/SsdamSsdam/textpref.pref");
  			Log.d("coinBlockIntroActivity", "TextPref(intent)fbPref.Ready();");
  			fbPref = new TextPref("mnt/sdcard/SsdamSsdam/facebookprofile.txt");
  			Log.d("coinBlockIntroActivity", "fbPref(intent)fbPref.Ready();");
  			

  		} catch (Exception e) { 
  			e.printStackTrace();
  		}       
  		mPref.Ready();
  		Log.d("coinBlockIntroActivity", "mPref.Ready();(intent)fbPref.Ready();");
		fbPref.Ready();
		Log.d("coinBlockIntroActivity", "fbPref.Ready();(intent)fbPref.Ready();");
		

		//set Main Background Image & Text
		
		
		userId = fbPref.ReadString("userId", "");
  		String userFirstName = fbPref.ReadString("userFirstName", "");
  		String userLastName = fbPref.ReadString("userLastName", "");
		
  		
  		
		init = mPref.ReadBoolean("initstate", false);	
		lv0_1 = mPref.ReadBoolean("lv0_1state", false);
		lv0_2 = mPref.ReadBoolean("lv0_2state", false);
		lv1 = mPref.ReadBoolean("lv1state", false);
		lv2 = mPref.ReadBoolean("lv2state", false);
		
		//boolean lv0state = mPref.ReadBoolean("lv0state", false);
		/*
		
		final android.app.ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
 
        actionBar.addTab(actionBar
                .newTab()
                .setText("Tab1")
                .setTabListener(
                        new TabListener<FragmentTab1>(this, "tab1",
                                FragmentTab1.class)));
        actionBar.addTab(actionBar
                .newTab()
                .setText("Tab2")
                .setTabListener(
                        new TabListener<FragmentTab2>(this, "tab3",
                                FragmentTab2.class)));
        actionBar.addTab(actionBar
                .newTab()
                .setText("Tab3")
                .setTabListener(
                        new TabListener<FragmentTab3>(this, "tab3",
                                FragmentTab3.class)));
 
        if (savedInstanceState != null) {
            actionBar.setSelectedNavigationItem(savedInstanceState.getInt(
                    "selectedTab", 0));
        }
		
        */
        
	
		
        
        setContentView(R.layout.activity_collection_demo);

        // Create an adapter that when requested, will return a fragment representing an object in
        // the collection.
        // 
        // ViewPager and its adapters use support library fragments, so we must use
        // getSupportFragmentManager.
        mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());

        // Set up action bar.
        final android.app.ActionBar actionBar = getActionBar();

        // Specify that the Home button should show an "Up" caret, indicating that touching the
        // button will take the user one step up in the application's hierarchy.
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Set up the ViewPager, attaching the adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        	
		//setContentView(R.layout.states); 
		
	
        
        
        
        //styled
        

		
		
	/*
		
  		
		welcome = (TextView)findViewById(R.id.welcome);		
		welcome.setText(userFirstName+" "+userLastName+" 님 환영합니다 위젯을 시작하려면 Set-up 버튼을 누르세요");
		
		
		
		//UpdateIntroView();
			
			
		
    	
    	profilePic = (ProfilePictureView)findViewById(R.id.profilepic);
    	
    	
    	
    //	profilePic.setCropped(true);
    	profilePic.setPresetSize(ProfilePictureView.LARGE);
    	
    	profilePic.setProfileId(userId);
    	
    	
    	*/
  
    	
    	
    	/*
    	mSelected = (TextView)findViewById(R.id.text02);

        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for (int i = 1; i <= 2; i++) {
            ActionBar.Tab tab = getSupportActionBar().newTab();
            //tab.setText("Tab " + i);
            tab.setTabListener(this);
            if(i==1){
            	tab.setText("STATATES");
            }
            if(i==2){
            	tab.setText("PROFILE");
            	tab.setContentDescription(R.layout.profile);
            }
            getSupportActionBar().addTab(tab);
        } 
         
        */
    	
	
  		
  		
  		fbPref.EndReady();
  		
  		
  		Log.d("coinBlockIntroActivity","EndReady");
  		
  		
        
        
	} 
	
	/*
	 @Override
	    protected void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putInt("selectedTab", getActionBar()
	                .getSelectedNavigationIndex());
	    }
	
	*/
	
	
	 public static class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {

	        public DemoCollectionPagerAdapter(FragmentManager fm) {
	            super(fm);
	        }
	/*
	        @Override
	        public Fragment getItem(int i) {
	            Fragment fragment = new DemoObjectFragment();
	            Bundle args = new Bundle();
	            args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1); // Our object is just an integer :-P
	            fragment.setArguments(args);
	            return fragment;
	        }
	        
	        */
	        
	        @Override
	        public Fragment getItem(int i) {
	            switch (i) {
	                case 0:
	                    // The first section of the app is the most interesting -- it offers
	                    // a launchpad into the other demonstrations in this example application.
	                    return new LaunchpadSectionFragment();

	                default:
	                    // The other sections of the app are dummy placeholders.
	                    Fragment fragment = new DummySectionFragment();
	                    Bundle args = new Bundle();
	                    args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
	                    fragment.setArguments(args);
	                    return fragment;
	            }
	        }

	        @Override
	        public int getCount() {
	            // For this contrived example, we have a 100-object collection.
	            return 100;
	        }

	        @Override
	        public CharSequence getPageTitle(int position) {
	            return "OBJECT " + (position + 1);
	        }
	    }
	
	
	
	public void onClick(View v)
	{
		if(mButton.getText().equals("start"))
		{
			Log.v("tag7", "equals(start");
			
			mTask = new AsyncTask<Void, Integer, Void>()
			{
		    	private boolean isCanceled = false;
		    	 
		    	@Override
		    	protected void onPreExecute()
		    	{
		    		//publishProgress(0);
		    		isCanceled = false;
		    	}
		    	 
				@Override 
				protected Void doInBackground(Void... params)
				{
					
					Log.v("tag7", "doInBackground");
					
					for(int i = 1 ; i <= 50 && !isCanceled ; i++)
					{
						Log.v("tag7", "for"+i);

						try
						{
							publishProgress(i);
							Thread.sleep(100);
						}
						catch(InterruptedException e)
						{
							e.printStackTrace();
						}
					}
					/* 
					
					while(true)

					{
						
					if(isCanceled == true) break;
					
					try
					{
						publishProgress(1);
						Thread.sleep(100);
					}
					catch(InterruptedException e)
					{
						e.printStackTrace();
					}
					

					}*/
					return null;
				}

				@Override
				protected void onProgressUpdate(Integer... progress)
				{
					//mProgress.setProgress(progress[0]);
					
					count ++;
					second = getSecond(count);
					time.setText( second + "초 " + count%10 );
					
					
				}
				
				@Override
				protected void onPostExecute(Void result)
				{
					//Toast.makeText(coinBlockIntroActivity.this, "onPostExecute", Toast.LENGTH_SHORT).show();
					//mButton.setText("start");
				}
				 
				@Override
				protected void onCancelled()
				{
					Log.v("tag7", "onCancelled");
					isCanceled = true;
					//publishProgress(0);
					//Toast.makeText(coinBlockIntroActivity.this, "onCancelled", Toast.LENGTH_SHORT).show();
				}
			};
			
			mTask.execute();
			mButton.setText("cancel");
		}
		else if(mButton.getText().equals("cancel"))
		{
			
			Log.v("tag7", "equals(cancel");
			mTask.cancel(false);
			mButton.setText("start");
		}
	}
	
	
	

	public void mOnClick(View v)
	{
		switch(v.getId())
		{
		case R.id.setbutton:    		
			Intent intent = new Intent(this, Setting.class);
			startActivityForResult(intent, SETTING ); 		
			break;
			
			
		case R.id.btn_start: 
			
			
			
			
			if(taskTimer1.isCanceled == false){
				TaskTimer taskTimer1 = new TaskTimer();
				taskTimer1.setTextView1(R.id.time0);
		        //taskTimer1.setTime(0);
		        taskTimer1.execute("");
		        //taskTimer1.execute("");
			}
			else
				taskTimer1.isCanceled = false;

			
			/*
				//taskTimer1.isCanceled = false;
			
				TaskTimer taskTimer2 = new TaskTimer();
				taskTimer2.setTextView1(R.id.time0);
		        taskTimer2.setTime(0);
		        taskTimer2.execute("");
			
				*/
		
			/*
				TaskTimer taskTimer1 = new TaskTimer();
				taskTimer1.setTextView1(R.id.time0);
		        //taskTimer1.setTime(0);
		        taskTimer1.execute(""); 
		
			*/
			
			break;
			
		case R.id.btn_pause: 
			//thread.onStop();		
			
			//tasktime = taskTimer1.GetTime();
			//Log.v("tag9", "tasktiem" +Integer.toString(tasktime));
		//	taskTimer1.isCanceled = true;
			
			
			//taskTimer1.cancel(false);
			break;
			
			 
		
			
		case R.id.btn_stop:
			
			
			
			if(taskTimer1.isCanceled == false){
				TaskTimer taskTimer1 = new TaskTimer();
				taskTimer1.setTextView1(R.id.time0);
		        taskTimer1.setTime(0);
		        taskTimer1.timer.setText("0");
		        //taskTimer1.execute("");
			}
			else
				taskTimer1.isCanceled = false;
			
			
			break;
			
			
		case R.id.reset1:
			
			
						
			
			break;

		
	        
			
		}
	}
	
	
	
	
	
		
		public static long getSecond(long milli){
			long secondValue = 0;
			secondValue = milli / 10;
			return secondValue;
		}
		
		/*
		
		//Facebook Login
		
		private void init() {
	        buttonLoginLogout = (Button)findViewById(R.id.buttonLoginLogout);
	    }
	 
	    @SuppressLint("NewApi")
	    private void dataInit() {
	        //ActionBar Init
	        getActionBar().setDisplayShowHomeEnabled(false);
	        getActionBar().setTitle(R.string.board_detail_activity_title);
	    }
	    
	    private void facebookInit(Bundle savedInstanceState) {
	        Settings.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
	        
	        Session session = Session.getActiveSession();
	        if (session == null) {
	            if (savedInstanceState != null) {
	                session = Session.restoreSession(this, null, statusCallback, savedInstanceState);
	            }
	            if (session == null) {
	                session = new Session(this);
	            }
	            Session.setActiveSession(session);
	            if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
	                session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
	            }
	        }
	        
	        updateView();
	    }
	 
	     @Override
	        public void onStart() {
	            super.onStart();
	            Session.getActiveSession().addCallback(statusCallback);
	        }
	 
	        @Override
	        public void onStop() {
	            super.onStop();
	            Session.getActiveSession().removeCallback(statusCallback);
	        }
	 
	        @Override
	        public void onActivityResult(int requestCode, int resultCode, Intent data) {
	            super.onActivityResult(requestCode, resultCode, data);
	            Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	        }
	 
	        @Override
	        protected void onSaveInstanceState(Bundle outState) {
	            super.onSaveInstanceState(outState);
	            Session session = Session.getActiveSession();
	            Session.saveSession(session, outState);
	        }
	 
	        private void updateView() {
	            Session session = Session.getActiveSession();
	            if (session.isOpened()) {
	                buttonLoginLogout.setText("로그아웃");
	                buttonLoginLogout.setOnClickListener(new OnClickListener() {
	                    public void onClick(View view) { onClickLogout(); }
	                });
	            } else {
	                buttonLoginLogout.setText("로그인");
	                buttonLoginLogout.setOnClickListener(new OnClickListener() {
	                    public void onClick(View view) { onClickLogin(); }
	                });
	            }
	        }
	 
	        private void onClickLogin() {
	            Session session = Session.getActiveSession();
	            if (!session.isOpened() && !session.isClosed()) {
	                session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
	            } else {
	                Session.openActiveSession(this, true, statusCallback);
	            }
	        }
	  
	        private void onClickLogout() {
	            Session session = Session.getActiveSession();
	            if (!session.isClosed()) {
	                session.closeAndClearTokenInformation();
	            }
	        }
	 
	        private class SessionStatusCallback implements Session.StatusCallback {
	            @Override
	            public void call(Session session, SessionState state, Exception exception) {
	                updateView();    
	                getFaceBookMe(session);
	            }
	        }
	        
	        
	        private void getFaceBookMe(Session session){
	        	 
	            if(session.isOpened()){
	                Request.newMeRequest(session, new Request.GraphUserCallback() {
	     
	                    @Override
	                    public void onCompleted(GraphUser user, Response response) {
	                        response.getError();
	                        
	                        
	                        System.err.println(" getId  :  " + user.getId());
	                        System.err.println(" getFirstName  :  " + user.getFirstName());
	                        System.err.println(" getLastName  :  " + user.getLastName());
	                        System.err.println(" getMiddleName  :  " + user.getMiddleName());
	                        System.err.println(" getBirthday  :  " + user.getBirthday());
	                        //System.err.println(" getLink  :  " + user.getLink());
	                        //System.err.println(" getName  :  " + user.getName());
	                        //System.err.println(" getUsername :  " + user.getUsername());
	                        //System.err.println(" getLocation :  " + user.getLocation());
	                        //System.err.println("getRawResponse  :  " + response.getRawResponse());
	                       
	                         
	                        Log.d("tag01"," getId  :  " + user.getId() );
	                        Log.d("tag01"," getFirstName  :  " + user.getFirstName() );
	                        Log.d("tag01"," getLastName  :  " + user.getLastName() );
	                        Log.d("tag01"," getMiddleName  :  " + user.getMiddleName() );
	                        Log.d("tag01"," getBirthday  :  " + user.getBirthday() );
	                         
	                        
	                        
	                       
	                        
	                    }
	                }).executeAsync();
	            }
	        }

			*/
		
		
		@SuppressLint("NewApi")
		protected void onActivityResult (int requestCode, int resultCode, Intent data) {
			switch (requestCode){
			case SETTING:
				Log.d("tag02","onActivityResult");
				
				
				/*
				//if(!initstate)
				setContentView(R.layout.main);
				
				
				
				LinearLayout a = (LinearLayout)findViewById(R.id.mainlinear);
				Log.d("tag03","LinearLayout");
				
				
				a.setBackgroundResource(R.drawable.background);
				//a.setBackground( (Drawable)getResources().getDrawable(R.drawable.coin_background2));
				Log.d("tag03","setBackgroundResource");
				
				
				//coinBlockIntroActivity b = new coinBlockIntroActivity();
				//b.setBackground( (Drawable)getResources().getDrawable(R.drawable.coin_background2));
						
				
				InitStateText = (TextView)findViewById(R.id.welcome);
				InitStateText.setText("상자를 열어라");
				*/
				
			}
			
			
		}
		

    	//AsyncTask for Http request
    	
    	
    	
    	 private class back extends AsyncTask<String, Integer,Bitmap>{
    	        
    		 
    		 
    	        @Override
    	        protected Bitmap doInBackground(String... urls) {
    	            // TODO Auto-generated method stub
    	            try{
    	                URL myFileUrl = new URL(urls[0]);
    	                HttpURLConnection conn = (HttpURLConnection)myFileUrl.openConnection();
    	                conn.setDoInput(true);
    	                conn.connect();
    	                Log.d("tag1-1","connect"+urls[0]);
    	                /*
    	                InputStream is = conn.getInputStream();
    	                
    	                bmImg = BitmapFactory.decodeStream(is);
    	                */
    	                bmImg = BitmapFactory.decodeStream(myFileUrl.openConnection().getInputStream());
    	                Log.d("tag1-1","BitmapFactory"+bmImg);
    	                
    	            }catch(IOException e){
    	                e.printStackTrace();
    	            }
    	            return bmImg;
    	        }
    	        
    	        protected void onPostExecute(Bitmap img){
    	        	Profile.setImageBitmap(bmImg);
    	        	Log.d("tag1-1","setImageBitmap"+bmImg);
    	        }
    	        
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
    			
    	    			
    	    			if (init){
    	    				
    	    				
    	    				updateview(R.drawable.background,"init 임 ㅇㅇ", true);
    	    				
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
    		 
    	    			Log.d("coinBlockIntroActivity","else if (lv2)  	 mP");
    		 
    	    			
    	    			
    	    			
    		 
    	 }
    	 
    	 public static void updateview (int drawbleid, String txt, boolean notstopped){
    		 
    		 
    		 
    		 Log.d("coinBlockIntroActivity","updateview"+drawbleid+txt);
    		 
    			//set newstate's background img
    			LinearLayout a = (LinearLayout)instance.findViewById(R.id.mainlinear);			
    			a.setBackgroundResource(drawbleid);
    			
    			 
    			//set new state's text
    			TextView statetxt = (TextView)instance.findViewById(R.id.welcome);		
    			statetxt.setText(txt);
    			
    			
    			if(notstopped){
    			TaskTimer taskTimer1 = new TaskTimer();
				taskTimer1.setTextView1(R.id.time0);
		        taskTimer1.execute("");
    			}
    			
    			
    		 
    	 }
    	 
    	 
    


		
		
		
	
	
	
	
}