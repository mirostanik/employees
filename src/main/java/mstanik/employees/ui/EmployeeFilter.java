package mstanik.employees.ui;

import org.springframework.format.datetime.standard.DateTimeFormatterFactory;

import mstanik.employees.model.Employee;

public class EmployeeFilter {

	private final String pattern;

	public EmployeeFilter(String pattern) {
		this.pattern = pattern;
	}

	public boolean match(Employee employee) {
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
