package com.week8.activityTracker.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class AppUser {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length=100, nullable = false)
    private String firstname;
    @Column(unique = true)
    private String email;
    @Column(length=100, nullable = false)
    private String password;

    @OneToMany
    private List<Task> tasks = new ArrayList<>();

}
