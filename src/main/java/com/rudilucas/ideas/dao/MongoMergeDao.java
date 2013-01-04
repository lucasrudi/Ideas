package com.rudilucas.ideas.dao;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.rudilucas.ideas.model.*;

@Service(value = "mergeDao")
public class MongoMergeDao implements MergeDao {

    @Autowired
    @Qualifier("mongoTemplate")
    private MongoOperations mongoOperations;

    @Override
    public void saveMerge(MergeRequest mergeRequest) {
        mongoOperations.save(mergeRequest);
    }

    @Override
    public MergeRequest findById(ObjectId id) {
        return mongoOperations.findById(id, MergeRequest.class);
    }

    @Override
    public List<MergeRequest> findByReceiverRequestUser(User user) {
        final List<Ideas> ideasFromCreator = mongoOperations.find(query(where("creator.$id").is(user.getId())), Ideas.class);
        List<MergeRequest> requests = mongoOperations.findAll(MergeRequest.class);
        Collection<MergeRequest> collection = CollectionUtils.select(requests, new Predicate<MergeRequest>() {
            @Override
            public boolean evaluate(final MergeRequest request) {
                return request.getAcceptedDate() == null &&
                        CollectionUtils.exists(ideasFromCreator, new Predicate<Ideas>() {
                    @Override
                    public boolean evaluate(Ideas idea) {
                        if (request.getDestinationIdea() == null) {
                            return false;
                        }
                        return idea.getId().equals(request.getDestinationIdea().getId());
                    }
                });
            }
        });
        return (List<MergeRequest>) collection;
    }

    @Override
    public void delete(ObjectId id) {
        mongoOperations.remove(findById(id));
    }

    @Override
    public void deleteMergesOfIdea(ObjectId id, User user) {
        Query query = new Query(where("'destinationIdea' : { '$id': { creator:{ $oid: ?0 } } }").is(user.getId()));
        mongoOperations.remove(query, MergeRequest.class);
        query = new Query(where("originIdea.creator.$id").is(user.getId()));
        mongoOperations.remove(query, MergeRequest.class);
    }

}
