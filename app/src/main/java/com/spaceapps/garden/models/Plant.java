package com.spaceapps.garden.models;

import android.support.annotation.DrawableRes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * @author Seth-Pharès Gnavo (sethgnavo)
 */

@RealmClass

public class Plant extends RealmObject {
    @PrimaryKey
    private long id;
    private String plantName;
    private String plantFamily;
    private String plantSpecy;
    private String plantVarieties;
    @DrawableRes
    private int plantImageRessource;
    private long productionDuration;
    private long productionCost;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantFamily() {
        return plantFamily;
    }

    public void setPlantFamily(String plantFamily) {
        this.plantFamily = plantFamily;
    }

    public String getPlantSpecy() {
        return plantSpecy;
    }

    public void setPlantSpecy(String plantSpecy) {
        this.plantSpecy = plantSpecy;
    }

    public String getPlantVarieties() {
        return plantVarieties;
    }

    public void setPlantVarieties(String plantVarieties) {
        this.plantVarieties = plantVarieties;
    }

    public int getPlantImageRessource() {
        return plantImageRessource;
    }

    public void setPlantImageRessource(int plantImageRessource) {
        this.plantImageRessource = plantImageRessource;
    }

    public long getProductionDuration() {
        return productionDuration;
    }

    public void setProductionDuration(long productionDuration) {
        this.productionDuration = productionDuration;
    }

    public long getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(long productionCost) {
        this.productionCost = productionCost;
    }
}
