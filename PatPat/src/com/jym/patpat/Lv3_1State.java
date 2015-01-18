package com.jym.patpat;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.RemoteViews;

import com.jym.helper.TextPref;
import com.jym.service.TaskTimer;

public class Lv3_1State implements IPatpatViewState {

	public static int clickcount_3_1 = 0;
	TextPref clickPref;

	public static boolean overlapAnimSwitch = true;
	PatpatView mViewContext;

	public Lv3_1State(PatpatView viewContext) {
		mViewContext = viewContext;

		try {
			clickPref = new TextPref("mnt/sdcard/SsdamSsdam/clickpref.pref");
		} catch (Exception e) {
			e.printStackTrace();
		}

		clickPref.Ready();
		clickcount_3_1 = clickPref.ReadInt("ClickCount_3_1", 0);
		clickPref.EndReady();
		
		
		
		InputStream is = null;
		Bitmap bmp = null;
		is = PatpatView.Context.getApplicationContext()
				.getResources().openRawResource(R.drawable.fish_animation);
		bmp = BitmapFactory.decodeStream(is);
		
		

	}

	public void Draw(PatpatView viewContext) {

		Log.e("stop_double_Draw", "Draw_setState_Lv3_1State_");

		viewContext.setState(new Lv3_1WaitState());

	}

	public boolean NeedRedraw() {
		return false;
	}

	public void OnClick(PatpatView viewContext) {

	}

	private class Lv3_1WaitState implements IPatpatViewState {

		// int spriteVib = 0;

		public PatpatView context;

		public void Draw(PatpatView viewContext) {

			Log.d("updateClickIntent_right", "Draw");

			// viewContext.addAnimatable(new Lv3ClickAnim());

			// if(spriteVib == 0){
			/*
			 * PatpatView.rviews.setImageViewResource(R.id.patview01,
			 * R.drawable.fish_animation_left);
			 * PatpatView.rviews.setImageViewResource(R.id.patview02,
			 * R.drawable.fish_animation_right);
			 */
			/*
			 * spriteVib++; } else {mViewContext.removeAnimatable(this);
			 * Log.d("addClickIntent","removeAnimatable: "+this);
			 * 
			 * }
			 */

		}

