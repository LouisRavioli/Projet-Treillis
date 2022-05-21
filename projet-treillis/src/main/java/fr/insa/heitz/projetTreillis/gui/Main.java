package fr.insa.heitz.projetTreillis.gui;

import fr.insa.heitz.projetTreillis.Treillis;
import fr.insa.heitz.projetTreillis.dessin.Groupe;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainBorderPane mainBorderPane = new MainBorderPane(new Groupe(), new Treillis());
    	Scene sc = new Scene(mainBorderPane, 1200, 600);
    	sc.getStylesheets().add("/stylesheets/sombre.css");
    	primaryStage.setScene(sc);
    	primaryStage.setTitle("Interface de fou malade");
    	primaryStage.getIcons().add(new Image("/images/barre.png"));
    	primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}