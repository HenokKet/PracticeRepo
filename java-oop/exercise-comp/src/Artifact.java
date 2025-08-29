public class Artifact {
    private String name;
    private int yearOfDiscovery;
    private Person discoverer;
    private Person curator;

    // Constructor
    public Artifact(String name, int yearOfDiscovery, Person discoverer, Person curator) {
        this.name = name;
        this.yearOfDiscovery = yearOfDiscovery;
        this.discoverer = discoverer;
        this.curator = curator;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getYearOfDiscovery() {
        return yearOfDiscovery;
    }

    public Person getDiscoverer() {
        return discoverer;
    }

    public Person getCurator() {
        return curator;
    }

    @Override
    public String toString() {
        return "Artifact: " + name +
                " (" + yearOfDiscovery + ")" +
                "\nDiscoverer: " + discoverer +
                "\nCurator: " + curator;
    }
}
