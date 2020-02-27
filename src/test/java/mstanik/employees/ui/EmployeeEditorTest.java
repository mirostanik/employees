package mstanik.employees.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.vaadin.flow.component.UI;

import mstanik.employees.repository.PositionRepository;

 
public class EmployeeEditorTest {

	private PositionRepository positionRepository;

	@BeforeEach
	public void setup() {
		positionRepository = Mockito.mock(PositionRepository.class);
	}

	@Test
	public void test() {
		UI.setCurrent(new UI());
		new EmployeeEditor(positionRepository, e -> {
		});
		Mockito.verify(positionRepository, Mockito.only()).findAll();
	}

}
