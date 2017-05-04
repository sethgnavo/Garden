package com.spaceapps.garden.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.StackingBehavior;
import com.spaceapps.garden.R;
import com.spaceapps.garden.models.Cultivation;
import com.spaceapps.garden.models.Plant;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmQuery;

public class PlantDetailActivity extends AppCompatActivity {
    final Calendar now = Calendar.getInstance();
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

        RealmQuery<Plant> query = realm.where(Plant.class);
        final OrderedRealmCollection<Plant> results = query.findAll();
        final Plant p = results.first();

        commencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(PlantDetailActivity.this)
                        .title(R.string.start_now)
                        .content(R.string.launch_description)
                        .positiveText(R.string.noThanks)
                        .negativeText(R.string.start_later)
                        .btnStackedGravity(GravityEnum.END)
                        .stackingBehavior(StackingBehavior.ALWAYS)  // this generally should not
                        // be forced, but is used for demo purposes
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull
                                    DialogAction which) {
                                startActivity(new Intent(PlantDetailActivity.this,
                                        CultivationActivity.class));
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull
                                    DialogAction which) {
                                DatePickerDialog dpd = DatePickerDialog.newInstance(
                                        new DatePickerDialog.OnDateSetListener() {
                                            @Override
                                            public void onDateSet(DatePickerDialog view, int
                                                    year, int monthOfYear, int dayOfMonth) {

                                                startActivity(new Intent(PlantDetailActivity
                                                        .this, CultivationActivity.class));
                                            }
                                        },
                                        now.get(Calendar.YEAR),
                                        now.get(Calendar.MONTH),
                                        now.get(Calendar.DAY_OF_MONTH)
                                );
                                dpd.setMinDate(now);
                                dpd.show(getFragmentManager(), "Datepickerdialog");
                            }
                        })
                        .show();

                // create a cultivation
                realm.beginTransaction();
                Cultivation c = new Cultivation();
                c.setId(1);
                c.setPlantBundle(p);
                realm.insertOrUpdate(c);
                realm.commitTransaction();
                realm.close();
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
