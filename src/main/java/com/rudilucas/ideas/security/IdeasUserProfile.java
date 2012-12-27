package com.rudilucas.ideas.security;


public class IdeasUserProfile {

    private String firstName;

    private String lastname;

    private String email;

    private String phoneNumber;

    public IdeasUserProfile() {
    }

    public IdeasUserProfile(String firstName, String lastname, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
