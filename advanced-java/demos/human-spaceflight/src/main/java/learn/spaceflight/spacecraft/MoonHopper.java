package learn.spaceflight.spacecraft;

import learn.spaceflight.personnel.Astronaut;

public class MoonHopper {

    private final Astronaut pilot;
    private final Astronaut copilot;

    public MoonHopper(Astronaut pilot, Astronaut copilot) {
        this.pilot = pilot;
        this.copilot = copilot;
    }

    @Override
    public String toString() {
        return "MoonHopper{" +
                "pilot=" + pilot +
                ", copilot=" + copilot +
                '}';
    }
}
