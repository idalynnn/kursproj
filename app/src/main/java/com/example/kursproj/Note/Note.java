package com.example.kursproj.Note;

import io.realm.RealmObject;

public class Note extends RealmObject{

    String Name;
    String description;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
