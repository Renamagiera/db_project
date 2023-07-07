package com.platform.platformapi.api.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Station {

    public String ril100;
    public String name;

    public ArrayList<String> tracks = new ArrayList<>();
    public ArrayList<String> trains = new ArrayList<>();
    public ArrayList<Integer> wagons = new ArrayList<>();
    public String section;

    private static final String FILENAME = "src/main/resources/xml/FF_2017-12-01_10-47-17.xml";
    public NodeList trackNodeList;

    public Station() {
        this.readDataFromXML();
    }

    // getter & setter


    public String getRil100() {
        return ril100;
    }

    public void setRil100(String ril100) {
        this.ril100 = ril100;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<String> tracks) {
        this.tracks = tracks;
    }

    public ArrayList<String> getTrains() {
        return trains;
    }

    public void setTrains(ArrayList<String> trains) {
        this.trains = trains;
    }

    public ArrayList<Integer> getWagons() {
        return wagons;
    }

    public void setWagons(ArrayList<Integer> wagons) {
        this.wagons = wagons;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public NodeList getTrackNodeList() {
        return trackNodeList;
    }

    public void setTrackNodeList(NodeList trackNodeList) {
        this.trackNodeList = trackNodeList;
    }

    public void readDataFromXML() {
        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(FILENAME));
            doc.getDocumentElement().normalize();

            this.ril100 = doc.getElementsByTagName("shortcode").item(0).getTextContent();
            this.name = doc.getElementsByTagName("name").item(0).getTextContent();
            this.trackNodeList = doc.getElementsByTagName("track");

            for (int i = 0; i < this.trackNodeList.getLength(); i++) {
                Node node = this.trackNodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    this.tracks.add(element.getElementsByTagName("name").item(0).getTextContent());
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

}