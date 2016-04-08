package voteCalculations;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InterfaceAid {
	public static void windowBasic(Stage window, String title, int size, Scene mainScene) {
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(size);
		window.setMinHeight(size);
		window.setOnCloseRequest(e -> window.close());
		mainScene.getStylesheets().add("style.css");
	}
}
