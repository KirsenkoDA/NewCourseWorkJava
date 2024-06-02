package ru.vlsu.ispi.models;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN, ROLE_CLADOVSHIK, ROLE_MODERATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}