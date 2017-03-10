package de.acando.dao;

import org.springframework.data.repository.CrudRepository;

import de.acando.jpa.UserProfile;

public interface UserDao extends CrudRepository<UserProfile, Integer>{
	
	UserProfile findByName(String name);

}
