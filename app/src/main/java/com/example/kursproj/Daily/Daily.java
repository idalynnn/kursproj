package com.example.kursproj.Daily;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

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




        Drawable img = DayBut.getDrawable();
        img.setTint(Color.parseColor("#ffe4e1"));
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

                Drawable img1 = DayBut.getDrawable();
                Drawable img2 = WeekBut.getDrawable();
                Drawable img3 = MouthBut.getDrawable();
                img1.setTint(Color.parseColor("#ffe4e1"));
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

                Drawable img1 = DayBut.getDrawable();
                Drawable img2 = WeekBut.getDrawable();
                Drawable img3 = MouthBut.getDrawable();
                img1.setTint(Color.parseColor("#FFFFFF"));
                img2.setTint(Color.parseColor("#ffe4e1"));
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
                Drawable img1 = DayBut.getDrawable();
                Drawable img2 = WeekBut.getDrawable();
                Drawable img3 = MouthBut.getDrawable();
                img1.setTint(Color.parseColor("#FFFFFF"));
                img2.setTint(Color.parseColor("#FFFFFF"));
                img3.setTint(Color.parseColor("#ffe4e1"));
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
}