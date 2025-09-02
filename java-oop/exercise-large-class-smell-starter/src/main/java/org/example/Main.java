package org.example;

public class Main {
    public static void main(String[] args) {
        Student student = new Student();

        ConsoleIO.display("Welcome to Better School Tracker!");

        student.setFirstName(ConsoleIO.promptString("Enter Student First Name"));
        student.setLastName(ConsoleIO.promptString("Enter Student Last Name"));
        student.setGPA(ConsoleIO.promptDouble("Enter Student's GPA"));
        student.setHomeroomTeacherFirstName(ConsoleIO.promptString("Enter Homeroom teacher's first Name"));
        student.setHomeroomTeacherLastName(ConsoleIO.promptString("Enter Homeroom teacher's last name"));

        System.out.println("Student: " + student.getFirstName() + " " + student.getLastName());
        System.out.println("GPA: " + student.getGPA());
        System.out.println("Homeroom Teacher: " + student.getHomeroomTeacherFirstName() + " " + student.getHomeroomTeacherLastName());

    }
}