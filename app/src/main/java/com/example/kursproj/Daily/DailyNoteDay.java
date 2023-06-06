package com.example.kursproj.Daily;

import io.realm.RealmObject;

public class DailyNoteDay extends RealmObject {

    String Text;
    String Data;

    public String getTextDay() {
        return Text;
    }

    public void setTextDay(String Text) {
        this.Text = Text;
    }

    public String getDataDay() {
        return Data;
    }

    public void setDataDay(String Data) {
        this.Data = Data;
    }
}
