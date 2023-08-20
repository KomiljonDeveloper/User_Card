package com.example.second_projects.repository;

import com.example.second_projects.modul.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

    Optional<Card> findByCardIdAndDeletedAtIsNull(Integer card_id);

    List<Card> findByUserIdAndDeletedAtIsNull (Integer user_id);

}
