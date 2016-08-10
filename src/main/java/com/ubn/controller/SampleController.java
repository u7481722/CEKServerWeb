package com.ubn.controller;

import javax.annotation.Resource;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ubn.model.Token;
import com.ubn.repository.TokenRepository;

@RestController
public class SampleController {
	@Resource
	CounterService counterService;
	
	@Resource
	TokenRepository tokenRepository;

	@RequestMapping(value = {"/saveToken/{id}"}, method = {RequestMethod.GET})
	public void saveToken(@PathVariable long id) {
		Token token = new Token();
		token.setId(id);
		token.setToken("123");
		tokenRepository.save(token);

		counterService.increment("count.kevin.test");
	}

}
