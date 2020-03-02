package mstanik.crud.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.repository.CrudRepository;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;

import mstanik.crud.filter.Filter;
import mstanik.crud.filter.FilterFactory;
import mstanik.crud.model.IdentifiedEntity;
import mstanik.crud.ui.factory.CrudUIFactory;

public class CrudUI<T extends IdentifiedEntity> extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Grid<T> grid;
	private final TextField filterText = new TextField();
	private final Button addButton = new Button("Add");
	private final Button editButton = new Button("Edit");
	private final Button deleteButton = new Button("Delete");
	private final FilterFactory<T> filterFactory;
	private CrudUIFactory<T> uiFactory;
	private CrudRepository<T, ?> crudRepository;

	public CrudUI(CrudUIFactory<T> uiFactory, FilterFactory<T> filterFactory, CrudRepository<T, ?> crudRepository) {
		this.filterFactory = filterFactory;
		this.uiFactory = uiFactory;
		this.crudRepository = crudRepository;

		this.grid = uiFactory.createGrid();

		initHeader();

		initFilter();

		initButtons();

		initGrid();

		setHeightFull();

		filterEntities("", getEntities().values());

	}

	private void initHeader() {
		H2 title = new H2("Employees");

		add(title);
	}

	private void initGrid() {

		grid.addSelectionListener(e -> {
			editButton.setEnabled(!e.getAllSelectedItems().isEmpty());
			deleteButton.setEnabled(!e.getAllSelectedItems().isEmpty());
		});

		add(grid);
	}

	private void initButtons() {

		addButton.addClickListener(e -> {
			Dialog dialog = uiFactory.createAddDialog(this::saveEntity);

			dialog.open();
		});

		editButton.addClickListener(e -> {
			grid.getSelectedItems().stream().findFirst().ifPresent(entity -> {
				Dialog dialog = uiFactory.createEditDialog(entity, this::saveEntity);
				dialog.open();
			});
		});
		editButton.setEnabled(!grid.getSelectedItems().isEmpty());

		deleteButton.addClickListener(e -> {
			grid.getSelectedItems().stream().findFirst().ifPresent(entity -> {
				Dialog dialog = uiFactory.createConfirmDeleteDialog(entity, this::deleteEntity);
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
			filterEntities(e.getValue(), getEntities().values());
		});

		add(filterText);
	}

	private void filterEntities(String value, Collection<T> employees) {
		if (value.trim().isEmpty()) {
			grid.setItems(employees);
		} else {
			Filter<T> filter = filterFactory.create(value);
			grid.setItems(employees.stream().filter(e -> {
				return filter.match(e);
			}));
		}
	}

	private Map<Long, T> getEntities() {
		return StreamSupport.stream(crudRepository.findAll().spliterator(), false)
				.collect(Collectors.toMap(IdentifiedEntity::getId, Function.identity()));
	}

	private void saveEntity(T entity) {
		crudRepository.save(entity);

		Map<Long, T> entities = getEntities();
		filterEntities(filterText.getValue(), entities.values());

		selectSavedEntity(entity, entities);
	}

	// select and scroll to saved employee
	private void selectSavedEntity(T entity, Map<Long, T> entities) {
		Optional.ofNullable(entities.get(entity.getId())).ifPresent(e -> {
			grid.select(e);
			@SuppressWarnings("unchecked")
			ListDataProvider<IdentifiedEntity> listDataProvider = (ListDataProvider<IdentifiedEntity>) grid
					.getDataProvider();
			ArrayList<IdentifiedEntity> items = new ArrayList<IdentifiedEntity>(listDataProvider.getItems());
			int index = items.indexOf(e);
			grid.scrollToIndex(index);
		});
	}

	private void deleteEntity(T e) {
		crudRepository.delete(e);
		filterEntities(filterText.getValue(), getEntities().values());
	}
}