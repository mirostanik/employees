package mstanik.crud.ui.factory;

import java.util.function.Consumer;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;

import mstanik.crud.model.IdentifiedEntity;

public interface CrudUIFactory<T extends IdentifiedEntity> {

	Grid<T> createGrid();

	Dialog createEditDialog(T entity, Consumer<T> saveConsumer);

	Dialog createAddDialog(Consumer<T> saveConsumer);

	Dialog createConfirmDeleteDialog(T entity, Consumer<T> deleteConsumer);
	
	String getTitle();
	
	String getFilterText();
}
