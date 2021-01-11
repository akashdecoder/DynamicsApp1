package com.example.dynamicsapp1;

public class UserEvent {
    private String userName;
    private String userEmail;
    private String phone;
    private String event;
    private String Usn;
    private String year;

    public UserEvent() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getContact() {
        return phone;
    }

    public void setContact(String phone) {
        this.phone = phone;
    }

    public String getUsn() {
        return Usn;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setUsn(String usn) {
        Usn = usn;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
