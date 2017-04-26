package ru.javastudy.springMVC.service;

import ru.javastudy.springMVC.dao.DAODocument;
import ru.javastudy.springMVC.dao.DAOPermission;
import ru.javastudy.springMVC.dao.DAOUser;
import ru.javastudy.springMVC.model.Document;

import java.sql.SQLException;
import java.util.List;

public class DocService {
    private DAOUser daoUser;
    private DAODocument daoDocument;
    private DAOPermission daoPermission;

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
}
