package fr.insa.heitz.projetTreillis.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Gauche extends GridPane {	
	private VBox vbOutils;
        private Titre bpTitreOutils;                
	private GridPane gpOutils;
        private ToggleButton tbSelection;
        private ToggleButton tbDeplacerSelection;
        private ToggleButton tbNoeud;
        private ToggleButton tbBarre;
        
	private VBox vbCouleurs;
        private Titre bpTitreCouleurs;
	private SelecteurCouleur selecteurCouleur;

	public Gauche() {
		//I. Outils
                vbOutils = new VBox();
		vbOutils.getStyleClass().add("gauche-outils-vbox");
                
		//I.1 Titre
		bpTitreOutils = new Titre("Outils", "gauche-outils-l1");
                
		//I.2 Toggle Buttons
		gpOutils = new GridPane();
		gpOutils.getStyleClass().add("gauche-outils-l2-grid-pane");
                tbSelection = new ToggleButton();
                tbDeplacerSelection = new ToggleButton();
                tbNoeud = new ToggleButton();
                tbBarre = new ToggleButton();
		String[][] tooltipsButtonsOutils = {{"Sélection", "Déplacer la sélection"}, {"Noeud", "Barre"}};
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				ToggleButton currentToggleButton = new ToggleButton();
				currentToggleButton.setTooltip(new Tooltip(tooltipsButtonsOutils[j][i]));
				currentToggleButton.getStyleClass().addAll("gauche-outils-l2-toggle-button", "gauche-outils-l2-toggle-button" + (j + 1) + (i + 1));
				gpOutils.add(currentToggleButton, i, j);
			}
		}
		vbOutils.getChildren().addAll(bpTitreOutils, gpOutils);
		
		//II. Couleurs
		//II.1 Titre
		vbCouleurs = new VBox();
		vbCouleurs.getStyleClass().add("gauche-couleurs-vbox");
		Label lCouleursL1 = new Label("Couleurs");
		lCouleursL1.getStyleClass().add("gauche-couleurs-l1-label");
		Button bCouleursL1 = new Button();
		bCouleursL1.getStyleClass().add("gauche-couleurs-l1-button");
		BorderPane bpCouleursL1 = new BorderPane(null, null, bCouleursL1, null, lCouleursL1);
		bpCouleursL1.getStyleClass().add("gauche-couleurs-l1-border-pane");
		//II.2 Sélection couleurs
		selecteurCouleur = new SelecteurCouleur();
		vbCouleurs.getChildren().addAll(bpCouleursL1, selecteurCouleur);
		
		add(vbOutils, 0, 0);
		add(vbCouleurs, 0, 1, 2, 1);
		getStyleClass().add("gauche-grid-pane");
	}
}
