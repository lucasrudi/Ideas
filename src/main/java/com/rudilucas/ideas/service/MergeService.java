package com.rudilucas.ideas.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.rudilucas.ideas.model.Ideas;
import com.rudilucas.ideas.model.MergeRequest;
import com.rudilucas.ideas.model.User;

public interface MergeService {

    void requestMerge(Ideas originIdea, Ideas destinationIdea);

    MergeRequest acceptMerge(ObjectId id);

    List<MergeRequest> findPendingMerges(User user);

}
