package ru.javastudy.springMVC.service;

import ru.javastudy.springMVC.dao.DAODocument;
import ru.javastudy.springMVC.dao.DAOPermission;
import ru.javastudy.springMVC.dao.DAOUser;
import ru.javastudy.springMVC.model.Document;
import ru.javastudy.springMVC.model.Permission;
import ru.javastudy.springMVC.model.User;

import java.util.ArrayList;

public class UserService {
    private DAOUser daoUser;
    private DAODocument daoDocument;
    private DAOPermission daoPermission;

    public UserService() throws Exception {
        this.daoUser = new DAOUser();
        this.daoDocument = new DAODocument();
        this.daoPermission = new DAOPermission();
    }

    public Boolean register(String login, String password) throws Exception {
        if (!daoUser.isLoginNotExist(login)) return false;
        ArrayList<Document> files = (ArrayList<Document>) daoDocument.getAll();
        for (Document d : files) {
            if (d.getProtected()) daoPermission.create(new Permission(login, d.getDocId(), 1));
            else daoPermission.create(new Permission(login, d.getDocId(), 2));
        }

        daoUser.create(new User(login, password, 1));
        return true;
    }

    public Boolean auth(String login, String password) throws Exception {
        if (daoUser.isLoginNotExist(login)) return false;
        User u = daoUser.read(login);
        return u.getPassword().equals(password);
    }
}
