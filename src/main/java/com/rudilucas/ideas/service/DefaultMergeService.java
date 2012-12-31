package com.rudilucas.ideas.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rudilucas.ideas.dao.IdeasDao;
import com.rudilucas.ideas.dao.MergeDao;
import com.rudilucas.ideas.model.Ideas;
import com.rudilucas.ideas.model.MergeRequest;
import com.rudilucas.ideas.model.User;

@Service(value = "mergeService")
public class DefaultMergeService implements MergeService {

    @Autowired
    private MergeDao mergeDao;

    @Autowired
    private IdeasDao ideasDao;

    @Override
    public void requestMerge(Ideas originIdea, Ideas destinationIdea) {
        mergeDao.saveMerge(new MergeRequest(originIdea, destinationIdea));
        // TODO send mail
    }

    @Override
    public MergeRequest acceptMerge(ObjectId id) {
        MergeRequest merge = mergeDao.findById(id);
        merge.setAcceptedDate(new Date());
        mergeDao.saveMerge(merge);
        return merge;
    }

    @Override
    public List<MergeRequest> findPendingMerges(User user) {
        List<MergeRequest> requests = mergeDao.findByReceiverRequestUser(user);
        return requests;
    }

}
