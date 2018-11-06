package com.skynet.sbredis.repository;

import com.skynet.sbredis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final String KEY = "User";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public UserRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Map<Integer, User> findAllUsers() {
        return hashOperations.entries(KEY);
    }

    @Override
    public void add(User user) {
        hashOperations.put(KEY, user.getId(), user);
    }

    @Override
    public void delete(Integer id) {
        hashOperations.delete(KEY, id);
    }

    @Override
    public User findUser(Integer id) {
        return (User) hashOperations.get(KEY, id);
    }
}
