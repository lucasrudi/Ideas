package com.rudilucas.ideas.service;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rudilucas.ideas.dao.IdeasDao;
import com.rudilucas.ideas.model.IdeaStatus;
import com.rudilucas.ideas.model.Ideas;
import com.rudilucas.ideas.model.MergeRequest;
import com.rudilucas.ideas.model.User;

@Transactional
@Service(value = "ideasService")
public class DefaultIdeasService implements IdeasService {

    @Autowired
    private IdeasDao ideasDao;

    @Autowired
    private MergeService mergeService;

    public Collection<Ideas> findActiveIdeas() {
        List<Ideas> findAllIdeas = ideasDao.findAllIdeas();
        Collection<Ideas> activeIdeas = CollectionUtils.select(findAllIdeas, new Predicate<Ideas>() {

            @Override
            public boolean evaluate(Ideas ideas) {
                return ideas.isActive();
            }
        });
        return activeIdeas;
    }

    public void sotreIdea(Ideas idea) {
        ideasDao.store(idea);
    }

    public Ideas loadIdea(ObjectId id) {
        return ideasDao.find(id);
    }

    @Override
    public void mergeRequest(ObjectId origin, ObjectId destination) {
        Ideas originIdea = ideasDao.find(origin);
        Ideas destinationIdea = ideasDao.find(destination);
        mergeService.requestMerge(originIdea, destinationIdea);
    }

    @Override
    public void acceptMerge(ObjectId id) {
        // TODO validate that destinationIdea user name match with logged user
        MergeRequest acceptedMerge = mergeService.acceptMerge(id);
        Ideas destinationIdea = acceptedMerge.getDestinationIdea();
        Ideas originIdea = acceptedMerge.getOriginIdea();
        destinationIdea.addMergedIdea(originIdea);
        originIdea.setStatus(IdeaStatus.MERGED);
        ideasDao.store(originIdea);
        ideasDao.store(destinationIdea);
    }

    @Override
    public Collection<Ideas> findMyIdeas(User user) {
        return ideasDao.findByCreator(user);
    }
}
