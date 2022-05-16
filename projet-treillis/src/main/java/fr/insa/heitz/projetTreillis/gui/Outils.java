package fr.insa.heitz.projetTreillis.gui;

import fr.insa.heitz.projetTreillis.gui.Controleur.Etat;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Outils extends VBox {

	private Titre bpTitre;                
    private GridPane gpOutils;
    private ToggleButton tbSelection;
    private ToggleButton tbDeplacerSelection;
    private ToggleButton tbNoeud;
    private ToggleButton tbBarre;
    
    public Outils(MainBorderPane bpMain) {               
		//Titre
    	bpTitre = new Titre("Outils", "outils-titre");
                
		//Toggle Buttons
		gpOutils = new GridPane();
		gpOutils.getStyleClass().add("outils-corps-grid-pane");
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
				toggleButtonsOutils[i][j].getStyleClass().addAll("outils-corps-toggle-button", "outils-corps-toggle-button" + (i + 1) + (j + 1));
				gpOutils.add(toggleButtonsOutils[i][j], j, i);
			}
		}
		
		getChildren().addAll(bpTitre, gpOutils);
		getStyleClass().add("outils-vbox");
		StackPane.setAlignment(this, Pos.TOP_LEFT);
		
		tbSelection.setOnAction(event -> {
			if (tbSelection.isSelected()) {
				bpMain.getControleur().changeEtat(Etat.SELECTION);
			}
			else {
				bpMain.getControleur().changeEtat(Etat.DEFAUT);
			}
		});
				
		tbDeplacerSelection.setOnAction(event -> {
			if (tbDeplacerSelection.isSelected()) {
				bpMain.getControleur().changeEtat(Etat.DEPLACER_SELECTION);
			}
			else {
				bpMain.getControleur().changeEtat(Etat.DEFAUT);
			}		
		});
		
		tbNoeud.setOnAction(event -> {
			if (tbNoeud.isSelected()) {
				bpMain.getControleur().changeEtat(Etat.POINT);
			}
			else {
				bpMain.getControleur().changeEtat(Etat.DEFAUT);
			}
		});
		
		tbBarre.setOnAction(event -> {
			if (tbBarre.isSelected()) {
				bpMain.getControleur().changeEtat(Etat.SEGMENT_P1);
			}
			else {
				bpMain.getControleur().changeEtat(Etat.DEFAUT);
			}
		});
    }

	public Titre getBpTitre() {
		return bpTitre;
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
}
