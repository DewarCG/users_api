package com.nisum.users.models.dto;

import java.time.LocalDate;
import java.util.List;

public class UserResponse {
    private String userId;
    private LocalDate modified;
    private LocalDate lastLogin;
    private LocalDate created;
    private boolean isActive;
    private String token;

    private List<PhoneResponse> phones;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<PhoneResponse> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneResponse> phones) {
        this.phones = phones;
    }
}
