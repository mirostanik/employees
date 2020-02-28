package mstanik.employees.ui;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.vaadin.flow.component.UI;

import mstanik.employees.repository.PositionRepository;

public class EmployeeEditorTest {

	@Test
	public void test() {

		PositionRepository positionRepository = Mockito.mock(PositionRepository.class);
		UI.setCurrent(new UI());
		new EmployeeEditor(positionRepository, e -> {
		});
		Mockito.verify(positionRepository, Mockito.only()).findAll();
	}

}
