package com.example.second_projects.modul;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @SequenceGenerator(name = "card_id_sequence",sequenceName = "card_id_seq",allocationSize = 1)
    @GeneratedValue(generator = "card_id_sequence")
    private Integer cardId;
    private String cardNumber;
    private String cardName;
    private String type;
    private Double amount;
    @Column(name = "user_id")
    private Integer userId;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private User users;

    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;


}
