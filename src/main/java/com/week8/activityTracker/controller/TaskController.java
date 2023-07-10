package com.week8.activityTracker.controller;

import com.week8.activityTracker.dto.request.TaskDeleteRequest;
import com.week8.activityTracker.dto.request.TaskRequest;
import com.week8.activityTracker.dto.response.TaskResponse;
import com.week8.activityTracker.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/createTask/{Id}")
    public ResponseEntity<?> createTask(@RequestBody @Valid TaskRequest request, @PathVariable("Id")Long Id){
        var response = taskService.createTask(request,Id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/editTask/{AppUserId}/{taskId}")
    public ResponseEntity<?> editedTask
            (@RequestBody TaskRequest request, @PathVariable("AppUserId")Long appUserId, @PathVariable("taskId")Long taskId) {
        var response = taskService.editTask(request, appUserId, taskId);
        return ResponseEntity.ok(response);



    }

    @DeleteMapping("/deleteTask/{AppUserId}/{TaskId}")
    public ResponseEntity<?> deleteTask
            (@RequestBody @Valid @PathVariable("AppUserId")Long appUserId, @PathVariable("TaskId")Long taskId ){
        var response = taskService.deleteTask(appUserId, taskId );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all/{AppUserId}")
    public ResponseEntity<List<TaskResponse>> viewAllTaskByUserId(@PathVariable("AppUserId") Long appUserId){
        List<TaskResponse>tasks = taskService.viewAllTask(appUserId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/task/{AppUserId}/{TaskId}")
    public ResponseEntity<TaskResponse> viewTaskByAppUserAndId (@PathVariable("AppUserId") Long appUserId,
                                                                   @PathVariable("TaskId")Long taskId){
        TaskResponse taskResponse = taskService.viewATask(appUserId, taskId);
        return ResponseEntity.ok(taskResponse);
    }





}