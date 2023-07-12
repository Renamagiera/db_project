package com.platform.platformapi.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.platform.platformapi.api.controller.StationController;
import com.platform.platformapi.api.model.*;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StationService {

    private final XMLFiles xmlFiles;

    public StationService() {
        xmlFiles = new XMLFiles();
    }

    public Map<String, Map<String, ArrayList<String >>> getSection(String ril100, int myTrain, int myWaggon) throws Exception {

        InputStream xmlResource = StationController.class.getClassLoader().getResourceAsStream(xmlFiles.searchForRil100Key(ril100));
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(xmlResource);

        // star of the class!! maps the xml to the java-classes
        XmlMapper xmlMapper = new XmlMapper();
        //xmlMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Station station = xmlMapper.readValue(xmlStreamReader, Station.class);

        return station.searchForPlatform(myTrain, myWaggon);
    }

}
