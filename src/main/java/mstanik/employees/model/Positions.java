package mstanik.employees.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
 
public class Positions {

	@JsonProperty("positions")
	private List<String> positions = new ArrayList<String>(); 

	@JsonProperty("positions")
	public List<String> getPositions() {
		return positions;
	}

	@JsonProperty("positions")
	public void setPositions(List<String> positions) {
		this.positions = positions;
	}
}