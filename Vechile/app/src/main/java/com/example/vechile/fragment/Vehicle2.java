package com.example.vechile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.vechile.R;

import java.util.ArrayList;

public class Vehicle2 extends Fragment {
    private Button next;
    private TextView textView;
    private CheckBox general_condition,no_order,front_seat_area_clean,Rear_seat_area_clean,front_rear_carpet_clean,front_rear_mat_installed,
    Front_seat,clean,control_working_order,seat_belts_working_order,Rear_seat,rear_clean,rear_control_working_order,
    climate_control,heat,ac,defroster,blower_fan,rear_controler,all_door_lock,rear_door_child_lock,all_window_switch,interior_light,
    w_front_doors,w_rear_door,v12_auxilary,rear,critical_system,seat_belt_light,air_bag_light,abs_light,electronic_stabilituy,
    tranction_control,fluid_level,engine_oil,transmation_fluid,brake_fluid,spare_tyre,tyres,wind_sheild,fuel_level,fan_belts,
    wheel_spanner,jack,tyre_pressure,left_front,right_front,left_rear,right_rear,windscreen;
    private EditText FR,FL,RR,RL,left_front_vcalue,right_front_value,left_rear_value,right_rear_value,windscreen_value;
    private ArrayList<String> GeneralCondition = new ArrayList<>();
    private ArrayList<String> FrontSeat = new ArrayList<>();
    private ArrayList<String> RearSeat = new ArrayList<>();
    private ArrayList<String> ClimateControl = new ArrayList<>();
    private ArrayList<String> InteriorLight = new ArrayList<>();
    private ArrayList<String> CriticalSystem = new ArrayList<>();
    private ArrayList<String> MrchanicalInspec = new ArrayList<>();
    private ArrayList<String> TyrePressure = new ArrayList<>();
    private String general_condition_check,no_order_check,front_seat_area_clean_check,Rear_seat_area_clean_check,front_rear_carpet_clean_check,front_rear_mat_installed_check,
            Front_seat_check,clean_check,control_working_order_check,seat_belts_working_order_check,Rear_seat_check,rear_clean_check,rear_control_working_order_check,
            climate_control_check,heat_check,ac_check,defroster_check,blower_fan_check,rear_controler_check,all_door_lock_check,rear_door_child_lock_check,all_window_switch_check,interior_light_check,
            w_front_doors_check,w_rear_door_check,v12_auxilary_check,rear_check,critical_system_check,seat_belt_light_check,air_bag_light_check,abs_light_check,electronic_stabilituy_check,
            tranction_control_check,fluid_level_check,engine_oil_check,transmation_fluid_check,brake_fluid_check,spare_tyre_check,tyres_check,wind_sheild_check,fuel_level_check,fan_belts_check,
            wheel_spanner_check,jack_check,tyre_pressure_check,left_front_check,right_front_check,left_rear_check,right_rear_check,windscreen_check;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vehicle2,null,false);
        next = view.findViewById(R.id.next);
        textView = view.findViewById(R.id.mech);

        general_condition = view.findViewById(R.id.general_condition);
        no_order = view.findViewById(R.id.no_order);
        front_seat_area_clean = view.findViewById(R.id.front_seat_area_clean);
        Rear_seat_area_clean = view.findViewById(R.id.rear_seat_area_clean);
        front_rear_carpet_clean = view.findViewById(R.id.front_rear_carpet_clean);
        front_rear_mat_installed = view.findViewById(R.id.front_rear_mat_clean);
        Front_seat = view.findViewById(R.id.front_seats);
        clean = view.findViewById(R.id.clean);
        control_working_order = view.findViewById(R.id.control_working_order);
        seat_belts_working_order = view.findViewById(R.id.seat_belts_working_otder);
        Rear_seat = view.findViewById(R.id.rear_seats);
        rear_clean = view.findViewById(R.id.rear_clean);
        rear_control_working_order = view.findViewById(R.id.rear_control_working_order);
        climate_control = view.findViewById(R.id.climate_control);
        heat = view.findViewById(R.id.heat);
        ac = view.findViewById(R.id.ac);
        defroster = view.findViewById(R.id.defroster);
        blower_fan = view.findViewById(R.id.blower_fan);
        rear_controler = view.findViewById(R.id.rear_control);
        all_door_lock = view.findViewById(R.id.all_door_lock);
        rear_door_child_lock = view.findViewById(R.id.rear_door_child_lock);
        all_window_switch = view.findViewById(R.id.all_window_switches);
        interior_light = view.findViewById(R.id.interior_light);
        w_front_doors = view.findViewById(R.id.w_front_door);
        w_rear_door = view.findViewById(R.id.w_rear_door);
        v12_auxilary = view.findViewById(R.id.v12_auxlary);
        rear = view.findViewById(R.id.rear);
        critical_system = view.findViewById(R.id.critical_system_funtionality);
        seat_belt_light = view.findViewById(R.id.seat_belt_light);
        air_bag_light = view.findViewById(R.id.air_bag_light);
        abs_light = view.findViewById(R.id.abs_light);
        electronic_stabilituy = view.findViewById(R.id.electric_stability);
        tranction_control = view.findViewById(R.id.tranction_control);
        fluid_level = view.findViewById(R.id.fluid_level);
        engine_oil = view.findViewById(R.id.engine_oil);
        transmation_fluid = view.findViewById(R.id.transmition_fluid);
        brake_fluid = view.findViewById(R.id.brake_fluid);
        spare_tyre = view.findViewById(R.id.spare_tyre);
        tyres = view.findViewById(R.id.tyres);
        wind_sheild = view.findViewById(R.id.wind_sheild_washer);
        fuel_level = view.findViewById(R.id.fuel_level);
        fan_belts = view.findViewById(R.id.fan_belts);
        wheel_spanner = view.findViewById(R.id.wheel_spanner);
        jack = view.findViewById(R.id.jack);
        tyre_pressure = view.findViewById(R.id.tyre_pressure);
        left_front = view.findViewById(R.id.left_front);
        right_front = view.findViewById(R.id.right_front);
        left_rear = view.findViewById(R.id.left_rear);
        right_rear = view.findViewById(R.id.right_rear);
        windscreen = view.findViewById(R.id.windscreen);

        FR = view.findViewById(R.id.fr);
        FL = view.findViewById(R.id.fl);
        RR = view.findViewById(R.id.rr);
        RL = view.findViewById(R.id.rl);
        left_front_vcalue = view.findViewById(R.id.left_front_value);
        right_front_value = view.findViewById(R.id.right_front_value);
        left_rear_value = view.findViewById(R.id.left_rear_value);
        right_rear_value = view.findViewById(R.id.right_rear_value);
        windscreen_value = view.findViewById(R.id.wwindscreen_value);
