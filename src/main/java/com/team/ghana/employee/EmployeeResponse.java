package com.team.ghana.employee;

public class EmployeeResponse {

    private Long id;
    private String fullName;
    private String phoneNumber;
    private String unitName;
    private String position;

    public EmployeeResponse(Long id, String fullName, String phoneNumber, String unitName, String position) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.unitName = unitName;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
