package ru.solon4ak.service;

import ru.solon4ak.dao.UserDao;
import ru.solon4ak.dao.UserDaoHBNTImpl;
import ru.solon4ak.dao.UserDaoJDBCImpl;
import ru.solon4ak.model.User;
import ru.solon4ak.util.DBException;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userService;

    private UserDao userDao;

    private UserServiceImpl() {
        userDao = UserDaoHBNTImpl.getInstance();
    }

    public static UserServiceImpl getInstance() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    @Override
    public List<User> getAllUsers() throws DBException {
        try {
            return userDao.listAll();
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
    }

    @Override
    public User getUserById(long id) throws DBException {
        try {
            return userDao.get(id);
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
    }

    @Override
    public User getUserByName(String name) throws DBException {
        try {
            return userDao.getByName(name);
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
    }

    @Override
    public void addUser(User user) throws DBException {
        try {
            user.setDateCreated(Date.from(Instant.now()));
            user.setLastUpdate(Date.from(Instant.now()));
            userDao.add(user);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void updateUser(User user) throws DBException {
        try {
            user.setLastUpdate(Date.from(Instant.now()));
            userDao.update(user);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deleteUser(User user) throws DBException {
        try {
            userDao.delete(user);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

}
