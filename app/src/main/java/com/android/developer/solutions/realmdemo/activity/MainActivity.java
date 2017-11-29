package com.android.developer.solutions.realmdemo.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.android.developer.solutions.realmdemo.R;
import com.android.developer.solutions.realmdemo.model.Building;
import com.android.developer.solutions.realmdemo.model.Plot;

import java.util.Random;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        insertPlotData();
        readPlotInfo();
    }

    private void readPlotInfo() {
        Realm.init(this);
        RealmResults<Plot> plots = Realm.getDefaultInstance().where(Plot.class).findAllAsync();
        plots.load();
        String result ="";
        for (int i=0;i<plots.size(); i++) {
            result = result+ plots.get(0).toString();
            Realm.getDefaultInstance().beginTransaction();
            plots.get(0).deleteFromRealm();
            Realm.getDefaultInstance().commitTransaction();
        }

        Log.d("result is===",result);
    }

    private void insertPlotData() {

        try {
            Realm.init(this);
            Realm.getDefaultInstance().beginTransaction();  //open the database
            //database operation
            Plot obj = Realm.getDefaultInstance().createObject(Plot.class,new Random().nextInt()+50);  //this will create a
            //plot object which will be inserted in database

            //obj.setId(1);
            obj.setTitle("plot1");
            obj.setNo_of_building(2);
            for(int i=0;i<obj.getNo_of_building();i++) {
                insertBuildingData(obj,i);
            }
            Realm.getDefaultInstance().commitTransaction(); //close the database
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertBuildingData(Plot obj, int count) {
        Building building = Realm.getDefaultInstance().createObject(Building.class,new Random().nextInt()+50);  //this will create a
        building.setPlot(obj);
        building.setNo_of_floors(3);
        building.setTitle("Building "+count);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
