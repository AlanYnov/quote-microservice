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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lemal.gateway.model.Author;
import com.lemal.gateway.model.Quote;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * @author Vitaliy Fedoriv
 *
 */

public class JacksonCustomQuoteDeserializer extends StdDeserializer<Quote> {

	public JacksonCustomQuoteDeserializer() {
		this(null);
	}

	public JacksonCustomQuoteDeserializer(Class<Quote> t) {
		super(t);
	}

	@Override
	public Quote deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		Quote quote = new Quote();
		Author author = new Author();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = parser.getCodec().readTree(parser);
		JsonNode author_node = node.get("author");
		author = mapper.treeToValue(author_node, Author.class);
		int quoteId = node.get("id").asInt();
		String name = node.get("name").asText(null);
		String releaseDate = node.get("releaseDate").asText(null);

		if (!(quoteId == 0)) {
			quote.setId(quoteId);
		}
		quote.setName(name);
		quote.setReleaseDate(releaseDate);
		quote.setAuthor(author);
		return quote;
	}

}
