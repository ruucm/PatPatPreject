package com.exam.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.media.*;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.os.*;
import android.util.*;
import android.widget.*;

import com.exam.*;
import com.exam.tab.Setting;
import com.exam.tab.coinBlockIntroActivity;
import com.exam.tab.viewPager01;

public class Lv0_1State implements ICoinBlockViewState {
	// sprites
	Sprite flowerSprite = MediaAssets.getInstance().getSprite(R.drawable.samsung_test);
	Sprite sp = MediaAssets.getInstance().getSprite(R.drawable.egg);
	
	// sound
	MediaPlayer snd = MediaAssets.getInstance().getSoundPlayer(R.raw.smb_powerup_appears);
	MediaPlayer snd1 = MediaAssets.getInstance().getSoundPlayer(R.raw.notify_sound);

	// sprite vibration controller 
	private int animStage = 0;
	private int[] heightModifier = { 8, -8, 6, -6, 4, -4, 2, -2 };	
	private int[] widthModifier = { 3, -3, 2, -2, 1, -1, 0, -0 };
	
	boolean fuck = false;   
	CoinBlockView context;
  
	// Animations instances
	Lv0OftenAnim lv0ofAnim; 
	Lv0ClickAnim lv0clAnim;
	Lv0_1DblClickAnim lv0_1dblClick;

	Lv0_1WifiOnAnim lv0_1wifiOn;
	Lv0_1WifiOffAnim lv0_1wifiOff;

	Lv0_1PowerConnectedAnim lv0_1powerOn;
	Lv0_1PowerDisconnectedAnim lv0_1powerOff;

	Lv0_1USBConnectedAnim lv0_1usbOn;
	Lv0_1USBDisconnectedAnim lv0_1usbOff;

	Lv0_1HeadsetConnectedAnim lv0_1headsetOn;
	Lv0_1HeadsetDisconnectedAnim lv0_1headsetOff;

	Lv0_1PlaneOnAnim lv0_1planeOn;
	Lv0_1PlaneOffAnim lv0_1planeOff;
	
	Lv0_1SMSAnim lv0_1sms;

	public Lv0_1State(CoinBlockView viewContext) {
		context = viewContext;
		lv0clAnim = new Lv0ClickAnim();
		viewContext.addAnimatable(lv0clAnim);

		snd.seekTo(0);
		snd.setOnSeekCompleteListener(new OnSeekCompleteListener() {
			public void onSeekComplete(MediaPlayer mp) {
				snd.start(); 
			}
		});
	}

	private class Lv0_1DblClickAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 16, -16, 8, -8, 4, -4, 0, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom

			SpriteHelper.DrawSprite(canvas, sp, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * context.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}

	private class Lv0_1WifiOnAnim implements IAnimatable {
		private int blockVib = 0;	
		//private int[] widthModifier = { 12, -12, 8, -8, 4, -4, 0, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			Log.v("WIFI", "Drawanim");

			SpriteHelper.DrawSprite(canvas, sp, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * context.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}

	private class Lv0_1WifiOffAnim implements IAnimatable {
		private int blockVib = 0;	
		//private int[] widthModifier = { 12, -12, 8, -8, 4, -4, 0, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			Log.v("WIFI", "Drawanim");

			SpriteHelper.DrawSprite(canvas, sp, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * context.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}

	private class Lv0_1USBConnectedAnim implements IAnimatable {
		private int blockVib = 0;

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			Log.v("USB", "Drawanim");

			SpriteHelper.DrawSprite(canvas, sp, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * context.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}

	private class Lv0_1USBDisconnectedAnim implements IAnimatable {
		private int blockVib = 0;

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			Log.v("USB", "Drawanim");

			SpriteHelper.DrawSprite(canvas, sp, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * context.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}

	private class Lv0_1HeadsetConnectedAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 24, -24, 16, -16, 8, -8, 4, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * context.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}

	private class Lv0_1HeadsetDisconnectedAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 24, -24, 16, -16, 8, -8, 4, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * context.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}

	private class Lv0_1PlaneOnAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 24, -24, 16, -16, 8, -8, 4, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom

			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * context.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}

	private class Lv0_1PlaneOffAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 24, -24, 16, -16, 8, -8, 4, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom

			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * context.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}

