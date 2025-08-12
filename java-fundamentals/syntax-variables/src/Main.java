//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static int hippoCount = 0;
    public static void main(String[] args) {
        for(int i =0; i <= 10; i++){
            hippoCount++;
            System.out.print(hippoCount + " Hippopotamus" + "\n" );
        }
        int height;
        height =170;
        double weight = 175.5;
        char oneLetter = 'S';
        String eyeColor = "Blue";
        System.out.println(eyeColor.substring(1,3));
        int age = 51;
        boolean canDrive = age >= 21;

        System.out.println(canDrive);
        int heightIn = (int) Math.round(height * 0.3937);
        System.out.println(heightIn);
    }
}