package com.bitkap.test.presentation;

import com.bitkap.test.model.User;
import com.bitkap.test.service.iservice.IUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class UserControllerRest {

    private final IUser iUser;



    public UserControllerRest(IUser iUser) {
        this.iUser = iUser;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //method for find one user by id
    // example of url for this get method :
    //localhost:8080/api/1
    @GetMapping("/{userId}")
    public User findUserById(@PathVariable(value = "userId") Long id) {
        return iUser.findUserById(id);
    }

    // get all users
    // example of url for this get method :
    //localhost:8080/api/users
    @GetMapping("/users")
    public Page<User> getAllUser(Pageable pageable) {
        return iUser.getAllUserPageable(pageable);
    }

    // create new user
    // example of url for this post method :
    //localhost:8080/api/users
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
      return iUser.saveUser(user);

    }

    // update user by her userId
    // example of url for this put method :
    // localhost:8080/api/users/1
    @PutMapping("/users/{userId}")
    public User updateUser(@PathVariable(value = "userId") Long userId, @Valid @RequestBody User userRequest) {
        return iUser.updateUser(userRequest,userId);
    }

    // delete user by userId
    // example of url for this delete method :
    //localhost:8080/api/users/1
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable(value = "userId") Long userId) {
        return iUser.deleteByUserId(userId);
    }




}









