package ru.javastudy.springMVC.model;

public class Document {
    private Integer docId;
    private String name;
    private String owner;
    private String docPath;
    private Integer parentId;
    private Boolean isDirectory;
    private Boolean isProtected;

    public Document(Integer docId, String name, String owner, String docPath, Integer parentId, Boolean isDirectory, Boolean isProtected) {
        this.docId = docId;
        this.name = name;
        this.owner = owner;
        this.docPath = docPath;
        this.parentId = parentId;
        this.isDirectory = isDirectory;
        this.isProtected = isProtected;
    }

    public Document() {
    }

    @Override
    public String toString() {
        return "Document{" +
                "docId=" + docId +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", docPath='" + docPath + '\'' +
                ", parentId=" + parentId +
                ", isDirectory=" + isDirectory +
                ", isProtected=" + isProtected +
                '}';
    }

    public Integer getDocId() {
        return docId;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getDocPath() {
        return docPath;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Boolean getDirectory() {
        return isDirectory;
    }

    public Boolean getProtected() {
        return isProtected;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProtected(Boolean aProtected) {
        isProtected = aProtected;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setDirectory(Boolean directory) {
        isDirectory = directory;
    }
}

