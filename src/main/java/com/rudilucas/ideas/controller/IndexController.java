package com.rudilucas.ideas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rudilucas.ideas.service.UserService;

@Transactional
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView getIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("redirect:index");
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView saveRegister(final HttpServletRequest request) {
        String name = (String) request.getParameter("name");
        String mail = (String) request.getParameter("mail");
        userService.register(name, mail);
        return new ModelAndView("redirect:index");
    }

}
