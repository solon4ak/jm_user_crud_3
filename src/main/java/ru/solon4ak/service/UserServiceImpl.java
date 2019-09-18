package ru.solon4ak.service;

import ru.solon4ak.dao.UserDao;
import ru.solon4ak.dao.UserDaoFactoryImpl;
import ru.solon4ak.model.User;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userService;

    private UserDao userDao;

    private UserServiceImpl() {
        userDao = new UserDaoFactoryImpl().createUserDao();
    }

    public static UserServiceImpl getInstance() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.listAll();
    }

    @Override
    public User getUserById(long id) {
        return userDao.get(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public void addUser(User user) {
        user.setDateCreated(Date.from(Instant.now(Clock.systemUTC())));
        user.setLastUpdate(Date.from(Instant.now(Clock.systemUTC())));
        userDao.add(user);
    }

    @Override
    public void updateUser(User user) {
        user.setLastUpdate(Date.from(Instant.now(Clock.systemUTC())));
        userDao.update(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

}
