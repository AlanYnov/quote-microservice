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
package com.lemal.gateway.repository;

import java.util.Collection;
import java.util.List;

import com.lemal.gateway.model.BaseEntity;
import com.lemal.gateway.model.Quote;

/**
 * Repository class for <code>Quote</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Vitaliy Fedoriv
 */
public interface QuoteRepository {


    /**
     * Retrieve a <code>Quote</code> from the data store by id.
     *
     * @param id the id to search for
     * @return the <code>Quote</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not found
     */
    Quote findById(int id);

    /**
     * Save a <code>Quote</code> to the data store, either inserting or updating it.
     *
     * @param quote the <code>Quote</code> to save
     * @see BaseEntity#isNew
     */
    void save(Quote quote);
    
    /**
     * Retrieve <code>Quote</code>s from the data store, returning all authors 
     *
     * @return a <code>Collection</code> of <code>Quote</code>s (or an empty <code>Collection</code> if none
     * found)
     */
	Collection<Quote> findAll();

    /**
     * Delete an <code>Quote</code> to the data store by <code>Quote</code>.
     *
     * @param quote the <code>Quote</code> to delete
     * 
     */
	void delete(int quoteId);

}
