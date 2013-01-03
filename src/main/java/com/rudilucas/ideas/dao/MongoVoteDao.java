package com.rudilucas.ideas.dao;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.rudilucas.ideas.model.Ideas;
import com.rudilucas.ideas.model.Vote;

@Repository(value = "voteDao")
public class MongoVoteDao implements VoteDao {

    @Autowired
    @Qualifier("mongoTemplate")
    private MongoOperations mongoOperations;

    @Autowired
    private IdeasDao ideasDao;

    @Override
    public void addVote(ObjectId id, Vote vote) {
        Ideas ideas = ideasDao.find(id);
        ideas.addVote(vote);
        ideasDao.store(ideas);
        store(vote);
    }

    private void store(Vote vote) {
        mongoOperations.save(vote);
    }

}
