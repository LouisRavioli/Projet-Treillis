package fr.insa.heitz.projetTreillis.dessin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.insa.heitz.projetTreillis.gui.Controleur;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Point extends FigureSimple {
	
	public static final double TAILLE_POINT = 5;

	private double px;
	private double py;
	
	public Point(Color couleur, double px, double py) {
		super(couleur);
		this.px = px;
		this.py = py;
	}
	
	public Point(double px, double py) {
		this(Color.BLACK, px, py);
	}
	
	public Point() {
		this(0, 0);
	}
	
	public Point(Point modele) {
		this(modele.getCouleur(), modele.px, modele.py);
	}
	
	@Override
	public String toString() {
		return "pos : [" + px + "," + py + "]\ncouleur : " + getCouleur();
	}

	public double getPx() {
		return px;
	}

	public void setPx(double px) {
		this.px = px;
	}

	public double getPy() {
		return py;
	}

	public void setPy(double py) {
		this.py = py;
	}

	@Override
	public double maxX() throws Exception {
		return px;
	}

	@Override
	public double minX() throws Exception {
		return px;
	}

	@Override
	public double maxY() throws Exception {
		return py;
	}

	@Override
	public double minY() throws Exception {
		return py;
	}

	@Override
	public List<Node> dessine(Controleur controleur) {
		Ellipse e = new Ellipse(px, py, TAILLE_POINT, TAILLE_POINT);
		e.setFill(getCouleur());
		e.getStyleClass().add("forme-point");
		e.setOnMouseClicked(event -> {
			controleur.clicZoneDessin(event, e);
		});
		return new ArrayList<Node>(Arrays.asList(e));
	}
}
