package com.week8.activityTracker.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AppUserLoginRequest {
    @Email(message = "Please provide a valid email")
    @NotBlank (message = "Email is required, please provide a valid email")
    private String email;

    @NotBlank(message = "Provide your password")
    private String password;
}
