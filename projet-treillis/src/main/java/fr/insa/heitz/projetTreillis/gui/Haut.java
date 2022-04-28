package fr.insa.heitz.projetTreillis.gui;

import java.util.ArrayList;

import javafx.geometry.Orientation;
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
	
	private ArrayList<Menu> menusL1;
	private ArrayList<Button> buttonsL2;
	private ArrayList<ToggleButton> toggleButtonsL2;
	
	public Haut() {
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
		
		hbL2Gauche = new HBox();
		hbL2Gauche.getStyleClass().add("haut-l2-hbox");
		String[] toolTipsButtons = {"Nouveau", "Ouvrir", "Enregistrer", "Couper", "Copier", "Coller", "Annuler", "Répéter"};
		int[] indicesSeparateursL2 = {3, 7};
		for (int i = 0; i < toolTipsButtons.length; i++) {
			Button currentButton = new Button();
			currentButton.setTooltip(new Tooltip(toolTipsButtons[i]));
			currentButton.getStyleClass().addAll("haut-l2-button", "haut-l2-button" + (i + 1));
			hbL2Gauche.getChildren().add(currentButton);
		}
		for (int i : indicesSeparateursL2) {
			Separator currentSeparator = new Separator();
			currentSeparator.getStyleClass().add("haut-l2-separator");
			hbL2Gauche.getChildren().add(i, currentSeparator);
		}
		bpL2 = new BorderPane(null, null, null, null, hbL2Gauche);
		bpL2.getStyleClass().add("haut-l2-border-pane");
		
		getChildren().addAll(mbL1, bpL2);
	}
}
