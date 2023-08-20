package com.example.second_projects.modul;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Gender {
    Male("Male"),
    Female("Female");
    private String title;
}
