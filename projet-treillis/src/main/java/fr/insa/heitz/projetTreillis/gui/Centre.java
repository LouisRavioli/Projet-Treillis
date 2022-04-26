package fr.insa.heitz.projetTreillis.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;

public class Centre extends StackPane {

	public Centre() {
		getChildren().add(new Canvas(900, 500));
	}
}
