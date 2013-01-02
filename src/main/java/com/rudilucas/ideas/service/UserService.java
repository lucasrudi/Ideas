package com.rudilucas.ideas.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.rudilucas.ideas.security.IdeasUserDetails;

public interface UserService extends UserDetailsService {

    void updateUser(IdeasUserDetails loyaltyUserDetails);

    void register(String name, String mail);

}
