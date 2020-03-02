package mstanik.crud.employees.ui;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.vaadin.flow.component.UI;

import mstanik.crud.employee.repository.PositionRepository;
import mstanik.crud.employee.ui.EmployeeEditor;

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
