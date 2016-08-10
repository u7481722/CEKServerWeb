package com.ubn.repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class KeyRepositoryImpl {

	protected Logger logger = LoggerFactory.getLogger(KeyRepositoryImpl.class);
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	private ValueOperations<String, String> hashOps;

	@PostConstruct
	private void init() {
		this.logger.info("init KeyRepositoryImpl...");
		this.hashOps = this.redisTemplate.opsForValue();
		this.redisTemplate.setKeySerializer(new StringRedisSerializer());
		this.redisTemplate.setValueSerializer(new StringRedisSerializer());
		this.redisTemplate.setDefaultSerializer(new StringRedisSerializer());
	}

	public void save(String key, String value) {
		this.hashOps.set(key, value);
	}

	public String findOne(String key) {
		return (String) this.hashOps.get(key);
	}

}
