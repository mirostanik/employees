package mstanik.employees.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import mstanik.employees.filter.EmployeeFilterFactory;
import mstanik.employees.repository.EmployeeRepository;
import mstanik.employees.repository.PositionRepository;

public class MainViewTest {

	private EmployeeRepository employeeRepository;
	private PositionRepository positionRepository;
	private EmployeeFilterFactory factory;

	@BeforeEach
	public void setup() {
		employeeRepository = Mockito.mock(EmployeeRepository.class);
		positionRepository = Mockito.mock(PositionRepository.class);
		factory = Mockito.mock(EmployeeFilterFactory.class);
	}

	@Test
	public void test() {
		MainView view = new MainView(employeeRepository, positionRepository, factory);
		Mockito.verify(employeeRepository, Mockito.only()).findAll();
		Mockito.verify(factory, Mockito.never()).create(Mockito.anyString());
	}

}
