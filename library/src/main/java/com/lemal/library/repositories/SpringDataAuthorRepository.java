/*
 * Copyright 2002-2013 the original author or authors.
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

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.lemal.library.models.Author;

/**
 * Spring Data JPA specialization of the {@link AuthorRepository} interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */

public interface SpringDataAuthorRepository extends AuthorRepository, Repository<Author, Integer> {

    @Override
    @Query("SELECT DISTINCT author FROM Author author left join fetch author.quotes WHERE author.lastName LIKE :lastName%")
    Collection<Author> findByLastName(@Param("lastName") String lastName);

    @Override
    @Query("SELECT author FROM Author author left join fetch author.quotes WHERE author.id =:id")
    Author findById(@Param("id") int id);
    
    Collection<Author> findByQuotes_Name(String quoteName);
}