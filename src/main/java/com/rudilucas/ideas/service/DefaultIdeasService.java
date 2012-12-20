package com.rudilucas.ideas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rudilucas.ideas.dao.IdeasDao;
import com.rudilucas.ideas.model.Ideas;

@Transactional
@Service(value = "ideasService")
public class DefaultIdeasService implements IdeasService {
	@Autowired
	private IdeasDao ideasDao;

	public List<Ideas> findAllIdeas() {
		return ideasDao.findAllIdeas();
	}

	public void sotreIdea(Ideas idea) {
		ideasDao.store(idea);
	}

	public Ideas loadIdea(Long id) {
		return ideasDao.find(id);
	}
}
