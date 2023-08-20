package com.example.second_projects.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDto {
    private Integer userId;
    private Integer cardId;
    private String cardNumber;
    private String cardName;
    private String type;
    private Double amount;

    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;

    private UserDto users;
}

