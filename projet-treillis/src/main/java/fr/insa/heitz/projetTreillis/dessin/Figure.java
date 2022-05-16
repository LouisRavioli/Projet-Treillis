package fr.insa.heitz.projetTreillis.dessin;

import java.util.List;

import fr.insa.heitz.projetTreillis.gui.Controleur;
import javafx.scene.Node;

public abstract class Figure {

	private Groupe groupe;
	
	public Figure(Groupe groupe) {
		this.groupe = groupe;
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
	
	public abstract double maxX() throws Exception;
	
	public abstract double minX() throws Exception;
	
	public abstract double maxY() throws Exception;
	
	public abstract double minY() throws Exception;
	
	public abstract List<Node> dessine(Controleur controleur); 
	}
