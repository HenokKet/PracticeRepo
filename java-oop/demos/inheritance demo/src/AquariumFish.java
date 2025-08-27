public class AquariumFish extends Fish{
    private String nickName;
    private String food;
    private double maxTemp;
    private double minTemp;

    public AquariumFish(String name, String scientificName, String nickName, String food, double maxTemp, double minTemp){
        super(name, scientificName);
        this.nickName = nickName;
        this.food = food;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    @Override
    public String toString(){
        return "This Aquarium fish has the nickname " + nickName +
                " it is of species " + getScientificName();
    }

    @Override
    public String getSound() {
        return "glubbbb glubb..";
    }
}
