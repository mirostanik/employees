package mstanik.crud.filter;

public interface Filter<T> {

	boolean match(T entity);
}
