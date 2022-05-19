package fr.insa.heitz.projetTreillis.dessin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;

public abstract class FigureSimple extends Figure {

	private int id;
	private Color couleur;
	private Forme forme;
	
	public FigureSimple(int id, Color couleur, Forme forme) {
		this.id = id;
		this.couleur = couleur;
		this.forme = forme;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	
	public Forme getForme() {
		return forme;
	}

	public void setForme(Forme forme) {
		this.forme = forme;
	}

	@Override
	public List<FigureSimple> getFiguresSimples() {
		return new ArrayList<FigureSimple>(Arrays.asList(this));
	}
}
