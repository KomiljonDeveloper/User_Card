package com.example.second_projects.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Integer userId;
    @NotBlank(message = "This field is cannot be empty!")
    private String firstname;
    @NotBlank(message = "This field is cannot be empty!")
    private String lastname;
    @NotBlank(message = "This field is cannot be empty!")
    @Email(message = "This email is invalid!")
    private String email;
    @Length(min = 6,message = "Password must be longer than 6 characters!")
    @NotBlank(message = "This field is cannot be empty!")
    private String password;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;
    private Set<CardDto> cards;
}
