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
		for (Figure f : figures) {
			f.setGroupe(this);
		}
	}
	
	public Groupe(Figure... figures) {
		this(new ArrayList<Figure>(Arrays.asList(figures)));
	}
	
	public Groupe() {
		this(new ArrayList<Figure>());
	}
	
	@Override
	public String toString() {
		String s = "groupe :\n";
		for (Figure f : figures) {
			s += f + "\n\n";
		}
		return s.substring(0, s.length() - 2);
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
	public void dessine(Controleur controleur, List<Node> formes) {
		for (Figure f : figures) {				
			f.dessine(controleur, formes);
		}
	}
	
	@Override
	public List<Figure> copie() {
		List<Figure> copies = new ArrayList<Figure>();
		for (Figure f : figures) {
			copies.addAll(f.copie());
		}
		return copies;
	}
	
	@Override
	public List<FigureSimple> getFiguresSimples() {
		List<FigureSimple> figuresSimples = new ArrayList<FigureSimple>();
		for (Figure f : figures) {
			figuresSimples.addAll(f.getFiguresSimples());
		}
		return figuresSimples;
	}
	
	@Override
	public void deplacer(double dx, double dy) {
		for (Figure f : figures) {
			f.deplacer(dx, dy);
		}
	}
	
	@Override
	public List<Figure> getDependance() {
		List<Figure> dependance = new ArrayList<Figure>(Arrays.asList(this));
		for (Figure f : figures) {
			dependance.addAll(f.getDependance());
		}
		return dependance;
	}
	
	public void addFigure(Figure f) {
		f.setGroupe(this);
		figures.add(f);
		for (FigureSimple fs : f.getFiguresSimples()) {
			if (fs.getId() == 0) {
				fs.setId(idLibre());
			}
		}
	}
	
	public void removeFigure(Figure f) {
		figures.remove(f);
	}
	
	public int idLibre() {
		boolean idTrouve = true;
		int i = 0;
		while (idTrouve) {
			i++;
			idTrouve = false;
			for (FigureSimple fs : getFiguresSimples()) {
				if (fs.getId() == i) {
					idTrouve = true;
				}
			}
		}
		return i;
	}
	
	public Groupe grouper(List<Figure> elements) {
		List<Figure> groupe = new ArrayList<Figure>();
		for (Figure f : elements) {
			for (FigureSimple fs : f.getFiguresSimples()) {
				groupe.add(fs);
			}
			removeFigure(f);
		}
		Groupe g = new Groupe(groupe);
		addFigure(g);
		return g;
	}
	
	public List<Figure> scinder(Groupe groupe){
		removeFigure(groupe);
		List<Figure> elements = new ArrayList<Figure>(groupe.getFiguresSimples());
		for (Figure f : elements) {
			addFigure(f);
		}
		return elements;
	}
	
	public static Groupe groupeTest() {
		return new Groupe(Banque.S1, Banque.P1, Banque.P2);
	}
}
