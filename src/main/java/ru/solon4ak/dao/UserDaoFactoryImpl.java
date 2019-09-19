package ru.solon4ak.dao;

import org.hibernate.SessionFactory;
import ru.solon4ak.util.DBHelper;
import ru.solon4ak.util.PropertyUtil;

import java.util.Properties;

public class UserDaoFactoryImpl implements UserDaoFactory {

    Properties properties = PropertyUtil.getInstance().getProperties();
    String api = properties.getProperty("db.conn.api");

    @Override
    public UserDao createUserDao() {
        DBHelper helper = DBHelper.getInstance();
        if ("hibernate".equals(api)) {
            SessionFactory sessionFactory = helper.getSessionFactory();
            UserDao userDao = new UserDaoHibernateImpl(sessionFactory);
            return userDao;
        } else if ("jdbc".equals(api)) {
            return new UserDaoJDBCImpl(helper);
        }
        return null;
    }
}
