package com.week8.activityTracker.repository;

import com.week8.activityTracker.entity.AppUser;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface AppUserRepository extends JpaRepository <AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

}
