package com.ponkratov.busmanagementserver.controller;

import com.ponkratov.busmanagementserver.model.Bus;
import com.ponkratov.busmanagementserver.model.City;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/city")
@CrossOrigin(origins = "*", maxAge = 3600)
public interface CityController {
    @GetMapping("/get/all")
    //@PreAuthorize("hasRole('ROLE_DISPATCHER')")
    ResponseEntity<?> getAll();

    @GetMapping("/get/{id}")
    //@PreAuthorize("hasRole('ROLE_DISPATCHER')")
    ResponseEntity<?> getById(@PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasRole('ROLE_DISPATCHER')")
    ResponseEntity<?> delete(@PathVariable Long id);

    @PostMapping("/add")
    //@PreAuthorize("hasRole('ROLE_DISPATCHER')")
    ResponseEntity<?> add(@Valid @RequestBody City city);

    @PutMapping("/update/{id}")
    //@PreAuthorize("hasRole('ROLE_DISPATCHER')")
    ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody City city);
}
