package ca.polymtl.log2810;

public class Graph {

	private City[] cities;
	private Road[] roads;
	
	public Graph() {
		
	}
	
	public City[] getCities() {
		return this.cities;
	}
	
	public Road[] getRoads() {
		return roads;
	}
	
	public void createCity(int id, boolean gazStation) throws Exception {
		if (this.getCity(id) != null)
			throw new Exception("A city with this id already exists.");
		
		this.cities[this.roads.length] = new City(id, gazStation);
	}
	
	public void createRoad(int length, int[] endCities) {
		this.roads[this.roads.length] = new Road(length, new City[]{this.getCity(endCities[0]), this.getCity(endCities[1])});
	}
	
	public City getCity(int id) {
		for (City city : this.cities)
			if (city.getID() == id)
				return city;
		
		return null;
	}
}
