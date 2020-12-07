package com.example.lbltakefive.Databases;

public class Employee {
    private String id;
    private String Employee_name;
    private String Employee_no;
    private String Date;
    private String Job_location;
    private String Job_description;
    private String work_fitness;
    private String competency_trained;
    private String competency_auth;
    private String correct_tools;
    private String tools_condition;
    private String tools_safety;
    private String risk_hazards;
    private String control_measure;
    private String jsa_wp_permit;
    private String workplace_permit;
    private String supervisor_approved;
    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWork_fitness() {
        return work_fitness;
    }

    public void setWork_fitness(String work_fitness) {
        this.work_fitness = work_fitness;
    }

    public String getCompetency_trained() {
        return competency_trained;
    }

    public void setCompetency_trained(String competency_trained) {
        this.competency_trained = competency_trained;
    }

    public String getCompetency_auth() {
        return competency_auth;
    }

    public void setCompetency_auth(String competency_auth) {
        this.competency_auth = competency_auth;
    }

    public String getCorrect_tools() {
        return correct_tools;
    }

    public void setCorrect_tools(String correct_tools) {
        this.correct_tools = correct_tools;
    }

    public String getTools_condition() {
        return tools_condition;
    }

    public void setTools_condition(String tools_condition) {
        this.tools_condition = tools_condition;
    }

    public String getTools_safety() {
        return tools_safety;
    }

    public void setTools_safety(String tools_safety) {
        this.tools_safety = tools_safety;
    }

    public String getJsa_wp_permit() {
        return jsa_wp_permit;
    }

    public void setJsa_wp_permit(String jsa_wp_permit) {
        this.jsa_wp_permit = jsa_wp_permit;
    }

    public String getWorkplace_permit() {
        return workplace_permit;
    }

    public void setWorkplace_permit(String workplace_permit) {
        this.workplace_permit = workplace_permit;
    }

    public String getSupervisor_approved() {
        return supervisor_approved;
    }

    public void setSupervisor_approved(String supervisor_approved) {
        this.supervisor_approved = supervisor_approved;
    }

    public String getEmployee_name() {
        return Employee_name;
    }

    public void setEmployee_name(String employee_name) {
        Employee_name = employee_name;
    }

    public String getEmployee_no() {
        return Employee_no;
    }

    public void setEmployee_no(String employee_no) {
        Employee_no = employee_no;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getJob_location() {
        return Job_location;
    }

    public void setJob_location(String job_location) {
        Job_location = job_location;
    }

    public String getJob_description() {
        return Job_description;
    }

    public void setJob_description(String job_description) {
        Job_description = job_description;
    }

    public String getRisk_hazards() {
        return risk_hazards;
    }

    public void setRisk_hazards(String risk_hazards) {
        this.risk_hazards = risk_hazards;
    }

    public String getControl_measure() {
        return control_measure;
    }

    public void setControl_measure(String control_measure) {
        this.control_measure = control_measure;
    }


}
