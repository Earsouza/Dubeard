package com.dubeard.activity.barber.model;

public class Client {
    private String id;
    private String name;
    private String phone;
    private String mail;

    public Client() {
    }

    public Client(String name, String phone, String mail) {
        this.phone = phone;
        this.name = name;
        this.mail = mail;
    }

    public Client(String id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.mail = email;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Nome:" + name + "\n" + "Telefone:" + phone + "\n" + "Email:" + mail;
    }

}