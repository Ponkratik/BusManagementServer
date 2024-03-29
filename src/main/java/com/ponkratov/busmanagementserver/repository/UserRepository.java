package com.ponkratov.busmanagementserver.repository;

import com.ponkratov.busmanagementserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    Optional<User> findByUserId(Long userId);
    Boolean existsByLogin(String login);
    Boolean existsByEmail(String email);
}
