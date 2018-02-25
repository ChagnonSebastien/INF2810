package ca.polymtl.log2810;

import java.util.ArrayList;
import java.util.List;

public class City {

	private int id;;
	private String name;
	private boolean gazStation;
	private List<Road> outgoingRoads;
	
	City (int id, boolean gazStation) {
		this.id = id;
		this.gazStation = gazStation;
		this.outgoingRoads = new ArrayList<Road>();
		
		try {
			this.name = CityName.getCityById(id).name;
		} catch (IndexOutOfBoundsException e) {
			this.name = String.valueOf(id);
		}
	}
	
	public boolean hasGazStation () {
		return this.gazStation;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String toString() {
		return "City (id: " + this.id + ", name: " + this.name + ")";
	}

	public void addRoad(Road road) {
		this.outgoingRoads.add(road);
	}
}
