package com.rudilucas.ideas.controller;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.rudilucas.ideas.model.User;
import com.rudilucas.ideas.security.IdeasUserDetails;

public abstract class AbstractController {

    protected User getLoggedUser(Principal principal) {
        return ((IdeasUserDetails) ((UsernamePasswordAuthenticationToken) principal).getDetails()).getUser();
    }
}
