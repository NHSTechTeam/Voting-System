package voteCalculations;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

class InterfaceAid {
    public static void windowBasic(Stage window, Scene mainScene) {
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Voting Booth");
        window.setMinWidth(700);
        window.setMinHeight(700);
        window.setOnCloseRequest(e -> window.close());
        window.setMaximized(true);
        mainScene.getStylesheets().add("style.css");
    }
}
