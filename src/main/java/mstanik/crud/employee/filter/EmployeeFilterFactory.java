package mstanik.crud.employee.filter;

import mstanik.crud.employee.model.Employee;
import mstanik.crud.filter.Filter;
import mstanik.crud.filter.FilterFactory;

public interface EmployeeFilterFactory extends FilterFactory<Employee>{

	Filter<Employee> create(String pattern);
}
