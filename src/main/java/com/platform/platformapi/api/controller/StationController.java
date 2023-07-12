package com.platform.platformapi.api.controller;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.platform.platformapi.api.model.Station;
import com.platform.platformapi.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class StationController {

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }


    @GetMapping("/station")
    public ResponseEntity<?> getPlatform(@RequestParam String ril100, @RequestParam int myTrain, @RequestParam int myWaggon) throws Exception {
        return ResponseEntity.ok(stationService.getSection(ril100, myTrain, myWaggon));
    }

}
