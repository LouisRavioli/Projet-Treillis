package fr.insa.heitz.projetTreillis.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.insa.heitz.projetTreillis.Barre;
import fr.insa.heitz.projetTreillis.Matrice;
import fr.insa.heitz.projetTreillis.Noeud;
import fr.insa.heitz.projetTreillis.NoeudAppuiGlissant;
import fr.insa.heitz.projetTreillis.NoeudAppuiSimple;
import fr.insa.heitz.projetTreillis.NoeudSimple;
import fr.insa.heitz.projetTreillis.Vecteur2D;
import fr.insa.heitz.projetTreillis.dessin.Banque;
import fr.insa.heitz.projetTreillis.dessin.Figure;
import fr.insa.heitz.projetTreillis.dessin.FigureSimple;
import fr.insa.heitz.projetTreillis.dessin.Groupe;
import fr.insa.heitz.projetTreillis.dessin.Point;
import fr.insa.heitz.projetTreillis.dessin.Segment;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Controleur {
	
	private MainBorderPane bpMain;
	
	public enum Etat {DEFAUT, SELECTION, DEPLACER_SELECTION, POINT, SEGMENT_P1, SEGMENT_P2, APPUI_SIMPLE, APPUI_GLISSANT, TERRAIN_P1, TERRAIN_P2, EFFACER};
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
			bpMain.getTerrain().removeFigure(curSegmentP1);
			bpMain.getTreillis().supprimeNoeud(curSegmentP1.getNoeud());
			bpMain.getVbInformations().removeLignePoint(curSegmentP1);
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
				if (event.isPrimaryButtonDown()) {
					Point p1 = creerPoint(couleur, event.getX(), event.getY());
					bpMain.getpZoneDessin().dessinerTout();
					clearSelection();
					addToSelection(p1);
				}
				break;
			case SEGMENT_P1:
				if (event.isPrimaryButtonDown()) {
					curSegmentP1 = creerPoint(couleur, event.getX(), event.getY());
					bpMain.getpZoneDessin().dessinerTout();
					clearSelection();
					addToSelection(curSegmentP1);
					addLinePreview(couleur, event.getX(), event.getY());
					curSegmentIsTemp = true;
					etat = Etat.SEGMENT_P2;
				}
				break;
			case SEGMENT_P2:
				if (event.isPrimaryButtonDown()) {
					bpMain.getpZoneDessin().getChildren().remove(curSegment);
					Point p2 = creerPoint(couleur, event.getX(), event.getY());
					creerSegment(couleur, curSegmentP1, p2);
					curSegmentP1 = null;
					bpMain.getpZoneDessin().dessinerTout();
					clearSelection();
					addToSelection(p2);
					curSegmentIsTemp = false;
					etat = Etat.SEGMENT_P1;
				}
				break;
			case APPUI_SIMPLE:
				break;
			case APPUI_GLISSANT:
				break;
			case TERRAIN_P1:
				if (event.isPrimaryButtonDown()) {
					curSegmentP1 = creerAngleAppui(event.getX(), event.getY());
					bpMain.getpZoneDessin().dessinerTout();
					clearSelection();
					addToSelection(curSegmentP1);
					addLinePreviewTerrain(event.getX(), event.getY());
					curSegmentIsTemp = true;
					etat = Etat.TERRAIN_P2;
				}
				break;
			case TERRAIN_P2:
				if (event.isPrimaryButtonDown()) {
					bpMain.getpZoneDessin().getChildren().remove(curSegment);
					Point p2 = creerAngleAppui(curSegment.getEndX(), curSegment.getEndY());
					creerPenteAppui(curSegmentP1, p2);
					curSegmentP1 = null;
					bpMain.getpZoneDessin().dessinerTout();
					clearSelection();
					addToSelection(p2);
					curSegmentIsTemp = false;
					etat = Etat.TERRAIN_P1;
				}
				break;
			case EFFACER:
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
			if ((event.isControlDown())&&(event.isPrimaryButtonDown())) {
				if ((selection.contains(p))||selection.contains(p.getGroupe())) {
					removeFromSelection(p);
				}
				else {
					addToSelection(p);
				}
			}
			else if (event.isPrimaryButtonDown()) {
				clearSelection();
				addToSelection(p);
			}
			else if (event.isSecondaryButtonDown()) {
				clearSelection();
			}
			break;
		case DEPLACER_SELECTION:
			break;
		case POINT:
			break;
		case SEGMENT_P1:
			if ((event.isPrimaryButtonDown())&&(!p.getNoeud().isTerrain())) {
				clearSelection();
				addToSelection(p);
				curSegmentP1 = p;
				addLinePreview(couleur, event.getX(), event.getY());
				etat = Etat.SEGMENT_P2;
			}
			break;
		case SEGMENT_P2:
			if ((event.isPrimaryButtonDown())&&(!p.getNoeud().isTerrain())) {
				bpMain.getpZoneDessin().getChildren().remove(curSegment);
				creerSegment(bpMain.getVbCouleurs().getSelecteurCouleur().getCouleur(), curSegmentP1, p);
				curSegmentP1 = null;
				bpMain.getpZoneDessin().dessinerTout();
				clearSelection();
				addToSelection(p);
				curSegmentIsTemp = false;
				etat = Etat.SEGMENT_P1;
			}
			break;
		case APPUI_SIMPLE:
			break;
		case APPUI_GLISSANT:
			break;
		case TERRAIN_P1:
			if ((event.isPrimaryButtonDown())&&(p.getNoeud().isTerrain())) {
				clearSelection();
				addToSelection(p);
				curSegmentP1 = p;
				addLinePreviewTerrain(event.getX(), event.getY());
				etat = Etat.TERRAIN_P2;
			}
			break;
		case TERRAIN_P2:
			if ((event.isPrimaryButtonDown())&&(p.getNoeud().isTerrain())) {
				bpMain.getpZoneDessin().getChildren().remove(curSegment);
				creerPenteAppui(curSegmentP1, p);
				curSegmentP1 = null;
				bpMain.getpZoneDessin().dessinerTout();
				clearSelection();
				addToSelection(p);
				curSegmentIsTemp = false;
				etat = Etat.TERRAIN_P1;
			}
			break;
		case EFFACER:
			if (event.isPrimaryButtonDown()) {
				effacerForme(p);
				bpMain.getpZoneDessin().dessinerTout();
			}
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
			if ((event.isControlDown())&&(event.isPrimaryButtonDown())) {
				if ((selection.contains(s))||(selection.contains(s.getGroupe()))) {
					removeFromSelection(s);
				}
				else {
					addToSelection(s);
				}
			}
			else if (event.isPrimaryButtonDown()) {
				clearSelection();
				addToSelection(s);
			}
			else if (event.isSecondaryButtonDown()) {
				clearSelection();
			}
			break;
		case DEPLACER_SELECTION:
			break;
		case POINT:
			if ((event.isPrimaryButtonDown())&&(!s.getBarre().isTerrain())) {
				Point p1 = scinderSegment(s, couleur, event.getX(), event.getY());
				bpMain.getpZoneDessin().dessinerTout();
				clearSelection();
				addToSelection(p1);
			}
			break;
		case SEGMENT_P1:
			if ((event.isPrimaryButtonDown())&&(!s.getBarre().isTerrain())) {
				curSegmentP1 = scinderSegment(s, couleur, event.getX(), event.getY());
				bpMain.getpZoneDessin().dessinerTout();
				clearSelection();
				addToSelection(curSegmentP1);
				addLinePreview(couleur, event.getX(), event.getY());
				curSegmentIsTemp = true;
				etat = Etat.SEGMENT_P2;
			}
			break;
		case SEGMENT_P2:
			if ((event.isPrimaryButtonDown())&&(!s.getBarre().isTerrain())) {
				bpMain.getpZoneDessin().getChildren().remove(curSegment);
				Point p2 = scinderSegment(s, couleur, event.getX(), event.getY());
				creerSegment(couleur, curSegmentP1, p2);
				curSegmentP1 = null;
				bpMain.getpZoneDessin().dessinerTout();
				clearSelection();
				addToSelection(p2);
				curSegmentIsTemp = false;
				etat = Etat.SEGMENT_P1;
			}
			break;
		case APPUI_SIMPLE:
			if ((event.isPrimaryButtonDown())&&(s.getBarre().isTerrain())) {
				Point p = creerAppuiSimple(s, event.getX(), event.getY());
				bpMain.getpZoneDessin().dessinerTout();
				clearSelection();
				addToSelection(p);
			}
			break;
		case APPUI_GLISSANT:
			if ((event.isPrimaryButtonDown())&&(s.getBarre().isTerrain())) {
				Point p = creerAppuiGlissant(s, event.getX(), event.getY());
				bpMain.getpZoneDessin().dessinerTout();
				clearSelection();
				addToSelection(p);
			}
			break;
		case TERRAIN_P1:
			break;
		case TERRAIN_P2:
			break;
		case EFFACER:
			if (event.isPrimaryButtonDown()) {
				effacerForme(s);
				bpMain.getpZoneDessin().dessinerTout();
			}
			break;
		}
	}

	public void addLinePreview(Color couleur, double px, double py) {
		curSegment = new Line(px, py, px, py);
		curSegment.setStroke(couleur);
		curSegment.getStyleClass().add("forme-segment-temp");
		bpMain.getpZoneDessin().getChildren().add(0, curSegment);
	}
	
	private void addLinePreviewTerrain(double px, double py) {
		curSegment = new Line(px, py, px, py);
		curSegment.setStroke(Color.GREEN);
		curSegment.getStyleClass().add("forme-segment-temp-terrain");
		bpMain.getpZoneDessin().getChildren().add(0, curSegment);
	}
		
	public Point creerPoint(Color couleur, double px, double py) {
		Point p = new Point(couleur, px, py);
		p.setNoeud(new NoeudSimple(px, bpMain.getpZoneDessin().getHeight() - py));
		bpMain.getModele().addFigure(p);
		bpMain.getTreillis().ajouteNoeud(p.getNoeud());
		bpMain.getVbInformations().addLignePoint(p);
		return p;
	}
	
	public Segment creerSegment(Color couleur, Point pointDepart, Point pointArrivee) {
		Segment s = new Segment(couleur, pointDepart, pointArrivee);
		s.setBarre(new Barre(pointDepart.getNoeud(), pointArrivee.getNoeud()));
		bpMain.getModele().addFigure(s);
		bpMain.getTreillis().ajouteBarre(s.getBarre());
		bpMain.getVbInformations().addLigneSegment(s);
		return s;
	}
	
	public Point scinderSegment(Segment s, Color couleur, double px, double py) {
		bpMain.getModele().removeFigure(s);
		bpMain.getTreillis().supprimeBarre(s.getBarre());
		bpMain.getVbInformations().removeLigneSegment(s);
		Point p = creerPoint(couleur, px, py);
		creerSegment(couleur, p, s.getPointDepart());
		creerSegment(couleur, p, s.getPointArrivee());
		return p;
	}
	
	
	private Point creerAngleAppui(double px, double py) {
		Point p = new Point(Color.GREEN, px, py);
		p.setNoeud(new NoeudSimple(px, bpMain.getpZoneDessin().getHeight() - py));
		p.getNoeud().setTerrain(true);
		bpMain.getTerrain().addFigure(p);
		bpMain.getTreillis().ajouteNoeud(p.getNoeud());
		bpMain.getVbInformations().addLignePoint(p);
		return p;
	}
	
	private Segment creerPenteAppui(Point pointDepart, Point pointArrivee) {
		Segment s = new Segment(Color.GREEN, pointDepart, pointArrivee);
		s.setBarre(new Barre(pointDepart.getNoeud(), pointArrivee.getNoeud()));
		s.getBarre().setTerrain(true);
		bpMain.getTerrain().addFigure(s);
		bpMain.getTreillis().ajouteBarre(s.getBarre());
		bpMain.getVbInformations().addLigneSegment(s);
		return s;
	}
	
	private Point creerAppuiSimple(Segment s, double px, double py) {
		Point p = new Point(Color.BLUE, px, py);
		p.setNoeud(new NoeudAppuiSimple(px, bpMain.getpZoneDessin().getHeight() - py, new Vecteur2D(0, 0), s.getBarre()));
		bpMain.getModele().addFigure(p);
		bpMain.getTreillis().ajouteNoeud(p.getNoeud());
		bpMain.getVbInformations().addLignePoint(p);
		return p;
	}
	
	private Point creerAppuiGlissant(Segment s, double px, double py) {
		Point p = new Point(Color.YELLOW, px, py);
		p.setNoeud(new NoeudAppuiGlissant(px, bpMain.getpZoneDessin().getHeight() - py, new Vecteur2D(0, 0), s.getBarre()));
		bpMain.getModele().addFigure(p);
		bpMain.getTreillis().ajouteNoeud(p.getNoeud());
		bpMain.getVbInformations().addLignePoint(p);
		return p;
	}
		
	public void addToSelection(Figure f) {
		if ((f.getGroupe() == bpMain.getModele())||f.getGroupe() == bpMain.getTerrain()) {
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

	public void clicBoutonFermer(VBox vbCur, ToggleButton toggleButton) {
		bpMain.getSpCentre().getChildren().remove(vbCur);
		toggleButton.setSelected(false);
	}

	public void moveMouseZoneDessinLine(MouseEvent move) {
		curSegment.setEndX(move.getX());
		curSegment.setEndY(move.getY());
	}
	
	public void dragMouseZoneDessinDeplacer(MouseEvent drag) {
		if (etat == Etat.DEPLACER_SELECTION) {
			for (Figure f : selection) {
				f.deplacer(bpMain.getpZoneDessin(), drag.getX() - posInitialeSouris[0], drag.getY() - posInitialeSouris[1]);
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
		if ((selection.size() > 1)&&(!contientFigureTerrain(selection))) {
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
	
	public boolean contientFigureTerrain(List<Figure> figures) {
		boolean contient = false;
		for (Figure f : figures) {
			for (FigureSimple fs : f.getFiguresSimples()) {
				if (fs.getGroupe() == bpMain.getTerrain()) {
					contient = true;
				}
			}
		}
		return contient;
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
		if ((f.getGroupe() == bpMain.getModele())||(f.getGroupe() == bpMain.getTerrain())) {
			for (Figure dependance : f.getDependance()) {
				bpMain.getModele().removeFigure(dependance);
				bpMain.getTerrain().removeFigure(dependance);
				dependance.supprimeDuTreillis(bpMain.getTreillis());
				dependance.supprimeDeInformations(bpMain.getVbInformations());
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
			bpMain.getpZoneDessin().dessinerTout();
		}
		catch (Exception e) {
			p.getLigne().getTfCouleur().setStyle("-fx-text-fill: #FF0000");
		}
	}

	public void refreshPx(Point p, ObservableValue<? extends String> observable, String oldValue, String newValue) {
		try {
			p.setPx(Double.parseDouble(newValue));
			p.getNoeud().setPx(Double.parseDouble(newValue));
			p.getLigne().getTfPx().setStyle("-fx-text-fill: #FFFFFF");
			bpMain.getpZoneDessin().dessinerTout();
		}
		catch (Exception e) {
			p.getLigne().getTfPx().setStyle("-fx-text-fill: #FF0000");
		}
	}
	
	public void refreshPy(Point p, ObservableValue<? extends String> observable, String oldValue, String newValue) {
		try {
			p.setPy(bpMain.getpZoneDessin().getHeight() - Double.parseDouble(newValue));
			p.getNoeud().setPy(Double.parseDouble(newValue));
			p.getLigne().getTfPy().setStyle("-fx-text-fill: #FFFFFF");
			bpMain.getpZoneDessin().dessinerTout();
		}
		catch (Exception e) {
			p.getLigne().getTfPy().setStyle("-fx-text-fill: #FF0000");
		}
	}
	
	public void refreshFx(Point p, ObservableValue<? extends String> observable, String oldValue, String newValue) {
		try {
			p.getNoeud().getV().setVx(Double.parseDouble(newValue));
			p.getLigne().getTfFx().setStyle("-fx-text-fill: #FFFFFF");
			bpMain.getpZoneDessin().dessinerTout();
		}
		catch (Exception e) {
			p.getLigne().getTfFx().setStyle("-fx-text-fill: #FF0000");
		}
	}
	
	public void refreshFy(Point p, ObservableValue<? extends String> observable, String oldValue, String newValue) {
		try {
			p.getNoeud().getV().setVy(Double.parseDouble(newValue));
			p.getLigne().getTfFy().setStyle("-fx-text-fill: #FFFFFF");
			bpMain.getpZoneDessin().dessinerTout();
		}
		catch (Exception e) {
			p.getLigne().getTfFy().setStyle("-fx-text-fill: #FF0000");
		}
	}

	public void refreshLinePoint(LigneInformationPoint ligne) {
		ligne.getTfCouleur().setText(String.format("#%02X%02X%02X", (int) (ligne.getP().getCouleur().getRed()*255), (int) (ligne.getP().getCouleur().getGreen()*255), (int) (ligne.getP().getCouleur().getBlue()*255)));
		ligne.getTfPx().setText(String.valueOf(ligne.getP().getNoeud().getPx()));
		ligne.getTfPy().setText(String.valueOf(ligne.getP().getNoeud().getPy()));
		ligne.getTfFx().setText(String.valueOf(ligne.getP().getNoeud().getV().getVx()));
		ligne.getTfFy().setText(String.valueOf(ligne.getP().getNoeud().getV().getVy()));
	}

	public void refreshCouleur(Segment s, ObservableValue<? extends String> observable, String oldValue, String newValue) {
		try {
			s.setCouleur(Color.web(newValue));
			s.getLigne().getrCouleur().setFill(Color.web(newValue));
			s.getLigne().getTfCouleur().setStyle("-fx-text-fill: #FFFFFF");
			bpMain.getpZoneDessin().dessinerTout();
		}
		catch (Exception e) {
			s.getLigne().getTfCouleur().setStyle("-fx-text-fill: #FF0000");
		}
	}
	
	public void refreshComp(Segment s, ObservableValue<? extends String> observable, String oldValue, String newValue) {
		try {
			s.getBarre().setCompMax(Double.parseDouble(newValue));
			s.getLigne().getTfComp().setStyle("-fx-text-fill: #FFFFFF");
			bpMain.getpZoneDessin().dessinerTout();
		}
		catch (Exception e) {
			s.getLigne().getTfComp().setStyle("-fx-text-fill: #FF0000");
		}
	}
	
	public void refreshTrac(Segment s, ObservableValue<? extends String> observable, String oldValue, String newValue) {
		try {
			s.getBarre().setTracMax(Double.parseDouble(newValue));
			s.getLigne().getTfTrac().setStyle("-fx-text-fill: #FFFFFF");
			bpMain.getpZoneDessin().dessinerTout();
		}
		catch (Exception e) {
			s.getLigne().getTfTrac().setStyle("-fx-text-fill: #FF0000");
		}
	}
	
	public void refreshCout(Segment s, ObservableValue<? extends String> observable, String oldValue, String newValue) {
		try {
			s.getBarre().setCout(Double.parseDouble(newValue));
			s.getLigne().getTfCout().setStyle("-fx-text-fill: #FFFFFF");
			bpMain.getpZoneDessin().dessinerTout();
		}
		catch (Exception e) {
			s.getLigne().getTfCout().setStyle("-fx-text-fill: #FF0000");
		}
	}
	
	public void refreshLineSegment(LigneInformationSegment ligne) {
		ligne.getTfCouleur().setText(String.format("#%02X%02X%02X", (int) (ligne.getS().getCouleur().getRed()*255), (int) (ligne.getS().getCouleur().getGreen()*255), (int) (ligne.getS().getCouleur().getBlue()*255)));
		ligne.getTfComp().setText(String.valueOf(ligne.getS().getBarre().getCompMax()));
		ligne.getTfTrac().setText(String.valueOf(ligne.getS().getBarre().getTracMax()));
		ligne.getTfCout().setText(String.valueOf(ligne.getS().getBarre().getCout()));
	}

	public void clicInformationP1(LigneInformationSegment ligne) {
		ligne.getS().getPointDepart().getLigne().setExpanded(true);
	}
	
	public void clicInformationP2(LigneInformationSegment ligne) {
		ligne.getS().getPointArrivee().getLigne().setExpanded(true);
	}

	public void openInformationsPoint(LigneInformationPoint ligne) {
		if (ligne.isExpanded()) {
			clearSelection();
			addToSelection(ligne.getP());
		}
	}
	
	public void openInformationsSegment(LigneInformationSegment ligne) {
		if (ligne.isExpanded()) {
			clearSelection();
			addToSelection(ligne.getS());
		}
	}
	
	public void clicMoletteZoneDessin(MouseEvent event) {
        if (event.isMiddleButtonDown()) {
        	bpMain.getpZoneDessin().setMouseAnchorX(event.getSceneX());
        	bpMain.getpZoneDessin().setMouseAnchorY(event.getSceneY());
        	bpMain.getpZoneDessin().setTranslateAnchorX(bpMain.getpZoneDessin().getTranslateX());
        	bpMain.getpZoneDessin().setTranslateAnchorY(bpMain.getpZoneDessin().getTranslateY());
        }
    }
	
	public void dragMoletteZoneDessin(MouseEvent event) {
		if (event.isMiddleButtonDown()) {
			bpMain.getpZoneDessin().setTranslateX(bpMain.getpZoneDessin().getTranslateAnchorX() + event.getSceneX() - bpMain.getpZoneDessin().getMouseAnchorX());
			bpMain.getpZoneDessin().setTranslateY(bpMain.getpZoneDessin().getTranslateAnchorY() + event.getSceneY() - bpMain.getpZoneDessin().getMouseAnchorY());
		}
	}

	public void zoomZoneDessin(ScrollEvent event) {
	    double scale = bpMain.getpZoneDessin().getScale();
	    double oldScale = scale;
	    if (event.getDeltaY() < 0) {
	        scale /= 1.1;
	    }
	    else {
	        scale *= 1.1;
	    }
	    scale = clamp(scale, ZoneDessin.MIN_SCALE, ZoneDessin.MAX_SCALE);

	    double f = (scale/oldScale) - 1;
	    double dx = (event.getSceneX() - (bpMain.getpZoneDessin().getBoundsInParent().getWidth()/2 + bpMain.getpZoneDessin().getBoundsInParent().getMinX()));
	    double dy = (event.getSceneY() - (bpMain.getpZoneDessin().getBoundsInParent().getHeight()/2 + bpMain.getpZoneDessin().getBoundsInParent().getMinY()));

	    bpMain.getpZoneDessin().setScale(scale);
	    bpMain.getpZoneDessin().setPivot(f*dx, f*dy);
	}

	public double clamp(double value, double min, double max) {
		if (value < min) {
			return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

	public void keyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ESCAPE) {
			changeEtat(Etat.DEFAUT);
			bpMain.getVbOutils().getTbSelection().setSelected(false);
			bpMain.getVbOutils().getTbDeplacerSelection().setSelected(false);
			bpMain.getVbOutils().getTbNoeud().setSelected(false);
			bpMain.getVbOutils().getTbBarre().setSelected(false);
			bpMain.getVbOutils().getTbAppuiSimple().setSelected(false);
			bpMain.getVbOutils().getTbAppuiGlissant().setSelected(false);
			bpMain.getVbOutils().getTbTerrain().setSelected(false);
			bpMain.getVbOutils().getTbEffacer().setSelected(false);
		}
		else if (event.getCode() == KeyCode.BACK_SPACE) {
			for (Figure f : selection) {
				effacerForme(f);
				bpMain.getpZoneDessin().dessinerTout();
			}
		}
	}

	public void calculForces() throws Exception {
		Map<Barre,Integer> colonnesBarres = new HashMap<Barre,Integer>();
		Map<Noeud,Integer> colonnesNoeuds = new HashMap<Noeud,Integer>();
		for (Barre b : bpMain.getTreillis().getBarres().keySet()) {
			if (!b.isTerrain()) {
				colonnesBarres.put(b, colonnesBarres.size());
			}
		}
		int cur = colonnesBarres.size();
		for (Noeud n : bpMain.getTreillis().getNoeuds().keySet()) {
			if (!n.isTerrain()) {
				colonnesNoeuds.put(n, cur);
				cur += n.nbrInconnues();
			}
		}
		int ni = colonnesBarres.size();
		for (Noeud n : colonnesNoeuds.keySet()) {
			ni += n.nbrInconnues();
		}
		if (ni == 2*colonnesNoeuds.size()) {
			Matrice m = new Matrice(2*colonnesNoeuds.size(), ni + 1);
			int ligne = 0;
			for (Noeud n : colonnesNoeuds.keySet()) {
				for (Barre b : n.barresIncidentes()) {
					if (bpMain.getTreillis().getBarres().containsKey(b)) {
						m.setCoeff(ligne, colonnesBarres.get(b), Math.cos(b.angle(n)));
						m.setCoeff(ligne + 1, colonnesBarres.get(b), Math.sin(b.angle(n)));
						m.setCoeff(ligne, ni, -n.getV().getVx());
						m.setCoeff(ligne + 1, ni, -n.getV().getVy());
						n.remplirMatrice(m, ligne, colonnesNoeuds.get(n));
					}
				}
				ligne += 2;
			}
			try {
				m.descenteGauss();
			}
			catch (Exception e) {
				System.out.println(e);
			}
			m.unitaire();
			try {
				m.remonteeGauss();
			}
			catch (Exception e) {
				System.out.println(e);
			}
			System.out.println(m);
			
			GridPane gpForces = new GridPane();
			gpForces.getStyleClass().add("calcul-grid-pane");
			Label lTrac = new Label("Barres en traction");
			Label lComp = new Label("Barres en compression");
			Label lNorm = new Label("Forces normales aux appuis");
			
			GridPane gpTrac = new GridPane();
			gpTrac.getStyleClass().add("calcul-grid-pane-forces");
			GridPane gpComp = new GridPane();
			gpComp.getStyleClass().add("calcul-grid-pane-forces");
			GridPane gpNorm = new GridPane();
			gpNorm.getStyleClass().add("calcul-grid-pane-forces");
			gpTrac.add(new Label("Barre"), 0, 0);
			gpTrac.add(new Label("Force calculée"), 1, 0);
			gpTrac.add(new Label("Force maximum"), 2, 0);
			gpComp.add(new Label("Barre"), 0, 0);
			gpComp.add(new Label("Force calculée"), 1, 0);
			gpComp.add(new Label("Force maximum"), 2, 0);
			gpNorm.add(new Label("Appui"), 0, 0);
			gpNorm.add(new Label("Force calculée"), 1, 0);
			int i = 1;
			int j = 1;
			int k = 1;
			for (Barre b : colonnesBarres.keySet()) {
				if (m.getCoeff(colonnesBarres.get(b), ni) >= 0) {
					Label curLabel = new Label(String.valueOf(m.getCoeff(colonnesBarres.get(b), ni)));
					if (m.getCoeff(colonnesBarres.get(b), ni) > b.getTracMax()) {
						curLabel.setStyle("-fx-text-fill: #FF0000");
					}
					gpTrac.add(new Label(String.valueOf(bpMain.getTreillis().getBarres().get(b))), 0, i);
					gpTrac.add(curLabel, 1, i);
					gpTrac.add(new Label(String.valueOf(b.getTracMax())), 2, i);
					i++;
				}
				else {
					Label curLabel = new Label(String.valueOf(m.getCoeff(colonnesBarres.get(b), ni)));
					if (m.getCoeff(colonnesBarres.get(b), ni) < b.getCompMax()) {
						curLabel.setStyle("-fx-text-fill: #FF0000");
					}
					gpComp.add(new Label(String.valueOf(bpMain.getTreillis().getBarres().get(b))), 0, j);
					gpComp.add(curLabel, 1, j);
					gpComp.add(new Label(String.valueOf(b.getCompMax())), 2, j);
					j++;
				}
			}
			for (Noeud n : colonnesNoeuds.keySet()) {
				if (n.nbrInconnues() == 1) {
					gpNorm.add(new Label(String.valueOf(bpMain.getTreillis().getNoeuds().get(n))), 0, k);
					gpNorm.add(new Label(String.valueOf(m.getCoeff(colonnesNoeuds.get(n), ni))), 1, k);
					k++;
				}
				else if (n.nbrInconnues() == 2) {
					gpNorm.add(new Label(String.valueOf(bpMain.getTreillis().getNoeuds().get(n)) + " (x)"), 0, k);
					gpNorm.add(new Label(String.valueOf(bpMain.getTreillis().getNoeuds().get(n)) + " (y)"), 0, k + 1);
					gpNorm.add(new Label(String.valueOf(m.getCoeff(colonnesNoeuds.get(n), ni))), 1, k);
					gpNorm.add(new Label(String.valueOf(m.getCoeff(colonnesNoeuds.get(n) + 1, ni))), 1, k + 1);
					k += 2;
				}
			}
			
			
			ScrollPane spTrac = new ScrollPane(gpTrac);
			spTrac.getStyleClass().add("calcul-scroll-pane");
			ScrollPane spComp = new ScrollPane(gpComp);
			spComp.getStyleClass().add("calcul-scroll-pane");
			ScrollPane spNorm = new ScrollPane(gpNorm);
			spNorm.getStyleClass().add("calcul-scroll-pane");
			
			gpForces.add(lTrac, 0, 0);
			gpForces.add(lComp, 1, 0);
			gpForces.add(lNorm, 2, 0);
			gpForces.add(spTrac, 0, 1);
			gpForces.add(spComp, 1, 1);
			gpForces.add(spNorm, 2, 1);
			
			Scene secondaryScene = new Scene(gpForces);
	        secondaryScene.getStylesheets().add("/stylesheets/sombre.css");
	    
	        Stage newWindow = new Stage();
	        newWindow.setTitle("Forces");
	        newWindow.setScene(secondaryScene);
	    
	        newWindow.show();
		}
		else {
			throw new Exception("Treillis non isostatique");
		}
	}

	public void doubleClicPoint(MouseEvent event, Point point) {
		if ((etat == Etat.DEFAUT)&&(event.getButton().equals(MouseButton.PRIMARY))&&(event.getClickCount() == 2)) {
			point.getLigne().setExpanded(!point.getLigne().isExpanded());
		}
	}
	
	public void doubleClicSegment(MouseEvent event, Segment segment) {
		if ((etat == Etat.DEFAUT)&&(event.getButton().equals(MouseButton.PRIMARY))&&(event.getClickCount() == 2)) {
			segment.getLigne().setExpanded(!segment.getLigne().isExpanded());
		}
	}
	
	public void clicOpen() {
        Label lTexte = new Label("Saisir le nom du fichier à ouvrir.");
        lTexte.getStyleClass().add("enregistrement-label");
        TextField tfOpen = new TextField();
        tfOpen.getStyleClass().add("enregistrement-text-field");
        Button bOpen = new Button("Ouvrir");
        bOpen.getStyleClass().add("enregistrement-button");
        
        Stage newWindow = new Stage();
        
        VBox vbCorps = new VBox(lTexte,tfOpen,bOpen);
        vbCorps.getStyleClass().add("enregistrement-vbox");
        
        Scene secondaryScene = new Scene(vbCorps, 220, 150);
        secondaryScene.getStylesheets().add("/stylesheets/sombre.css");

        newWindow.setTitle("Ouvrir");
        newWindow.setScene(secondaryScene);

        newWindow.show();
        
        bOpen.setOnAction(event -> {
            bpMain.getControleur().clicOuvrir(tfOpen.getText());
            newWindow.close();
        });
	}
	
	public void clicOuvrir(String s) {
		System.out.println("Méthode non implémentée");
	    /*try {
			BufferedReader in = new BufferedReader(new FileReader(s + ".txt"));
			String ligne;
			Map<Integer,Noeud> idNoeuds = new HashMap<Integer,Noeud>();
			while ((ligne = in.readLine()) != null) {
				String[] mots = ligne.split(";");
	        }
	        in.close();
	    }
	    catch (FileNotFoundException e) {
	        System.out.println("Erreur : Le fichier n'existe pas !\n" + e);
		}
	    catch (IOException e) {
	    	System.out.println(e);
	    }*/
	}
	
	public void clicEnregistrer() {
        Label lTexte = new Label("Saisir le nom du fichier.\nIl sera enregistré dans le dossier du projet.");
        lTexte.getStyleClass().add("enregistrement-label");
        TextField tfSauvegarde = new TextField();
        tfSauvegarde.getStyleClass().add("enregistrement-text-field");
        Button bSauvegarder = new Button("Sauvegarder");
        bSauvegarder.getStyleClass().add("enregistrement-button");
        
        Stage newWindow = new Stage();
        
        VBox vbCorps = new VBox(lTexte,tfSauvegarde,bSauvegarder);
        vbCorps.getStyleClass().add("enregistrement-vbox");
        
        Scene secondaryScene = new Scene(vbCorps, 260, 150);
        secondaryScene.getStylesheets().add("/stylesheets/sombre.css");

        newWindow.setTitle("Enregistrement");
        newWindow.setScene(secondaryScene);

        newWindow.show();
        
        bSauvegarder.setOnAction(event -> {
            bpMain.getControleur().clicSauvegarder(tfSauvegarde.getText());
            newWindow.close();
        });
    }
	
	public void clicSauvegarder(String s) {
	    try {
			BufferedWriter out = new BufferedWriter(new FileWriter(s + ".txt", false));
			for (Noeud n : bpMain.getTreillis().getNoeuds().keySet()) {
				out.write("id:" + bpMain.getTreillis().getNoeuds().get(n) + ";type:" + n.nbrInconnues() + ";px:" + n.getPx() + ";py" + n.getPy() + ";fx:" + n.getV().getVx() + ";fy:" + n.getV().getVy() + ";terrain:" + n.isTerrain());
				out.write(";barre:" + n.barreToSave());
				out.newLine();
			}
			out.newLine();
			for (Barre b  : bpMain.getTreillis().getBarres().keySet()) {
				out.write("id:" + bpMain.getTreillis().getBarres().get(b) + ";p1:" + bpMain.getTreillis().getNoeuds().get(b.getNoeudDepart()) + ";p2:" + bpMain.getTreillis().getNoeuds().get(b.getNoeudArrivee()) + ";trac:" + b.getTracMax() + ";comp:" + b.getCompMax() + ";cout:" + b.getCout() + ";terrain:" + b.isTerrain());
				out.newLine();
			}
			out.close();
	    }
	    catch (IOException e) {
			System.out.println("Erreur :\n" + e);
		}
	}

	public void clicNouveau() {
		selection.clear();
		bpMain.getModele().getFigures().clear();
		bpMain.getTerrain().getFigures().clear();
		bpMain.getTreillis().getNoeuds().clear();
		bpMain.getTreillis().getBarres().clear();
		bpMain.getVbInformations().getVbContainer().getChildren().clear();
		bpMain.getpZoneDessin().dessinerTout();
	}

	public void clicSample1() {
		//TODO
	}

	public void clicToggleButtonVBox(VBox vBox, ToggleButton toggleButton) {
		if (toggleButton.isSelected()) {
			bpMain.getSpCentre().getChildren().add(vBox);
		}
		else {
			clicBoutonFermer(vBox, toggleButton);
		}
	}
}
