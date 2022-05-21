package fr.insa.heitz.projetTreillis.gui;

import java.util.ArrayList;
import java.util.List;

import fr.insa.heitz.projetTreillis.dessin.Figure;
import fr.insa.heitz.projetTreillis.dessin.FigureSimple;
import fr.insa.heitz.projetTreillis.dessin.Groupe;
import fr.insa.heitz.projetTreillis.dessin.Point;
import fr.insa.heitz.projetTreillis.dessin.Segment;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Controleur {
	
	private MainBorderPane bpMain;
	
	public enum Etat {DEFAUT, SELECTION, DEPLACER_SELECTION, POINT, SEGMENT_P1, SEGMENT_P2, EFFACER, FORCE};
	private Etat etat;
	
	private Line curSegment;
	private Point curSegmentP1;
	private boolean curSegmentIsTemp;
	
	private boolean clicForme;
	
	private List<Figure> selection;
	
	private double[] posInitialeSouris;
    
	public Controleur(MainBorderPane bpMain) {
		this.bpMain = bpMain;
		etat = Etat.DEFAUT;
		clicForme = false;
		curSegment = new Line();
		curSegmentIsTemp = false;
		selection = new ArrayList<Figure>();
		posInitialeSouris = new double[2];
	}
	
	public void changeEtat(Etat nouvelEtat) {
		etat = nouvelEtat;
		if ((nouvelEtat != Etat.SELECTION)&&(nouvelEtat != Etat.DEPLACER_SELECTION)) {
			clearSelection();
		}
		if (curSegmentIsTemp) {
			bpMain.getModele().removeFigure(curSegmentP1);
			curSegmentIsTemp = false;
		}
		bpMain.getpZoneDessin().getChildren().remove(curSegment);
		bpMain.getpZoneDessin().dessinerTout();
		refreshSelection();
	}

	public void clicZoneDessin(MouseEvent event) {
		if (clicForme) {
			clicForme = false;
		}
		else {
			Color couleur = bpMain.getVbCouleurs().getSelecteurCouleur().getCouleur();
			switch (etat) {
			case DEFAUT:
				break;	
			case SELECTION:
				if (event.getButton() == MouseButton.SECONDARY) {
					clearSelection();
				}
				break;
			case DEPLACER_SELECTION:
				break;
			case POINT:
				Point p1 = creerPoint(couleur, event.getX(), event.getY());
				bpMain.getpZoneDessin().dessinerTout();
				clearSelection();
				addToSelection(p1);
				break;
			case SEGMENT_P1:
				curSegmentP1 = creerPoint(couleur, event.getX(), event.getY());
				bpMain.getpZoneDessin().dessinerTout();
				clearSelection();
				addToSelection(curSegmentP1);
				addLinePreview(couleur, event.getX(), event.getY());
				curSegmentIsTemp = true;
				etat = Etat.SEGMENT_P2;
				break;
			case SEGMENT_P2:
				bpMain.getpZoneDessin().getChildren().remove(curSegment);
				Point p2 = creerPoint(couleur, event.getX(), event.getY());
				creerSegment(couleur, curSegmentP1, p2);
				curSegmentP1 = null;
				bpMain.getpZoneDessin().dessinerTout();
				clearSelection();
				addToSelection(p2);
				curSegmentIsTemp = false;
				etat = Etat.SEGMENT_P1;
				break;
			case EFFACER:
				break;
			case FORCE:
				break;
			}
		}
	}
		
	public void clicPoint(MouseEvent event, Point p) {
		clicForme = true;
		Color couleur = bpMain.getVbCouleurs().getSelecteurCouleur().getCouleur();
		switch (etat) {
		case DEFAUT:
			break;
		case SELECTION:
			if (event.isControlDown()) {
				if ((selection.contains(p))||selection.contains(p.getGroupe())) {
					removeFromSelection(p);
				}
				else {
					addToSelection(p);
				}
			}
			else {
				clearSelection();
				addToSelection(p);
			}
			break;
		case DEPLACER_SELECTION:
			break;
		case POINT:
			break;
		case SEGMENT_P1:
			clearSelection();
			addToSelection(p);
			curSegmentP1 = p;
			addLinePreview(couleur, event.getX(), event.getY());
			etat = Etat.SEGMENT_P2;
			break;
		case SEGMENT_P2:
			bpMain.getpZoneDessin().getChildren().remove(curSegment);
			creerSegment(bpMain.getVbCouleurs().getSelecteurCouleur().getCouleur(), curSegmentP1, p);
			curSegmentP1 = null;
			bpMain.getpZoneDessin().dessinerTout();
			clearSelection();
			addToSelection(p);
			curSegmentIsTemp = false;
			etat = Etat.SEGMENT_P1;
			break;
		case EFFACER:
			effacerForme(p);
			bpMain.getpZoneDessin().dessinerTout();
			break;
		case FORCE:
			break;
		}
	}
	
	public void clicSegment(MouseEvent event, Segment s) {
		clicForme = true;
		Color couleur = bpMain.getVbCouleurs().getSelecteurCouleur().getCouleur();
		switch (etat) {
		case DEFAUT:
			break;	
		case SELECTION:
			if (event.isControlDown()) {
				if ((selection.contains(s))||(selection.contains(s.getGroupe()))) {
					removeFromSelection(s);
				}
				else {
					addToSelection(s);
				}
			}
			else {
				clearSelection();
				addToSelection(s);
			}
			break;
		case DEPLACER_SELECTION:
			break;
		case POINT:
			Point p1 = scinderSegment(s, couleur, event.getX(), event.getY());
			bpMain.getpZoneDessin().dessinerTout();
			clearSelection();
			addToSelection(p1);
			break;
		case SEGMENT_P1:
			curSegmentP1 = scinderSegment(s, couleur, event.getX(), event.getY());
			bpMain.getpZoneDessin().dessinerTout();
			clearSelection();
			addToSelection(curSegmentP1);
			addLinePreview(couleur, event.getX(), event.getY());
			curSegmentIsTemp = true;
			etat = Etat.SEGMENT_P2;
			break;
		case SEGMENT_P2:
			bpMain.getpZoneDessin().getChildren().remove(curSegment);
			Point p2 = scinderSegment(s, couleur, event.getX(), event.getY());
			creerSegment(couleur, curSegmentP1, p2);
			curSegmentP1 = null;
			bpMain.getpZoneDessin().dessinerTout();
			clearSelection();
			addToSelection(p2);
			curSegmentIsTemp = false;
			etat = Etat.SEGMENT_P1;
			break;
		case EFFACER:
			effacerForme(s);
			bpMain.getpZoneDessin().dessinerTout();
			break;
		case FORCE:
			break;
		}
	}
	
	public void addLinePreview(Color couleur, double px, double py) {
		curSegment = new Line(px, py, px, py);
		curSegment.setStroke(couleur);
		curSegment.getStyleClass().add("forme-segment-temp");
		bpMain.getpZoneDessin().getChildren().add(0, curSegment);
	}
		
	public Point creerPoint(Color couleur, double px, double py) {
		Point p = new Point(couleur, px, py);
		bpMain.getModele().addFigure(p);
		return p;
	}
	
	public Segment creerSegment(Color couleur, Point pointDepart, Point pointArrivee) {
		Segment s = new Segment(couleur, pointDepart, pointArrivee);
		bpMain.getModele().addFigure(s);
		return s;
	}
	
	public Point scinderSegment(Segment s, Color couleur, double px, double py) {
		bpMain.getModele().removeFigure(s);
		Point p = creerPoint(couleur, px, py);
		creerSegment(couleur, p, s.getPointDepart());
		creerSegment(couleur, p, s.getPointArrivee());
		return p;
	}
		
	public void addToSelection(Figure f) {
		if (f.getGroupe() == bpMain.getModele()) {
			for (FigureSimple fs : f.getFiguresSimples()) {
				fs.getForme().getShape().getStyleClass().add("forme-selection");
			}
			selection.add(f);
		}
		else {
			addToSelection(f.getGroupe());
		}
	}
	
	public void removeFromSelection(Figure f) {
		if (f.getGroupe() == bpMain.getModele()) {
			for (FigureSimple fs : f.getFiguresSimples()) {
				fs.getForme().getShape().getStyleClass().remove("forme-selection");
			}
			selection.remove(f);
		}
		else {
			removeFromSelection(f.getGroupe());
		}
	}
	
	public void clearSelection() {
		for (Figure f : selection) {
			for (FigureSimple fs : f.getFiguresSimples()) {
				fs.getForme().getShape().getStyleClass().remove("forme-selection");
			}
		}
		selection.clear();
	}
	
	public void refreshSelection() {
		for (Figure f : selection) {
			for (FigureSimple fs : f.getFiguresSimples()) {
				fs.getForme().getShape().getStyleClass().add("forme-selection");
			}
		}
	}
	
	public void clicBoutonCouleur(String nom, Color c) {
		bpMain.getVbCouleurs().getSelecteurCouleur().setCouleur(c);
		bpMain.getVbCouleurs().getSelecteurCouleur().getlCouleurChoisie().setText(nom);
	}

	public void clicContact() {                       
        TextField tfMailArthur = new TextField("arthur.bourgeois@insa-strasbourg.fr");
        tfMailArthur.getStyleClass().add("aide-contact-text-field");
        Button bEnvoiMailArthur = new Button("Envoyer un mail à Arthur");
        bEnvoiMailArthur.getStyleClass().add("aide-contact-button");
        bEnvoiMailArthur.setOnAction(event -> {
        	new Main().getHostServices().showDocument("mailto:" + tfMailArthur.getText());
        });
        
        TextField tfMailMelanie = new TextField("melanie.dalibard@insa-strasbourg.fr");
        tfMailMelanie.getStyleClass().add("aide-contact-text-field");
        Button bEnvoiMailMelanie = new Button("Envoyer un mail à Mélanie");
        bEnvoiMailMelanie.getStyleClass().add("aide-contact-button");
        bEnvoiMailMelanie.setOnAction(event -> {
        	new Main().getHostServices().showDocument("mailto:" + tfMailMelanie.getText());
        });
        
        TextField tfMailLouis = new TextField("louis.heitz@insa-strasbourg.fr");
        tfMailLouis.getStyleClass().add("aide-contact-text-field");
        Button bEnvoiMailLouis = new Button("Envoyer un mail à Louis");
        bEnvoiMailLouis.getStyleClass().add("aide-contact-button");
        bEnvoiMailLouis.setOnAction(event -> {
        	new Main().getHostServices().showDocument("mailto:" + tfMailLouis.getText());
        });
        
        Label lTexte = new Label("Si vous avez besoin d'aide, n'hésitez pas à contacter un des membres de notre équipe de \ncodeurs du dimanche : Arthur, Mélanie ou Louis. \nVous n'avez aucun soucis à vous faire, car nous sommes toujours prêts à vous apporter \nnotre expertise pour que votre expérience se déroule au mieux sur notre logiciel !\n ");
        lTexte.getStyleClass().add("aide-contact-label");
        
        VBox vbCorps = new VBox(lTexte, tfMailArthur, bEnvoiMailArthur, tfMailMelanie, bEnvoiMailMelanie, tfMailLouis, bEnvoiMailLouis);
        vbCorps.getStyleClass().add("aide-contact-vbox");
        
        Scene secondaryScene = new Scene(vbCorps);
        secondaryScene.getStylesheets().add("/stylesheets/sombre.css");
    
        Stage newWindow = new Stage();
        newWindow.setTitle("Contact");
        newWindow.setScene(secondaryScene);
    
        newWindow.show();
	}

	public void clicBoutonFermer(VBox vbCur) {
		bpMain.getSpCentre().getChildren().remove(vbCur);
	}

	public void moveMouseZoneDessinLine(MouseEvent move) {
		curSegment.setEndX(move.getX());
		curSegment.setEndY(move.getY());
	}
	
	public void dragMouseZoneDessinDeplacer(MouseEvent drag) {
		if (etat == Etat.DEPLACER_SELECTION) {
			for (Figure f : selection) {
				f.deplacer(drag.getX() - posInitialeSouris[0], drag.getY() - posInitialeSouris[1]);
			}
			posInitialeSouris = new double[] {drag.getX(), drag.getY()};
			bpMain.getpZoneDessin().dessinerTout();
			refreshSelection();
		}
	}
	
	public void setPosInitiale(MouseEvent dragEntered) {
		posInitialeSouris = new double[] {dragEntered.getX(), dragEntered.getY()};
	}

	public void clicGrouper() {
		if (selection.size() > 1) {
			Groupe g = bpMain.getModele().grouper(selection);
			bpMain.getpZoneDessin().dessinerTout();
			clearSelection();
			addToSelection(g);
		}
	}
	
	public void clicScinder() {
		if (selection.size() == 1) {
			List<Figure> elements = bpMain.getModele().scinder(selection.get(0).getFiguresSimples().get(0).getGroupe());
			bpMain.getpZoneDessin().dessinerTout();
			clearSelection();
			for (Figure f : elements) {
				addToSelection(f);
			}
		}
	}

	public void mouseEnterForme(FigureSimple fs) {
		if (fs.getGroupe() != bpMain.getModele()) {
			for (FigureSimple curFs : fs.getGroupe().getFiguresSimples()) {
				curFs.getForme().getShape().getStyleClass().add("forme-hover");
			}
		}
		else {
			fs.getForme().getShape().getStyleClass().add("forme-hover");
		}
	}
	
	public void mouseExitForme(FigureSimple fs) {
		if (fs.getGroupe() != bpMain.getModele()) {
			for (FigureSimple curFs : fs.getGroupe().getFiguresSimples()) {
				curFs.getForme().getShape().getStyleClass().remove("forme-hover");
			}
		}
		else {
			fs.getForme().getShape().getStyleClass().remove("forme-hover");
		}
	}

	public void effacerForme(Figure f) {
		if (f.getGroupe() == bpMain.getModele()) {
			for (Figure dependance : f.getDependance()) {
				bpMain.getModele().removeFigure(dependance);
			}
		}
		else {
			effacerForme(f.getGroupe());
		}
	}

	public void refreshCouleur(Point p, ObservableValue<? extends String> observable, String oldValue, String newValue) {
		try {
			p.setCouleur(Color.web(newValue));
			p.getLigne().getrCouleur().setFill(Color.web(newValue));
			p.getLigne().getTfCouleur().setStyle("-fx-text-fill: #FFFFFF");
		}
		catch (Exception e) {
			p.getLigne().getTfCouleur().setStyle("-fx-text-fill: #FF0000");
		}
	}

	public void refreshPx(Point p, ObservableValue<? extends String> observable, String oldValue, String newValue) {
		try {
			p.setPx(Double.parseDouble(newValue));
			p.getLigne().getTfPx().setStyle("-fx-text-fill: #FFFFFF");
		}
		catch (Exception e) {
			p.getLigne().getTfPx().setStyle("-fx-text-fill: #FF0000");
		}
	}
	
	public void refreshPy(Point p, ObservableValue<? extends String> observable, String oldValue, String newValue) {
		try {
			p.setPy(Double.parseDouble(newValue));
			p.getLigne().getTfPy().setStyle("-fx-text-fill: #FFFFFF");
		}
		catch (Exception e) {
			p.getLigne().getTfPy().setStyle("-fx-text-fill: #FF0000");
		}
	}
	
	public void refreshFx(Point p, ObservableValue<? extends String> observable, String oldValue, String newValue) {
		try {
			p.getNoeud().getV().setVx(Double.parseDouble(newValue));
			p.getLigne().getTfFx().setStyle("-fx-text-fill: #FFFFFF");
		}
		catch (Exception e) {
			p.getLigne().getTfFx().setStyle("-fx-text-fill: #FF0000");
		}
	}
	
	public void refreshFy(Point p, ObservableValue<? extends String> observable, String oldValue, String newValue) {
		try {
			p.getNoeud().getV().setVy(Double.parseDouble(newValue));
			p.getLigne().getTfFy().setStyle("-fx-text-fill: #FFFFFF");
		}
		catch (Exception e) {
			p.getLigne().getTfFy().setStyle("-fx-text-fill: #FF0000");
		}
	}

	public void refreshLine(LigneInformationPoint ligne) {
		ligne.getTfCouleur().setText(String.format("#%02X%02X%02X", (int) (ligne.getP().getCouleur().getRed()*255), (int) (ligne.getP().getCouleur().getGreen()*255), (int) (ligne.getP().getCouleur().getBlue()*255)));
		ligne.getTfPx().setText(String.valueOf(ligne.getP().getPx()));
		ligne.getTfPy().setText(String.valueOf(ligne.getP().getPy()));
		ligne.getTfFx().setText(String.valueOf(ligne.getP().getNoeud().getV().getVx()));
		ligne.getTfFy().setText(String.valueOf(ligne.getP().getNoeud().getV().getVy()));
	}
}
