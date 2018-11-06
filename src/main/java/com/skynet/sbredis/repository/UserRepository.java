package com.skynet.sbredis.repository;

import com.skynet.sbredis.model.User;

import java.util.Map;

public interface UserRepository {
    Map<Integer, User> findAllUsers();

    void add(User user);

    void delete(Integer id);

    User findUser(Integer id);
}
