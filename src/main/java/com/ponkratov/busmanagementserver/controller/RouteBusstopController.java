package com.ponkratov.busmanagementserver.controller;

import com.ponkratov.busmanagementserver.model.Busstop;
import com.ponkratov.busmanagementserver.model.Routebusstop;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/routebusstop")
@CrossOrigin(origins = "*", maxAge = 3600)
public interface RouteBusstopController {
    @GetMapping("/get/all/{id}")
        //@PreAuthorize("hasRole('ROLE_DISPATCHER')")
    ResponseEntity<?> getAllByRouteId(@PathVariable Long id);

    @DeleteMapping("/delete/all/{id}")
        //@PreAuthorize("hasRole('ROLE_DISPATCHER')")
    ResponseEntity<?> deleteAllByRouteId(@PathVariable Long id);

    @PutMapping("/update/{id}")
        //@PreAuthorize("hasRole('ROLE_DISPATCHER')")
    ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody List<Routebusstop> routebusstops);
}
