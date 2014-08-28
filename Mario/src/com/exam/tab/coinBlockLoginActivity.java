package com.exam.tab;



import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.exam.R;
import com.exam.TextPref;
import com.facebook.LoggingBehavior;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.facebook.model.GraphUser;





public class coinBlockLoginActivity extends Activity
{
	/** Called when the activity is first created. */
	
	
	  
	 
	
	//facebook	
	private Session.StatusCallback statusCallback = new SessionStatusCallback();
	
    private Button buttonLoginLogout;
    
    
    
    
    //facebook login profile
    
    String userId ;
    String userFirstName ;
    String userLastName ;
    
    
	
    
    //프레퍼런스 
    public static TextPref mPref;	
  	public static TextPref fbPref;	
  	
  	boolean setDialogOn ;
  	
  
 
	

	 

	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		 
		Log.d("coinBlockLoginActivity","onCreate.");
		super.onCreate(savedInstanceState);
		
		 
		setContentView(R.layout.login);
		
			 
		 
		init();
        dataInit();
         
        
        facebookInit(savedInstanceState);
		
        

		
		// device 고유 정보값 가져오기
	    Log.d("coinBlockLoginActivity", "BOARD: " + Build.BOARD);
	    Log.d("coinBlockLoginActivity", "BRAND: " + Build.BRAND);
	    Log.d("coinBlockLoginActivity", "CPU_ABI: " + Build.CPU_ABI);
	    Log.d("coinBlockLoginActivity", "DEVICE: " + Build.DEVICE);
	    Log.d("coinBlockLoginActivity", "DISPLAY: " + Build.DISPLAY);
	    Log.d("coinBlockLoginActivity", "FINGERPRINT: " + Build.FINGERPRINT);
	    Log.d("coinBlockLoginActivity", "HOST: " + Build.HOST);
	    Log.d("coinBlockLoginActivity", "ID: " + Build.ID);
	    Log.d("coinBlockLoginActivity", "MANUFACTURER: " + Build.MANUFACTURER);
	    Log.d("coinBlockLoginActivity", "MODEL: " + Build.MODEL);
	    Log.d("coinBlockLoginActivity", "PRODUCT: " + Build.PRODUCT);
	    Log.d("coinBlockLoginActivity", "TAGS: " + Build.TAGS);
	    Log.d("coinBlockLoginActivity", "TIME: " + Build.TIME);
	    Log.d("coinBlockLoginActivity", "TYPE: " + Build.TYPE);
	    Log.d("coinBlockLoginActivity", "USER: " + Build.USER);
	       
	       
	 // device 정보 가져오기
	    TelephonyManager telephony=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
	       
	    Log.d("coinBlockLoginActivity", "getCallState : "+telephony.getCallState());
	    Log.d("coinBlockLoginActivity", "getDataActivity : "+telephony.getDataActivity());
	    Log.d("coinBlockLoginActivity", "getDataState : "+telephony.getDataState());
	    Log.d("coinBlockLoginActivity", "getDeviceId : "+telephony.getDeviceId());
	    Log.d("coinBlockLoginActivity", "getDeviceSoftwareVersion : "+telephony.getDeviceSoftwareVersion());
	    Log.d("coinBlockLoginActivity", "getLine1Number : "+telephony.getLine1Number());
	    Log.d("coinBlockLoginActivity", "getNetworkCountryIso : "+telephony.getNetworkCountryIso());
	    Log.d("coinBlockLoginActivity", "getNetworkOperator : "+telephony.getNetworkOperator());
	    Log.d("coinBlockLoginActivity", "getNetworkOperatorName : "+telephony.getNetworkOperatorName());
	    Log.d("coinBlockLoginActivity", "getNetworkType : "+telephony.getNetworkType());
	    Log.d("coinBlockLoginActivity", "getPhoneType : "+telephony.getPhoneType());
	    Log.d("coinBlockLoginActivity", "getSimCountryIso : "+telephony.getSimCountryIso());
	    Log.d("coinBlockLoginActivity", "getSubscriberId : "+telephony.getSubscriberId());
	    Log.d("coinBlockLoginActivity", "getVoiceMailAlphaTag : "+telephony.getVoiceMailAlphaTag());
	    Log.d("coinBlockLoginActivity", "getVoiceMailNumber : "+telephony.getVoiceMailNumber());
	    Log.d("coinBlockLoginActivity", "isNetworkRoaming : "+telephony.isNetworkRoaming());
	    Log.d("coinBlockLoginActivity", "hasIccCard : "+telephony.hasIccCard());
	    Log.d("coinBlockLoginActivity", "hashCode : "+telephony.hashCode());
	    Log.d("coinBlockLoginActivity", "toString : "+telephony.toString());
	       
	    
	    //wifi 정보
	    
	    
	    WifiManager wifi = (WifiManager)this.getSystemService(Context.WIFI_SERVICE);
	    Log.d("coinBlockLoginActivity", "wificonnectioninfo : "+wifi.getConnectionInfo());
	    Log.d("coinBlockLoginActivity", "wifistate : "+wifi.getWifiState());
	    Log.d("coinBlockLoginActivity", "wifiDhcpInfo : "+wifi.getDhcpInfo());
	    
        
	    
