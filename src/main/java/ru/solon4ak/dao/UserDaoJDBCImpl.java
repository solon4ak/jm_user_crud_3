package ru.solon4ak.dao;

import ru.solon4ak.model.User;
import ru.solon4ak.util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoJDBCImpl extends UserDao {

    private final static Logger log = Logger.getLogger(UserDaoJDBCImpl.class.getName());

    private DBHelper dbUtil;

    public UserDaoJDBCImpl(DBHelper dbHelper) {
        this.dbUtil = dbHelper;
    }

    @Override
    public void add(User user) {

        StringBuilder sb = new StringBuilder();
        sb.append("insert into users (first_name, last_name, email, address, phone_number, birth_date) ");
        sb.append("values (?, ?, ?, ?, ?, ?)");

        try (Connection connection = dbUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sb.toString())) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getPhoneNumber());
            ps.setDate(6, new java.sql.Date(user.getBirthDate().getTime()));
//            ps.setDate(7, new java.sql.Date(user.getDateCreated().getTime()));
//            ps.setDate(8, new java.sql.Date(user.getLastUpdate().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            log.warning("Exception while adding user.");
        }

    }

    @Override
    public List<User> listAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";
        User user;

        try (Connection connection = dbUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet result = stmt.executeQuery(sql)) {
            createTable();
            while (result.next()) {
                user = new User(
                        result.getLong("id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("email"),
                        result.getString("address"),
                        result.getString("phone_number"),
                        result.getDate("birth_date")
                );
                user.setDateCreated(result.getTimestamp("date_created"));
                user.setLastUpdate(result.getTimestamp("last_update"));
                users.add(user);
            }
        } catch (SQLException e) {
            log.warning("Exception while listing users.");
        }

        return users;
    }

    @Override
    public void delete(User user) {
        String sql = "DELETE FROM users where id = ?";
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.warning("Exception while deleting user.");
        }
    }

    @Override
    public void update(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("update users set first_name=?, last_name=?, email=?, ");
        sb.append("address=?, phone_number=?, birth_date=? ");
        sb.append("where id=?");

        try (Connection connection = dbUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sb.toString())) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getPhoneNumber());
            ps.setDate(6, new java.sql.Date(user.getBirthDate().getTime()));
//            ps.setDate(7, new java.sql.Date(user.getLastUpdate().getTime()));
            ps.setLong(7, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.warning("Exception while updating user.");
        }
    }

    @Override
    public User get(long id) {
        User user = null;
        String sql = "select * from users where id = ?";
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("phone_number"),
                        resultSet.getDate("birth_date")
                );
                user.setDateCreated(resultSet.getTimestamp("date_created"));
                user.setLastUpdate(resultSet.getTimestamp("last_update"));
            }
            resultSet.close();
        } catch (SQLException e) {
            log.warning("Exception while finding user by id.");
        }
        return user;
    }

    @Override
    public User getByName(String name) {
        User user = null;
        String sql = "select * from users where first_name = ?";
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("phone_number"),
                        resultSet.getDate("birth_date")
                );
                user.setDateCreated(resultSet.getTimestamp("date_created"));
                user.setLastUpdate(resultSet.getTimestamp("last_update"));
            }
            resultSet.close();
        } catch (SQLException e) {
            log.warning("Exception while finding user by name.");
        }
        return user;
    }

    public void createTable() {
        dropTable();
        StringBuilder sb = new StringBuilder();
        sb.append("create table users ");
        sb.append("(id bigint auto_increment, ");
        sb.append("first_name varchar(256), ");
        sb.append("last_name varchar(256), ");
        sb.append("email varchar(256), ");
        sb.append("address varchar(512), ");
        sb.append("phone_number varchar(15), ");
        sb.append("birth_date date not null,");
        sb.append("date_created timestamp default CURRENT_TIMESTAMP, ");
        sb.append("last_update timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP, ");
        sb.append("primary key (id))");

        try (Connection connection = dbUtil.getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sb.toString());
        } catch (SQLException e) {
            log.warning("Exception while creating table.");
        }
    }

    public void dropTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("drop table if exists users");
        try (Connection connection = dbUtil.getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sb.toString());
        } catch (SQLException e) {
            log.warning("Exception while deleting table.");
        }
    }
}
