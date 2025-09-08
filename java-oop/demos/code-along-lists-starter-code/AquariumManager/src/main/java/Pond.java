public class Pond implements Exhibit{
    private String name;
    private double temperature;
    private String fish;
    private boolean hasFountain;

    public Pond(String name, double temperature, String fish, boolean fountain) {
        this.name = name;
        this.temperature = temperature;
        this.fish = fish;
        this.hasFountain = fountain;
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

    public boolean isHasFountain() {
        return hasFountain;
    }

    public void setHasFountain(boolean hasFountain) {
        this.hasFountain = hasFountain;
    }

`
    @Override
    public String getDescription() {
        return "The " + name +
                " houses the "  + fish;
    }

    @Override
    public boolean isOpen() {
        return true;
    }`
}
