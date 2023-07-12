package com.platform.platformapi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Train {
    List<TrainNumber> trainNumbers;
    List<Waggon> waggons;

    public List<TrainNumber> getTrainNumbers() {
        return trainNumbers;
    }

    public void setTrainNumbers(List<TrainNumber> trainNumbers) {
        this.trainNumbers = trainNumbers;
    }

    public List<Waggon> getWaggons() {
        return waggons;
    }

    public void setWaggons(List<Waggon> waggons) {
        this.waggons = waggons;
    }

    public Train searchForTrainNumber () {
        return null;
    }
}
