package ru.javastudy.springMVC.model;

public class Permission {
    private String userLogin;
    private Integer docId;
    private Integer permissions;

    public Permission(String userLogin, Integer docId, Integer permissions) {
        this.userLogin = userLogin;
        this.docId = docId;
        this.permissions = permissions;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public Integer getDocId() {
        return docId;
    }

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }
}

