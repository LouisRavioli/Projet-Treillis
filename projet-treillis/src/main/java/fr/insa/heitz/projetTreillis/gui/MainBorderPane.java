package fr.insa.heitz.projetTreillis.gui;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainBorderPane extends BorderPane{

	private VBox vbMainHaut;
	private HBox hbMainBas;
	private VBox vbMainGauche;
	private VBox vbMainDroite;
	private StackPane spMainCentre;
	
	private ArrayList<Node> composantsHaut;
	private ArrayList<Node> composantsBas;
	private ArrayList<Node> composantsGauche;
	private ArrayList<Node> composantsDroite;
	
	
	public MainBorderPane() {
		composantsHaut = new ArrayList<Node>();
		addMenuButton(composantsHaut, 3, "haut-l1-menu-button");
		addButton(composantsHaut, 8, "haut-l2-button");
		addToggleButton(composantsHaut, 3, "haut-l2-toggle-button");
		addButton(composantsHaut, 2, "");
		composantsHaut.add(6, new Label("|"));
		composantsHaut.add(10, new Label("|"));
				
		vbMainHaut = new Haut(composantsHaut);
		hbMainBas = new Bas(composantsBas);
		vbMainGauche = new Gauche(composantsGauche);
		vbMainDroite = new Droite(composantsDroite);
		spMainCentre = new Centre();
				
		setTop(vbMainHaut);
		setBottom(hbMainBas);
		setLeft(vbMainGauche);
		setRight(vbMainDroite);
		setCenter(spMainCentre);
	}
	
	static void addMenuButton(ArrayList<Node> list, int nombre, String... styleClasses) {
		for (int i = 1; i <= nombre; i++) {
			MenuButton current = new MenuButton();
			current.getStyleClass().addAll(styleClasses);
			list.add(current);
		}
	}
	
	static void addButton(ArrayList<Node> list, int nombre, String... styleClasses) {
		for (int i = 1; i <= nombre; i++) {
			Button current = new Button();
			current.getStyleClass().addAll(styleClasses);
			list.add(current);
		}
	}
	
	static void addToggleButton(ArrayList<Node> list, int nombre, String... styleClasses) {
		for (int i = 1; i <= nombre; i++) {
			ToggleButton current = new ToggleButton();
			current.getStyleClass().addAll(styleClasses);
			list.add(current);
		}
	}
}
