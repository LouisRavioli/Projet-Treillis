package fr.insa.heitz.projetTreillis.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Haut extends VBox {

	private MenuBar mbL1;
	private Menu mFichier;
	private Menu mEdition;
	private Menu mAffichage;
	private MenuItem miNouveau;
	private MenuItem miOuvrir;
	private MenuItem miEnregistrer;
	private MenuItem miAnnuler;
	private MenuItem miRepeter;
	private MenuItem miCouper;
	private MenuItem miCopier;
	private MenuItem miColler;
	private MenuItem miZoomAvant;
	private MenuItem miZoomArriere;
	private MenuItem miTailleReelle;
	private MenuItem miAjuster;
	
	private BorderPane bpL2;
	private HBox hbL2Gauche;
	private HBox hbL2Droite;
	private Button bNouveau;
	private Button bOuvrir;
	private Button bEnregistrer;
	private Button bCouper;
	private Button bCopier;
	private Button bColler;
	private Button bAnnuler;
	private Button bRepeter;
	private ToggleButton tbGrille;
	private ToggleButton tbCouleurs;
	private ToggleButton tbOutils;
	private ToggleButton tbInformations;
		
	public Haut() {
		//I. Barre menu
		mbL1 = new MenuBar();
		mbL1.getStyleClass().add("haut-l1-menu-bar");
		mFichier = new Menu();
		mEdition = new Menu();
		mAffichage = new Menu();
		Menu[] menusHaut = {mFichier, mEdition, mAffichage};
		miNouveau = new MenuItem();
		miOuvrir = new MenuItem();
		miEnregistrer = new MenuItem();
		miAnnuler = new MenuItem();
		miRepeter = new MenuItem();
		miCouper = new MenuItem();
		miCopier = new MenuItem();
		miColler = new MenuItem();
		miZoomAvant = new MenuItem();
		miZoomArriere = new MenuItem();
		miTailleReelle = new MenuItem();
		miAjuster = new MenuItem();
		MenuItem[][] menuItemsHaut = {{miNouveau, miOuvrir, miEnregistrer}, {miAnnuler, miRepeter, miCouper, miCopier, miColler}, {miZoomAvant, miZoomArriere, miTailleReelle, miAjuster}};
		String[] nomsMenus = {"Fichier", "Edition", "Affichage"};
		String[][] nomsMenuItems = {{"Nouveau", "Ouvrir", "Enregistrer"}, {"Annuler", "R�p�ter", "Couper", "Copier", "Coller"}, {"Zoom avant", "Zoom arri�re", "Taille r�elle", "Ajuster � la fen�tre"}};
		for (int i = 0; i < menusHaut.length; i++) {
			menusHaut[i].setText(nomsMenus[i]);
			for (int j = 0; j < menuItemsHaut[i].length; j++) {
				menuItemsHaut[i][j].setText(nomsMenuItems[i][j]);;
				menusHaut[i].getItems().add(menuItemsHaut[i][j]);
			}
		}
		mFichier.getItems().add(2, new SeparatorMenuItem());
		mEdition.getItems().add(2, new SeparatorMenuItem());
		mbL1.getMenus().addAll(menusHaut);
		
		//II. Barre boutons
		bpL2 = new BorderPane();
		bpL2.getStyleClass().add("haut-l2-border-pane");
		
		//II.1 Boutons gauche
		hbL2Gauche = new HBox();
		hbL2Gauche.getStyleClass().add("haut-l2-gauche-hbox");
		bNouveau = new Button();
		bOuvrir = new Button();
		bEnregistrer = new Button();
		bCouper = new Button();
		bCopier = new Button();
		bColler = new Button();
		bAnnuler= new Button();
		bRepeter = new Button();
		Button[] buttonsHaut = {bNouveau, bOuvrir, bEnregistrer, bCouper, bCopier, bColler, bAnnuler, bRepeter};
		String[] tooltipsButtons = {"Nouveau", "Ouvrir", "Enregistrer", "Couper", "Copier", "Coller", "Annuler", "R�p�ter"};
		for (int i = 0; i < buttonsHaut.length; i++) {
			buttonsHaut[i].setTooltip(new Tooltip(tooltipsButtons[i]));
			buttonsHaut[i].getStyleClass().addAll("haut-l2-button", "haut-l2-button" + (i + 1));
		}
		hbL2Gauche.getChildren().addAll(buttonsHaut);
		for (int i : new int[] {3, 7, 10}) {
			Separator currentSeparator = new Separator();
			currentSeparator.getStyleClass().add("haut-l2-separator");
			hbL2Gauche.getChildren().add(i, currentSeparator);
		}
		
		//II.2 Boutons droite
		hbL2Droite = new HBox();
		hbL2Droite.getStyleClass().add("haut-l2-hbox");
		tbGrille = new ToggleButton();
		tbCouleurs = new ToggleButton();
		tbOutils = new ToggleButton();
		tbInformations = new ToggleButton();
		ToggleButton[] toggleButtonsHaut = {tbGrille, tbCouleurs, tbOutils, tbInformations};
		String[] tooltipsToggleButtons = {"Afficher la grille", "Couleurs", "Outils", "Informations"};
		for (int i = 1; i < toggleButtonsHaut.length; i++) {
			toggleButtonsHaut[i].setTooltip(new Tooltip(tooltipsToggleButtons[i]));
			toggleButtonsHaut[i].getStyleClass().addAll("haut-l2-toggle-button", "haut-l2-toggle-button" + (i + 1));
		}
		hbL2Droite.getChildren().addAll(toggleButtonsHaut);
		tbGrille.setTooltip(new Tooltip(tooltipsToggleButtons[0]));
		tbGrille.getStyleClass().addAll("haut-l2-toggle-button", "haut-l2-toggle-button1");
		hbL2Gauche.getChildren().add(tbGrille);
		
		bpL2.setLeft(hbL2Gauche);
		bpL2.setRight(hbL2Droite);
		
		getChildren().addAll(mbL1, bpL2);
	}

	public MenuBar getMbL1() {
		return mbL1;
	}

	public Menu getmFichier() {
		return mFichier;
	}

	public Menu getmEdition() {
		return mEdition;
	}

	public Menu getmAffichage() {
		return mAffichage;
	}

	public MenuItem getMiNouveau() {
		return miNouveau;
	}

	public MenuItem getMiOuvrir() {
		return miOuvrir;
	}

	public MenuItem getMiEnregistrer() {
		return miEnregistrer;
	}

	public MenuItem getMiAnnuler() {
		return miAnnuler;
	}

	public MenuItem getMiRepeter() {
		return miRepeter;
	}

	public MenuItem getMiCouper() {
		return miCouper;
	}

	public MenuItem getMiCopier() {
		return miCopier;
	}

	public MenuItem getMiColler() {
		return miColler;
	}

	public MenuItem getMiZoomAvant() {
		return miZoomAvant;
	}

	public MenuItem getMiZoomArriere() {
		return miZoomArriere;
	}

	public MenuItem getMiTailleReelle() {
		return miTailleReelle;
	}

	public MenuItem getMiAjuster() {
		return miAjuster;
	}

	public BorderPane getBpL2() {
		return bpL2;
	}

	public HBox getHbL2Gauche() {
		return hbL2Gauche;
	}

	public HBox getHbL2Droite() {
		return hbL2Droite;
	}

	public Button getbNouveau() {
		return bNouveau;
	}

	public Button getbOuvrir() {
		return bOuvrir;
	}

	public Button getbEnregistrer() {
		return bEnregistrer;
	}

	public Button getbCouper() {
		return bCouper;
	}

	public Button getbCopier() {
		return bCopier;
	}

	public Button getbColler() {
		return bColler;
	}

	public Button getbAnnuler() {
		return bAnnuler;
	}

	public Button getbRepeter() {
		return bRepeter;
	}

	public ToggleButton getTbGrille() {
		return tbGrille;
	}

	public ToggleButton getTbCouleurs() {
		return tbCouleurs;
	}

	public ToggleButton getTbOutils() {
		return tbOutils;
	}

	public ToggleButton getTbInformations() {
		return tbInformations;
	}
}
