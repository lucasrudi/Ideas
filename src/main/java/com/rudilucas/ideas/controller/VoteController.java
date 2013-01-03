package com.rudilucas.ideas.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rudilucas.ideas.model.Vote;
import com.rudilucas.ideas.service.VoteService;

@Transactional
@Controller(value = "voteController")
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @RequestMapping(value = "/saveVote/{id}", method = RequestMethod.POST)
    public void voteIdea(@PathVariable ObjectId id, @ModelAttribute Vote voteForm, HttpServletResponse response) throws IOException {
        voteService.saveVote(id, voteForm);
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/Ideas");
    }

}
