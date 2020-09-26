package com.promineotech.multimediadatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.multimediadatabase.entity.User;
import com.promineotech.multimediadatabase.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public User createUser(User user) {
		return repo.save(user);
	}
	
	public User login(User user) throws Exception {
		User foundUser = repo.findByUsername(user.getUsername());
		if(foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
			return foundUser;
		} else {
			throw new Exception("Invalid username or password, please try again.");
		}
	}
	
}
