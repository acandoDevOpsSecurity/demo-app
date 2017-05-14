package de.secdevops.demo.snippets;

import org.springframework.data.repository.CrudRepository;

public interface SnippetDao extends CrudRepository<Snippet, Integer>{

}
