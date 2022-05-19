package fr.insa.heitz.projetTreillis.dessin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.insa.heitz.projetTreillis.gui.Controleur;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Point extends FigureSimple {
	
	public static final double TAILLE_POINT = 5;
	
	private double px;
	private double py;
	private List<Segment> segmentsIncidents;
	
	public Point(int id, Color couleur, double px, double py, Forme forme) {
		super(id, couleur, forme);
		this.px = px;
		this.py = py;
		this.segmentsIncidents = new ArrayList<Segment>();
	}

	public Point(Color couleur, double px, double py) {
		this(0, couleur, px, py, null);
	}
	
	public Point(double px, double py) {
		this(Color.BLACK, px, py);
	}
	
	public Point() {
		this(0, 0);
	}
	
	public Point(Point modele) {
		this(modele.getId(), modele.getCouleur(), modele.px, modele.py, modele.getForme());
		segmentsIncidents = modele.segmentsIncidents;
	}
	
	@Override
	public String toString() {
		return "id : " + getId() + "\npos : [" + px + "," + py + "]\ncouleur : " + getCouleur();
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
	
	public List<Segment> getSegmentsIncidents() {
		return segmentsIncidents;
	}

	public void setSegmentsIncidents(List<Segment> segmentsIncidents) {
		this.segmentsIncidents = segmentsIncidents;
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
	public void dessine(Controleur controleur, List<Node> formes) {
		CustomEllipse ce = new CustomEllipse(getCouleur(), px, py, TAILLE_POINT, TAILLE_POINT, this);
		setForme(ce);
		ce.getShape().setOnMouseClicked(event -> {
			controleur.clicZoneDessin(event, this);
		});
		ce.getShape().setOnMouseEntered(event -> {
			controleur.mouseEnterForme(this);
		});
		ce.getShape().setOnMouseExited(event -> {
			controleur.mouseExitForme(this);
		});
		formes.add(ce.getShape());
	}
	
	@Override
	public List<Figure> copie() {
		return new ArrayList<Figure>(Arrays.asList(new Point(this)));
	}
	
	@Override
	public void deplacer(double dx, double dy) {
		px += dx;
		py += dy;
	}

	@Override
	public List<FigureSimple> getDependance() {
		return new ArrayList<FigureSimple>(Arrays.asList(this));
	}
}
