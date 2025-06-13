package de.hitec.nhplus.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Abstract base class for all persons in the system.
 * <p>
 * A <code>Person</code> has a first name and a surname. The class uses JavaFX properties
 * to allow for data binding in the UI layer.
 */
public abstract class Person {

    private final SimpleStringProperty firstName;
    private final SimpleStringProperty surname;

    /**
     * Constructor to initialize a <code>Person</code> object with a first name and a surname.
     *
     * @param firstName First name of the person.
     * @param surname   Surname of the person.
     */
    public Person(String firstName, String surname) {
        this.firstName = new SimpleStringProperty(firstName);
        this.surname = new SimpleStringProperty(surname);
    }

    /**
     * Returns the first name of the person.
     *
     * @return First name as a string.
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     * Returns the JavaFX property for the first name.
     *
     * @return JavaFX's property for first name.
     */
    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    /**
     * Sets the first name of the person.
     *
     * @param firstName New first name.
     */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    /**
     * Returns the surname of the person.
     *
     * @return Surname as a string.
     */
    public String getSurname() {
        return surname.get();
    }

    /**
     * Returns the JavaFX property for the surname.
     *
     * @return JavaFX's property for surname.
     */
    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    /**
     * Sets the surname of the person.
     *
     * @param surname New surname.
     */
    public void setSurname(String surname) {
        this.surname.set(surname);
    }
}