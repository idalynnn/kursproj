package com.example.kursproj.MainPageWithoutNotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.kursproj.R;

public class Information extends AppCompatActivity {
    String currentTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentTheme = preferences.getString("theme", "default");

        switch (currentTheme) {
            case "Purple":
                setTheme(R.style.Theme_Purple);
                break;
            case "Blue":
                setTheme(R.style.Theme_Blue);
                break;
            default:
                setTheme(R.style.Theme_Default);
                break;
        }
    }
}