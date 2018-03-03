package ca.polymtl.log2810;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

public class View implements Observer {

	private final Model model;

	private JButton quitButton;
	private JButton shortestRoute;
	private JButton loadButton;

	private JList<String> shortestPathList;
	private JList<String> graphList;

	private JPanel mapPanel;

	public View(Model model) {
		this.model = model;
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
		label.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		gbcq.gridy = 0;
		leftPane.add(label, gbcq);

		JScrollPane scrollPane = new JScrollPane();
		this.graphList = new JList<String>();
		graphList.setListData(new String[0]);
		scrollPane.setViewportView(graphList);
		gbcq.fill = GridBagConstraints.BOTH;
		gbcq.weighty = 1;
		gbcq.gridy = 1;
		leftPane.add(scrollPane, gbcq);

		mainPanel.add(leftPane, BorderLayout.LINE_START);

		this.mapPanel = new JPanel() {
			private static final long serialVersionUID = -4869689506583232672L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image image = Toolkit.getDefaultToolkit()
						.getImage(Main.class.getResource("/ressources/canada-map.png"));

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

				Graphics2D g2d = (Graphics2D) g;
				g2d.drawImage(image, xPosition, yPosition, width, height, Color.WHITE, this);
				if (model.getGraph() != null) {
					for (City city : model.getGraph().getCities()) {
						g2d.setStroke(new BasicStroke(3));
						g2d.fillOval(
								(int) (xPosition + width * CityName.getCityById(city.getID()).ratioWidth
										- 0.015 * width / 2),
								(int) (yPosition + height * CityName.getCityById(city.getID()).ratioHeight
										- 0.015 * width / 2),
								(int) (0.015 * width), (int) (0.015 * width));
					}
					for (Road road : model.getGraph().getRoads()) {
						City c1 = road.getEnds().get(0);
						City c2 = road.getEnds().get(1);

						g2d.drawLine((int) (xPosition + width * CityName.getCityById(c1.getID()).ratioWidth),
								(int) (yPosition + height * CityName.getCityById(c1.getID()).ratioHeight),
								(int) (xPosition + width * CityName.getCityById(c2.getID()).ratioWidth),
								(int) (yPosition + height * CityName.getCityById(c2.getID()).ratioHeight));
					}
				}
				if (model.getRoute() != null) {
					for (int i = 1; i < model.getRoute().size(); i++) {
						City c1 = model.getRoute().get(i - 1);
						City c2 = model.getRoute().get(i);
						g2d.setStroke(new BasicStroke(5));
						g2d.setColor(Color.RED);
						g2d.drawLine((int) (xPosition + width * CityName.getCityById(c1.getID()).ratioWidth),
								(int) (yPosition + height * CityName.getCityById(c1.getID()).ratioHeight),
								(int) (xPosition + width * CityName.getCityById(c2.getID()).ratioWidth),
								(int) (yPosition + height * CityName.getCityById(c2.getID()).ratioHeight));
					}
				}
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
		Model model = (Model) object;
		if (model.getGraph() != null) {
			List<String> graphComponents = new ArrayList<String>();
			for (City city : model.getGraph().getCities())
				graphComponents.add(city.toString());

			for (Road road : model.getGraph().getRoads())
				graphComponents.add(road.toString());
			this.graphList.setListData(graphComponents.toArray(new String[graphComponents.size()]));

		}

		if (model.getRoute() != null) {
			List<String> routeComponents = new ArrayList<String>();
			for (City city : model.getRoute())
				routeComponents.add(city.toString());
			this.shortestPathList.setListData(routeComponents.toArray(new String[routeComponents.size()]));
		}
		mapPanel.repaint();
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
				JOptionPane.OK_OPTION, new ImageIcon(Main.class.getResource("/ressources/bank.png")), cities, null);
		if (from == null)
			return;

		City to = (City) JOptionPane.showInputDialog(null, "In which city do you want to hide?", "To city",
				JOptionPane.OK_OPTION, new ImageIcon(Main.class.getResource("/ressources/mask.png")), cities, null);
		if (to == null)
			return;

		Vehicle vehicle = (Vehicle) JOptionPane.showInputDialog(null, "What vehicle do you want to use?", "Vehicle",
				JOptionPane.OK_OPTION, new ImageIcon(Main.class.getResource("/ressources/taxi.png")), Vehicle.values(), null);
		if (vehicle == null)
			return;

		controller.shortestPath(from, to, vehicle);
	}

}
