package ru.solon4ak.dao;

import ru.solon4ak.model.User;

import java.sql.SQLException;
import java.util.List;

public abstract class UserDao {
    public abstract void add(User user);
    public abstract List<User> listAll();
    public abstract void delete(User user);
    public abstract void update(User user);
    public abstract User get(long id);
    public abstract User getByName(String name);
}
