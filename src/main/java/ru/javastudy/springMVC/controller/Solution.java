package ru.javastudy.springMVC.controller;

import ru.javastudy.springMVC.model.Document;
import ru.javastudy.springMVC.service.DocService;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by user on 19.04.2017.
 */
public class Solution {
    public static void delete(DocService ds, Integer id) throws Exception {
        ArrayList<Document> children = (ArrayList<Document>) ds.getDirectory(id);
        for (Document d : children) {
            delete(ds, d.getDocId());
        }
        ds.deleteDocument(id);
    }

    public static void main(String[] args) throws Exception {
        /*User user = new User("ихихихихихихиххиииииииииииии!!!", "123", 1);
        UserService us = new UserService();
        System.out.println(us.register(user.getLogin(), user.getPassword()));*/
        DocService ds = new DocService();
        /*Document d = new Document(5, "Ляляля", "Taisia", "", 0, false, false);*/
        /*ds.createDocument(d);*/
        int id = 13;

        String rootPath = "C:\\Users\\user\\Documents\\Веб-приложение\\";
        File dir = new File(rootPath);
        Document curDir = ds.get(id);

        if (curDir != null) {
            String deletedPath = dir.getAbsolutePath() + File.separator + curDir.getDocPath() + curDir.getName();
            System.out.println(deletedPath);
            File deletedDoc = new File(deletedPath);
            if (deletedDoc.exists()) deletedDoc.delete();

            delete(ds, id);
        }

    }
}
