package com.aitbol.expensetracker.model.dto;

import com.aitbol.expensetracker.model.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private byte[] profilePic;

    private Boolean enabled;

    public UserDto() {

    }

    public UserDto(String username, String password, String firstName, String lastName, byte[] profilePic, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePic = profilePic;
        this.enabled = enabled;
    }


    public UserDto(User userEntity) {
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
        this.profilePic = userEntity.getProfilePic();
        this.enabled = userEntity.getEnabled();
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("profilePic")
    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    @JsonProperty("enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
