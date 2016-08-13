package com.radioserver.kxoj.screens;

import org.san.iphonestyle.CustomScreen;

import com.radioserver.kxoj.R;
import com.radioserver.kxoj.helpers.AppSettings;
import com.radioserver.kxoj.helpers.CommonUtils;
import com.radioserver.kxoj.models.RadioSong;
import com.radioserver.kxoj.services.RadioPlayerService;
import com.radioserver.kxoj.helpers.SharedAlgorithm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class RadioScreen extends CustomScreen implements OnClickListener, OnSeekBarChangeListener {

    ImageView btnStations, btnRecentPlaylist, btnPlay;
    ImageView ivAlbumCover;
    TextView tvAlbumTitle, tvAlbumArtist;
    private SeekBar skbVolume;

    public static final String ACTION_VOLUME_CHANGE = "android.media.VOLUME_CHANGED_ACTION";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().registerReceiver(mVolumeChangeReceiver, new IntentFilter(ACTION_VOLUME_CHANGE));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen_radio, container, false);

        this.initComponents(view);
        this.setListeners();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        RadioPlayerService.getInstance().executeGetRecentSongs();
        getActivity().registerReceiver(mReceiver, makeIntentFilter());
        setupSongInfo();
    }

    private IntentFilter makeIntentFilter() {
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(RadioPlayerService.ACTION_PLAYER_STATE_CHANGE);
        iFilter.addAction(RadioPlayerService.ACTION_SONG_LIST_READY);
        return iFilter;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(mReceiver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mVolumeChangeReceiver);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initComponents(View container) {
        btnStations = (ImageView) container.findViewById(R.id.btnStations);
        btnRecentPlaylist = (ImageView) container.findViewById(R.id.btnRecentPlaylist);
        btnPlay = (ImageView) container.findViewById(R.id.btnPlay);
        ivAlbumCover = (ImageView) container.findViewById(R.id.ivAlbumCover);
        tvAlbumTitle = (TextView) container.findViewById(R.id.tvAlbumTitle);
        tvAlbumArtist = (TextView) container.findViewById(R.id.tvAlbumArtist);
        skbVolume = (SeekBar) container.findViewById(R.id.skbVolume);

        AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        skbVolume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        skbVolume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        SharedAlgorithm.setupAdView((WebView) container.findViewById(R.id.adView), getActivity());
    }

    @Override
    protected void setListeners() {
        btnStations.setOnClickListener(this);
        btnRecentPlaylist.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        skbVolume.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay:
                getActivity().sendBroadcast(new Intent(RadioPlayerService.ACTION_PLAY));
                break;
            case R.id.btnRecentPlaylist:
                startActivity(new Intent(getActivity(), RecentPlaylistActivity.class));
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_SHOW_UI);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (RadioPlayerService.ACTION_SONG_LIST_READY.equalsIgnoreCase(intent.getAction())) {
                setupSongInfo();
            }
            if (RadioPlayerService.ACTION_PLAYER_STATE_CHANGE.equalsIgnoreCase(intent.getAction())) {
                if (RadioPlayerService.getInstance().isPlaying()) {
                    btnPlay.setBackgroundResource(R.drawable.btn_stop);
                } else {
                    btnPlay.setBackgroundResource(R.drawable.btn_play);
                }
            }
        }
    };

    BroadcastReceiver mVolumeChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION_VOLUME_CHANGE.equalsIgnoreCase(intent.getAction())) {
                AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                if (skbVolume != null) {
                    int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    if (skbVolume.getProgress() != currVolume)
                        skbVolume.setProgress(currVolume);
                }
            }
        }
    };

    private void setupSongInfo() {
        RadioSong currentSong = RadioPlayerService.getInstance().getCurrentSong();
        if (currentSong != null) {
            tvAlbumTitle.setText(currentSong.getTitle());
            tvAlbumArtist.setText(currentSong.getDescription());
            CommonUtils.loadImage(ivAlbumCover, currentSong.getThumbnailUrl(), AppSettings.shared().defaultAlbum);
        }
    }
}