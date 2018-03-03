package ca.polymtl.log2810;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.polymtl.log2810.Road.CityIsNotOnRoadException;
import ca.polymtl.log2810.Route.GazThresholdLevelException;
import ca.polymtl.log2810.Route.NotSameCityException;

public class Algorithm {

	private Graph graph;
	private Vehicle vehicleUsed;
	private City from;
	private City destination;

	public Algorithm(Graph graph, Vehicle vehicleUsed, City from, City destination) {
		this.graph = graph;
		this.vehicleUsed = vehicleUsed;
		this.from = from;
		this.destination = destination;
	}

	public Route shortestRoute() {
		Route shortestRoute = this.modifiedDijistra(Company.CHEAP_CAR);

		if (shortestRoute == null)
			shortestRoute = this.modifiedDijistra(Company.SUPER_CAR);

		return shortestRoute;
	}

	private Route modifiedDijistra(Company company) {
		System.out.println("Starting Algorithm with company " + company.name());
		HashMap<Integer, List<Route>> possibilities = initialize(company);

		Route shortestRoute;
		while ((shortestRoute = this.getShortestRoute(possibilities)) != null) {
			if (shortestRoute.getLast() == this.destination) {
				break;
			}

			outer: for (Route newPossibility : this.getSubRoutes(shortestRoute)) {
				List<Route> obsoleteRoutes = new ArrayList<Route>();
				for (Route olderRoute : possibilities.get(newPossibility.getLast().getID())) {
					try {
						if (newPossibility.isWorstThan(olderRoute)) {
							continue outer;
						}
						if (olderRoute.isWorstThan(newPossibility))
							obsoleteRoutes.add(olderRoute);
					} catch (NotSameCityException e) {
						System.err.println("A route with the last city being : " + olderRoute.getLast().toString()
								+ " is stored in the wrong table.");
					}
				}
				possibilities.get(newPossibility.getLast().getID()).add(newPossibility);
				possibilities.get(newPossibility.getLast().getID()).removeAll(obsoleteRoutes);
			}
		}

		return shortestRoute;
	}

	private List<Route> getSubRoutes(Route shortestRoute) {
		ArrayList<Route> subRoutes = new ArrayList<Route>();

		for (Road road : shortestRoute.getLast().allConnectedRoads()) {
			try {
				subRoutes.add(new Route(shortestRoute, road, vehicleUsed));
			} catch (CityIsNotOnRoadException e) {
				System.err.println("A city : " + this.toString() + " is linked to a road : " + road.toString()
						+ ", while the road is not connected to the city.");
			} catch (GazThresholdLevelException e) {
				continue;
			}
		}

		shortestRoute.markExpanded();
		return subRoutes;
	}

	private Route getShortestRoute(HashMap<Integer, List<Route>> possibilities) {
		double routeTime = Double.MAX_VALUE;
		Route shortestRoute = null;
		
		for (List<Route> cityRoutes : possibilities.values()) {
			for (Route route : cityRoutes) {
				if (!route.isExpanded() && routeTime > route.getTravelTime()) {
					routeTime = route.getTravelTime();
					shortestRoute = route;
				}
			}
		}

		return shortestRoute;
	}

	private HashMap<Integer, List<Route>> initialize(Company company) {
		HashMap<Integer, List<Route>> map = new HashMap<Integer, List<Route>>();

		for (City city : graph.getCities())
			map.put(city.getID(), new ArrayList<Route>());

		map.get(from.getID()).add(new Route(from, destination, company));
		return map;
	}
}
