package mstanik.crud;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import mstanik.crud.employee.filter.EmployeeFilterFactory;
import mstanik.crud.employee.repository.EmployeeRepository;
import mstanik.crud.employee.repository.PositionRepository;

@SpringBootTest
class ApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		assertNotNull(context.getBean(EmployeeRepository.class));
		assertNotNull(context.getBean(PositionRepository.class));
		assertNotNull(context.getBean(EmployeeFilterFactory.class).create("")); 
	}
	
}
