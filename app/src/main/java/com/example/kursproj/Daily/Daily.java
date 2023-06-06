package com.example.kursproj.Daily;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.kursproj.Daily.Adapters.AdapterDailyDay;
import com.example.kursproj.Daily.Adapters.AdapterDailyMouth;
import com.example.kursproj.Daily.Adapters.AdapterDailyWeek;
import com.example.kursproj.R;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class Daily extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        ImageButton addNewNoteButton = findViewById(R.id.addNewNoteButton);

        ImageButton DayBut =findViewById(R.id.dayButton);
        ImageButton WeekBut =findViewById(R.id.weekButton);
        ImageButton MouthBut =findViewById(R.id.monthButton);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addNewNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(Daily.this, DailyNoteView.class));
            }
        });

        Realm.init(getApplicationContext());
        Realm realmDay = Realm.getDefaultInstance();

        RealmResults<DailyNoteDay> dailyList = realmDay.where(DailyNoteDay.class).findAll();

        AdapterDailyDay myAdapter = new AdapterDailyDay(getApplicationContext(),dailyList);
        recyclerView.setAdapter(myAdapter);

        dailyList.addChangeListener(new RealmChangeListener<RealmResults<DailyNoteDay>>() {

            @Override
            public void onChange(RealmResults<DailyNoteDay> notes) {
                myAdapter.notifyDataSetChanged();
            }
        });


        DayBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RealmResults<DailyNoteDay> dailyList = realmDay.where(DailyNoteDay.class).findAll();

                AdapterDailyDay myAdapter = new AdapterDailyDay(getApplicationContext(),dailyList);
                recyclerView.setAdapter(myAdapter);

                dailyList.addChangeListener(new RealmChangeListener<RealmResults<DailyNoteDay>>() {

                    @Override
                    public void onChange(RealmResults<DailyNoteDay> notes) {
                        myAdapter.notifyDataSetChanged();
                    }
                });
            }
        });


        WeekBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RealmResults<DailyNoteWeek> dailyList = realmDay.where(DailyNoteWeek.class).findAll();

                AdapterDailyWeek myAdapter = new AdapterDailyWeek(getApplicationContext(),dailyList);
                recyclerView.setAdapter(myAdapter);

                dailyList.addChangeListener(new RealmChangeListener<RealmResults<DailyNoteWeek>>() {

                    @Override
                    public void onChange(RealmResults<DailyNoteWeek> notes) {
                        myAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        MouthBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RealmResults<DailyNoteMouth> dailyList = realmDay.where(DailyNoteMouth.class).findAll();

                AdapterDailyMouth myAdapter = new AdapterDailyMouth(getApplicationContext(),dailyList);
                recyclerView.setAdapter(myAdapter);

                dailyList.addChangeListener(new RealmChangeListener<RealmResults<DailyNoteMouth>>() {

                    @Override
                    public void onChange(RealmResults<DailyNoteMouth> notes) {
                        myAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}