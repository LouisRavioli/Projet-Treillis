package fr.insa.heitz.projetTreillis.gui;

import fr.insa.heitz.projetTreillis.dessin.Groupe;
import javafx.scene.layout.Pane;

public class ZoneDessin extends Pane {
	
	private Groupe modele;
	
	public ZoneDessin(Groupe modele) {
		this.modele = modele;
		dessiner();
				
		getStyleClass().add("zone-dessin-pane");
	}

	public Groupe getModele() {
		return modele;
	}

	public void setModele(Groupe modele) {
		this.modele = modele;
	}
	
	public void dessiner() {
		getChildren().add(modele.dessine());
	}
}
