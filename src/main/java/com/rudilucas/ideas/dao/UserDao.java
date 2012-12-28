package com.rudilucas.ideas.dao;

import com.rudilucas.ideas.security.IdeasUserDetails;

public interface UserDao {

    void updateUser(IdeasUserDetails loyaltyUserDetails);

    IdeasUserDetails findUser(int memberId);

}
