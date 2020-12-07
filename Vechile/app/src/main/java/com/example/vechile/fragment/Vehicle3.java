package com.example.vechile.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.vechile.DatabaseHelper;
import com.example.vechile.Localbackup;
import com.example.vechile.R;
import com.example.vechile.model.Vehicle;
import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

public class Vehicle3 extends Fragment {
    private CheckBox first_aid_kit,buggy_whip,revolving_light,fire_extinguiser,wheel_chocks,carpets;
    private EditText check_by,recevied_by;
    private String first_aid_kit_check,buggy_whip_check,revolving_light_check,fire_extinguiser_check,wheel_chocks_check,carpets_check;
    private Button save,clear_sign,save_sign,clear_sign1,save_sign1;
    private ArrayList<String> Safety_equipment = new ArrayList<>();
    private Vehicle vehicle;
    private DatabaseHelper databaseHelper;
    private SignaturePad mechanics_image_sign,receiver_image_sign;
    private Localbackup localbackup = null;
    byte[] mechanics_signature;
    byte[] receiver_signature;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.vehicle3,null,false);
         save = view.findViewById(R.id.save);

         first_aid_kit = view.findViewById(R.id.first_aid_kit);
         buggy_whip = view.findViewById(R.id.buggy_whip);
         revolving_light = view.findViewById(R.id.revolving_lights);
         fire_extinguiser = view.findViewById(R.id.fire_extinguisher);
         wheel_chocks = view.findViewById(R.id.wheel_chocks);
         carpets = view.findViewById(R.id.carpets);
         mechanics_image_sign = view.findViewById(R.id.mecha_image_sign);
         receiver_image_sign = view.findViewById(R.id.receive_image_sign);

         clear_sign = view.findViewById(R.id.clear_sign);
         clear_sign1 = view.findViewById(R.id.clear_sign1);
         save_sign = view.findViewById(R.id.save_sign);
         save_sign1 = view.findViewById(R.id.save_sign1);


         check_by = view.findViewById(R.id.item_check_by);
         recevied_by = view.findViewById(R.id.recevied_by);


         vehicle = new Vehicle();
         databaseHelper = new DatabaseHelper(getContext());
         localbackup = new Localbackup(getContext());

         mechanics_image_sign.setOnSignedListener(new SignaturePad.OnSignedListener() {
             @Override
             public void onStartSigning() {

             }

             @Override
             public void onSigned() {
                 clear_sign.setEnabled(true);
                 save_sign.setEnabled(true);

             }

             @Override
             public void onClear() {
                 clear_sign.setEnabled(false);
                 save_sign.setEnabled(false);


             }
         });

         receiver_image_sign.setOnSignedListener(new SignaturePad.OnSignedListener() {
             @Override
             public void onStartSigning() {

             }

             @Override
             public void onSigned() {
                 clear_sign1.setEnabled(true);
                 save_sign1.setEnabled(true);

             }

             @Override
             public void onClear() {
                 clear_sign1.setEnabled(false);
                 save_sign1.setEnabled(false);

             }
         });

         clear_sign.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 mechanics_image_sign.clear();
             }
         });
         clear_sign1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 receiver_image_sign.clear();
             }
         });
         save_sign.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Bitmap mechanics_sign = mechanics_image_sign.getSignatureBitmap();
                 mechanics_signature = getBitmapAsarray(mechanics_sign);
                 clear_sign.setVisibility(View.GONE);
             }
         });
         save_sign1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Bitmap receiver_sign = receiver_image_sign.getSignatureBitmap();
                 receiver_signature = getreceverBitmapAsarray(receiver_sign);
                 clear_sign1.setVisibility(View.GONE);
             }
         });




         save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (first_aid_kit.isChecked()){
                     first_aid_kit_check = first_aid_kit.getText().toString();
                     Safety_equipment.add(first_aid_kit_check);
                 }
                 if (buggy_whip.isChecked()){
                     buggy_whip_check = buggy_whip.getText().toString();
                     Safety_equipment.add(buggy_whip_check);
                 }
                 if (revolving_light.isChecked()){
                     revolving_light_check = revolving_light.getText().toString();
                     Safety_equipment.add(revolving_light_check);
                 }
                 if (fire_extinguiser.isChecked()){
                     fire_extinguiser_check = fire_extinguiser.getText().toString();
                     Safety_equipment.add(fire_extinguiser_check);
                 }
                 if (wheel_chocks.isChecked()){
                     wheel_chocks_check = wheel_chocks.getText().toString();
                     Safety_equipment.add(wheel_chocks_check);
                 }
                 if (carpets.isChecked()){
                     carpets_check = carpets.getText().toString();
                     Safety_equipment.add(carpets_check);
                 }
                 postData();
                 dataBackup();

                 replaceFragment(new Successfragment());

             }
         });
         return view;
    }

    private byte[] getreceverBitmapAsarray(Bitmap receiver_sign) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        receiver_sign.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();

    }

    private byte[] getBitmapAsarray(Bitmap mechanics_sign) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        mechanics_sign.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    private void dataBackup() {
        String outFileName = Environment.getExternalStorageDirectory() + File.separator + getResources().getString(R.string.app_name) + File.separator;
        localbackup.performBackup(databaseHelper, outFileName);
    }

    private void postData() {
        vehicle.setVehicle_type(getArguments().getString("vehicle_type_1"));
        vehicle.setVehicle_model(getArguments().getString("vehicle_model_1"));
        vehicle.setMileage(getArguments().getString("millege_1"));
        vehicle.setColor(getArguments().getString("color_1"));
        vehicle.setFleet_no(getArguments().getString("fleet_no_1"));
        vehicle.setRego_no(getArguments().getString("reg_no_1"));
        vehicle.setDate(getArguments().getString("date_1"));
        vehicle.setStart_km(getArguments().getString("start_km_1"));
        vehicle.setEnd_km(getArguments().getString("end_km_1"));
        vehicle.setCompany(getArguments().getString("company_name_1"));
        vehicle.setLocation(getArguments().getString("location_1"));
        vehicle.setReservation(getArguments().getString("reservation_no_1"));
        vehicle.setPoint_contact(getArguments().getString("point_of_contact_1"));
        vehicle.setPhone(getArguments().getString("phone_no_1"));
        vehicle.setEmail(getArguments().getString("email_1"));
        vehicle.setPrefered_status(getArguments().getString("prefered_status_1"));
        vehicle.setAcc(getArguments().getString("acc_1"));

        vehicle.setGeneralCondition(getArguments().getString("GeneralCondition"));
        vehicle.setFrontSea(getArguments().getString("FrontSeat"));
        vehicle.setRearSeat(getArguments().getString("RearSeat"));
        vehicle.setClimateControl(getArguments().getString("ClimateControl"));
        vehicle.setInteriorLight(getArguments().getString("InteriorLight"));
        vehicle.setCriticalSystem(getArguments().getString("CriticalSystem"));
        vehicle.setMrchanicalInspec(getArguments().getString("MrchanicalInspec"));
        vehicle.setTyrePressure(getArguments().getString("TyrePressure"));
        vehicle.setAll_door_lock(getArguments().getString("all_door_lock_check"));
        vehicle.setRear_child_lock(getArguments().getString("rear_door_child_lock_check"));
        vehicle.setAll_window_switch(getArguments().getString("all_window_switch_check"));
        vehicle.setV12_auxlliary(getArguments().getString("v12_auxilary_check"));
        vehicle.setRear(getArguments().getString("rear_check"));
        vehicle.setWindscreen(getArguments().getString("windscreen_check"));
        vehicle.setFR(getArguments().getString("FR"));
        vehicle.setRR(getArguments().getString("RR"));
        vehicle.setRL(getArguments().getString("RL"));
        vehicle.setFL(getArguments().getString("FL"));
        vehicle.setLeft_front(getArguments().getString("left_front_vcalue"));
        vehicle.setRight_front(getArguments().getString("right_front_value"));
        vehicle.setLeft_rear(getArguments().getString("left_rear_value"));
        vehicle.setRight_rear(getArguments().getString("right_rear_value"));
        vehicle.setWind_screen_value(getArguments().getString("windscreen_value"));

        vehicle.setSafety_equipment(String.valueOf(Safety_equipment));
        vehicle.setCheck_by(check_by.getText().toString());
        vehicle.setReceived_by(recevied_by.getText().toString());
        vehicle.setMechanics_signature(mechanics_signature);
        vehicle.setReceiver_signature(receiver_signature);


        databaseHelper.addUser(vehicle);

    }

    private void replaceFragment( Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framelayout,fragment).commit();
    }
}
