package com.paskar.email.application.model;

import lombok.Getter;

public enum Permission {
    WRITE_READ("read/write"),
    DELETE("delete");

    @Getter
    private String permission;

    Permission(String permission) {
        this.permission = permission;
    }
}
