package com.ponkratov.busmanagementserver.repository;

import com.ponkratov.busmanagementserver.model.Busstop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusStopRepository extends JpaRepository<Busstop, Long> {
}
