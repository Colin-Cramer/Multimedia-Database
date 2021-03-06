package com.promineotech.multimediadatabase.service;

import javax.naming.AuthenticationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.promineotech.multimediadatabase.entity.User;
import com.promineotech.multimediadatabase.repository.UserRepository;

@Service
public class UserService {
	
	private static final Logger logger = LogManager.getLogger(UserService.class);

	@Autowired
	private UserRepository repo;
	
	public User getUserById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to retrieve customer: " + id, e);
			throw e;
		}
	}
	
	public void deleteUser(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to delete user: " + id, e);
			throw new Exception("Unable to delete user.");
		}
	}
	
	public User updateUser(User user, Long userId) throws AuthenticationException {
		try {
			User oldUser = repo.findOne(userId);
			oldUser.setUsername(user.getUsername());
			oldUser.setHash((BCrypt.hashpw(user.getHash(), BCrypt.gensalt())));
			return repo.save(oldUser);
		} catch (DataIntegrityViolationException e) {
			throw new AuthenticationException("Information not valid.");
		}
	}
}
