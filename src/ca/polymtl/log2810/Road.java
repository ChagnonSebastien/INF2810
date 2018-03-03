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

	public String toString() {
		return "Road (ends: [" + this.endCities.get(0).getName() + ", " + this.endCities.get(1).getName()
				+ "], length: " + this.length + ")";
	}

	public City getOtherEnd(City city) throws CityIsNotOnRoadException {
		int index = endCities.indexOf(city);
		if (index == -1)
			throw new CityIsNotOnRoadException();
		return endCities.get(index == 0 ? 1 : 0);
	}

	public class CityIsNotOnRoadException extends Exception {
		private static final long serialVersionUID = 2373344479349407721L;
	}

	public List<City> getEnds() {
		return endCities;
	}
}
