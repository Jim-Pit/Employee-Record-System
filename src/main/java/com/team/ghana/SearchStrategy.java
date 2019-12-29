package com.team.ghana;

import com.team.ghana.employee.Employee;

import java.util.List;

public interface SearchStrategy {

    List<Employee> execute(Long criteriaId, Iterable<Employee> allEmployeess);

}
