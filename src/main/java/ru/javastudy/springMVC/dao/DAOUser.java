package ru.javastudy.springMVC.dao;

import ru.javastudy.springMVC.model.Document;
import ru.javastudy.springMVC.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAOUser extends DAOFM<User, String> {

    public DAOUser() throws Exception {
        connect();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<String> getAllKeys() {
        return null;
    }

    @Override
    public User read(String login) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement(
                    "SELECT login, password, role_id FROM users " +
                            "WHERE login=?");
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString(1), rs.getString(2), rs.getInt(3));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return null;
    }

    @Override
    public User getEntityById(String id) {
        return null;
    }

    @Override
    public Boolean update(User entity) {
        return null;
    }

    @Override
    public Boolean create(User entity) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
                    "INSERT INTO users " +
                            "(login, password, role_id) " +
                            "VALUES (?, ?, ?)");
            stmt.setString(1, entity.getLogin());
            stmt.setString(2, entity.getPassword());
            stmt.setInt(3, entity.getRoleId());
            return stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public Boolean delete(String id) throws SQLException {
        User user = read(id);
        if(user == null) return false;
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
                    "DELETE FROM users WHERE login=?");
            stmt.setString(1, id);
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return true;
    }

    public Boolean isLoginNotExist(String login) throws Exception {
        User user = read(login);
        return user == null;
    }
}
