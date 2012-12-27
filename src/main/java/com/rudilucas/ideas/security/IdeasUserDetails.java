package com.rudilucas.ideas.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class IdeasUserDetails extends IdeasUserProfile implements UserDetails, Serializable {

    private static final long serialVersionUID = 3641320491712760550L;

    private Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    private int userId;

    public IdeasUserDetails() {
    }

    public IdeasUserDetails(int userId, String firstName, String lastname, String email, String phoneNumber) {
        super(firstName, lastname, email, phoneNumber);
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getPassword() {
        return null;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public String getUsername() {
        return this.getEmail();
    }

}
