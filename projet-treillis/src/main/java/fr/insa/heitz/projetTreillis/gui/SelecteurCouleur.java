package fr.insa.heitz.projetTreillis.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SelecteurCouleur extends VBox {
	
	public static final String[][][] PALETTE = {
		{{"330000", "Rouge 1"}, {"331900", "Orange 1"}, {"333300", "Jaune 1"}, {"193300", "Vert 11"}, {"003300", "Vert 21"}, {"003319", "Vert 31"}, {"003333", "Bleu cyan 1"}, {"001933", "Bleu 1"}, {"000033", "Bleu foncé 1"}, {"190033", "Violet 1"}, {"330033", "Magenta 1"}, {"330019", "Rose 1"}, {"000000", "Noir"}},
		{{"660000", "Rouge 2"}, {"663300", "Orange 2"}, {"666600", "Jaune 2"}, {"336600", "Vert 12"}, {"006600", "Vert 22"}, {"006633", "Vert 32"}, {"006666", "Bleu cyan 2"}, {"003366", "Bleu 2"}, {"000066", "Bleu foncé 2"}, {"330066", "Violet 2"}, {"660066", "Magenta 2"}, {"660033", "Rose 2"}, {"202020", "Gris 1"}},
		{{"990000", "Rouge 3"}, {"994C00", "Orange 3"}, {"999900", "Jaune 3"}, {"4C9900", "Vert 13"}, {"009900", "Vert 23"}, {"00994C", "Vert 33"}, {"009999", "Bleu cyan 3"}, {"004C99", "Bleu 3"}, {"000099", "Bleu foncé 3"}, {"4C0099", "Violet 3"}, {"990099", "Magenta 3"}, {"99004C", "Rose 3"}, {"404040", "Gris 2"}},
		{{"CC0000", "Rouge 4"}, {"CC6600", "Orange 4"}, {"CCCC00", "Jaune 4"}, {"66CC00", "Vert 14"}, {"00CC00", "Vert 24"}, {"00CC66", "Vert 34"}, {"00CCCC", "Bleu cyan 4"}, {"0066CC", "Bleu 4"}, {"0000CC", "Bleu foncé 4"}, {"6600CC", "Violet 4"}, {"CC00CC", "Magenta 4"}, {"CC0066", "Rose 4"}, {"606060", "Gris 3"}},
		{{"FF0000", "Rouge 5"}, {"FF8000", "Orange 5"}, {"FFFF00", "Jaune 5"}, {"80FF00", "Vert 15"}, {"00FF00", "Vert 25"}, {"00FF80", "Vert 35"}, {"00FFFF", "Bleu cyan 5"}, {"0080FF", "Bleu 5"}, {"0000FF", "Bleu foncé 5"}, {"7F00FF", "Violet 5"}, {"FF00FF", "Magenta 5"}, {"FF007F", "Rose 5"}, {"808080", "Gris 4"}},
		{{"FF3333", "Rouge 6"}, {"FF9933", "Orange 6"}, {"FFFF33", "Jaune 6"}, {"99FF33", "Vert 16"}, {"33FF33", "Vert 26"}, {"33FF99", "Vert 36"}, {"33FFFF", "Bleu cyan 6"}, {"3399FF", "Bleu 6"}, {"3333FF", "Bleu foncé 6"}, {"9933FF", "Violet 6"}, {"FF33FF", "Magenta 6"}, {"FF3399", "Rose 6"}, {"A0A0A0", "Gris 5"}},
		{{"FF6666", "Rouge 7"}, {"FFB266", "Orange 7"}, {"FFFF66", "Jaune 7"}, {"B2FF66", "Vert 17"}, {"66FF66", "Vert 27"}, {"66FFB2", "Vert 37"}, {"66FFFF", "Bleu cyan 7"}, {"66B2FF", "Bleu 7"}, {"6666FF", "Bleu foncé 7"}, {"B266FF", "Violet 7"}, {"FF66FF", "Magenta 7"}, {"FF66B2", "Rose 7"}, {"C0C0C0", "Gris 6"}},
		{{"FF9999", "Rouge 8"}, {"FFCC99", "Orange 8"}, {"FFFF99", "Jaune 8"}, {"CCFF99", "Vert 18"}, {"99FF99", "Vert 28"}, {"99FFCC", "Vert 38"}, {"99FFFF", "Bleu cyan 8"}, {"99CCFF", "Bleu 8"}, {"9999FF", "Bleu foncé 8"}, {"CC99FF", "Violet 8"}, {"FF99FF", "Magenta 8"}, {"FF99CC", "Rose 8"}, {"E0E0E0", "Gris 8"}},
		{{"FFCCCC", "Rouge 9"}, {"FFE5CC", "Orange 9"}, {"FFFFCC", "Jaune 9"}, {"E5FFCC", "Vert 19"}, {"CCFFCC", "Vert 29"}, {"CCFFE5", "Vert 39"}, {"CCFFFF", "Bleu cyan 9"}, {"CCE5FF", "Bleu 9"}, {"CCCCFF", "Bleu foncé 9"}, {"E5CCFF", "Violet 9"}, {"FFCCFF", "Magenta 9"}, {"FFCCE5", "Rose 9"}, {"FFFFFF", "Blanc"}}
	};
	
	private Label lCouleurChoisie;
	private Color couleur;
	private Rectangle rGraphicCouleur;
	private GridPane gpPalette;
	
	public SelecteurCouleur(MainBorderPane bpMain, String style) {		
		//Couleur choisie
		couleur = Color.BLACK;
		rGraphicCouleur = new Rectangle(15, 15, couleur);
		rGraphicCouleur.getStyleClass().add(style + "-graphic");
		lCouleurChoisie = new Label("Noir", rGraphicCouleur);
		lCouleurChoisie.getStyleClass().add(style + "-label");
		
		//Palette
		gpPalette = new GridPane();
		gpPalette.getStyleClass().add(style + "-grid-pane");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 13; j++) {
				Color c = Color.web(PALETTE[i][j][0]);
				String nom = PALETTE[i][j][1];
				Button currentColorButton = new Button(null, new Rectangle(15, 15, c));
				currentColorButton.getStyleClass().add(style + "-button");
				currentColorButton.setOnAction(event -> {
					bpMain.getControleur().clicBoutonCouleur(nom, c);
				});
				gpPalette.add(currentColorButton, j, i);
			}
		}
		
		getChildren().addAll(lCouleurChoisie, gpPalette);
	}
	
	public Label getlCouleurChoisie() {
		return lCouleurChoisie;
	}
	
	public Color getCouleur() {
		return couleur;
	}
	
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
		rGraphicCouleur.setFill(couleur);
		
	}

	public GridPane getGpPalette() {
		return gpPalette;
	}
}
