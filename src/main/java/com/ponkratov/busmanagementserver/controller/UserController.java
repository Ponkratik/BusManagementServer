package com.ponkratov.busmanagementserver.controller;

import com.ponkratov.busmanagementserver.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public interface UserController {
    @GetMapping("/get/all")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> getAllUsers();

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> getUserById(@PathVariable("id") Long userId);

    @DeleteMapping ("/block/{id}")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> blockUser(@PathVariable("id") Long userId);

    @PutMapping ("/update/{id}")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> updateUser(@PathVariable("id") Long userId, @Valid @RequestBody SignupRequest request);
}
