package com.week8.activityTracker.dto.request;

import com.week8.activityTracker.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDeleteRequest {
    private Long AppUserId;
    private Long TaskId;

}
