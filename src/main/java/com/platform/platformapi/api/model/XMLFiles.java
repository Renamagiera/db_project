package com.platform.platformapi.api.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class XMLFiles {

    private final Map<String, String> ril100Map;
    private final String dir = "src/main/resources/xml/";

    public XMLFiles() {
        this.ril100Map = new HashMap<>();
        this.splitFilenamesToMap(this.listFiles(this.dir));
    }

    private String[] listFiles(String dir) {
        File directory = new File(dir);
        return directory.list();
    }

    private void splitFilenamesToMap(String[] fileList) {
        for (String file : fileList) {
            String[] split = file.split("_");
            this.ril100Map.put(split[0], file);
        }
    }

    public String searchForRil100Key(String key) {
        return "xml/" + this.ril100Map.get(key);
    }

}
