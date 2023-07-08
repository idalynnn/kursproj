package com.example.kursproj.Daily;

import io.realm.RealmObject;

public class DailyNoteWeek extends RealmObject {

    String Text;//Note text
    String Data;//Date of note

    boolean isChecked = false;    //Checking the note for readiness
    //Installing a new checker
    public void setChecked(boolean checked) {
        isChecked = checked;
    }
    //Get the Checker
    public boolean isChecked() {
        return isChecked;
    }
    //Setting the text
    public String getTextWeek() {
        return Text;
    }
    //Taking text
    public void setTextWeek(String Text) {
        this.Text = Text;
    }
    //Take the date
    public String getDataWeek() {
        return Data;
    }
    //Insert date
    public void setDataWeek(String Data) {
        this.Data = Data;
    }
}