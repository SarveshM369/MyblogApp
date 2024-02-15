package com.myblog.service;

import java.util.List;

import com.myblog.entities.User;

public interface UserService {

	
    User createUser(User user) throws Exception;

    //Update User Details By User Id
    User updateUse(User user, Long userId);

    //Get User Details By User Id
    User getUse(Long userId);

    //GetAll User Details
    List<User> getAllUser();

    // Delete User Details By User Id
    void deleteUse(Long userId);
}
