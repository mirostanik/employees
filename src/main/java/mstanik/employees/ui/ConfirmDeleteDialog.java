package mstanik.employees.ui;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.convert.Jsr310Converters.StringToLocalDateConverter;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDateConverter;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.spring.annotation.UIScope;

import mstanik.employees.model.Employee;
import mstanik.employees.model.Position;
import mstanik.employees.repository.EmployeeRepository;
import mstanik.employees.repository.PositionRepository;

public class ConfirmDeleteDialog extends Dialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Button yesButton = new Button("Yes");
	private final Button noButton = new Button("No");

	private final VerticalLayout layout = new VerticalLayout();

	public ConfirmDeleteDialog(Employee employee, Consumer<Employee> function) {
		super();

		layout.setWidth(UIConstants.DIALOG_WIDTH);

		H2 h2 = new H2("Delete employee");
		layout.add(h2);

		Label label = new Label(String.format("Do you want to delete employee %s %s?", employee.getFirstName(),
				employee.getLastName()));
		layout.add(label);
		HorizontalLayout buttonBar = new HorizontalLayout();
		layout.add(buttonBar);

		yesButton.addClickListener(e -> {
			close();
			function.accept(employee);
		});
		buttonBar.add(yesButton);

		noButton.addClickListener(e -> {
			close();
		});
		buttonBar.add(noButton);

		add(layout);

	}

}
