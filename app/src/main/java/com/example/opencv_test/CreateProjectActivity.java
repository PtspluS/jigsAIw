package com.example.opencv_test;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreateProjectActivity extends AppCompatActivity {
    private int id;
    private String pathMainImage;
    private String[] pathImagePictures;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobileAds.initialize(this,new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adViewBannerCreateProject);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        AdView nAdView = findViewById(R.id.adViewNatifCreateProject);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        nAdView.loadAd(adRequest1);

        id = getIntent().getIntExtra("ID",0);
        pathMainImage = getIntent().getStringExtra("pathMainPicture");
        pathImagePictures = getIntent().getStringArrayExtra("pathImagePieces");


        Toast.makeText(this, R.string.loading, Toast.LENGTH_LONG).show();

        ProgressBar bar = findViewById(R.id.progressBarCircle);

    }
}
