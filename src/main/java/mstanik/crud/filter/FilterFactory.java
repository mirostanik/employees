package mstanik.crud.filter;

public interface FilterFactory<T> {
	Filter<T> create(String pattern);
}
