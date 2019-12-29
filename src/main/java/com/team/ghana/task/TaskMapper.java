package com.team.ghana.task;

import com.team.ghana.employee.EmployeeMapper;
import com.team.ghana.employee.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    @Autowired
    EmployeeMapper employeeMapper;

    public List<TaskResponse> mapEmployees_TasksToTasksResponse(List<Task> tasks) {
        return tasks.stream()
                .map(this::mapTaskToTaskResponse)
                .collect(Collectors.toList());
    }

    public TaskResponse mapTaskToTaskResponse(Task task) {
        if(task!=null)
            return new TaskResponse(
                    task.getId(),
                    task.getTitle(),
                    task.getDesc(),
                    eazyOrPeazy(task),
                    task.getStatus(),
                    task.getEmployees().size(),
                    getTask_sEmployees(task)
                    );
        else
            return null;
    }

    private List<EmployeeResponse> getTask_sEmployees(Task task){
        return employeeMapper.mapEmployeeListToEmployeeResponseList(task.getEmployees());
    }

    private String eazyOrPeazy(Task task) {
        int avg = (task.getEstA()+task.getEstB()+task.getEstC())/3;
        if(avg<=2)
            return  "EASY";
        else if(avg<=4)
            return "MEDIUM";
        else
            return "HARD";
    }

    public List<Long> employee_sTaskIDs(List<Task> tasks) {
        List<Long> tasks_Ids = new ArrayList<>();
        for(Task task:tasks){
            tasks_Ids.add(task.getId());
        }
        return tasks_Ids;
    }
}
