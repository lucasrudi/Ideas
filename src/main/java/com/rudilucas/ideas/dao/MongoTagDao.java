package com.rudilucas.ideas.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.rudilucas.ideas.model.Tag;

@Repository(value = "tagDao")
public class MongoTagDao implements TagDao {

    @Autowired
    @Qualifier("mongoTemplate")
    private MongoOperations mongoOperations;

    @Override
    public List<Tag> getAllTags() {
        return mongoOperations.findAll(Tag.class);
    }

    @Override
    public void addTag(Tag tag) {
        mongoOperations.save(tag);
    }

}
