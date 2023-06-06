package com.example.kursproj.Daily;

import io.realm.RealmObject;

public class DailyNoteWeek extends RealmObject {

    String Text;
    String Data;
    public String getTextWeek() {
        return Text;
    }

    public void setTextWeek(String Text) {
        this.Text = Text;
    }

    public String getDataWeek() {
        return Data;
    }

    public void setDataWeek(String Data) {
        this.Data = Data;
    }
}
