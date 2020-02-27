package mstanik.employees.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import mstanik.employees.filter.Filter;
import mstanik.employees.filter.EmployeeFilterFactory;
import mstanik.employees.model.Employee;
import mstanik.employees.repository.EmployeeRepository;
import mstanik.employees.repository.PositionRepository;

@Route
@Component
public class MainView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final EmployeeRepository employeeRepository;
	private final PositionRepository positionRepository;
	private final Grid<Employee> grid = new Grid<>();
	private final TextField filterText = new TextField();
	private final Button addButton = new Button("Add");
	private final Button editButton = new Button("Edit");
	private final Button deleteButton = new Button("Delete");
	private final EmployeeFilterFactory filterFactory;

	public MainView(EmployeeRepository employeeRepository, PositionRepository positionRepository,
			EmployeeFilterFactory filterFactory) {
		this.employeeRepository = employeeRepository;
		this.positionRepository = positionRepository;
		this.filterFactory = filterFactory;

		initHeader();

		initFilter();

		initButtons();

		initGrid();

		setHeightFull();

		filterEmployees("", getEmployees().values());

	}

	private void initHeader() {
		H2 title = new H2("Employees");

		add(title);
	}

	private void initGrid() {
		grid.addColumn(Employee::getId).setHeader("ID");
		grid.addColumn(Employee::getFirstName).setHeader("First Name");
		grid.addColumn(Employee::getLastName).setHeader("Last Name");
		grid.addColumn(Employee::getBirthDate).setHeader("Birth Date");

		grid.addColumn(e -> e.getPosition().getName()).setHeader("Position");

		grid.addSelectionListener(e -> {
			editButton.setEnabled(!e.getAllSelectedItems().isEmpty());
			deleteButton.setEnabled(!e.getAllSelectedItems().isEmpty());
		});

		add(grid);
	}

	private void initButtons() {

		addButton.addClickListener(e -> {
			EmployeeEditor dialog = new EmployeeEditor(positionRepository, this::saveEmployee);

			dialog.open();
		});

		editButton.addClickListener(e -> {
			grid.getSelectedItems().stream().findFirst().ifPresent(employee -> {
				EmployeeEditor dialog = new EmployeeEditor(employee, positionRepository, this::saveEmployee);
				dialog.open();
			});
		});
		editButton.setEnabled(!grid.getSelectedItems().isEmpty());

		deleteButton.addClickListener(e -> {
			grid.getSelectedItems().stream().findFirst().ifPresent(employee -> {
				ConfirmDeleteDialog dialog = new ConfirmDeleteDialog(employee, this::deleteEmployee);
				dialog.open();
			});
		});
		deleteButton.setEnabled(!grid.getSelectedItems().isEmpty());

		HorizontalLayout actions = new HorizontalLayout(filterText, addButton, editButton, deleteButton);
		add(actions);
	}

	private void initFilter() {
		filterText.setClearButtonVisible(true);
		filterText.setPlaceholder("Find employee");
		filterText.setValueChangeMode(ValueChangeMode.EAGER);
		filterText.addValueChangeListener(e -> {
			filterEmployees(e.getValue(), getEmployees().values());
		});

		add(filterText);
	}

	private void filterEmployees(String value, Collection<Employee> employees) {
		if (value.trim().isEmpty()) {
			grid.setItems(employees);
		} else {
			Filter<Employee> filter = filterFactory.create(value);
			grid.setItems(employees.stream().filter(e -> {
				return filter.match(e);
			}));
		}
	}

	private Map<Long, Employee> getEmployees() {
		return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
				.collect(Collectors.toMap(Employee::getId, Function.identity()));
	}

	private void saveEmployee(Employee employee) {
		employeeRepository.save(employee);

		Map<Long, Employee> employees = getEmployees();
		filterEmployees(filterText.getValue(), employees.values());

		selectSavedEmployee(employee, employees);
	}

	// select and scroll to saved employee
	private void selectSavedEmployee(Employee employee, Map<Long, Employee> employees) {
		Optional.ofNullable(employees.get(employee.getId())).ifPresent(e -> {
			grid.select(e);
			@SuppressWarnings("unchecked")
			ListDataProvider<Employee> listDataProvider = (ListDataProvider<Employee>) grid.getDataProvider();
			ArrayList<Employee> items = new ArrayList<Employee>(listDataProvider.getItems());
			int index = items.indexOf(e);
			grid.scrollToIndex(index);
		});
	}

	private void deleteEmployee(Employee e) {
		employeeRepository.delete(e);
		filterEmployees(filterText.getValue(), getEmployees().values());
	}
}