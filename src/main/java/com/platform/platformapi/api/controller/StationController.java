package com.platform.platformapi.api.controller;

import com.platform.platformapi.api.model.Station;
import com.platform.platformapi.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StationController {

    private StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/station")
    public String getSection(@RequestParam String ril100) {
        Station station = stationService.getSection(ril100);
        if (!(station == null)) {
            return station.getName();
        }
        return null;
    }
}
