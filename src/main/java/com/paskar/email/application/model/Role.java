package com.paskar.email.application.model;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.WRITE_READ)),
    ADMIN(Set.of(Permission.WRITE_READ,Permission.DELETE));

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Getter
    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
