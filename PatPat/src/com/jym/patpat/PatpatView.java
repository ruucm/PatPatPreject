package com.jym.patpat;

import java.util.HashSet;
import java.util.Set;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;

public class PatpatView extends Activity{

	final public static String INTENT_CLICK_HEAD = "click.head.jym.id.%d";
	final public static String INTENT_CLICK_BODY = "click.body.jym.id.%d";
	final public static String INTENT_CLICK_LEG = "click.leg.jym.id.%d";

	ImageView imageviews;

	public static RemoteViews rviews;
	public static AnimationDrawable frameAnimation;

	private static final int REFRESH_RATE = 0;
	public static Context Context = null;

	private volatile Set<IAnimatable> Children;
	private static PatpatView instance;

	private float density;
	private int cheight, cwidth;
	private long RedrawMillis = 0;
	public static int mWidgetId;

	//for evolve
	public static long second = 0;



	//static variables
	public static IPatpatViewState state;

	static boolean init = false;
	public static boolean lv0_1;
	public static boolean lv0_2;
	public static  boolean lv1 ;
	public static  boolean lv2 ;
	public static  boolean lv3_1 ;

	public static  boolean stateNum ;

	public static int CliCountInit = 0 ;
	public static int CliCount0_1 = 0 ;
	public static int CliCount0_2 = 0;
	public static int CliCount1 = 0;
	public static int CliCount2 = 0;

	public PatpatView(Context context, int widgetId) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);

		density = metrics.density;
		cwidth = (int) (233* metrics.density);
		cheight = (int) (197* metrics.density);

		Children = new HashSet<IAnimatable>();
		mWidgetId = widgetId;

		//context for toast
		Context = context;


		Log.v("stop_double_Draw","Draw_setState_PatpatView");

		setState(new Lv3_1State(this));
	}

	public static PatpatView getInstance() {
		return instance;
	}

	public static Context getContext() {
		return (PatpatWidgetApp.getApplication());
	}

	public float getDensity() {
		return density;
	}

	public int getmWidgetId() {
		return mWidgetId;
	}

	public void OnClick() {
		state.OnClick(this);
	}

	public void OnClickBody() {
		state.OnClickBody(this);
	}
	
	public void OnClickLeg() {
		state.OnClickLeg(this);
	}

	public void OnOften() {
		state.OnOften(this);
	}

	public void OnEvolve() {
		state.OnEvolve(this);
	}

	public void OnHeadsetConnected() {
		state.OnHeadsetConnected(this);
		Log.d("HEADSET", "Headset Connected");
	}

	public void OnHeadsetDisconnected() {
	}

	public void Redraw(Context context) {
		Log.d("animCount","Redraw");

		rviews = new RemoteViews(context.getPackageName(), R.layout.patpat_widget);

		//state drawing
		state.Draw(this);

		IAnimatable[] child = new IAnimatable[Children.size()];
		Children.toArray(child);

		for (int i = 0; i < child.length; i++) {
			child[i].Draw();
			if (child[i].AnimationFinished())
				Children.remove(child[i]);
		}

		Log.i("refreshing_RemoteView","End_child.draw");

		updateClickIntent(rviews);
		AppWidgetManager.getInstance(context).updateAppWidget(mWidgetId, rviews);

		Log.i("refreshing_RemoteView","updateAppWidget(mWidgetId, rviews)");

		RedrawMillis = SystemClock.uptimeMillis();

		Log.i("draw_Speeding","Redraw, RedrawMillis : "+RedrawMillis);	
	}

	void scheduleRedraw() {
		Log.v("animCount","scheduleRedraw, RedrawMillis : "+RedrawMillis);
		Log.i("draw_Speeding","scheduleRedraw, SystemClock.uptimeMillis() : "+SystemClock.uptimeMillis());

		long nextRedraw = RedrawMillis + REFRESH_RATE;

		nextRedraw = nextRedraw > SystemClock.uptimeMillis() ? nextRedraw :
			SystemClock.uptimeMillis() + REFRESH_RATE;

		scheduleRedrawAt(nextRedraw);
	}

	private void scheduleRedrawAt(long timeMillis) {

		Log.i("animCount","scheduleRedrawAt, nextRedraw : "+timeMillis);
		(new Handler()).postAtTime(new Runnable() {
			public void run() {
				Redraw(PatpatWidgetApp.getApplication());
			}
		}, timeMillis);

		if (state.NeedRedraw() || Children.size() > 0){
			Log.d("animCount","Children.size() : "+Children.size());
			Redraw(Context);
		}
	}

	public synchronized void addAnimatable(IAnimatable child)
	{
		Children.add(child);
		scheduleRedraw();
	}

	public synchronized void removeAnimatable(IAnimatable child)
	{
		Children.remove(child);
	}

	public  void setState(IPatpatViewState newState) {
		Log.d("addClickIntent","setState : "+newState);

		state = newState;
		scheduleRedraw();
	}	

	public IPatpatViewState getState(){
		return state;
	}

	private void updateClickIntent(RemoteViews rviews)
	{
		Log.d("Seperate_ClickIntent","rviews.getLayoutId(); : "+rviews.getLayoutId());
		PendingIntent pi;

		// Insert head intent
		Intent headIntent = new Intent(String.format(INTENT_CLICK_HEAD, mWidgetId));
		headIntent.setClass(getContext(), PatpatWidgetProvider.class);
		headIntent.putExtra("widgetId", mWidgetId);
		pi = PendingIntent.getBroadcast(getContext(), 0, headIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		rviews.setOnClickPendingIntent(R.id.patview01, pi);
		rviews.setOnClickPendingIntent(R.id.girl_head, pi);

		// Insert body intent
		Intent bodyIntent = new Intent(String.format(INTENT_CLICK_BODY, mWidgetId));
		bodyIntent.setClass(getContext(), PatpatWidgetProvider.class);
		bodyIntent.putExtra("widgetId", mWidgetId);
		pi = PendingIntent.getBroadcast(getContext(), 0, bodyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		rviews.setOnClickPendingIntent(R.id.patview01, pi);
		rviews.setOnClickPendingIntent(R.id.girl_body, pi);

		// Insert body intent
		Intent legIntent = new Intent(String.format(INTENT_CLICK_LEG, mWidgetId));
		legIntent.setClass(getContext(), PatpatWidgetProvider.class);
		legIntent.putExtra("widgetId", mWidgetId);
		pi = PendingIntent.getBroadcast(getContext(), 0, legIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		rviews.setOnClickPendingIntent(R.id.patview01, pi);
		rviews.setOnClickPendingIntent(R.id.girl_leg, pi);
	}

	public static Bitmap drawableToBitmap (Drawable drawable) {
		if (drawable instanceof BitmapDrawable) {
			return ((BitmapDrawable)drawable).getBitmap();
		}

		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		drawable.draw(canvas);

		return bitmap;
	}	
}