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

import com.lemal.gateway.model.Author;
import com.lemal.gateway.model.Quote;

/**
 * Mostly used as a facade so all controllers have a single point of entry
 *
 * @author Michael Isvy
 * @author Vitaliy Fedoriv
 */
public interface LibraryService {

	Quote findQuoteById(int id) ;

	Collection<Quote> findAllQuotes() ;

	void saveQuote(Quote quote) ;

	void deleteQuote(Quote quote) ;

	Author findAuthorById(int id) ;

	Collection<Author> findAllAuthors() ;

	void saveAuthor(Author author) ;

	void deleteAuthor(Author author) ;

	Collection<Author> findAuthorByLastName(String lastName) ;

	Collection<Author> findAuthorsByQuoteName(String quoteName);

}
