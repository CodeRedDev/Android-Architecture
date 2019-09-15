package de.codereddev.architecture.model;

/**
 * This serves as a Model class with multiple fields.
 */
public class NamePair {

    private String firstName;
    private String lastName;

    public NamePair(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
