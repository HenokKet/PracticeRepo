package org.example;

public class Main {
    public static void main(String[] args) {
        ConsoleIO.display("Welcome to Better School Tracker!");
        //Gathers all the teachers and students info
        String sFirst = ConsoleIO.promptString("Enter Student First Name:");
        String sLast  = ConsoleIO.promptString("Enter Student Last Name:");
        double gpa    = ConsoleIO.promptDouble("Enter Student's GPA");
        String tFirst = ConsoleIO.promptString("Enter Homeroom teacher's first name:");
        String tLast  = ConsoleIO.promptString("Enter Homeroom teacher's last name:");
        //Creates the teacher and the student using the info obtained from User Input
        Teacher teacher = new Teacher(tFirst, tLast);
        Student student = new Student(sFirst, sLast, gpa, teacher);
        //Displays the information afterward
        System.out.println("Student: " + student.getFullName());
        System.out.println("GPA: " + student.getGPA());
        System.out.println("Homeroom Teacher: " + student.getHomeroomTeacher().getFullName());
    }
}