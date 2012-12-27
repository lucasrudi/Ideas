package com.rudilucas.ideas.security;


import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;

import com.rudilucas.ideas.service.UserService;

public class IdeasAuthenticationDetailsSource implements
        AuthenticationDetailsSource<HttpServletRequest, IdeasUserDetails> {

    private UserService userService;

    public IdeasUserDetails buildDetails(HttpServletRequest context) {
        int userId = Integer.parseInt(context.getParameter("userId"));
        String email = context.getParameter("email");
        IdeasUserDetails loyaltyUserDetails = new IdeasUserDetails(userId, context.getParameter("firstName"),
                context.getParameter("lastName"), email, null);

        userService.updateUser(loyaltyUserDetails);
        return loyaltyUserDetails;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
