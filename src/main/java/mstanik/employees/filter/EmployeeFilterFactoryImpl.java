package mstanik.employees.filter;

import org.springframework.stereotype.Component;

import mstanik.employees.model.Employee;

@Component
public class EmployeeFilterFactoryImpl implements EmployeeFilterFactory{

	@Override
	public Filter<Employee> create(String pattern) {
		return new EmployeeFilterImpl(pattern);
	}

}
