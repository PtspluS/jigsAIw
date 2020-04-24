package com.example.opencv_test;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class CreateProjectActivity extends AppCompatActivity {
    private int id;
    private String pathMainImage;
    private String[] pathImagePictures;

    private AsyncTaskImageAnalyse task;
    private Button btnNext;
    private ProgressBar bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createproject);

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

        bar = findViewById(R.id.progressBar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            bar.setMin(0);
            bar.setMax(pathImagePictures.length*2+2);
            bar.setProgress(0);
        }

        btnNext = findViewById(R.id.button_next_create_project);
        btnNext.setAlpha(0.2f);


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(task.getStatus() == AsyncTask.Status.RUNNING){
            task.cancel(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(task == null){
            task = new AsyncTaskImageAnalyse(this, btnNext,bar,pathMainImage,id);
            Project project = task.doInBackground(pathImagePictures);
        }
    }
}
