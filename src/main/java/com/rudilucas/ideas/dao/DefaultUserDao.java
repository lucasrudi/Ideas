package com.rudilucas.ideas.dao;

import org.springframework.stereotype.Repository;

import com.rudilucas.ideas.security.IdeasUserDetails;

@Repository(value="userDao")
public class DefaultUserDao implements UserDao {

	@Override
	public void updateUser(IdeasUserDetails loyaltyUserDetails) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IdeasUserDetails findUser(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}
}
