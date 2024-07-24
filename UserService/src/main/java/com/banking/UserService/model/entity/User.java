package com.banking.UserService.model.entity;

import com.banking.UserService.model.Status;
import com.banking.UserService.model.dto.UserProfileDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private String password;

    private String emailId;

    private String contactNo;

    private String authId;

    private String identificationNumber;

    @CreationTimestamp
    private LocalDate creationOn;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_id", referencedColumnName = "userProfileId")
    private UserProfile userProfile;
}
