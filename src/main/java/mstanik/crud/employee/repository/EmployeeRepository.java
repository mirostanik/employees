package mstanik.crud.employee.repository;

import org.springframework.data.repository.CrudRepository;

import mstanik.crud.employee.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
