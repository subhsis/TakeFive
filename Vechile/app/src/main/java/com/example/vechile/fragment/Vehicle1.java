package com.example.vechile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.vechile.R;

import java.util.ArrayList;
import java.util.List;

public class Vehicle1 extends Fragment {
    private Button next;
    private CheckBox double_cab,single_cab,land_cruiser_5,side_lifter,hine_flatbed,land_cruiser_3,bus_25_15,other;
    private EditText vechile_type, millege, color, fleet_no, reg_no, date, start_km, end_km, company, location, reservation, point_of_contact, phone,
    email, prefered_status, acc;
    private String double_cab_check,single_cab_check,land_cruiser_5_check,side_lifter_check,hino_flatbed_check,
    land_cruiser_3_check,bus_25_15_check,other_check;
    List<String> items = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vehile1,null,false);

        next = view.findViewById(R.id.next);
        vechile_type = view.findViewById(R.id.vehiclke_type);
        millege = view.findViewById(R.id.millege);
        color = view.findViewById(R.id.color);
        fleet_no = view.findViewById(R.id.fleet_no);
        reg_no = view.findViewById(R.id.reg_no);
        date = view.findViewById(R.id.date);
        start_km = view.findViewById(R.id.start_km);
        end_km = view.findViewById(R.id.end_km);
        company = view.findViewById(R.id.company);
        location = view.findViewById(R.id.location);
        reservation = view.findViewById(R.id.reservation);
        point_of_contact = view.findViewById(R.id.points_contact);
        phone = view.findViewById(R.id.phone);
        email = view.findViewById(R.id.email);
        prefered_status = view.findViewById(R.id.preferred_status);
        acc = view.findViewById(R.id.acc);

        double_cab = view.findViewById(R.id.double_cab);
        single_cab = view.findViewById(R.id.single_cab);
        land_cruiser_5 = view.findViewById(R.id.land_cruser_5);
        side_lifter = view.findViewById(R.id.side_lifter);
        hine_flatbed  = view.findViewById(R.id.hino_flatbed);
        land_cruiser_3 = view.findViewById(R.id.land_cruiser_3);
        bus_25_15 = view.findViewById(R.id.bus_25_15);
        other = view.findViewById(R.id.other);






        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (double_cab.isChecked()){
                    double_cab_check = double_cab.getText().toString();
                    items.add(double_cab_check);
                }
                if (single_cab.isChecked()){
                    single_cab_check = single_cab.getText().toString();
                    items.add(single_cab_check);
                }
                if (land_cruiser_5.isChecked()){
                    land_cruiser_5_check = land_cruiser_5.getText().toString();
                    items.add(land_cruiser_5_check);
                }
                if (side_lifter.isChecked()){
                    side_lifter_check = side_lifter.getText().toString();
                    items.add(side_lifter_check);
                }
                if (hine_flatbed.isChecked()){
                    hino_flatbed_check = hine_flatbed.getText().toString();
                    items.add(hino_flatbed_check);
                }
                if (land_cruiser_3.isChecked()){
                    land_cruiser_3_check = land_cruiser_3.getText().toString();
                    items.add(land_cruiser_3_check);
                }
                if (bus_25_15.isChecked()){
                    bus_25_15_check = bus_25_15.getText().toString();
                    items.add(bus_25_15_check);
                }
                if (other.isChecked()){
                    other_check = other.getText().toString();
                    items.add(other_check);
                }



                if (validate()) {
                    Vehicle2 vehicle2 = new Vehicle2();
                    Bundle args = new Bundle();
                    args.putString("vehicle_type", vechile_type.getText().toString());
                    args.putString("vehicle_model", String.valueOf(items));
                    args.putString("millege", millege.getText().toString());
                    args.putString("color", color.getText().toString());
                    args.putString("fleet_no", fleet_no.getText().toString());
                    args.putString("reg_no", reg_no.getText().toString());
                    args.putString("date", date.getText().toString());
                    args.putString("start_km", start_km.getText().toString());
                    args.putString("end_km", end_km.getText().toString());
                    args.putString("company_name", company.getText().toString());
                    args.putString("location", location.getText().toString());
                    args.putString("reservation_no", reservation.getText().toString());
                    args.putString("point_of_contact", point_of_contact.getText().toString());
                    args.putString("phone_no",phone.getText().toString());
                    args.putString("email", email.getText().toString());
                    args.putString("prefered_status", prefered_status.getText().toString());
                    args.putString("acc", acc.getText().toString());
                    vehicle2.setArguments(args);
                    replaceFragment(vehicle2);
                }
            }
        });
        return  view;
    }

    private boolean validate() {
        boolean valid = true;
        String vehicle_name = vechile_type.getText().toString().trim();
        String vehicle_reg_no = reg_no.getText().toString().trim();
        String vehicle_millege =millege.getText().toString().trim();
        String vehicle_color = color.getText().toString().trim();
        String vehicle_fleet_no = fleet_no.getText().toString().trim();
        String vehicle_date = date.getText().toString().trim();
        String vehicle_start_km = start_km.getText().toString().trim();
        String vehicle_end_km = end_km.getText().toString().trim();
        String vehicle_company = company.getText().toString().trim();
        String vehicle_location = location.getText().toString().trim();
        String vehicle_reservation = reservation.getText().toString().trim();
        String vehicle_point_conatct = point_of_contact.getText().toString().trim();
        String vehicle_phone = phone.getText().toString().trim();
        String vehicle_email = email.getText().toString().trim();
        String vehicle_prefered_status = prefered_status.getText().toString().trim();
        String vehicle_acc = acc.getText().toString().trim();

        if (vehicle_name.isEmpty()){
            Toast.makeText(getContext(),"vehicle name must be fill",Toast.LENGTH_LONG).show();
            valid= false;
        }else if(vehicle_millege.isEmpty()){
            Toast.makeText(getContext(),"Vehicle millege must be fill",Toast.LENGTH_LONG).show();
            valid= false;
        }else if (vehicle_color.isEmpty()){
            Toast.makeText(getContext(),"Vehicle color must be fill",Toast.LENGTH_LONG).show();
            valid= false;
        }else if (vehicle_fleet_no.isEmpty()){
            Toast.makeText(getContext(),"Vehicle fleet no must be fill",Toast.LENGTH_LONG).show();
            valid = false;
        }else if (vehicle_date.isEmpty()){
            Toast.makeText(getContext(),"Please enter the Date",Toast.LENGTH_LONG).show();
            valid = false;
        }else if (vehicle_start_km.isEmpty()){
            Toast.makeText(getContext(),"Please enter start km",Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (vehicle_end_km.isEmpty()){
            Toast.makeText(getContext(),"please enter end km",Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (vehicle_company.isEmpty()){
            Toast.makeText(getContext(),"Please enter company name",Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (vehicle_location.isEmpty()){
            Toast.makeText(getContext(),"Please enter vehicle locatio", Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (vehicle_reservation.isEmpty()){
            Toast.makeText(getContext(),"Please fill the reservation field",Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (vehicle_point_conatct.isEmpty()){
            Toast.makeText(getContext(),"Please fill the point of contact",Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (vehicle_phone.isEmpty()){
            Toast.makeText(getContext(),"Enter the phone number",Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (vehicle_email.isEmpty()){
            Toast.makeText(getContext(),"Please enter the email",Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (vehicle_prefered_status.isEmpty()){
            Toast.makeText(getContext(),"Please enter prefered_status",Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (vehicle_acc.isEmpty()){
            Toast.makeText(getContext(),"Please enter acc information",Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (vehicle_reg_no.isEmpty()){
            Toast.makeText(getContext(),"Please enter registration number",Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }

    private void replaceFragment( Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framelayout,fragment).commit();
    }
}
