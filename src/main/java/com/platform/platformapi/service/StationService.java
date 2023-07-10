package com.platform.platformapi.service;

import com.platform.platformapi.api.model.Station;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Map;

@Service
public class StationService {

    private final Station station;

    public StationService() {
        station = new Station();
    }

    public Map<String, Map<String, ArrayList<String>>> getSection(String ril100, String myTrain, String myWaggon) throws Exception {
        return this.station.searchInXMLForPlatform(this.station.getDocument(ril100), myTrain, myWaggon);
    }

}
