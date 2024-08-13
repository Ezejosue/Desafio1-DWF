package com.boombastic.model;

public class Department {

    private int id;
    private String dept_name;
    private String dept_description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getDept_description() {
        return dept_description;
    }

    public void setDept_description(String dept_description) {
        this.dept_description = dept_description;
    }
}
