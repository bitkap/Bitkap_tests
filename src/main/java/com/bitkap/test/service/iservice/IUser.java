package com.bitkap.test.service.iservice;


import com.bitkap.test.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface IUser {

    void checkExistsUserById(Long userId);

    // get all users
    Page<User> getAllUserPageable(Pageable pageable);

    // create new user
    User saveUser(User user);

    // get user by userId
    User findUserById(Long userId);

    // update user by userId
    User updateUser(User newUser, Long id);

    //delete user by userId
    ResponseEntity<?> deleteByUserId(Long userId);



}
