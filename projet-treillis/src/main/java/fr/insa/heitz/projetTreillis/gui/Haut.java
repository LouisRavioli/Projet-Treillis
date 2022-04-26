package fr.insa.heitz.projetTreillis.gui;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Haut extends VBox {

	private HBox l1;
	private BorderPane l2;
	private HBox l2Gauche;
	private HBox l2Droite;
	
	private ArrayList<MenuButton> menuButtonsL1;
	private ArrayList<Button> buttonsL2;
	private ArrayList<ToggleButton> toggleButtonsL2;
	
	public Haut() {
		menuButtonsL1 = new ArrayList<MenuButton>();
		String[] noms = {"Fichier", "Edition", "Affichage"};
		for (int i = 0; i <= 2; i++) {
			MenuButton current = new MenuButton(noms[i]);
			current.getStyleClass().addAll("haut-l1-menu-button", "haut-l1-menu-button" + (i + 1));
			menuButtonsL1.add(current);
		}
		l1 = new HBox();
		l1.getChildren().addAll(menuButtonsL1);
		
		getChildren().addAll(l1);
	}
}
