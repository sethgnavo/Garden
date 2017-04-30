package com.spaceapps.garden.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.spaceapps.garden.R;
import com.spaceapps.garden.adapters.CultivationAdapter;
import com.spaceapps.garden.models.Cultivation;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmQuery;

public class GardenDetailActivity extends AppCompatActivity {
    private RecyclerView cultivationList;
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        realm = Realm.getDefaultInstance();
        cultivationList = (RecyclerView) findViewById(R.id.cultivation_list);

        RealmQuery<Cultivation> query = realm.where(Cultivation.class);
        final OrderedRealmCollection<Cultivation> results = query.findAll();

        CultivationAdapter gardenAdapter = new CultivationAdapter(this, results);
        cultivationList.setAdapter(gardenAdapter);
    }

}
