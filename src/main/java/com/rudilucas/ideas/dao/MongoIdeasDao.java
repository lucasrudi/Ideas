package com.rudilucas.ideas.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rudilucas.ideas.model.Ideas;

@Component
@Service(value="ideasDao")
public class MongoIdeasDao implements IdeasDao {
	
	@Autowired
	@Qualifier("mongoTemplate")
	private MongoOperations mongoOperations;
	
	public List<Ideas> findAllIdeas() {
		return mongoOperations.findAll(Ideas.class);
	}

	public void store(Ideas idea) {
		mongoOperations.save(idea);
	}

	public Ideas find(Long id) {
		return mongoOperations.findById(id, Ideas.class);
	}
}
