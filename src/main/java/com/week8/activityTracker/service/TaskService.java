package com.week8.activityTracker.service;

import com.week8.activityTracker.dto.request.TaskDeleteRequest;
import com.week8.activityTracker.dto.request.TaskRequest;
import com.week8.activityTracker.dto.response.TaskDeleteResponse;
import com.week8.activityTracker.dto.response.TaskEditedResponse;
import com.week8.activityTracker.dto.response.TaskResponse;
import com.week8.activityTracker.entity.TaskStatus;

import java.util.List;

public interface TaskService {
    TaskResponse createTask (TaskRequest request, Long appUserId);
    TaskEditedResponse editTask (TaskRequest request, Long appUserId, Long taskId );
    TaskDeleteResponse deleteTask (Long taskId, Long appUserId);
    TaskResponse viewATask (Long appUserId, Long taskId);
    TaskResponse  moveTaskStatus (TaskStatus taskStatus, Long taskId, Long appUserId);
    List <TaskResponse> viewAllTask (Long appUserId);
    List <TaskResponse> viewAllPendingTask (TaskRequest request);
    List <TaskResponse> viewAllDoneTask (TaskRequest request);
    List <TaskResponse> viewAllInProgressTask (TaskRequest request);

}
