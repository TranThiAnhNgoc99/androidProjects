package com.example.ngoctta999123.heath.models.profile;

public class UpdateInfo {
    String address;
    String name;
    String dob;
    int gender;
    String phone;
    String email;

    public UpdateInfo(String address, String fullName, String dayOfBirth, int gender, String phone, String email) {
        this.address = address;
        this.name = fullName;
        this.dob = dayOfBirth;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return name;
    }

    public void setFullName(String fullName) {
        this.name = fullName;
    }

    public String getDayOfBirth() {
        return dob;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dob = dayOfBirth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
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
}
