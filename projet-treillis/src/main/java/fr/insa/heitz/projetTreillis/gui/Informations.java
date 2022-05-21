package fr.insa.heitz.projetTreillis.gui;

import fr.insa.heitz.projetTreillis.dessin.Point;
import fr.insa.heitz.projetTreillis.dessin.Segment;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Informations extends VBox {

	private MainBorderPane bpMain;
	
	private Titre bpTitre;
	private GridPane gpInformations;

	public Informations(MainBorderPane bpMain) {
		this.bpMain = bpMain;
		
		//Titre
		bpTitre = new Titre("Informations", "informations-titre", bpMain.getControleur(), this);
		
		//Informations
		gpInformations = new GridPane();
		
		getChildren().addAll(bpTitre, gpInformations);
		getStyleClass().add("informations-vbox");
		StackPane.setAlignment(this, Pos.TOP_RIGHT);
	}

	public Titre getBpTitre() {
		return bpTitre;
	}
	
	public GridPane getGpInformations() {
		return gpInformations;
	}
	
	public void addLignePoint(Point p) {
	    getChildren().add(new LigneInformationPoint(bpMain, p));
	}
	
	public void addLigneSegment(Segment s) {
	    getChildren().add(new LigneInformationSegment(bpMain, s));
	}      
}
