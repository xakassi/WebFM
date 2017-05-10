package ru.javastudy.springMVC.service;

import ru.javastudy.springMVC.dao.DAODocument;
import ru.javastudy.springMVC.dao.DAOPermission;
import ru.javastudy.springMVC.dao.DAOUser;
import ru.javastudy.springMVC.model.Document;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DocService {
    private DAOUser daoUser;
    private DAODocument daoDocument;
    private DAOPermission daoPermission;
    Set<String> selectedDocs = new HashSet<String>();

    public DocService() throws Exception {
        this.daoUser = new DAOUser();
        this.daoDocument = new DAODocument();
        this.daoPermission = new DAOPermission();
    }

    public List<Document> getDirectory(Integer parentId) throws SQLException {
        return daoDocument.readDirectory(parentId);
    }

    public Document get(Integer docId) throws SQLException {
        return daoDocument.read(docId);
    }

    public String currentPath(Integer docId) throws SQLException {
        if (docId == 0) return "Корневая директория";
        Document curDir = get(docId);
        return curDir.getDocPath() + "\\" + curDir.getName();
    }

    public Boolean createDocument(Document d) throws SQLException {
        return daoDocument.create(d);
    }

    public Boolean deleteDocument(Integer id) throws SQLException {
        return daoDocument.delete(id);
    }

    public ArrayList<Document> getAllDocuments() throws SQLException {
        return (ArrayList<Document>) daoDocument.getAll();
    }
}
