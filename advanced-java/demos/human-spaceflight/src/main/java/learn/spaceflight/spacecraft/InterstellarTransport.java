package learn.spaceflight.spacecraft;

import learn.spaceflight.personnel.Astronaut;

import java.util.List;

public class InterstellarTransport {

    private Astronaut captain;
    private List<Astronaut> crew;

    public InterstellarTransport(List<Astronaut> crew) {
        this.crew = crew;
    }

    public Astronaut getCaptain() {
        return captain;
    }

    public void setCaptain(Astronaut captain) {
        this.captain = captain;
    }

    public List<Astronaut> getCrew() {
        return crew;
    }

    @Override
    public String toString() {
        return "InterstellarTransport{" +
                "captain=" + captain +
                ", crew=" + crew +
                '}';
    }
}
