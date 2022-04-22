package fr.insa.heitz.projetTreillis.gui;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.MenuButton;
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
		for (int i = 0; i <= 2; i++) {
			composantsHaut.add(new MenuButton());
		}
		
		vbMainHaut = new MainHaut(composantsHaut);
		hbMainBas = new MainBas(composantsBas);
		vbMainGauche = new MainGauche(composantsGauche);
		vbMainDroite = new MainDroite(composantsDroite);
		spMainCentre = new MainCentre();
		
		setTop(vbMainHaut);
		setBottom(hbMainBas);
		setLeft(vbMainGauche);
		setRight(vbMainDroite);
		setCenter(spMainCentre);
	}
}
