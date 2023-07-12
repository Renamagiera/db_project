package com.platform.platformapi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainNumber {

    private String trainNumber;

    public TrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }


    /*
    private int trainNumber;

    public int getTrainNumber() {
        return trainNumber;
    }

    public TrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    @XmlElement(name = "trainNumber")
    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }*/
}
