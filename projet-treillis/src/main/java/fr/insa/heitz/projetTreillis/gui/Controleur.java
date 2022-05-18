package fr.insa.heitz.projetTreillis.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import fr.insa.heitz.projetTreillis.dessin.Point;
import fr.insa.heitz.projetTreillis.dessin.Segment;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Controleur {
	
	private MainBorderPane bpMain;
	
	public enum Etat {DEFAUT, SELECTION, DEPLACER_SELECTION, POINT, SEGMENT_P1, SEGMENT_P2};
	private Etat etat;
	
	private Line curSegment;
	private Point curSegmentP1;
	private boolean clicForme;
	private List<Node> selection;
    
	public Controleur(MainBorderPane bpMain) {
		this.bpMain = bpMain;
		etat = Etat.DEFAUT;
		clicForme = false;
		selection = new ArrayList<Node>();
	}
	
	public void changeEtat(Etat nouvelEtat) {
		etat = nouvelEtat;
		if ((nouvelEtat != Etat.SELECTION)&&(nouvelEtat != Etat.DEPLACER_SELECTION)) {
			clearSelection();
		}
		bpMain.getModele().removeFigure(curSegmentP1);
		bpMain.getpZoneDessin().getChildren().remove(curSegment);
		bpMain.getpZoneDessin().dessinerTout();
	}

	public void clicZoneDessin(MouseEvent event) {
		if (clicForme) {
			clicForme = false;
		}
		else {
			switch (etat) {
			case DEFAUT:
				break;	
			case SELECTION:
				clearSelection();
				break;
			case DEPLACER_SELECTION:
				break;
			case POINT:
				creerPoint(bpMain.getVbCouleurs().getSelecteurCouleur().getCouleur(), event.getX(), event.getY());
				bpMain.getpZoneDessin().dessinerTout();
				clearSelection();
				addToSelection(bpMain.getpZoneDessin().getChildren().get(bpMain.getpZoneDessin().getChildren().size() - 1));
				break;
			case SEGMENT_P1:
				curSegmentP1 = creerPoint(bpMain.getVbCouleurs().getSelecteurCouleur().getCouleur(), event.getX(), event.getY());
				bpMain.getpZoneDessin().dessinerTout();
				clearSelection();
				addToSelection(bpMain.getpZoneDessin().getChildren().get(bpMain.getpZoneDessin().getChildren().size() - 1));
				curSegment = new Line(event.getX(), event.getY(), event.getX(), event.getY());
				curSegment.setStroke(bpMain.getVbCouleurs().getSelecteurCouleur().getCouleur());
				curSegment.getStyleClass().add("forme-segment-temp");
				bpMain.getpZoneDessin().setOnMouseMoved(move -> {
					curSegment.setEndX(move.getX());
					curSegment.setEndY(move.getY());
				});
				bpMain.getpZoneDessin().getChildren().add(bpMain.getpZoneDessin().getChildren().size() - 2, curSegment);
				etat = Etat.SEGMENT_P2;
				break;
			case SEGMENT_P2:
				bpMain.getpZoneDessin().getChildren().remove(curSegment);
				bpMain.setOnMouseMoved(null);
				creerSegment(bpMain.getVbCouleurs().getSelecteurCouleur().getCouleur(), curSegmentP1, creerPoint(bpMain.getVbCouleurs().getSelecteurCouleur().getCouleur(), event.getX(), event.getY()));
				Collections.swap(bpMain.getModele().getFigures(), bpMain.getModele().getFigures().size() - 1, bpMain.getModele().getFigures().size() - 3);
				bpMain.getpZoneDessin().dessinerTout();
				clearSelection();
				addToSelection(bpMain.getpZoneDessin().getChildren().get(bpMain.getpZoneDessin().getChildren().size() - 2));
				etat = Etat.SEGMENT_P1;
				break;
			}
		}
	}
		
	public void clicZoneDessin(MouseEvent event, Ellipse e) {
		clicForme = true;
		switch (etat) {
		case DEFAUT:
			break;	
		case SELECTION:
			if (!event.isControlDown()) {
				clearSelection();
			}
			addToSelection(e);
			break;
		case DEPLACER_SELECTION:
			break;
		case POINT:
			clearSelection();
			addToSelection(e);
			break;
		case SEGMENT_P1:
			//TODO curSegmentP1 = 
			break;
		case SEGMENT_P2:
			break;
		}
	}
	
	public void clicZoneDessin(MouseEvent event, Line l) {
		clicForme = true;
		switch (etat) {
		case DEFAUT:
			break;	
		case SELECTION:
			if (!event.isControlDown()) {
				clearSelection();
			}
			addToSelection(l);
			break;
		case DEPLACER_SELECTION:
			break;
		case POINT:
			clearSelection();
			break;
		case SEGMENT_P1:
			break;
		case SEGMENT_P2:
			break;	
		}
	}
	
	public Point creerPoint(Color couleur, double px, double py) {
		Point p = new Point(couleur, px, py);
		bpMain.getModele().addFigure(p);
		return p;
	}
	
	public Segment creerSegment(Color couleur, Point pointDepart, Point pointArrivee) {
		Segment s = new Segment(couleur ,pointDepart, pointArrivee);
		bpMain.getModele().addFigure(s);
		return s;
	}

	public void clicBoutonCouleur(String nom, Color c) {
		bpMain.getVbCouleurs().getSelecteurCouleur().setCouleur(c);
		bpMain.getVbCouleurs().getSelecteurCouleur().getlCouleurChoisie().setText(nom);
	}
	
	public void addToSelection(Node n) {
		n.getStyleClass().add("forme-selection");
		selection.add(n);
	}
	
	public void removeFromSelection(Node n) {
		n.getStyleClass().remove("forme-selection");
		selection.remove(n);
	}
	
	public void clearSelection() {
		for (Node n : selection) {
			n.getStyleClass().remove("forme-selection");
		}
		selection.clear();
	}
	
	public Point nouveauPoint(Color couleur, double px, double py) {
		Point p = new Point(couleur, px, py);
		return p;
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
}
