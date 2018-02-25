package ca.polymtl.log2810;

public class City {

	private int id;;
	private String name;
	private boolean gazStation;
	
	City (int id, boolean gazStation) {
		this.id = id;
		
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
}
