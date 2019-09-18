package ru.solon4ak.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.solon4ak.model.User;
import ru.solon4ak.util.DBHelper;

import java.util.List;

public class UserDaoHibernateImpl extends UserDao {

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> listAll() {
        Session session = sessionFactory.openSession();
        List<User> userList = (List<User>) session.createQuery("FROM User").list();
        session.close();
        return userList;
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public User get(long id) {
        Session session = sessionFactory.openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public User getByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from User u where u.firstName = :name");
        query.setString("name", name);
        return (User) query.uniqueResult();
    }
}
