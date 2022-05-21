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
	        
	// Methide pour ajouter les infos de chaque barre créée à la VBox à droite        
	public void addInfoBarre(Segment s) {
	    // Création de tous les "label" et "Textfield"
	    Label pDebut = new Label("P début:");
	    pDebut.setStyle("-fx-text-fill: #FFFFFF");
	    Label pFin = new Label("P fin:");
	    pFin.setStyle("-fx-text-fill: #FFFFFF");
	    Label compressionMax = new Label("Comp Max:");
	    compressionMax.setStyle("-fx-text-fill: #FFFFFF");
	    Label tractionMax = new Label("Trac Max:");
	    tractionMax.setStyle("-fx-text-fill: #FFFFFF");
	    Label couleur = new Label("Couleur:");
	    couleur.setStyle("-fx-text-fill: #FFFFFF");
	    Label cout = new Label("Coût:");
	    
	    TextField tFNomDeLaBarre = new TextField();  // jsp comment on veut appeler
	    tFNomDeLaBarre.setStyle("-fx-text-fill: #FFFFFF");
	    TextField tFPDebut = new TextField();
	    tFPDebut.setStyle("-fx-text-fill: #FFFFFF");
	    TextField tFPFin = new TextField(); // je peux prendre Noeud Arrivée ?
	    tFPFin.setStyle("-fx-text-fill: #FFFFFF");
	    TextField tFPy = new TextField();
	    tFPy.setStyle("-fx-text-fill: #FFFFFF");
	    TextField tFCompressionMax = new TextField();
	    tFCompressionMax.setStyle("-fx-text-fill: #FFFFFF");
	    TextField tFTractionMax = new TextField();
	    tFTractionMax.setStyle("-fx-text-fill: #FFFFFF");
	    TextField tFCouleur = new TextField();  // il n'y a pas encore de get pour la couleur dans la classe Barre
	    tFCouleur.setStyle("-fx-text-fill: #FFFFFF");
	    TextField tFCout = new TextField();
	    tFCout.setStyle("-fx-text-fill: #FFFFFF");
	    // gridPane avec les informations pour une barre
	    gpInformations.add(tFNomDeLaBarre,0,0);
	    gpInformations.add(pDebut,1,0);
	    gpInformations.add(tFPDebut,2,0);
	    gpInformations.add(pFin, 1, 1);
	    gpInformations.add(tFPFin,2,1);
	    gpInformations.add(couleur, 1, 2);
	    gpInformations.add(tFCouleur, 2, 2);
	    gpInformations.add(compressionMax, 3, 1);
	    gpInformations.add(tFCompressionMax, 4, 0);
	    gpInformations.add(tractionMax, 3, 1);
	    gpInformations.add(tFTractionMax, 4, 1);
	    gpInformations.add(couleur, 3, 2);
	    gpInformations.add(tFCout, 4, 2);
	    gpInformations.setHgap(8);
	    gpInformations.setVgap(5);
	}
}
