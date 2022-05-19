package fr.insa.heitz.projetTreillis.dessin;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class CustomEllipse extends Forme {
	
	private Point point;
	
	public CustomEllipse(Color couleur, double px, double py, double rx, double ry, Point point) {
		super(new Ellipse(px, py, rx, ry), point);
		this.point = point;
		getShape().setFill(couleur);
		getShape().getStyleClass().add("forme-point");
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
}
