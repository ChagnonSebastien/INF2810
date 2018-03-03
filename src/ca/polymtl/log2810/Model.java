package ca.polymtl.log2810;

import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.swing.JOptionPane;

public class Model extends Observable {

	private Graph graph;

	public Model() {
		
	}

	public void creerGraphe(File selectedFile) {
		try {
			this.graph = GraphCreater.createGraph(selectedFile);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error while reading file.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public Graph getGraph() {
		return graph;
	}

	public void shortestPath(City from, City to, Vehicle vehicle) {
		Algorithm algorithm = new Algorithm(graph, vehicle, from, to);
		System.out.println(algorithm.shortestRoute());
	}
	
}
