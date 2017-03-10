package de.acando.dao;

import org.springframework.data.repository.CrudRepository;

import de.acando.jpa.User;

public interface UserDao extends CrudRepository<User, Integer>{
	
	User findByName(String name);
	User findByNameAndPassword(String name, String password);

}
