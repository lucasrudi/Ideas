package com.rudilucas.ideas.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository(value = "voteDao")
public class MongoVoteDao implements VoteDao {
    @Autowired
    @Qualifier("mongoTemplate")
    private MongoOperations mongoOperations;
    
}
