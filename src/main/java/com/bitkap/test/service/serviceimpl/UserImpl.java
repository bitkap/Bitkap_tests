package com.bitkap.test.service.serviceimpl;


import com.bitkap.test.dao.UserRepo;
import com.bitkap.test.model.ResourceNotFoundException;
import com.bitkap.test.model.User;
import com.bitkap.test.service.iservice.IUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserImpl implements IUser {

    private final UserRepo userRepo;



    public UserImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public void checkExistsUserById(Long userId) {
        if (!userRepo.existsById(userId)) {
            throw new ResourceNotFoundException("UserId" +userId + "not found");
        }
    }

    @Override
    public Page<User> getAllUserPageable(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User findUserById(Long userId) {

        Optional< User > optional = userRepo.findById(userId);
        User user;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new ResourceNotFoundException(" User not found for id :: " + userId);
        }
        return user;
    }


    @Override
    public ResponseEntity<?> deleteByUserId(Long userId) {
        return userRepo.findById(userId).map(user -> {
            userRepo.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("User not found with userId" + userId ));
    }


    @Override
    public User updateUser(User newUser, Long id) {
        return userRepo.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setSurname(newUser.getSurname());

            return userRepo.save(user);
        }).orElseGet(()-> {
            newUser.setId(id);
            return userRepo.save(newUser);
        });
    }




}
