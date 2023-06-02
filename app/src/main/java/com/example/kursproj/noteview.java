package com.example.kursproj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import io.realm.Realm;

public class noteview extends AppCompatActivity {

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
                String Name = NameNote.getText().toString();
                String Text = TextNote.getText().toString();

                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setName(Name);
                note.setDescription(Text);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(), "Заметка сохранена",Toast.LENGTH_SHORT).show();
                finish();
            }
        });



    }



}