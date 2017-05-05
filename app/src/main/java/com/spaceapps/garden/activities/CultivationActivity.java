package com.spaceapps.garden.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.spaceapps.garden.R;
import com.spaceapps.garden.adapters.CultivationLevelAdapter;
import com.spaceapps.garden.models.CultivationLevel;
import com.spaceapps.garden.models.Plant;
import com.spaceapps.garden.models.Step;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmQuery;

public class CultivationActivity extends AppCompatActivity {
    private List<CultivationLevel> levelList;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private CultivationLevelAdapter mCultivationLevelAdapter;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.list_cultivation_levels);
        mCultivationLevelAdapter = new CultivationLevelAdapter(this);

        setUpRecyclerView();
        setUpLevelListAdapter();

        Intent cultivationIntent = getIntent();
        Date startDate = (Date) cultivationIntent.getSerializableExtra(PlantDetailActivity
                .INTENT_START_DATE);
        int plantId = cultivationIntent.getIntExtra(PlantDetailActivity.INTENT_PLANT_ID, 1);

        realm = Realm.getDefaultInstance();
        levelList = new ArrayList<>();

        RealmQuery<Plant> query = realm.where(Plant.class);
        final OrderedRealmCollection<Plant> results = query.findAll();
        final Plant plant = results.get(plantId);

        if (true) {//set the cultivation levelList accordingly
            Step step1Level1 = new Step("Préparation du sol de semis", 1, 1);
            Step step2Level1 = new Step("Semis", 1, 1);
            Step step3Level1 = new Step("Arrosage", 1, 1);
            Step step4Level1 = new Step("Entretien et Arrosage", 3, 2);

            ArrayList<Step> stepsLevel1List = new ArrayList<>();
            stepsLevel1List.add(step1Level1);
            stepsLevel1List.add(step2Level1);
            stepsLevel1List.add(step3Level1);
            stepsLevel1List.add(step4Level1);

            CultivationLevel preparationPepiniere = new CultivationLevel("Préparation de la " +
                    "pépinière", startDate, 1, 25, stepsLevel1List);
            CultivationLevel plantation = new CultivationLevel("Plantation", startDate, 23, 29,
                    stepsLevel1List);

            levelList.add(preparationPepiniere);
            levelList.add(plantation);
            mCultivationLevelAdapter.        notifyDataSetChanged();
        }
    }

    // Sets up recycler view
    private void setUpRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mCultivationLevelAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration
                .VERTICAL));

    }

    // Sets up levels list adapter
    private void setUpLevelListAdapter() {
        mCultivationLevelAdapter.setOnItemClickListener(new CultivationLevelAdapter
                .OnItemClickListener() {

            @Override
            public void onItemClick(CultivationLevel channel) {

            }
        });

        mCultivationLevelAdapter.setOnItemLongClickListener(new CultivationLevelAdapter
                .OnItemLongClickListener() {

            @Override
            public void onItemLongClick(CultivationLevel channel) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCultivationLevelAdapter.setCultivationLevelList(levelList);
    }
}
