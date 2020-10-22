package com.paskar.email.application.model;

import lombok.Getter;

public enum Permission {
    WRITE("write"),
    READ_DELETE("delete/read");

    @Getter
    private String permission;

    Permission(String permission) {
        this.permission = permission;
    }
}
