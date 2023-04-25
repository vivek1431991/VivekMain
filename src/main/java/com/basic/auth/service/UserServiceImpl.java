package com.basic.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.auth.dao.UserRepository;
import com.basic.auth.exception.UserNotFoundException;
import com.basic.auth.model.User;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	  @Autowired
	  public UserServiceImpl(UserRepository userRepository){
	    this.userRepository=userRepository;
	  }
	  //Changes done
	  @Override
	  public void saveUser(User user) {
	    userRepository.save(user);
	  }
	  
	  @Override
	  public User getUserByNameAndPassword(String name, String password) throws UserNotFoundException {
	    User user = userRepository.findByUserNameAndPassword(name, password);
	    if(user == null){
	       throw new UserNotFoundException("Invalid id and password");
	    }
	    return user;
	  }

}
