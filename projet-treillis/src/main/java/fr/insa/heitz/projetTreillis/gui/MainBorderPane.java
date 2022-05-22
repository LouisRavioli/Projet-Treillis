package fr.insa.heitz.projetTreillis.gui;

import fr.insa.heitz.projetTreillis.Treillis;
import fr.insa.heitz.projetTreillis.dessin.Groupe;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MainBorderPane extends BorderPane {

	private Controleur controleur;
	private Groupe modele;
	private Treillis treillis;
	private Groupe terrain;
		
	private Haut vbHaut;
	private Bas hbBas;
	private Outils vbOutils;
	private Couleurs vbCouleurs;
	private Informations vbInformations;
	private ZoneDessin pZoneDessin;
	private StackPane spCentre;
	
	
	public MainBorderPane(Groupe modele, Treillis treillis, Groupe terrain) {
		controleur = new Controleur(this);
		this.modele = modele;
		this.treillis = treillis;
		this.terrain = terrain;
		
		vbHaut = new Haut(this);
		hbBas = new Bas(this);
		vbOutils = new Outils(this);
		vbCouleurs = new Couleurs(this);
		vbInformations = new Informations(this);
		pZoneDessin = new ZoneDessin(this);
		spCentre = new StackPane(pZoneDessin, vbOutils, vbCouleurs, vbInformations);
		spCentre.getStyleClass().add("centre-stack-pane");
		
		setCenter(spCentre);
		setTop(vbHaut);
		setBottom(hbBas);
		
		pZoneDessin.setTranslateX(pZoneDessin.getTranslateX() - 25);
	}

	public Controleur getControleur() {
		return controleur;
	}

	public void setControleur(Controleur controleur) {
		this.controleur = controleur;
	}
	
	public Groupe getModele() {
		return modele;
	}
	
	public void setModele(Groupe modele) {
		this.modele = modele;
	}

	public Treillis getTreillis() {
		return treillis;
	}

	public void setTreillis(Treillis treillis) {
		this.treillis = treillis;
	}

	public Groupe getTerrain() {
		return terrain;
	}

	public void setTerrain(Groupe terrain) {
		this.terrain = terrain;
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
