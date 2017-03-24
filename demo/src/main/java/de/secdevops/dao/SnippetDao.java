package de.secdevops.dao;

import org.springframework.data.repository.CrudRepository;

import de.secdevops.jpa.Snippet;

public interface SnippetDao extends CrudRepository<Snippet, Integer>{

}
