package com.example.kursproj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class AllNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allnotes);

        ImageButton addNewNote = (ImageButton)findViewById(R.id.addNewNoteButton);

        addNewNote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                startActivity(new Intent(AllNotes.this, NoteView.class));
            }
        });

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Note> notesList = realm.where(Note.class).findAll();

        RecyclerView recyclerView = findViewById(R.id.rycView);
        recyclerView.(new LinearLayoutManager(this));
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(),notesList);
        recyclerView.setAdapter(myAdapter);

        notesList.addChangeListener(new RealmChangeListener<RealmResults<Note>>() {
            @Override
            public void onChange(RealmResults<Note> notes) {
                myAdapter.notifyDataSetChanged();
            }
        });
    }


}