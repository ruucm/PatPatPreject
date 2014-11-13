package ji.ruucm.android.vertical_view.fragment;

import ji.ruucm.android.chat.R;
import ji.ruucm.android.vertical_view.quickaction.ActionItem;
import ji.ruucm.android.vertical_view.quickaction.QuickAction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class Frag01 extends Fragment {

	
	//for hide on asynctask
	static QuickAction quickAction;
	
	
	static Frag01 instance;
	
	
	//action id
		private static final int ID_UP     = 1;
		private static final int ID_DOWN   = 2;
		private static final int ID_SEARCH = 3;
		private static final int ID_INFO   = 4;
		private static final int ID_ERASE  = 5;	
		private static final int ID_OK     = 6;
		
		private static final int ID_TXTapple     = 7;
		private static final int ID_TXTpineapple     = 8;
		private static final int ID_TXTorange    = 9;
		private static final int ID_TXTwatermelon     = 10;
		private static final int ID_TXTbanana     = 11;

	
	
	
	public static View v;


	
	
	
/*	  public void onResume() {
	        super.onResume();
	    //SET FEATURE FROM INSIDE ACTIVITY
	        CopyOfMainActivity.instance.getWindow().
	               setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mytitle);
	        Log.d("Frag01","setFeatureInt");
	    }
*/
	
	static Frag01 getInstance(){
		return instance;
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		instance = this;
		
		
//		ActionItem nextItem 	= new ActionItem(ID_DOWN, "Next", getResources().getDrawable(R.drawable.menu_down_arrow));
//		ActionItem prevItem 	= new ActionItem(ID_UP, "Prev", getResources().getDrawable(R.drawable.menu_up_arrow));
//        ActionItem searchItem 	= new ActionItem(ID_SEARCH, "Find", getResources().getDrawable(R.drawable.menu_search));
//        ActionItem infoItem 	= new ActionItem(ID_INFO, "Info", getResources().getDrawable(R.drawable.menu_info));
//        ActionItem eraseItem 	= new ActionItem(ID_ERASE, "Clear", getResources().getDrawable(R.drawable.menu_eraser));
//        ActionItem okItem 		= new ActionItem(ID_OK, "OK", getResources().getDrawable(R.drawable.menu_ok));
        

		
		//create QuickAction. Use QuickAction.VERTICAL or QuickAction.HORIZONTAL param to define layout 
	    //orientation
//		final QuickAction quickAction = new QuickAction(getActivity(), QuickAction.VERTICAL);

		
		
		//add action items into QuickAction
//        quickAction.addActionItem(nextItem);
//		quickAction.addActionItem(prevItem);
//        quickAction.addActionItem(searchItem);
//        quickAction.addActionItem(infoItem);
//        quickAction.addActionItem(eraseItem);
//        quickAction.addActionItem(okItem);
		
        
        
        //add text into QuickAction
         
//        quickAction.addActionItem(txtItemApple);
        

		//Start service after Frag01 created
//		MainActivity.instance.startService(MainActivity.nintent);
		
		
		
		v = inflater.inflate(R.layout.frag01, container, false);

		
		
		
		// setListener
		ImageView apple = (ImageView) v.findViewById(R.id.object_apple);

		apple.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				 quickAction = new QuickAction(getActivity(),
				 QuickAction.VERTICAL);
				ActionItem txtItemApple = new ActionItem(ID_TXTapple,
						"홍익대학교 근처", null);
				quickAction.addActionItem(txtItemApple);

				quickAction.show(v);
				
				

			}
		});

		apple.setOnLongClickListener(new View.OnLongClickListener() {
			public boolean onLongClick(View v) {
				Toast.makeText(getActivity(), "apple...", Toast.LENGTH_SHORT)
						.show();
				return true;
			}
		});

		ImageView pineapple = (ImageView) v.findViewById(R.id.object_pineapple);

		pineapple.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				quickAction = new QuickAction(getActivity(), QuickAction.VERTICAL);
		        ActionItem txtItemPineApple		= new ActionItem(ID_TXTpineapple, "수유역 엔제리너스", null);
				quickAction.addActionItem(txtItemPineApple);
				quickAction.show(v);

			}
		});

		pineapple.setOnLongClickListener(new View.OnLongClickListener() {
			public boolean onLongClick(View v) {
				Toast.makeText(getActivity(), "pineapple...",
						Toast.LENGTH_SHORT).show();
				return true;
			}
		});

		ImageView orange = (ImageView) v.findViewById(R.id.object_orange);

		orange.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				quickAction = new QuickAction(getActivity(), QuickAction.VERTICAL);
		        ActionItem txtItemOrange		= new ActionItem(ID_TXTorange, "인수중 근처 분식점", null);
		        quickAction.addActionItem(txtItemOrange);
				quickAction.show(v);

			}
		});

		orange.setOnLongClickListener(new View.OnLongClickListener() {
			public boolean onLongClick(View v) {
				Toast.makeText(getActivity(), "apple...", Toast.LENGTH_SHORT)
						.show();
				return true;
			}
		});

		ImageView watermelon = (ImageView) v
				.findViewById(R.id.object_watermelon);

		watermelon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				quickAction = new QuickAction(getActivity(), QuickAction.VERTICAL);
		        ActionItem txtItemWaterMelon		= new ActionItem(ID_TXTwatermelon, "19:25 집 도착", null);
		        quickAction.addActionItem(txtItemWaterMelon);
				quickAction.show(v);

			}
		});

		watermelon.setOnLongClickListener(new View.OnLongClickListener() {
			public boolean onLongClick(View v) {
				Toast.makeText(getActivity(), "apple...", Toast.LENGTH_SHORT)
						.show();
				return true;
			}
		});

		ImageView banana = (ImageView) v.findViewById(R.id.object_banana);

		banana.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				quickAction = new QuickAction(getActivity(), QuickAction.VERTICAL);
		        ActionItem txtItemBanana		= new ActionItem(ID_TXTbanana, "17:00 집 도착", null);
		        quickAction.addActionItem(txtItemBanana);
				quickAction.show(v);

			}
		});

		banana.setOnLongClickListener(new View.OnLongClickListener() {
			public boolean onLongClick(View v) {
				Toast.makeText(getActivity(), "apple...", Toast.LENGTH_SHORT)
						.show();
				return true;
			}
		});
		
		
		
		AsyncTask01 changebackground = new AsyncTask01();
 		changebackground.execute("");
		
		
		
		
		

		return v;

	}
}

