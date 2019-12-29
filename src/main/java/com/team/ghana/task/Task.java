package com.team.ghana.task;

import com.sun.org.apache.xpath.internal.objects.XBoolean;
import com.team.ghana.employee.Employee;
import com.team.ghana.enums.TaskStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {

    /*
    (id, title, desc,
    estimationA : int (οι μέρες που χρειάζεται για να υλοποηθεί), estimationB: int,  estimationC: int,
    status[NEW, STARTED, DONE], updates List<String>)
    */

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String desc;
    private int estA;
    private int estB;
    private int estC;
    private TaskStatus status;

//    private List<String> updates;

    @ManyToMany
    private List<Employee> employees;

    public Task() {
    }

    public Task(String title, String desc, int estA, int estB, int estC, TaskStatus status) {
        this.title = title;
        this.desc = desc;
        this.estA = estA;
        this.estB = estB;
        this.estC = estC;
        this.status = status;
        this.employees = new ArrayList<>();
        //this.updates = new ArrayList<String>();
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

    public int getEstA() {
        return estA;
    }

    public void setEstA(int estA) {
        this.estA = estA;
    }

    public int getEstB() {
        return estB;
    }

    public void setEstB(int estB) {
        this.estB = estB;
    }

    public int getEstC() {
        return estC;
    }

    public void setEstC(int estC) {
        this.estC = estC;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    /*
    public List<String> getUpdates() {
        return updates;
    }

    public void setUpdates(List<String> updates) {
        this.updates = updates;
    }

    public void addUpdate(String update) {
        this.updates.add(update);
    }
     */

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
         this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        // don't add an employee to some task's employees if another employee of the same unit has been assigned this task
        // not necessary as we check that a task is not assigned to employees of the same unit in the first place
        /*
        boolean sameUnit = false;
        for(Employee e : this.employees){
            if(e.getUnit().equals(employee.getUnit()))
                sameUnit = true;
        }
        if(!sameUnit)
         */

        boolean exists = false;
        for(Employee e : this.employees){
            if(e.getId()==employee.getId())
                exists=true;
        }
        if(!exists) {
            (this.employees).add(employee);
            employee.addTask(this);
        }
    }

}
