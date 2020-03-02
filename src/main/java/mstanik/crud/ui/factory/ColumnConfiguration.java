package mstanik.crud.ui.factory;

import com.vaadin.flow.function.ValueProvider;

public class ColumnConfiguration {

	private final ValueProvider<?, ?> valueProvider;
	private final String label;

	public ColumnConfiguration(ValueProvider<?, ?> valueProvider, String label) {
		this.valueProvider = valueProvider;
		this.label = label;
	}

	public ValueProvider<?, ?> getValueProvider() {
		return valueProvider;
	}

	public String getLabel() {
		return label;
	}

}
