package ca.polymtl.log2810;

public enum CityName {
	MONTREAL(1, "Montréal", 328/450.0, 254/300.0),
	QUEBEC(2, "Québec", 340/450.0, 240/300.0),
	OTTAWA(3, "Ottawa", 312/450.0, 265/300.0),
	TORONTO(4, "Totonto", 293/450.0, 282/300.0),
	HALIFAX(5, "Halifax", 385/450.0, 254/300.0),
	SEPT_ILE(6, "Sept-Ile", 357/450.0, 211/300.0),
	THUNDER_BAY(7, "Thunder Bay", 236/450.0, 245/300.0),
	SANDY_LAKE(8, "Sandy Lake", 213/450.0, 204/300.0),
	WINNIPEG(9, "Winnipeg", 186/450.0, 229/300.0),
	REGINA(10, "Regina", 151/450.0, 222/300.0),
	SASKATOON(11, "Saskatoon", 135/450.0, 201/300.0),
	CALGARY(12, "Calgary", 103/450.0, 205/300.0),
	VANCOUVER(13, "Vancouver", 49/450.0, 204/300.0),
	EDMONTON(14, "Edmonton", 110/450.0, 177/300.0),
	FORT_MCMURRAY(15, "Fort McMurray", 129/450.0, 146/300.0),
	CHURCHILl(16, "Churchill", 210/450.0, 153/300.0),
	PRINCE_GEORGE(17, "Prince George", 61/450.0, 165/300.0),
	FORT_NELSON(18, "Fort Nelson", 83/450.0, 119/300.0),
	WHITEHORSE(19, "Whitehorse", 36/450.0, 81/300.0),
	YELLOWKNIFE(20, "Yellowknife", 126/450.0, 105/300.0);
	
	public final int id;
	public final String name;
	public final double ratioWidth;
	public final double ratioHeight;
	
	CityName(int id, String name, double ratioWidth, double ratioHeight) {
		this.id = id;
		this.name = name;
		this.ratioHeight = ratioHeight;
		this.ratioWidth = ratioWidth;
	}
	
	public static CityName getCityById(int id) throws ArrayIndexOutOfBoundsException {
		for (CityName city : CityName.values())
			if (city.id == id)
				return city;
		
		throw new ArrayIndexOutOfBoundsException("This city is not registered.");
	}
}
