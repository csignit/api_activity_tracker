package com.week8.activityTracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AppUserSignupResponse {
    private Long id;
    private String firstname;
    private String email;
}
