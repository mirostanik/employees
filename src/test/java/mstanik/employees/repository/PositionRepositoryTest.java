package mstanik.employees.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootTest
public class PositionRepositoryTest {

	@Autowired
	private PositionRepositoryREST repository;

	@Test
	public void testDeserialization() throws JsonMappingException, JsonProcessingException {
		assertFalse(repository.findAll().isEmpty());
	}
}
