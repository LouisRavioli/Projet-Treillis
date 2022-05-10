package fr.insa.heitz.projetTreillis.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SelecteurCouleur extends VBox {

	private Label lCouleurChoisie;
	private GridPane gpPalette;
	
	public SelecteurCouleur() {
		//I. Couleur choisie
		Rectangle rGraphicCouleur = new Rectangle(15, 15, Color.BLACK);
		rGraphicCouleur.getStyleClass().add("gauche-couleurs-l2-graphic");
		lCouleurChoisie = new Label("Noir", rGraphicCouleur);
		lCouleurChoisie.getStyleClass().add("gauche-couleurs-l2-label");
		
		//II. Palette
		gpPalette = new GridPane();
		gpPalette.getStyleClass().add("gauche-couleurs-l2-grid-pane");
		String[][][] paletteId = {
			{{"330000"}, {"331900"}, {"333300"}, {"193300"}, {"003300"}, {"003319"}, {"003333"}, {"001933"}, {"000033"}, {"190033"}, {"330033"}, {"330019"}, {"000000"}},
			{{"660000"}, {"663300"}, {"666600"}, {"336600"}, {"006600"}, {"006633"}, {"006666"}, {"003366"}, {"000066"}, {"330066"}, {"660066"}, {"660033"}, {"202020"}},
			{{"990000"}, {"994C00"}, {"999900"}, {"4C9900"}, {"009900"}, {"00994C"}, {"009999"}, {"004C99"}, {"000099"}, {"4C0099"}, {"990099"}, {"99004C"}, {"404040"}},
			{{"CC0000"}, {"CC6600"}, {"CCCC00"}, {"66CC00"}, {"00CC00"}, {"00CC66"}, {"00CCCC"}, {"0066CC"}, {"0000CC"}, {"6600CC"}, {"CC00CC"}, {"CC0066"}, {"606060"}},
			{{"FF0000"}, {"FF8000"}, {"FFFF00"}, {"80FF00"}, {"00FF00"}, {"00FF80"}, {"00FFFF"}, {"0080FF"}, {"0000FF"}, {"7F00FF"}, {"FF00FF"}, {"FF007F"}, {"808080"}},
			{{"FF3333"}, {"FF9933"}, {"FFFF33"}, {"99FF33"}, {"33FF33"}, {"33FF99"}, {"33FFFF"}, {"3399FF"}, {"3333FF"}, {"9933FF"}, {"FF33FF"}, {"FF3399"}, {"A0A0A0"}},
			{{"FF6666"}, {"FFB266"}, {"FFFF66"}, {"B2FF66"}, {"66FF66"}, {"66FFB2"}, {"66FFFF"}, {"66B2FF"}, {"6666FF"}, {"B266FF"}, {"FF66FF"}, {"FF66B2"}, {"C0C0C0"}},
			{{"FF9999"}, {"FFCC99"}, {"FFFF99"}, {"CCFF99"}, {"99FF99"}, {"99FFCC"}, {"99FFFF"}, {"99CCFF"}, {"9999FF"}, {"CC99FF"}, {"FF99FF"}, {"FF99CC"}, {"E0E0E0"}},
			{{"FFCCCC"}, {"FFE5CC"}, {"FFFFCC"}, {"E5FFCC"}, {"CCFFCC"}, {"CCFFE5"}, {"CCFFFF"}, {"CCE5FF"}, {"CCCCFF"}, {"E5CCFF"}, {"FFCCFF"}, {"FFCCE5"}, {"FFFFFF"}}
		};
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 13; j++) {
				Button currentColorButton = new Button(null, new Rectangle(15, 15, Color.web(paletteId[i][j][0])));
				currentColorButton.getStyleClass().add("gauche-couleurs-l2-color-button");
				gpPalette.add(currentColorButton, j, i);
			}
		}
		
		getChildren().addAll(lCouleurChoisie, gpPalette);
	}
}
