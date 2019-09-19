package ru.solon4ak.service;

import ru.solon4ak.dao.UserDao;
import ru.solon4ak.dao.UserDaoFactoryImpl;
import ru.solon4ak.model.User;

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
        return userDao.getByNickName(name);
    }

    @Override
    public void addUser(User user) {
        user.setRole("user");
        user.setDateCreated(Date.from(Instant.now()));
        user.setLastUpdate(Date.from(Instant.now()));
        userDao.add(user);
    }

    @Override
    public void updateUser(User user) {
        user.setLastUpdate(Date.from(Instant.now()));
        userDao.update(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public boolean verifyUser(String login, String password) {
        User user = this.getUserByName(login);
        return user != null && password.equals(user.getPassword());
    }

}
