package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller implements Initializable {

	@FXML
	private TextArea textArea;
	@FXML
	private Label label;
	private FileChooser fileChooser = new FileChooser();

	private File file;
	double x, y;

	@FXML
	void min(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	void max(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setFullScreen(true);

	}

	@FXML
	void close(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	@FXML
	void pressed(MouseEvent event) {
		x = event.getSceneX();
		y = event.getSceneY();
	}

	@FXML
	void dragged(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setX(event.getScreenX() - x);
		stage.setY(event.getScreenY() - y);
	}

	@FXML
	protected void newFile(ActionEvent event) {
		label.setText(" Untitled - Notepad");
		textArea.clear();
		file = null;
	}

	@FXML
	protected void openFile(ActionEvent event) {
		file = fileChooser.showOpenDialog(null);
		if (file != null) {
			textArea.clear();
			label.setText(" " + file.getName() + " - Notepad");
			BufferedReader br = null;
			try {
				String sCurrentLine;
				br = new BufferedReader(new FileReader(file));
				while ((sCurrentLine = br.readLine()) != null) {
					textArea.appendText(sCurrentLine + "\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	protected void saveFile(ActionEvent event) {
		String content = textArea.getText();
		if (file != null) {
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			file = fileChooser.showSaveDialog(null);
			if (file != null) {
				label.setText(" " + file.getName() + " - Notepad");
				try {
					if (!file.exists()) {
						file.createNewFile();
					}
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(content);
					bw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@FXML
	protected void saveasFile(ActionEvent event) {
		file = fileChooser.showSaveDialog(null);
		String content = textArea.getText();
		if (file != null) {
			label.setText(" " + file.getName() + " - Notepad");
			try {

				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
}
