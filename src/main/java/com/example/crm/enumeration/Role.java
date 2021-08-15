package com.example.crm.enumeration;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    OPERATOR("OPERATOR");

    private final String value;

    Role(String value) {
        this.value = value;
    }
}
