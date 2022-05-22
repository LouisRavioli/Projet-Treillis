package fr.insa.heitz.projetTreillis.dessin;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class CustomLine extends Forme {
	
	private Segment segment;
	
	public CustomLine(Color couleur, double x1, double y1, double x2, double y2, Segment segment, boolean terrain) {
		super(segment);
		this.segment = segment;
		setShape(new Line(x1, y1, x2, y2));
		if (terrain) {
			getShape().getStyleClass().add("forme-pente");
		}
		else {
			getShape().getStyleClass().add("forme-segment");
		}
		getShape().setStroke(couleur);
	}

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}
}
