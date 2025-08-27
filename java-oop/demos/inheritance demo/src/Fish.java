public class Fish {
    private String name;
    private String scientificName;

    //Constructor
    public Fish(String name, String scientificName){
        this.name = name;
        this.scientificName = scientificName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public String getSound(){
        return name + " says Oh Yeah!";
    }
}
