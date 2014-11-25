package com.ruucm.patpat;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class Lv3_1State implements ICoinBlockViewState {


	public static boolean overlapAnimSwitch  = true;


	CoinBlockView mViewContext;


	public Lv3_1State(CoinBlockView viewContext) {
		mViewContext = viewContext;


	}
	
	
	
	public void Draw(CoinBlockView viewContext, Bitmap canvas) {


		viewContext.setState(new Lv3_1WaitState());
		


	}

	public boolean NeedRedraw() {
		return false; 
	}

	public void OnClick(CoinBlockView viewContext) {
		
		
		
		
		Log.d("addClickIntent","3OnClick");
		
	/*	RemoteViews rviews = new RemoteViews(viewContext.getPackageName(), R.layout.coin_block_widget);
		
		
		rviews.setImageViewResource(R.id.block, R.drawable.spin_animation);*/
		
		
//		drawable.stop();
		
		viewContext.addAnimatable(new Lv3ClickAnim());
		
		
		
	}

	
	






	private class Lv3_1WaitState implements ICoinBlockViewState {


		public CoinBlockView  context;




		public void OnClick(CoinBlockView viewContext) {

			Log.d("addClickIntent","3wait OnClick");

			int textcode = (int)(Math.random()*9);
			String text = null;

			switch(textcode)
			{
			case 0:
				text = "꼬북꼬북";
				break;

			case 1:
				text = "꼬북꼬북꼬북꼬북";
				break;

			case 2:
				text = "꼬부우우욱";
				break;

			case 3:
				text = "꼬부우우욱";
				break;

			case 4:
				text = "꼬부우우욱꼬부우우욱";
				break;

			case 5:
				text = "얍얍";
				break;

			case 6:
				text = "꼬부우우욱";
				break;

			case 7:
				text = "뀨!";
				break;

			case 8:
				text = "뀨우!! 꼬부우우욱!!";
				break;
			}

			Toast.makeText(CoinBlockView.Context, text, Toast.LENGTH_SHORT).show();

		}


		public void Draw(CoinBlockView viewContext, Bitmap canvas) {
			//<<<<<<< HEAD
			
			
			

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
			
		}



		@Override
		public void OnHeadsetConnected(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void OnHeadsetDisconnected(CoinBlockView viewContext) {
			// TODO Auto-generated method stub
			
		}



	}

	private class Lv3Animation implements IAnimatable {
		//진동할때 올라오고, 상단에 남는 드로블
		private int flowerRaise = 4;
		private int animstage = 0;

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw() {
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


	private class Lv3OftenAnim implements IAnimatable {
		private int spriteVib = 0; 



		public boolean AnimationFinished() {
			return false;
		}

		public void Draw() {
			
			
		}
	}

	private class Lv3ClickAnim implements IAnimatable {
		private int spriteVib = 0; 



		public boolean AnimationFinished() {
			return false;
		}



		public void Draw() {
			// Draw the brick at bottom
			
			Log.d("addClickIntent","Draw_Lv3ClickAnim"); 
			
//			RemoteViews  rviews = new RemoteViews(mViewContext.getPackageName(), R.layout.coin_block_widget);

//			Log.e("addClickIntent","mViewContext.getPackageName() : "+mViewContext.getPackageName());
			
			CoinBlockView.rviews.setImageViewResource(R.id.block, R.drawable.spin_animation);
			


		}
	}

/*	private void animeRemove(IAnimatable animeObject)
	{
		if(animeSwitch){
			animeSwitch = false;
			mViewContext.removeAnimatable(animeObject);
			mViewContext.setState(new Lv3_1WaitState());
		}else{
			mViewContext.removeAnimatable(animeObject);
		}
	}*/


	@Override
	public void OnHeadsetConnected(CoinBlockView viewContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnHeadsetDisconnected(CoinBlockView viewContext) {
		// TODO Auto-generated method stub

	}







}
