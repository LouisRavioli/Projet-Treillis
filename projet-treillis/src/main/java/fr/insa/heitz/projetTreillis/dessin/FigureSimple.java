package fr.insa.heitz.projetTreillis.dessin;

import javafx.scene.paint.Color;

public abstract class FigureSimple extends Figure {

	private Color couleur;
	
	public FigureSimple(Color couleur) {
		this.couleur = couleur;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
}
