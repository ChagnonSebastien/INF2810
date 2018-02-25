package ca.polymtl.log2810;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

	public static void main(String[] args) {
		
		try {
			System.out.println(GraphCreater.createGraph(new File("villes.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame("Escape the cops");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        frame.setSize(1000, 750);
        frame.setVisible(true);
	}

}
