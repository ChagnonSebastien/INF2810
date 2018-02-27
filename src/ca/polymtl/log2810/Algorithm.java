package ca.polymtl.log2810;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {

	private static Route shortestRoute;
	private static Company companyUsed;
	
	public static Route shortestRoute(City from, City to, Vehicle vehicle) {
		List<Route> routes = new ArrayList<Route>();
		routes.add(new Route(from, to));
		
		for (Route route: routes) {
			
		}
		
		Algorithm.shortestRoute = routes.get(0);
		return Algorithm.shortestRoute;
	}
	
}
