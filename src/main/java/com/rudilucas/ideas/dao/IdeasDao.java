package com.rudilucas.ideas.dao;

import java.util.List;

import com.rudilucas.ideas.model.Ideas;

public interface IdeasDao {

	List<Ideas> findAllIdeas();

	void store(Ideas idea);

	Ideas find(Long id);

}
