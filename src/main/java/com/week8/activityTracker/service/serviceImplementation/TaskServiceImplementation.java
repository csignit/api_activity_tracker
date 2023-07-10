package com.week8.activityTracker.service.serviceImplementation;

import com.week8.activityTracker.dto.request.TaskDeleteRequest;
import com.week8.activityTracker.dto.request.TaskRequest;
import com.week8.activityTracker.dto.response.TaskDeleteResponse;
import com.week8.activityTracker.dto.response.TaskEditedResponse;
import com.week8.activityTracker.dto.response.TaskResponse;
import com.week8.activityTracker.entity.AppUser;
import com.week8.activityTracker.entity.Task;
import com.week8.activityTracker.entity.TaskStatus;
import com.week8.activityTracker.exception.AppUserException;
import com.week8.activityTracker.repository.AppUserRepository;
import com.week8.activityTracker.repository.TaskRepository;
import com.week8.activityTracker.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImplementation implements TaskService {

    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;

    @Override
    public TaskResponse createTask(TaskRequest request, Long appUserId) {
        Optional<AppUser> OptionalAppUser = appUserRepository.findById(appUserId);
        if (OptionalAppUser.isEmpty()){
            throw new AppUserException("Sorry, User does not exist");
        }
        AppUser appUser = OptionalAppUser.get();
        // Creating new Task
        Task newTask = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(TaskStatus.PENDING) //Created task set at pending as default
                .createdAt(LocalDateTime.now())
                .appUser(appUser)
                .build();
        Task savedTask = taskRepository.save(newTask);

        return TaskResponse.builder()
                .id(savedTask.getId())
                .title(savedTask.getTitle())
                .description(savedTask.getDescription())
                .taskStatus(savedTask.getStatus())
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Override
    public TaskEditedResponse editTask(TaskRequest request, Long taskId, Long appUserId) {

        Optional<AppUser> optionalAppUser = appUserRepository.findById(appUserId);
        if (optionalAppUser.isEmpty()){
            throw  new AppUserException("You are not the Authorized User", HttpStatus.BAD_REQUEST);
        } else {
            AppUser appUser = optionalAppUser.get();
            // check if the task trying to be updated actually exists in my database
            Optional<Task> optionalTask = taskRepository.findById(taskId);
            if (optionalTask.isEmpty()) {
                throw new AppUserException("Sorry, task does not exist", HttpStatus.NO_CONTENT);
            }else {
                Task existingTask = optionalTask.get();
                if (!existingTask.getAppUser().getId().equals(appUser.getId())){
                    throw new AppUserException("You are not authorised to edit this task", HttpStatus.BAD_REQUEST);
                }

                Task editedTask = optionalTask.get();
                Task.builder()
                        .title(request.getTitle())
                        .description(request.getDescription())
                        .editedAt(LocalDateTime.now())
                        .build();

                Task updatedTask = taskRepository.save(editedTask);

                return TaskEditedResponse.builder()
                        .id(updatedTask.getId())
                        .title(updatedTask.getTitle())
                        .description(updatedTask.getDescription())
                        .taskStatus(updatedTask.getStatus())
                        .editedAt(LocalDateTime.now())
                        .build();
            }
        }
    }

    @Override
    public TaskDeleteResponse deleteTask(Long taskId, Long appUserId) {
        Optional<AppUser> appUser = appUserRepository.findById(appUserId);
        if (appUser.isEmpty()){
            throw new AppUserException("User not in the Database");
        }else {
            AppUser existingUser = appUser.get();
            Optional<Task> optionalTask = taskRepository.findById(taskId);
            if(optionalTask.isEmpty()){
                throw new AppUserException("Task is not available", HttpStatus.NO_CONTENT);
            } else {
                Task existingTask = optionalTask.get();

                //compare the Id of user with AppUserId that created the task
                if (!existingTask.getAppUser().getId().equals(existingTask.getId())){
                    throw new AppUserException("You are not eligible to delete this task", HttpStatus.FORBIDDEN);
                }
                taskRepository.deleteById(taskId);
                return TaskDeleteResponse.builder()
                        .taskId(taskId)
                        .message("Your task have been successfully deleted")
                        .build();

                }
            }
        }

    @Override
    public TaskResponse viewATask(Long appUserId, Long taskId) {
        Optional<AppUser> optionalAppUser = appUserRepository.findById(appUserId);
        if (optionalAppUser.isEmpty()) {
            throw new AppUserException("User not found");
        } else {
            AppUser appUser = optionalAppUser.get();
            Optional<Task> tasks = taskRepository.findTaskByAppUserAndId(appUser, taskId);
            if (tasks.isEmpty()) {
                throw new AppUserException("Task not found");
            }else {
                Task task = tasks.get();
            return TaskResponse.builder()
                    .id(task.getId())
                    .title(task.getTitle())
                    .description(task.getDescription())
                    .taskStatus(task.getStatus())
                    .createdAt(task.getCreatedAt())
                    .build();
            }
        }
    }

    @Override
    public TaskResponse moveTaskStatus(TaskStatus taskStatus, Long taskId, Long appUserId) {
        return null;
    }

    @Override
    public List<TaskResponse> viewAllTask(Long appUserId) {
        Optional<AppUser> optionalAppUser = appUserRepository.findById(appUserId);
        if (optionalAppUser.isEmpty()){
            throw new AppUserException("User not found");
        }else {
            AppUser appUser = optionalAppUser.get();
            List<Task> tasks = taskRepository.findByAppUser(appUser);
            return  tasks.stream().map(task -> TaskResponse.builder()
                    .id(task.getId())
                    .title(task.getTitle())
                    .description(task.getDescription())
                    .taskStatus(task.getStatus())
                    .createdAt(task.getCreatedAt())
                    .build()).toList();
        }
    }

    @Override
    public List<TaskResponse> viewAllPendingTask(TaskRequest request) {
        return null;
    }

    @Override
    public List<TaskResponse> viewAllDoneTask(TaskRequest request) {
        return null;
    }

    @Override
    public List<TaskResponse> viewAllInProgressTask(TaskRequest request) {
        return null;
    }
}
