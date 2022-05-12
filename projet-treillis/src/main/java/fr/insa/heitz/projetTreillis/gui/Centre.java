package fr.insa.heitz.projetTreillis.gui;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Centre extends StackPane {
	
	private Pane pDessin;

	public Centre() {
		//I. Surface dessin
		pDessin = new Pane();
		pDessin.getStyleClass().add("centre-pane");
		
		getChildren().add(pDessin);
		getStyleClass().add("centre-stack-pane");
	}

	public Pane getpDessin() {
		return pDessin;
	}
}
