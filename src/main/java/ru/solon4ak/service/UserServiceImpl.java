package ru.solon4ak.service;

import ru.solon4ak.dao.UserDao;
import ru.solon4ak.dao.UserDaoImpl;
import ru.solon4ak.model.User;
import ru.solon4ak.util.DBException;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userService;

    private UserDao userDao;

    private UserServiceImpl() {
        userDao = UserDaoImpl.getInstance();
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
    public boolean addUser(User user) throws DBException {
        try {
            return userDao.add(user);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public boolean updateUser(User user) throws DBException {
        try {
            return userDao.update(user);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public boolean deleteUser(long id) throws DBException {
        try {
            return userDao.delete(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

}
