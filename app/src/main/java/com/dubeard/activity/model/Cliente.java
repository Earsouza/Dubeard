package com.dubeard.activity.model;

public class Cliente {
    public String name = "";
    public String fone = "";
    public String mail = "";

    public Cliente(){}

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