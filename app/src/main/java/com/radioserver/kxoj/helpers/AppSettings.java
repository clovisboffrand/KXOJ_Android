package com.radioserver.kxoj.helpers;

import android.content.Context;

import com.radioserver.kxoj.R;

public class AppSettings {
    private static AppSettings sInstance;

    public static void init(Context context) {
        if (sInstance == null) {
            sInstance = new AppSettings(context);
            sInstance.loadDefaultChannel();
        }
    }

    public static AppSettings shared() {
        return sInstance;
    }

    private Context context;

    public Integer channelId;
    public String streamLink;
    public String feedLink;
    public String eventsLink;
    public int logo;
    public int defaultAlbum;

    public AppSettings(Context context) {
        this.context = context;
    }

    private void loadDefaultChannel() {
        loadFirstChannel();
    }

    public void loadFirstChannel() {
        channelId = 1;
        streamLink = context.getString(R.string.stream_link1);
        feedLink = context.getString(R.string.feed_link1);
        eventsLink = context.getString(R.string.events_link1);
        logo = R.drawable.img_logo1;
        defaultAlbum = R.drawable.kxojdefaultcover;
    }

    public void loadSecondChannel() {
        channelId = 2;
        streamLink = context.getString(R.string.stream_link2);
        feedLink = context.getString(R.string.feed_link2);
        eventsLink = context.getString(R.string.events_link2);
        logo = R.drawable.img_logo2;
        defaultAlbum = R.drawable.kxoj2defaultcover;
    }
}
