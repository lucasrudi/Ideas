package com.rudilucas.ideas.service;

import org.bson.types.ObjectId;

import com.rudilucas.ideas.model.Ideas;
import com.rudilucas.ideas.model.MergeRequest;

public interface MergeService {

    void requestMerge(Ideas originIdea, Ideas destinationIdea);

    MergeRequest acceptMerge(ObjectId id);

}
