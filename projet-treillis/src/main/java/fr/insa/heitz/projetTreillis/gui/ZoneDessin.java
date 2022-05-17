package fr.insa.heitz.projetTreillis.gui;

import javafx.css.PseudoClass;
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
	}

	public void dessinerTout() {
		getChildren().addAll(bpMain.getModele().dessine(bpMain.getControleur()));
	}
}
