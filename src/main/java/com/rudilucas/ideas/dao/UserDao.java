package com.rudilucas.ideas.dao;

import org.bson.types.ObjectId;

import com.rudilucas.ideas.model.User;

public interface UserDao {

    void updateUser(User user);

    User findUser(ObjectId memberId);

    User findUserByName(String name);

}
