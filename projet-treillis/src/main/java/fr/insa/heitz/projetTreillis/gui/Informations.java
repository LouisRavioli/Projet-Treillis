package fr.insa.heitz.projetTreillis.gui;

import fr.insa.heitz.projetTreillis.dessin.Point;
import fr.insa.heitz.projetTreillis.dessin.Segment;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Informations extends VBox {

	private MainBorderPane bpMain;
	
	private Titre bpTitre;
	private ScrollPane spInformations;
	private VBox vbContainer;

	public Informations(MainBorderPane bpMain) {
		this.bpMain = bpMain;
		
		//Titre
		bpTitre = new Titre("Informations", "informations-titre", bpMain.getControleur(), this);
		
		//Informations
		vbContainer = new VBox();
		vbContainer.getStyleClass().add("informations-container");
		spInformations = new ScrollPane(vbContainer);
		spInformations.getStyleClass().add("informations-scroll-pane");
		spInformations.prefHeightProperty().bind(heightProperty());
		
		getChildren().addAll(bpTitre, spInformations);
		getStyleClass().add("informations-vbox");
		StackPane.setAlignment(this, Pos.TOP_RIGHT);
	}

	public Titre getBpTitre() {
		return bpTitre;
	}
	
	public ScrollPane getSpInformations() {
		return spInformations;
	}

	public VBox getVbContainer() {
		return vbContainer;
	}

	public void addLignePoint(Point p) {
	    vbContainer.getChildren().add(new LigneInformationPoint(bpMain, p));
	}
	
	public void addLigneSegment(Segment s) {
		vbContainer.getChildren().add(new LigneInformationSegment(bpMain, s));
	}      
}