//        String value = getArguments().getString("YourKey");
//        textView.setText(value);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (general_condition.isChecked()){
                    general_condition_check = general_condition.getText().toString();
                    GeneralCondition.add(general_condition_check);
                }
                if (no_order.isChecked()){
                    no_order_check = no_order.getText().toString();
                    GeneralCondition.add(no_order_check);
                }
                if (front_seat_area_clean.isChecked()){
                    front_seat_area_clean_check = front_seat_area_clean.getText().toString();
                    GeneralCondition.add(front_seat_area_clean_check);
                }
                if (Rear_seat_area_clean.isChecked()){
                    Rear_seat_area_clean_check = Rear_seat_area_clean.getText().toString();
                    GeneralCondition.add(Rear_seat_area_clean_check);
                }
                if (front_rear_carpet_clean.isChecked()){
                    front_rear_carpet_clean_check = front_rear_carpet_clean.getText().toString();
                    GeneralCondition.add(front_rear_carpet_clean_check);
                }
                if (front_rear_mat_installed.isChecked()){
                    front_rear_mat_installed_check = front_rear_mat_installed.getText().toString();
                    GeneralCondition.add(front_rear_mat_installed_check);
                }
                if (Front_seat.isChecked()){
                    Front_seat_check = Front_seat.getText().toString();
                    FrontSeat.add(Front_seat_check);
                }
                if (clean.isChecked()){
                    clean_check = clean.getText().toString();
                    FrontSeat.add(clean_check);
                }
                if (control_working_order.isChecked()){
                    control_working_order_check = control_working_order.getText().toString();
                    FrontSeat.add(control_working_order_check);
                }
                if (seat_belts_working_order.isChecked()){
                    seat_belts_working_order_check = seat_belts_working_order.getText().toString();
                    FrontSeat.add(seat_belts_working_order_check);
                }
                if (Rear_seat.isChecked()){
                    Rear_seat_check = Rear_seat.getText().toString();
                    RearSeat.add(Rear_seat_check);
                }
                if (rear_clean.isChecked()){
                    rear_clean_check = rear_clean.getText().toString();
                    RearSeat.add(rear_clean_check);
                }
                if (rear_control_working_order.isChecked()){
                    rear_control_working_order_check = rear_control_working_order.getText().toString();
                    RearSeat.add(rear_control_working_order_check);
                }
                if (climate_control.isChecked()){
                    climate_control_check = climate_control.getText().toString();
                    ClimateControl.add(climate_control_check);
                }
                if (heat.isChecked()){
                    heat_check = heat.getText().toString();
                    ClimateControl.add(heat_check);
                }
                if (ac.isChecked()){
                    ac_check = ac.getText().toString();
                    ClimateControl.add(ac_check);
                }
                if (defroster.isChecked()){
                    defroster_check = defroster.getText().toString();
                    ClimateControl.add(defroster_check);
                }
                if (blower_fan.isChecked()){
                    blower_fan_check = blower_fan.getText().toString();
                    ClimateControl.add(blower_fan_check);
                }
                if (rear_controler.isChecked()){
                    rear_controler_check = rear_controler.getText().toString();
                    ClimateControl.add(rear_controler_check);
                }
                if (all_door_lock.isChecked()){
                    all_door_lock_check = all_door_lock.getText().toString();
                }
                if (rear_door_child_lock.isChecked()){
                    rear_door_child_lock_check = rear_door_child_lock.getText().toString();
                }
                if (all_window_switch.isChecked()){
                    all_window_switch_check = all_window_switch.getText().toString();
                }
                if (interior_light.isChecked()){
                    interior_light_check = interior_light.getText().toString();
                    InteriorLight.add(interior_light_check);
                }
                if (w_front_doors.isChecked()){
                    w_front_doors_check = w_front_doors.getText().toString();
                    InteriorLight.add(w_front_doors_check);
                }
                if (w_rear_door.isChecked()){
                    w_rear_door_check = w_rear_door.getText().toString();
                    InteriorLight.add(w_rear_door_check);
                }
                if (v12_auxilary.isChecked()){
                    v12_auxilary_check = v12_auxilary.getText().toString();
                }
                if (rear.isChecked()){
                    rear_check = rear.getText().toString();
                }
                if (critical_system.isChecked()){
                    critical_system_check = critical_system.getText().toString();
                    CriticalSystem.add(critical_system_check);
                }
                if (seat_belt_light.isChecked()){
                    seat_belt_light_check = seat_belt_light.getText().toString();
                    CriticalSystem.add(seat_belt_light_check);
                }
                if (air_bag_light.isChecked()){
                    air_bag_light_check = air_bag_light.getText().toString();
                    CriticalSystem.add(air_bag_light_check);
                }
                if (abs_light.isChecked()){
                    abs_light_check = abs_light.getText().toString();
                    CriticalSystem.add(abs_light_check);
                }
                if (electronic_stabilituy.isChecked()){
                    electronic_stabilituy_check = electronic_stabilituy.getText().toString();
                    CriticalSystem.add(electronic_stabilituy_check);
                }
                if(tranction_control.isChecked()){
                    tranction_control_check = tranction_control.getText().toString();
                    CriticalSystem.add(tranction_control_check);
                }
                if (fluid_level.isChecked()){
                    fluid_level_check = fluid_level.getText().toString();
                    MrchanicalInspec.add(fluid_level_check);
                }
                if (engine_oil.isChecked()){
                    engine_oil_check = engine_oil.getText().toString();
                    MrchanicalInspec.add(engine_oil_check);
                }
                if (transmation_fluid.isChecked()){
                    transmation_fluid_check = transmation_fluid.getText().toString();
                    MrchanicalInspec.add(transmation_fluid_check);
                }
                if (brake_fluid.isChecked()){
                    brake_fluid_check = brake_fluid.getText().toString();
                    MrchanicalInspec.add(brake_fluid_check);
                }
                if (spare_tyre.isChecked()){
                    spare_tyre_check = spare_tyre.getText().toString();
                    MrchanicalInspec.add(spare_tyre_check);
                }
                if (tyres.isChecked()){
                    tyres_check = tyres.getText().toString();
                    MrchanicalInspec.add(tyres_check);
                }
                if (wind_sheild.isChecked()){
                    wind_sheild_check = wind_sheild.getText().toString();
                    MrchanicalInspec.add(wind_sheild_check);
                }
                if (fuel_level.isChecked()){
                    fuel_level_check = fuel_level.getText().toString();
                    MrchanicalInspec.add(fuel_level_check);
                }
                if (fan_belts.isChecked()){
                    fan_belts_check = fan_belts.getText().toString();
                    MrchanicalInspec.add(fan_belts_check);
                }
                if (wheel_spanner.isChecked()){
                    wheel_spanner_check = wheel_spanner.getText().toString();
                    MrchanicalInspec.add(wheel_spanner_check);
                }
                if (jack.isChecked()){
                    jack_check = jack.getText().toString();
                    MrchanicalInspec.add(jack_check);
                }
                if (tyre_pressure.isChecked()){
                    tyre_pressure_check = tyre_pressure.getText().toString();
                    TyrePressure.add(tyre_pressure_check);
                }
                if (left_front.isChecked()){
                    left_front_check = left_front.getText().toString();
                    TyrePressure.add(left_front_check);
                }
                if (right_front.isChecked()){
                    right_front_check = right_front.getText().toString();
                    TyrePressure.add(right_front_check);
                }
                if (left_rear.isChecked()){
                    left_rear_check = left_rear.getText().toString();
                    TyrePressure.add(left_rear_check);
                }
                if (right_rear.isChecked()){
                    right_rear_check = right_rear.getText().toString();
                    TyrePressure.add(right_rear_check);
                }
                if (windscreen.isChecked()){
                    windscreen_check = windscreen.getText().toString();
                }
                Vehicle3 vehicle3 = new Vehicle3();
                Bundle bundle = new Bundle();
                bundle.putString("GeneralCondition", String.valueOf(GeneralCondition));
                bundle.putString("FrontSeat", String.valueOf(FrontSeat));
                bundle.putString("RearSeat", String.valueOf(RearSeat));
                bundle.putString("ClimateControl", String.valueOf(ClimateControl));
                bundle.putString("InteriorLight", String.valueOf(InteriorLight));
                bundle.putString("CriticalSystem", String.valueOf(CriticalSystem));
                bundle.putString("MrchanicalInspec", String.valueOf(MrchanicalInspec));
                bundle.putString("TyrePressure", String.valueOf(TyrePressure));
                bundle.putString("all_door_lock_check", all_door_lock_check);
                bundle.putString("rear_door_child_lock_check",rear_door_child_lock_check);
                bundle.putString("all_window_switch_check",all_window_switch_check);
                bundle.putString("v12_auxilary_check",v12_auxilary_check);
                bundle.putString("rear_check",rear_check);
                bundle.putString("windscreen_check",windscreen_check);
                bundle.putString("FR",FR.getText().toString());
                bundle.putString("FL",FL.getText().toString());
                bundle.putString("RR",RR.getText().toString());
                bundle.putString("RL",RL.getText().toString());
                bundle.putString("left_front_vcalue",left_front_vcalue.getText().toString());
                bundle.putString("right_front_value",right_front_value.getText().toString());
                bundle.putString("left_rear_value",left_rear_value.getText().toString());
                bundle.putString("right_rear_value",right_rear_value.getText().toString());
                bundle.putString("windscreen_value",windscreen_value.getText().toString());

                bundle.putString("vehicle_type_1",getArguments().getString("vehicle_type"));
                bundle.putString("vehicle_model_1",getArguments().getString("vehicle_model"));
                bundle.putString("millege_1",getArguments().getString("millege"));
                bundle.putString("color_1",getArguments().getString("color"));
                bundle.putString("fleet_no_1",getArguments().getString("fleet_no"));
                bundle.putString("reg_no_1",getArguments().getString("reg_no"));
                bundle.putString("date_1",getArguments().getString("date"));
                bundle.putString("start_km_1",getArguments().getString("start_km"));
                bundle.putString("end_km_1",getArguments().getString("end_km"));
                bundle.putString("company_name_1",getArguments().getString("company_name"));
                bundle.putString("location_1",getArguments().getString("location"));
                bundle.putString("reservation_no_1",getArguments().getString("reservation_no"));
                bundle.putString("point_of_contact_1",getArguments().getString("point_of_contact"));
                bundle.putString("phone_no_1",getArguments().getString("phone_no"));
                bundle.putString("email_1",getArguments().getString("email"));
                bundle.putString("prefered_status_1",getArguments().getString("prefered_status"));
                bundle.putString("acc_1",getArguments().getString("acc"));

                vehicle3.setArguments(bundle);
                replaceFragment(vehicle3);
            }
        });
        return view;
    }
    private void replaceFragment( Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framelayout,fragment).commit();
    }
}
