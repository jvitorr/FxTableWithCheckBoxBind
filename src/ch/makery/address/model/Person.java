package ch.makery.address.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Person {

	private final StringProperty firstName;
	private final StringProperty lastName;
	private final BooleanProperty active;
	private final BooleanProperty active2;

	/**
	 * Default constructor.
	 */

	/**
	 * Constructor with some initial data.
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Person(String firstName, String lastName, boolean active, boolean active2) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.active = new SimpleBooleanProperty(active);
		this.active2 = new SimpleBooleanProperty(active2);

		// Some initial dummy data, just for convenient testing.

	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", active=" + active + ", active2="
				+ active2 + "]";
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public Boolean getActive() {
		return active.get();
	}

	public void setActive(boolean active) {
		this.active.set(active);
	}

	public BooleanProperty activeProperty() {
		return active;
	}

	public Boolean getActive2() {
		return active2.get();
	}

	public void setActive2(boolean active) {
		this.active2.set(active);
	}

	public BooleanProperty active2Property() {
		return active2;
	}

}