package fr.insa.heitz.projetTreillis.dessin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Group;

public class Groupe extends Figure {

	private List<Figure> figures;

	public Groupe(List<Figure> figures) {
		this.figures = figures;
	}
	
	public Groupe(Figure... figures) {
		this.figures = Arrays.asList(figures);
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
	public Group dessine() {
		Group g = new Group();
		for (Figure f : figures) {
			g.getChildren().add(f.dessine());
		}
		return g;
	}
	
	public void addFigure(Figure f) {
		figures.add(f);
	}
	
	public static Groupe groupeTest() {
		return new Groupe(Banque.P1, Banque.P2, Banque.S1);
	}
}
