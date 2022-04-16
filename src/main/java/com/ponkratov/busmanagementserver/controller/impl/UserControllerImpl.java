package com.ponkratov.busmanagementserver.controller.impl;

import com.ponkratov.busmanagementserver.controller.UserController;
import com.ponkratov.busmanagementserver.model.Role;
import com.ponkratov.busmanagementserver.model.User;
import com.ponkratov.busmanagementserver.payload.request.SignupRequest;
import com.ponkratov.busmanagementserver.payload.response.MessageResponse;
import com.ponkratov.busmanagementserver.repository.RoleRepository;
import com.ponkratov.busmanagementserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUserById(Long userId) {
        Optional<User> temp = userRepository.findByUserId(userId);
        User tempUser;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Could not find this user"), HttpStatus.NO_CONTENT);
        } else {
            tempUser = temp.get();
        }

        return new ResponseEntity<>(tempUser, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> blockUser(Long userId) {
        Optional<User> temp = userRepository.findByUserId(userId);
        User tempUser;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Could not find this user"), HttpStatus.NO_CONTENT);
        } else {
            tempUser = temp.get();
        }

        tempUser.setBlocked(!tempUser.isBlocked());
        userRepository.save(tempUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateUser(Long userId, SignupRequest request) {
        Optional<User> temp = userRepository.findByLogin(request.getLogin());
        User tempUser;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Could not find this user"), HttpStatus.NO_CONTENT);
        } else {
            tempUser = temp.get();
        }

        if (!request.getLogin().equals(tempUser.getLogin()) && userRepository.existsByLogin(request.getLogin())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (!request.getEmail().equals(tempUser.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        tempUser.setLogin(request.getLogin());
        tempUser.setPassword(request.getPassword());
        tempUser.setEmail(request.getEmail());
        tempUser.setLastName(request.getLastName());
        tempUser.setFirstName(request.getFirstName());
        tempUser.setSurName(request.getSurName());
        tempUser.setPhone(request.getPhone());

        //Set<String> requestRoles = request.getRoleByRoleId();
        //Set<Role> roles = new HashSet<>();
        /*request.getRoleByRoleId().forEach(role -> {
            //Role r = roleRepository.findByRoleName(role).orElseThrow(() -> new RuntimeException("Error: Role not found"));
            tempUser.setRoleId(role.getRoleId());
        });*/
        tempUser.setRoleId(request.getRoleByRoleId().getRoleId());

        userRepository.save(tempUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
