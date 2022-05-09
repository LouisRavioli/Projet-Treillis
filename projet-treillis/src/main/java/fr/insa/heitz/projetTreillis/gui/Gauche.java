package fr.insa.heitz.projetTreillis.gui;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Gauche extends GridPane {
	
	private VBox vbOutils;
	private GridPane gpOutils;
	private VBox vbCouleurs;

	public Gauche() {
		//I. Outils
		//I.1 Titre
		vbOutils = new VBox();
		vbOutils.getStyleClass().add("gauche-outils-vbox");
		Label lOutilsL1 = new Label("Outils");
		lOutilsL1.getStyleClass().add("gauche-outils-l1-label");
		Button bOutilsL1 = new Button();
		bOutilsL1.getStyleClass().add("gauche-outils-l1-button");
		BorderPane bpOutilsL1 = new BorderPane(null, null, bOutilsL1, null, lOutilsL1);
		bpOutilsL1.getStyleClass().add("gauche-outils-l1-border-pane");
		//I.2 Boutons
		gpOutils = new GridPane();
		gpOutils.getStyleClass().add("gauche-outils-l2-grid-pane");
		String[][] tooltipsButtonsOutils = {{"Sélection", "Déplacer la sélection"}, {"Noeud", "Barre"}};
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				ToggleButton currentToggleButton = new ToggleButton();
				currentToggleButton.setTooltip(new Tooltip(tooltipsButtonsOutils[j][i]));
				currentToggleButton.getStyleClass().addAll("gauche-outils-l2-toggle-button", "gauche-outils-l2-toggle-button" + (j + 1) + (i + 1));
				gpOutils.add(currentToggleButton, i, j);
			}
		}
		vbOutils.getChildren().addAll(bpOutilsL1, gpOutils);
		
		//II. Couleurs
		//II.1 Titre
		vbCouleurs = new VBox();
		vbOutils.getStyleClass().add("gauche-couleurs-vbox");
		Label lCouleursL1 = new Label("Couleurs");
		lCouleursL1.getStyleClass().add("gauche-couleurs-l1-label");
		Button bCouleursL1 = new Button();
		bCouleursL1.getStyleClass().add("gauche-couleurs-l1-button");
		BorderPane bpCouleursL1 = new BorderPane(null, null, bCouleursL1, null, lCouleursL1);
		bpCouleursL1.getStyleClass().add("gauche-couleurs-l1-border-pane");
		//II.2 Sélection couleurs
		ColorPicker cpCouleurs = new ColorPicker(Color.BLACK);
		cpCouleurs.getStyleClass().add("gauche-couleurs-l2-color-picker");
		cpCouleurs.show();
		
		vbCouleurs.getChildren().addAll(bpCouleursL1, cpCouleurs);
		
		add(vbOutils, 0, 0);
		add(vbCouleurs, 0, 1, 2, 1);
		getStyleClass().add("gauche-grid-pane");
	}
}
