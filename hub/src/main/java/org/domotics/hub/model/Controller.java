package org.domotics.hub.model;

import java.util.UUID;

public class Controller {
    private String id;
    private String description;

    public Controller(){
        this.id = UUID.randomUUID().toString();
        this.description = "new controller";
    }

    public String getId() {
        return id;
    }

    public Controller setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Controller setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("{ ");
        buffer.append("\"id\": \"").append(id).append("\", ");
        buffer.append("\"description\": \"").append(description).append("\" ");
        buffer.append(" }");
        return  buffer.toString();
    }

}
