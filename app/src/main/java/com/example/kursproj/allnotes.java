package com.example.kursproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import io.realm.Realm;

public class allnotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allnotes);

        ImageButton addNewNote = (ImageButton)findViewById(R.id.addNewNoteButton);

        addNewNote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                startActivity(new Intent(allnotes.this, noteview.class));
            }
        });
    }


}