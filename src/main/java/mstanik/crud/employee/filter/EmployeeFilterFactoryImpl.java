package mstanik.crud.employee.filter;

import org.springframework.stereotype.Component;

import mstanik.crud.employee.model.Employee;
import mstanik.crud.filter.Filter;

@Component
public class EmployeeFilterFactoryImpl implements  EmployeeFilterFactory{

	@Override
	public Filter<Employee> create(String pattern) {
		return new EmployeeFilterImpl(pattern);
	}

}
