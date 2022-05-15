package fr.insa.heitz.projetTreillis.dessin;

import javafx.scene.Group;

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
	
	public abstract Group dessine(); 
	}
