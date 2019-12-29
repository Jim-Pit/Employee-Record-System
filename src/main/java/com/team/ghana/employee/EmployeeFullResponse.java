package com.team.ghana.employee;

import com.team.ghana.task.TaskResponse;

import java.util.List;
import java.util.Objects;

public class EmployeeFullResponse {

    private Long id;
    private String fullName;
    private String homeAddress;
    private String phoneNumber;
    private String workingPeriod;
    private String status;
    private String contractType;
    private String companyName;
    private String unitName;
    private String position;
    //private List<TaskResponse> tasks;
    private List<Long> taskIDs;

    public EmployeeFullResponse(Long id, String fullName, String homeAddress, String phoneNumber, String workingPeriod, String status, String contractType, String companyName, String unitName, String position, List<Long> tasks) {
        this.id = id;
        this.fullName = fullName;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.workingPeriod = workingPeriod;
        this.status = status;
        this.contractType = contractType;
        this.companyName = companyName;
        this.unitName = unitName;
        this.position = position;
        this.taskIDs = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWorkingPeriod() {
        return workingPeriod;
    }

    public void setWorkingPeriod(String workingPeriod) {
        this.workingPeriod = workingPeriod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public List<Long> getTaskIDs() {
        return taskIDs;
    }

    public void setTaskIDs(List<Long> taskIDs) {
        this.taskIDs = taskIDs;
    }

    //    public List<TaskResponse> getTasks() {
//        return tasks;
//    }
//
//    public void setTasks(List<TaskResponse> tasks) {
//        this.tasks = tasks;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeFullResponse that = (EmployeeFullResponse) o;
        return  id.equals(that.id) &&
                fullName.equals(that.fullName) &&
                homeAddress.equals(that.homeAddress) &&
                phoneNumber.equals(that.phoneNumber) &&
                workingPeriod.equals(that.workingPeriod) &&
                status.equals(that.status) &&
                contractType.equals(that.contractType) &&
                companyName.equals(that.companyName) &&
                unitName.equals(that.unitName) &&
                position.equals(that.position) &&
                taskIDs.equals(that.taskIDs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, homeAddress, phoneNumber, workingPeriod, status, contractType, companyName, unitName, position, taskIDs);
    }
}
