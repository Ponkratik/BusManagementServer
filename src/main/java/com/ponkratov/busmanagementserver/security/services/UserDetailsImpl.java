package com.ponkratov.busmanagementserver.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ponkratov.busmanagementserver.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;
    private long userId;
    private String login;
    private String email;
    @JsonIgnore
    private String password;
    private String lastName;
    private String firstName;
    private String surName;
    private String phone;
    private boolean blocked;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(long userId, String login, String email, String password, String lastName, String firstName, String surName, String phone, boolean blocked, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.login = login;
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.surName = surName;
        this.phone = phone;
        this.blocked = blocked;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoleByRoleId().getRoleName()));
        return new UserDetailsImpl(user.getUserId(), user.getLogin(), user.getEmail(), user.getPassword(), user.getLastName(), user.getFirstName(), user.getSurName(), user.getPhone(), user.isBlocked(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //return blocked;
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
