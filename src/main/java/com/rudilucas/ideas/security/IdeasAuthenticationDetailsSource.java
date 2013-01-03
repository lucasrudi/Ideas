package com.rudilucas.ideas.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import com.rudilucas.ideas.service.UserService;

@Service(value  ="ideasAuthenticationDetailsSource")
public class IdeasAuthenticationDetailsSource extends WebAuthenticationDetailsSource {

    @Autowired
    private UserService userService;

    public IdeasUserDetails buildDetails(HttpServletRequest request) {
        String username = request.getParameter("username");
        UserDetails userDetails = userService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new AuthenticationServiceException("User not found");
        }

        IdeasUserDetails ideasUserDetails = new IdeasUserDetails(request, userDetails);

        userService.updateUser(ideasUserDetails);
        return ideasUserDetails;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
