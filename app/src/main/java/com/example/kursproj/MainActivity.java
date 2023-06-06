package com.example.kursproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.kursproj.Daily.Daily;
import com.example.kursproj.MainPageWithoutNotes.Information;
import com.example.kursproj.Note.AllNotes;
import com.example.kursproj.MainPageWithoutNotes.settings;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton goToAllNotes = (ImageButton)findViewById(R.id.notesButton);
        ImageButton goToSettings = (ImageButton)findViewById(R.id.settingsButton);
        ImageButton goToInfo = (ImageButton)findViewById(R.id.infoButton);
        ImageButton goToDiary = (ImageButton)findViewById(R.id.dailyButton);

        goToAllNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, AllNotes.class));
            }
        });

        goToSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, settings.class));
            }
        });

        goToInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, Information.class));
            }
        });

        goToDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, Daily.class));
            }
        });


    }


}