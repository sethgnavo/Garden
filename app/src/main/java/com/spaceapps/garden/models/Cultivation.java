package com.spaceapps.garden.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Class handling the ongoing cultivation of a given plant; it encompasses all necessary data
 * provided by the {@link Plant} class
 *
 * @author Seth-Pharès Gnavo (sethgnavo)
 */
public class Cultivation extends RealmObject {
    @PrimaryKey
    private long id;
    private Plant plantBundle;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Plant getPlantBundle() {
        return plantBundle;
    }

    public void setPlantBundle(Plant plantBundle) {
        this.plantBundle = plantBundle;
    }
}
