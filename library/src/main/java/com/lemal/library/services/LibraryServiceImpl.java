/*
 * Copyright 2002-2017 the original author or authors.
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
package com.lemal.library.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lemal.library.models.Author;
import com.lemal.library.models.Quote;
import com.lemal.library.repositories.AuthorRepository;
import com.lemal.library.repositories.QuoteRepository;

/**
 * Mostly used as a facade for all Quoteclinic controllers
 * Also a placeholder for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 * @author Vitaliy Fedoriv
 */
@Service

public class LibraryServiceImpl implements LibraryService {

    private QuoteRepository quoteRepository;
    private AuthorRepository authorRepository;

    @Autowired
     public LibraryServiceImpl(
       		 QuoteRepository quoteRepository,
    		 AuthorRepository authorRepository) {
        this.quoteRepository = quoteRepository;
        this.authorRepository = authorRepository;
    }

	@Override
	@Transactional(readOnly = true)
	public Collection<Quote> findAllQuotes() throws DataAccessException {
		return quoteRepository.findAll();
	}

	@Override
	@Transactional
	public void deleteQuote(Quote quote) throws DataAccessException {
		quoteRepository.delete(quote);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Author> findAllAuthors() throws DataAccessException {
		return authorRepository.findAll();
	}

	@Override
	@Transactional
	public void deleteAuthor(Author author) throws DataAccessException {
		authorRepository.delete(author);
	}

	@Override
	@Transactional(readOnly = true)
	public Author findAuthorById(int id) throws DataAccessException {
		Author author = null;
		try {
			author = authorRepository.findById(id);
		} catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
		// just ignore not found exceptions for Jdbc/Jpa realization
			return null;
		}
		return author;
	}

	@Override
	@Transactional(readOnly = true)
	public Quote findQuoteById(int id) throws DataAccessException {
		Quote quote = null;
		try {
			quote = quoteRepository.findById(id);
		} catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
		// just ignore not found exceptions for Jdbc/Jpa realization
			return null;
		}
		return quote;
	}

	@Override
	@Transactional
	public void saveQuote(Quote quote) throws DataAccessException {
		quoteRepository.save(quote);
		
	}

	@Override
	@Transactional
	public void saveAuthor(Author author) throws DataAccessException {
		authorRepository.save(author);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Author> findAuthorByLastName(String lastName) throws DataAccessException {
		return authorRepository.findByLastName(lastName);
	}

	@Override
	public Collection<Author> findAuthorsByQuoteName(String quoteName) {
		return authorRepository.findByQuotes_Name(quoteName);
	}
	
}
