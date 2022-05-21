package fr.insa.heitz.projetTreillis.gui;

import fr.insa.heitz.projetTreillis.Barre;
import fr.insa.heitz.projetTreillis.NoeudSimple;
import fr.insa.heitz.projetTreillis.Treillis;
import fr.insa.heitz.projetTreillis.dessin.Groupe;
import fr.insa.heitz.projetTreillis.dessin.Point;
import fr.insa.heitz.projetTreillis.dessin.Segment;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class MainBorderPane extends BorderPane {

	private Controleur controleur;
	private Groupe modele;
		
	private Haut vbHaut;
	private Bas hbBas;
	private Outils vbOutils;
	private Couleurs vbCouleurs;
	private Informations vbInformations;
	private ZoneDessin pZoneDessin;
	private StackPane spCentre;
	
	
	public MainBorderPane(Groupe modele) {
		this.modele = modele;
		controleur = new Controleur(this);
		
		vbHaut = new Haut(this);
		hbBas = new Bas(this);
		vbOutils = new Outils(this);
		vbCouleurs = new Couleurs(this);
		vbInformations = new Informations(this);
		pZoneDessin = new ZoneDessin(this);
		spCentre = new StackPane(pZoneDessin, vbOutils, vbCouleurs, vbInformations);
		spCentre.getStyleClass().add("centre-stack-pane");
		
		setTop(vbHaut);
		setBottom(hbBas);
		setCenter(spCentre);
		
		Treillis t = new Treillis();
		
		Point p1 = new Point(Color.web("#168426"), 1, 2);
		p1.setNoeud(new NoeudSimple());
		t.ajouteNoeud(p1.getNoeud());
		vbInformations.addLignePoint(p1);
		
		Point p2 = new Point(Color.web("#123626"), 5, 8);
		p2.setNoeud(new NoeudSimple());
		t.ajouteNoeud(p2.getNoeud());
		vbInformations.addLignePoint(p2);
		
		Segment s = new Segment(Color.web("#168426"), p1, p2);
		s.setBarre(new Barre(p1.getNoeud(), p2.getNoeud()));
		t.ajouteBarre(s.getBarre());
		vbInformations.addLigneSegment(s);
	}

	public Groupe getModele() {
		return modele;
	}
	
	public void setModele(Groupe modele) {
		this.modele = modele;
	}

	public Controleur getControleur() {
		return controleur;
	}

	public void setControleur(Controleur controleur) {
		this.controleur = controleur;
	}

	public Haut getVbHaut() {
		return vbHaut;
	}

	public Bas getHbBas() {
		return hbBas;
	}

	public Outils getVbOutils() {
		return vbOutils;
	}

	public Couleurs getVbCouleurs() {
		return vbCouleurs;
	}

	public Informations getVbInformations() {
		return vbInformations;
	}

	public ZoneDessin getpZoneDessin() {
		return pZoneDessin;
	}

	public StackPane getSpCentre() {
		return spCentre;
	}
}