	private class Lv0_1SMSAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 24, -24, 16, -16, 8, -8, 4, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			//Sprite sp1 = MediaAssets.getInstance().getSprite(R.drawable.mushroom);
			//吏꾨룞�븷�븣�쓽 �븯�떒�뱶濡쒕툝

			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * context.getDensity()),0);

			if (blockVib < 7) { 
				blockVib++;
			}
		}
	}

	private class Lv0_1PowerConnectedAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 24, -24, 16, -16, 8, -8, 4, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			Log.v("WIFI", "Drawanim");

			SpriteHelper.DrawSprite(canvas, sp, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * context.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}

	private class Lv0_1PowerDisconnectedAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 24, -24, 16, -16, 8, -8, 4, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			Log.v("WIFI", "Drawanim");

			SpriteHelper.DrawSprite(canvas, sp, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * context.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}

	public void Draw(CoinBlockView viewContext, Bitmap canvas) {
		// Draw the brick at bottom
		//Sprite sp1 = MediaAssets.getInstance().getSprite(R.drawable.brick_disabled);
		//진동할때의 하단드로블
		SpriteHelper.DrawSprite(canvas, sp, 0, SpriteHelper.DrawPosition.BottomCenter, 0,
				-(int)(heightModifier[animStage] * viewContext.getDensity()));


		animStage++;  

		Log.v("tag3", "animstage");

		if (animStage >= heightModifier.length)
			viewContext.setState(new Lv0WaitState(viewContext));
	}

	public boolean NeedRedraw() {
		return true;
	}

	public void OnClick(CoinBlockView viewContext) {
		// TODO Auto-generated method stub 

	}

	@Override
	public void OnOften(CoinBlockView coinBlockView) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnEvolve(CoinBlockView coinBlockView) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnInit(CoinBlockView coinBlockView) {
		//coinBlockView.removeAnimatable(lv0Anim);
	}

	class Lv0WaitState implements ICoinBlockViewState {

		final MediaPlayer snd = MediaAssets.getInstance().getSoundPlayer(R.raw.smb_powerup);
		CoinBlockView mViewContext;
		
		
		
		

		long second = 0 ;
		
		//프레퍼런스 
		TextPref mPref;	

		boolean lv0_1;
		boolean lv0_2;
		public String INTENT_EVOLVE_FORMAT = "com.exam.view.INTENT_EVOLVE_FORMAT";
		public String INTENT_INIT_FORMAT = "com.exam.view.INTENT_INIT_FORMAT";

//		public  boolean lv0_2;
//		public static  boolean lv1 ;
//		public static  boolean lv2 ;
//		public static  boolean lv3_1 ;
		
//		public static  boolean stateNum ;

		 int CliCount0_1 ;
		

		public Lv0WaitState(CoinBlockView viewContext) {
			mViewContext = viewContext;
			
			(new Handler()).postDelayed(new Runnable(){
				public void run() {
					/*if (mViewContext.getState().getClass() == Lv0WaitState.class)
					{
						Log.v("tag2", "lv0-run");

						
						mViewContext.addAnimatable(lv0Anim);

						if (CoinBlockView.second >= 10 && CoinBlockView.second <45)	{
							mViewContext.removeAnimatable(lv0Anim);							
							mViewContext.setState(new DisabledState(mViewContext));
							mViewContext.setState(new Lv1State(mViewContext));

							Log.v("tag3", "Lv0WaitState-setState"); 
						}

						mViewContext.setState(new OftenState(mViewContext, flowerSprite)); 
						Log.v("tag3", "mViewContext.setState(new OftenState");

						v0Anim.Draw2(Bitmap.createBitmap(mViewContext.cwidth, mViewContext.cheight, Bitmap.Config.ARGB_8888));
						mViewContext.scheduleRedraw();
						 
					}*/
				}
			}, 3000);
		}

		public void OnClick(CoinBlockView viewContext) {
			
			
			
			viewContext.removeAnimatable(lv0clAnim);			

			lv0clAnim = new Lv0ClickAnim();
			viewContext.addAnimatable(lv0clAnim);


			
			
			
			
			snd.seekTo(0);
			snd.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd.start();
				}
			});
			
			
	
			
			try {
				mPref = new TextPref("mnt/sdcard/SsdamSsdam/textpref.pref");
			} catch (Exception e) { 
				e.printStackTrace();
			}
			
			
			mPref.Ready();			
			
			
			CliCount0_1 = mPref.ReadInt("clicount0_1", 0);			 
			CliCount0_1++;			
			
			

						
		
			
			
			
		

//			mPref.Ready();

//			lv0_1 = mPref.ReadBoolean("lv0_1state", false);	
//			lv0_2 = mPref.ReadBoolean("lv0_2state", false);
//			lv0_2 = mPref.ReadBoolean("lv0_2state", false);
//			lv1 = mPref.ReadBoolean("lv1state", false);
//			lv2 = mPref.ReadBoolean("lv2state", false);
//			lv3_1 = mPref.ReadBoolean("lv3_1state", false);

//			CliCount0_1 = mPref.ReadInt("clicount0_1", 0);
//			CliCount0_2 = mPref.ReadInt("clicount0_2", 0);
//			CliCount1 = mPref.ReadInt("clicount1", 0);
//			CliCount2 = mPref.ReadInt("clicount2", 0);
////			CliCount3 = mPref.ReadInt("clicount2", 0);

