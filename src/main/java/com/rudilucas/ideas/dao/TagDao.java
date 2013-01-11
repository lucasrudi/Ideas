package com.rudilucas.ideas.dao;

import java.util.List;

import com.rudilucas.ideas.model.Tag;

public interface TagDao {

    List<Tag> getAllTags();

    void addTag(Tag tag);

}
