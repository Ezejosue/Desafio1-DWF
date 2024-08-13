package com.boombastic.model;

public class Recruitment {

    private int id;
    private int id_dept;
    private int id_employee;
    private int id_position;
    private String date_recr;
    private double salary;
    private boolean status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_dept() {
        return id_dept;
    }

    public void setId_dept(int id_dept) {
        this.id_dept = id_dept;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public int getId_position() {
        return id_position;
    }

    public void setId_position(int id_position) {
        this.id_position = id_position;
    }

    public String getDate_recr() {
        return date_recr;
    }

    public void setDate_recr(String date_recr) {
        this.date_recr = date_recr;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
