package com.ponkratov.busmanagementserver.controller.impl;

import com.ponkratov.busmanagementserver.controller.AuthController;
import com.ponkratov.busmanagementserver.model.Role;
import com.ponkratov.busmanagementserver.model.User;
import com.ponkratov.busmanagementserver.payload.request.SigninRequest;
import com.ponkratov.busmanagementserver.payload.request.SignupRequest;
import com.ponkratov.busmanagementserver.payload.response.JwtResponse;
import com.ponkratov.busmanagementserver.payload.response.MessageResponse;
import com.ponkratov.busmanagementserver.repository.RoleRepository;
import com.ponkratov.busmanagementserver.repository.UserRepository;
import com.ponkratov.busmanagementserver.security.jwt.JwtUtils;
import com.ponkratov.busmanagementserver.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class AuthControllerImpl implements AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SigninRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> rolesStr = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        List<Role> roles = new ArrayList<>();
        for (String str: rolesStr) {
            Role r = new Role();
            r = roleRepository.findByRoleName(str).get();
            roles.add(r);
        }
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getLastName(),
                userDetails.getFirstName(),
                userDetails.getSurName(),
                userDetails.getPhone(),
                userDetails.getEmail(),
                userDetails.isAccountNonLocked(),
                roles));
    }

    @Override
    public ResponseEntity<?> registerUser(SignupRequest signupRequest) {
        if (userRepository.existsByLogin(signupRequest.getLogin())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User();
        user.setLogin(signupRequest.getLogin());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setEmail(signupRequest.getEmail());
        user.setLastName(signupRequest.getLastName());
        user.setFirstName(signupRequest.getFirstName());
        user.setSurName(signupRequest.getSurName());
        user.setPhone(signupRequest.getPhone());
        user.setBlocked(false);

        Set<String> requestRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();
        requestRoles.forEach(role -> {
            Role r = roleRepository.findByRoleName(role).orElseThrow(() -> new RuntimeException("Error: Role not found"));
            user.setRoleId(r.getRoleId());
        });
        /*Role r = roleRepository.findByRoleName(signupRequest.getRole()).orElseThrow(() -> new RuntimeException("Error: Role not found"));
        user.setRoleId(r.getRoleId());*/

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registred successfully"));
    }
}
