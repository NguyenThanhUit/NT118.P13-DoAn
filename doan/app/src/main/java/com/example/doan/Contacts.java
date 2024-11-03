package com.example.doan;

public class Contacts {
    private String maKH;
    private String name;
    private String phoneNumber;
    private String address;

    public Contacts(String maKH, String name, String phoneNumber, String address) {
        this.maKH = maKH;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
