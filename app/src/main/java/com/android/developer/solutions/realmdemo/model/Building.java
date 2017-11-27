package com.android.developer.solutions.realmdemo.model;

import java.util.ArrayList;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mukesh on 27/11/17.
 */

public class Building extends RealmObject {
    @PrimaryKey
    private long id;
    private Plot plot;
    private String title;
    private String no_of_floors;
    private ArrayList<Floor> floorArrayList;

}
