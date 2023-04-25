package com.basic.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basic.auth.exception.UserNotFoundException;
import com.basic.auth.jwt.JwtGeneratorInterface;
import com.basic.auth.model.User;
import com.basic.auth.service.UserService;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
	
	private UserService userService;
	private JwtGeneratorInterface jwtGenerator;
	
	
	  @Autowired
	  public UserController(UserService userService, JwtGeneratorInterface jwtGenerator){
	    this.userService=userService;
	    this.jwtGenerator=jwtGenerator;
	  }
	
	  @GetMapping("/test")
	  public String test() {
		  return "test string";
	  }
	  
	  @PostMapping("/register")
	  public ResponseEntity<?> postUser(@RequestBody User user){
		  System.out.println("POst user=============");
	  try{
	     userService.saveUser(user);
	     return new ResponseEntity<>(user, HttpStatus.CREATED);
	   } catch (Exception e){
	     return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	   }
	  }
	  
	    
	  
	  @PostMapping("/login")
	  public ResponseEntity<?> loginUser(@RequestBody User user) {
	    try {
	      if(user.getUserName() == null || user.getPassword() == null) {
	      throw new UserNotFoundException("UserName or Password is Empty");
	    }
	    User userData = userService.getUserByNameAndPassword(user.getUserName(), user.getPassword());
	    if(userData == null){
	       throw new UserNotFoundException("UserName or Password is Invalid");
	    }
	       return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
	    } catch (UserNotFoundException e) {
	       return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	    }
	  }

}
