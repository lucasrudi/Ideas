package com.rudilucas.ideas.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rudilucas.ideas.model.MergeRequest;
import com.rudilucas.ideas.model.User;
import com.rudilucas.ideas.service.MergeService;

@Transactional
@Controller(value = "profileController")
@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    private MergeService mergeService;

    @RequestMapping(method = RequestMethod.GET, value = "/pendingMerges")
    @ResponseBody
    public List<MergeRequest> pendingMerges(final HttpServletRequest request, Principal principal) {
        User user = (User) principal;
        List<MergeRequest> requests = mergeService.findPendingMerges(user);
        return requests;
    }
}
