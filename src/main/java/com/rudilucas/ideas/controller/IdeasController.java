package com.rudilucas.ideas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rudilucas.ideas.exception.ResourceNotFoundException;
import com.rudilucas.ideas.model.Ideas;
import com.rudilucas.ideas.service.IdeasService;

@Controller(value = "ideasController")
@RequestMapping("/ideas")
public class IdeasController {
	@Autowired
	private IdeasService ideasService;

	@RequestMapping(method = RequestMethod.GET, value = "/getAll")
	@ResponseBody
	public List<Ideas> getIdeas() {
		return ideasService.findAllIdeas();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/createForm")
	public String getCreateForm(Model model) {
		model.addAttribute(new Ideas());
		return "ideas/createForm";
	}

	@RequestMapping(value = "/store", method = RequestMethod.POST)
	public void addProvider(@ModelAttribute Ideas idea) {
		ideasService.sotreIdea(idea);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Ideas getIdea(@PathVariable Long id) {
		final Ideas idea = ideasService.loadIdea(id);
		if (idea == null) {
			throw new ResourceNotFoundException(id);
		}
		return idea;
	}

}
