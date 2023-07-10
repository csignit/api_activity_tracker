package com.week8.activityTracker.controller;

import com.week8.activityTracker.dto.request.AppUserLoginRequest;
import com.week8.activityTracker.dto.request.AppUserSignupRequest;
import com.week8.activityTracker.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody @Valid AppUserSignupRequest request){
        var response = appUserService.registerUser(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser (@RequestBody AppUserLoginRequest appUserLoginRequest, HttpServletRequest request){
        var loginResponse = appUserService.loginUser(appUserLoginRequest, request);
        return ResponseEntity.ok(loginResponse);
    }
}
