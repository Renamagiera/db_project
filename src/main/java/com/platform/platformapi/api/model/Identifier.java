package com.platform.platformapi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Identifier {
    private String identifier;

    public Identifier(String identifier) {
        this.identifier = identifier;
    }

    public Identifier() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
