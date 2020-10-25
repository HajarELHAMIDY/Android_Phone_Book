package com.example.phonebook;

import java.io.Serializable;

public class Contact implements Serializable {
    private Integer Id;
    private String firstName;
    private String lastName ;
    private String Job;
    private String Phone;
    private String Email;

    public Contact(Integer id, String fname, String Lname,String job,  String email,String phone) {
        Id = id;
        firstName = fname;
        lastName =Lname;
        Job = job;
        Phone = phone;
        Email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }



    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

}
