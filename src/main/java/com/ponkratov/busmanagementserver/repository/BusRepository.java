package com.ponkratov.busmanagementserver.repository;

import com.ponkratov.busmanagementserver.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {
    boolean existsBusByVin(String vin);
    boolean existsBusByNumber(String number);
}
