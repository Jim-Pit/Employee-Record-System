package com.team.ghana.employee;

import com.team.ghana.task.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    @Autowired
    TaskMapper taskMapper;

    public EmployeeFullResponse mapEmployeeToEmployeeFullResponse(Employee employee) {
        return new EmployeeFullResponse(
                employee.getId(),
                mapEmployeeFullName(employee),
                employee.getHomeAddress(),
                employee.getPhoneNumber(),
                mapEmployeeWorkingPeriod(employee),
                employee.getStatus().toString(),
                employee.getContractType().toString(),
                employee.getCompanyName(),
                employee.getUnit().getName(),
                employee.getPosition(),
                //taskMapper.mapEmployee_sTasksToTasksResponse(employee.getTasks())
                taskMapper.employee_sTaskIDs(employee.getTasks())
        );
    }

    public List<EmployeeFullResponse> mapEmployeeListToEmployeeFullResponseList(List<Employee> employeeList) {

        return employeeList.stream()
                .map(this::mapEmployeeToEmployeeFullResponse)
                .collect(Collectors.toList());
    }

    public EmployeeResponse mapEmployeeToEmployeeResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                mapEmployeeFullName(employee),
                employee.getPhoneNumber(),
                employee.getUnit().getName(),
                employee.getPosition()
                );
    }

    public List<EmployeeResponse> mapEmployeeListToEmployeeResponseList(List<Employee> employeeList) {

        return employeeList.stream()
                .map(this::mapEmployeeToEmployeeResponse)
                .collect(Collectors.toList());
    }

    private String mapEmployeeWorkingPeriod(Employee employee) {

        String hireDate = employee.getHireDate() == null ?
                "[Missing Hire Date]" :
                employee.getHireDate().toString();

        String dismissalDate = employee.getHireDate() == null ?
                "Contact Administration" :
                employee.getRedundancyDate() == null ?
                        "Present" :
                        employee.getRedundancyDate().toString();

        return hireDate + " ---> " + dismissalDate;
    }
    private String mapEmployeeFullName(Employee employee) {

        String firstName = employee.getFirstName() == null || "".equals(employee.getFirstName()) ?
                "[Missing First Name]" :
                employee.getFirstName();
        String lastName = employee.getLastName() == null || "".equals(employee.getLastName()) ?
                "[Missing Last Name]" :
                employee.getLastName();


        return firstName + " " + lastName;
    }
}

