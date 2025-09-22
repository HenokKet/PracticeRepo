package learn.spaceflight.personnel;

public class Astronaut {
    private final String firstName;
    private final String lastName;
    private final int yearsOfService;

    public Astronaut(String firstName, String lastName, int yearsOfService) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearsOfService = yearsOfService;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getYearsOfService() {
        return yearsOfService;
    }

    @Override
    public String toString() {
        return "Astronaut{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearsOfService=" + yearsOfService +
                '}';
    }
}
