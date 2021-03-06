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
package com.lemal.library.repositories;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.lemal.library.models.BaseEntity;
import com.lemal.library.models.Author;

/**
 * Repository class for <code>Author</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Vitaliy Fedoriv
 */
public interface AuthorRepository {

    /**
     * Retrieve <code>Author</code>s from the data store by last name, returning all authors whose last name <i>starts</i>
     * with the given name.
     *
     * @param lastName Value to search for
     * @return a <code>Collection</code> of matching <code>Author</code>s (or an empty <code>Collection</code> if none
     * found)
     */
    Collection<Author> findByLastName(String lastName) throws DataAccessException;

    /**
     * Retrieve an <code>Author</code> from the data store by id.
     *
     * @param id the id to search for
     * @return the <code>Author</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not found
     */
    Author findById(int id) throws DataAccessException;


    /**
     * Save an <code>Author</code> to the data store, either inserting or updating it.
     *
     * @param author the <code>Author</code> to save
     * @see BaseEntity#isNew
     */
    void save(Author author) throws DataAccessException;
    
    /**
     * Retrieve <code>Author</code>s from the data store, returning all authors 
     *
     * @return a <code>Collection</code> of <code>Author</code>s (or an empty <code>Collection</code> if none
     * found)
     */
	Collection<Author> findAll() throws DataAccessException;
	
    /**
     * Delete an <code>Author</code> to the data store by <code>Author</code>.
     *
     * @param author the <code>Author</code> to delete
     * 
     */
	void delete(Author author) throws DataAccessException;

	Collection<Author> findByQuotes_Name(String quoteName);


}
