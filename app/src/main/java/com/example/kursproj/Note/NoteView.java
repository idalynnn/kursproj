package com.example.kursproj.Note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.kursproj.R;

import io.realm.Realm;

public class NoteView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noteview);

        EditText NameNote = findViewById(R.id.titleinput);
        EditText TextNote = findViewById(R.id.descriptioninput);
        ImageButton Save = findViewById(R.id.saveButton);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = NameNote.getText().toString();
                String text = TextNote.getText().toString();
                String bufSec = name;
                bufSec=bufSec.replaceAll("\\s+","");
                if(!name.isEmpty())
                {
                    if(!bufSec.isEmpty())
                    {
                        realm.beginTransaction();
                        Note note = realm.createObject(Note.class);
                        note.setName(name);
                        note.setDescription(text);

                        realm.commitTransaction();
                        Toast.makeText(getApplicationContext(), "Заметка сохранена",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Пусто",Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Пусто",Toast.LENGTH_SHORT).show();

                }
            }
        });



    }



}