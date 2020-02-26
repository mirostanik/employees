package mstanik.employees.model;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	@OneToOne
	@JoinColumn(name = "position_id", referencedColumnName = "id")
	private Position position;

	public Employee() {

	}

	public Employee(String firstName, String lastName, Position position) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Book))
			return false;
		Employee e = (Employee) o;
		return id != null && id.equals(e.getId());
	}

	@Override
	public int hashCode() {
		return id == null ? Objects.hash(Long.MIN_VALUE) : Objects.hash(id);
	}
}
