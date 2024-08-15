package com.boombastic.model;

public class Recruitment {

    private int id;
    private String deptName;
    private String employeeName;
    private String positionName;
    private TypeRecruitment typeRecruitment;
    private String date_recr;
    private double salary;
    private boolean status;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public TypeRecruitment getTypeRecruitment() {
        return typeRecruitment;
    }

    public void setTypeRecruitment(TypeRecruitment typeRecruitment) {
        this.typeRecruitment = typeRecruitment;
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
