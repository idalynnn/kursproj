package com.example.kursproj;

import io.realm.RealmObject;

public class Note extends RealmObject{

    String Name;
    String description;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = Name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
