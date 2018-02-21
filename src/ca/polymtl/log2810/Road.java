package ca.polymtl.log2810;

public class Road {
		
	private int length;
	
	private City[] endCities;

	public Road(int length, City[] endCities) {
		this.length = length;
		this.endCities = endCities;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public City[] getEndCities() {
		return this.endCities;
	}
}
