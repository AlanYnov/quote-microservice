package com.lemal.gateway.repository.api;

import java.util.Collection;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import com.lemal.gateway.model.Quote;
import com.lemal.gateway.repository.QuoteRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${feign.quoteServiceURL}/api/quotes/", name = "quotes-api")
public interface ApiQuoteRepository extends QuoteRepository {


	@RequestMapping(value = "/{quoteId}", method = RequestMethod.GET, produces = "application/json")
    Quote findById(@PathVariable("quoteId") int id);

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    void save(@RequestBody Quote quote);
    
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	Collection<Quote> findAll();

	@RequestMapping(value = "/{quoteId}", method = RequestMethod.DELETE, produces = "application/json")
	void delete(@PathVariable("quoteId") int quoteId);
}
