package mstanik.crud.employees.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.vaadin.flow.component.grid.Grid;

import mstanik.crud.employee.filter.EmployeeFilterFactory;
import mstanik.crud.employee.model.Employee;
import mstanik.crud.employee.repository.EmployeeRepository;
import mstanik.crud.employee.ui.EmployeeUIFactory;
import mstanik.crud.ui.CrudUI;

public class MainViewTest {

	private EmployeeRepository employeeRepository;
	private EmployeeFilterFactory factory;
	private EmployeeUIFactory uiFactory;

	@BeforeEach
	public void setup() {
		employeeRepository = Mockito.mock(EmployeeRepository.class);
		uiFactory = Mockito.mock(EmployeeUIFactory.class);
		Mockito.when(uiFactory.createGrid()).thenReturn(new Grid<Employee>());
		factory = Mockito.mock(EmployeeFilterFactory.class);
	}

	@Test
	public void test() {
		new CrudUI<Employee>(uiFactory, factory, employeeRepository);
		Mockito.verify(employeeRepository, Mockito.only()).findAll();
		Mockito.verify(factory, Mockito.never()).create(Mockito.anyString());
	}

}
