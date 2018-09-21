package com.gecko.meldezettel.db;

/**
 * Created by alarmattacke on 06.11.16.
 */

public class Mandate {

    private String name;
    private String code;
    private long id;

    public Mandate(String name, String code, long id) {
        this.name = name;
        this.code = code;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString()
    {
        return getCode() + " " + getName();
    }
}