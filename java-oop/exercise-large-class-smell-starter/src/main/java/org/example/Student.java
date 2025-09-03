package org.example;

public class Student extends Person{
    private double gpa;
    private Teacher homeroomTeacher;

    public Student() {}

    public Student(String firstName, String lastName, double gpa, Teacher homeroomTeacher) {
        super(firstName, lastName);
        this.gpa = gpa;
        this.homeroomTeacher = homeroomTeacher;
    }

    public double getGPA() {
        return gpa;
    }
    public void setGPA(double gpa) {
        this.gpa = gpa;
    }

    public Teacher getHomeroomTeacher() {
        return homeroomTeacher;
    }
    public void setHomeroomTeacher(Teacher homeroomTeacher) {
        this.homeroomTeacher = homeroomTeacher;
    }
}
