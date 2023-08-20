package com.example.second_projects.modul;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    /* TODO: 1- method
    @SequenceGenerator(name = "user_id_seq",sequenceName = "user_seq_s",allocationSize = 1)
    @GeneratedValue(generator = "user_id_seq") */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;
}
