package fr.insa.heitz.projetTreillis.gui;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Couleurs extends VBox {

	private Titre bpTitre;
	private SelecteurCouleur selecteurCouleur;
	
	public Couleurs() {
		//Titre
		bpTitre = new Titre("Couleurs", "couleurs-titre");
		
		//Sélection couleurs
		selecteurCouleur = new SelecteurCouleur("couleurs-corps");
		
		getChildren().addAll(bpTitre, selecteurCouleur);
		getStyleClass().add("couleurs-vbox");
		StackPane.setAlignment(this, Pos.BOTTOM_LEFT);
	}

	public Titre getBpTitre() {
		return bpTitre;
	}

	public SelecteurCouleur getSelecteurCouleur() {
		return selecteurCouleur;
	}
}
