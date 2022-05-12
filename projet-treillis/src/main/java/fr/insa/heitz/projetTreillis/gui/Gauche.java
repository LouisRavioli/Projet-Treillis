package fr.insa.heitz.projetTreillis.gui;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
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
		ToggleGroup tgOutils = new ToggleGroup();
        ToggleButton[][] toggleButtonsOutils = {{tbSelection, tbDeplacerSelection}, {tbNoeud, tbBarre}};
 		String[][] tooltipsButtonsOutils = {{"Sélection", "Déplacer la sélection"}, {"Noeud", "Barre"}};
		for (int i = 0; i < toggleButtonsOutils.length; i++) {
			for (int j = 0; j < 2; j++) {
				toggleButtonsOutils[i][j].setToggleGroup(tgOutils);
				toggleButtonsOutils[i][j].setTooltip(new Tooltip(tooltipsButtonsOutils[i][j]));
				toggleButtonsOutils[i][j].getStyleClass().addAll("gauche-outils-l2-toggle-button", "gauche-outils-l2-toggle-button" + (i + 1) + (j + 1));
				gpOutils.add(toggleButtonsOutils[i][j], j, i);
			}
		}
		vbOutils.getChildren().addAll(bpTitreOutils, gpOutils);
		
		//II. Couleurs
		vbCouleurs = new VBox();
		vbCouleurs.getStyleClass().add("gauche-couleurs-vbox");
		
		//II.1 Titre
		bpTitreCouleurs = new Titre("Couleurs", "gauche-couleurs-l1");
		
		//II.2 Sélection couleurs
		selecteurCouleur = new SelecteurCouleur();
		
		vbCouleurs.getChildren().addAll(bpTitreCouleurs, selecteurCouleur);
		
		add(vbOutils, 0, 0);
		add(vbCouleurs, 0, 1, 2, 1);
		getStyleClass().add("gauche-grid-pane");
	}

	public VBox getVbOutils() {
		return vbOutils;
	}

	public Titre getBpTitreOutils() {
		return bpTitreOutils;
	}

	public GridPane getGpOutils() {
		return gpOutils;
	}

	public ToggleButton getTbSelection() {
		return tbSelection;
	}

	public ToggleButton getTbDeplacerSelection() {
		return tbDeplacerSelection;
	}

	public ToggleButton getTbNoeud() {
		return tbNoeud;
	}

	public ToggleButton getTbBarre() {
		return tbBarre;
	}

	public VBox getVbCouleurs() {
		return vbCouleurs;
	}

	public Titre getBpTitreCouleurs() {
		return bpTitreCouleurs;
	}

	public SelecteurCouleur getSelecteurCouleur() {
		return selecteurCouleur;
	}
}
