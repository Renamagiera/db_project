package com.platform.platformapi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tracks {

    private String name;
    private String number;
    private List<Train> trains;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Train> getTrains() {
        return trains;
    }

    @XmlElement(name = "train")
    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public ArrayList<Train> searchForTrainNumber(String trainNumber) {
        /*ArrayList<Train> results = new ArrayList<>();
        Train find = trains.stream()
                        .filter(train -> trainNumber.equals(trainNumber))
                        .findAny()
                        .orElse(null);
        results.add(find);
        return results;*/
        return null;
    }
}
