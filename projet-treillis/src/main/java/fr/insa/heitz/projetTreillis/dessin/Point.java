package fr.insa.heitz.projetTreillis.dessin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.insa.heitz.projetTreillis.Noeud;
import fr.insa.heitz.projetTreillis.Treillis;
import fr.insa.heitz.projetTreillis.gui.Controleur;
import fr.insa.heitz.projetTreillis.gui.Informations;
import fr.insa.heitz.projetTreillis.gui.LigneInformationPoint;
import fr.insa.heitz.projetTreillis.gui.ZoneDessin;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Point extends FigureSimple {
	
	public static final double TAILLE_POINT = 3;
	
	private double px;
	private double py;
	private List<Segment> segmentsIncidents;
	private Noeud noeud;
	private LigneInformationPoint ligne;
	
	public Point(Color couleur, double px, double py, Forme forme) {
		super(couleur, forme);
		this.px = px;
		this.py = py;
		this.segmentsIncidents = new ArrayList<Segment>();
	}

	public Point(Color couleur, double px, double py) {
		this(couleur, px, py, null);
	}
	
	public Point(double px, double py) {
		this(Color.BLACK, px, py);
	}
	
	public Point() {
		this(0, 0);
	}
	
	public Point(Point modele) {
		this(modele.getCouleur(), modele.px, modele.py, modele.getForme());
		segmentsIncidents = modele.segmentsIncidents;
	}
	
	@Override
	public String toString() {
		return "point " + getNoeud().getTreillis().getNoeuds().get(getNoeud()) + " :\npos : [" + px + "," + py + "]\ncouleur : " + getCouleur();
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

	public Noeud getNoeud() {
		return noeud;
	}

	public void setNoeud(Noeud noeud) {
		this.noeud = noeud;
	}

	public LigneInformationPoint getLigne() {
		return ligne;
	}

	public void setLigne(LigneInformationPoint ligne) {
		this.ligne = ligne;
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
		CustomEllipse ce = new CustomEllipse(getCouleur(), px, py, TAILLE_POINT, TAILLE_POINT, this, getNoeud().isTerrain());
		setForme(ce);
		ce.getShape().setOnMousePressed(event -> {
			controleur.clicPoint(event, this);
		});
		ce.getShape().setOnMouseClicked(event -> {
			controleur.doubleClicPoint(event, this);
		});
		if (!getNoeud().isTerrain()) {
			ce.getShape().setOnMouseEntered(event -> {
				controleur.mouseEnterForme(this);
			});
			ce.getShape().setOnMouseExited(event -> {
				controleur.mouseExitForme(this);
			});
		}
		formes.add(ce.getShape());
	}
	
	@Override
	public List<Figure> copie() {
		return new ArrayList<Figure>(Arrays.asList(new Point(this)));
	}
	
	@Override
	public void deplacer(ZoneDessin zoneDessin, double dx, double dy) {
		px += dx;
		py += dy;
		getLigne().getTfPx().setText(String.valueOf(px));
		getLigne().getTfPy().setText(String.valueOf(zoneDessin.getHeight() - py));
	}

	@Override
	public List<Figure> getDependance() {
		List<Figure> dependance = new ArrayList<Figure>(Arrays.asList(this));
		dependance.addAll(segmentsIncidents);
		return dependance; 
	}
	
	@Override
	public void supprimeDuTreillis(Treillis treillis) {
		treillis.supprimeNoeud(getNoeud());
	}
	
	@Override
	public void supprimeDeInformations(Informations informations) {
		informations.removeLignePoint(this);
	}
	
	public int idLigne() {
		int i = 0;
		for (Noeud n : getNoeud().getTreillis().getNoeuds().keySet()) {
			if ((getNoeud().getTreillis().getNoeuds().get(getNoeud()) > n.getTreillis().getNoeuds().get(n))&&(!n.isTerrain())) {
				i++;
			}
		}
		return i;
	}
}
