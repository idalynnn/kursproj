package com.example.kursproj.Daily;

import io.realm.RealmObject;

public class DailyNoteDay extends RealmObject {

    String Text; //Note text
    boolean isChecked = false;    //Checking the note for readiness
    String Data;//Date of note

    //Installing a new checker
    public void setChecked(boolean checked) {
        isChecked = checked;
    }
    //Get the Checker
    public boolean isChecked() {
        return isChecked;
    }
    //Setting the text
    public String getTextDay() {
        return Text;
    }
    //Taking text
    public void setTextDay(String Text) {
        this.Text = Text;
    }
    //Take the date
    public String getDataDay() {
        return Data;
    }
    //Insert date
    public void setDataDay(String Data) {
        this.Data = Data;
    }
}