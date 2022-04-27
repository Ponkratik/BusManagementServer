package com.ponkratov.busmanagementserver.repository;

import com.ponkratov.busmanagementserver.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}