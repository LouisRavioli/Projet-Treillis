package fr.insa.heitz.projetTreillis.gui;

import fr.insa.heitz.projetTreillis.Barre;
import fr.insa.heitz.projetTreillis.dessin.Segment;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class LigneInformationSegment extends TitledPane {

	private Segment s;
	private Label lCouleur;
	private Label lExtremites;
	private Label lCaracteristiques;
	private HBox hbCouleur;
	private Rectangle rCouleur;
	private TextField tfCouleur;
	private Label lP1;
	private Label lP2;
	private Label lComp;
	private TextField tfComp;
	private Label lTrac;
	private TextField tfTrac;
	private Label lCout;
	private TextField tfCout;
	private GridPane gpInformations;
	
	public LigneInformationSegment(MainBorderPane bpMain, Segment s) {
		this.s = s;
		s.setLigne(this);
		
		Barre b = s.getBarre();
		lCouleur = new Label("Couleur");
		lCouleur.getStyleClass().add("informations-label");
		lExtremites = new Label("Extrémités");
		lExtremites.getStyleClass().add("informations-label");
		lCaracteristiques = new Label("Caractéristiques");
		lCaracteristiques.getStyleClass().add("informations-label");
		rCouleur = new Rectangle(18, 18, s.getCouleur());
		rCouleur.getStyleClass().add("informations-rectangle");
		tfCouleur = new TextField(String.format("#%02X%02X%02X", (int) (s.getCouleur().getRed()*255), (int) (s.getCouleur().getGreen()*255), (int) (s.getCouleur().getBlue()*255)));
		tfCouleur.getStyleClass().add("informations-couleur");
		hbCouleur = new HBox(rCouleur, tfCouleur);
		hbCouleur.getStyleClass().add("informations-hbox-couleur");
		lP1 = new Label("Point " + b.getTreillis().getNoeuds().get(b.getNoeudDepart()));
		lP1.getStyleClass().add("informations-extremites-label");
		lP2 = new Label("Point " + b.getTreillis().getNoeuds().get(b.getNoeudArrivee()));
		lP2.getStyleClass().add("informations-extremites-label");
		lComp = new Label("Comp.\nmax");
		lComp.getStyleClass().add("informations-label-wrap");
		tfComp = new TextField(String.valueOf(b.getCompMax()));
		tfComp.getStyleClass().add("informations-segment-caracteristique");
		lTrac = new Label("Trac.\nmax");
		lTrac.getStyleClass().add("informations-label-wrap");
		tfTrac = new TextField(String.valueOf(b.getTracMax()));
		tfTrac.getStyleClass().add("informations-segment-caracteristique");
		lCout = new Label("Coût");
		lCout.getStyleClass().add("informations-label-wrap");
		tfCout = new TextField(String.valueOf(b.getCout()));
		tfCout.getStyleClass().add("informations-segment-caracteristique");
		
		gpInformations = new GridPane();
		gpInformations.getStyleClass().add("informations-segment-grid-pane");
		gpInformations.add(lCouleur, 0, 0);
		gpInformations.add(lExtremites, 2, 0);
		gpInformations.add(lCaracteristiques, 4, 0, 2, 1);
		gpInformations.add(hbCouleur, 0, 1);
		gpInformations.add(lP1, 2, 1);
		gpInformations.add(lP2, 2, 2);
		gpInformations.add(lComp, 4, 1);
		gpInformations.add(tfComp, 5, 1);
		gpInformations.add(lTrac, 4, 2);
		gpInformations.add(tfTrac, 5, 2);
		gpInformations.add(lCout, 4, 3);
		gpInformations.add(tfCout, 5, 3);
		for (int i : new int[] {1, 3}) {
			Separator sep = new Separator();
			sep.getStyleClass().add("informations-segment-separator");
			gpInformations.add(sep, i, 0, 1, 4);
		}
		
		setText("Segment " + String.valueOf(b.getTreillis().getBarres().get(b)));
		setContent(gpInformations);
		setExpanded(false);
		getStyleClass().add("informations-titled-pane");
		
		tfCouleur.textProperty().addListener((observable, oldValue, newValue) -> {
			bpMain.getControleur().refreshCouleur(s, observable, oldValue, newValue);
		});
		
		tfComp.textProperty().addListener((observable, oldValue, newValue) -> {
			bpMain.getControleur().refreshComp(s, observable, oldValue, newValue);
		});
		
		tfTrac.textProperty().addListener((observable, oldValue, newValue) -> {
			bpMain.getControleur().refreshTrac(s, observable, oldValue, newValue);
		});
		
		tfCout.textProperty().addListener((observable, oldValue, newValue) -> {
			bpMain.getControleur().refreshCout(s, observable, oldValue, newValue);
		});
		
		heightProperty().addListener(observable -> {
			bpMain.getControleur().refreshLineSegment(this);
		});
		
		lP1.setOnMouseClicked(event -> {
			bpMain.getControleur().clicInformationP1(this);
		});

		lP2.setOnMouseClicked(event -> {
			bpMain.getControleur().clicInformationP2(this);
		});
	}

	public Segment getS() {
		return s;
	}

	public Label getlCouleur() {
		return lCouleur;
	}

	public Label getlExtremites() {
		return lExtremites;
	}

	public Label getlCaracteristiques() {
		return lCaracteristiques;
	}

	public HBox getHbCouleur() {
		return hbCouleur;
	}

	public Rectangle getrCouleur() {
		return rCouleur;
	}

	public TextField getTfCouleur() {
		return tfCouleur;
	}

	public Label getlP1() {
		return lP1;
	}

	public Label getlP2() {
		return lP2;
	}

	public Label getlComp() {
		return lComp;
	}

	public TextField getTfComp() {
		return tfComp;
	}

	public Label getlTrac() {
		return lTrac;
	}

	public TextField getTfTrac() {
		return tfTrac;
	}

	public Label getlCout() {
		return lCout;
	}

	public TextField getTfCout() {
		return tfCout;
	}

	public GridPane getGpInformations() {
		return gpInformations;
	}
}
