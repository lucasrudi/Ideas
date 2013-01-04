package com.rudilucas.ideas.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rudilucas.ideas.dao.MergeDao;
import com.rudilucas.ideas.model.Ideas;
import com.rudilucas.ideas.model.MergeRequest;
import com.rudilucas.ideas.model.User;

@Service(value = "mergeService")
public class DefaultMergeService implements MergeService {

    @Autowired
    private MergeDao mergeDao;

    @Override
    public void requestMerge(Ideas originIdea, Ideas destinationIdea) {
        mergeDao.saveMerge(new MergeRequest(originIdea, destinationIdea));
        // TODO send mail to the origin creator
    }

    @Override
    public MergeRequest acceptMerge(ObjectId id, User user) {
        MergeRequest merge = mergeDao.findById(id);
        if (!merge.getOriginIdea().getCreator().equals(user)) {
            throw new IllegalAccessError("User is not allowed to accept the merge of the requested item");
        }
        merge.setAcceptedDate(new Date());
        mergeDao.saveMerge(merge);
        //TODO send mail to the accepted requestor user
        return merge;
    }

    @Override
    public List<MergeRequest> findPendingMerges(User user) {
        List<MergeRequest> requests = mergeDao.findByReceiverRequestUser(user);
        return requests;
    }

    @Override
    public void rejectMerge(ObjectId id, User user) {
        MergeRequest merge = mergeDao.findById(id);
        if (!merge.getOriginIdea().getCreator().equals(user)) {
            throw new IllegalAccessError("User is not allowed to reject the merge of the requested item");
        }
        //TODO send mail to the rejected user
        mergeDao.delete(id);
    }

    @Override
    public void deleteMergeOfIdea(ObjectId id, User user) {
        mergeDao.deleteMergesOfIdea(id, user);
    }

}
