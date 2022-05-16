package fr.insa.heitz.projetTreillis.dessin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.insa.heitz.projetTreillis.gui.Controleur;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Segment extends FigureSimple {

	private Point pointDepart;
	private Point pointArrivee;
	
	public Segment(Color couleur, Point pointDepart, Point pointArrivee) {
		super(couleur);
		this.pointDepart = pointDepart;
		this.pointArrivee = pointArrivee;
	}
	
	public Segment(Point pointDepart, Point pointArrivee) {
		this(Color.BLACK, pointDepart, pointArrivee);
	}
	
	public Segment() {
		this(new Point(), new Point());
	}
	
	public Segment(Segment modele) {
		this(modele.getCouleur(), modele.pointDepart, modele.pointArrivee);
	}
	
	@Override
	public String toString() {
		return "couleur : " + getCouleur() + "\npoint départ :\n" + pointDepart + "\npoint arrivée :\n" + pointArrivee;
	}

	public Point getPointDepart() {
		return pointDepart;
	}

	public void setPointDepart(Point pointDepart) {
		this.pointDepart = pointDepart;
	}

	public Point getPointArrivee() {
		return pointArrivee;
	}

	public void setPointArrivee(Point pointArrivee) {
		this.pointArrivee = pointArrivee;
	}

	@Override
	public double maxX() throws Exception {
		return Math.max(pointDepart.maxX(), pointArrivee.maxX());
	}

	@Override
	public double minX() throws Exception {
		return Math.min(pointDepart.minX(), pointArrivee.minX());
	}

	@Override
	public double maxY() throws Exception {
		return Math.max(pointDepart.maxY(), pointArrivee.maxY());
	}

	@Override
	public double minY() throws Exception {
		return Math.min(pointDepart.minY(), pointArrivee.minY());
	}

	@Override
	public List<Node> dessine(Controleur controleur) {
		Line l = new Line(pointDepart.getPx(), pointDepart.getPy(), pointArrivee.getPx(), pointArrivee.getPy());
		l.setStroke(getCouleur());
		l.getStyleClass().add("forme-segment");
		l.setOnMouseClicked(event -> {
			controleur.clicZoneDessin(event, l);
		});
		return new ArrayList<Node>(Arrays.asList(l));
	}
}
