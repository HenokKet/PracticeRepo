public class Person {
    private String firstName;
    private String lastName;
    private String primarySpecialty;

    // Constructor
    public Person(String firstName, String lastName, String primarySpecialty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.primarySpecialty = primarySpecialty;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPrimarySpecialty() {
        return primarySpecialty;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", " + primarySpecialty ;
    }

}