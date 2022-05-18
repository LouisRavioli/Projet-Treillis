package fr.insa.heitz.projetTreillis.dessin;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class CustomLine extends Line {

	private Segment segment;
	
	public CustomLine(Color couleur, double x1, double y1, double x2, double y2, Segment segment) {
		super(x1, y1, x2, y2);
		setStroke(couleur);
		this.segment = segment;
		getStyleClass().add("forme-segment");
	}

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}
}
