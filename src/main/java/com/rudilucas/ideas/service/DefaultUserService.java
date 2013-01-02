package com.rudilucas.ideas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rudilucas.ideas.dao.UserDao;
import com.rudilucas.ideas.model.User;
import com.rudilucas.ideas.security.IdeasUserDetails;

@Service(value="userService")
public class DefaultUserService implements UserService {

    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void updateUser(IdeasUserDetails ideasUserDetails) {
        userDao.updateUser(ideasUserDetails.getUser());
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userDao.findUserByName(name);
        return user;
    }

    @Override
    public void register(String name, String mail) {
        User user = userDao.findUserByName(name);
        if (user != null) {
            throw new AuthenticationServiceException("Username already taken");
        }
        userDao.updateUser(new User(name, mail));
    }

}
