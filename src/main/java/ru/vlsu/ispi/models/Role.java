package ru.vlsu.ispi.models;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN, ROLE_CLADOVSHIK;

    @Override
    public String getAuthority() {
        return name();
    }
}