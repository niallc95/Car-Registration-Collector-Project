//package mobileapp.assignment.mobileappproject.Database;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Hashtable;
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.DatabaseUtils;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.database.sqlite.SQLiteDatabase;
//import android.provider.ContactsContract;
//
//import mobileapp.assignment.mobileappproject.Objects.Vehicle;
//
//public class DBHelper extends SQLiteOpenHelper {
//
//    public static final String DATABASE_NAME = "mobileAppProject.db";
//    public static final String VEHICLES_TABLE_NAME = "vehicles";
//    public static final String VEHICLES_COLUMN_ID = "id";
//    public static final String VEHICLES_COLUMN_MANUFACTURER = "vehicleManufacturer";
//    public static final String VEHICLES_COLUMN_NAME = "vehicleName";
//    public static final String VEHICLES_COLUMN_CATEGORY = "vehicleCategory";
//    public static final String VEHICLES_COLUMN_REGISTRATION = "registration";
//    public static final String VEHICLES_COLUMN_REGISTRATION_DATE = "registrationDate";
//    public static final String VEHICLE_COLUMN_OWNER = "owner";
//    private HashMap hp;
//
//    public DBHelper(Context context) {
//        super(context, DATABASE_NAME , null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // TODO Auto-generated method stub
//        db.execSQL(
//                "create table vehicles " +
//                        "(id integer primary key,vehicleManufacturer text, vehicleName text,vehicleCategory text,registration text, registrationDate text,owner text)"
//        );
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // TODO Auto-generated method stub
//        db.execSQL("DROP TABLE IF EXISTS contacts");
//        onCreate(db);
//    }
//
//    public boolean insertVehicle (String vehicleManufacturer, String vehicleName, String vehicleCategory, String registration,String registrationDate,String owner) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("vehicleManufacturer", vehicleManufacturer);
//        contentValues.put("vehicleName", vehicleName);
//        contentValues.put("vehicleCategory", vehicleCategory);
//        contentValues.put("registration", registration);
//        contentValues.put("registrationDate", registrationDate);
//        contentValues.put("owner", owner);
//        db.insert("vehicles", null, contentValues);
//        return true;
//    }
//
//    public Cursor getVehicleByRegistration(int registration) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from vehicles where registration="+registration+"", null );
//        return res;
//    }
//
//    public boolean updateVehicle (Integer id, String vehicleManufacturer, String vehicleName, String vehicleCategory, String registration,String registrationDate,String owner) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("vehicleManufacturer", vehicleManufacturer);
//        contentValues.put("vehicleName", vehicleName);
//        contentValues.put("vehicleCategory", vehicleCategory);
//        contentValues.put("registration", registration);
//        contentValues.put("registrationDate", registrationDate);
//        contentValues.put("owner", owner);
//        db.update("vehicles", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
//        return true;
//    }
//
//    public Cursor getVehicleById(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
//        return res;
//    }
//
//    public Integer deleteVehicle (Integer id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete("vehicles",
//                "id = ? ",
//                new String[] { Integer.toString(id) });
//    }
//
//    public ArrayList<Vehicle> getAllVehicles() {
//        ArrayList<Vehicle> array_list = new ArrayList<Vehicle>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from vehicles", null );
//        res.moveToFirst();
//
//        return array_list;
//    }
//}