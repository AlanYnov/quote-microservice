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

package com.lemal.gateway.rest;

import java.io.IOException;

import com.lemal.gateway.model.Author;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * @author Vitaliy Fedoriv
 *
 */

public class JacksonCustomAuthorDeserializer extends StdDeserializer<Author> {

	public JacksonCustomAuthorDeserializer(){
		this(null);
	}

	public JacksonCustomAuthorDeserializer(Class<Author> t) {
		super(t);
	}

	@Override
	public Author deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);
		Author author = new Author();
		String firstName = node.get("first_name").asText(null);
		String lastName = node.get("last_name").asText(null);
		String birth = node.get("birth").asText(null);
		if (node.hasNonNull("id")) {
			author.setId(node.get("id").asInt());
		}
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBirth(birth);
		return author;
	}

}
