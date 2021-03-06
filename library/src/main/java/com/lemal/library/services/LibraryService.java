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

import org.springframework.dao.DataAccessException;

import com.lemal.library.models.Author;
import com.lemal.library.models.Quote;

/**
 * Mostly used as a facade so all controllers have a single point of entry
 *
 * @author Michael Isvy
 * @author Vitaliy Fedoriv
 */
public interface LibraryService {

	Quote findQuoteById(int id) throws DataAccessException;
	Collection<Quote> findAllQuotes() throws DataAccessException;
	void saveQuote(Quote quote) throws DataAccessException;
	void deleteQuote(Quote quote) throws DataAccessException;
	
	Author findAuthorById(int id) throws DataAccessException;
	Collection<Author> findAllAuthors() throws DataAccessException;
	void saveAuthor(Author author) throws DataAccessException;
	void deleteAuthor(Author author) throws DataAccessException;
	Collection<Author> findAuthorByLastName(String lastName) throws DataAccessException;
	Collection<Author> findAuthorsByQuoteName(String quoteName);
	
}
