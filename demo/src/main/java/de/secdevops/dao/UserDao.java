package de.secdevops.dao;

import org.springframework.data.repository.CrudRepository;

import de.secdevops.jpa.User;

public interface UserDao extends CrudRepository<User, Integer>{
	
	User findByName(String name);
	User findByNameAndPassword(String name, String password);

}
