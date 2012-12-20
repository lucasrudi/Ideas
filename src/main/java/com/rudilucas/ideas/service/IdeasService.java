package com.rudilucas.ideas.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.rudilucas.ideas.model.Ideas;

public interface IdeasService {
	public List<Ideas> findAllIdeas();

	public void sotreIdea(Ideas idea);

	public Ideas loadIdea(ObjectId id);

}
