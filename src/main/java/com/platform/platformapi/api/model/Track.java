package org.example;

public class Track {

    private String name;
    private int number;
    private Train[] trains;
    private String[] sections;

    public Track() {

    }

    public Track(String name, int number) {
        this.name = name;
        this.number = number;
        //this.trains = trains;
        //this.sections = sections;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Train[] getTrains() {
        return trains;
    }

    public void setTrains(Train[] trains) {
        this.trains = trains;
    }

    public String[] getSections() {
        return sections;
    }

    public void setSections(String[] sections) {
        this.sections = sections;
    }
}
