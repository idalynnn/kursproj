package com.example.kursproj.MainPageWithoutNotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageButton;

import com.example.kursproj.R;

public class settings extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private String currentTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // Получаем текущую тему
        currentTheme = sharedPreferences.getString("theme", "default");

        /*ImageButton purpleThemeButton = findViewById(R.id.purpleThemeButton);
        ImageButton blueThemeButton = findViewById(R.id.blueThemeButton);
        ImageButton defaultThemeButton = findViewById(R.id.defaultThemeButton);*/

    }
}