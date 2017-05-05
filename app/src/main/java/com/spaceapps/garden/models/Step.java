package com.spaceapps.garden.models;

/**
 * @author Seth-Pharès Gnavo (sethgnavo)
 */
public class Step {
    private String stepName;
    private int dayInRange;
    /**
     * The number of times the action at this step should be performed per day.
     * For example, with an inputCount of 3, the action labeled at stepName shall be performed on
     * the morning, at noon, and in the afternoon.
     */
    private int inputCount;//

    public Step(String stepName, int dayInRange, int inputCount) {
        this.stepName = stepName;
        this.dayInRange = dayInRange;
        this.inputCount = inputCount;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public int getDayInRange() {
        return dayInRange;
    }

    public void setDayInRange(int dayInRange) {
        this.dayInRange = dayInRange;
    }

    public int getInputCount() {
        return inputCount;
    }

    public void setInputCount(int inputCount) {
        this.inputCount = inputCount;
    }
}
