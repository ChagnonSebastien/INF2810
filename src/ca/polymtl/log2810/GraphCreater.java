package ca.polymtl.log2810;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphCreater {

	private static enum ReadMode {
		VERTICE, EDGE
	}

	public static Graph createGraph(File file) throws FileNotFoundException {
		Graph graph = new Graph();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		ReadMode mode = ReadMode.VERTICE;
		
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.length() == 0) {
					mode = ReadMode.EDGE;
					continue;
				}
				List<Integer> args;
				
				try {
					args = GraphCreater.parseArgs(line);
				} catch (NumberFormatException e) {
					System.out.printf("WARNING: Can't parse int in line: %n. Line ignored.\n", line);
					continue;
				}
					
					
				switch (mode) {
				case VERTICE:
					try {
						graph.createCity(args.get(0), args.get(1));
					} catch (Exception e) {
						System.out.printf("WARNING: Two cities in the file selected have the same id: %n. Second one ignored.\n", args.get(0));
					}
					break;
					
				case EDGE:
					graph.createRoad(args.get(0), args.subList(1, 2));
					break;
				}
				
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		do {
			
		} while (false);
		
		return graph;
	}

	private static List<Integer> parseArgs(String line) throws NumberFormatException {
		List<Integer> args = new ArrayList<Integer>();
		
		for (String arg: line.split(","))
			args.add(Integer.parseInt(arg));
		
		return args;
	}

}
