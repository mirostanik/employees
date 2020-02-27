package mstanik.employees.filter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import mstanik.employees.model.Employee;
import mstanik.employees.model.Position;

public class EmployeeFilterTest {

	@ParameterizedTest
	@ValueSource(strings = { "1", "12","123","1234","B","Bene","b","bene","a","Actor","C","Cumber","dict","batch","Benedict Cumberbatch","Cumberbatch","200","2008","10" })
	public void testMatch(String pattern) {
		Employee e = new Employee();
		e.setId(1234L);
		e.setFirstName("Benedict");
		e.setLastName("Cumberbatch");
		e.setPosition(new Position("Actor"));
		e.setBirthDate(LocalDate.of(2008, 10, 2));
		
		assertTrue(new EmployeeFilterImpl(pattern).match(e));
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "100", "dicto","2000","100"})
	public void testNotMatch(String pattern) {
		Employee e = new Employee();
		e.setId(1234L);
		e.setFirstName("Benedict");
		e.setLastName("Cumberbatch");
		e.setPosition(new Position("Actor"));
		e.setBirthDate(LocalDate.of(2008, 10, 2));
		
		assertFalse(new EmployeeFilterImpl(pattern).match(e));
	}
}
