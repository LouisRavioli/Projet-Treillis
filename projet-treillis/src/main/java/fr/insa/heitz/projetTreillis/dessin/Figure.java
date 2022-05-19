package fr.insa.heitz.projetTreillis.dessin;

import java.util.List;

import fr.insa.heitz.projetTreillis.gui.Controleur;
import javafx.scene.Node;

public abstract class Figure {

	private Groupe groupe;
	private boolean hover;
	private boolean selected;
	
	public Figure(Groupe groupe) {
		this.groupe = groupe;
		hover = false;
		selected = false;
	}
	
	public Figure() {
		this(null);
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	
	public boolean isHover() {
		return hover;
	}
	
	public void setHover(boolean hover) {
		this.hover = hover;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public abstract double maxX() throws Exception;
	
	public abstract double minX() throws Exception;
	
	public abstract double maxY() throws Exception;
	
	public abstract double minY() throws Exception;
	
	public abstract void dessine(Controleur controleur, List<Node> formes);
		
	public abstract List<Figure> copie();
	
	public abstract List<FigureSimple> getFiguresSimples();

	public abstract void deplacer(double dx, double dy);
	}

