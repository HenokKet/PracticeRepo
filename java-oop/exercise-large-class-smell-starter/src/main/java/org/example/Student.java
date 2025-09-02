package org.example;

public class Student {
    private String firstName;
    private String lastName;
    private double GPA;
    private String homeroomTeacherFirstName;
    private String homeroomTeacherLastName;


    public String getHomeroomTeacherLastName() {
        return homeroomTeacherLastName;
    }

    public void setHomeroomTeacherLastName(String homeroomTeacherLastName) {
        this.homeroomTeacherLastName = homeroomTeacherLastName;
    }

    public String getHomeroomTeacherFirstName() {
        return homeroomTeacherFirstName;
    }

    public void setHomeroomTeacherFirstName(String homeroomTeacherFirstName) {
        this.homeroomTeacherFirstName = homeroomTeacherFirstName;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
