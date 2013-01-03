package com.rudilucas.ideas.dao;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.rudilucas.ideas.model.User;

@Repository(value = "userDao")
public class MongoUserDao implements UserDao {

    @Autowired
    @Qualifier("mongoTemplate")
    private MongoOperations mongoOperations;

    @Override
    public void updateUser(User user) {
        User userByName = findUserByName(user.getName());
        if (userByName != null) {
            mongoOperations.updateFirst(findByName(user.getName()), update("timeBeforeExpiration", user.getTimeBeforeExpiration()), User.class);
        } else {
            mongoOperations.insert(user);
        }
    }

    @Override
    public User findUser(ObjectId id) {
        return mongoOperations.findById(id, User.class);
    }

    @Override
    public User findUserByName(String name) {
        return mongoOperations.findOne(findByName(name), User.class);
    }

    private Query findByName(String name) {
        return new Query(where("name").is(name));
    }
}
