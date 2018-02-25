package ca.polymtl.log2810;

import java.util.ArrayList;
import java.util.List;

public class Road {
		
	private int length;
	
	private List<City> endCities;

	public Road(int length) {
		this.endCities = new ArrayList<City>();
		this.length = length;
	}
	
	public List<City> addCity(City city) throws IndexOutOfBoundsException {
		if (this.endCities.size() >= 2)
			throw new IndexOutOfBoundsException("This road already has two end cities.");
		
		city.addRoad(this);
		this.endCities.add(city);
		return this.endCities;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public List<City> getEndCities() {
		return this.endCities;
	}
	
	public String toString() {
		return "Road (ends: " + this.endCities.toString() + ", length: " + this.length + ")";
	}
}
