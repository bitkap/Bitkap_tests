package com.bitkap.test.dao;



import com.bitkap.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {

    boolean existsById(Long userId);


}


