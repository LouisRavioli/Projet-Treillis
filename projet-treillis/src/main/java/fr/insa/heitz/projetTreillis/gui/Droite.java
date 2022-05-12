package fr.insa.heitz.projetTreillis.gui;

import javafx.scene.layout.VBox;

public class Droite extends VBox {
	
	private Titre bpTitre;

	public Droite() {
		//I. Titre
		bpTitre = new Titre("Informations", "droite-l1");
		
		getChildren().addAll(bpTitre);
		getStyleClass().add("droite-vbox");
	}
}
