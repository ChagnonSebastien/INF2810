package ca.polymtl.log2810;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class Controller implements ActionListener {

	public static final String LOAD_GRAPH_ACTION_COMMAND = "load";
	public static final String SHORTEST_ROUTE_ACTION_COMMAND = "route";
	public static final String QUIT_ACTION_COMMAND = "quit";
	
	private View view;
	private Model model;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case LOAD_GRAPH_ACTION_COMMAND:
			view.chooseFile(this);
			break;

		case SHORTEST_ROUTE_ACTION_COMMAND:
			List<City> cities = model.getGraph().getCities();
			view.chooseRouteOptions(this, cities.toArray(new City[cities.size()]));
			break;

		case QUIT_ACTION_COMMAND:
			System.exit(0);
			break;

		default:
			break;
		}
	}

	public void loadGraph(File selectedFile) {
		model.creerGraphe(selectedFile);
	}

	public void shortestPath(City from, City to, Vehicle vehicle) {
		model.shortestPath(from, to, vehicle);
	}

}
