public class App {
    public static void main(String[] args) {
        Exhibit[] myExhibits = new Exhibit[3];
        myExhibits[0] = new Pond("Front Pond", 27.0, "Catfish", true);
        myExhibits[1] = new Aquarium("100 Gallon Tank", 23.0, "Oscar", "Floating Water Moss");
        myExhibits[2] = new Terrarium("Little Turtle Tank", 27.0, 22.0, "Ornate Box Turtle");


        System.out.println("Welcome to the Aquarium Manager!");
        System.out.println("Here are the current exhibits: ");

        for(Exhibit e : myExhibits) {
            System.out.println(e.getDescription());
        }
    }
}
