package com.ubn.repository;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import com.ubn.model.Token;

@Repository
public class TokenRepositoryImpl implements TokenRepository {
	private static final String KEY = "tokenStore";
	@Resource
	private RedisTemplate<String, Token> redisTemplate;
	private HashOperations hashOps;

	@PostConstruct
	private void init() {
		System.out.println("init TokenStoreRepositoryImpl...");
		this.hashOps = this.redisTemplate.opsForHash();
		this.redisTemplate.setKeySerializer(new StringRedisSerializer());
		this.redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		this.redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer(Token.class));
		this.redisTemplate.setDefaultSerializer(new StringRedisSerializer());
	}

	public void save(Token token) {
		this.hashOps.put("tokenStore", token.getId(), token);
		this.redisTemplate.expire("tokenStore", 60L, TimeUnit.SECONDS);
	}

	public void update(Token token) {
		this.hashOps.put("tokenStore", Long.valueOf(token.getId()), token);
	}

	public Token findOne(String id) {
		return (Token) this.hashOps.get("tokenStore", id);
	}

	public Map<Object, Object> findAll() {
		return this.hashOps.entries("tokenStore");
	}

}
