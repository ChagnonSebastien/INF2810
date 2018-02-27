package ca.polymtl.log2810;

public enum Company {
	CHEAP_CAR(0.05, 0.07, 0.08),
	SUPER_CAR(0.03, 0.04, 0.06);
	
	private double carConsumation;
	private double pickUpConsumation;
	private double vanConsumation;
	
	Company(double carConsumation, double pickUpConsumation, double vanConsumation) {
		this.carConsumation = carConsumation;
		this.pickUpConsumation = pickUpConsumation;
		this.vanConsumation = vanConsumation;
	}
}
	 

