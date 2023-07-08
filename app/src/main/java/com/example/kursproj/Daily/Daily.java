package com.example.kursproj.Daily;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kursproj.Daily.Adapters.AdapterDailyDay;
import com.example.kursproj.Daily.Adapters.AdapterDailyMouth;
import com.example.kursproj.Daily.Adapters.AdapterDailyWeek;
import com.example.kursproj.MainActivity;
import com.example.kursproj.Note.AllNotes;
import com.example.kursproj.R;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class Daily extends AppCompatActivity {

    private BroadcastReceiver minuteUpdateReceiver;
    private int counter=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (counter==0){
            counter=1;
        }
        setContentView(R.layout.activity_daily);
        ImageButton addNewNoteButton = findViewById(R.id.addNewNoteButton);
        ImageButton DayBut = findViewById(R.id.dayButton);
        ImageButton WeekBut = findViewById(R.id.weekButton);
        ImageButton MouthBut = findViewById(R.id.monthButton);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Drawable img = DayBut.getDrawable();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        img.setTint(Color.parseColor("#F4AE9E"));
        DayBut.setBackground(img);



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
                counter = 1;
                Drawable img1 = DayBut.getDrawable();
                Drawable img2 = WeekBut.getDrawable();
                Drawable img3 = MouthBut.getDrawable();
                img1.setTint(Color.parseColor("#F4AE9E"));
                img2.setTint(Color.parseColor("#FFFFFF"));
                img3.setTint(Color.parseColor("#FFFFFF"));
                DayBut.setBackground(img1);
                WeekBut.setBackground(img2);
                MouthBut.setBackground(img3);
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
                counter=7;
                Drawable img1 = DayBut.getDrawable();
                Drawable img2 = WeekBut.getDrawable();
                Drawable img3 = MouthBut.getDrawable();
                img1.setTint(Color.parseColor("#FFFFFF"));
                img2.setTint(Color.parseColor("#F4AE9E"));
                img3.setTint(Color.parseColor("#FFFFFF"));
                DayBut.setBackground(img1);
                WeekBut.setBackground(img2);
                MouthBut.setBackground(img3);

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
                counter=30;
                Drawable img1 = DayBut.getDrawable();
                Drawable img2 = WeekBut.getDrawable();
                Drawable img3 = MouthBut.getDrawable();
                img1.setTint(Color.parseColor("#FFFFFF"));
                img2.setTint(Color.parseColor("#FFFFFF"));
                img3.setTint(Color.parseColor("#F4AE9E"));
                DayBut.setBackground(img1);
                WeekBut.setBackground(img2);
                MouthBut.setBackground(img3);
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
    public void startMinuteUpdater() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        minuteUpdateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(counter==1){
                    ImageButton DayBut = findViewById(R.id.dayButton);
                    ImageButton WeekBut = findViewById(R.id.weekButton);
                    ImageButton MouthBut = findViewById(R.id.monthButton);
                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    Realm.init(getApplicationContext());
                    Realm realmDay = Realm.getDefaultInstance();
                    Drawable img1 = DayBut.getDrawable();
                    Drawable img2 = WeekBut.getDrawable();
                    Drawable img3 = MouthBut.getDrawable();
                    img1.setTint(Color.parseColor("#F4AE9E"));
                    img2.setTint(Color.parseColor("#FFFFFF"));
                    img3.setTint(Color.parseColor("#FFFFFF"));
                    DayBut.setBackground(img1);
                    WeekBut.setBackground(img2);
                    MouthBut.setBackground(img3);
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
                else if(counter==7)
                {
                    ImageButton DayBut = findViewById(R.id.dayButton);
                    ImageButton WeekBut = findViewById(R.id.weekButton);
                    ImageButton MouthBut = findViewById(R.id.monthButton);
                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    Realm.init(getApplicationContext());
                    Realm realmDay = Realm.getDefaultInstance();
                    Drawable img1 = DayBut.getDrawable();
                    Drawable img2 = WeekBut.getDrawable();
                    Drawable img3 = MouthBut.getDrawable();
                    img1.setTint(Color.parseColor("#FFFFFF"));
                    img2.setTint(Color.parseColor("#F4AE9E"));
                    img3.setTint(Color.parseColor("#FFFFFF"));
                    DayBut.setBackground(img1);
                    WeekBut.setBackground(img2);
                    MouthBut.setBackground(img3);

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
                else if(counter==30){
                    ImageButton DayBut = findViewById(R.id.dayButton);
                    ImageButton WeekBut = findViewById(R.id.weekButton);
                    ImageButton MouthBut = findViewById(R.id.monthButton);
                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    Realm.init(getApplicationContext());
                    Realm realmDay = Realm.getDefaultInstance();
                    Drawable img1 = DayBut.getDrawable();
                    Drawable img2 = WeekBut.getDrawable();
                    Drawable img3 = MouthBut.getDrawable();
                    img1.setTint(Color.parseColor("#FFFFFF"));
                    img2.setTint(Color.parseColor("#FFFFFF"));
                    img3.setTint(Color.parseColor("#F4AE9E"));
                    DayBut.setBackground(img1);
                    WeekBut.setBackground(img2);
                    MouthBut.setBackground(img3);
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
            }
        };

        registerReceiver(minuteUpdateReceiver, intentFilter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        startMinuteUpdater();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(minuteUpdateReceiver);
    }
}