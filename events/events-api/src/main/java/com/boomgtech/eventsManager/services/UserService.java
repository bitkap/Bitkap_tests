package com.boomgtech.eventsManager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boomgtech.eventsManager.dao.UserRepository;
import com.boomgtech.eventsManager.entities.User;

@Service
public class UserService {
	
	@Autowired
    UserRepository userRepository;
	
	public  List<User> getAllUser()
    { 
        List<User> list = userRepository.findAll();
        return list;
    }
	
	public Optional<User> getOneById(long id)
    {         
        return userRepository.findById(id);
    }
    
    public User saveUser(User user)
    {         
        return userRepository.saveAndFlush(user);
    }
    
    public void deleteUser(User user)
    {         
        userRepository.delete(user);
    }
}
