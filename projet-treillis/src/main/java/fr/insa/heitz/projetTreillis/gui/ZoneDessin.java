package fr.insa.heitz.projetTreillis.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ZoneDessin extends Pane {
	
	private MainBorderPane bpMain;
	
	public ZoneDessin(MainBorderPane bpMain) {
		this.bpMain = bpMain;
		
		dessinerTout();
		
		getStyleClass().add("zone-dessin-pane");
		
		setOnMouseClicked(event -> {
			bpMain.getControleur().clicZoneDessin(event);
		});
		
		setOnMouseMoved(move -> {
			bpMain.getControleur().moveMouseZoneDessinLine(move);
		});
		
		setOnMousePressed(dragEntered -> {
			bpMain.getControleur().setPosInitiale(dragEntered);
		});
		
		setOnMouseDragged(drag -> {
			bpMain.getControleur().dragMouseZoneDessinDeplacer(drag);
		});		
	}

	public void dessinerTout() {
		getChildren().clear();
		List<Node> formes = new ArrayList<Node>();
		bpMain.getModele().dessine(bpMain.getControleur(), formes);
		getChildren().addAll(formes);
	}
}
