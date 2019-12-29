package com.team.ghana.task;

import com.team.ghana.employee.Employee;
import com.team.ghana.employee.EmployeeRepository;
import com.team.ghana.errorHandling.CustomError;
import com.team.ghana.errorHandling.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TaskMapper taskMapper;

    public GenericResponse getTasks(){
//        Iterable<Task> tasks = repository.findAll();
//        List<Task> taskList = new ArrayList<>();
//        for(Task task:tasks){
//            taskList.add(task);
//        }
        return  new GenericResponse(taskMapper.mapEmployees_TasksToTasksResponse((List) taskRepository.findAll()));
    }

    public GenericResponse getTask(Long id){
        Task weAreLookingFor=null;
        if(!taskRepository.findById(id).isPresent())
            return new GenericResponse(new CustomError(159,"Wrong Input", "Non existent Id"));

        for (Task task: taskRepository.findAll()) {
            if(task.getId()==id) {
                weAreLookingFor = task;
                break;
            }
        }
        return new GenericResponse(taskMapper.mapTaskToTaskResponse(weAreLookingFor));
    }

    public GenericResponse postTask(Task task){
        if (task.getId() != null) {
            return new GenericResponse<>(new CustomError(0, "Error", "Task's ID is set automatically, do not try to set it"));
        }

        GenericResponse response = setEmployees_Properties(task);
        if(response.getError() != null) {
            return response;
        }

        Task addedTask = taskRepository.save(task);
        for(Employee employee : addedTask.getEmployees()){
            employee.addTask(task);
            employeeRepository.save(employee);
        }

        return new GenericResponse<>(taskMapper.mapTaskToTaskResponse(addedTask));
    }

    private GenericResponse setEmployees_Properties(Task task) {
        List<Employee> employees = new ArrayList<>();
        ArrayList<Long> ids = new ArrayList<>();
        for(Employee employee: task.getEmployees()) {
            Long currentId = employee.getId();
            Employee retrievedEmployee = employeeRepository.findEmployeeById(currentId);
            if(retrievedEmployee != null && !ids.contains(currentId)) {
                employees.add(retrievedEmployee);
                //retrievedEmployee.addTask(task);
                ids.add(currentId);
            } else if(ids.contains(currentId))
                continue;
            else {
                return new GenericResponse<>(new CustomError(0, "Error", "Employee with ID: " + employee.getId() + " does not exist"));
            }
        }
        task.setEmployees(employees);

        return new GenericResponse<>("Successful");
    }

    public List<TaskResponse> getTasksByDifficulty(String difficulty){
        Iterable<Task> tasks = taskRepository.findAll();
        List<TaskResponse> taskResponses = new ArrayList<>();
        for(Task task:tasks){
            taskResponses.add(taskMapper.mapTaskToTaskResponse(task));
        }
        List<TaskResponse> askedTasks = taskResponses.stream()
                .filter(task -> difficulty.equals(task.getDifficulty()))
                .collect(Collectors.toList());
        return askedTasks;
    }

    public GenericResponse getTasksByCriteria(String difficulty,int assignedEmployees){

        return new GenericResponse<>(getTasksByDifficulty(difficulty).stream()
                .filter(numOfEmployees -> numOfEmployees.getAssignedEmployees()>=assignedEmployees)
                .collect(Collectors.toList())
        );
    }
}
