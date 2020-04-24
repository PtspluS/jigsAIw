package com.example.opencv_test;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AssistantActivity extends AppCompatActivity {

    Project project;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.assistant_activity);

        final RecyclerView rv = findViewById(R.id.recyclerViewAssistant);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new AdapterAssistant(this ,project));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
