package com.rudilucas.ideas.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rudilucas.ideas.model.Ideas;
import com.rudilucas.ideas.model.MergeRequest;
import com.rudilucas.ideas.model.User;
import com.rudilucas.ideas.service.IdeasService;
import com.rudilucas.ideas.service.MergeService;

@Transactional
@Controller(value = "profileController")
@RequestMapping("/profile")
public class ProfileController extends AbstractController {

    @Autowired
    private MergeService mergeService;

    @Autowired
    private IdeasService ideasService;

    @RequestMapping(method = RequestMethod.GET, value = "/myProfile")
    public ModelAndView profile(final HttpServletRequest request, Principal principal) {
        ModelAndView mav = new ModelAndView("profile/user_profile");
        Collection<Ideas> ideasList = ideasService.findMyIdeas(getLoggedUser(principal));
        mav.addObject("ideasList", ideasList);
        User user = getLoggedUser(principal);
        List<MergeRequest> pendingMerges = mergeService.findPendingMerges(user);
        mav.addObject("pendingMerges", pendingMerges);
        return mav;
    }

}
