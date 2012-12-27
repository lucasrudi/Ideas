package com.rudilucas.ideas.service;

import org.bson.types.ObjectId;

import com.rudilucas.ideas.model.Vote;

public interface VoteService {

	void saveVote(ObjectId id, Vote vote);

}
