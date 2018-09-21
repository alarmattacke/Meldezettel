package com.gecko.meldezettel.db;

/**
 * Created by alarmattacke on 11.10.16.
 */

public class Room {
    private String room_name;
    private String floor_name;
    private long building_id;

    private long id;


    public Room(String room_name, String floor_name,long building_id ,long id) {

        this.room_name = room_name;
        this.floor_name = floor_name;
        this.building_id = building_id;
        this.id = id;
    }


    public String getRoomName() {
        return room_name;
    }

    public void setRoomName(String room_name) {
        this.room_name = room_name;
    }

    public String getFloorName() {
        return floor_name;
    }

    public void setFloorName(String floor_name) {
        this.floor_name = floor_name;
    }

    public long getBuildingId() {
        return building_id;
    }

    public void setBuildingId(long building_id) {
        this.building_id = building_id;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString()
    {
        return getRoomName() + " " + getFloorName();
    }
}
