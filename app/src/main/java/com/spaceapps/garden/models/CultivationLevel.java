package com.spaceapps.garden.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Seth-Pharès Gnavo (sethgnavo)
 */

public class CultivationLevel implements Parcelable {
    private String levelName;
    private Date startDate;
    private Date endDate;
    private int firstDayInRange;
    private int lastDayInRange;
    private ArrayList<Step> stepArrayList;

    public CultivationLevel(String levelName, Date startDate, int firstDayInRange,
                            int lastDayInRange, ArrayList<Step> stepArrayList) {
        //timestamp at the end of the reading; 1 day = 86400000ms
        long startTimestamp = startDate.getTime() + (firstDayInRange * 86400000);//not sure, verify
        long endTimestamp = startDate.getTime() + (lastDayInRange * 86400000);
        startDate = new Date(startTimestamp);
        Date endDate = new Date(endTimestamp);
        this.levelName = levelName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.firstDayInRange = firstDayInRange;
        this.lastDayInRange = lastDayInRange;
        this.stepArrayList = stepArrayList;
    }

    protected CultivationLevel(Parcel in) {
        levelName = in.readString();
        firstDayInRange = in.readInt();
        lastDayInRange = in.readInt();
    }

    public static final Creator<CultivationLevel> CREATOR = new Creator<CultivationLevel>() {
        @Override
        public CultivationLevel createFromParcel(Parcel in) {
            return new CultivationLevel(in);
        }

        @Override
        public CultivationLevel[] newArray(int size) {
            return new CultivationLevel[size];
        }
    };

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getFirstDayInRange() {
        return firstDayInRange;
    }

    public void setFirstDayInRange(int firstDayInRange) {
        this.firstDayInRange = firstDayInRange;
    }

    public int getLastDayInRange() {
        return lastDayInRange;
    }

    public void setLastDayInRange(int lastDayInRange) {
        this.lastDayInRange = lastDayInRange;
    }

    public ArrayList<Step> getStepArrayList() {
        return stepArrayList;
    }

    public void setStepArrayList(ArrayList<Step> stepArrayList) {
        this.stepArrayList = stepArrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(levelName);
        dest.writeInt(firstDayInRange);
        dest.writeInt(lastDayInRange);
    }
}
