package fr.insa.heitz.projetTreillis.dessin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.insa.heitz.projetTreillis.gui.Controleur;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Segment extends FigureSimple {

	private Point pointDepart;
	private Point pointArrivee;
	
	public Segment(int id, Color couleur, Point pointDepart, Point pointArrivee, Forme forme) {
		super(id, couleur, forme);
		this.pointDepart = pointDepart;
		this.pointArrivee = pointArrivee;
		pointDepart.getSegmentsIncidents().add(this);
		pointArrivee.getSegmentsIncidents().add(this);
	}
	
	public Segment(Color couleur, Point pointDepart, Point pointArrivee) {
		this(0, couleur, pointDepart, pointArrivee, null);
	}
	
	public Segment(Point pointDepart, Point pointArrivee) {
		this(Color.BLACK, pointDepart, pointArrivee);
	}
	
	public Segment() {
		this(new Point(), new Point());
	}
	
	public Segment(Segment modele) {
		this(modele.getId(), modele.getCouleur(), modele.pointDepart, modele.pointArrivee, modele.getForme());
	}
	
	@Override
	public String toString() {
		return "id : " + getId() + "\npoint départ :\n" + pointDepart + "\npoint arrivée :\n" + pointArrivee + "\ncouleur : " + getCouleur() ;
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
	public void dessine(Controleur controleur, List<Node> formes) {
		CustomLine cl = new CustomLine(getCouleur(), pointDepart.getPx(), pointDepart.getPy(), pointArrivee.getPx(), pointArrivee.getPy(), this);
		setForme(cl);
		cl.getShape().setOnMouseClicked(event -> {
			controleur.clicZoneDessin(event, this);
		});
		cl.getShape().setOnMouseEntered(event -> {
			controleur.mouseEnterForme(this);
		});
		cl.getShape().setOnMouseExited(event -> {
			controleur.mouseExitForme(this);
		});
		formes.add(0, cl.getShape());
	}
	
	@Override
	public List<Figure> copie() {
		return new ArrayList<Figure>(Arrays.asList(new Segment(this)));
	}
	
	@Override
	public void deplacer(double dx, double dy) {
	}
	
	@Override
	public List<FigureSimple> getDependance() {
		return new ArrayList<FigureSimple>(Arrays.asList(this));
	}
}
