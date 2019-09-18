package ru.solon4ak.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.solon4ak.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {

    private Properties configProps;

    private static DBHelper instance;

    private DBHelper() {
        configProps = PropertyUtil.getInstance().getProperties();
    }

    public static DBHelper getInstance() {
        if (instance == null) {
            return new DBHelper();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(configProps.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }

        Connection connection = DriverManager.getConnection(
                configProps.getProperty("db.url"),
                configProps.getProperty("db.user"),
                configProps.getProperty("db.password")
        );
        return connection;
    }

    public SessionFactory getSessionFactory() {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registryBuilder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @SuppressWarnings("UnusedDeclaration")
    private Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        Properties properties = new Properties();
        properties.put("hibernate.dialect", configProps.getProperty("db.dialect.mysql"));
        properties.put("hibernate.connection.driver_class", configProps.getProperty("db.driver"));
        properties.put("hibernate.connection.url", configProps.getProperty("db.url"));
        properties.put("hibernate.connection.username", configProps.getProperty("db.user"));
        properties.put("hibernate.connection.password", configProps.getProperty("db.password"));
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "create");

        configuration.setProperties(properties);
        return configuration;
    }
}
