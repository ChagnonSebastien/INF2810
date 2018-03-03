package ca.polymtl.log2810;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	private List<City> cities;
	private List<Road> roads;

	public Graph() {
		this.cities = new ArrayList<City>();
		this.roads = new ArrayList<Road>();
	}

	public List<City> getCities() {
		return cities;
	}

	public void createCity(int id, int gazStation) throws Exception {
		if (this.getCity(id) != null)
			throw new Exception("A city with this id already exists.");

		this.cities.add(new City(id, gazStation > 0));
	}

	public void createRoad(Integer length, List<Integer> cityIds) {
		Road road = new Road(length);

		for (Integer id : cityIds)
			road.addCity(this.getCity(id));

		this.roads.add(road);
	}

	public City getCity(int id) {
		for (City city : this.cities)
			if (city.getID() == id)
				return city;

		return null;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("Graph: (\n");
		for (City city : this.cities)
			builder.append("\t" + city.toString() + "\n");
		for (Road road : this.roads)
			builder.append("\t" + road.toString() + "\n");
		builder.append(")");
		return builder.toString();
	}

	public List<Road> getRoads() {
		return roads;
	}
}
