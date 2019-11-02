package org.domotics.hub.model;

import java.util.UUID;

public class Terminal {
    private String controllerId;
    private int pin;
    private String description;

    public String getControllerId() {
        return controllerId;
    }

    public Terminal setControllerId(String id) {
        this.controllerId = id;
        return this;
    }

    public int getPin(){
        return pin;
    }

    public Terminal setPin(int pin){
        this.pin = pin;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Terminal setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("{ ");
        buffer.append("\"controllerId\": \"").append(controllerId).append("\", ");
        buffer.append("\"pin\": ").append(pin).append(", ");
        buffer.append("\"description\": \"").append(description).append("\" ");
        buffer.append(" }");
        return  buffer.toString();
    }
}
