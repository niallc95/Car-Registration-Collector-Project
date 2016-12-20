package mobileapp.assignment.mobileappproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import mobileapp.assignment.mobileappproject.Objects.Vehicle;

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Books table name
    private static final String TABLE_VEHICLES = "vehicles";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_VEHICLE_MANUFACTURER = "vehicleManufacturer";
    private static final String KEY_VEHICLE_NAME = "vehicleName";
    private static final String KEY_VEHICLE_CATEGORY = "vehicleCategory";
    private static final String KEY_VEHICLE_REGISTRATION = "vehicleRegistration";
    private static final String KEY_VEHICLE_OWNER= "owner";

    private static final String[] COLUMNS = {KEY_ID,KEY_VEHICLE_MANUFACTURER,KEY_VEHICLE_NAME,KEY_VEHICLE_CATEGORY,KEY_VEHICLE_REGISTRATION,KEY_VEHICLE_OWNER};

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "vehicleDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create the vehicle table
        String CREATE_VEHICLE_TABLE = "CREATE TABLE vehicles ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "vehicleManufacturer TEXT, " +
                "vehicleName TEXT, " +
                "vehicleCategory TEXT, " +
                "vehicleRegistration TEXT, " +
                "owner TEXT )";

        // create books table
        db.execSQL(CREATE_VEHICLE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS vehicles");

        // create fresh books table
        this.onCreate(db);
    }

    public void addVehicle(Vehicle vehicle){
        //Log vehicle when added for testing purposes
        Log.d("addVehicle", vehicle.toString());

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(KEY_VEHICLE_MANUFACTURER, vehicle.getVehicleManufacturer());
        values.put(KEY_VEHICLE_NAME, vehicle.getVehicleName());
        values.put(KEY_VEHICLE_CATEGORY, vehicle.getVehicleCategory());
        values.put(KEY_VEHICLE_REGISTRATION, vehicle.getRegistration());
        values.put(KEY_VEHICLE_OWNER, vehicle.getOwner());


        db.insert(TABLE_VEHICLES,null,values);

        db.close();
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new LinkedList<Vehicle>();

        //Select all from vehicles query
        String query = "SELECT  * FROM " + TABLE_VEHICLES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //go over each row, build vehicle object and add it to list
        Vehicle vehicle = null;
        if (cursor.moveToFirst()) {
            do {
                vehicle = new Vehicle(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));

                // Add vehicle to vehicles
                vehicles.add(vehicle);
            } while (cursor.moveToNext());
        }

        Log.d("getAllVehicles()", vehicles.toString());

        // return vehicles
        return vehicles;
    }

    public List<Vehicle> getVehicle(String reg) {
        List<Vehicle> vehicles = new LinkedList<Vehicle>();
        //Select vehicle query
        String query = "SELECT  * FROM " + TABLE_VEHICLES +" WHERE "+KEY_VEHICLE_REGISTRATION+"="+reg;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //go over each row, build vehicle object and add it to list
        Vehicle vehicle = null;
        if (cursor.moveToFirst()) {
            do {
                vehicle = new Vehicle(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));

                // Add vehicle to vehicles
                vehicles.add(vehicle);
            } while (cursor.moveToNext());
        }

        Log.d("getVehicle()", vehicles.toString());

        // return vehicles
        return vehicles;
    }

    //Delete
    public void deleteVehicleById(int Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VEHICLES, KEY_ID + "=?", new String[]{String.valueOf(Id)});
    }

    public void deleteAllCars() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VEHICLES, null, null);
    }
}
