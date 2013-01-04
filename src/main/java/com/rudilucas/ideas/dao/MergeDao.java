package com.rudilucas.ideas.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.rudilucas.ideas.model.MergeRequest;
import com.rudilucas.ideas.model.User;

public interface MergeDao {

    void saveMerge(MergeRequest mergeRequest);

    MergeRequest findById(ObjectId id);

    List<MergeRequest> findByReceiverRequestUser(User user);

    void delete(ObjectId id);

    void deleteMergesOfIdea(ObjectId id, User user);

}
