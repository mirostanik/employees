package mstanik.employees.repository;

import org.springframework.data.repository.CrudRepository;

import mstanik.employees.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
