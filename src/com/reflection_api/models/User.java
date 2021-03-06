package com.reflection_api.models;

import com.reflection_api.annotations.MyAnnotation;

public class User {
    private static int id = 1;
    private String firstName;
    private String lastName;
    private final String birthday;

    public User(String firstName, String lastName, String birthday) {
        id++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        User.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }
    @MyAnnotation(name = "", value = "")
    public String toString() {
        return "com.reflection_api.models.User [firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday + "]";
    }
}
