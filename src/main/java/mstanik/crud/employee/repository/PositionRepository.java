package mstanik.crud.employee.repository;

import org.springframework.data.repository.CrudRepository;

import mstanik.crud.employee.model.Position;
 
public interface PositionRepository extends CrudRepository<Position, Long> {

}
