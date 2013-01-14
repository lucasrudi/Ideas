package com.rudilucas.ideas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rudilucas.ideas.model.Tag;
import com.rudilucas.ideas.service.TagService;

@Transactional
@Controller(value = "tagsController")
@RequestMapping("/tags")
public class TagsController {

    @Autowired
    private TagService tagService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAllTags")
    @ResponseBody
    public List<Tag> getAllTags() {
        List<Tag> tags = tagService.getAllTags();
        return tags;
    }
}
