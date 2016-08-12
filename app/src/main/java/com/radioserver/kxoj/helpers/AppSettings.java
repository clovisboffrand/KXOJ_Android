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

    public String facebookLink;
    public String twitterLink;
    public String instagramLink;
    public String youtubeLink;
    public String websiteLink;
    public String phoneNumber;
    public String emailAddress;
    public String smsNumber;
    public String vineLink;

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

        facebookLink = context.getString(R.string.facebook_link1);
        twitterLink = context.getString(R.string.twitter_link1);
        instagramLink = context.getString(R.string.instagram_link1);
        youtubeLink = context.getString(R.string.youtube_link1);
        websiteLink = context.getString(R.string.website_link1);
        phoneNumber = context.getString(R.string.phone_number1);
        emailAddress = context.getString(R.string.email_address1);
        smsNumber = context.getString(R.string.sms_number1);
        vineLink = context.getString(R.string.vine_link1);
    }

    public void loadSecondChannel() {
        channelId = 2;
        streamLink = context.getString(R.string.stream_link2);
        feedLink = context.getString(R.string.feed_link2);
        eventsLink = context.getString(R.string.events_link2);
        logo = R.drawable.img_logo2;
        defaultAlbum = R.drawable.kxoj2defaultcover;

        facebookLink = context.getString(R.string.facebook_link2);
        twitterLink = context.getString(R.string.twitter_link2);
        instagramLink = context.getString(R.string.instagram_link2);
        youtubeLink = context.getString(R.string.youtube_link2);
        websiteLink = context.getString(R.string.website_link2);
        phoneNumber = context.getString(R.string.phone_number2);
        emailAddress = context.getString(R.string.email_address2);
        smsNumber = context.getString(R.string.sms_number2);
        vineLink = context.getString(R.string.vine_link2);
    }
}
