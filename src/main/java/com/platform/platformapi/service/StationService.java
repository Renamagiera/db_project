package com.platform.platformapi.service;

import com.platform.platformapi.api.model.Station;
import com.platform.platformapi.api.model.StationStatic;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Map;

@Service
public class StationService {

    private final StationStatic stationStatic;

    public StationService() {
        stationStatic = new StationStatic();
        //this.station = new Station();
    }

    public String getSection(String shortcode, int myTrain, int myWaggon) throws Exception {
        //return this.stationStatic.searchInXMLForPlatform(this.stationStatic.getDocument(ril100), myTrain, myWaggon);
        Station station = new Station();

        // TO-DO Map
        return station.searchForPlatform(shortcode, myTrain, myWaggon);

    }

}
