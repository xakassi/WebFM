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
    public Document read(Integer id) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement(
                    "SELECT document_id, name, owner, doc_path, parent_id," +
                            "isDirectory, isProtected FROM documents WHERE document_id=?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Boolean isDir = rs.getInt(6) != 0;
                Boolean isProt = rs.getInt(7) != 0;
                return new Document(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getInt(5), isDir, isProt);
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

    public List<Document> readDirectory(Integer parentId) throws SQLException {
        List<Document> files = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement(
                    "SELECT document_id, name, owner, doc_path, parent_id," +
                            "isDirectory, isProtected FROM documents WHERE parent_id=?");
            stmt.setInt(1, parentId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Boolean isDir = rs.getInt(6) != 0;
                Boolean isProt = rs.getInt(7) != 0;
                files.add(new Document(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getInt(5), isDir, isProt));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return files;
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
    public Boolean create(Document entity) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement(
                    "INSERT INTO documents " +
                            "(name, owner, doc_path, parent_id, isDirectory, isProtected) " +
                            "VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getOwner());
            stmt.setString(3, entity.getDocPath());
            stmt.setInt(4, entity.getParentId());
            int dir = entity.getDirectory() ? 1 : 0;
            stmt.setInt(5, dir);
            int prot = entity.getProtected() ? 1 : 0;
            stmt.setInt(6, prot);
            stmt.execute();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return true;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
