package fr.insa.heitz.projetTreillis.gui;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Informations extends VBox {

	private Titre bpTitre;

	public Informations() {
		//Titre
		bpTitre = new Titre("Informations", "informations-titre");
		
		getChildren().addAll(bpTitre);
		getStyleClass().add("informations-vbox");
		StackPane.setAlignment(this, Pos.TOP_RIGHT);
	}

	public Titre getBpTitre() {
		return bpTitre;
	}
}
