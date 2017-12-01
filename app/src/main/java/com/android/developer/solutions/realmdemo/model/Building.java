package com.android.developer.solutions.realmdemo.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mukesh on 27/11/17.
 */

public class Building extends RealmObject {
    @PrimaryKey
    private long id;
    private Plot plot;
    private String title;
    private int no_of_floors;
    private  RealmList<Floor> floorArrayList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;

    public RealmResults<Plot> getOwners() {
        return owners;
    }

    @LinkingObjects("buildingArrayList")
    private final  RealmResults<Plot> owners;

    public Building(RealmResults<Plot> owners) {
        this.owners = owners;
    }

    public Building() {
        this.owners = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNo_of_floors() {
        return no_of_floors;
    }

    public void setNo_of_floors(int no_of_floors) {
        this.no_of_floors = no_of_floors;
    }

    public RealmList<Floor> getFloorArrayList() {
        return floorArrayList;
    }

    public void setFloorArrayList(RealmList<Floor> floorArrayList) {
        this.floorArrayList = floorArrayList;
    }
}
