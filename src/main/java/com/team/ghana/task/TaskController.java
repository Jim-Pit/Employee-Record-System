package com.team.ghana.task;

import com.team.ghana.errorHandling.GenericResponse;
import jdk.internal.jline.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("ghana/tasks")
public class TaskController {

    @Autowired
    TaskService service;

    @GetMapping("/{id}")
    public GenericResponse getTaskById(@PathVariable Long id){
        return service.getTask(id);
    }

    @GetMapping("")
    public GenericResponse getTasks(){
        return service.getTasks();
    }

    @GetMapping("/{difficulty}")
    public GenericResponse getTasksByCriteria(@PathVariable String difficulty) {
        return new GenericResponse<>(service.getTasksByDifficulty(difficulty.toUpperCase()));
    }

    @GetMapping("/{difficulty}/{assignedEmployees}")
    public GenericResponse getTasksByCriteria(@PathVariable String difficulty, @PathVariable int assignedEmployees){
        return service.getTasksByCriteria(difficulty.toUpperCase(), assignedEmployees);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/post")
    @ResponseBody
    public ResponseEntity addTask(@Valid @RequestBody Task task) {
        ResponseEntity response = new ResponseEntity(
                service.postTask(task),
                null,
                HttpStatus.OK
        );
        return response;
        // return service.postTask(task);
    }
}
