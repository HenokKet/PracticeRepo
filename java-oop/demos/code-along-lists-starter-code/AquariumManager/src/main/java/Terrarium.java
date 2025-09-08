public class Terrarium implements Exhibit{
    private String name;
    private double airTemperature;
    private double waterTemperature;
    private String turtle;

    public Terrarium(String name, double airTemperature, double waterTemperature, String turtle) {
        this.name = name;
        this.airTemperature = airTemperature;
        this.waterTemperature = waterTemperature;
        this.turtle = turtle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public double getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(double waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public String getTurtle() {
        return turtle;
    }

    public void setTurtle(String turtle) {
        this.turtle = turtle;
    }

    @Override
    public String getDescription() {
        return "The " + name +
                " houses the " + turtle;
    }

    @Override
    public boolean isOpen() {
        return false;
    }
}
