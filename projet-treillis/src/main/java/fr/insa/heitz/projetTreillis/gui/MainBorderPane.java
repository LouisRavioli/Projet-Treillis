package fr.insa.heitz.projetTreillis.gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainBorderPane extends BorderPane {

	private VBox vbMainHaut;
	private HBox hbMainBas;
	private GridPane vbMainGauche;
	private VBox vbMainDroite;
	private StackPane spMainCentre;
	
	public MainBorderPane() {
		vbMainHaut = new Haut();
		hbMainBas = new Bas();
		vbMainGauche = new Gauche();
		vbMainDroite = new Droite();
		spMainCentre = new Centre();
				
		setTop(vbMainHaut);
		setBottom(hbMainBas);
		setLeft(vbMainGauche);
		setRight(vbMainDroite);
		setCenter(spMainCentre);
	}
}
