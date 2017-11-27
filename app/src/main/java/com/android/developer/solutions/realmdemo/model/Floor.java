package com.android.developer.solutions.realmdemo.model;

import java.util.ArrayList;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mukesh on 27/11/17.
 */

public class Floor extends RealmObject {
    @PrimaryKey
    private long id;
    private Building building;
    private String title;
}
