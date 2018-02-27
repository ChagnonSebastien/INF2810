package ca.polymtl.log2810;

import java.util.LinkedList;
import java.util.List;

public class Route {
	
	private List<Pair<City, Double>> path;
	private City destination;
	
	public Route(City from, City to) {
		this.destination = to;
		this.path = new LinkedList<Pair<City, Double>>();
		path.add(new Pair<City, Double>(from, 1.0));
	}
}
