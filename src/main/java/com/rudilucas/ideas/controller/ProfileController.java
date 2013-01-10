package com.rudilucas.ideas.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
        return new ModelAndView("profile/user_profile");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ideasList")
    @ResponseBody
    public Map<String, Collection<Ideas>> ideasList(final HttpServletRequest request, Principal principal) {
        Collection<Ideas> ideasList = ideasService.findMyIdeas(getLoggedUser(principal));
        Map<String, Collection<Ideas>> map = new HashMap<String, Collection<Ideas>>();
        map.put("ideas", ideasList);
        return map;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pendingMerges")
    @ResponseBody
    public Map<String, Collection<MergeRequest>> pendingMerges(final HttpServletRequest request, Principal principal) {
        User user = getLoggedUser(principal);
        Map<String, Collection<MergeRequest>> map = new HashMap<String, Collection<MergeRequest>>();
        List<MergeRequest> pendingMerges = mergeService.findPendingMerges(user);
        map.put("merge", pendingMerges);
        return map;
    }

}
