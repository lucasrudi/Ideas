package com.rudilucas.ideas.dao;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.rudilucas.ideas.model.MergeRequest;

@Service(value="mergeDao")
public class MongoMergeDao implements MergeDao {
	
	@Autowired
	@Qualifier("mongoTemplate")
	private MongoOperations mongoOperations;

	@Override
	public void saveMerge(MergeRequest mergeRequest) {
		mongoOperations.insert(mergeRequest);
	}

	@Override
	public MergeRequest findById(ObjectId id) {
		return mongoOperations.findById(id, MergeRequest.class);
	}
	
}