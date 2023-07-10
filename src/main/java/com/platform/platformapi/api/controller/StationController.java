package com.platform.platformapi.api.controller;

import com.platform.platformapi.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StationController {

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/station")
    public ResponseEntity<?> mapReturn(@RequestParam String ril100, @RequestParam String myTrain, @RequestParam String myWaggon) throws Exception {
        return ResponseEntity.ok(stationService.getSection(ril100, myTrain, myWaggon));
    }

}
