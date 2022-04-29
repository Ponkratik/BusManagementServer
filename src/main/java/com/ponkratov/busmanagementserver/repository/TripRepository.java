package com.ponkratov.busmanagementserver.repository;

import com.ponkratov.busmanagementserver.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByUserByUserId_UserId(long userByUserId_userId);
}
