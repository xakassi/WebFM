package ru.javastudy.springMVC.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private String login;
    private String password;
    private Integer roleId;

    public User(String login, String password, Integer roleId) {
        this.login = login;
        this.password = password;
        this.roleId = roleId;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}

/*
<spring:form method="post" modelAttribute="userJSP" action="check-user">

    Name: <spring:input
        path="name"/> (path="" - указывает путь, используемый в modelAttribute=''. в нашем случае User.name) <br/>
    Password: <spring:input path="password"/> <br/>
    <spring:button>Next Page</spring:button>

</spring:form>

@Component
public class User {

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}*/
