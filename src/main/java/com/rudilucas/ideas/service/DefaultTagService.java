package com.rudilucas.ideas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rudilucas.ideas.dao.TagDao;
import com.rudilucas.ideas.model.Tag;

@Transactional
@Service(value = "tagService")
public class DefaultTagService implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    public List<Tag> getAllTags() {
        return tagDao.getAllTags();
    }

    @Override
    public void saveAllNecesaryTags(List<Tag> tags) {
        if (tags == null || tags.isEmpty()) {
            return;
        }
        List<Tag> allTags = getAllTags();
        for (Tag tag : tags) {
            if (!allTags.contains(tag)) {
                tagDao.addTag(tag);
            }
        }
    }
}
