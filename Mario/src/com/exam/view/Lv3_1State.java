package com.exam.view;

import android.graphics.*;
import android.media.*;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.os.*;
import android.util.*;
import android.widget.*;

import com.exam.*;
import com.exam.view.Lv1State.Lv1WaitState;

public class Lv3_1State implements ICoinBlockViewState {
	Sprite flowerSprite = MediaAssets.getInstance().getSprite(R.drawable.knifing);
	Sprite evolve 		= MediaAssets.getInstance().getSprite(R.drawable.knifing8);
	Sprite blankSprite 	= MediaAssets.getInstance().getSprite(R.drawable.blankimage);
	MediaPlayer snd  = MediaAssets.getInstance().getSoundPlayer(R.raw.smb_powerup_appears);
	MediaPlayer snd1 = MediaAssets.getInstance().getSoundPlayer(R.raw.dingding);
	MediaPlayer snd2 = MediaAssets.getInstance().getSoundPlayer(R.raw.smb_1_up);

	private int animStage = 0;
	private int[] heightModifier = { 8, -8, 6, -6, 4, -4, 2, -2 };		// here
	private int[] heightModifier2 = { 0, 0, 0, 0, 0, 0, 0, 0 };
	private int[] widthModifier = {4, -4, 4, -4, 3, -3, 2, -2, 1, -1, 0, -0, 0, 0 };	// here
	
	private Lv3AnimStatus aniStatus;

	boolean animeSwitch = false;
	
	/*
	Lv3OftenAnim lv3ofAnim;
	Lv3DblClickAnim lv3dblClick;

	Lv3WifiOnAnim lv3wifiOn;
	Lv3WifiOffAnim lv3wifiOff;

	Lv3PowerConnectedAnim lv3powerOn;
	Lv3PowerDisconnectedAnim lv3powerOff;

	Lv3USBConnectedAnim lv3usbOn;
	Lv3USBDisconnectedAnim lv3usbOff;

	Lv3HeadsetConnectedAnim lv3headsetOn;
	Lv3HeadsetDisconnectedAnim lv3headsetOff;

	Lv3PlaneOnAnim lv3planeOn;
	Lv3PlaneOffAnim lv3planeOff;

	Lv3SMSAnim lv3sms;
	 */
	
	CoinBlockView mViewContext;
	
	private static class Lv3AnimStatus {
		private Lv3AnimStatus instance = null;
		private static boolean isLv3Clicked;
		
		private Lv3AnimStatus() {}
		
		public Lv3AnimStatus GetInstance() {
			if(instance == null)
				instance = this;
			return instance;
		}
		
		public boolean GetAnimStatus() {
			return isLv3Clicked;
		}
		
		public void SetAnimStatus(boolean s) {
			isLv3Clicked = s;
		}
	}

	public Lv3_1State(CoinBlockView viewContext) {
		mViewContext = viewContext;
 
		snd2.seekTo(0);
		snd2.setOnSeekCompleteListener(new OnSeekCompleteListener() {
			public void onSeekComplete(MediaPlayer mp) {
				snd2.start(); 
			} 
		}); 
		
		aniStatus.GetInstance();
	}
	
