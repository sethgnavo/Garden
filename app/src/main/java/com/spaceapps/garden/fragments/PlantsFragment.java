package com.spaceapps.garden.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spaceapps.garden.R;
import com.spaceapps.garden.adapters.PlantAdapter;
import com.spaceapps.garden.models.Plant;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmQuery;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlantsFragment extends Fragment {
    private RecyclerView plantList;
    private Realm realm;

    public PlantsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_plants, container, false);
        realm = Realm.getDefaultInstance();
        plantList = (RecyclerView) v.findViewById(R.id.plant_list);

        //START CREATE PLANTS
        // Obtain a Realm instance
        realm.beginTransaction();
        Plant plantTomato = new Plant();
        plantTomato.setPlantName("Tomato");
        plantTomato.setPlantFamily("Plant family");
        plantTomato.setPlantSpecy("Specy");
        plantTomato.setPlantVarieties("Plant variety");
        plantTomato.setPlantImageRessource(R.drawable.image_tomato);
        plantTomato.setId(1);
        realm.insertOrUpdate(plantTomato);


        Plant plantLettuce = new Plant();
        plantLettuce.setPlantName("Lettuce");
        plantLettuce.setPlantFamily("Plant family");
        plantLettuce.setPlantSpecy("Specy");
        plantLettuce.setPlantVarieties("Plant variety");
        plantLettuce.setPlantImageRessource(R.drawable.img_lettuce);
        plantLettuce.setId(2);
        realm.insertOrUpdate(plantLettuce);

        Plant bean = new Plant();
        bean.setPlantName("Bean");
        bean.setPlantFamily("Plant family");
        bean.setPlantSpecy("Specy");
        bean.setPlantVarieties("Plant variety");
        bean.setPlantImageRessource(R.drawable.img_beans);
        bean.setId(3);

        realm.insertOrUpdate(bean);
        Plant carrot = new Plant();
        carrot.setPlantName("Carrot");
        carrot.setPlantFamily("Plant family");
        carrot.setPlantSpecy("Specy");
        carrot.setPlantVarieties("Plant variety");
        carrot.setPlantImageRessource(R.drawable.ic_carrot);
        carrot.setId(4);

        realm.insertOrUpdate(carrot);
        Plant cucumber = new Plant();
        cucumber.setPlantName("Cucumber");
        cucumber.setPlantFamily("Plant family");
        cucumber.setPlantSpecy("Specy");
        cucumber.setPlantVarieties("Plant variety");
        cucumber.setPlantImageRessource(R.drawable.ic_cucumber);
        cucumber.setId(5);

        realm.insertOrUpdate(cucumber);
        Plant garlic = new Plant();
        garlic.setPlantName("Garlic");
        garlic.setPlantFamily("Plant family");
        garlic.setPlantSpecy("Specy");
        garlic.setPlantVarieties("Plant variety");
        garlic.setPlantImageRessource(R.drawable.ic_garlic);
        garlic.setId(6);

        realm.insertOrUpdate(garlic);
        Plant maize = new Plant();
        maize.setPlantName("Maize");
        maize.setPlantFamily("Plant family");
        maize.setPlantSpecy("Specy");
        maize.setPlantVarieties("Plant variety");
        maize.setPlantImageRessource(R.drawable.img_lettuce);
        maize.setId(7);
        realm.insertOrUpdate(maize);

        realm.commitTransaction();
        realm.close();
        //END CREATE PLANTS

        RealmQuery<Plant> query = realm.where(Plant.class);
        final OrderedRealmCollection<Plant> results = query.findAll();

        PlantAdapter gardenAdapter = new PlantAdapter(getActivity(), results);
        plantList.setAdapter(gardenAdapter);

        return v;
    }

}
