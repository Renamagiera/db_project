package com.platform.platformapi.api.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Station {

    public Document document = readXMLDocumentFromFile("src/main/resources/xml/FF_2017-12-01_10-47-17.xml");

    public String ril100;
    public String name;
    public ArrayList<String> tracks = new ArrayList<>();
    public Map<String, ArrayList<Integer>> trains = new HashMap<>();

    public Station() throws Exception {
        //this.readDataFromXML();
    }

    public Document getDocument() {
        return document;
    }

    public static Document readXMLDocumentFromFile(String fileNameWithPath) throws Exception {

        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Build Document
        Document document = builder.parse(new File(fileNameWithPath));

        //Normalize the XML Structure;
        document.getDocumentElement().normalize();

        return document;
    }

    public Map<String, Map<String, ArrayList<String>>> searchForPlatform(Document doc, String myTrain, String myWaggon) {

        NodeList tracks = doc.getElementsByTagName("track");
        NodeList trainList;
        NodeList trainNumberList;
        NodeList waggonNumberList;
        NodeList waggonList;

        Element trackElement;
        Element trainElement;
        Element waggonElement;

        Map<String, Map<String, ArrayList<String>>> platformResult = new HashMap<>();

        for (int i = 0; i < tracks.getLength(); i++) {
            trackElement = (Element) tracks.item(i);
            trainList = trackElement.getElementsByTagName("train");
            for (int j = 0; j < trainList.getLength(); j++) {
                trainElement = (Element) trainList.item(j);
                trainNumberList = trainElement.getElementsByTagName("trainNumber");
                if (trainNumberList.item(0).getTextContent().equals(myTrain)) {
                    // train found
                    waggonList = trainElement.getElementsByTagName("waggon");
                    Map<String, ArrayList<String>> platforms = new HashMap<>();
                    for (int k = 0; k < waggonList.getLength(); k++) {
                        waggonElement = (Element) waggonList.item(k);
                        waggonNumberList = waggonElement.getElementsByTagName("number");
                        if (waggonNumberList.item(0).getTextContent().equals(myWaggon)) {
                            // train & waggon found
                            ArrayList<String> identifier = new ArrayList<>();
                            for (int x = 0; x < waggonElement.getElementsByTagName("identifier").getLength(); x++) {
                                identifier.add(waggonElement.getElementsByTagName("identifier").item(x).getTextContent());
                            }
                            platforms.put("sections", identifier);
                            platformResult.put(trackElement.getElementsByTagName("name").item(0).getTextContent(), platforms);
                        }
                    }
                }
            }
        }
        return platformResult;
    }

    // TO-DO
    // search in xml-files for ril100

}
