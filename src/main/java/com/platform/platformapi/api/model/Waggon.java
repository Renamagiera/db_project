package com.platform.platformapi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Waggon {
    private String number;
    private List<Identifier> sections;

    public Waggon(String number) {
        this.number = number;
    }

    public Waggon() {
    }

    public List<Identifier> getSections() {
        return sections;
    }

    public void setSections(List<Identifier> sections) {
        this.sections = sections;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ArrayList<String> searchForIdentifier() {
        return null;
    }
}
