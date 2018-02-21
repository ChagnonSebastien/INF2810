package ca.polymtl.log2810;

public enum CityName {
	MONTREAL(1, "Montréal"),
	QUEBEC(2, "Québec"),
	OTTAWA(3, "Ottawa"),
	TORONTO(4, "Totonto"),
	HALIFAX(5, "Halifax"),
	SEPT_ILE(6, "Sept-Ile"),
	THUNDER_BAY(7, "Thunder Bay"),
	SANDY_LAKE(8, "Sandy Lake"),
	WINNIPEG(9, "Winnipeg"),
	REGINA(10, "Regina"),
	SASKATOON(11, "Saskatoon"),
	CALGARY(12, "Calgary"),
	VANCOUVER(13, "Vancouver"),
	EDMONTON(14, "Edmonton"),
	FORT_MCMURRAY(15, "Fort McMurray"),
	CHURCHILl(16, "Churchill"),
	PRINCE_GEORGE(17, "Prince George"),
	FORT_NELSON(18, "Fort Nelson"),
	WHITEHORSE(19, "Whitehorse"),
	YELLOWKNIFE(20, "Yellowknife");
	
	public int id;
	public String name;
	
	CityName(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static CityName getCityById(int id) throws ArrayIndexOutOfBoundsException {
		for (CityName city : CityName.values())
			if (city.id == id)
				return city;
		
		throw new ArrayIndexOutOfBoundsException("This city is not registered.");
	}
}
