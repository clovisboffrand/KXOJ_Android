package com.radioserver.kxoj.screens;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.radioserver.kxoj.R;
import com.radioserver.kxoj.helpers.AppSettings;
import com.radioserver.kxoj.services.RadioPlayerService;

public class StationsActivity extends Activity implements View.OnClickListener {

    TextView btnChannel1, btnChannel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_stations);

        // Map view elements to class members.
        btnChannel1 = (TextView) findViewById(R.id.btnChannel1);
        btnChannel2 = (TextView) findViewById(R.id.btnChannel2);

        // Wrap event handlers to view elements.
        btnChannel1.setOnClickListener(this);
        btnChannel2.setOnClickListener(this);

        updateChannelInfo();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btnChannel1.getId()) {
            AppSettings.shared().loadFirstChannel();
        } else if (view.getId() == btnChannel2.getId()) {
            AppSettings.shared().loadSecondChannel();
        }
        RadioPlayerService.getInstance().reloadStream();
        finish();
    }

    private void updateChannelInfo() {
        if (AppSettings.shared().channelId == 1) {
            btnChannel1.setEnabled(false);
            btnChannel1.setText(R.string.Playing);
            btnChannel2.setEnabled(true);
            btnChannel2.setText(R.string.Play);
        } else if (AppSettings.shared().channelId == 2) {
            btnChannel1.setEnabled(true);
            btnChannel1.setText(R.string.Play);
            btnChannel2.setEnabled(false);
            btnChannel2.setText(R.string.Playing);
        }
    }
}
