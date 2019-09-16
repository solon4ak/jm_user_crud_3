package ru.solon4ak.dao;

import ru.solon4ak.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    boolean add(User user) throws SQLException;
    List<User> listAll() throws SQLException;
    boolean delete(long id) throws SQLException;
    boolean update(User user) throws SQLException;
    User get(long id) throws SQLException;
    User getByName(String name) throws SQLException;
}
