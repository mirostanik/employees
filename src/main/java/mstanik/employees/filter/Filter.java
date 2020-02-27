package mstanik.employees.filter;

public interface Filter<T> {
	
	boolean match(T employee);
}
