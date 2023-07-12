package com.platform.platformapi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Station {

    private String shortcode;
    private String name;
    private List<Tracks> tracks;

    public Station() {
    }

    public Station(String shortcode, String name, List<Tracks> tracks) {
        this.shortcode = shortcode;
        this.name = name;
        this.tracks = tracks;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tracks> getTracks() {
        return tracks;
    }

    @XmlElement(name = "track")
    public void setTracks(List<Tracks> tracks) {
        this.tracks = tracks;
    }

    public Map<String, Map<String, ArrayList<String>>> searchForPlatform(int myTrainNumber, int myWaggon) {
        String trainNumber = String.valueOf(myTrainNumber);
        String waggon = String.valueOf(myWaggon);

        return this.searchForTrainsWithGivenParam(trainNumber, waggon);
    }

    private Map<String, Map<String, ArrayList<String>>> searchForTrainsWithGivenParam(String myTrainNumber, String myWaggon) {
        Map<String, Map<String, ArrayList<String>>> resultsMap = new HashMap<>();
        for (Tracks track : tracks) {
            for (Train train : track.getTrains()) {
                for (TrainNumber trainNr : train.trainNumbers) {
                    if (trainNr.getTrainNumber().equals(myTrainNumber)) {
                        for (Waggon waggon : train.waggons) {
                            if (waggon.getNumber().equals(myWaggon)) {
                                this.handleResults(waggon, track, resultsMap);
                            }
                        }
                    }
                }
            }
        }
        return resultsMap;
    }

    private void handleResults(Waggon waggon, Tracks track, Map<String, Map<String, ArrayList<String>>> resultsMap) {
        ArrayList<String> sec = new ArrayList<>();
        for (Identifier identifier : waggon.getSections()) {
            sec.add(identifier.getIdentifier());
        }
        Map<String, ArrayList<String>> sections = new HashMap<>();
        sections.put("sections", sec);
        resultsMap.put(track.getName(), sections);
    }
}
