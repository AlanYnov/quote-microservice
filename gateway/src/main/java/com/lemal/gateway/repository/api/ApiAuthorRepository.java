package com.lemal.gateway.repository.api;

import java.util.Collection;

import org.springframework.cloud.openfeign.FeignClient;
import com.lemal.gateway.model.Author;
import com.lemal.gateway.repository.AuthorRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${feign.quoteServiceURL}/api/authors/", name = "authors-api")
public interface ApiAuthorRepository extends AuthorRepository {

	@RequestMapping(value = "/*/lastname/{lastName}", method = RequestMethod.GET, produces = "application/json")
	Collection<Author> findByLastName(@PathVariable("lastName") String lastName);
	
	@RequestMapping(value = "/{authorId}", method = RequestMethod.GET, produces = "application/json")
	Author findById(@PathVariable("authorId") int id);
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	void save(@RequestBody Author author);
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	Collection<Author> findAll();
	
	@RequestMapping(value = "/{authorId}", method = RequestMethod.DELETE, produces = "application/json")
	void delete(@PathVariable("authorId") int authorId);
	
	@RequestMapping(value = "/*/quotename/{quoteName}", method = RequestMethod.GET, produces = "application/json")
	Collection<Author> findByQuotes_Name(@PathVariable("quoteName") String quoteName);
	
}
