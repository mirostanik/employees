package mstanik.employees.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

import mstanik.employees.model.Position;
 
public interface PositionRepository extends CrudRepository<Position, Long> {

}
