package mstanik.employees.ui;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vaadin.flow.component.UI;

import mstanik.employees.repository.PositionRepository;

@SpringBootTest
public class EmployeeEditorTest {

	@Autowired
	private PositionRepository  positionRepository;

	@Test
	public void test() {
		UI.setCurrent(new UI());
		new EmployeeEditor(positionRepository, e -> {
		});
		Mockito.verify(positionRepository, Mockito.only()).findAll();
	}

}
