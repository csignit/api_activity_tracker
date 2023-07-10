package com.week8.activityTracker.repository;

import com.week8.activityTracker.dto.response.TaskDeleteResponse;
import com.week8.activityTracker.entity.AppUser;
import com.week8.activityTracker.entity.Task;
import com.week8.activityTracker.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findById(Long id);
    Optional<Task> findTaskByStatusAndId(TaskStatus taskStatus, Long id);

    List<Task> findByAppUser(AppUser appUser);
    Optional<Task> findTaskByAppUserAndId(AppUser appUser, Long id );
}
