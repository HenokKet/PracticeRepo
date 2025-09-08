public class Aquarium implements Exhibit {
    private String name;
    private double temperature;
    private String fish;
    private String plant;

    public Aquarium(String name, double temperature, String fish, String plant) {
        this.name = name;
        this.temperature = temperature;
        this.fish = fish;
        this.plant = plant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getFish() {
        return fish;
    }

    public void setFish(String fish) {
        this.fish = fish;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    @Override
    public String getDescription() {
        return "The " + name +
                " houses the " +
                fish + " and " +
                plant;
    }

    @Override
    public boolean isOpen() {
        return true;
    }
}
