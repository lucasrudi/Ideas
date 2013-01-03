package com.rudilucas.ideas.dao;

import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;

import com.rudilucas.ideas.model.Ideas;
import com.rudilucas.ideas.model.User;

public interface IdeasDao {

    List<Ideas> findAllIdeas();

    void store(Ideas idea);

    Ideas find(ObjectId id);

    Collection<Ideas> findByCreator(User user);

}
