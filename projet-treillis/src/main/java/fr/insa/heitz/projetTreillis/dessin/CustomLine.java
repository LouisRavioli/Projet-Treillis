package fr.insa.heitz.projetTreillis.dessin;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class CustomLine extends Forme {
	
	private Segment segment;
	
	public CustomLine(Color couleur, double x1, double y1, double x2, double y2, Segment segment) {
		super(new Line(x1, y1, x2, y2), segment);
		this.segment = segment;
		getShape().setStroke(couleur);
		getShape().getStyleClass().add("forme-segment");
	}

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}
}
