/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lemal.library.controllers;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lemal.library.models.Author;
import com.lemal.library.services.LibraryService;

/**
 * @author Vitaliy Fedoriv
 *
 */

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/authors")
public class AuthorRestController {

	@Autowired
	private LibraryService libraryService;

	@RequestMapping(value = "/*/lastname/{lastName}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Author>> getAuthorsList(@PathVariable("lastName") String authorLastName) {
		if (authorLastName == null) {
			authorLastName = "";
		}
		Collection<Author> authors = this.libraryService.findAuthorByLastName(authorLastName);
		if (authors.isEmpty()) {
			return new ResponseEntity<Collection<Author>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Author>>(authors, HttpStatus.OK);
	}

	@RequestMapping(value = "/*/quotename/{quoteName}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Author>> getAuthorsListByQuoteName(@PathVariable("quoteName") String quoteName) {
		if (quoteName == null) {
			quoteName = "";
		}
		System.out.println("Get authors by quote name with name : " + quoteName);
		Collection<Author> authors = this.libraryService.findAuthorsByQuoteName(quoteName);
		if (authors.isEmpty()) {
			return new ResponseEntity<Collection<Author>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Author>>(authors, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Author>> getAuthors() {
		Collection<Author> authors = this.libraryService.findAllAuthors();
		return new ResponseEntity<Collection<Author>>(authors, HttpStatus.OK);
	}

	@RequestMapping(value = "/{authorId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Author> getAuthor(@PathVariable("authorId") int authorId) {
		System.out.println(this.libraryService.toString());
		Author author = null;
		author = this.libraryService.findAuthorById(authorId);
		if (author == null) {
			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Author>(author, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Author> addAuthor(@RequestBody @Valid Author author, BindingResult bindingResult,
			UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || author.getId() != null) {
			BindingErrorsResponse errors = new BindingErrorsResponse(author.getId());
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Author>(headers, HttpStatus.BAD_REQUEST);
		}
		this.libraryService.saveAuthor(author);
		headers.setLocation(ucBuilder.path("/api/authors/{id}").buildAndExpand(author.getId()).toUri());
		return new ResponseEntity<Author>(author, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{authorId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Author> updateAuthor(@PathVariable("authorId") int authorId, @RequestBody @Valid Author author,
			BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
		boolean bodyIdMatchesPathId = author.getId() == null || authorId == author.getId();
		if (bindingResult.hasErrors() || !bodyIdMatchesPathId) {
			BindingErrorsResponse errors = new BindingErrorsResponse(authorId, author.getId());
			errors.addAllErrors(bindingResult);
			HttpHeaders headers = new HttpHeaders();
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Author>(headers, HttpStatus.BAD_REQUEST);
		}
		Author currentAuthor = this.libraryService.findAuthorById(authorId);
		if (currentAuthor == null) {
			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		}
		currentAuthor.setFirstName(author.getFirstName());
		currentAuthor.setLastName(author.getLastName());
		currentAuthor.setBirth(author.getBirth());
		this.libraryService.saveAuthor(currentAuthor);
		return new ResponseEntity<Author>(currentAuthor, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{authorId}", method = RequestMethod.DELETE, produces = "application/json")
	@Transactional
	public ResponseEntity<Void> deleteAuthor(@PathVariable("authorId") int authorId) {
		Author author = this.libraryService.findAuthorById(authorId);
		System.out.println("Deleting author with id : " + authorId);
		if (author == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		this.libraryService.deleteAuthor(author);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
