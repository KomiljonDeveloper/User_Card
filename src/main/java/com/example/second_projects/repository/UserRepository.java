package com.example.second_projects.repository;


import com.example.second_projects.modul.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // TODO : select u from users as u where user_id = ? and deleted_at is null;
    Optional<User> findByUserIdAndDeletedAtIsNull(Integer userId);

    boolean existsByEmail(String email);

}
