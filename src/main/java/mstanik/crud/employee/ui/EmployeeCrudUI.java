package mstanik.crud.employee.ui;

import org.springframework.stereotype.Component;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

import mstanik.crud.employee.filter.EmployeeFilterFactory;
import mstanik.crud.employee.model.Employee;
import mstanik.crud.employee.repository.EmployeeRepository;
import mstanik.crud.ui.CrudUI;

@Route(value = "")
@Component
@UIScope
public class EmployeeCrudUI extends CrudUI<Employee> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeCrudUI(EmployeeUIFactory uiFactory, EmployeeFilterFactory filterFactory,
			EmployeeRepository employeeRepository) {
		super(uiFactory, filterFactory, employeeRepository);
	}

}
