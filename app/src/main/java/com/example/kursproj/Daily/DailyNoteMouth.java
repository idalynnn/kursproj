package com.example.kursproj.Daily;

import io.realm.RealmObject;

public class DailyNoteMouth extends RealmObject {

    String Text;
    String Data;
    public String getTextMouth() {
        return Text;
    }

    public void setTextMouth(String Text) {
        this.Text = Text;
    }

    public String getDataMouth() {
        return Data;
    }

    public void setDataMouth(String Data) {
        this.Data = Data;
    }
}
