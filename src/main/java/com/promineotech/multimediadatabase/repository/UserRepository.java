package com.promineotech.multimediadatabase.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.multimediadatabase.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

	public User findByUsername(String username);
}
