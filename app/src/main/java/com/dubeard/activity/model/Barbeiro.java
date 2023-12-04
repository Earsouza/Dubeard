package com.dubeard.activity.model;

public class Barbeiro {

    public String name = "";
    public String fone = "";
    public String mail = "";

    public Barbeiro(){}

    @Override
    public String toString() {
        return  "Nome:" + name + "\n" + "Telefone:" + fone + "\n"+ "Email:" + mail;
    }

    public Barbeiro(String name, String fone, String mail) {
        this.fone = fone;
        this.name = name;
        this.mail = mail;
    }
}
