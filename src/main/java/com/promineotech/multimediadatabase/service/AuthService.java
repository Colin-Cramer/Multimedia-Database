package com.promineotech.multimediadatabase.service;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.promineotech.multimediadatabase.entity.Credentials;
import com.promineotech.multimediadatabase.entity.User;
import com.promineotech.multimediadatabase.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository repo;
	
	public User register(Credentials cred) throws AuthenticationException {
		User user = new User();
		user.setUsername(cred.getUsername());
		user.setHash((BCrypt.hashpw(cred.getPassword(), BCrypt.gensalt())));
		try {
			repo.save(user);
			return user;
		} catch (DataIntegrityViolationException e) {
			throw new AuthenticationException("Username not available");
		}
	}
	
	public User login(Credentials cred) throws AuthenticationException {
		User foundUser = repo.findByUsername(cred.getUsername());
		if(foundUser != null && BCrypt.checkpw(cred.getPassword(), foundUser.getHash())) {
			return foundUser;
		}
		throw new AuthenticationException("Incorrect username or password");
	}
	
	public User updateUser(Credentials cred, User user, Long id ) throws AuthenticationException {
		try {
			User oldUser = repo.findOne(id);
			oldUser.setUsername(cred.getUsername());
			user.setHash((BCrypt.hashpw(cred.getPassword(), BCrypt.gensalt())));
			oldUser.setEmail(user.getEmail());
			return repo.save(oldUser);
		} catch (DataIntegrityViolationException e) {
			throw new AuthenticationException("Information not valid.");
		}
	}
}
