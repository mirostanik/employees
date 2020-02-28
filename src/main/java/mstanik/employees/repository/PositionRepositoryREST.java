package mstanik.employees.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mstanik.employees.model.Position;
import mstanik.employees.model.Positions;

@Component
public class PositionRepositoryREST {

	private Client client;
	private WebTarget target;

	@PostConstruct
	protected void init() {
		client = ClientBuilder.newClient();
		target = client.target("http://ibillboard.com/api/positions");
	}

	public Collection<Position> findAll() {
		
		Positions positions;
		try {
			positions = new ObjectMapper().readValue(target.request(MediaType.APPLICATION_JSON).get(String.class),
					Positions.class);
			AtomicLong count = new AtomicLong(0L);
			return positions.getPositions().stream().map(s -> {
				Position p = new Position();
				p.setId(count.incrementAndGet());
				p.setName(s);
				return p;
			}).collect(Collectors.toList());
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return new ArrayList<Position>();
	}
}
