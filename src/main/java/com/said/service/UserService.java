package com.said.service;

import com.said.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> getListUsers();
    void updateUser(User user);
    void deleteUserById(int id);
    User getUserById(int id);
    User getUserByLogin(String login);
}
