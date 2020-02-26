package mstanik.employees.model;

import java.awt.print.Book;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Position {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	public Position() {
	}

	public Position(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name ;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Book))
			return false;
		Position e = (Position) o;
		return id != null && id.equals(e.getId());
	}

	@Override
	public int hashCode() {
		return id == null ? Objects.hash(Long.MIN_VALUE) : Objects.hash(id);
	}
}
