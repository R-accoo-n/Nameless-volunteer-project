package com.nameless.volunteerproject.enums;

/**
 * Enumerations for user's role
 */

public enum UserRole {
    VOLUNTEER("VOLUNTEER"), MILITARY("MILITARY"), ADMINISTRATOR("ADMINISTRATOR"), USER("USER");
    private final String value;
    UserRole(String value) {
        this.value=value;
    }
}
