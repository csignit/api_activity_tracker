package com.week8.activityTracker.service;

import com.week8.activityTracker.dto.request.AppUserLoginRequest;
import com.week8.activityTracker.dto.request.AppUserSignupRequest;
import com.week8.activityTracker.dto.response.AppUserLoginResponse;
import com.week8.activityTracker.dto.response.AppUserSignupResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface AppUserService {
    AppUserSignupResponse registerUser (AppUserSignupRequest request);
    AppUserLoginResponse loginUser (AppUserLoginRequest  appUserLoginRequest,  HttpServletRequest request);


}
