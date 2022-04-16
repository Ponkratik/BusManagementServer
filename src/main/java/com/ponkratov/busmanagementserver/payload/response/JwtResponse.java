package com.ponkratov.busmanagementserver.payload.response;

import com.ponkratov.busmanagementserver.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private String token;
    private final String type = "Bearer";
    private long userId;
    private String userName;
    private String lastName;
    private String firstName;
    private String surName;
    private String phone;
    private boolean blocked;
    private List<Role> roleByRoleId;
}
