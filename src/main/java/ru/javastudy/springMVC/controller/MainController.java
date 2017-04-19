package ru.javastudy.springMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.javastudy.springMVC.model.User;
import ru.javastudy.springMVC.service.UserService;


@Controller
public class MainController {

    /*Попадаем сюда на старте приложения*/
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userJSP", new User());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /*как только на index.jsp подтвердится форма
    <spring:form method="post"  modelAttribute="userJSP" action="check-user">,
    то попадем вот сюда
     */
    @RequestMapping(value = "/check-user")
    public ModelAndView checkUser(@ModelAttribute("userJSP") User user) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        UserService us = new UserService();
        if (us.auth(user.getLogin(), user.getPassword())) {
            modelAndView.setViewName("fileExplorer");
        } else {
            modelAndView.setViewName("authFail");
        }

        //записываем в атрибут userJSP (используется на странице *.jsp) объект user
        modelAndView.addObject("userJSP", user);

        return modelAndView; //после уйдем на представление, указанное чуть выше, если оно будет найдено.
    }

    @RequestMapping(value = "/reg-user")
    public ModelAndView regUser(@ModelAttribute("userJSP") User user) {
        ModelAndView modelAndView = new ModelAndView();

        //имя представления, куда нужно будет перейти
        modelAndView.setViewName("reg");

        //записываем в атрибут userJSP (используется на странице *.jsp объект user
        modelAndView.addObject("userJSP", user);

        return modelAndView; //после уйдем на представление, указанное чуть выше, если оно будет найдено.
    }

    @RequestMapping(value = "/checkLogin")
    public ModelAndView checkLogin(@ModelAttribute("userJSP") User user) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        UserService us = new UserService();
        if (us.register(user.getLogin(), user.getPassword())) {
            modelAndView.setViewName("RegSuccess");
        } else {
            modelAndView.setViewName("RegFail");
        }

        return modelAndView; //после уйдем на представление, указанное чуть выше, если оно будет найдено.
    }
}