package com.gecko.meldezettel.db;

/**
 * Created by alarmattacke on 26.09.16.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;


public class DataSource {
    private SQLiteDatabase database;
    private DbHelper dbHelper;
    private static final String LOG_TAG = DataSource.class.getSimpleName();

    private String[] mandate_columns = {DbHelper.COLUMN_ID, DbHelper.COLUMN_CODE, DbHelper.COLUMN_NAME};
    private String[] building_columns = {DbHelper.COLUMN_ID, DbHelper.COLUMN_CODE, DbHelper.COLUMN_NAME, DbHelper.COLUMN_MANDATE_ID};
    private String[] room_columns = {DbHelper.COLUMN_ID, DbHelper.COLUMN_ROOM, DbHelper.COLUMN_FLOOR, DbHelper.COLUMN_BUILDING_ID};

    public DataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new DbHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    public Mandate createMandate(String code, String name) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_CODE, code);
        values.put(DbHelper.COLUMN_NAME, name);

        long insertId = database.insert(DbHelper.TABLE_MANDATE_LIST, null, values);

        Cursor cursor = database.query(DbHelper.TABLE_MANDATE_LIST,
                mandate_columns, DbHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Mandate mandateMemo = cursorToMandate(cursor);
        cursor.close();

        return mandateMemo;
    }

    public Building createBuilding(String code, String name, long mandate_id) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_CODE, code);
        values.put(DbHelper.COLUMN_NAME, name);
        values.put(DbHelper.COLUMN_MANDATE_ID, mandate_id);

        long insertId = database.insert(DbHelper.TABLE_BUILDING_LIST, null, values);

        Cursor cursor = database.query(DbHelper.TABLE_BUILDING_LIST,
                building_columns, DbHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Building buildingMemo = cursorToBuilding(cursor);
        cursor.close();

        return buildingMemo;
    }

    private Mandate cursorToMandate(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DbHelper.COLUMN_ID);
        int idCode = cursor.getColumnIndex(DbHelper.COLUMN_CODE);
        int idName = cursor.getColumnIndex(DbHelper.COLUMN_NAME);

        String code = cursor.getString(idCode);
        String name = cursor.getString(idName);
        long id = cursor.getLong(idIndex);

        Mandate mandate = new Mandate(code, name, id);
        return mandate;
    }

    private Building cursorToBuilding(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DbHelper.COLUMN_ID);
        int idCode = cursor.getColumnIndex(DbHelper.COLUMN_CODE);
        int idName = cursor.getColumnIndex(DbHelper.COLUMN_NAME);
        int idMandate = cursor.getColumnIndex(DbHelper.COLUMN_MANDATE_ID);

        String code = cursor.getString(idCode);
        String name = cursor.getString(idName);
        long mandate_id = cursor.getLong(idMandate);
        long id = cursor.getLong(idIndex);

        Building building = new Building(code, name, mandate_id,id);

        return building;
    }

    public Room createRoom(String room_name, String floor_name, long building_id) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_ROOM, room_name);
        values.put(DbHelper.COLUMN_FLOOR, floor_name);
        values.put(DbHelper.COLUMN_BUILDING_ID, building_id);

        long insertId = database.insert(DbHelper.TABLE_ROOM_LIST, null, values);

        Cursor cursor = database.query(DbHelper.TABLE_ROOM_LIST,
                room_columns, DbHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Room room_memo = cursorToRoom(cursor);
        cursor.close();

        return room_memo;
    }

    private Room cursorToRoom(Cursor cursor) {
        int idRoom = cursor.getColumnIndex(DbHelper.COLUMN_ROOM);
        int idFloor = cursor.getColumnIndex(DbHelper.COLUMN_FLOOR);
        int idBuilding = cursor.getColumnIndex(DbHelper.COLUMN_BUILDING_ID);
        int idIndex = cursor.getColumnIndex(DbHelper.COLUMN_ID);

        String room_name = cursor.getString(idRoom);
        String floor_name = cursor.getString(idFloor);
      //  long building_id = cursor.getLong(idBuilding);
      //  long id = cursor.getLong(idIndex);

        Room room = new Room(room_name, floor_name, 0 , 0);
        return room;
    }

    public List<Building> getAllBuildings() {
        List<Building> buildingList = new ArrayList<>();
        Cursor cursor = database.query(DbHelper.TABLE_BUILDING_LIST,
                                        building_columns, null, null, null, null, null);
        cursor.moveToFirst();
        Building building;

        while(!cursor.isAfterLast()) {
            building = cursorToBuilding(cursor);
            buildingList.add(building);
            Log.d(LOG_TAG, "ID: " + building.getId() + ", Inhalt: " + building.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return buildingList;
    }

    public List<Room> getRoomsbyBuilding(String building_id) {
        List<Room> roomList = new ArrayList<>();
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select room_name, floor_name from rooms where building_id =" +  building_id  ,null);
        cursor.moveToFirst();
        Room room;

        while(!cursor.isAfterLast()) {
            room = cursorToRoom(cursor);
            roomList.add(room);
            Log.d(LOG_TAG, "ID: " + room.getId() + ", Inhalt: " + room.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return roomList;
    }

    public void emptyDbTables() {
        database = dbHelper.getWritableDatabase();
        dbHelper.dropTables(database);
        dbHelper.onCreate(database);
    }
}


