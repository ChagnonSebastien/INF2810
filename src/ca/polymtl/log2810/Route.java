package ca.polymtl.log2810;

import java.util.LinkedList;

import ca.polymtl.log2810.Road.CityIsNotOnRoadException;

public class Route extends LinkedList<City> {

	private static final long serialVersionUID = 6934253983742606349L;

	private double travelTime;
	private double gazRemaining;
	private Company company;
	private boolean expanded;
	private City destination;

	public Route(City from, City to, Company company) {
		super();
		this.destination = to;
		this.gazRemaining = 1;
		this.travelTime = 0;
		this.company = company;
		this.add(from);
		this.expanded = false;
	}

	public Route(Route previousRoute, Road newSegment, Vehicle vehicle) throws CityIsNotOnRoadException, GazThresholdLevelException {
		super(previousRoute);
		this.destination = previousRoute.destination;
		this.company = previousRoute.company;
		
		City goingTo = newSegment.getOtherEnd(previousRoute.getLast());
		this.add(goingTo);
		this.travelTime = previousRoute.travelTime + newSegment.getLength();
		this.gazRemaining = previousRoute.gazRemaining - company.getConsumption(vehicle, newSegment.getLength());
		if (this.gazRemaining < 0.2) throw new GazThresholdLevelException();
		
		if (goingTo.hasGazStation()) {
			this.travelTime += goingTo == this.destination ? 0 : 0.25;
			this.gazRemaining = 1;
		}
		
	}
	
	public double getTravelTime() {
		return this.travelTime;
	}

	public boolean isWorstThan(Route toCompare) throws NotSameCityException {
		if (this.getLast() != toCompare.getLast()) throw new NotSameCityException();
		return this.gazRemaining <= toCompare.gazRemaining && this.travelTime >= toCompare.gazRemaining;
	}

	public boolean isExpanded() {
		return this.expanded;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("{\n");
		for (City city : this) {
			builder.append(city.toString() + "\n");
		}
		builder.append("}\nTime: " + this.travelTime + "\nGaz Remaining: " + this.gazRemaining + "\nCompany: " + this.company.name() + "\n");
		return builder.toString();
	}

	public class NotSameCityException extends Exception {
		private static final long serialVersionUID = 8388684865128364834L;
	}

	public class GazThresholdLevelException extends Exception {
		private static final long serialVersionUID = 2811579095157966032L;
	}

	public void markExpanded() {
		this.expanded = true;
	}
}
