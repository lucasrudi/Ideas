package com.rudilucas.ideas.dao;

import org.bson.types.ObjectId;

import com.rudilucas.ideas.model.MergeRequest;

public interface MergeDao {

	void saveMerge(MergeRequest mergeRequest);

	MergeRequest findById(ObjectId id);

}
