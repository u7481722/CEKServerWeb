package com.ubn.repository;

import java.util.Map;

import com.ubn.model.Token;

public interface TokenRepository {

	  void save(Token paramToken);
	  
	  void update(Token paramToken);
	  
	  Token findOne(String paramString);
	  
	  Map<Object, Object> findAll();
	
}
