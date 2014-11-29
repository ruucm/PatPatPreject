package com.jym.patpat;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

public class Lv3_1State implements IPatpatViewState {


	public static boolean overlapAnimSwitch  = true;


	PatpatView mViewContext;


	public Lv3_1State(PatpatView viewContext) {
		mViewContext = viewContext;


	}
	
	
	
	public void Draw(PatpatView viewContext) {


		viewContext.setState(new Lv3_1WaitState());
		


	}

	public boolean NeedRedraw() {
		return false; 
	}

	public void OnClick(PatpatView viewContext) {
		
		
		
		
		
		
	}

	
	






	private class Lv3_1WaitState implements IPatpatViewState {

//		int spriteVib = 0;

		public PatpatView  context;


		public void Draw(PatpatView viewContext) {
			
			
			Log.d("draw_Lv3_1WaitState","Draw");
			
//			viewContext.addAnimatable(new Lv3ClickAnim());
			
//			if(spriteVib == 0){
				PatpatView.rviews.setImageViewResource(R.id.block, R.drawable.fish_animation);
				/*spriteVib++;
				} else {mViewContext.removeAnimatable(this);
				Log.d("addClickIntent","removeAnimatable: "+this);
				
				}*/
			
		}

		public void OnClick(PatpatView viewContext) {

			Log.d("addClickIntent","3wait OnClick");

			
			
			

			Log.d("addClickIntent","3OnClick");
			
		/*	RemoteViews rviews = new RemoteViews(viewContext.getPackageName(), R.layout.coin_block_widget);
			
			
			rviews.setImageViewResource(R.id.block, R.drawable.spin_animation);*/
			
			
//			drawable.stop();
			
			viewContext.addAnimatable(new Lv3ClickAnim());
			
			
			
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

			Toast.makeText(PatpatView.Context, text, Toast.LENGTH_SHORT).show();

		}


	

		public boolean NeedRedraw() {
			return false;
		}

		@Override
		public void OnEvolve(PatpatView coinBlockView) {
			//coinBlockView.setState(new InitState(coinBlockView));	
		}

		@Override
		public void OnOften(PatpatView coinBlockView) {	
			
		}



		@Override
		public void OnHeadsetConnected(PatpatView viewContext) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void OnHeadsetDisconnected(PatpatView viewContext) {
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
	public void OnEvolve(PatpatView coinBlockView) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnOften(PatpatView coinBlockView) {
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
			Log.d("addClickIntent","spriteVib : "+spriteVib); 
			
//			RemoteViews  rviews = new RemoteViews(mViewContext.getPackageName(), R.layout.coin_block_widget);
	
//			Log.e("addClickIntent","mViewContext.getPackageName() : "+mViewContext.getPackageName());
			
			if(spriteVib == 0){
			PatpatView.rviews.setImageViewResource(R.id.block, R.drawable.fish_animation);
			spriteVib++;
			} else {mViewContext.removeAnimatable(this);
			Log.d("addClickIntent","removeAnimatable: "+this);
			
			}

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
	public void OnHeadsetConnected(PatpatView viewContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnHeadsetDisconnected(PatpatView viewContext) {
		// TODO Auto-generated method stub

	}







}
