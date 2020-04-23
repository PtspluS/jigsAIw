package com.example.opencv_test;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.File;
import java.util.ArrayList;

public class AsyncTaskImageAnalyse extends AsyncTask<String, Double, Project> {

    private int id;
    private String globalImagePath;
    private String globalImagePathAnalysed;
    private Double progress0;
    private Double progress1;
    private Activity activity;

    private Project project;

    private Button btnNext;
    private ProgressBar bar;
    private ArrayList<Thread> threads;
    private ArrayList<String> paths;
    private ArrayList<Piece> pieces;

    public AsyncTaskImageAnalyse(Activity act,Button btn, ProgressBar bar, String path, int id){

        this.activity = act;

        this.btnNext = btn;
        this.bar = bar;
        this.globalImagePath = path;
        this.progress0 = 0.0;
        this.progress1 = 0.0;
        this.id = id;

        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/jigsAIw/id_"+id+"/modified_pieces/");
        if(!dir.exists()){
            dir.mkdir();
        }
    }

    @Override
    protected Project doInBackground(String... arrayLists) {

        // if there is a global image
        if(globalImagePath!=null){
            AnalyseImageThread t = new AnalyseImageThread();
            globalImagePathAnalysed = t.run(this.globalImagePath, true, id).get(0);
            progress0 += 0.5;
            progress1++;
        }
        // we create thread to analyse each image
        for(String path: arrayLists) {
            AnalyseImageThread t = new AnalyseImageThread();
            ArrayList<String> p = t.run(path, false, id);
            for(String pt : p) {
                paths.add(pt);
            }
            progress0 += 0.5;
            progress1 = progress1+1%arrayLists.length;
            publishProgress(progress0, progress1);
        }
        // for each picture created
        for (String p : paths) {
            Piece piece = new Piece(p);
            pieces.add(piece);
            progress0 += 0.5/paths.size();
            progress1 = progress1+1%arrayLists.length;
            publishProgress(progress0, progress1);
        }

        Project project = new Project(this.id, this.globalImagePathAnalysed, pieces);

        progress0 = (double)this.bar.getMax();
        publishProgress(progress0, progress1);

        this.project = project;

        return project;
    }

    @Override
    protected void onProgressUpdate(Double... values) {
        super.onProgressUpdate(values);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.bar.setProgress(values[0].intValue(), true);
            this.bar.setSecondaryProgress(values[1].intValue());
        }
    }

    @Override
    protected void onPostExecute(final Project project) {
        super.onPostExecute(project);
        btnNext.setAlpha(1.0f);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AssistantActivity.class);
                intent.putExtra("project", project);

                activity.startActivity(intent);
            }
        });
    }

    protected void pause(){

    }

}
