package ru.javastudy.springMVC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

abstract class DAOFM<E, K> {
    Connection connection = null;
    void connect () throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/WebFM";
            connection = DriverManager.getConnection(url, "root", "123");
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception(e);
        }
    }

    public abstract List<E> getAll() throws SQLException;

    public abstract List<K> getAllKeys() throws SQLException;

    public abstract E read(K id) throws SQLException;

    public abstract E getEntityById(K id);

    public abstract Boolean update(E entity) throws SQLException;

    public abstract Boolean create(E entity) throws SQLException;

    public abstract Boolean delete(K id) throws SQLException;
}
