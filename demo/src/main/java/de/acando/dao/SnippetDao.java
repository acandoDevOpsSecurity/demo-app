package de.acando.dao;

import org.springframework.data.repository.CrudRepository;

import de.acando.jpa.Snippet;

public interface SnippetDao extends CrudRepository<Snippet, Integer>{

}
