package com.example.animtest;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	public Context ctx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ctx = this;
		
		ImageView img = (ImageView) findViewById(R.id.img);
		
		
//		img.setBackgroundResource(R.drawable.spin_animation);
		
		
		img.setImageResource(R.drawable.spin_animation);
		
		
		  final AnimationDrawable frameAnimation = (AnimationDrawable) img.getDrawable();
		
		 img.setOnClickListener(new OnClickListener() {
			
			
			
			@Override
			public void onClick(View v) {
				
				frameAnimation.start();
				Toast.makeText(ctx, "isRunning() : "+frameAnimation.isRunning(), Toast.LENGTH_LONG).show();
			}
		});
		 
		 img.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View arg0) {
				
				frameAnimation.stop();
				Toast.makeText(ctx, "isRunning() : "+frameAnimation.isRunning(), Toast.LENGTH_LONG).show();

				return true;
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}