package com.backlashprogramming.ecommerce.EcommerceByBacklash.api.model;

import jakarta.validation.constraints.*;

public class RegistrationBody {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 32)
    private  String username;

    @NotNull
    @NotBlank
    @Email
    private  String email;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 32)
    @Pattern(regexp = "^.*(?=.{6,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")
    private  String password;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 32)
    private  String firstName;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 32)
    private  String lastName;

    public RegistrationBody(){

    }

    public RegistrationBody(String username, String email, String password, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
