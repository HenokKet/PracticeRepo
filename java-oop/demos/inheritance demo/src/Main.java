public class Main {
    public static void main(String[] args) {
        AquariumFish myFish = new AquariumFish("Bass", "Bassus Maximus", "Loud Mouth Bass", "Hamburgers", 90.00, 50.00);
        TropicalFish yourFish = new TropicalFish("Piranah", "Bitus Rearus", "Chomper", "People");
        System.out.println(myFish.toString());
        System.out.println(myFish.getSound());
        System.out.println(yourFish.toString());
        System.out.println(yourFish.getSound());
    }
}
