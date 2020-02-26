package mstanik.employees.ui;

import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.data.util.StreamUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;

import mstanik.employees.model.Employee;
import mstanik.employees.model.Position;
import mstanik.employees.repository.EmployeeRepository;
import mstanik.employees.repository.PositionRepository;

 
public class EmployeeEditor extends Dialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	
	private final TextField firstName = new TextField("First Name");
	private final TextField lastName = new TextField("Last Name");
	private ComboBox<Position> position = new ComboBox<>("Position");
	private DatePicker birthDate = new DatePicker("Birth Date");
	private Binder<Employee> binder = new Binder<Employee>(Employee.class); 
	private final Button okButton = new Button("OK");
	private final Button cancelButton = new Button("Cancel");
	
	private final FormLayout layout = new FormLayout();

	public EmployeeEditor(String title, Employee employee, EmployeeRepository employeeRepository,
			PositionRepository positionRepository, Consumer<Employee> consumer) {
		super(); 
		
		layout.setWidth(UIConstants.DIALOG_WIDTH);
		 layout.setResponsiveSteps(
			 new FormLayout.ResponsiveStep(UIConstants.DIALOG_WIDTH, 1, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));

		H2 h2 = new H2(title);
		layout.add(h2);
		
		initForm(employee, employeeRepository, positionRepository, consumer);
	}

	private void initForm( Employee employee, EmployeeRepository employeeRepository,
			PositionRepository positionRepository, Consumer<Employee> consumer) {

		firstName 
		 .setValueChangeMode(ValueChangeMode.EAGER);
		firstName.setRequired(true);
		layout.add(firstName); 
		binder.forField(firstName).asRequired("First name cannot be empty!") 
		.bind(Employee::getFirstName, Employee::setFirstName); 
		
		lastName.setValueChangeMode(ValueChangeMode.EAGER);
		layout.add(lastName );
		binder.forField(lastName).asRequired("Last name cannot be empty!") 
		.bind(Employee::getLastName, Employee::setLastName); 

		position.setPreventInvalidInput(true);
		position.setRequired(true);
		layout.add(position);
		binder.forField(position).asRequired("Position must be set!") 
		.bind(Employee::getPosition, Employee::setPosition);
		position.setItems(StreamUtils.createStreamFromIterator(positionRepository.findAll().iterator())
				.collect(Collectors.toList()));

		birthDate.setRequired(true);  
		layout.add(birthDate);
		binder.forField(birthDate).asRequired("Birth date is not valid or empty!").bind(Employee::getBirthDate, Employee::setBirthDate);

		HorizontalLayout buttonBar = new HorizontalLayout();
		buttonBar.add(okButton);
		okButton.setEnabled(!firstName.isEmpty());
		okButton.addClickListener(e->{

			close();
			if(binder.writeBeanIfValid(employee)) {
				close();
				if(binder.isValid()) {
					consumer.accept(employee);
				}
			}
			
		});
		
		cancelButton.addClickListener(e->{
			close(); 
		});

		buttonBar.add(cancelButton);
		
		layout.add(buttonBar);
 
		binder.setBean(employee); 
 
		binder.addStatusChangeListener(l->{
			okButton.setEnabled(binder.isValid() && !l.hasValidationErrors());
		}); 
		
		add(layout);
	}

}
