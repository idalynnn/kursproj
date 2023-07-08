package com.example.kursproj.Daily;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import io.realm.Realm;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.kursproj.Note.Note;
import com.example.kursproj.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DailyNoteView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_note_view);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.types, R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);


        spinner.setAdapter(adapter);

        ImageButton SaveDaily = findViewById(R.id.saveButton);
        EditText Text = findViewById(R.id.descriptioninput);

        Realm.init(getApplicationContext());
        Realm realmDay = Realm.getDefaultInstance();

        SaveDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chang_sp = spinner.getSelectedItem().toString();//
                String txt = Text.getText().toString();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

                Calendar calendar = Calendar.getInstance();
                long createdTime = System.currentTimeMillis();

                calendar.setTimeInMillis(createdTime);

                if (chang_sp.equals("Сегодня")){//A check is made to see what will be displayed
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    String strDate = sdf.format(calendar.getTime());
                    realmDay.beginTransaction();//
                    DailyNoteDay daily = realmDay.createObject(DailyNoteDay.class);
                    daily.setTextDay(txt);//unloading and setting in the text object
                    daily.setDataDay(strDate);
                    realmDay.commitTransaction();
                    Toast.makeText(getApplicationContext(), "Заметка сохранена",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if (chang_sp.equals("Неделя"))
                {
                    calendar.add(Calendar.DAY_OF_MONTH, 7);
                    String strDate = sdf.format(calendar.getTime());
                    realmDay.beginTransaction();
                    DailyNoteWeek daily = realmDay.createObject(DailyNoteWeek.class);
                    daily.setTextWeek(txt);//unloading and setting in the text object
                    daily.setDataWeek(strDate);
                    realmDay.commitTransaction();
                    Toast.makeText(getApplicationContext(), "Заметка сохранена",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if (chang_sp.equals("Месяц"))
                {
                    calendar.add(Calendar.MONTH, 1);
                    String strDate = sdf.format(calendar.getTime());
                    realmDay.beginTransaction();
                    DailyNoteMouth daily = realmDay.createObject(DailyNoteMouth.class);
                    daily.setTextMouth(txt);//unloading and setting in the text object
                    daily.setDataMouth(strDate);
                    realmDay.commitTransaction();
                    Toast.makeText(getApplicationContext(), "Заметка сохранена",Toast.LENGTH_SHORT).show();
                    finish();
                    
                }
            }

        });

    }
}