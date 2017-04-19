package ru.javastudy.springMVC.controller;

import ru.javastudy.springMVC.service.UserService;
import ru.javastudy.springMVC.model.User;

/**
 * Created by user on 19.04.2017.
 */
public class Solution {
    public static void main(String[] args) throws Exception {
        User user = new User("ихихихихихихиххиииииииииииии!!!", "123", 1);
        UserService us = new UserService();
        System.out.println(us.register(user.getLogin(), user.getPassword()));
    }
}
