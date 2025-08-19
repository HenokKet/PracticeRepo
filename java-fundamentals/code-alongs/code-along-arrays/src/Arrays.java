public class Arrays {
    public static void main(String[] args) {
        //The Hard Way
        String s1 = "Jerry";
        String s2 = "Elaine";
        String s3 = "George";
        String s4 = "Cosmo";
        String s5 = "Newman";

        System.out.println("Welcome " + s1);
        System.out.println("Welcome " + s2);
        System.out.println("Welcome " + s3);
        System.out.println("Welcome " + s4);
        System.out.println("Welcome " + s5);

        //Using an Array
        String[] students;
        students = new String[5];

        students[0] = "Jerry";
        students[1]  = "Elaine";
        students[2] = "George";
        students[3] = "Cosmo";
        students[4] = "Newman";
        System.out.println(students[2] + " IS GETTING UPSET!!!");

//        String[] studentCopy = students;
//        studentCopy[2] = "Frank";
        //New Array From Old
        String[] residents = new String[students.length];
        for (int i = 0; i < students.length; i++){
            residents[i] = students[i];
        }
        // OR
        //System.arraycopy(students, 0, residents, 0, students.length);

        

        //For each value  in residents
        for (String name : residents){
            System.out.println("Welcome " + name);
            if (name.equalsIgnoreCase("George")){
                name = "Frank";
            }
        }

        double[] grades = {68.8, 97.8, 92.2, 68.7, 80.1, 99.9, 99.8, 99.7};
        double total = 0.0;
        boolean canPlay = true;
        for (int i = 0; i < grades.length; i++){
            if (grades[i] < 70.0){
                grades[i]+= 5;
            }
        }

        for (int i = 0; i < grades.length; i++){
            if (grades[i] < 70.0){
                canPlay = false;
            }
            total += grades[i];
        }
        if (total/grades.length < 70.0){
            canPlay = false;
        }
        System.out.println("The Average is " + total/grades.length);
        if (canPlay){
            System.out.println("Play Ball!");
        } else {
            System.out.println("No Sports for you!!");
        }

    }
}
