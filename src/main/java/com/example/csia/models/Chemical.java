package com.example.csia.models;

public class Chemical {
    private int id;
    private String name;
    private String HCode;
    private String disposal;

    public Chemical(int id, String name, String HCode, String disposal) {
        this.id = id;
        this.name = name;
        this.HCode = HCode;
        this.disposal = disposal;
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

    public String getDisposal() {
        return disposal;
    }
}
