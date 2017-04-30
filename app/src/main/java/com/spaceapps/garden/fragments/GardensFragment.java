package com.spaceapps.garden.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.spaceapps.garden.R;
import com.spaceapps.garden.adapters.GardenAdapter;
import com.spaceapps.garden.models.Garden;

import java.util.Date;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * A simple {@link Fragment} subclass.
 */
public class GardensFragment extends Fragment {
    public static long gardenCount;//handle Garden id for new gardens
    private Realm realm;
    private FloatingActionButton fab;
    private RecyclerView gardenList;

    private EditText gardenName;
    private EditText gardenWidth;
    private EditText gardenLength;
    private TextView noGardenText;
    private View positiveAction;


    public GardensFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gardens, container, false);

        realm = Realm.getDefaultInstance();
        gardenList = (RecyclerView) v.findViewById(R.id.garden_list);
        noGardenText = (TextView) v.findViewById(R.id.text_no_gardens);


        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setUseCompatPadding(true);
        fab.setImageDrawable(new IconicsDrawable(getActivity()).icon(GoogleMaterial.Icon.gmd_add)
                .colorRes(R.color.md_white_1000).paddingDp(4));

        RealmQuery<Garden> query = realm.where(Garden.class);
        final OrderedRealmCollection<Garden> results = query.findAll();

        GardenAdapter gardenAdapter = new GardenAdapter(getActivity(), results);
        gardenList.setAdapter(gardenAdapter);
        gardenList.addOnChildAttachStateChangeListener(new RecyclerView
                .OnChildAttachStateChangeListener() {

            @Override
            public void onChildViewAttachedToWindow(View view) {
                if (results.size() == 0)
                    noGardenText.setVisibility(View.VISIBLE);
                else
                    noGardenText.setVisibility(View.GONE);
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                if (results.size() == 0)
                    noGardenText.setVisibility(View.VISIBLE);
                else
                    noGardenText.setVisibility(View.GONE);
            }
        });

        makeCountExtra(results);

        if (results.size() == 0) {//somewhat if the garden list is empty
            TapTargetView.showFor(getActivity(), TapTarget.forView(v.findViewById(R.id.fab),
                    "Ajoutez un potager", "")
                    .outerCircleColor(R.color.accent)
                    .targetCircleColor(R.color.md_white_1000)
                    .textColor(R.color.md_white_1000)
                    .dimColor(R.color.md_black_1000)
                    .tintTarget(false)
                    .icon(new IconicsDrawable(getActivity()).icon(GoogleMaterial.Icon.gmd_add)
                            .sizeDp(24)
                            .colorRes(R.color.md_white_1000).paddingDp(4), true)
                    .drawShadow(true), new TapTargetView.Listener() {
                @Override
                public void onTargetClick(TapTargetView view) {
                    super.onTargetClick(view);
                    showCustomView();
                }
            });

        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCountExtra(results);
                showCustomView();
                realm.close();//TODO remove if unnecessary
            }
        });

        return v;
    }

    private void showCustomView() {
        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .title(R.string.add_new_garden)
                .customView(R.layout.new_garden_layout, true)
                .positiveText(R.string.add)
                .negativeText(android.R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction
                            which) {
                        final Realm realm = Realm.getDefaultInstance();

                        // Obtain a Realm instance
                        realm.beginTransaction();
                        Garden garden = realm.createObject(Garden.class);
                        if (!gardenName.getText().toString().isEmpty()) {
                            garden.setGardenName(gardenName.getText().toString());
                        }
                        if (!gardenWidth.getText().toString().isEmpty() && !gardenLength.getText()
                                .toString().isEmpty()) {

                            garden.setGardenSize(gardenWidth.getText().toString() + "x" +
                                    gardenLength.getText().toString() + "m");
                        }

                        if (!gardenName.getText().toString().isEmpty() && !gardenWidth.getText()
                                .toString().isEmpty() && !gardenLength.getText()
                                .toString().isEmpty()) {
                            positiveAction.setEnabled(true);

                        } else {
                            Toast.makeText(getActivity(), R.string.garden_fields_empty, Toast
                                    .LENGTH_SHORT).show();
                        }

                        garden.setGardenPlantsCount(0);
                        garden.setCreatedTime(new Date());
                        garden.setId(gardenCount);

                        realm.commitTransaction();
                        realm.close();
                    }
                }).build();

        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        //noinspection ConstantConditions
        gardenName = (EditText) dialog.getCustomView().findViewById(R.id.ed_garden_name);
        gardenWidth = (EditText) dialog.getCustomView().findViewById(R.id.ed_garden_width);
        gardenLength = (EditText) dialog.getCustomView().findViewById(R.id.ed_garden_lenght);

        dialog.show();
        //positiveAction.setEnabled(false); // disabled by default
    }

    /**
     * Set the value of gardenCount to the higher garden id in the garden list
     *
     * @param results
     */
    private void makeCountExtra(OrderedRealmCollection<Garden> results) {

        //make sure gardenCount has a value even if the list is empty
        if (results.size() == 0)
            gardenCount = 1;
        else
            gardenCount = results.size() + 1;

        //assign the id of the garden to var id if this one is superior to the latest value
        for (int i = 0; i < results.size(); i++) {
            if ((results.get(i).getId()) >= gardenCount) {
                gardenCount = results.get(i).getId() + 1;
            }
        }
    }
}
