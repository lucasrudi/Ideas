package com.rudilucas.ideas.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rudilucas.ideas.dao.VoteDao;
import com.rudilucas.ideas.model.Vote;

@Service(value = "voteService")
public class DefaultVoteService implements VoteService {
    @Autowired
    private VoteDao voteDao;

    @Override
    public void saveVote(ObjectId id, Vote vote) {
        voteDao.addVote(id, vote);
    }

}
