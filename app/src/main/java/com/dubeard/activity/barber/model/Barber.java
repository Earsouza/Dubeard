package com.dubeard.activity.barber.model;

public class Barber {
    private String id;
    private String name;
    private String phone;
    private String email;

    private boolean isCabelo;

    private boolean isBarba;

    private boolean isSobrancelha;


    public Barber() {
    }

    public Barber(String id, String name, String phone, String email, boolean isCabelo, boolean isBarba, boolean isSobrancelha) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.isCabelo = isCabelo;
        this.isBarba = isBarba;
        this.isSobrancelha = isSobrancelha;
    }
    public Barber(String id, String name, String phone, boolean isCabelo, boolean isBarba, boolean isSobrancelha) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.isCabelo = isCabelo;
        this.isBarba = isBarba;
        this.isSobrancelha = isSobrancelha;
    }

    public Barber(String id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public boolean isCabelo() {
        return isCabelo;
    }

    public void setIsCabelo(boolean isCabelo) {
        this.isCabelo = isCabelo;
    }

    public boolean isBarba() {
        return isBarba;
    }

    public void setIsBarba(boolean isBarba) {
        this.isBarba = isBarba;
    }

    public boolean isSobrancelha() {
        return isSobrancelha;
    }

    public void setIsSobrancelha(boolean isSobrancelha) {
        this.isSobrancelha = isSobrancelha;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" + "Phone: " + phone + "\n" + "E-mail: " + email;
    }

}
