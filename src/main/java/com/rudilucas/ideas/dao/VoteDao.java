package com.rudilucas.ideas.dao;

import org.bson.types.ObjectId;

import com.rudilucas.ideas.model.Vote;

public interface VoteDao {

    void addVote(ObjectId id, Vote vote);
}
