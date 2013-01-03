package com.rudilucas.ideas.service;

import java.util.Collection;

import org.bson.types.ObjectId;

import com.rudilucas.ideas.model.Ideas;
import com.rudilucas.ideas.model.User;

public interface IdeasService {
    public Collection<Ideas> findActiveIdeas();

    public void sotreIdea(Ideas idea);

    public Ideas loadIdea(ObjectId id);

    public void mergeRequest(ObjectId origin, ObjectId destination);

    public void acceptMerge(ObjectId id);

    public Collection<Ideas> findMyIdeas(User user);

}
