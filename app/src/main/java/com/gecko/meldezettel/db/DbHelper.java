package com.gecko.meldezettel.db;

/**
 * Created by alarmattacke on 26.09.16.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = DbHelper.class.getSimpleName();


    public static final String DB_NAME = "meldezettel.db";
    public static final int DB_VERSION = 9;

    public static final String TABLE_BUILDING_LIST = "buildings";
    public static final String TABLE_ROOM_LIST = "rooms";
    public static final String TABLE_MANDATE_LIST = "mandates";

    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_ROOM = "room_name";
    public static final String COLUMN_FLOOR = "floor_name";
    public static final String COLUMN_BUILDING_ID = "building_id";

    public static final String COLUMN_MANDATE_ID = "mandate_id";

    public static final String SQL_CREATE_MANDATE =
            "CREATE TABLE " + TABLE_MANDATE_LIST +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CODE + " TEXT NOT NULL, " +
                    COLUMN_NAME + " TEXT NOT NULL);";


    public static final String SQL_CREATE_BUILDING =
            "CREATE TABLE " + TABLE_BUILDING_LIST +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CODE + " TEXT NOT NULL, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_MANDATE_ID + " INTEGER NOT NULL," +
                    "FOREIGN KEY(" + COLUMN_MANDATE_ID + ") REFERENCES mandates(id));";

    public static final String SQL_CREATE_ROOM =
            "CREATE TABLE " + TABLE_ROOM_LIST +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ROOM + " TEXT NOT NULL, " +
                    COLUMN_FLOOR + " TEXT NOT NULL, " +
                    COLUMN_BUILDING_ID + " INTEGER NOT NULL," +
                    "FOREIGN KEY(" + COLUMN_BUILDING_ID + ") REFERENCES buildings(id));";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_BUILDING + " angelegt.");
            db.execSQL(SQL_CREATE_MANDATE);
            db.execSQL(SQL_CREATE_BUILDING);
            db.execSQL(SQL_CREATE_ROOM);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTables(db);
        onCreate(db);
    }

    public void dropTables(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUILDING_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANDATE_LIST);
    }
}

