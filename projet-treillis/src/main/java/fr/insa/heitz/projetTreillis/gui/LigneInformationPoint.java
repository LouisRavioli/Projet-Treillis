package fr.insa.heitz.projetTreillis.gui;

import fr.insa.heitz.projetTreillis.Noeud;
import fr.insa.heitz.projetTreillis.dessin.Point;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class LigneInformationPoint extends TitledPane {

	private Point p;
	private Label lCouleur;
	private Label lPosition;
	private Label lForce;
	private HBox hbCouleur;
	private Rectangle rCouleur;
	private TextField tfCouleur;
	private Label lPx;
	private TextField tfPx;
	private Label lPy;
	private TextField tfPy;
	private Label lFx;
	private TextField tfFx;
	private Label lFy;
	private TextField tfFy;
	private GridPane gpInformations;
	
	public LigneInformationPoint(MainBorderPane bpMain, Point p) {
		this.p = p;
		p.setLigne(this);
		
		Noeud n = p.getNoeud();
		lCouleur = new Label("Couleur");
		lCouleur.getStyleClass().add("informations-label");
		lPosition = new Label("Position");
		lPosition.getStyleClass().add("informations-label");
		lForce = new Label("Force");
		lForce.getStyleClass().add("informations-label");
		rCouleur = new Rectangle(18, 18, p.getCouleur());
		rCouleur.getStyleClass().add("informations-rectangle");
		tfCouleur = new TextField(String.format("#%02X%02X%02X", (int) (p.getCouleur().getRed()*255), (int) (p.getCouleur().getGreen()*255), (int) (p.getCouleur().getBlue()*255)));
		tfCouleur.getStyleClass().add("informations-couleur");
		hbCouleur = new HBox(rCouleur, tfCouleur);
		hbCouleur.getStyleClass().add("informations-hbox-couleur");
		lPx = new Label("Px");
		lPx.getStyleClass().add("informations-label");
		tfPx = new TextField(String.valueOf(p.getPx()));
		tfPx.getStyleClass().add("informations-point-coordonnee");
		lPy = new Label("Py");
		lPy.getStyleClass().add("informations-label");
		tfPy = new TextField(String.valueOf(p.getPy()));
		tfPy.getStyleClass().add("informations-point-coordonnee");
		lFx = new Label("Fx");
		lFx.getStyleClass().add("informations-label");
		tfFx = new TextField(String.valueOf(n.getV().getVx()));
		tfFx.getStyleClass().add("informations-point-coordonnee");
		lFy = new Label("Fy");
		lFy.getStyleClass().add("informations-label");
		tfFy = new TextField(String.valueOf(n.getV().getVy()));
		tfFy.getStyleClass().add("informations-point-coordonnee");
		
		gpInformations = new GridPane();
		gpInformations.getStyleClass().add("informations-point-grid-pane");
		gpInformations.add(lCouleur, 0, 0);
		gpInformations.add(lPosition, 2, 0, 2, 1);
		gpInformations.add(lForce, 5, 0, 2, 1);
		gpInformations.add(hbCouleur, 0, 1);
		gpInformations.add(lPx, 2, 1);
		gpInformations.add(tfPx, 3, 1);
		gpInformations.add(lFx, 5, 1);
		gpInformations.add(tfFx, 6, 1);
		gpInformations.add(lPy, 2, 2);
		gpInformations.add(tfPy, 3, 2);
		gpInformations.add(lFy, 5, 2);
		gpInformations.add(tfFy, 6, 2);
		for (int i : new int[] {1, 4}) {
			Separator sep = new Separator();
			sep.getStyleClass().add("informations-point-separator");
			gpInformations.add(sep, i, 0, 1, 3);
		}
		
		setText("Point " + String.valueOf(n.getTreillis().getNoeuds().get(n)));
		setContent(gpInformations);
		setExpanded(false);
		getStyleClass().add("informations-titled-pane");
		
		tfCouleur.textProperty().addListener((observable, oldValue, newValue) -> {
			bpMain.getControleur().refreshCouleur(p, observable, oldValue, newValue);
		});
		
		tfPx.textProperty().addListener((observable, oldValue, newValue) -> {
			bpMain.getControleur().refreshPx(p, observable, oldValue, newValue);
		});
		
		tfPy.textProperty().addListener((observable, oldValue, newValue) -> {
			bpMain.getControleur().refreshPy(p, observable, oldValue, newValue);
		});
		
		tfFx.textProperty().addListener((observable, oldValue, newValue) -> {
			bpMain.getControleur().refreshFx(p, observable, oldValue, newValue);
		});
		
		tfFy.textProperty().addListener((observable, oldValue, newValue) -> {
			bpMain.getControleur().refreshFy(p, observable, oldValue, newValue);
		});
		
		heightProperty().addListener(observable -> {
			bpMain.getControleur().refreshLinePoint(this);
		});
		
		setOnMouseClicked(event -> {
			bpMain.getControleur().openInformationsPoint(this);
		});
	}

	public Point getP() {
		return p;
	}

	public void setP(Point p) {
		this.p = p;
	}

	public Label getlCouleur() {
		return lCouleur;
	}

	public Label getlPosition() {
		return lPosition;
	}

	public Label getlForce() {
		return lForce;
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

	public Label getlPx() {
		return lPx;
	}

	public TextField getTfPx() {
		return tfPx;
	}

	public Label getlPy() {
		return lPy;
	}

	public TextField getTfPy() {
		return tfPy;
	}

	public Label getlFx() {
		return lFx;
	}

	public TextField getTfFx() {
		return tfFx;
	}

	public Label getlFy() {
		return lFy;
	}

	public TextField getTfFy() {
		return tfFy;
	}

	public GridPane getGpInformations() {
		return gpInformations;
	}
}
