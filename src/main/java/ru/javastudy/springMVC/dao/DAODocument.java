package ru.javastudy.springMVC.dao;

import ru.javastudy.springMVC.model.Document;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAODocument extends DAOFM<Document, Integer> {
    public DAODocument() throws Exception {
        connect();
    }

    @Override
    public List<Document> getAll() throws SQLException {
        List<Document> keys = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement(
                    "SELECT document_id, name, owner, doc_path, " +
                            "parent_id, isDirectory, isProtected FROM documents ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Boolean isDir = rs.getInt(6) != 0;
                Boolean isProt = rs.getInt(7) != 0;
                keys.add(new Document(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), isDir, isProt));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return keys;
    }

    @Override
    public List<Integer> getAllKeys() throws SQLException {
        List<Integer> keys = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement(
                    "SELECT document_id FROM documents ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                keys.add(rs.getInt(1));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return keys;
    }

    @Override
    public Document read(Integer id) {
        return null;
    }

    @Override
    public Document getEntityById(Integer id) {
        return null;
    }

    @Override
    public Boolean update(Document entity) {
        return null;
    }

    @Override
    public Boolean create(Document entity) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
