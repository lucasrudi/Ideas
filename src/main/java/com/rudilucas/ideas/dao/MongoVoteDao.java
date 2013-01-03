package com.rudilucas.ideas.dao;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rudilucas.ideas.model.Ideas;
import com.rudilucas.ideas.model.Vote;

@Repository(value = "voteDao")
public class MongoVoteDao implements VoteDao {

    @Autowired
    private IdeasDao ideasDao;

    @Override
    public void addVote(ObjectId id, Vote vote) {
        Ideas ideas = ideasDao.find(id);
        ideas.addVote(vote);
        ideasDao.store(ideas);
    }
}
