package com.gecko.meldezettel.json;

/**
 * Created by alarmattacke on 10.10.16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Room {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("room_name")
    @Expose
    private String roomName;

    @SerializedName("floor_name")
    @Expose
    private String floorName;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     *
     * @param roomName
     * The room_name
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     *
     * @return
     * The floorName
     */
    public String getFloorName() {
        return floorName;
    }

    /**
     *
     * @param floorName
     * The floor_name
     */
    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
