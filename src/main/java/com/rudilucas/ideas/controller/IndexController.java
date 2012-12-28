package com.rudilucas.ideas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping(value = "/")
public class IndexController {
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView getIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("redirect:index");
    }

}
