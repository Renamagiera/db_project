package com.platform.platformapi.api.model;

import java.util.ArrayList;

public class Waggon {

    private String trainType;
    private int position;
    private int isWaggon;
    private ArrayList<String> identifier;
    private int number;
    private String type;
    private int length;

    public Waggon() {
        this.identifier = new ArrayList<>();
    }
}
