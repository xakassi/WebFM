package ru.javastudy.springMVC.service;

import ru.javastudy.springMVC.dao.DAODocument;
import ru.javastudy.springMVC.dao.DAOPermission;
import ru.javastudy.springMVC.dao.DAOUser;
import ru.javastudy.springMVC.model.Document;

import java.sql.SQLException;
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

    public String normalName(String s) {
        return s.replaceAll(" ", "_");
    }

    public Boolean createDocument(Document d) throws SQLException {
        return daoDocument.create(d);
    }
}
