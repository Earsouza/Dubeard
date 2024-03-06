package com.dubeard.activity.model;

public class Cliente {
    private String id;

    private String name;
    private String fone;
    private String mail;

    public Cliente(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return  "Nome:" + name + "\n" + "Telefone:" + fone + "\n"+ "Email:" + mail;
    }

    public Cliente (String name, String fone, String mail) {
        this.fone = fone;
        this.name = name;
        this.mail = mail;
    }

}