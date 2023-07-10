package com.platform.platformapi.service;

import com.platform.platformapi.api.model.Station;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Map;

@Service
public class StationService {

    private Station station;

    public StationService() throws Exception {
        station = new Station();
    }

    public Map<String, Map<String, ArrayList<String>>> getSection(String ril100, String myTrain, String myWaggon) {
        // search for xml-File
        return this.station.searchForPlatform(station.getDocument(), myTrain, myWaggon);
    }

}
