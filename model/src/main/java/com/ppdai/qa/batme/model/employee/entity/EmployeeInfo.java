package com.ppdai.qa.batme.model.employee.entity;

import java.util.Date;

public class EmployeeInfo {
    private Integer id;

    private String employee_name;

    private String employee_password;

    private String description;

    private String phone_no;

    private String employee_no;

    private String personalized_signature;

    private Date update_time;

    private Integer encrypt_type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name == null ? null : employee_name.trim();
    }

    public String getEmployee_password() {
        return employee_password;
    }

    public void setEmployee_password(String employee_password) {
        this.employee_password = employee_password == null ? null : employee_password.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no == null ? null : phone_no.trim();
    }

    public String getEmployee_no() {
        return employee_no;
    }

    public void setEmployee_no(String employee_no) {
        this.employee_no = employee_no == null ? null : employee_no.trim();
    }

    public String getPersonalized_signature() {
        return personalized_signature;
    }

    public void setPersonalized_signature(String personalized_signature) {
        this.personalized_signature = personalized_signature == null ? null : personalized_signature.trim();
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getEncrypt_type() {
        return encrypt_type;
    }

    public void setEncrypt_type(Integer encrypt_type) {
        this.encrypt_type = encrypt_type;
    }
}