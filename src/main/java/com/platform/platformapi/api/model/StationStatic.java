package com.platform.platformapi.api.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

public class StationStatic {

    private final String dir = "src/main/resources/xml";
    private final Map<String, String> ril100Map;

    public StationStatic() {
        String[] filenameList = listFiles(this.dir);
        this.ril100Map = new HashMap<>();
        this.splitStrings(filenameList);
    }

    public Document getDocument(String ril100) throws Exception {
        return readXMLDocumentFromFile(this.searchForRil100Key(ril100));
    }

    public static Document readXMLDocumentFromFile(String filename) throws Exception {
        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Build Document
        Document document = builder.parse(new File(filename));

        //Normalize the XML Structure;
        document.getDocumentElement().normalize();
        return document;
    }

    public Map<String, Map<String, ArrayList<String>>> searchInXMLForPlatform(Document doc, int myTrain, int myWaggon) {

        NodeList tracks = doc.getElementsByTagName("track");
        Map<String, Map<String, ArrayList<String>>> platformResult = new HashMap<>();

        for (int i = 0; i < tracks.getLength(); i++) {
            Element trackElement = (Element) tracks.item(i);
            NodeList trainList = trackElement.getElementsByTagName("train");
            for (int j = 0; j < trainList.getLength(); j++) {
                Element trainElement = (Element) trainList.item(j);
                NodeList trainNumberList = trainElement.getElementsByTagName("trainNumber");
                for (int k = 0; k < trainNumberList.getLength(); k++) {
                    int trainNumber = trainNumberList.item(0).getTextContent().isEmpty() ? -1 : Integer.parseInt(trainNumberList.item(0).getTextContent());
                    if (trainNumber == myTrain) {
                        // train found
                        NodeList waggonList = trainElement.getElementsByTagName("waggon");
                        Map<String, ArrayList<String>> platforms = new HashMap<>();
                        for (int l = 0; l < waggonList.getLength(); l++) {
                            Element waggonElement = (Element) waggonList.item(l);
                            NodeList waggonNumberList = waggonElement.getElementsByTagName("number");
                            int waggonNumber = waggonNumberList.item(0).getTextContent().isEmpty() ? -1 : Integer.parseInt(waggonNumberList.item(0).getTextContent());
                            if (waggonNumber == myWaggon) {
                                // train & waggon found
                                ArrayList<String> identifier = new ArrayList<>();
                                for (int m = 0; m < waggonElement.getElementsByTagName("identifier").getLength(); m++) {
                                    identifier.add(waggonElement.getElementsByTagName("identifier").item(m).getTextContent());
                                /* test for file-name
                                identifier.add(this.searchForRil100Key(ril100));*/
                                }
                                platforms.put("sections", identifier);
                                platformResult.put(trackElement.getElementsByTagName("name").item(0).getTextContent(), platforms);
                            }
                        }
                    }
                }
            }
        }
        return platformResult;
    }

    public String[] listFiles(String dir) {
        File directory = new File(dir);
        return directory.list();
    }

    public void splitStrings(String[] fileList) {
        for (String file : fileList) {
            String[] split = file.split("_");
            this.ril100Map.put(split[0], file);
        }
    }

    public String searchForRil100Key(String key) {
        return this.dir + "/" + this.ril100Map.get(key);
    }

    public void sort(Map<String, String> ril100Map) {
        // TO-DO: Sorting, to get Values faster?
    }
}
