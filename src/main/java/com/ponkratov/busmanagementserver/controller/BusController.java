package com.ponkratov.busmanagementserver.controller;

import com.ponkratov.busmanagementserver.model.Bus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/bus")
@CrossOrigin(origins = "*", maxAge = 3600)
public interface BusController {

    @GetMapping("/get/all")
    @PreAuthorize("hasRole('ROLE_GARAGEMANAGER')")
    ResponseEntity<?> getAllBuses();

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ROLE_GARAGEMANAGER')")
    ResponseEntity<?> getBusById(@PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_GARAGEMANAGER')")
    ResponseEntity<?> deleteBus(@PathVariable Long id);

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_GARAGEMANAGER')")
    ResponseEntity<?> addBus(@Valid @RequestBody Bus bus);

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_GARAGEMANAGER')")
    ResponseEntity<?> updateBus(@PathVariable Long id, @Valid @RequestBody Bus bus);
}
