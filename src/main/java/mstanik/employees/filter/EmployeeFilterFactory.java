package mstanik.employees.filter;

import mstanik.employees.model.Employee;

public interface EmployeeFilterFactory {

	
	Filter<Employee> create(String pattern);
}
