package com.rudilucas.ideas.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rudilucas.ideas.model.Ideas;
import com.rudilucas.ideas.model.Vote;

@Component
@Service(value="ideasDao")
public class MongoIdeasDao implements IdeasDao {
	
	@Autowired
	@Qualifier("mongoTemplate")
	private MongoOperations mongoOperations;
	
	@Override
	public List<Ideas> findAllIdeas() {
		return mongoOperations.findAll(Ideas.class);
	}

	@Override
	public void store(Ideas idea) {
		mongoOperations.save(idea);
	}

	@Override
	public Ideas find(ObjectId id) {
		return mongoOperations.findById(id, Ideas.class);
	}

	@Override
	public void addVote(ObjectId id, Vote vote) {
		Ideas ideas = find(id);
		ideas.addVote(vote);
		store(ideas);
	}
}
