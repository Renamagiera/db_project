package com.platform.platformapi.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.platform.platformapi.api.controller.StationController;
import com.platform.platformapi.api.model.Station;
import com.platform.platformapi.api.model.XMLFiles;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class StationService {

    private final XMLFiles xmlFiles;
    public StationService() {
        xmlFiles = new XMLFiles();
    }

    /**
     * At this point, the mapping is called using the Jackson framework.
     * The Station class is passed as a stream.
     * */
    public Map<String, Map<String, List<String>>> getSection(String ril100, int myTrain, int myWaggon) throws Exception {

        InputStream xmlResource = StationController.class.getClassLoader().getResourceAsStream(xmlFiles.searchForRil100Key(ril100));
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(xmlResource);

        // star of the class <3 maps the xml to the java-classes
        XmlMapper xmlMapper = new XmlMapper();
        Station station = xmlMapper.readValue(xmlStreamReader, Station.class);

        return station.searchForPlatform(myTrain, myWaggon);
    }
}
