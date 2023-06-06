package com.example.kursproj.Daily;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.kursproj.Note.MyAdapter;
import com.example.kursproj.Note.Note;
import com.example.kursproj.R;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class Daily extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        ImageButton addNewNoteButton = (ImageButton)findViewById(R.id.addNewNoteButton);

        addNewNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(Daily.this, DailyNoteView.class));
            }
        });

        Realm.init(getApplicationContext());
        Realm realmDay = Realm.getDefaultInstance();

        RealmResults<DailyNoteDay> dailyList = realmDay.where(DailyNoteDay.class).findAll();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterDailyDay myAdapter = new AdapterDailyDay(getApplicationContext(),dailyList);
        recyclerView.setAdapter(myAdapter);

        dailyList.addChangeListener(new RealmChangeListener<RealmResults<DailyNoteDay>>() {

            @Override
            public void onChange(RealmResults<DailyNoteDay> notes) {
                myAdapter.notifyDataSetChanged();
            }
        });

    }
}