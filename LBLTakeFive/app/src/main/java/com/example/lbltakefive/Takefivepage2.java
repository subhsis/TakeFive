package com.example.lbltakefive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lbltakefive.Databases.DatabaseHelper;
import com.example.lbltakefive.Databases.Employee;
import com.example.lbltakefive.Databases.LocalbackUp;
import com.example.lbltakefive.activity.Succefulactivity;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.OpenFileActivityOptions;

import java.io.File;

public class Takefivepage2 extends AppCompatActivity {

    public static final int REQUEST_CODE_SIGN_IN = 0;
    public static final int REQUEST_CODE_OPENING = 1;
    public static final int REQUEST_CODE_CREATION = 2;
    public static final int REQUEST_CODE_PERMISSIONS = 2;
    public static final String TAG = "Google drive activity";
    private  boolean isBackup = true;


    RadioGroup fit_group,competency_group,competency_group2,correct_tools_group,tools_condition_group,tools_checked_group,
            permit_radio_group,jsa_swp_group,supervisor_group;
    RadioButton radioButton;

    EditText risk_hazards,control_measure;
    TextView employee_name_in;
    Button save_data;
    DatabaseHelper databaseHelper;
    Employee employee;
    String employee_name,employee_no,date,job_location,job_description;
    RadioButton rb,rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8;
    LocalbackUp localbackUp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takefivepage2);

        save_data = findViewById(R.id.save_data);

        fit_group = findViewById(R.id.fit_group);
        competency_group = findViewById(R.id.competency_group);
        competency_group2 = findViewById(R.id.compentency_group2);
        correct_tools_group = findViewById(R.id.correct_tools_group);
        tools_condition_group = findViewById(R.id.tools_condition_group);
        tools_checked_group = findViewById(R.id.tools_checked_group);
        permit_radio_group = findViewById(R.id.permit_radio);
        jsa_swp_group = findViewById(R.id.jsa_swp_group);
        supervisor_group = findViewById(R.id.supervisor_group);
        databaseHelper = new DatabaseHelper(this);
        employee = new Employee();
        localbackUp = new LocalbackUp(this);




        risk_hazards = findViewById(R.id.enter_hazards);
        control_measure = findViewById(R.id.enter_control);

        employee_name_in = findViewById(R.id.employee_name_in);

         employee_name = getIntent().getExtras().getString("name");
         employee_no = getIntent().getExtras().getString("employee_name");
         date = getIntent().getExtras().getString("date");
         job_location = getIntent().getExtras().getString("job_location");
         job_description = getIntent().getExtras().getString("job_description");

        employee_name_in.setText(employee_name);

        fit_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                 rb =  group.findViewById(checkedId);

            }
        });
        competency_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                 rb1 =  group.findViewById(checkedId);

            }
        });
        competency_group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                 rb2 =  group.findViewById(checkedId);

            }
        });
        correct_tools_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                 rb3 =  group.findViewById(checkedId);

            }
        });
        tools_condition_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                 rb4 =  group.findViewById(checkedId);

            }
        });
        tools_checked_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                 rb5 =  group.findViewById(checkedId);

            }
        });
        permit_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                 rb6 =  group.findViewById(checkedId);

            }
        });
        jsa_swp_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rb7 =  group.findViewById(checkedId);

            }
        });
        supervisor_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                 rb8 = group.findViewById(checkedId);

            }
        });

        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    postdata();
                    backupdata();
                    Intent intent = new Intent(Takefivepage2.this, Succefulactivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        });




    }

    private void backupdata() {
        String outFileName = Environment.getExternalStorageDirectory() + File.separator + getResources().getString(R.string.app_name) + File.separator;
        localbackUp.performBackup(databaseHelper, outFileName);
    }

    private boolean validate() {
        boolean valid = true;
        if(fit_group.getCheckedRadioButtonId()== -1){
            Toast.makeText(this,"please select work fitness",Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (competency_group.getCheckedRadioButtonId()== -1){
            Toast.makeText(this,"please select option are you trained for this work",Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (competency_group2.getCheckedRadioButtonId() == -1){
            Toast.makeText(this,"please select option are you authrized for this work",Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (correct_tools_group.getCheckedRadioButtonId()== -1){
            Toast.makeText(this,"please select option from tools correct bar",Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (tools_condition_group.getCheckedRadioButtonId()== -1){
            Toast.makeText(this,"please select an option from tools condition",Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (tools_checked_group.getCheckedRadioButtonId() == -1){
            Toast.makeText(this," please select an option from tools checked list",Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (permit_radio_group.getCheckedRadioButtonId() == -1){
            Toast.makeText(this,"please select an option from jsa wp permit", Toast.LENGTH_SHORT).show();
            valid = false;

        }else if (supervisor_group.getCheckedRadioButtonId() == -1){
            Toast.makeText(this,"please select a option from supervisor apporved",Toast.LENGTH_SHORT).show();
            valid = false;

        }else if (jsa_swp_group.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "please select an option from jsa swp workplace permit", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }

    private void postdata() {
        employee.setEmployee_name(employee_name);
        employee.setEmployee_no(employee_no);
        employee.setDate( date);
        employee.setJob_location(job_location );
        employee.setJob_description( getIntent().getExtras().getString("job_description"));
        employee.setWork_fitness((String) rb.getText());
        employee.setCompetency_trained((String) rb1.getText());
        employee.setCompetency_auth((String) rb2.getText());
        employee.setCorrect_tools((String) rb3.getText());
        employee.setTools_condition((String) rb4.getText());
        employee.setTools_safety((String) rb5.getText());
        employee.setRisk_hazards(risk_hazards.getText().toString());
        employee.setControl_measure(control_measure.getText().toString());
        employee.setJsa_wp_permit((String) rb6.getText());
        employee.setWorkplace_permit((String) rb7.getText());
        employee.setSupervisor_approved((String) rb8.getText());
        employee.setImage(getIntent().getExtras().getByteArray("profileimage"));

       databaseHelper.addUser(employee);
    }
}
