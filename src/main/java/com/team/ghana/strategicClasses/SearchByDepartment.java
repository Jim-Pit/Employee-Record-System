package com.team.ghana.strategicClasses;

import com.team.ghana.SearchStrategy;
import com.team.ghana.employee.Employee;

import java.util.ArrayList;
import java.util.List;

public class SearchByDepartment implements SearchStrategy {
    @Override
    public List<Employee> execute(Long criteriaId, Iterable<Employee> allEmployees) {
        List<Employee> retrievedEmployees = new ArrayList<>();
        for (Employee emp:allEmployees) {
            if(emp.getUnit().getDepartment().getId() == criteriaId)
                retrievedEmployees.add(emp);
        }
        return retrievedEmployees;    }
}
