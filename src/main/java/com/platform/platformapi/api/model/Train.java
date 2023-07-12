package com.platform.platformapi.api.model;

import java.sql.Time;
import java.util.ArrayList;

public class Train {

    private ArrayList<Integer> trainNumbers;
    private Time time;
    private String additionalText;
    private String name;
    private String[] sections;
    private ArrayList<Waggon> waggons;

    public Train() {
        this.trainNumbers = new ArrayList<>();
        this.waggons = new ArrayList<>();
    }

    public ArrayList<Integer> getTrainNumbers() {
        return trainNumbers;
    }

    public void setTrainNumbers(ArrayList<Integer> trainNumbers) {
        this.trainNumbers = trainNumbers;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getAdditionalText() {
        return additionalText;
    }

    public void setAdditionalText(String additionalText) {
        this.additionalText = additionalText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSections() {
        return sections;
    }

    public void setSections(String[] sections) {
        this.sections = sections;
    }

    public ArrayList<Waggon> getWaggons() {
        return waggons;
    }

    public void setWaggons(ArrayList<Waggon> waggons) {
        this.waggons = waggons;
    }
}
