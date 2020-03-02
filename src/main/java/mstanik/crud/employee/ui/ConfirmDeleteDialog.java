package mstanik.crud.employee.ui;

import java.util.function.Consumer;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import mstanik.crud.employee.model.Employee;
import mstanik.crud.ui.UIConstants;

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
