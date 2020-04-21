package com.example.opencv_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.File;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        File dir = new File(Environment.getExternalStorageDirectory()+"/jigsAIw/");

        if(!dir.exists()){
            dir.mkdir();
        }

        MobileAds.initialize(this,new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adViewMain);
        //mAdView.setAdSize(AdSize.BANNER);
        //mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        Button btnNewProject = findViewById(R.id.buttonNewProject);
        Button btnOpenProject = findViewById(R.id.buttonOpenProject);
        Button btnManageProjects = findViewById(R.id.buttonManageProjects);

        btnNewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                intent.putExtra("General Map Picture", true);
                // create a id to be sure that all the img for the same project start with the same id
                // create repo only for the project
                File dir1;
                int id = 0;
                boolean newCreated = false;
                do{
                    Random r = new Random();
                    id = r.nextInt(10000);
                    dir1 = new File(Environment.getExternalStorageDirectory()+"/jigsAIw/id_"+id);

                    if(!dir1.exists()) {
                        dir1.mkdir();
                        newCreated = true;
                    }

                } while (!newCreated);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        btnManageProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManageActivity.class);
                startActivity(intent);
            }
        });
    }
}
