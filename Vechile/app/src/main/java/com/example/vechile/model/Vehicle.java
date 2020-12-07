package com.example.vechile.model;

public class Vehicle {

    private String vehicle_type;
    private String mileage;
    private String vehicle_model;
    private String color;
    private String fleet_no;
    private String rego_no;
    private String date;
    private String start_km;
    private String end_km;
    private String company;
    private String location;
    private String reservation;
    private String point_contact;
    private String phone;
    private String email;
    private String prefered_status;
    private String acc;
    private String other;

    String GeneralCondition;
    String FrontSea;
    String RearSeat;
    String ClimateControl;
    String InteriorLight;
    String CriticalSystem;
    String MrchanicalInspec;
    String TyrePressure;
    String All_door_lock;
    String Rear_child_lock;
    String All_window_switch;
    String v12_auxlliary;
    String Rear;
    String FR;
    String FL;
    String RR;
    String RL;
    String Left_front;
    String Right_front;
    String Left_rear;
    String Right_rear;
    String windscreen;
    String wind_screen_value;

    String Safety_equipment;
    String check_by;
    String received_by;
    byte [] Mechanics_signature;
    byte[] Receiver_signature;

    public String getGeneralCondition() {
        return GeneralCondition;
    }

    public void setGeneralCondition(String generalCondition) {
        GeneralCondition = generalCondition;
    }

    public String getFrontSea() {
        return FrontSea;
    }

    public void setFrontSea(String frontSea) {
        FrontSea = frontSea;
    }

    public String getRearSeat() {
        return RearSeat;
    }

    public void setRearSeat(String rearSeat) {
        RearSeat = rearSeat;
    }

    public String getClimateControl() {
        return ClimateControl;
    }

    public void setClimateControl(String climateControl) {
        ClimateControl = climateControl;
    }

    public String getInteriorLight() {
        return InteriorLight;
    }

    public void setInteriorLight(String interiorLight) {
        InteriorLight = interiorLight;
    }

    public String getCriticalSystem() {
        return CriticalSystem;
    }

    public void setCriticalSystem(String criticalSystem) {
        CriticalSystem = criticalSystem;
    }

    public String getMrchanicalInspec() {
        return MrchanicalInspec;
    }

    public void setMrchanicalInspec(String mrchanicalInspec) {
        MrchanicalInspec = mrchanicalInspec;
    }

    public String getTyrePressure() {
        return TyrePressure;
    }

    public void setTyrePressure(String tyrePressure) {
        TyrePressure = tyrePressure;
    }

    public String getSafety_equipment() {
        return Safety_equipment;
    }

    public void setSafety_equipment(String safety_equipment) {
        Safety_equipment = safety_equipment;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public byte[] getMechanics_signature() {
        return Mechanics_signature;
    }

    public void setMechanics_signature(byte[] mechanics_signature) {
        Mechanics_signature = mechanics_signature;
    }

    public byte[] getReceiver_signature() {
        return Receiver_signature;
    }

    public void setReceiver_signature(byte[] receiver_signature) {
        Receiver_signature = receiver_signature;
    }

    public String getWindscreen() {
        return windscreen;
    }

    public void setWindscreen(String windscreen) {
        this.windscreen = windscreen;
    }

    public String getWind_screen_value() {
        return wind_screen_value;
    }

    public void setWind_screen_value(String wind_screen_value) {
        this.wind_screen_value = wind_screen_value;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }


    public String getCheck_by() {
        return check_by;
    }

    public void setCheck_by(String check_by) {
        this.check_by = check_by;
    }

    public String getReceived_by() {
        return received_by;
    }

    public void setReceived_by(String received_by) {
        this.received_by = received_by;
    }

    public String getAll_door_lock() {
        return All_door_lock;
    }

    public void setAll_door_lock(String all_door_lock) {
        All_door_lock = all_door_lock;
    }

    public String getRear_child_lock() {
        return Rear_child_lock;
    }

    public void setRear_child_lock(String rear_child_lock) {
        Rear_child_lock = rear_child_lock;
    }

    public String getAll_window_switch() {
        return All_window_switch;
    }

    public void setAll_window_switch(String all_window_switch) {
        All_window_switch = all_window_switch;
    }

    public String getV12_auxlliary() {
        return v12_auxlliary;
    }

    public void setV12_auxlliary(String v12_auxlliary) {
        this.v12_auxlliary = v12_auxlliary;
    }

    public String getRear() {
        return Rear;
    }

    public void setRear(String rear) {
        Rear = rear;
    }

    public String getFR() {
        return FR;
    }

    public void setFR(String FR) {
        this.FR = FR;
    }

    public String getFL() {
        return FL;
    }

    public void setFL(String FL) {
        this.FL = FL;
    }

    public String getRR() {
        return RR;
    }

    public void setRR(String RR) {
        this.RR = RR;
    }

    public String getRL() {
        return RL;
    }

    public void setRL(String RL) {
        this.RL = RL;
    }

    public String getLeft_front() {
        return Left_front;
    }

    public void setLeft_front(String left_front) {
        Left_front = left_front;
    }

    public String getRight_front() {
        return Right_front;
    }

    public void setRight_front(String right_front) {
        Right_front = right_front;
    }

    public String getLeft_rear() {
        return Left_rear;
    }

    public void setLeft_rear(String left_rear) {
        Left_rear = left_rear;
    }

    public String getRight_rear() {
        return Right_rear;
    }

    public void setRight_rear(String right_rear) {
        Right_rear = right_rear;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }



    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFleet_no() {
        return fleet_no;
    }

    public void setFleet_no(String fleet_no) {
        this.fleet_no = fleet_no;
    }

    public String getRego_no() {
        return rego_no;
    }

    public void setRego_no(String rego_no) {
        this.rego_no = rego_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_km() {
        return start_km;
    }

    public void setStart_km(String start_km) {
        this.start_km = start_km;
    }

    public String getEnd_km() {
        return end_km;
    }

    public void setEnd_km(String end_km) {
        this.end_km = end_km;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }

    public String getPoint_contact() {
        return point_contact;
    }

    public void setPoint_contact(String point_contact) {
        this.point_contact = point_contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrefered_status() {
        return prefered_status;
    }

    public void setPrefered_status(String prefered_status) {
        this.prefered_status = prefered_status;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }
}
