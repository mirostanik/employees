package mstanik.employees.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import mstanik.employees.repository.PositionRepository;
import mstanik.employees.repository.PositionRepositoryREST;

@Component
public class PositionDataloader implements CommandLineRunner {

	private final PositionRepository positionRepository;
	private final PositionRepositoryREST restRepository;
	private static final Logger LOG = LoggerFactory.getLogger(PositionDataloader.class);

	public PositionDataloader(PositionRepository positionRepository, PositionRepositoryREST restRepository) {
		super();
		this.positionRepository = positionRepository;
		this.restRepository = restRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("Updating positions from IBILLBOARD");
		restRepository.findAll().forEach(p->{
			positionRepository.save(p);
		});
		
	}

}