		public void OnClick(PatpatView viewContext) {

			Log.w("seperated_ClickCount", "OnClick");

			/*
			 * //write clickcount_3_1_left (using textpref)
			 * TaskTimer.CliCount3_1_left++; TaskTimer.ePref.Ready();
			 * TaskTimer.ePref.WriteInt("clicount3_1_left",
			 * TaskTimer.CliCount3_1_left); TaskTimer.ePref.CommitWrite();
			 */

			clickcount_3_1++;
			Log.w("seperated_ClickCount", "clickcount_3_1 : " + clickcount_3_1);

			clickPref.Ready();
			clickPref.WriteInt("ClickCount_3_1", clickcount_3_1);
			clickPref.CommitWrite();

			Log.w("seperated_ClickCount", "End_CommitWrite");

			viewContext.addAnimatable(new Lv3ClickAnim());
			// System.gc();
			


			Log.w("fix_futuretask", "addAnimatabe(start_scheduledraw)");

			/*
			 * new
			 * MyNewTask().execute("http://feeds.feedburner.com/karanbalkar");
			 * Log.d("anim_asyncTask", "execute");
			 */
			int textcode = (int) (Math.random() * 9);
			String text = null;

			switch (textcode) {
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

			// Toast.makeText(PatpatView.Context, text,
			// Toast.LENGTH_SHORT).show();

		}

		public void OnClick_right(PatpatView viewContext) {

			// write clickcount_3_1_left (using textpref)
			TaskTimer.CliCount3_1_right++;
			TaskTimer.ePref.Ready();
			TaskTimer.ePref.WriteInt("clicount3_1_right",
					TaskTimer.CliCount3_1_right);

			// TaskTimer.temp_Count2++;
			TaskTimer.ePref.CommitWrite();

			viewContext.addAnimatable(new Lv3ClickAnim_right());

			int textcode = (int) (Math.random() * 5);
			String text = null;

			switch (textcode) {
			case 0:
				text = "거거긴..";
				break;

			case 1:
				text = "아 ㅅㅂ;;";
				break;

			case 2:
				text = "아ㅏㅇ";
				break;

			case 3:
				text = "왜 ..";
				break;

			case 4:
				text = "아퍼색갸";
				break;

			}

			// Toast.makeText(PatpatView.Context, text,
			// Toast.LENGTH_SHORT).show();

		}

		public boolean NeedRedraw() {
			return false;
		}

		@Override
		public void OnEvolve(PatpatView coinBlockView) {
			// coinBlockView.setState(new InitState(coinBlockView));
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
		// 진동할때 올라오고, 상단에 남는 드로블
		private int flowerRaise = 4;
		private int animstage = 0;

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw() {
			/*
			 * SpriteHelper.DrawSprite(canvas, flowerSprite,
			 * flowerSprite.NextFrame(), SpriteHelper.DrawPosition.BottomCenter,
			 * 0, -(int) (flowerRaise * 4 * context.getDensity()));
			 * 
			 * // Draw the flower if (flowerRaise < 8) flowerRaise++;
			 * 
			 * // Draw the brick at bottom SpriteHelper.DrawSprite(canvas,
			 * flowerSprite, 0, SpriteHelper.DrawPosition.BottomCenter, 0, -
			 * (int)(heightModifier2[animStage] * context.getDensity()));
			 * 
			 * if (animstage < 3) animstage++;
			 * 
			 * if (animStage >= heightModifier.length) context.setState(new
			 * DisabledState(context));
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
		Lv3ClickAnim con;

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw() {
			// Draw the brick at bottom

			con = this;

			// RemoteViews rviews = new
			// RemoteViews(mViewContext.getPackageName(),
			// R.layout.coin_block_widget);

			// Log.e("addClickIntent","mViewContext.getPackageName() : "+mViewContext.getPackageName());

			Log.d("animCount","spriteVib_atChild_Draw : "+spriteVib);
			
			if (spriteVib ==0) {

				Log.w("animCount","setResource_atChild_Draw : "+spriteVib);
				
//				PatpatView.rviews = new RemoteViews(PatpatView.Context.getPackageName(), R.layout.patpat_widget);
				
				PatpatView.rviews.setImageViewResource(R.id.patview01, R.drawable.knifing01);

				PatpatView.rviews.setImageViewResource(R.id.patview01,
						R.drawable.anim_touch_headb);
				

				spriteVib++;
			}
			
		/*	
			
			else if (spriteVib ==1  ){
				
				Log.w("animCount","setResource_atChild_Draw : "+spriteVib);
				
				PatpatView.rviews.setImageViewResource(R.id.patview01, R.drawable.knifing01);

				PatpatView.rviews.setImageViewResource(R.id.patview01,
						R.drawable.fish_animation);
				
			}*/
			
			
			else {
				mViewContext.removeAnimatable(this);

				Log.d("addClickIntent", "removeAnimatable: " + this);

			}

		}

	}
	private class Lv3ClickAnim_right implements IAnimatable {
		private int spriteVib = 0;

		public boolean AnimationFinished() {
			return false;
		}

		public void Draw() {
			// Draw the brick at bottom

			Log.d("addClickIntent", "Draw_Lv3ClickAnim");
			Log.d("addClickIntent", "spriteVib : " + spriteVib);

			// RemoteViews rviews = new
			// RemoteViews(mViewContext.getPackageName(),
			// R.layout.coin_block_widget);

			// Log.e("addClickIntent","mViewContext.getPackageName() : "+mViewContext.getPackageName());

			if (spriteVib == 0) {
				PatpatView.rviews.setImageViewResource(R.id.patview01,
						R.drawable.fish_animation_left);
				/*
				 * PatpatView.rviews.setImageViewResource(R.id.patview02,
				 * R.drawable.fish_right08);
				 */
				spriteVib++;
			} else {
				mViewContext.removeAnimatable(this);
				Log.d("addClickIntent", "removeAnimatable: " + this);

			}

		}
	}

	/*
	 * private void animeRemove(IAnimatable animeObject) { if(animeSwitch){
	 * animeSwitch = false; mViewContext.removeAnimatable(animeObject);
	 * mViewContext.setState(new Lv3_1WaitState()); }else{
	 * mViewContext.removeAnimatable(animeObject); } }
	 */

	@Override
	public void OnHeadsetConnected(PatpatView viewContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnHeadsetDisconnected(PatpatView viewContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnClick_right(PatpatView patpatView) {
		// TODO Auto-generated method stub

	}

}
