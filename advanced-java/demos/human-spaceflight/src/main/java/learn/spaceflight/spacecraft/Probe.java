package learn.spaceflight.spacecraft;

public class Probe {

    private double distanceTraveled;

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public void addDistance(double distance) {
        if (distance > 0.0) {
            distanceTraveled += distance;
        }
    }

    @Override
    public String toString() {
        return "Probe{" +
                "distanceTraveled=" + distanceTraveled +
                '}';
    }
}
