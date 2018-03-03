package ca.polymtl.log2810;

public enum Company {
	CHEAP_CAR(0.05, 0.07, 0.08),
	SUPER_CAR(0.03, 0.04, 0.06);
	
	public final double carConsumption;
	public final double pickUpConsumption;
	public final double vanConsumption;
	
	Company(double carConsumption, double pickUpConsumption, double vanConsumption) {
		this.carConsumption = carConsumption;
		this.pickUpConsumption = pickUpConsumption;
		this.vanConsumption = vanConsumption;
	}

	public double getConsumption(Vehicle vehicle, int length) throws NullPointerException {
		switch (vehicle) {
		case CAR:
			return this.carConsumption * length;
		case PICK_UP:
			return this.pickUpConsumption * length;
		case VAN:
			return this.vanConsumption * length;
		default:
			throw new NullPointerException();
		}
	}
}
	 

