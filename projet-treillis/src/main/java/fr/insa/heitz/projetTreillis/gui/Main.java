package fr.insa.heitz.projetTreillis.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    	//Test
    	/*Button b1 = new Button();
    	Button b2 = new Button();
    	b1.getStyleClass().add("gauche-outils-l1-button");
    	b2.getStyleClass().add("test");
    	HBox hb = new HBox(b1, b2);
    	Scene sc = new Scene(hb);*/
    	
    	
    	Scene sc = new Scene(new MainBorderPane(), 1200, 600);
    	sc.getStylesheets().add("/stylesheets/sombre.css");
    	primaryStage.setScene(sc);
    	primaryStage.setTitle("Interface de fou malade");
    	primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}