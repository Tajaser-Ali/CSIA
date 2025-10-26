package com.example.csia.models;

public class Chemical {
    private int id;
    private String name;
    private String HCode;

    public Chemical(int id, String name, String HCode) {
        this.id = id;
        this.name = name;
        this.HCode = HCode;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHCode() {
        return HCode;
    }
}