	    //배터리 정보
	    IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
	    Intent batteryStatus = this.registerReceiver(null, ifilter);
	    
	    Log.d("coinBlockLoginActivity", "battery Level : "+batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1));
	    Log.d("coinBlockLoginActivity", "battery status  : "+batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1));
	    Log.d("coinBlockLoginActivity", "battery chargePlug : "+batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1));
	    Log.d("coinBlockLoginActivity", "battery scale : "+batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1));

        
        
        
        
         
        //프레퍼런스 읽어오기   
      		File saveDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "SsdamSsdam"); // dir : 생성하고자 하는 경로
      		if(!saveDir.exists()) 
      		{
      			saveDir.mkdirs();
      		}


      		try {
      			mPref = new TextPref("mnt/sdcard/SsdamSsdam/textpref.pref");
      			fbPref = new TextPref("mnt/sdcard/SsdamSsdam/facebookprofile.txt");

      		} catch (Exception e) { 
      			e.printStackTrace();
      		}       


      		mPref.Ready();
      		fbPref.Ready();
        
      		Log.d("coinBlockLoginActivity","fbPref.Ready();.");
        
		
      		
      		setDialogOn = mPref.ReadBoolean("setdialogon", true);
      		
      		userId = fbPref.ReadString("userId", "");
      		userFirstName = fbPref.ReadString("userFirstName", "");
      		userLastName = fbPref.ReadString("userLastName", "");
      		 
      		
      		mPref.EndReady();
      		fbPref.EndReady();
      		Log.d("coinBlockLoginActivity","fbPref.");
      		
      		
        
	} 
	
	
	
		
		
		//Facebook Login
		
		private void init() {
	        buttonLoginLogout = (Button)findViewById(R.id.buttonLoginLogout1);
	    }
	 
	    @SuppressLint("NewApi")
	    private void dataInit() {
	        //ActionBar Init
	        //getActionBar().setDisplayShowHomeEnabled(false);
	        //getActionBar().setTitle(R.string.board_detail_activity_title);
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
	                
	                /*
	                
	                Intent intent = new Intent(this, coinBlockIntroActivity.class);
		            //intent.putExtra("userId", userId);
		            //intent.putExtra("userFirstName", userFirstName);
		            //intent.putExtra("userLastName", userLastName);
					startActivity(intent); 
	                
					/*
	                if(DialogOn){
	                

		            Intent intent = new Intent(this, coinBlockIntroActivity.class);
		            //intent.putExtra("userId", userId);
		            //intent.putExtra("userFirstName", userFirstName);
		            //intent.putExtra("userLastName", userLastName);
					startActivity(intent); 
	                }
	                
	                else {
	                	Intent intent = new Intent(this, coinBlockIntroActivity2.class);
						startActivity(intent);
	                }
	                
	                */
					Log.d("tag02","userFirstName"+ userFirstName );
		            
					 
					
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
	        
	        
	        private void toIntro() {

	        	        	
	        	
				
				if(setDialogOn){					
					
					Intent intent = new Intent(this, Setting.class);	 
		        	
					startActivity(intent);

					
		        	//for one-time showing dialog										
					setDialogOn = false;		        	
					mPref.Ready();
					mPref.WriteBoolean("setdialogon", setDialogOn);
					mPref.CommitWrite();
					
					
				}
				else{
					Intent intent = new Intent(this, IntroActivity.class);
					startActivity(intent);		        	
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
	                        
	                        /*
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
	                        */
	                         
	                        /* 
	                        Log.d("tag01"," getId  :  " + user.getId() );
	                        Log.d("tag01"," getFirstName  :  " + user.getFirstName() );
	                        Log.d("tag01"," getLastName  :  " + user.getLastName() );
	                        Log.d("tag01"," getMiddleName  :  " + user.getMiddleName() );
	                        Log.d("tag01"," getBirthday  :  " + user.getBirthday() );
	                        */
	                         
	                        
	                        userId = user.getId() ;
	                        userFirstName = user.getFirstName() ;
	                        userLastName = user.getLastName() ;
	                        
	                        Log.d("coinBlockLoginActivity","userFirstName2"+userFirstName);
	                        
	                        
	                        fbPref.Ready();
	                        
	                        fbPref.WriteString("userId", userId);
	            			fbPref.WriteString("userFirstName", userFirstName);
	            			fbPref.WriteString("userLastName", userLastName);
	                  		
	                    
	            			Log.d("coinBlockLoginActivity", "WriteString;");
	                    
	            			
	            			fbPref.CommitWrite();
	            			
	            			toIntro();
	            			finish();
	            			
	            			

	            			
	            			
	                        
	                        
	                    }
	                }).executeAsync();
	            }
	        }

			
		
		
	
	
	
	
}