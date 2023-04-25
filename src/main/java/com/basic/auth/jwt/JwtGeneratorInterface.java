package com.basic.auth.jwt;

import java.util.Map;

import com.basic.auth.model.User;

public interface JwtGeneratorInterface {
	Map<String, String> generateToken(User user);
}
