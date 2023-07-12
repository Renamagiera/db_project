package com.platform.platformapi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Through the service called in the 'StationService' class,
 * a stream is created from the file name of the requested XML file.
 * This stream is then passed to a mapper that maps this 'Station' class into an object.
 * <p>
 * This is the start of the object.
 * There are only the Attributes needed for the platform-request.
 * */
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

    public void setTracks(List<Tracks> tracks) {
        this.tracks = tracks;
    }

    /**
     * This is the method called through the service.
     * Need to cast the parameters because the values from the XML files can also contain strings,
     * even though integers are expected.
     * It calls the method "searchForTrainsWithGivenParams",
     * in which the Station object is searched through multiple loops for the given parameters.
     * */
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
