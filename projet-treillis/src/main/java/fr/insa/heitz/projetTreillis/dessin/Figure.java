package fr.insa.heitz.projetTreillis.dessin;

import java.util.List;

import fr.insa.heitz.projetTreillis.Treillis;
import fr.insa.heitz.projetTreillis.gui.Controleur;
import fr.insa.heitz.projetTreillis.gui.Informations;
import fr.insa.heitz.projetTreillis.gui.ZoneDessin;
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
	
	public abstract void dessine(Controleur controleur, List<Node> formes);
		
	public abstract List<Figure> copie();
	
	public abstract List<FigureSimple> getFiguresSimples();

	public abstract void deplacer(ZoneDessin zoneDessin, double dx, double dy);
	
	public abstract List<Figure> getDependance();

	public abstract void supprimeDuTreillis(Treillis treillis);

	public abstract void supprimeDeInformations(Informations informations);
}

