package ca.polymtl.log2810;

public class Road {
		
	private int length;
	
	private City[] endCities;

	public Road(int length) {
		this.length = length;
	}
	
	public City[] addCity(City city) {
		if (this.endCities.length >= 2)
			throw new ArrayIndexOutOfBoundsException("This road already has two end cities.");
		
		this.endCities[this.endCities.length] = city;
		return this.endCities;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public City[] getEndCities() {
		return this.endCities;
	}
}
