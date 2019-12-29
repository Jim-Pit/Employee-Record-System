package com.team.ghana.employee;

import com.team.ghana.Factory;
import com.team.ghana.SearchStrategy;
import com.team.ghana.errorHandling.CustomError;
import com.team.ghana.errorHandling.GenericResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<Employee> getEmployeesByCriteria(Long criteriaId, String criteria){
        SearchStrategy strategy = Factory.choose(criteria);
        return strategy.execute(criteriaId,employeeRepository.findAll());
    }

    public GenericResponse getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();

        return new GenericResponse<>(employeeMapper.mapEmployeeListToEmployeeFullResponseList(employeeList));
    }

    public GenericResponse getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findEmployeeById(employeeId);

        return employee == null ?
                new GenericResponse<>(
                        new CustomError(
                                0,
                                "Bad Input",
                                "Employee with ID: " + employeeId + " doesn't exist."
                        )
                ) :
                new GenericResponse<>(employeeMapper.mapEmployeeToEmployeeFullResponse(employee));
    }

}
