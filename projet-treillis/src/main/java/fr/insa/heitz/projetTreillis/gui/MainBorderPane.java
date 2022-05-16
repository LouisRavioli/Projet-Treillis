package fr.insa.heitz.projetTreillis.gui;

import fr.insa.heitz.projetTreillis.dessin.Groupe;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

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
		spCentre = new StackPane(vbOutils, vbCouleurs, vbInformations, pZoneDessin);
		spCentre.getStyleClass().add("centre-stack-pane");
		
		setTop(vbHaut);
		setBottom(hbBas);
		setCenter(spCentre);
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