	private class Lv3DblClickAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 16, -16, 8, -8, 4, -4, 0, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom

			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * mViewContext.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}

	private class Lv3WifiOnAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 24, -24, 16, -16, 8, -8, 4, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * mViewContext.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}

	private class Lv3WifiOffAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 24, -24, 16, -16, 8, -8, 4, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * mViewContext.getDensity()),0);

			if (blockVib < 7) 
				blockVib++;
		}
	}

	private class Lv3HeadsetConnectedAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 24, -24, 16, -16, 8, -8, 4, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * mViewContext.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}

	private class Lv3HeadsetDisconnectedAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 24, -24, 16, -16, 8, -8, 4, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * mViewContext.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}
	
	private class Lv3USBConnectedAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 24, -24, 16, -16, 8, -8, 4, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * mViewContext.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}
	
	private class Lv3USBDisconnectedAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 24, -24, 16, -16, 8, -8, 4, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * mViewContext.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}

	private class Lv3PlaneOnAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 24, -24, 16, -16, 8, -8, 4, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * mViewContext.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}
	private class Lv3PlaneOffAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 24, -24, 16, -16, 8, -8, 4, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * mViewContext.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}

	private class Lv3SMSAnim implements IAnimatable {
		private int blockVib = 0;	
		private int[] widthModifier = { 24, -24, 16, -16, 8, -8, 4, 0 };	// here

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * mViewContext.getDensity()),0);

			if (blockVib < 7)
				blockVib++;
		}
	}

	private class Lv3PowerConnectedAnim implements IAnimatable {
		private int blockVib = 0;

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			Log.v("Lv2State", "Drawanim");

			if (blockVib < 7)
				blockVib++;
		}
	}

	private class Lv3PowerDisconnectedAnim implements IAnimatable {
		private int blockVib = 0;

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			Log.v("Lv2State", "Drawanim");

			if (blockVib < 7)
				blockVib++;
		}
	}

	public void Draw(CoinBlockView viewContext, Bitmap canvas) {
		// Draw the brick at bottom
		SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,0,
				0);
		
		
		viewContext.setState(new Lv3_1WaitState());
		
		/*animStage++;

		if(animStage > 60 )	
			viewContext.setState(new Lv3_1WaitState());
		*/
		

	}

	public boolean NeedRedraw() {
		return false; 
	}

	public void OnClick(CoinBlockView viewContext) {
		// TODO Auto-generated method stub 
	}

	private class Lv3_1WaitState implements ICoinBlockViewState {
		MediaPlayer snd = MediaAssets.getInstance().getSoundPlayer(R.raw.smb_powerup);
		
		

		public void OnClick(CoinBlockView viewContext) {
			Log.v("Lv2State", "OnClick3");
			
			if(aniStatus.GetAnimStatus() == false) {
				aniStatus.SetAnimStatus(true);
				animeSwitch = true;
				viewContext.addAnimatable(new Lv3ClickAnim());
	
				snd.seekTo(0);
				snd.setOnSeekCompleteListener(new OnSeekCompleteListener() {
					public void onSeekComplete(MediaPlayer mp) {
						snd.start();
					}
				});
	
				int textcode = (int)(Math.random()*9);
				String text = null;
	
				switch(textcode)
				{
				case 0:
					text = "뀨? 죽었냐";
					break;
	
				case 1:
					text = "디져랑";
					break;
	
				case 2:
					text = "디져";
					break;
	
				case 3:
					text = "죽어랏!!";
					break;
	
				case 4:
					text = "소ㅑㅅ소ㅑㄱ!!";
					break;
	
				case 5:
					text = "얍얍";
					break;
	
				case 6:
					text = "하하하하하하핳";
					break;
	
				case 7:
					text = "뀨!";
					break;
	
				case 8:
					text = "뀨우!! 죽어랏!!";
					break;
				}
	
				Toast.makeText(CoinBlockView.Context, text, Toast.LENGTH_SHORT).show();
	
				CoinBlockView.CliCount2++;			
				CoinBlockView.mPref.Ready();			
				CoinBlockView.mPref.WriteInt("clicount2", CoinBlockView.CliCount2);			
				CoinBlockView.mPref.CommitWrite();
				
				aniStatus.SetAnimStatus(false);
			}
		}

		private int blockVib = 0;

		public void Draw(CoinBlockView viewContext, Bitmap canvas) {
//<<<<<<< HEAD
			if(animeSwitch) SpriteHelper.DrawSprite(canvas, blankSprite, 0, SpriteHelper.DrawPosition.BottomCenter, 0, 0 );
			else 			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter, 0, 0 );
			
			if (blockVib < 13) blockVib++;
/*=======
			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * context.getDensity()),0);

			if (blockVib < 13)
				blockVib++;
			
			
			Log.d("Lv3_1State","Draw(in WaitState) blockVib is "+blockVib);
			*/
			
		}

		public boolean NeedRedraw() {
			return false;
		}

		@Override
		public void OnEvolve(CoinBlockView coinBlockView) {
			//coinBlockView.setState(new InitState(coinBlockView));	
		}

		@Override
		public void OnOften(CoinBlockView coinBlockView) {		
			coinBlockView.addAnimatable(new Lv3OftenAnim());
		}

		@Override
		public void OnInit(CoinBlockView coinBlockView) {
		}

		@Override
		public void OnDblClick(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			viewContext.addAnimatable(new Lv3DblClickAnim());

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});
		}

		@Override
		public void OnSMSReceived(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			viewContext.addAnimatable(new Lv3PlaneOnAnim());

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
			viewContext.addAnimatable(new Lv3WifiOnAnim());

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
			viewContext.addAnimatable(new Lv3WifiOffAnim());

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
			viewContext.addAnimatable(new Lv3PowerConnectedAnim());

			Log.v("Lv2State", "addAnimatable");			

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
			Log.v("POWER", "OnPower");	
			viewContext.addAnimatable(new Lv3PowerDisconnectedAnim());

			Log.v("Lv2State", "addAnimatable");			

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
			viewContext.addAnimatable(new Lv3USBConnectedAnim());

			Log.v("Lv2State", "addAnimatable");			

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});
		}

		@Override
		public void OnUSBDisconnected(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			viewContext.addAnimatable(new Lv3USBDisconnectedAnim());

			Log.v("Lv2State", "addAnimatable");			

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});
		}
		
		@Override
		public void OnHeadsetConnected(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			viewContext.addAnimatable(new Lv3HeadsetConnectedAnim());

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});
		}

		@Override
		public void OnHeadsetDisconnected(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			viewContext.addAnimatable(new Lv3HeadsetDisconnectedAnim());

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});
		}

		@Override
		public void OnPlaneModeOn(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			viewContext.addAnimatable(new Lv3PlaneOnAnim());

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
			viewContext.addAnimatable(new Lv3PlaneOffAnim());

			snd1.seekTo(0);
			snd1.setOnSeekCompleteListener(new OnSeekCompleteListener() {
				public void onSeekComplete(MediaPlayer mp) {
					snd1.start();
				}
			});
		}
	}

	private class Lv2Animation implements IAnimatable {
		//진동할때 올라오고, 상단에 남는 드로블
		private int flowerRaise = 4;
		private int animstage = 0;

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			/*
			SpriteHelper.DrawSprite(canvas, flowerSprite, flowerSprite.NextFrame(),
							SpriteHelper.DrawPosition.BottomCenter, 0, -(int) (flowerRaise * 4 * context.getDensity()));

			// Draw the flower
			if (flowerRaise < 8)
				flowerRaise++;

			// Draw the brick at bottom
			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter, 0,
							- (int)(heightModifier2[animStage] * context.getDensity()));

			if (animstage < 3)
				animstage++;

			if (animStage >= heightModifier.length)
				context.setState(new DisabledState(context));
			 */
		}
	}

	@Override
	public void OnEvolve(CoinBlockView coinBlockView) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnOften(CoinBlockView coinBlockView) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnInit(CoinBlockView coinBlockView) {
		// TODO Auto-generated method stub

	}

	private class Lv3OftenAnim implements IAnimatable {
		private int blockVib = 0;

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[blockVib] * mViewContext.getDensity()),0);

			if (blockVib < 13) blockVib++;
			else			   animeRemove(this); 

			Log.v("tag4", "blockVib"+Integer.toString(blockVib));
		}
	}

	private class Lv3ClickAnim implements IAnimatable {
		private int spriteVib = 0; 
		private boolean isAnimRunning = false;

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw(Bitmap canvas) {
			// Draw the brick at bottom
			/*SpriteHelper.DrawSprite(canvas, flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter,
					-(int)(widthModifier[spriteVib] * mViewContext.getDensity()), 0 );

			if (spriteVib < 13) spriteVib++;
			else				animeRemove(this);
			*/
			
			if (spriteVib < 8) {
				SpriteHelper.DrawSprite(canvas, evolve, evolve.NextFrame(), SpriteHelper.DrawPosition.BottomCenter,0,0);
				spriteVib++;
			}
			else 
				animeRemove(this);
		}
	}
	
	private void animeRemove(IAnimatable animeObject)
	{
		if(animeSwitch){
			animeSwitch = false;
			mViewContext.removeAnimatable(animeObject);
			mViewContext.setState(new Lv3_1WaitState());
		}else{
			mViewContext.removeAnimatable(animeObject);
		}
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
