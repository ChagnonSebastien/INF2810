package ca.polymtl.log2810;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
			
		Model model = new Model();
		View view = new View();
		model.addObserver(view);

		Controller controller = new Controller(model, view);
		view.addController(controller);
	}

}
