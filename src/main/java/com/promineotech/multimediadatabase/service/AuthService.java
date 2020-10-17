package com.promineotech.multimediadatabase.service;

import java.security.Key;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.promineotech.multimediadatabase.entity.Credentials;
import com.promineotech.multimediadatabase.entity.User;
import com.promineotech.multimediadatabase.repository.UserRepository;
import com.promineotech.multimediadatabase.view.LoggedInView;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthService {

	private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	@Autowired
	private UserRepository userRepoository;
	
	public User register(Credentials cred) throws AuthenticationException {
		User user = new User();
		user.setUsername(cred.getUsername());
		user.setHash((BCrypt.hashpw(cred.getPassword(), BCrypt.gensalt())));
		user.setLevel(cred.getLevel());
		try {
			userRepoository.save(user);
			return user;
		} catch (DataIntegrityViolationException e) {
			throw new AuthenticationException("Username not available");
		}
	}
	
	public LoggedInView login(Credentials cred) throws AuthenticationException {
		User foundUser = userRepoository.findByUsername(cred.getUsername());
		if(BCrypt.checkpw(cred.getPassword(), foundUser.getHash())) {
			LoggedInView view = new LoggedInView();
			view.setUser(foundUser);
			view.setJwt(generateToken(foundUser));
			return view;
		}else {
		throw new AuthenticationException("Incorrect username or password.");
		}
	}
	
	private String generateToken(User user) {
		String jwt = Jwts.builder()
				.claim("role", user.getLevel())
				.claim("userId", user.getId())
				.setSubject("RL RL ITEMS")
				.signWith(key)
				.compact();
		return jwt;
	}
	
	public boolean isCorrectUser(String jwt, Long userId) {
		return new Long((Integer)Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(jwt)
				.getBody()
				.get("userId"))
				.equals(userId);
	}
	
	public String getToken(HttpServletRequest request) throws Exception {
		String header = request.getHeader("Authorization");
		if (header == null) {
			throw new Exception("Request contains no token.");
		}
		return header.replaceAll("Bearer ", "");
	}

}
