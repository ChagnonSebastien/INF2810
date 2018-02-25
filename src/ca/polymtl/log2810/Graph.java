package ca.polymtl.log2810;

import java.util.List;

public class Graph {

	private List<City> cities;
	private List<Road> roads;
	
	public Graph() {
		
	}
	
	public List<City> getCities() {
		return this.cities;
	}
	
	public List<Road> getRoads() {
		return roads;
	}
	
	public void createCity(int id, int gazStation) throws Exception {
		if (this.getCity(id) != null)
			throw new Exception("A city with this id already exists.");
		
		this.cities.add(new City(id, gazStation > 0));
	}
	
	public void createRoad(Integer length, List<Integer> cityIds) {
		Road road = new Road(length, null);
		
		for (Integer id : cityIds)
			;
		
		this.roads.add(road);
	}
	
	public City getCity(int id) {
		for (City city : this.cities)
			if (city.getID() == id)
				return city;
		
		return null;
	}
}
