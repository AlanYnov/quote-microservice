/*
 * Copyright 2016 the original author or authors.
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

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.lemal.library.models.Author;
import com.lemal.library.models.Quote;

/**
 * @author Vitaliy Fedoriv
 *
 */

public class JacksonCustomAuthorSerializer extends StdSerializer<Author> {

	public JacksonCustomAuthorSerializer() {
		this(null);
	}

	public JacksonCustomAuthorSerializer(Class<Author> t) {
		super(t);
	}

	@Override
	public void serialize(Author author, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject();
		if (author.getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeNumberField("id", author.getId());
		}

		jgen.writeStringField("first_name", author.getFirstName());
		jgen.writeStringField("last_name", author.getLastName());
		jgen.writeStringField("birth", author.getBirth());
		// write quotes array
		jgen.writeArrayFieldStart("quotes");
		for (Quote quote : author.getQuotes()) {
			jgen.writeStartObject(); // quote
			if (quote.getId() == null) {
				jgen.writeNullField("id");
			} else {
				jgen.writeNumberField("id", quote.getId());
			}
			jgen.writeStringField("name", quote.getName());
			jgen.writeStringField("release_date", quote.getReleaseDate());

            if (quote.getAuthor().getId() == null) {
                jgen.writeNullField("author");
            } else {
                jgen.writeNumberField("author", quote.getAuthor().getId());
            }
			jgen.writeEndObject(); // quote
		}
		jgen.writeEndArray(); // quotes
		jgen.writeEndObject(); // author
	}

}
