package com.rudilucas.ideas.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rudilucas.ideas.dao.UserDao;
import com.rudilucas.ideas.security.IdeasUserDetails;

public class DefaultUserService implements UserService, UserDetailsService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void updateUser(IdeasUserDetails ideasUserDetails) {
        // TODO Auto-generated method stub

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

}
