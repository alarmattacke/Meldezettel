package com.gecko.meldezettel.db;

/**
 * Created by alarmattacke on 26.09.16.
 */

public class Building {
    private String name;
    private String code;
    private long mandate_id;
    private long id;

    public Building(String code, String name, long mandate_id,long id) {
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
