package mstanik.crud.employee.filter;

import org.springframework.format.datetime.standard.DateTimeFormatterFactory;

import mstanik.crud.employee.model.Employee;
import mstanik.crud.filter.Filter;

public class EmployeeFilterImpl implements Filter<Employee> {

	private final String pattern;

	public EmployeeFilterImpl(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public boolean match(Employee employee) { 

		return matchAnyField(employee)|| matchNames(employee);
	}

	
	private boolean matchNames(Employee employee) {
		return (employee.getFirstName()+" "+employee.getLastName()).toLowerCase().startsWith(pattern.toLowerCase());
	}

	private boolean matchAnyField(Employee employee) {
		StringBuilder builder = new StringBuilder();
		builder.append(employee.getId());
		builder.append("\n");
		builder.append(employee.getFirstName());
		builder.append("\n");
		builder.append(employee.getLastName());
		builder.append("\n");
		builder.append(employee.getPosition().getName());
		builder.append("\n");
		builder.append(
				employee.getBirthDate().format(new DateTimeFormatterFactory("yyyy-MM-dd").createDateTimeFormatter()));

		return builder.toString().contains(pattern);
	}
}
