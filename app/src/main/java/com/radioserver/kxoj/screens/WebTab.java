package com.radioserver.kxoj.screens;

import android.os.Bundle;

import com.radioserver.kxoj.R;
import com.radioserver.kxoj.helpers.AppSettings;

import org.san.iphonestyle.CustomTab;

/**
 * Custom class which is the container of Web Screen
 *
 * @see WebScreen
 */
public class WebTab extends CustomTab {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custom_tab);
    }

    @Override
    protected void setRootScreen() {
        WebScreen webScreen = new WebScreen();
        Bundle bundle = new Bundle();
        bundle.putString("URL", AppSettings.shared().eventsLink);
        webScreen.setArguments(bundle);
        this.setScreen(getSupportFragmentManager(), webScreen);
    }
}
