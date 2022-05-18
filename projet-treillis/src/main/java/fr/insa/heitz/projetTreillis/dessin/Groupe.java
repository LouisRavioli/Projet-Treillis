package fr.insa.heitz.projetTreillis.dessin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.insa.heitz.projetTreillis.gui.Controleur;
import javafx.scene.Node;

public class Groupe extends Figure {

	private List<Figure> figures;

	public Groupe(List<Figure> figures) {
		this.figures = figures;
	}
	
	public Groupe(Figure... figures) {
		this(new ArrayList<Figure>(Arrays.asList(figures)));
	}
	
	public Groupe() {
		this(new ArrayList<Figure>());
	}
	
	public List<Figure> getFigures() {
		return figures;
	}

	public void setFigures(List<Figure> figures) {
		this.figures = figures;
	}

	@Override
	public double maxX() throws Exception {
		if (figures.size() == 0) {
			throw new Exception("Groupe vide");
		}
		else {
			double max = figures.get(0).maxX();
			for (Figure f : figures.subList(1, figures.size())) {
				if (f.maxX() > max) {
					max = f.maxX();
				}
			}
			return max;
		}
	}

	@Override
	public double minX() throws Exception {
		if (figures.size() == 0) {
			throw new Exception("Groupe vide");
		}
		else {
			double min = figures.get(0).minX();
			for (Figure f : figures.subList(1, figures.size())) {
				if (f.minX() < min) {
					min = f.minX();
				}
			}
			return min;
		}
	}

	@Override
	public double maxY() throws Exception {
		if (figures.size() == 0) {
			throw new Exception("Groupe vide");
		}
		else {
			double max = figures.get(0).maxY();
			for (Figure f : figures.subList(1, figures.size())) {
				if (f.maxY() > max) {
					max = f.maxY();
				}
			}
			return max;
		}
	}

	@Override
	public double minY() throws Exception {
		if (figures.size() == 0) {
			throw new Exception("Groupe vide");
		}
		else {
			double min = figures.get(0).minY();
			for (Figure f : figures.subList(1, figures.size())) {
				if (f.minY() < min) {
					min = f.minY();
				}
			}
			return min;
		}
	}

	@Override
	public void dessine(Controleur controleur, ArrayList<Node> formes) {
		for (Figure f : figures) {				
			f.dessine(controleur, formes);
		}
	}
	
	public void addFigure(Figure f) {
		figures.add(f);
	}
	
	public void removeFigure(Figure f) {
		figures.remove(f);
	}
	
	public static Groupe groupeTest() {
		return new Groupe(Banque.S1, Banque.P1, Banque.P2);
	}
}
