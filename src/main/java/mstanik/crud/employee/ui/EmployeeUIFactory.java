package mstanik.crud.employee.ui;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;

import mstanik.crud.employee.model.Employee;
import mstanik.crud.employee.repository.PositionRepository;
import mstanik.crud.ui.factory.CrudUIFactory;

@Component
public class EmployeeUIFactory implements CrudUIFactory<Employee> {

	private final PositionRepository positionRepository;

	public EmployeeUIFactory(PositionRepository positionRepository) {
		super();
		this.positionRepository = positionRepository;
	}

	public Grid<Employee> createGrid() {
		Grid<Employee> grid = new Grid<>();

		grid.addColumn(Employee::getId).setHeader("ID");
		grid.addColumn(Employee::getFirstName).setHeader("First Name");
		grid.addColumn(Employee::getLastName).setHeader("Last Name");
		grid.addColumn(Employee::getBirthDate).setHeader("Birth Date");
		grid.addColumn(e -> e.getPosition().getName()).setHeader("Position");

		return grid;
	}

	@Override
	public Dialog createEditDialog(Employee entity, Consumer<Employee> saveConsumer) {
		return new EmployeeEditor(entity, positionRepository, saveConsumer);
	}

	@Override
	public Dialog createAddDialog(Consumer<Employee> saveConsumer) {
		return new EmployeeEditor(positionRepository, saveConsumer);
	}

	@Override
	public Dialog createConfirmDeleteDialog(Employee entity, Consumer<Employee> deleteConsumer) {
		return new ConfirmDeleteDialog(entity, deleteConsumer);
	}

	@Override
	public String getTitle() {
		return "Employees";
	}

	@Override
	public String getFilterText() {
		return "Find employee";
	}

}
