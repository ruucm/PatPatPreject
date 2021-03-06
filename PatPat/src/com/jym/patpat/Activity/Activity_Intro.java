package com.jym.patpat.Activity;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.jym.patpat.R;
import com.viewpagerindicator.IconPagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

public class Activity_Intro extends FragmentActivity {
	/** Called when the activity is first created. */

	TabHost mTabHost;
	ViewPager mViewPager;
	TabsAdapter mTabsAdapter;
	
	private static final int[] ICONS = new int[] {
        R.drawable.perm_group_banana,
        R.drawable.perm_group_heart,
        
    };
	

	
	private static Activity_Intro instance;
	public static 	Activity_Intro getInstance() {
        return instance;
    }
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;

		
		
//		dalvik.system.VMRuntime.getRuntime().setTargetHeapUtilization(0.7f);
		// for Activity anim
		this.overridePendingTransition(R.anim.start_enter, R.anim.start_exit);

		setContentView(R.layout.patpat_layout);
		

		// for adding listviewpager
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		mViewPager = (ViewPager) findViewById(R.id.pager);

		mTabsAdapter = new TabsAdapter(this, /* mTabHost, */mViewPager);

		mTabsAdapter.addTab(mTabHost.newTabSpec("deice condition")
				.setIndicator("Device Condition"), DeviceConditionPage.class,
				null);
		mTabsAdapter
				.addTab(mTabHost.newTabSpec("deice state").setIndicator(
						"Device State"), DeviceStatePage.class, null);

		if (savedInstanceState != null) {
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}

		// for tabindicator
		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(mViewPager);

	}

	public static class TabsAdapter extends FragmentPagerAdapter implements
			/* TabHost.OnTabChangeListener, */ViewPager.OnPageChangeListener,
			IconPagerAdapter {
		private final Context mContext;
		private final TabHost mTabHost;
		private final ViewPager mViewPager;
		private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

		static final class TabInfo {
			private final String tag;
			private final Class<?> clss;
			private final Bundle args;

			TabInfo(String _tag, Class<?> _class, Bundle _args) {
				tag = _tag;
				clss = _class;
				args = _args;
			}
		}

		static class DummyTabFactory implements TabHost.TabContentFactory {
			private final Context mContext;

			public DummyTabFactory(Context context) {
				mContext = context;
			}

			@Override
			public View createTabContent(String tag) {
				View v = new View(mContext);
				v.setMinimumWidth(0);
				v.setMinimumHeight(0);
				return v;
			}
		}

		public TabsAdapter(Activity_Intro activity_Intro, /* TabHost tabHost, */
				ViewPager pager) {
			super(activity_Intro.getSupportFragmentManager());
			mContext = activity_Intro;
			mTabHost = null;
			mViewPager = pager;
			// mTabHost.setOnTabChangedListener(this);
			mViewPager.setAdapter(this);
			// mViewPager.setOnPageChangeListener(this);
		}

		public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
			// tabSpec.setContent(new DummyTabFactory(mContext));
			// String tag = tabSpec.getTag();

			TabInfo info = new TabInfo("tag", clss, args);
			mTabs.add(info);
			// mTabHost.addTab(tabSpec);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

		@Override
		public Fragment getItem(int position) {
			TabInfo info = mTabs.get(position);
			return Fragment.instantiate(mContext, info.clss.getName(),
					info.args);
			// return null;
		}

		/*
		 * @Override public void onTabChanged(String tabId) { int position =
		 * mTabHost.getCurrentTab(); mViewPager.setCurrentItem(position); }
		 */

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
		}

		@Override
		public void onPageSelected(int position) {
			// Unfortunately when TabHost changes the current tab, it kindly
			// also takes care of putting focus on it when not in touch mode.
			// The jerk.
			// This hack tries to prevent this from pulling focus out of our
			// ViewPager.
			TabWidget widget = mTabHost.getTabWidget();
			int oldFocusability = widget.getDescendantFocusability();
			widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
			mTabHost.setCurrentTab(position);
			widget.setDescendantFocusability(oldFocusability);
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}

		@Override
		public int getIconResId(int index) {
			return ICONS[index];
		}
	}

}