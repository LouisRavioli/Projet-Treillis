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
	private BorderPane bpL2;
	private HBox hbL2Gauche;
	private HBox hbL2Droite;
		
	public Haut() {
		//I. Barre menu
		mbL1 = new MenuBar();
		mbL1.getStyleClass().add("haut-l1-menu-bar");
		String[] nomsMenus = {"Fichier", "Edition", "Affichage"};
		String[][] nomsMenuItems = {{"Nouveau", "Ouvrir", "Enregistrer"}, {"Annuler", "Répéter", "Couper", "Copier", "Coller"}, {"Zoom avant", "Zoom arrière", "Taille réelle", "Ajuster à la fenêtre"}};
		int[] indicesSeparateursL1 = {2, 2, -1};
		for (int i = 0; i < nomsMenus.length; i++) {
			Menu currentMenu = new Menu(nomsMenus[i]);
			for (int j = 0; j < nomsMenuItems[i].length; j++) {
				currentMenu.getItems().add(new MenuItem(nomsMenuItems[i][j]));
			}
			if (indicesSeparateursL1[i] != -1) {
				currentMenu.getItems().add(indicesSeparateursL1[i], new SeparatorMenuItem());
			}
			mbL1.getMenus().add(currentMenu);
		}
		
		//II. Barre boutons
		//II.1 Boutons gauche
		hbL2Gauche = new HBox();
		hbL2Gauche.getStyleClass().add("haut-l2-gauche-hbox");
		String[] tooltipsButtonsL2 = {"Nouveau", "Ouvrir", "Enregistrer", "Couper", "Copier", "Coller", "Annuler", "Répéter"};
		for (int i = 0; i < tooltipsButtonsL2.length; i++) {
			Button currentButton = new Button();
			currentButton.setTooltip(new Tooltip(tooltipsButtonsL2[i]));
			currentButton.getStyleClass().addAll("haut-l2-button", "haut-l2-button" + (i + 1));
			hbL2Gauche.getChildren().add(currentButton);
		}
		for (int i : new int[] {3, 7, 10}) {
			Separator currentSeparator = new Separator();
			currentSeparator.getStyleClass().add("haut-l2-separator");
			hbL2Gauche.getChildren().add(i, currentSeparator);
		}
		//II.2 Boutons droite
		hbL2Droite = new HBox();
		hbL2Droite.getStyleClass().add("haut-l2-hbox");
		String[] tooltipsToggleButtonsL2 = {"Afficher la grille", "Couleurs", "Outils"};
		ToggleButton tbGrille = new ToggleButton();
		tbGrille.setTooltip(new Tooltip(tooltipsToggleButtonsL2[0]));
		tbGrille.getStyleClass().addAll("haut-l2-toggle-button", "haut-l2-toggle-button1");
		hbL2Gauche.getChildren().add(tbGrille);
		for (int i = 1; i < tooltipsToggleButtonsL2.length; i++) {
			ToggleButton currentToggleButton = new ToggleButton();
			currentToggleButton.setTooltip(new Tooltip(tooltipsToggleButtonsL2[i]));
			currentToggleButton.getStyleClass().addAll("haut-l2-toggle-button", "haut-l2-toggle-button" + (i + 1));
			hbL2Droite.getChildren().add(currentToggleButton);
		}
		bpL2 = new BorderPane(null, null, hbL2Droite, null, hbL2Gauche);
		bpL2.getStyleClass().add("haut-l2-border-pane");
		
		getChildren().addAll(mbL1, bpL2);
	}
}
