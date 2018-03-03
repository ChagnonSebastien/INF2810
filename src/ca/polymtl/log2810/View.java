package ca.polymtl.log2810;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class View implements Observer {

	private JButton quitButton;
	private JButton shortestRoute;
	private JButton loadButton;

	private JList<String> shortestPathList;
	private JList<String> graphList;

	public View() {
		JFrame frame = new JFrame("Escape the cops");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container mainPanel = frame.getContentPane();
		mainPanel.setLayout(new BorderLayout(10, 100));

		JPanel leftPane = new JPanel(new GridBagLayout());
		GridBagConstraints gbcq = new GridBagConstraints();
		gbcq.gridx = 0;
		gbcq.insets = new Insets(5, 10, 5, 10);
		gbcq.fill = GridBagConstraints.HORIZONTAL;
		leftPane.setBackground(Color.WHITE);
		JLabel label = new JLabel("Cities and Roads List");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 30));
		gbcq.gridy = 0;
		leftPane.add(label, gbcq);

		this.graphList = new JList<String>();
		graphList.setListData(new String[0]);
		gbcq.fill = GridBagConstraints.BOTH;
		gbcq.weighty = 1;
		gbcq.gridy = 1;
		leftPane.add(graphList, gbcq);

		mainPanel.add(leftPane, BorderLayout.LINE_START);

		JPanel mapPanel = new JPanel() {
			private static final long serialVersionUID = -4869689506583232672L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image image = Toolkit.getDefaultToolkit().getImage("canada-Map.png");

				double windowRatio = (double) this.getSize().getWidth() / (double) this.getSize().getHeight();
				double imageRatio = (double) image.getWidth(this) / (double) image.getHeight(this);

				int xPosition = 0;
				int yPosition = 0;
				int width = (int) this.getSize().getWidth();
				int height = (int) this.getSize().getHeight();
				if (windowRatio <= imageRatio) {
					yPosition = (int) (height - (height / imageRatio * windowRatio)) / 2;
					height = (int) (width / imageRatio);
				} else {
					xPosition = (int) (width - (width * imageRatio / windowRatio)) / 2;
					width = (int) (height * imageRatio);
				}

				g.drawImage(image, xPosition, yPosition, width, height, Color.WHITE, this);
			}
		};

		mapPanel.setBackground(Color.WHITE);
		mainPanel.add(mapPanel, BorderLayout.CENTER);

		JPanel rightPanel = new JPanel(new GridBagLayout());
		rightPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.insets = new Insets(5, 10, 5, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		Dimension buttonSize = new Dimension(220, 50);
		Font buttonFont = new Font("Arial", Font.PLAIN, 20);

		this.loadButton = new JButton("Load Graph");
		loadButton.setActionCommand(Controller.LOAD_GRAPH_ACTION_COMMAND);
		loadButton.setFont(buttonFont);
		loadButton.setMinimumSize(buttonSize);
		loadButton.setPreferredSize(buttonSize);
		gbc.gridy = 0;
		rightPanel.add(loadButton, gbc);

		this.shortestRoute = new JButton("Shortest Route");
		this.shortestRoute.setActionCommand(Controller.SHORTEST_ROUTE_ACTION_COMMAND);
		shortestRoute.setFont(buttonFont);
		shortestRoute.setMinimumSize(buttonSize);
		shortestRoute.setPreferredSize(buttonSize);
		gbc.gridy = 1;
		rightPanel.add(shortestRoute, gbc);

		this.quitButton = new JButton("Quit");
		quitButton.setActionCommand(Controller.QUIT_ACTION_COMMAND);
		quitButton.setFont(buttonFont);
		quitButton.setMinimumSize(buttonSize);
		quitButton.setPreferredSize(buttonSize);
		gbc.gridy = 2;
		rightPanel.add(quitButton, gbc);

		gbc.gridy = 3;
		rightPanel.add(new JSeparator(), gbc);

		JLabel shortestPathListTitle = new JLabel("Shortest Path");
		shortestPathListTitle.setHorizontalAlignment(JLabel.CENTER);
		shortestPathListTitle.setFont(new Font("Arial", Font.BOLD, 30));
		gbc.gridy = 4;
		rightPanel.add(shortestPathListTitle, gbc);

		this.shortestPathList = new JList<String>();
		shortestPathList.setListData(new String[0]);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1;
		gbc.gridy = 5;
		rightPanel.add(shortestPathList, gbc);

		mainPanel.add(rightPanel, BorderLayout.LINE_END);

		frame.setBounds(0, 0, 1900, 1000);
		frame.setVisible(true);

	}

	@Override
	public void update(Observable observable, Object object) {

	}

	public void addController(Controller controller) {
		this.loadButton.addActionListener(controller);
		this.quitButton.addActionListener(controller);
		this.shortestRoute.addActionListener(controller);
	}

	public void chooseFile(Controller controller) {
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
		fileChooser.showDialog(this.loadButton, "Select File");
		controller.loadGraph(fileChooser.getSelectedFile());
	}

	public void chooseRouteOptions(Controller controller, City[] cities) {
		City from = (City) JOptionPane.showInputDialog(null, "In which city do you want to rob the bank?", "From city",
				JOptionPane.OK_OPTION, new ImageIcon("bank.png"), cities, null);
		City to = (City) JOptionPane.showInputDialog(null, "In which city do you want to hide?", "To city",
				JOptionPane.OK_OPTION, new ImageIcon("mask.png"), cities, null);
		Vehicle vehicle = (Vehicle) JOptionPane.showInputDialog(null, "What vehicle do you want to use?", "Vehicle",
				JOptionPane.OK_OPTION, new ImageIcon("taxi.png"), Vehicle.values(), null);
		controller.shortestPath(from, to, vehicle);
	}

}
