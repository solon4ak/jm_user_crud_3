package ru.solon4ak.dao;

import ru.solon4ak.model.User;
import ru.solon4ak.util.DBHelperJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private DBHelperJDBC dbUtil;

    private static UserDaoJDBCImpl instance;

    private UserDaoJDBCImpl() {
        dbUtil = DBHelperJDBC.getInstance();
    }

    public static UserDaoJDBCImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoJDBCImpl();
        }
        return instance;
    }

    @Override
    public void add(User user) throws SQLException {

        StringBuilder sb = new StringBuilder();
        sb.append("insert into users (first_name, last_name, email, address, phone_number, birth_date) ");
        sb.append("values (?, ?, ?, ?, ?, ?)");

        Connection connection = dbUtil.connect();

        PreparedStatement ps = connection.prepareStatement(sb.toString());
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getAddress());
        ps.setString(5, user.getPhoneNumber());
        ps.setDate(6, new java.sql.Date(user.getBirthDate().getTime()));

        ps.executeUpdate();
        ps.close();
        dbUtil.disconnect();
    }

    @Override
    public List<User> listAll() throws SQLException {
        createTable();
        List<User> users = new ArrayList<>();
        String sql = "select * from users";
        Connection connection = dbUtil.connect();
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        User user;

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

        result.close();
        stmt.close();
        dbUtil.disconnect();

        return users;
    }

    @Override
    public void delete(User user) throws SQLException {
        String sql = "DELETE FROM users where id = ?";
        Connection connection = dbUtil.connect();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, user.getId());

        statement.executeUpdate();
        statement.close();
        dbUtil.disconnect();
    }

    @Override
    public void update(User user) throws SQLException {

        StringBuilder sb = new StringBuilder();
        sb.append("update users set first_name=?, last_name=?, email=?, ");
        sb.append("address=?, phone_number=?, birth_date=? ");
        sb.append("where id=?");

        Connection connection = dbUtil.connect();
        PreparedStatement ps = connection.prepareStatement(sb.toString());
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getAddress());
        ps.setString(5, user.getPhoneNumber());
        ps.setDate(6, new java.sql.Date(user.getBirthDate().getTime()));
        ps.setLong(7, user.getId());
        ps.executeUpdate();
        ps.close();
        dbUtil.disconnect();
    }

    @Override
    public User get(long id) throws SQLException {
        User user = null;

        String sql = "select * from users where id = ?";
        Connection connection = dbUtil.connect();
        PreparedStatement statement = connection.prepareStatement(sql);
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
        statement.close();
        dbUtil.disconnect();
        return user;
    }

    @Override
    public User getByName(String name) throws SQLException {

        String sql = "select * from users where first_name = ?";
        Connection connection = dbUtil.connect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);

        ResultSet resultSet = statement.executeQuery();

        User user = null;
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
        statement.close();
        dbUtil.disconnect();
        return user;
    }

    public void createTable() throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("create table if not exists users ");
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

        Connection connection = dbUtil.connect();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sb.toString());
        stmt.close();
        dbUtil.disconnect();
    }
}
