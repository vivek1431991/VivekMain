package com.basic.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basic.auth.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
   public User findByUserNameAndPassword(String userName, String password);
}
