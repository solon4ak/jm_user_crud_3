package ru.solon4ak.service;

import ru.solon4ak.model.User;
import ru.solon4ak.util.DBException;

import java.util.List;

public interface UserService {
    List<User> getAllUsers() throws DBException;
    User getUserById(long id) throws DBException;
    User getUserByName(String name) throws DBException;
    void addUser(User user) throws DBException;
    void updateUser(User user) throws DBException;
    void deleteUser(User user) throws DBException;
}
