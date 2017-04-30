package com.spaceapps.garden.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.spaceapps.garden.R;
import com.spaceapps.garden.models.Cultivation;
import com.spaceapps.garden.models.Plant;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmQuery;

public class PlantDetailActivity extends AppCompatActivity {
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button commencer = (Button) findViewById(R.id.btn_commencer);
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RealmQuery<Plant> query = realm.where(Plant.class);
        final OrderedRealmCollection<Plant> results = query.findAll();
        final Plant p = results.first();

        commencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlantDetailActivity.this, CultivationActivity.class));
//TODO create a culture
                Cultivation c = new Cultivation();
                c.setId(1);
                c.setPlantBundle(p);
                realm.insertOrUpdate(c);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
