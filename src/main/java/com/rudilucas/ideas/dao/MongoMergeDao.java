package com.rudilucas.ideas.dao;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.rudilucas.ideas.model.MergeRequest;
import com.rudilucas.ideas.model.User;

@Service(value = "mergeDao")
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

    @Override
    public List<MergeRequest> findByReceiverRequestUser(User user) {
        Query query = new Query(where("destinationIdea.creator.username").is(user.getUsername()));
        List<MergeRequest> requests = mongoOperations.find(query, MergeRequest.class);
        return requests;
    }

}
