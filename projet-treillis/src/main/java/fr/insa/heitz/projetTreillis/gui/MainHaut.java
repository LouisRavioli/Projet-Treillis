package fr.insa.heitz.projetTreillis.gui;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainHaut extends VBox {

	private HBox l1;
	private BorderPane l2;
	private HBox l2Gauche;
	private HBox l2Droite;
	
	public MainHaut(ArrayList<Node> composants) {
		l1 = new HBox();
		//l1.getChildren().addAll(composants.subList(0, 0));
		
		l2Gauche = new HBox();
		//l2Gauche.getChildren().addAll(composants.subList(0, 0));
		l2Droite = new HBox();
		//l2Droite.getChildren().addAll(composants.subList(0, 0));
		l2 = new BorderPane(null, null, l2Droite, null, l2Gauche);
		
		getChildren().addAll(l1, l2);
	}
}
