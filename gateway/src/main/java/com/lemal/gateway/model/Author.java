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
package com.lemal.gateway.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;
import com.lemal.gateway.rest.JacksonCustomAuthorDeserializer;
import com.lemal.gateway.rest.JacksonCustomAuthorSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Simple JavaBean domain object representing an author.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
@JsonSerialize(using = JacksonCustomAuthorSerializer.class)
@JsonDeserialize(using = JacksonCustomAuthorDeserializer.class)
public class Author extends BaseEntity {
    @NotEmpty
    private String lastName;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String birth;

    private Set<Quote> quotes;


    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirth() {
        return this.birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
    @JsonIgnore
    protected Set<Quote> getQuotesInternal() {
        if (this.quotes == null) {
            this.quotes = new HashSet<>();
        }
        return this.quotes;
    }

    protected void setQuotesInternal(Set<Quote> quotes) {
        this.quotes = quotes;
    }

    public List<Quote> getQuotes() {
        List<Quote> sortedQuotes = new ArrayList<>(getQuotesInternal());
        PropertyComparator.sort(sortedQuotes, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedQuotes);
    }

    public void addQuote(Quote quote) {
        getQuotesInternal().add(quote);
        quote.setAuthor(this);
    }

    /**
     * Return the Quote with the given name, or null if none found for this Author.
     *
     * @param name to test
     * @return true if quote name is already in use
     */
    public Quote getQuote(String name) {
        return getQuote(name, false);
    }

    /**
     * Return the Quote with the given name, or null if none found for this Author.
     *
     * @param name to test
     * @return true if quote name is already in use
     */
    public Quote getQuote(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Quote quote : getQuotesInternal()) {
            if (!ignoreNew || !quote.isNew()) {
                String compName = quote.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return quote;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

            .append("id", this.getId())
            .append("new", this.isNew())
            .append("lastName", this.getLastName())
            .append("firstName", this.getFirstName())
            .append("birth", this.getBirth())
            .toString();
    }
}
