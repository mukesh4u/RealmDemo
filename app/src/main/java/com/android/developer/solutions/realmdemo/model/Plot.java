package com.android.developer.solutions.realmdemo.model;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mukesh on 27/11/17.
 */

public class Plot extends RealmObject {
    @PrimaryKey
    private long id;
    private String title;
    private int no_of_building;
    private RealmList<Building> buildingArrayList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNo_of_building() {
        return no_of_building;
    }

    public void setNo_of_building(int no_of_building) {
        this.no_of_building = no_of_building;
    }

}
