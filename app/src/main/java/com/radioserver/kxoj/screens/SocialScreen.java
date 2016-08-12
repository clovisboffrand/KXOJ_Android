package com.radioserver.kxoj.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.radioserver.kxoj.R;
import com.radioserver.kxoj.helpers.AppSettings;
import com.radioserver.kxoj.helpers.SharedAlgorithm;

import org.san.iphonestyle.CustomScreen;

public class SocialScreen extends CustomScreen implements OnClickListener {

    ImageView btnFacebook, btnTwitter, btnInstagram, btnYoutube, btnWebsite;
    ImageView btnPhone, btnEmail, btnMessage, btnVine;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen_social, container, false);

        this.initComponents(view);
        this.setListeners();

        return view;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initComponents(View container) {
        SharedAlgorithm.setupAdView((WebView) container.findViewById(R.id.adView), getActivity());

        btnFacebook = (ImageView) container.findViewById(R.id.btnFacebook);
        btnTwitter = (ImageView) container.findViewById(R.id.btnTwitter);
        btnInstagram = (ImageView) container.findViewById(R.id.btnInstagram);
        btnYoutube = (ImageView) container.findViewById(R.id.btnYoutube);
        btnWebsite = (ImageView) container.findViewById(R.id.btnWebsite);
        btnPhone = (ImageView) container.findViewById(R.id.btnPhone);
        btnEmail = (ImageView) container.findViewById(R.id.btnEmail);
        btnMessage = (ImageView) container.findViewById(R.id.btnMessage);
        btnVine = (ImageView) container.findViewById(R.id.btnVine);
    }

    @Override
    protected void setListeners() {
        btnFacebook.setOnClickListener(this);
        btnTwitter.setOnClickListener(this);
        btnInstagram.setOnClickListener(this);
        btnYoutube.setOnClickListener(this);
        btnWebsite.setOnClickListener(this);
        btnPhone.setOnClickListener(this);
        btnEmail.setOnClickListener(this);
        btnMessage.setOnClickListener(this);
        btnVine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AppSettings settings = AppSettings.shared();
        switch (v.getId()) {
            case R.id.btnFacebook:
                goWebsite(settings.facebookLink);
                break;
            case R.id.btnTwitter:
                goWebsite(settings.twitterLink);
                break;
            case R.id.btnInstagram:
                goWebsite(settings.instagramLink);
                break;
            case R.id.btnYoutube:
                goWebsite(settings.youtubeLink);
                break;
            case R.id.btnWebsite:
                goWebsite(settings.websiteLink);
                break;
            case R.id.btnPhone:
                makeACall(settings.phoneNumber);
                break;
            case R.id.btnEmail:
                sendEmail(settings.emailAddress);
                break;
            case R.id.btnMessage:
                sendSMS(settings.smsNumber);
                break;
            case R.id.btnVine:
                goWebsite(settings.vineLink);
                break;
        }
    }

    private void goWebsite(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void makeACall(String number) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);

        callIntent.setData(Uri.parse("tel://" + number));
        this.startActivityForResult(callIntent, 911);
    }

    private void sendEmail(String emailAddress) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", emailAddress, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getActivity().getString(R.string.app_name) + " Email");
        startActivity(Intent.createChooser(emailIntent, "Send Email"));
    }

    private void sendSMS(String number) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
    }
}