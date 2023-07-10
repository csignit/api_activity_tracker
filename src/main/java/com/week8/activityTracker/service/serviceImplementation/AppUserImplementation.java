package com.week8.activityTracker.service.serviceImplementation;

import com.week8.activityTracker.dto.request.AppUserLoginRequest;
import com.week8.activityTracker.dto.request.AppUserSignupRequest;
import com.week8.activityTracker.dto.response.AppUserLoginResponse;
import com.week8.activityTracker.dto.response.AppUserSignupResponse;
import com.week8.activityTracker.entity.AppUser;
import com.week8.activityTracker.exception.AppUserException;
import com.week8.activityTracker.repository.AppUserRepository;
import com.week8.activityTracker.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserImplementation implements AppUserService {
    private final AppUserRepository appUserRepository;

    @Override
    public AppUserSignupResponse registerUser(AppUserSignupRequest request) {
        Optional<AppUser> appUser = appUserRepository.findByEmail(request.getEmail());
        if (appUser.isPresent()) {
            throw new AppUserException("User already exist, provide a different email");
        }

            AppUser newAppUser = AppUser.builder()
                    .firstname(request.getFirstname())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .build();

            AppUser saveAppUser = appUserRepository.save(newAppUser);

            return AppUserSignupResponse.builder()
                    .id(saveAppUser.getId())
                    .firstname(saveAppUser.getFirstname())
                    .email(saveAppUser.getEmail())
                    .build();

    }



    @Override
    public AppUserLoginResponse loginUser(AppUserLoginRequest appUserLoginRequest, HttpServletRequest request) {
        Optional<AppUser> OptionalAppUser = appUserRepository.findByEmail(appUserLoginRequest.getEmail());
        if (OptionalAppUser.isEmpty()){
            throw new AppUserException("Invalid request, please check your credentials");
        }
        AppUser appUser = OptionalAppUser.get();
        if(!appUserLoginRequest.getPassword().equals(appUser.getPassword())){
            throw  new AppUserException("Invalid email or password");
        }
        HttpSession session = request.getSession();
        session.setAttribute("appUser",appUser);

        return AppUserLoginResponse.builder()
                .email(appUser.getEmail())
                .id(appUser.getId()).build();





    }
}
