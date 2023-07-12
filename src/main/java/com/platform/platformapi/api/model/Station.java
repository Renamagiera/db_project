package com.platform.platformapi.api.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement
public class Station {

    private String shortcode;
    private String name;
    private ArrayList<Track> tracks;

    private final Map<String, String> ril100Map;
    private final String dir = "src/main/resources/xml";
    private Document document;
    private XPath xPath;

    public Station() {
        this.tracks = new ArrayList<>();
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

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public String searchForPlatform(String shortcode, int myTrain, int myWaggon) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            this.document = builder.parse(searchForRil100Key(shortcode));

            // create XPathFactory object
            XPathFactory xPathFactory = XPathFactory.newInstance();

            // create XPath object
            this.xPath = xPathFactory.newXPath();

            // fill station attributes
            this.fillStationAttributes();

            // create and fill track-objects
            this.createTrackObjects();
            this.fillTrackNamesFromXML();
            this.fillTrackNumbersFromXML();
            // DEBUG: this.getTrackSectionsFromXML(document, xPath);

            // create and fill train-objects
            this.createTrainObjects();
            this.fillTrainNamesFromXML();

            // create and fill waggon-objects
            this.createWaggonObjects();

        } catch (ParserConfigurationException | SAXException | IOException exception) {
            exception.printStackTrace();
        }
        return String.valueOf(this.getTracks().get(0).getTrains().get(0).getWaggons().size());
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

    // fill station attributes
    private void fillStationAttributes() {
        try {
            //create XPathExpression object
            XPathExpression stationShortcode = this.xPath.compile("/station/shortcode");
            XPathExpression stationName = this.xPath.compile("/station/name");
            //evaluate expression result on XML document
            this.setShortcode((String) stationShortcode.evaluate(this.document, XPathConstants.STRING));
            this.setName((String) stationName.evaluate(this.document, XPathConstants.STRING));
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    // create and fill track-objects
    private void createTrackObjects() {
        try {
            //create XPathExpression object
            XPathExpression trackObjects = this.xPath.compile("/station/tracks/track");

            //evaluate expression result on XML document
            NodeList tracks = (NodeList) trackObjects.evaluate(this.document, XPathConstants.NODESET);
            for (int i = 0; i < tracks.getLength(); i++) {
                Track track = new Track();
                this.getTracks().add(track);
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    private void fillTrackNamesFromXML() {
        try {
            //create XPathExpression object
            XPathExpression expressionTrackNames = this.xPath.compile("/station/tracks/track/name");

            //evaluate expression result on XML document
            NodeList trackNames = (NodeList) expressionTrackNames.evaluate(this.document, XPathConstants.NODESET);

            for (int i = 0; i < trackNames.getLength(); i++) {
                this.getTracks().get(i).setName(trackNames.item(i).getTextContent().isEmpty() ? "" : trackNames.item(i).getTextContent());
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    private void fillTrackNumbersFromXML() {
        try {
            //create XPathExpression object
            XPathExpression expressionTrackNumbers = this.xPath.compile("/station/tracks/track/number");

            //evaluate expression result on XML document
            NodeList trackNumbers = (NodeList) expressionTrackNumbers.evaluate(this.document, XPathConstants.NODESET);
            for (int i = 0; i < trackNumbers.getLength(); i++) {
                this.getTracks().get(i).setNumber(trackNumbers.item(i).getTextContent().isEmpty() ? -1 : Integer.parseInt(trackNumbers.item(i).getTextContent()));
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    private void fillTrackSectionsFromXML() {
        try {
            //create XPathExpression object
            XPathExpression expressionTrackSections = this.xPath.compile("/station/tracks/track/sections");

            //evaluate expression result on XML document
            NodeList trackSections = (NodeList) expressionTrackSections.evaluate(this.document, XPathConstants.NODESET);
            for (int i = 0; i < trackSections.getLength(); i++) {
                NodeList identifier = trackSections.item(i).getChildNodes();
                for (int j = 0; j < identifier.getLength(); j++) {
                    this.getTracks().get(i).getSections().add(identifier.item(j).getTextContent().isEmpty() ? "" : identifier.item(j).getTextContent());
                }
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    // create and fill train-objects
    private void createTrainObjects() {
        try {
            for (Track track : this.getTracks()) {
                //create XPathExpression object
                XPathExpression trainObjects = this.xPath.compile("/station/tracks/track[name = '"+ track.getName() + "']/trains/train");

                //evaluate expression result on XML document
                NodeList trains = (NodeList) trainObjects.evaluate(this.document, XPathConstants.NODESET);
                for (int i = 0; i < trains.getLength(); i++) {
                    Train train = new Train();
                    track.getTrains().add(train);
                }
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    private void fillTrainNamesFromXML() {
        try {
            for (Track track : this.getTracks()) {

                //create XPathExpression object
                XPathExpression expressionTrainNumbers = this.xPath.compile("/station/tracks/track[name = '"+ track.getName() + "']/trains/train/trainNumbers/trainNumber");

                //evaluate expression result on XML document
                String trainNumbers = (String) expressionTrainNumbers.evaluate(this.document, XPathConstants.STRING);

                for (Train train : track.getTrains()) {
                    train.getTrainNumbers().add(Integer.parseInt(trainNumbers));
                }

            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    // create and fill waggon-objects
    private void createWaggonObjects() {
        try {
            for (Track track : this.getTracks()) {
                for (Train train : track.getTrains()) {
                    //create XPathExpression object
                    XPathExpression waggonObjects = this.xPath.compile("/station/tracks/track[name = '"+ track.getName() + "']/trains/train[trainNumbers/trainNumber = '"+ train.getName() + "']/waggons/waggon");

                    //evaluate expression result on XML document
                    NodeList waggons = (NodeList) waggonObjects.evaluate(this.document, XPathConstants.NODESET);
                    for (int i = 0; i < waggons.getLength(); i++) {
                        Waggon waggon = new Waggon();
                        train.getWaggons().add(waggon);
                    }
                }
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
