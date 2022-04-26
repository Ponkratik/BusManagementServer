package com.ponkratov.busmanagementserver.controller;

import com.ponkratov.busmanagementserver.model.Busstop;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/busstop")
@CrossOrigin(origins = "*", maxAge = 3600)
public interface BusStopController {
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
    ResponseEntity<?> add(@Valid @RequestBody Busstop busstop);

    @PutMapping("/update/{id}")
        //@PreAuthorize("hasRole('ROLE_DISPATCHER')")
    ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Busstop busstop);
}
