package com.example.stackviewexam;

import java.util.ArrayList;
import java.util.List;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private final ImageDownloader imageDownloader = new ImageDownloader();
    private List<Buzz> mBuzzes = new ArrayList<Buzz>();
    private Context mContext;
    private int mAppWidgetId;

    public StackRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    public void onCreate() {
    }

    public void onDestroy() {
        mBuzzes.clear();
    }

    public int getCount() {
        return mBuzzes.size();
    }

    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.stackwidget_item);
        
        if (position <= getCount()) {
            Buzz buzz = mBuzzes.get(position);
            
            if (buzz.picture != null) {
                try {
                    Bitmap picture = imageDownloader.downloadBitmap(buzz.picture, 100, 100, 70);
                    rv.setImageViewBitmap(R.id.stackWidgetItemPicture, picture);
                }
                catch(Exception e) {
                    Logging.e("Error reading picture file", e);
                }
            }
            
            if (!buzz.username.isEmpty()) {
                rv.setTextViewText(R.id.stackWidgetItemUsername, buzz.username);
            }
            rv.setTextViewText(R.id.stackWidgetItemContent, Html.fromHtml(buzz.content));
            
            // store the buzz ID in the extras so the main activity can use it
            Bundle extras = new Bundle();
            extras.putString(HoneybuzzListActivity.EXTRA_ID, buzz.id);
            Intent fillInIntent = new Intent();
            fillInIntent.putExtras(extras);
            rv.setOnClickFillInIntent(R.id.stackWidgetItem, fillInIntent);
        }
        
        return rv;
    }

    public RemoteViews getLoadingView() {
        return null;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public long getItemId(int position) {
        return position;
    }

    public boolean hasStableIds() {
        return true;
    }

    public void onDataSetChanged() {
        mBuzzes = Buzz.getBuzzes(HoneybuzzApplication.buzz, mContext);
    }
}