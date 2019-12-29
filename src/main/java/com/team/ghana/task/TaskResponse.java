package com.team.ghana.task;

import com.team.ghana.employee.EmployeeResponse;
import com.team.ghana.enums.TaskStatus;

import java.util.List;

public class TaskResponse {

    private Long id;
    private String title;
    private String desc;
    private String difficulty;
    private TaskStatus status;
    private int assignedEmployees;
    private List<EmployeeResponse> employees;

    public TaskResponse(Long id, String title, String desc, String difficulty,
                        TaskStatus status, int assigned, List<EmployeeResponse> employees) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.difficulty = difficulty;
        this.status = status;
        this.assignedEmployees = assigned;
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public int getAssignedEmployees() {
        return assignedEmployees;
    }

    public void setAssignedEmployees(int assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

    public List<EmployeeResponse> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeResponse> employees) {
        this.employees = employees;
    }
}
