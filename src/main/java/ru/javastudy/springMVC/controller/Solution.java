package ru.javastudy.springMVC.controller;

import ru.javastudy.springMVC.service.DocService;

/**
 * Created by user on 19.04.2017.
 */
public class Solution {
    public static void main(String[] args) throws Exception {
        /*User user = new User("ихихихихихихиххиииииииииииии!!!", "123", 1);
        UserService us = new UserService();
        System.out.println(us.register(user.getLogin(), user.getPassword()));*/
        DocService ds = new DocService();
        System.out.println(ds.get(1));
    }
}