//			
//			second = mPref.ReadInt("time", 0);
			
			
			Log.i("InitState","second "+second);
			
			
//			mPref.EndReady();
			
			
			
			
			

			
//			if (second >= 10 && second <= 12 && CliCount0_1 >= 3 && lv0_1){
//				lv0_1 = false;
//				lv0_2 = true;
//				mPref.WriteBoolean("lv0_1state", lv0_1);	
//				mPref.WriteBoolean("lv0_2state", lv0_2);
//				mPref.CommitWrite();
//				
//				RemoteViews rviews = new RemoteViews(CoinBlockWidgetApp.getApplication().getPackageName(), R.layout.coin_block_widget);
//				updateEvolveIntent(rviews, CoinBlockWidgetApp.getApplication());
//
//				
//				
//			}		
//			else{

			mPref.WriteInt("clicount0_1", CliCount0_1);
			mPref.CommitWrite();
//			}
			
			
			
			
		}
		
		
		private  void updateEvolveIntent(RemoteViews rviews, Context context) {
			// TODO Auto-generated method stub				
			 
			
//			Log.d("CoinBlockView","state " + init+" "+lv0_1+" "+lv0_2+" "+lv1+" "+lv2 );
			
			int mWidgetId = CoinBlockView.mWidgetId;
//			
			Intent intent = new Intent(String.format(INTENT_INIT_FORMAT, mWidgetId));
			intent.putExtra("widgetId11", mWidgetId);		

			context.sendBroadcast(intent);

			Intent intent2 = new Intent(String.format(INTENT_EVOLVE_FORMAT, mWidgetId));
			intent2.putExtra("widgetId10", mWidgetId);				

			context.sendBroadcast(intent2);

			Log.d(coinBlockWidgetProvider.TAG," updateEvolveIntent(Remo(rviews);");
		}

		public void Draw(CoinBlockView viewContext, Bitmap canvas) {
			SpriteHelper.DrawSprite(canvas, sp, 0, SpriteHelper.DrawPosition.BottomCenter);
		}

		public boolean NeedRedraw() {
			return false;
		}

		@Override
		public void OnOften(CoinBlockView coinBlockView) {
			coinBlockView.removeAnimatable(lv0ofAnim);
			lv0ofAnim = new Lv0OftenAnim();			
			coinBlockView.addAnimatable(lv0ofAnim);
		}

		@Override
		public void OnEvolve(CoinBlockView coinBlockView) {
			// TODO Auto-generated method stub
			coinBlockView.setState(new Lv0_2State(coinBlockView));

			//coinBlockIntroActivity.taskTimer1.setTextView1(R.id.time0);
			
			
			
			coinBlockIntroActivity.taskTimer1.isCanceled = true;

//			CoinBlockView.lv0_1 = false;	
//			CoinBlockView.lv0_2 = true;	
//			
//			Log.d("Lv0_1State","CoinBlockView");
//
//			CoinBlockView.mPref.Ready();			
//			CoinBlockView.mPref.WriteBoolean("lv0_1state", CoinBlockView.lv0_1);		
//			CoinBlockView.mPref.WriteBoolean("lv0_2state", CoinBlockView.lv0_2);	
//			CoinBlockView.mPref.CommitWrite();
//
//			Log.d("Lv0_1State","CommitWrite");
			
			viewPager01.UpdateIntroView();
			
			
			
			Log.d("Lv0_1State","UpdateIntroView");
		}

		@Override
		public void OnInit(CoinBlockView coinBlockView) {
			// TODO Auto-generated method stub
			coinBlockView.removeAnimatable(lv0ofAnim);
			coinBlockView.removeAnimatable(lv0clAnim);
			coinBlockView.removeAnimatable(lv0_1powerOn);
			coinBlockView.removeAnimatable(lv0_1wifiOn);

			Log.d("tag3","OnInit");
		}

		@Override
		public void OnDblClick(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			viewContext.removeAnimatable(lv0_1dblClick);

			lv0_1dblClick = new Lv0_1DblClickAnim();			
			viewContext.addAnimatable(lv0_1dblClick);

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});

			Log.v("DOUBLECLICK", "Entering Doubleclick");

			Setting.DblClickCount++;
			Setting.mPref.Ready();
			Setting.mPref.WriteInt("dblclick", Setting.DblClickCount);			
			Setting.mPref.CommitWrite();
		}

		@Override
		public void OnSMSReceived(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			lv0_1planeOn = new Lv0_1PlaneOnAnim();
			viewContext.addAnimatable(lv0_1planeOn);

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});
		}

		@Override
		public void OnWifiConnected(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			Log.v("WIFI", "OnWifiConnected");

			viewContext.removeAnimatable(lv0clAnim);
			viewContext.removeAnimatable(lv0_1wifiOn);

			lv0_1wifiOn = new Lv0_1WifiOnAnim();	
			viewContext.addAnimatable(lv0_1wifiOn);

			Log.v("WIFI", "addAnimatable");			

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});
		}

		@Override
		public void OnWifiDisconnected(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			Log.v("WIFI", "OnWifiDisconnected");

			viewContext.removeAnimatable(lv0clAnim);
			viewContext.removeAnimatable(lv0_1wifiOff);

			lv0_1wifiOff = new Lv0_1WifiOffAnim();	
			viewContext.addAnimatable(lv0_1wifiOff);

			Log.v("WIFI", "addAnimatable");			

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});
		}
		
		@Override
		public void OnPowerConnected(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			Log.v("POWER", "OnPower");

			viewContext.removeAnimatable(lv0_1powerOn);

			lv0_1powerOn = new Lv0_1PowerConnectedAnim();	
			viewContext.addAnimatable(lv0_1powerOn);			

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});
		}

		@Override
		public void OnPowerDisconnected(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			Log.v("POWER", "OffPower");

			viewContext.removeAnimatable(lv0_1powerOff);

			lv0_1powerOff = new Lv0_1PowerDisconnectedAnim();	
			viewContext.addAnimatable(lv0_1powerOff);			

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});
		}

		@Override
		public void OnUSBConnected(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			lv0_1usbOn = new Lv0_1USBConnectedAnim();
			viewContext.addAnimatable(lv0_1usbOn);

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});

			Log.v("USB", "USBOn lv0-1");
		}

		@Override
		public void OnUSBDisconnected(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			lv0_1usbOff = new Lv0_1USBDisconnectedAnim();
			viewContext.addAnimatable(lv0_1usbOff);

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});

			Log.v("USB", "USBOff lv0-1");
		}

		@Override
		public void OnHeadsetConnected(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			lv0_1headsetOn = new Lv0_1HeadsetConnectedAnim();
			viewContext.addAnimatable(lv0_1headsetOn);

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});

			Log.v("HEADSET", "Headset lv0-1");
		}

		@Override
		public void OnHeadsetDisconnected(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			lv0_1headsetOff = new Lv0_1HeadsetDisconnectedAnim();
			viewContext.addAnimatable(lv0_1headsetOff);

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});

			Log.v("HEADSET", "HeadsetOff lv0-1");
		}

		@Override
		public void OnPlaneModeOn(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			lv0_1planeOn = new Lv0_1PlaneOnAnim();
			viewContext.addAnimatable(lv0_1planeOn);

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});
		}

		@Override
		public void OnPlaneModeOff(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			lv0_1planeOff = new Lv0_1PlaneOffAnim();
			viewContext.addAnimatable(lv0_1planeOff);

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});
		}
	}

	private class Lv0OftenAnim implements IAnimatable {
		private int blockVib = 0;

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {

			// Draw the brick at bottom
			SpriteHelper.DrawSprite(canvas, sp, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * context.getDensity()),0);

			if (blockVib < 7)
				blockVib++; 

			Log.v("tag4", "blockVib"+Integer.toString(blockVib));
		}
	}

	private class Lv0ClickAnim implements IAnimatable {
		private int spriteVib = 0;

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			SpriteHelper.DrawSprite(canvas, sp, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[spriteVib] * context.getDensity()), 0);

			if (spriteVib < 7)
				spriteVib++;
		}
	}

	public void setContentView(int drawbleid, String txt) {
		coinBlockIntroActivity instance = coinBlockIntroActivity.getInstance();	
		Log.d("Lv0_1State","instance"+instance);

		//set newstate's background img
		LinearLayout a = (LinearLayout)instance.findViewById(R.id.mainlinear);			
		a.setBackgroundResource(drawbleid);

		Log.d("Lv0_1State","setBackgroundResource");

		//set new state's text
		TextView statetxt = (TextView)instance.findViewById(R.id.welcome);		
		statetxt.setText(txt);
	}

	@Override
	public void OnDblClick(CoinBlockView viewContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnPowerConnected(CoinBlockView viewContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnHeadsetConnected(CoinBlockView viewContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnSMSReceived(CoinBlockView viewContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnWifiConnected(CoinBlockView viewContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnWifiDisconnected(CoinBlockView viewContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnPowerDisconnected(CoinBlockView viewContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnUSBConnected(CoinBlockView viewContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnUSBDisconnected(CoinBlockView viewContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnHeadsetDisconnected(CoinBlockView viewContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnPlaneModeOn(CoinBlockView viewContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnPlaneModeOff(CoinBlockView viewContext) {
		// TODO Auto-generated method stub

	}
}