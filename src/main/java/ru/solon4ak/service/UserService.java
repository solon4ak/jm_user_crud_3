package ru.solon4ak.service;

import ru.solon4ak.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(long id);
    User getUserByName(String name);
    void addUser(User user, String role);
    void updateUser(User user);
    void deleteUser(User user);
    boolean verifyUser(String login, String password);
}
