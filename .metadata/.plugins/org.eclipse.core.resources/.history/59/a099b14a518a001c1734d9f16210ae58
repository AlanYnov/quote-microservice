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

package com.lemal.gateway.rest;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.lemal.gateway.model.Quote;
import com.lemal.gateway.service.LibraryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Vitaliy Fedoriv
 *
 */

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api/quotes")
public class QuoteRestController {

	@Autowired
	private LibraryService libraryService;

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{quoteId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Quote> getQuote(@PathVariable("quoteId") int quoteId){
		Quote quote = this.libraryService.findQuoteById(quoteId);
		if(quote == null){
			return new ResponseEntity<Quote>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Quote>(quote, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Quote>> getQuotes(){
    	System.out.println(this.libraryService.toString());
		Collection<Quote> quotes = this.libraryService.findAllQuotes();
		if(quotes.isEmpty()){
			return new ResponseEntity<Collection<Quote>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Quote>>(quotes, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Quote> addQuote(@RequestBody @Valid Quote quote, BindingResult bindingResult, UriComponentsBuilder ucBuilder){
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (quote == null)){
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Quote>(headers, HttpStatus.BAD_REQUEST);
		}
		this.libraryService.saveQuote(quote);
		headers.setLocation(ucBuilder.path("/api/quotes/{id}").buildAndExpand(quote.getId()).toUri());
		return new ResponseEntity<Quote>(quote, headers, HttpStatus.CREATED);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{quoteId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Quote> updateQuote(@PathVariable("quoteId") int quoteId, @RequestBody @Valid Quote quote, BindingResult bindingResult){
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (quote == null)){
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Quote>(headers, HttpStatus.BAD_REQUEST);
		}
		Quote currentQuote = this.libraryService.findQuoteById(quoteId);
		if(currentQuote == null){
			return new ResponseEntity<Quote>(HttpStatus.NOT_FOUND);
		}
		currentQuote.setReleaseDate(quote.getReleaseDate());
		currentQuote.setName(quote.getName());
		currentQuote.setAuthor(quote.getAuthor());
		this.libraryService.saveQuote(currentQuote);
		return new ResponseEntity<Quote>(currentQuote, HttpStatus.NO_CONTENT);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{quoteId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Void> deleteQuote(@PathVariable("quoteId") int quoteId){
		Quote quote = this.libraryService.findQuoteById(quoteId);
		if(quote == null){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		this.libraryService.deleteQuote(quote);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}


}
