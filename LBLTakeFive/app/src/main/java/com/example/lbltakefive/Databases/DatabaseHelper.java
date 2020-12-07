package com.example.lbltakefive.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.itextpdf.text.Document;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;



public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    private static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "Employee_db";

    // User table name
    private static final String EMPLOYEE_USER = "Employee";

    // User Table Columns names
    private static final String COLUMN_EMPLOYEE_ID = "employee_id";
    private static final String COLUMN_EMPLOYEE_NAME = "employee_name";
    private static final String COLUMN_EMPLOYEE_NO = "employee_no";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_WORK_FIT = "work_fit";
    private static final String COLUMN_JOB_LOCATION = "job_location";
    private static final String COLUMN_JOB_DESCRIPTION = "job_description";
    private static final String COLUMN_COMPETENCY_TRAINED = "competency_trained";
    private static final String COLUMN_COMPETENCY_AUTH = "competency_auth";
    private static final String COLUMN_CORRECT_TOOLS = "correct_tools";
    private static final String COLUMN_TOOLS_CONDITION = "tools_condition";
    private static final String COLUMN_TOOLS_SAFETY = "tools_safety";
    private static final String COLUMN_RISK_HAZARDS = "risk_hazards";
    private static final String COLUMN_CONTROL_MEASURE = "control_measure";
    private static final String COLUMN_JSA_WP_WORKPERMIT = "jsa_wp_workpermit";
    private static final String COLUMN_WORKPLACE_PERMIT = "workplace_permit";
    private static final String COLUMN_SUPERVISOR_APPROVED = "supervisor_approved";
   // private static final String COLUMN_EMPLOYEE_IMAGE = "profile_image";


    // create table sql query
    private String CREATE_EMPLOYEE_TABLE = "CREATE TABLE " + EMPLOYEE_USER + "("
            + COLUMN_EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_EMPLOYEE_NAME + " TEXT,"
            + COLUMN_EMPLOYEE_NO + " TEXT," + COLUMN_DATE + " TEXT," + COLUMN_WORK_FIT + " TEXT," + COLUMN_JOB_LOCATION + " TEXT, "
            + COLUMN_JOB_DESCRIPTION + " TEXT, " +  COLUMN_COMPETENCY_TRAINED + " TEXT, " + COLUMN_COMPETENCY_AUTH + " TEXT, "
            + COLUMN_CORRECT_TOOLS + " TEXT, " + COLUMN_TOOLS_CONDITION + " TEXT, " + COLUMN_TOOLS_SAFETY + " TEXT, " + COLUMN_RISK_HAZARDS + " TEXT, "
            + COLUMN_CONTROL_MEASURE + " TEXT, " + COLUMN_JSA_WP_WORKPERMIT + " TEXT, " + COLUMN_WORKPLACE_PERMIT + " TEXT, " + COLUMN_SUPERVISOR_APPROVED + " TEXT " + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + EMPLOYEE_USER;

   ;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(CREATE_EMPLOYEE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);

    }

    public void addUser(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMPLOYEE_NAME, employee.getEmployee_name());
        values.put(COLUMN_EMPLOYEE_NO, employee.getEmployee_no());
        values.put(COLUMN_DATE, employee.getDate());
        values.put(COLUMN_WORK_FIT, employee.getWork_fitness());
        values.put(COLUMN_JOB_LOCATION, employee.getJob_location());
        values.put(COLUMN_JOB_DESCRIPTION, employee.getJob_description());
        values.put(COLUMN_COMPETENCY_TRAINED, employee.getCompetency_trained());
        values.put(COLUMN_COMPETENCY_AUTH, employee.getCompetency_auth());
        values.put(COLUMN_CORRECT_TOOLS, employee.getCorrect_tools());
        values.put(COLUMN_TOOLS_CONDITION, employee.getTools_condition());
        values.put(COLUMN_TOOLS_SAFETY, employee.getTools_safety());
        values.put(COLUMN_RISK_HAZARDS, employee.getRisk_hazards());
        values.put(COLUMN_CONTROL_MEASURE, employee.getControl_measure());
        values.put(COLUMN_JSA_WP_WORKPERMIT, employee.getJsa_wp_permit());
        values.put(COLUMN_WORKPLACE_PERMIT, employee.getWorkplace_permit());
        values.put(COLUMN_SUPERVISOR_APPROVED, employee.getSupervisor_approved());
       // values.put(COLUMN_EMPLOYEE_IMAGE,employee.getImage());


        // Inserting Row
        db.insert(EMPLOYEE_USER, null, values);
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
