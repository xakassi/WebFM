package ru.javastudy.springMVC.dao;

import ru.javastudy.springMVC.extra.Pair;
import ru.javastudy.springMVC.model.Permission;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DAOPermission extends DAOFM<Permission, Pair<Integer, String>> {
    public DAOPermission() throws Exception {
        connect();
    }

    @Override
    public List<Permission> getAll() {
        return null;
    }

    @Override
    public List<Pair<Integer, String>> getAllKeys() {
        return null;
    }

    @Override
    public Permission read(Pair<Integer, String> id) {
        return null;
    }

    @Override
    public Permission getEntityById(Pair<Integer, String> id) {
        return null;
    }

    @Override
    public Boolean update(Permission entity) {
        return null;
    }

    @Override
    public Boolean create(Permission entity) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(
                    "INSERT INTO permissions " +
                            "(user_login, document_id, permission) " +
                            "VALUES (?, ?, ?)");
            stmt.setString(1, entity.getUserLogin());
            stmt.setInt(2, entity.getDocId());
            stmt.setInt(3, entity.getPermissions());
            return stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public Boolean delete(Pair<Integer, String> id) {
        return null;
    }
}
