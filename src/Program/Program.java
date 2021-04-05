package Program;

import Controller.StoreController;
import Model.StoreModel;
import View.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainView view = new MainView(primaryStage);
		StoreModel model = new StoreModel();
		StoreController controller = new StoreController(model, view);
	}

}
