package com.rudilucas.ideas.controller;

import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rudilucas.ideas.exception.ResourceNotFoundException;
import com.rudilucas.ideas.model.Ideas;
import com.rudilucas.ideas.service.IdeasService;

@Transactional
@Controller(value = "ideasController")
@RequestMapping("/ideas")
public class IdeasController extends AbstractController {

    @Autowired
    private IdeasService ideasService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    @ResponseBody
    public ModelAndView getIdeas() {
        //TODO filter the ideas that I've requested to have merged.
        Collection<Ideas> ideas = ideasService.findActiveIdeas();
        ModelAndView mav = new ModelAndView("ideas/list");
        mav.addObject("ideasList", ideas);
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createForm")
    public String getCreateForm(Model model, Principal principal) {
        model.addAttribute(new Ideas("", "", getLoggedUser(principal)));
        return "ideas/createForm";
    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public void saveIdea(@ModelAttribute Ideas idea, Principal principal, HttpServletResponse response) {
        idea.setCreator(getLoggedUser(principal));
        ideasService.sotreIdea(idea);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Ideas getIdea(@PathVariable ObjectId id) {
        final Ideas idea = ideasService.loadIdea(id);
        if (idea == null) {
            throw new ResourceNotFoundException(id);
        }
        return idea;
    }

    @RequestMapping(value = "/merge", method = RequestMethod.POST)
    public void merge(@Param(value = "origin") ObjectId origin, @Param(value = "destination") ObjectId destination, HttpServletResponse response) {
        ideasService.mergeRequest(origin, destination);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @RequestMapping(value = "/mergeAccept/", method = RequestMethod.POST)
    public void merge(@Param(value = "id") ObjectId id, Principal principal, HttpServletResponse response) {
        ideasService.acceptMerge(id, getLoggedUser(principal));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable ObjectId id, Principal principal, HttpServletResponse response) {
        ideasService.delete(id, getLoggedUser(principal));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
