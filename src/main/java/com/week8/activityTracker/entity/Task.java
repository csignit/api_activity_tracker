package com.week8.activityTracker.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Task {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String title;

    @Column (length = 254,nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @CreatedDate
    @Column (updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime editedAt;

    private LocalDateTime completedAt;

    @ManyToOne
    @JoinColumn
    private AppUser appUser;



}
