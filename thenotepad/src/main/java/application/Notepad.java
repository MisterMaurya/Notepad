package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Notepad extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.initStyle(StageStyle.TRANSPARENT);
		Parent root = FXMLLoader.load(getClass().getResource("/application/MyFXML.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent clicked) {
				if (clicked.getClickCount() == 2) {
					if (!primaryStage.isFullScreen()) {
						primaryStage.setFullScreen(true);
					} else {
						primaryStage.setFullScreen(false);
					}

				}
			}

		});
		primaryStage.show();
	}
}
