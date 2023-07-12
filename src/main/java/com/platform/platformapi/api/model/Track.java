package com.platform.platformapi.api.model;

import java.util.ArrayList;

public class Track {

    private String name;
    private int number;
    private ArrayList<Train> trains;
    private ArrayList<String> sections;

    public Track() {
        this.trains = new ArrayList<>();
        this.sections = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<Train> getTrains() {
        return trains;
    }

    public void setTrains(ArrayList<Train> trains) {
        this.trains = trains;
    }

    public ArrayList<String> getSections() {
        return sections;
    }

    public void setSections(ArrayList<String> sections) {
        this.sections = sections;
    }
}
