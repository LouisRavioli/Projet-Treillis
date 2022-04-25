package fr.insa.heitz.projetTreillis.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    	Scene sc = new Scene(new MainBorderPane());
    	sc.getStylesheets().add("/stylesheets/sombre.css");
    	primaryStage.setScene(sc);
    	primaryStage.setTitle("Interface de fou malade");
    	primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}