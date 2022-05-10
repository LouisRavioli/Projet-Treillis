package fr.insa.heitz.projetTreillis.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Droite extends VBox {

	public Droite() {
		//I. Titre
		Label lL1 = new Label("Informations");
		lL1.getStyleClass().add("droite-l1-label");
		Button bL1 = new Button();
		bL1.getStyleClass().add("droite-l1-button");
		BorderPane bpL1 = new BorderPane(null, null, bL1, null, lL1);
		bpL1.getStyleClass().add("droite-l1-border-pane");
		
		getChildren().addAll(bpL1);
		getStyleClass().add("droite-vbox");
	}
}
