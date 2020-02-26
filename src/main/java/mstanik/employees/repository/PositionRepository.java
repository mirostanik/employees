package mstanik.employees.repository;

import org.springframework.data.repository.CrudRepository;

import mstanik.employees.model.Position;

public interface PositionRepository extends CrudRepository<Position, Long> {

}
