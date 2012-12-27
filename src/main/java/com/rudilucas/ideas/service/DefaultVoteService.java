package com.rudilucas.ideas.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rudilucas.ideas.dao.IdeasDao;
import com.rudilucas.ideas.model.Vote;

@Service(value = "voteService")
public class DefaultVoteService implements VoteService {
	@Autowired
	private IdeasDao ideasDao;

	@Override
	public void saveVote(ObjectId id, Vote vote) {
		ideasDao.addVote(id, vote);
	}
	
}
