package com.example.vechile;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.vechile.model.Vehicle;
import com.itextpdf.text.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;
    private static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "Vehicle_db";

    // User table name
    private static final String VEHICLE_DATA = "vehicle_data";

    // User Table Columns names
    private static final String COLUMN_VEHICLE_ID = "vehicle_id";
    private static final String COLUMN_VEHICLE_TYPE = "vehicle_type";
    private static final String COLUMN_VEHICLE_MILEAGE = "vehicle_mileage";
    private static final String COLUMN_VEHICLE_MODEL = "vehicle_model";
    private static final String COLUMN_VEHICLE_COLOR = "vehicle_color";
    private static final String COLUMN_FLEET_NO = "fleet_no";
    private static final String COLUMN_REGO_NO = "rego_no";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_START_KM = "start_km";
    private static final String COLUMN_END_KM = "end_km";
    private static final String COLUMN_VEHICLE_COMPANY = "vehicle_company";
    private static final String COLUMN_VEHICLE_LOCATION = "vehicle_location";
    private static final String COLUMN_VEHICLE_RESERVATION = "vehicle_reservation";
    private static final String COLUMN_VEHICLE_POINT_CONTACT = "point_of_contact";
    private static final String COLUMN_PHONE = "phone_number";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PREFERRED_STATUS = "prefered_status";
    private static final String COLUMN_ACC = "acc";
    private static final String COLUMN_OTHER = "other";
    private static final String COLUMN_GENERAL_CONDITION = "GeneralCondition";
    private static final String COLUMN_FRONT_SEAT = "FrontSeat";
    private static final String COLUMN_REAR_SEAT = "RearSeat";
    private static final String COLUMN_CLIMATE_CONTROL = "ClimateControl";
    private static final String COLUMN_INTERIOR_LIGHT = "InteriorLight";
    private static final String COLUMN_CRITICAL_SYSTEM = "CriticalSystem";
    private static final String COLUMN_MECHANICAL_INSPEC = "MechanicalInspec";
    private static final String COLUMN_TYREPRESSURE = "TyrePressure";
    private static final String COLUMN_ALL_DOOR_LOCK = "all_door_lock_check";
    private static final String COLUMN_REAR_DOOR_CHILD_LOCK = "rear_door_child_lock_check";
    private static final String COLUMN_ALL_WINDOW_SWITCH = "all_window_switch_check";
    private static final String COLUMN_V12_AUXILLARY = "v12_auxilary_check";
    private static final String COLUMN_REAR = "rear_check";
    private static final String COLUMN_WINDSCREEN = "windscreen_check";
    private static final String COLUMN_WINDSCREEN_VALUE = "windscreen_value";
    private static final String COLUMN_FR = "FR";
    private static final String COLUMN_FL = "FL";
    private static final String COLUMN_RR = "RR";
    private static final String COLUMN_RL = "RL";
    private static final String COLUMN_LEFT_FRONT = "left_front_vcalue";
    private static final String COLUMN_RIGHT_FRONT = "right_front_value";
    private static final String COLUMN_LEFT_REAR = "left_rear_value";
    private static final String COLUMN_RIGHT_REAR = "right_rear_value";
    private static final String COLUMN_SAFETY_EQUIPMENT = "safety_equipment";
    private static final String COLUMN_CHECK_BY = "check_by";
    private static final String COLUMN_RECEIVED_BY = "recevied_by";
    private static final String COLUMN_MECHANICS_SIGNATURE = "mechanics_signature";
    private static final String COLUMN_RECEIVER_SIGNATURE = "receiver_signature";







    // create table sql query
    private String CREATE_VEHICLE_TABLE = "CREATE TABLE " + VEHICLE_DATA + "("
            + COLUMN_VEHICLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_VEHICLE_TYPE + " TEXT,"
            + COLUMN_VEHICLE_MILEAGE + " TEXT," + COLUMN_VEHICLE_MODEL + " TEXT," + COLUMN_VEHICLE_COLOR + " TEXT," + COLUMN_FLEET_NO + " TEXT, "
            + COLUMN_REGO_NO + " TEXT, " +  COLUMN_START_KM + " TEXT, " + COLUMN_END_KM + " TEXT, "
            + COLUMN_DATE + " TEXT, " + COLUMN_VEHICLE_COMPANY + " TEXT, " + COLUMN_VEHICLE_LOCATION + " TEXT, " + COLUMN_VEHICLE_RESERVATION + " TEXT, "
            + COLUMN_VEHICLE_POINT_CONTACT + " TEXT, " + COLUMN_PHONE + " TEXT, " + COLUMN_EMAIL + " TEXT, " + COLUMN_PREFERRED_STATUS + " TEXT, " + COLUMN_ACC +" TEXT, " + COLUMN_OTHER +" TEXT, "
            + COLUMN_GENERAL_CONDITION + " TEXT, " + COLUMN_FRONT_SEAT + " TEXT, " + COLUMN_REAR_SEAT + " TEXT, " + COLUMN_CLIMATE_CONTROL + " TEXT, "
            + COLUMN_INTERIOR_LIGHT + " TEXT, " + COLUMN_CRITICAL_SYSTEM + " TEXT, " + COLUMN_MECHANICAL_INSPEC + " TEXT, " + COLUMN_TYREPRESSURE + " TEXT, "
            + COLUMN_ALL_DOOR_LOCK + " TEXT, " + COLUMN_REAR_DOOR_CHILD_LOCK + " TEXT, " + COLUMN_ALL_WINDOW_SWITCH + " TEXT, " + COLUMN_V12_AUXILLARY + " TEXT, "
            + COLUMN_REAR + " TEXT, " + COLUMN_WINDSCREEN + " TEXT, " + COLUMN_WINDSCREEN_VALUE + " TEXT, " + COLUMN_FR + " TEXT, " + COLUMN_FL + " TEXT,"
            + COLUMN_RR + " TEXT, " + COLUMN_RL + " TEXT, " + COLUMN_LEFT_FRONT + " TEXT, " + COLUMN_RIGHT_FRONT + " TEXT, " + COLUMN_LEFT_REAR + " TEXT,"
            + COLUMN_RIGHT_REAR + " TEXT, " + COLUMN_SAFETY_EQUIPMENT + " TEXT, " + COLUMN_CHECK_BY + " TEXT, " + COLUMN_RECEIVED_BY + " TEXT, "
            + COLUMN_MECHANICS_SIGNATURE + " BLOB, " + COLUMN_RECEIVER_SIGNATURE + " BLOB " + ")";

    public DatabaseHelper(@Nullable Context contex) {
        super(contex, DATABASE_NAME,null, DATABASE_VERSION);
        this.context = contex;
    }

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + VEHICLE_DATA;
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_VEHICLE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);

    }

    public void addUser(Vehicle vehicle) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_VEHICLE_TYPE, vehicle.getVehicle_type());
        values.put(COLUMN_VEHICLE_MILEAGE, vehicle.getMileage());
        values.put(COLUMN_VEHICLE_MODEL, String.valueOf(vehicle.getVehicle_model()));
        values.put(COLUMN_VEHICLE_COLOR, vehicle.getColor());
        values.put(COLUMN_FLEET_NO, vehicle.getFleet_no());
        values.put(COLUMN_REGO_NO, vehicle.getRego_no());
        values.put(COLUMN_START_KM, vehicle.getStart_km());
        values.put(COLUMN_END_KM, vehicle.getEnd_km());
        values.put(COLUMN_DATE, vehicle.getDate());
        values.put(COLUMN_VEHICLE_COMPANY, vehicle.getCompany());
        values.put(COLUMN_VEHICLE_LOCATION, vehicle.getLocation());
        values.put(COLUMN_VEHICLE_RESERVATION, vehicle.getReservation());
        values.put(COLUMN_VEHICLE_POINT_CONTACT, vehicle.getPoint_contact());
        values.put(COLUMN_PHONE, vehicle.getPhone());
        values.put(COLUMN_EMAIL, vehicle.getEmail());
        values.put(COLUMN_PREFERRED_STATUS, vehicle.getPrefered_status());
        values.put(COLUMN_ACC, vehicle.getAcc());
        values.put(COLUMN_OTHER, vehicle.getOther());
        values.put(COLUMN_GENERAL_CONDITION, String.valueOf(vehicle.getGeneralCondition()));
        values.put(COLUMN_FRONT_SEAT, String.valueOf(vehicle.getFrontSea()));
        values.put(COLUMN_REAR_SEAT, String.valueOf(vehicle.getRearSeat()));
        values.put(COLUMN_CLIMATE_CONTROL, String.valueOf(vehicle.getClimateControl()));
        values.put(COLUMN_INTERIOR_LIGHT, String.valueOf(vehicle.getInteriorLight()));
        values.put(COLUMN_CRITICAL_SYSTEM, String.valueOf(vehicle.getCriticalSystem()));
        values.put(COLUMN_MECHANICAL_INSPEC, String.valueOf(vehicle.getMrchanicalInspec()));
        values.put(COLUMN_TYREPRESSURE, String.valueOf(vehicle.getTyrePressure()));
        values.put(COLUMN_ALL_DOOR_LOCK, vehicle.getAll_door_lock());
        values.put(COLUMN_REAR_DOOR_CHILD_LOCK, vehicle.getRear_child_lock());
        values.put(COLUMN_ALL_WINDOW_SWITCH, vehicle.getAll_window_switch());
        values.put(COLUMN_V12_AUXILLARY, vehicle.getV12_auxlliary());
        values.put(COLUMN_REAR, vehicle.getRear());
        values.put(COLUMN_WINDSCREEN, vehicle.getWindscreen());
        values.put(COLUMN_WINDSCREEN_VALUE, vehicle.getWind_screen_value());
        values.put(COLUMN_FR, vehicle.getFR());
        values.put(COLUMN_FL, vehicle.getFL());
        values.put(COLUMN_RR, vehicle.getRR());
        values.put(COLUMN_RL, vehicle.getRL());
        values.put(COLUMN_LEFT_FRONT, vehicle.getLeft_front());
        values.put(COLUMN_RIGHT_FRONT, vehicle.getRight_front());
        values.put(COLUMN_LEFT_REAR, vehicle.getLeft_rear());
        values.put(COLUMN_RIGHT_REAR, vehicle.getRight_rear());
        values.put(COLUMN_SAFETY_EQUIPMENT, String.valueOf(vehicle.getSafety_equipment()));
        values.put(COLUMN_CHECK_BY, vehicle.getCheck_by());
        values.put(COLUMN_RECEIVED_BY, vehicle.getReceived_by());
        values.put(COLUMN_MECHANICS_SIGNATURE, vehicle.getMechanics_signature());
        values.put(COLUMN_RECEIVER_SIGNATURE, vehicle.getReceiver_signature());



        // Inserting Row
        db.insert(VEHICLE_DATA, null, values);
        db.close();
    }

    public void backup(String outFileName) {
        Document doc = new Document();

        //database path
        final String inFileName = context.getDatabasePath(DATABASE_NAME).toString();

        try {

            File dbFile = new File(inFileName);
            FileInputStream fis = new FileInputStream(dbFile);

            // Open the empty db as the output stream
            OutputStream output = new FileOutputStream(outFileName);

            // Transfer bytes from the input file to the output file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            // Close the streams
            output.flush();
            output.close();
            fis.close();

            Toast.makeText(context, "Backup Completed", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Unable to backup database. Retry", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
