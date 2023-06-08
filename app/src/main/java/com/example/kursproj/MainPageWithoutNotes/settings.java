package com.example.kursproj.MainPageWithoutNotes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;

import com.example.kursproj.R;

public class settings extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;
    public static String currentTheme = "default";

    public static String getMyTheme() {
        return currentTheme;
    }


    private void setAppTheme(String themeName) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("theme", themeName).apply();
        currentTheme = themeName;
        Intent intent = getIntent();
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentTheme = sharedPreferences.getString("theme", "default");

        switch (currentTheme) {
            case "Purple":
                setTheme(R.style.Theme_Purple);
                currentTheme = "Purple";
                break;
            case "Blue":
                setTheme(R.style.Theme_Blue);
                currentTheme = "Blue";
                break;
            default:
                setTheme(R.style.Theme_Default);
                currentTheme = "default";
                break;
        }

        setContentView(R.layout.activity_settings);

        ImageButton purpleThemeButton = findViewById(R.id.purpleBtn);
        ImageButton blueThemeButton = findViewById(R.id.blueBtn);
        ImageButton defaultThemeButton = findViewById(R.id.defaultBtn);

        purpleThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppTheme("Purple");
            }
        });

        blueThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppTheme("Blue");
            }
        });

        defaultThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppTheme("default");
            }
        });
    }


}

