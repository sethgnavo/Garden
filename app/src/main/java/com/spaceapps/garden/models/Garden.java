package com.spaceapps.garden.models;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Seth-Pharès Gnavo (sethgnavo)
 */

public class Garden extends RealmObject {
    @PrimaryKey
    private long id;
    private String gardenName;
    private String gardenSize;
    private int gardenPlantsCount;
    private Date createdTime;
    private RealmList<Cultivation> cultivationList;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGardenName() {
        return gardenName;
    }

    public void setGardenName(String gardenName) {
        this.gardenName = gardenName;
    }

    public String getGardenSize() {
        return gardenSize;
    }

    public void setGardenSize(String gardenSize) {
        this.gardenSize = gardenSize;
    }

    public int getGardenPlantsCount() {
        return gardenPlantsCount;
    }

    public void setGardenPlantsCount(int gardenPlantsCount) {
        this.gardenPlantsCount = gardenPlantsCount;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public RealmList<Cultivation> getCultivationList() {
        return cultivationList;
    }

    public void setCultivationList(RealmList<Cultivation> cultivationList) {
        this.cultivationList = cultivationList;
    }
}
