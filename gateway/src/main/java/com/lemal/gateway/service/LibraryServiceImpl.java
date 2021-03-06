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
package com.lemal.gateway.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import com.lemal.gateway.model.Author;
import com.lemal.gateway.model.Quote;
import com.lemal.gateway.repository.AuthorRepository;
import com.lemal.gateway.repository.QuoteRepository;
import org.springframework.stereotype.Service;

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
	public Collection<Quote> findAllQuotes()  {
		return quoteRepository.findAll();
	}

	@Override
	public void deleteQuote(Quote quote)  {
		quoteRepository.delete(quote.getId());
	}

	@Override
	public Collection<Author> findAllAuthors()  {
		return authorRepository.findAll();
	}

	@Override
	public void deleteAuthor(Author author)  {
		authorRepository.delete(author.getId());
	}

	@Override
	public Author findAuthorById(int id)  {
		Author author = null;
		try {
			author = authorRepository.findById(id);
		} catch (Exception e) {
		// just ignore not found exceptions for Jdbc/Jpa realization
			return null;
		}
		return author;
	}

	@Override
	public Quote findQuoteById(int id)  {
		Quote quote = null;
		try {
			quote = quoteRepository.findById(id);
		} catch (Exception e) {
		// just ignore not found exceptions for Jdbc/Jpa realization
			return null;
		}
		return quote;
	}

	@Override
	public void saveQuote(Quote quote)  {
		quoteRepository.save(quote);
		
	}

	@Override
	public void saveAuthor(Author author)  {
		authorRepository.save(author);
		
	}

	@Override
	public Collection<Author> findAuthorByLastName(String lastName)  {
		return authorRepository.findByLastName(lastName);
	}

	@Override
	public Collection<Author> findAuthorsByQuoteName(String quoteName) {
		return authorRepository.findByQuotes_Name(quoteName);
	}
	
}
