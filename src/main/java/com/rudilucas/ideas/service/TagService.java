package com.rudilucas.ideas.service;

import java.util.List;

import com.rudilucas.ideas.model.Tag;


public interface TagService {

    List<Tag> getAllTags();

    void saveAllNecesaryTags(List<Tag> tags);

}
