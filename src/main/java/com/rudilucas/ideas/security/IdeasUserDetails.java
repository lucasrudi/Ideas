package com.rudilucas.ideas.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.rudilucas.ideas.model.User;

public class IdeasUserDetails extends WebAuthenticationDetails implements UserDetails, Serializable {

    private static final long serialVersionUID = 3641320491712760550L;

    private Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    private User user;


    public IdeasUserDetails(HttpServletRequest request) {
        super(request);
        String username = request.getParameter("username");
        User user = new User(username);
        this.user = user;
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

    public User getUser() {
        return user;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

}
