package com.basic.auth.service;

import org.springframework.stereotype.Service;

import com.basic.auth.exception.UserNotFoundException;
import com.basic.auth.model.User;

@Service
public interface UserService {
	public void saveUser(User user);
    public User getUserByNameAndPassword(String name, String password) throws UserNotFoundException;

}
