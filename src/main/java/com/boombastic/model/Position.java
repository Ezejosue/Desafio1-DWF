package com.boombastic.model;

public class Position {

    private int id;
    private String position;
    private String position_description;
    private int leadership;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition_description() {
        return position_description;
    }

    public void setPosition_description(String position_description) {
        this.position_description = position_description;
    }

    public int getLeadership() {
        return leadership;
    }

    public void setLeadership(int leadership) {
        this.leadership = leadership;
    }
}
