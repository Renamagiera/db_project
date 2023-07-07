package com.platform.platformapi.service;

import com.platform.platformapi.api.model.Station;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationService {

    private List<Station> stationList;

    public StationService() {
        stationList = new ArrayList<>();
        Station station1 = new Station();
        stationList.add(station1);
    }

    public Station getSection(String ril100) {
        for (Station station : stationList) {
            if (ril100.equals(station.getRil100())) {
                return station;
            }
        }
        return null;
    }

}
